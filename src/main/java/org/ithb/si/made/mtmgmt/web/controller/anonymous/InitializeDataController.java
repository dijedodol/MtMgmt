/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.anonymous;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import org.ithb.si.made.mtmgmt.core.persistence.dao.MachineModelDao;
import org.ithb.si.made.mtmgmt.core.persistence.dao.MachineModelTotalizerDao;
import org.ithb.si.made.mtmgmt.core.persistence.dao.MachineTotalizerDao;
import org.ithb.si.made.mtmgmt.core.persistence.dao.SpbuDao;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author gde
 */
@Controller("anonymousInitializeDataController")
@RequestMapping("/anonymous/initialize_data")
public class InitializeDataController {
	
	private static final Logger LOG = LoggerFactory.getLogger(InitializeDataController.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private SpbuDao spbuDao;
	@Autowired
	private MachineModelDao machineModelDao;
	@Autowired
	private MachineTotalizerDao machineTotalizerDao;
	@Autowired
	private MachineModelTotalizerDao machineModelTotalizerDao;

	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public String showResult(Model model) throws SQLException {
		final UserEntity dbUserEntityAdm = createUserIfNotExist("adm", UserEntity.AccessRole.ADMIN);
		final UserEntity dbUserEntityTch = createUserIfNotExist("tch", UserEntity.AccessRole.TECHNICIAN);
		final UserEntity dbUserEntitySpv = createUserIfNotExist("spv", UserEntity.AccessRole.SUPERVISOR);
		model.addAttribute("dbUserEntityAdm", dbUserEntityAdm);
		model.addAttribute("dbUserEntityTch", dbUserEntityTch);
		model.addAttribute("dbUserEntitySpv", dbUserEntitySpv);

		try {
			final SpbuEntity dbSpbuEntity = createSpbuIfNotExist(dbUserEntitySpv, "54-801.21");
			model.addAttribute("spbuEntity", dbSpbuEntity);
			
			final MachineModelEntity dbMachineModel = createMachineIfNotExist("Encore S-300", 6);
			model.addAttribute("dbMachineModel", dbMachineModel);
		} catch (javax.validation.ConstraintViolationException ex) {
			LOG.error("Exception:[{}], violations:[{}]", ex.getMessage(), ex.getConstraintViolations(), ex);
		}
		return "anonymous/initialize_data";
	}
	
	private UserEntity createUserIfNotExist(String loginId, UserEntity.AccessRole accessRole) {
		UserEntity ret = userDao.findByLoginId(loginId);
		if (ret == null) {
			ret = new UserEntity();
			ret.setLoginId(loginId);
			ret.setFullName(loginId);
			ret.setAccessRole(accessRole);
			ret.setPasswordHash(loginId);
			ret = userDao.save(ret);
			userDao.flush();
		}
		return ret;
	}

	private SpbuEntity createSpbuIfNotExist(UserEntity supervisor, String code) {
		LOG.debug("createSpbuIfNotExist supervisor:[{}]", supervisor);
		SpbuEntity ret = spbuDao.findByCode(code);
		if (ret == null) {
			ret = new SpbuEntity();
			ret.setCode(code);
			ret.setAddress("Address_" + code);
			ret.setPhone("Phone_" + code);
			ret.setSupervisor(supervisor);
			ret = spbuDao.save(ret);
			spbuDao.flush();
		}
		return ret;
	}

	private MachineModelEntity createMachineIfNotExist(String code, int totalizerCount) {
		MachineModelEntity ret = machineModelDao.findByCode(code);
		if (ret == null) {
			ret = new MachineModelEntity();
			ret.setCode(code);
			ret.setName("Machine_" + code);
			ret.setMachineModelTotalizerEntityList(new ArrayList<MachineModelTotalizerEntity>(totalizerCount));
			ret = machineModelDao.save(ret);
			machineModelDao.flush();

			for (int i = 0; i < totalizerCount; i++) {
				MachineTotalizerEntity machineTotalizer = new MachineTotalizerEntity();
				machineTotalizer.setName("Totalizer_" + (i + 1));
				machineTotalizer = machineTotalizerDao.save(machineTotalizer);
				machineTotalizerDao.flush();

				final MachineModelTotalizerEntity machineModelTotalizer = new MachineModelTotalizerEntity(
						new MachineModelTotalizerEntityPK(ret.getId(), machineTotalizer.getId()));
				machineModelTotalizerDao.save(machineModelTotalizer);
			}
			machineModelTotalizerDao.flush();
		}
		return ret;
	}
}

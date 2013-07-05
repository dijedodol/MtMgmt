/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.common;

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
@Controller("commonInitializeSampleDataController")
@RequestMapping("/common/initialize_sample_data")
public class InitializeSampleDataController {

	private static final Logger LOG = LoggerFactory.getLogger(InitializeSampleDataController.class);
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

	@RequestMapping(method = RequestMethod.GET)
	public String showResult(Principal principal, Model model) throws SQLException {
		LOG.debug("showResult principal:[{}]", principal);
		final UserEntity dbUserEntity = userDao.findByLoginId(principal.getName());
		model.addAttribute("userEntity", dbUserEntity);

		try {
			final SpbuEntity dbSpbuEntity = createSpbuIfNotExist(dbUserEntity, "54-801.21");
			final MachineModelEntity dbMachineModel = createMachineIfNotExist("Encore S-300", 6);
			LOG.debug("showResult dbMachineModel:[{}], totalizers:[{}]", dbMachineModel, dbMachineModel.getMachineModelTotalizerEntityList());

			model.addAttribute("spbuEntity", dbSpbuEntity);
			model.addAttribute("dbMachineModel", dbMachineModel);
		} catch (javax.validation.ConstraintViolationException ex) {
			LOG.error("Exception:[{}], violations:[{}]", ex.getMessage(), ex.getConstraintViolations(), ex);
		}
		return "common/initialize_sample_data";
	}

	private SpbuEntity createSpbuIfNotExist(UserEntity supervisor, String code) {
		LOG.debug("createSpbuIfNotExist supervisor:[{}]", supervisor);
		final SpbuEntity ret;
		final SpbuEntity tmpDbSpbuEntity = spbuDao.findByCode(code);
		if (tmpDbSpbuEntity != null) {
			ret = tmpDbSpbuEntity;
		} else {
			final SpbuEntity tmpNewDbSpbuEntity = new SpbuEntity();
			tmpNewDbSpbuEntity.setCode(code);
			tmpNewDbSpbuEntity.setAddress("Address_" + code);
			tmpNewDbSpbuEntity.setPhone("Phone_" + code);
			tmpNewDbSpbuEntity.setSupervisor(supervisor);
			ret = spbuDao.save(tmpNewDbSpbuEntity);
		}
		return ret;
	}

	@Transactional
	private MachineModelEntity createMachineIfNotExist(String code, int totalizerCount) {
		final MachineModelEntity ret;
		final MachineModelEntity tmpDbMachineModelEntity = machineModelDao.findByCode(code);
		if (tmpDbMachineModelEntity != null) {
			ret = tmpDbMachineModelEntity;
		} else {
			final MachineModelEntity tmpNewDbMachineModelEntity = new MachineModelEntity();
			tmpNewDbMachineModelEntity.setCode(code);
			tmpNewDbMachineModelEntity.setName("Machine_" + code);
			tmpNewDbMachineModelEntity.setMachineModelTotalizerEntityList(new ArrayList<MachineModelTotalizerEntity>(totalizerCount));
			ret = machineModelDao.save(tmpNewDbMachineModelEntity);

			for (int i = 0; i < totalizerCount; i++) {
				final MachineTotalizerEntity machineTotalizer = new MachineTotalizerEntity();
				machineTotalizer.setName("Totalizer_" + (i + 1));
				machineTotalizerDao.save(machineTotalizer);

				final MachineModelTotalizerEntity machineModelTotalizer = new MachineModelTotalizerEntity();
				machineModelTotalizer.setMachineModelEntity(ret);
				machineModelTotalizer.setMachineTotalizerEntity(machineTotalizer);
				machineModelTotalizerDao.save(machineModelTotalizer);
			}
		}
		return ret;
	}
}

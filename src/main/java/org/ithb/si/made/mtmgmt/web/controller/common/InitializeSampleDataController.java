/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.common;

import java.security.Principal;
import java.sql.SQLException;
import org.ithb.si.made.mtmgmt.core.persistence.dao.MachineModelDao;
import org.ithb.si.made.mtmgmt.core.persistence.dao.SpbuDao;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(method = RequestMethod.GET)
	public String showResult(Principal principal, Model model) throws SQLException {
		LOG.debug("showResult principal:[{}]", principal);
		final UserEntity dbUserEntity = userDao.findByLoginId(principal.getName());
		model.addAttribute("userEntity", dbUserEntity);

		final SpbuEntity dbSpbuEntity1 = createSpbuIfNotExist(dbUserEntity, "0000");
		final SpbuEntity dbSpbuEntity2 = createSpbuIfNotExist(dbUserEntity, "0001");
		final MachineModelEntity dbMachineModel1 = createMachineIfNotExist("MCH000");

		model.addAttribute("spbuEntity1", dbSpbuEntity1);
		model.addAttribute("spbuEntity2", dbSpbuEntity2);
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
			tmpNewDbSpbuEntity.setAddress("Address " + code);
			tmpNewDbSpbuEntity.setPhone("Phone " + code);
			tmpNewDbSpbuEntity.setSupervisor(supervisor);
			ret = spbuDao.save(tmpNewDbSpbuEntity);
		}
		return ret;
	}

	private MachineModelEntity createMachineIfNotExist(String code) {
		final MachineModelEntity ret;
		final MachineModelEntity tmpDbMachineModelEntity = machineModelDao.findByCode(code);
		if (tmpDbMachineModelEntity != null) {
			ret = tmpDbMachineModelEntity;
		} else {
			final MachineModelEntity tmpNewDbMachineModelEntity = new MachineModelEntity();
			tmpNewDbMachineModelEntity.setCode(code);
			tmpNewDbMachineModelEntity.setName("Machine " + code);
			ret = machineModelDao.save(tmpNewDbMachineModelEntity);
		}
		return ret;
	}
}

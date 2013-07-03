/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.common;

import java.security.Principal;
import org.ithb.si.made.mtmgmt.core.persistence.dao.SpbuDao;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
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

	@RequestMapping(method = RequestMethod.GET)
	public String showResult(Principal principal, Model model) {
		LOG.debug("showResult principal:[{}]", principal);

		final UserEntity dbUserEntity = userDao.findByLoginId(principal.getName());
		model.addAttribute("userEntity", dbUserEntity);

		final SpbuEntity dbSpbuEntity1 = createSpbuIfNotExist(dbUserEntity, "0000");
		final SpbuEntity dbSpbuEntity2 = createSpbuIfNotExist(dbUserEntity, "0001");
		
		model.addAttribute("spbuEntity1", dbSpbuEntity1);
		model.addAttribute("spbuEntity2", dbSpbuEntity2);
		
		
		
		return "supervisor/home";
	}

	private SpbuEntity createSpbuIfNotExist(UserEntity supervisor, String code) {
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
}

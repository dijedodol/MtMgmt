/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.common;

import java.security.Principal;
import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author gde
 */
@Controller("commonSupervisorAjaxDataController")
@RequestMapping("/common/ajax/supervisor")
public class SupervisorAjaxDataController {

	@Autowired
	private UserDao userDao;

	@Transactional
	@RequestMapping(value = "spbu", produces = "application/json", method = RequestMethod.POST)
	public List<SpbuEntity> supervisorListSpbu(Principal principal) {
		final UserEntity dbUserEntity = userDao.findByLoginId(principal.getName());
		return dbUserEntity.getSpbuList();
	}

	@Transactional
	@RequestMapping(value = "spbu/{spbuId}/machine", produces = "application/json", method = RequestMethod.POST)
	public List<SpbuEntity> supervisorListSpbuMachine(Principal principal, @PathVariable long spbuId) {
		final UserEntity dbUserEntity = userDao.findByLoginId(principal.getName());
		return dbUserEntity.getSpbuList();
	}

	@Transactional
	@RequestMapping(value = "spbu/{spbuId}/machine/{spbuMachineId}/totalizer", produces = "application/json", method = RequestMethod.POST)
	public List<SpbuEntity> supervisorListSpbuMachineTotalizer(Principal principal, @PathVariable long spbuId) {
		final UserEntity dbUserEntity = userDao.findByLoginId(principal.getName());
		return dbUserEntity.getSpbuList();
	}
}

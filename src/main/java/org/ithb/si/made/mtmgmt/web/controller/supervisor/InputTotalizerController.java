/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.supervisor;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.persistence.dao.SpbuDao;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Uyeee
 */
@Controller("supervisorInputTotalizerController")
@RequestMapping("/supervisor/input_totalizer")
public class InputTotalizerController {

	private static final Logger LOG = LoggerFactory.getLogger(InputTotalizerController.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private SpbuDao spbuDao;

	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public String showInputTotalizer(Principal principal, Model model) {
		LOG.debug("showInputTotalizer principal:[{}]", principal);

		final UserEntity dbUserEntity = userDao.findByLoginId(principal.getName());
		for (final SpbuEntity spbuEntity : dbUserEntity.getSpbuList()) {
			LOG.debug("showInputTotalizer spbuEntity:[{}]", spbuEntity);
		}
		model.addAttribute("userEntity", dbUserEntity);
		return "supervisor/input_totalizer";
	}

	public String doInputTotalizer(Principal principal, @Valid TotalizerFormData formData, BindingResult bindingResult) {
		final String username = principal.getName();
		return "supervisor/input_totalizer";
	}

	@RequestMapping("{spbu_id}/machines")
	public List<SpbuMachineEntity> spbuList(Principal principal, long spbuId) {
		final List<SpbuMachineEntity> ret = new LinkedList<>();
		final UserEntity dbUserEntity = userDao.findByLoginId(principal.getName());
		final SpbuEntity dbSpbuEntity = spbuDao.findById(spbuId);

		if (dbUserEntity != null && dbSpbuEntity != null) {
			if (dbUserEntity.equals(dbSpbuEntity.getSupervisor())) {
				ret.addAll(dbSpbuEntity.getSpbuMachineEntityList());
			}
		}
		return ret;
	}

	public static class TotalizerFormData {

		private SpbuEntity[] spbuList;
		private long machineId;
		private long addition;

		public TotalizerFormData() {
		}

		public long getMachineId() {
			return machineId;
		}

		public void setMachineId(long machineId) {
			this.machineId = machineId;
		}

		public long getAddition() {
			return addition;
		}

		public void setAddition(long addition) {
			this.addition = addition;
		}
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.supervisor;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.util.MapBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gde
 */
@Controller("supervisorAjaxDataController")
@RequestMapping("/supervisor/ajax")
public class AjaxDataController {

	private static final Logger LOG = LoggerFactory.getLogger(AjaxDataController.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private SpbuMachineRepository spbuMachineRepository;
	@Autowired
	private SpbuMachineTotalizerRepository spbuMachineTotalizerRepository;

	@ResponseBody
	@RequestMapping(value = "spbu", produces = "application/json", method = RequestMethod.POST)
	public List<Map> supervisorListSpbu(Principal principal) {
		final UserEntity dbUserEntity = userRepository.findByLoginId(principal.getName());
		LOG.debug("supervisorListSpbu dbUserEntity:[{}]", dbUserEntity);
		LOG.debug("supervisorListSpbu dbUserEntity.getSpbuList:[{}]", dbUserEntity.getSpbuList());
		final List<Map> ret = new ArrayList<>(dbUserEntity.getSpbuList().size());
		for (final SpbuEntity spbuEntity : dbUserEntity.getSpbuList()) {
			final Map<String, Object> tmp = new HashMap<>();
			tmp.put("id", spbuEntity.getId());
			tmp.put("code", spbuEntity.getCode());
			tmp.put("address", spbuEntity.getAddress());
			tmp.put("phone", spbuEntity.getPhone());
			ret.add(tmp);
		}
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/machine", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<List<Map>> supervisorListSpbuMachine(Principal principal, @PathVariable long spbuId) {
		final UserEntity dbUserEntity = userRepository.findByLoginId(principal.getName());
		final SpbuEntity dbSpbuEntity = spbuRepository.findOne(spbuId);
		final ResponseEntity<List<Map>> ret;

		if (dbUserEntity != null && dbSpbuEntity != null && dbUserEntity.getId() == dbSpbuEntity.getSupervisor().getId()) {
			final List<Map> respEntity = new ArrayList<>(dbSpbuEntity.getSpbuMachineEntityList().size());
			for (final SpbuMachineEntity spbuMachineEntity : dbSpbuEntity.getSpbuMachineEntityList()) {
				final Map<String, Object> tmp = new HashMap<>();
				tmp.put("identifier", spbuMachineEntity.getSpbuMachineEntityPK().getIdentifier());
				tmp.put("machine_model_id", spbuMachineEntity.getMachineModelEntity().getId());
				tmp.put("machine_entity", new MapBuilder<>(new HashMap<String, Object>())
								.put("id", spbuMachineEntity.getMachineModelEntity().getId())
								.put("code", spbuMachineEntity.getMachineModelEntity().getCode())
								.put("name", spbuMachineEntity.getMachineModelEntity().getName())
								.getMap());
				respEntity.add(tmp);
			}
			ret = new ResponseEntity(respEntity, HttpStatus.OK);
		} else {
			ret = new ResponseEntity(HttpStatus.FORBIDDEN);
		}

		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/machine/{identifier}/totalizer", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<List<Map>> supervisorListSpbuMachineTotalizer(Principal principal, @PathVariable("spbuId") long spbuId, @PathVariable("identifier") String spbuMachineIdentifier) {
		final UserEntity dbUserEntity = userRepository.findByLoginId(principal.getName());
		final SpbuMachineEntity dbSpbuMachineEntity = spbuMachineRepository.findOne(new SpbuMachineEntityPK(spbuId, spbuMachineIdentifier));
		final ResponseEntity<List<Map>> ret;

		LOG.debug("supervisorListSpbuMachineTotalizer dbUserEntity:[{}], spbuId:[{}], spbuMachineIdentifier:[{}], dbSpbuMachineEntity:[{}]",
						dbUserEntity,
						spbuId,
						spbuMachineIdentifier,
						dbSpbuMachineEntity);

		if (dbUserEntity != null && dbSpbuMachineEntity != null && dbUserEntity.getId() == dbSpbuMachineEntity.getSpbuEntity().getSupervisor().getId()) {
			final List<Map> respEntity = new ArrayList<>(dbSpbuMachineEntity.getSpbuMachineTotalizerEntityList().size());
			LOG.debug("supervisorListSpbuMachineTotalizer dbSpbuMachineEntity.getSpbuMachineTotalizerEntityList:[{}]", dbSpbuMachineEntity.getSpbuMachineTotalizerEntityList());

			dbSpbuMachineEntity.getMachineModelEntity().getMachineModelTotalizerEntityList().size();
			LOG.debug("supervisorListSpbuMachineTotalizer dbSpbuMachineEntity.getMachineModelEntity().getMachineModelTotalizerEntityList:[{}]", dbSpbuMachineEntity.getMachineModelEntity().getMachineModelTotalizerEntityList());
			for (MachineModelTotalizerEntity machineModelTotalizerEntity : dbSpbuMachineEntity.getMachineModelEntity().getMachineModelTotalizerEntityList()) {
				final SpbuMachineTotalizerEntity spbuMachineTotalizerEntity = spbuMachineTotalizerRepository.findOne(new SpbuMachineTotalizerEntityPK(spbuId, spbuMachineIdentifier, machineModelTotalizerEntity.getMachineTotalizerEntity().getId()));
				final MapBuilder<String, Object> mapBuilder = new MapBuilder(new HashMap<>());

				respEntity.add(mapBuilder.put("machine_totalizer_id", machineModelTotalizerEntity.getMachineTotalizerEntity().getId())
								.put("counter", spbuMachineTotalizerEntity == null ? 0 : spbuMachineTotalizerEntity.getCounter())
								.getMap());
			}
			ret = new ResponseEntity(respEntity, HttpStatus.OK);
		} else {
			ret = new ResponseEntity(HttpStatus.FORBIDDEN);
		}

		return ret;
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.technician;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelPartRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.PartFailureModeRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Uyeee
 */
@Controller("technicianAjaxDataController")
@RequestMapping("/technician/ajax")
public class AjaxDataController {

	private static final Logger LOG = LoggerFactory.getLogger(AjaxDataController.class);
	@Autowired
	private ServiceReportRepository serviceReportRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private SpbuMachineRepository spbuMachineRepository;
	@Autowired
	private SpbuMachineTotalizerRepository spbuMachineTotalizerRepository;
	@Autowired
	private MachineModelPartRepository machineModelPartRepository;
	@Autowired
	private PartFailureModeRepository partFailureModeRepository;

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu", produces = "application/json", method = RequestMethod.GET)
	public List<Map> technicianListSpbu(Principal principal) {
		final List<Map> ret = new LinkedList<>();
		for (final SpbuEntity spbuEntity : spbuRepository.findAll()) {
			final Map<String, Object> tmp = new HashMap<>();
			tmp.put("id", spbuEntity.getId());
			tmp.put("code", spbuEntity.getCode());
			tmp.put("address", spbuEntity.getAddress());
			tmp.put("phone", spbuEntity.getPhone());
			ret.add(tmp);
		}
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/machine", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> technicianListSpbuMachine(Principal principal, @PathVariable long spbuId) {
		final SpbuEntity dbSpbuEntity = spbuRepository.findOne(spbuId);
		final ResponseEntity<List<Map>> ret;
		final List<Map> respEntity = new LinkedList<>();

		if (dbSpbuEntity != null) {
			for (final SpbuMachineEntity spbuMachineEntity : dbSpbuEntity.getSpbuMachineEntityList()) {
				final Map<String, Object> tmp = new HashMap<>();
				tmp.put("machineIdentifier", spbuMachineEntity.getSpbuMachineEntityPK().getMachineIdentifier());
				tmp.put("machineModelId", spbuMachineEntity.getMachineModelEntity().getId());
				tmp.put("machineEntity", new MapBuilder<>(new HashMap<String, Object>())
								.put("id", spbuMachineEntity.getMachineModelEntity().getId())
								.put("code", spbuMachineEntity.getMachineModelEntity().getCode())
								.put("name", spbuMachineEntity.getMachineModelEntity().getName())
								.getMap());
				respEntity.add(tmp);
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/machine/{machineIdentifier}/part", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> technicianListSpbuMachinePart(Principal principal, @PathVariable("spbuId") long spbuId, @PathVariable("machineIdentifier") String machineIdentifier) {
		final SpbuMachineEntityPK dbSpbuMachineEntityPK = new SpbuMachineEntityPK();
		dbSpbuMachineEntityPK.setSpbuId(spbuId);
		dbSpbuMachineEntityPK.setMachineIdentifier(machineIdentifier);

		final SpbuMachineEntity dbSpbuMachineEntity = spbuMachineRepository.findOne(dbSpbuMachineEntityPK);
		final ResponseEntity<List<Map>> ret;
		final List<Map> respEntity = new ArrayList<>(dbSpbuMachineEntity.getMachineModelEntity().getMachineModelPartEntityList().size());

		if (dbSpbuMachineEntity != null) {
			for (final MachineModelPartEntity dbMachineModelPartEntity : dbSpbuMachineEntity.getMachineModelEntity().getMachineModelPartEntityList()) {
				final Map<String, Object> tmp = new MapBuilder<>(new HashMap<String, Object>())
								.put("id", dbMachineModelPartEntity.getMachinePartEntity().getId())
								.put("name", dbMachineModelPartEntity.getMachinePartEntity().getName())
								.getMap();
				respEntity.add(tmp);
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/machine/{machineIdentifier}/part/{partId}/failure_mode", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> technicianListSpbuMachinePartFailureMode(Principal principal, @PathVariable("spbuId") long spbuId, @PathVariable("machineIdentifier") String machineIdentifier, @PathVariable("partId") long partId) {
		final SpbuMachineEntityPK dbSpbuMachineEntityPK = new SpbuMachineEntityPK();
		dbSpbuMachineEntityPK.setSpbuId(spbuId);
		dbSpbuMachineEntityPK.setMachineIdentifier(machineIdentifier);

		final SpbuMachineEntity dbSpbuMachineEntity = spbuMachineRepository.findOne(dbSpbuMachineEntityPK);
		final ResponseEntity<List<Map>> ret;
		final List<Map> respEntity = new LinkedList<>();

		if (dbSpbuMachineEntity != null) {
			final MachineModelPartEntityPK dbMachineModelPartEntityPK = new MachineModelPartEntityPK();
			dbMachineModelPartEntityPK.setMachineModelId(dbSpbuMachineEntity.getMachineModelEntity().getId());
			dbMachineModelPartEntityPK.setMachinePartId(partId);;

			final MachineModelPartEntity dbMachineModelPartEntity = machineModelPartRepository.findOne(dbMachineModelPartEntityPK);
			if (dbMachineModelPartEntity != null) {
				for (final PartFailureModeEntity dbPartFailureModeEntity : dbMachineModelPartEntity.getMachinePartEntity().getPartFailureModeEntityList()) {
					final Map<String, Object> tmp = new MapBuilder<>(new HashMap<String, Object>())
									.put("id", dbPartFailureModeEntity.getId())
									.put("name", dbPartFailureModeEntity.getName())
									.getMap();
					respEntity.add(tmp);
				}
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/machine/{machineIdentifier}/part/{partId}/failure_mode/{failureModeId}/handling", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> technicianListSpbuMachinePartFailureModeHandling(Principal principal, @PathVariable("spbuId") long spbuId, @PathVariable("machineIdentifier") String machineIdentifier, @PathVariable("partId") long partId, @PathVariable("failureModeId") long failureModeId) {
		final SpbuMachineEntityPK dbSpbuMachineEntityPK = new SpbuMachineEntityPK();
		dbSpbuMachineEntityPK.setSpbuId(spbuId);
		dbSpbuMachineEntityPK.setMachineIdentifier(machineIdentifier);

		final SpbuMachineEntity dbSpbuMachineEntity = spbuMachineRepository.findOne(dbSpbuMachineEntityPK);
		final ResponseEntity<List<Map>> ret;
		final List<Map> respEntity = new LinkedList<>();

		if (dbSpbuMachineEntity != null) {
			final MachineModelPartEntityPK dbMachineModelPartEntityPK = new MachineModelPartEntityPK();
			dbMachineModelPartEntityPK.setMachineModelId(dbSpbuMachineEntity.getMachineModelEntity().getId());
			dbMachineModelPartEntityPK.setMachinePartId(partId);;

			final MachineModelPartEntity dbMachineModelPartEntity = machineModelPartRepository.findOne(dbMachineModelPartEntityPK);
			if (dbMachineModelPartEntity != null) {
				final PartFailureModeEntity dbPartFailureModeEntity = partFailureModeRepository.findOne(failureModeId);
				if (dbPartFailureModeEntity != null) {
					for (final FailureModeHandlingEntity dbFailureModeHandlingEntity : dbPartFailureModeEntity.getFailureModeHandlingEntityList()) {
						final Map<String, Object> tmp = new MapBuilder<>(new HashMap<String, Object>())
										.put("id", dbFailureModeHandlingEntity.getId())
										.put("name", dbFailureModeHandlingEntity.getName())
										.getMap();
						respEntity.add(tmp);
					}
				}
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.supervisor;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.util.DateUtil;
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
 * @author gde
 */
@Controller("supervisorAjaxDataController")
@RequestMapping("/supervisor/ajax")
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

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu", produces = "application/json", method = RequestMethod.GET)
	public List<Map> supervisorListSpbu(Principal principal) {
		final UserEntity dbUserEntity = userRepository.findByLoginId(principal.getName());
		LOG.debug("supervisorListSpbu dbUserEntity:[{}]", dbUserEntity);
		LOG.debug("supervisorListSpbu dbUserEntity.getSpbuList:[{}]", dbUserEntity.getSpbuEntityList());
		final List<Map> ret = new ArrayList<>(dbUserEntity.getSpbuEntityList().size());
		for (final SpbuEntity spbuEntity : dbUserEntity.getSpbuEntityList()) {
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
	public ResponseEntity<List<Map>> supervisorListSpbuMachine(Principal principal, @PathVariable("spbuId") long spbuId) {
		final UserEntity dbUserEntity = userRepository.findByLoginId(principal.getName());
		final SpbuEntity dbSpbuEntity = spbuRepository.findOne(spbuId);
		final ResponseEntity<List<Map>> ret;

		final List<Map> respEntity = new LinkedList<>();
		if (dbUserEntity != null && dbSpbuEntity != null && dbUserEntity.getId() == dbSpbuEntity.getSupervisorEntity().getId()) {
			for (final SpbuMachineEntity spbuMachineEntity : dbSpbuEntity.getSpbuMachineEntityList()) {
				final Map<String, Object> tmp = new HashMap<>();
				tmp.put("machineSerial", spbuMachineEntity.getMachineSerial());
				tmp.put("modelId", spbuMachineEntity.getMachineModelEntity().getModelId());
				tmp.put("machineIdentifier", spbuMachineEntity.getMachineIdentifier());
				respEntity.add(tmp);
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/machine/{machineSerial}/totalizer", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> supervisorListSpbuMachineTotalizer(Principal principal, @PathVariable("spbuId") long spbuId, @PathVariable("machineSerial") String machineSerial) {
		final UserEntity userEntity = userRepository.findByLoginId(principal.getName());
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(machineSerial);
		final ResponseEntity<List<Map>> ret;

		LOG.debug("supervisorListSpbuMachineTotalizer dbUserEntity:[{}], spbuId:[{}], machineSerial:[{}], dbSpbuMachineEntity:[{}]",
						userEntity,
						spbuId,
						machineSerial,
						spbuMachineEntity);

		final List<Map> respEntity = new LinkedList<>();
		if (userEntity != null && spbuMachineEntity != null && userEntity.getId() == spbuMachineEntity.getSpbuEntity().getSupervisorEntity().getId()) {
			LOG.debug("supervisorListSpbuMachineTotalizer dbSpbuMachineEntity.getSpbuMachineTotalizerEntityList:[{}]", spbuMachineEntity.getSpbuMachineTotalizerEntityList());

			for (MachineModelTotalizerEntity machineModelTotalizerEntity : spbuMachineEntity.getMachineModelEntity().getMachineModelTotalizerEntityList()) {
				final SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK = new SpbuMachineTotalizerEntityPK();
				spbuMachineTotalizerEntityPK.setMachineSerial(machineSerial);
				spbuMachineTotalizerEntityPK.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
				spbuMachineTotalizerEntityPK.setTotalizerId(machineModelTotalizerEntity.getMachineModelTotalizerEntityPK().getTotalizerId());

				SpbuMachineTotalizerEntity spbuMachineTotalizerEntity = spbuMachineTotalizerRepository.findOne(spbuMachineTotalizerEntityPK);
				if (spbuMachineTotalizerEntity == null) {
					spbuMachineTotalizerEntity = new SpbuMachineTotalizerEntity(spbuMachineTotalizerEntityPK);
					spbuMachineTotalizerEntity.setAlias(machineModelTotalizerEntity.getMachineModelTotalizerEntityPK().getTotalizerId());
					spbuMachineTotalizerEntity.setCounter(0);
					spbuMachineTotalizerEntity.setSpbuMachineEntity(spbuMachineEntity);
					spbuMachineTotalizerEntity.setMachineModelTotalizerEntity(machineModelTotalizerEntity);
					spbuMachineTotalizerEntity = spbuMachineTotalizerRepository.saveAndFlush(spbuMachineTotalizerEntity);
				}

				final MapBuilder<String, Object> mapBuilder = new MapBuilder(new HashMap<>());
				respEntity.add(mapBuilder
								.put("totalizerId", machineModelTotalizerEntity.getMachineModelTotalizerEntityPK().getTotalizerId())
								.put("alias", spbuMachineTotalizerEntity.getAlias())
								.put("counter", spbuMachineTotalizerEntity.getCounter())
								.getMap());
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/service_report", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> supervisorListServiceReport(Principal principal, @PathVariable long spbuId) {
		final UserEntity userEntity = userRepository.findByLoginId(principal.getName());
		final SpbuEntity spbuEntity = spbuRepository.findOne(spbuId);
		final ResponseEntity<List<Map>> ret;

		final List<Map> respEntity = new LinkedList<>();
		if (userEntity != null && spbuEntity != null && userEntity.getId() == spbuEntity.getSupervisorEntity().getId()) {
			final List<ServiceReportEntity> serviceReportEntities = serviceReportRepository.findBySpbuMachineEntity_SpbuEntityOrderByDateDesc(spbuEntity);
			for (final ServiceReportEntity serviceReportEntity : serviceReportEntities) {
				final Map<String, Object> tmp = new MapBuilder<>(new HashMap<String, Object>())
								.put("date", DateUtil.format(serviceReportEntity.getDate()))
								.put("machineSerial", serviceReportEntity.getSpbuMachineEntity().getMachineSerial())
								.put("machineIdentifier", serviceReportEntity.getSpbuMachineEntity().getMachineIdentifier())
								.put("modelId", serviceReportEntity.getMachineModelPartEntity().getMachineModelPartEntityPK().getModelId())
								.put("partId", serviceReportEntity.getMachineModelPartEntity().getMachineModelPartEntityPK().getPartId())
								.put("failureModeCode", serviceReportEntity.getFailureModeHandlingEntity().getFailureModeHandlingEntityPK().getFailureModeCode())
								.put("failureModeHandlingCode", serviceReportEntity.getFailureModeHandlingEntity().getFailureModeHandlingEntityPK().getFailureModeHandlingCode())
								.put("technicianId", serviceReportEntity.getTechnicianEntity().getId())
								.put("technicianName", serviceReportEntity.getTechnicianEntity().getFullName())
								.getMap();
				respEntity.add(tmp);
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}
}

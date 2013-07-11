/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.technician;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelPartRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.PartFailureModeRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.util.DateUtil;
import org.ithb.si.made.mtmgmt.core.util.MaintenancePredictor;
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
	private MaintenancePredictor maintenancePredictor;
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
	@RequestMapping(value = "spbu/{spbuId}/machine/{machineSerial}/part", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> technicianListSpbuMachineModelPart(Principal principal, @PathVariable("spbuId") long spbuId, @PathVariable("machineSerial") String machineSerial) {
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(machineSerial);
		final ResponseEntity<List<Map>> ret;
		final List<Map> respEntity = new LinkedList<>();

		if (spbuMachineEntity != null) {
			for (final MachineModelPartEntity machineModelPartEntity : spbuMachineEntity.getMachineModelEntity().getMachineModelPartEntityList()) {
				final Map<String, Object> tmp = new MapBuilder<>(new HashMap<String, Object>())
								.put("partId", machineModelPartEntity.getMachineModelPartEntityPK().getPartId())
								.put("machineModelPartIdentifier", machineModelPartEntity.getMachineModelPartEntityPK().getMachineModelPartIdentifier())
								.put("name", machineModelPartEntity.getMachinePartTypeEntity().getName())
								.getMap();
				respEntity.add(tmp);
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/machine/{machineSerial}/part/{partId}/{machineModelPartIdentifier}/failure_mode", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> technicianListSpbuMachinePartFailureMode(Principal principal, @PathVariable("spbuId") long spbuId, @PathVariable("machineSerial") String machineSerial, @PathVariable("partId") String partId, @PathVariable("machineModelPartIdentifier") String machineModelPartIdentifier) {
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(machineSerial);
		final ResponseEntity<List<Map>> ret;
		final List<Map> respEntity = new LinkedList<>();

		if (spbuMachineEntity != null) {
			final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
			machineModelPartEntityPK.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
			machineModelPartEntityPK.setPartId(partId);
			machineModelPartEntityPK.setMachineModelPartIdentifier(machineModelPartIdentifier);

			final MachineModelPartEntity machineModelPartEntity = machineModelPartRepository.findOne(machineModelPartEntityPK);
			if (machineModelPartEntity != null) {
				for (final PartFailureModeEntity partFailureModeEntity : machineModelPartEntity.getMachinePartTypeEntity().getPartFailureModeEntityList()) {
					final Map<String, Object> tmp = new MapBuilder<>(new HashMap<String, Object>())
									.put("failureModeCode", partFailureModeEntity.getPartFailureModeEntityPK().getFailureModeCode())
									.put("name", partFailureModeEntity.getName())
									.put("description", partFailureModeEntity.getDescription())
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
	@RequestMapping(value = "spbu/{spbuId}/machine/{machineSerial}/part/{partId}/{machineModelPartIdentifier}/failure_mode/{failureModeCode}/handling", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> technicianListSpbuMachinePartFailureModeHandling(Principal principal, @PathVariable("spbuId") long spbuId, @PathVariable("machineSerial") String machineSerial, @PathVariable("partId") String partId, @PathVariable("machineModelPartIdentifier") String machineModelPartIdentifier, @PathVariable("failureModeCode") String failureModeCode) {
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(machineSerial);
		final ResponseEntity<List<Map>> ret;
		final List<Map> respEntity = new LinkedList<>();

		if (spbuMachineEntity != null) {
			final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
			machineModelPartEntityPK.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
			machineModelPartEntityPK.setPartId(partId);
			machineModelPartEntityPK.setMachineModelPartIdentifier(machineModelPartIdentifier);

			final MachineModelPartEntity machineModelPartEntity = machineModelPartRepository.findOne(machineModelPartEntityPK);
			if (machineModelPartEntity != null) {
				final PartFailureModeEntityPK partFailureModeEntityPK = new PartFailureModeEntityPK();
				partFailureModeEntityPK.setPartId(partId);
				partFailureModeEntityPK.setFailureModeCode(failureModeCode);
				final PartFailureModeEntity partFailureModeEntity = partFailureModeRepository.findOne(partFailureModeEntityPK);
				if (partFailureModeEntity != null) {
					for (final FailureModeHandlingEntity failureModeHandlingEntity : partFailureModeEntity.getFailureModeHandlingEntityList()) {
						final Map<String, Object> tmp = new MapBuilder<>(new HashMap<String, Object>())
										.put("failureModeHandlingCode", failureModeHandlingEntity.getFailureModeHandlingEntityPK().getFailureModeHandlingCode())
										.put("name", failureModeHandlingEntity.getName())
										.getMap();
						respEntity.add(tmp);
					}
				}
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbu/{spbuId}/service_report", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> technicianListServiceReport(Principal principal, @PathVariable long spbuId) {
		final SpbuEntity spbuEntity = spbuRepository.findOne(spbuId);
		final ResponseEntity<List<Map>> ret;

		final List<Map> respEntity = new LinkedList<>();
		if (spbuEntity != null) {
			final List<ServiceReportEntity> serviceReports = serviceReportRepository.findBySpbuMachineEntity_SpbuEntityOrderByDateDesc(spbuEntity);
			for (final ServiceReportEntity serviceReportEntity : serviceReports) {
				final FailureModeHandlingEntity failureModeHandlingEntity = serviceReportEntity.getFailureModeHandlingEntity();
				final PartFailureModeEntity partFailureModeEntity = failureModeHandlingEntity.getPartFailureModeEntity();

				final Map<String, Object> tmp = new MapBuilder<>(new HashMap<String, Object>())
								.put("date", DateUtil.format(serviceReportEntity.getDate()))
								.put("machineSerial", serviceReportEntity.getSpbuMachineEntity().getMachineSerial())
								.put("machineIdentifier", serviceReportEntity.getSpbuMachineEntity().getMachineIdentifier())
								.put("modelId", serviceReportEntity.getMachineModelPartEntity().getMachineModelEntity().getModelId())
								.put("partId", serviceReportEntity.getFailureModeHandlingEntity().getFailureModeHandlingEntityPK().getPartId())
								.put("machineModelPartIdentifier", serviceReportEntity.getMachineModelPartEntity().getMachineModelPartEntityPK().getMachineModelPartIdentifier())
								.put("failureModeCode", partFailureModeEntity.getPartFailureModeEntityPK().getFailureModeCode())
								.put("failureModeName", partFailureModeEntity.getName())
								.put("failureModeHandlingCode", failureModeHandlingEntity.getFailureModeHandlingEntityPK().getFailureModeHandlingCode())
								.put("failureModeHandlingName", failureModeHandlingEntity.getName())
								.put("technicianId", serviceReportEntity.getTechnicianEntity().getId())
								.put("technicianName", serviceReportEntity.getTechnicianEntity().getFullName())
								.getMap();
				respEntity.add(tmp);
			}
		}
		ret = new ResponseEntity(respEntity, HttpStatus.OK);
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "maintenance_predictions", produces = "application/json", method = RequestMethod.GET)
	public List<Map<String, Object>> getMaintenancePredictions(Principal principal) {
		final List<MaintenancePredictor.PredictionResult> predictionResults = maintenancePredictor.getPredictions();
		final List<Map<String, Object>> predictionResultsView = new LinkedList<>();

		for (final MaintenancePredictor.PredictionResult predictionResult : predictionResults) {
			final SpbuMachineEntity spbuMachineEntity = predictionResult.getSpbuMachineEntity();
			final MapBuilder<String, Object> mapBuilder = new MapBuilder(new HashMap<String, Object>());
			mapBuilder.put("spbuId", spbuMachineEntity.getSpbuEntity().getId());
			mapBuilder.put("spbuCode", spbuMachineEntity.getSpbuEntity().getCode());
			mapBuilder.put("machineSerial", spbuMachineEntity.getMachineSerial());
			mapBuilder.put("machineIdentifier", spbuMachineEntity.getMachineIdentifier());
			mapBuilder.put("machineModelId", spbuMachineEntity.getMachineModelEntity().getModelId());
			mapBuilder.put("partId", predictionResult.getMachineModelPartEntity().getMachineModelPartEntityPK().getPartId());
			mapBuilder.put("partName", predictionResult.getMachineModelPartEntity().getMachinePartTypeEntity().getName());
			mapBuilder.put("predictionType", predictionResult.getPredictionType());
			mapBuilder.put("mttf", predictionResult.getMachinePartMttf().getMttf());
			mapBuilder.put("mttfThreshold", predictionResult.getMachinePartMttf().getMttfThreshold());
			mapBuilder.put("ttf", predictionResult.getTtf());

			final Map<String, Object> predictionResultMap = mapBuilder.getMap();
//			final Map<String, Object> predictionResultMap = new MapBuilder<>(new HashMap<String, Object>())
//							.put("spbuId", spbuMachineEntity.getSpbuEntity().getId())
//							.put("spbuCode", spbuMachineEntity.getSpbuEntity().getCode())
//							.put("machineSerial", spbuMachineEntity.getMachineSerial())
//							.put("machineIdentifier", spbuMachineEntity.getMachineIdentifier())
//							.put("machineModelId", spbuMachineEntity.getMachineModelEntity().getModelId())
//							.put("partId", predictionResult.getMachineModelPartEntity().getMachineModelPartEntityPK().getPartId())
//							.put("partName", predictionResult.getMachineModelPartEntity().getMachinePartTypeEntity().getName())
//							.put("predictionType", predictionResult.getPredictionType())
//							.put("mttf", predictionResult.getMachinePartMttf().getMttf())
//							.put("mttfThreshold", predictionResult.getMachinePartMttf().getMttfThreshold())
//							.put("ttf", predictionResult.getTtf())
//							.getMap();
			predictionResultsView.add(predictionResultMap);
		}
		return predictionResultsView;
	}
}

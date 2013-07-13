/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.admin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachinePartTypeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelPartRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachinePartTypeRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.PartFailureModeRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.security.AccessRole;
import org.ithb.si.made.mtmgmt.core.util.MapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller("adminCrudAjaxDataController")
@RequestMapping("/admin/crud/ajax")
public class CrudAjaxDataController {

	@Autowired
	private MachinePartTypeRepository machinePartTypeRepository;
	@Autowired
	private MachineModelRepository machineModelRepository;
	@Autowired
	private MachineModelPartRepository machineModelPartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private PartFailureModeRepository partFailureModeRepository;

	@Transactional
	@ResponseBody
	@RequestMapping(value = "machine_parts", produces = "application/json", method = RequestMethod.GET)
	public List<Map> getAllMachineParts() {
		final List<Map> ret = new LinkedList<>();
		final List<MachinePartTypeEntity> machinePartTypeEntities = machinePartTypeRepository.findAll();
		for (final MachinePartTypeEntity machinePartTypeEntity : machinePartTypeEntities) {
			ret.add(new MapBuilder(new HashMap<String, Object>())
							.put("partId", machinePartTypeEntity.getPartId())
							.put("name", machinePartTypeEntity.getName())
							.getMap());
		}
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "machine_models", produces = "application/json", method = RequestMethod.GET)
	public List<Map> getAllMachineModels() {
		final List<Map> ret = new LinkedList<>();
		final List<MachineModelEntity> machineModelEntities = machineModelRepository.findAll();
		for (final MachineModelEntity machineModelEntity : machineModelEntities) {
			ret.add(new MapBuilder(new HashMap<String, Object>())
							.put("modelId", machineModelEntity.getModelId())
							.put("name", machineModelEntity.getName())
							.getMap());
		}
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "supervisors", produces = "application/json", method = RequestMethod.GET)
	public List<Map> getAllSupervisors() {
		final List<Map> ret = new LinkedList<>();
		final List<UserEntity> userEntities = userRepository.findByAccessRole(AccessRole.SUPERVISOR);
		for (final UserEntity userEntity : userEntities) {
			ret.add(new MapBuilder(new HashMap<String, Object>())
							.put("id", userEntity.getId())
							.put("fullName", userEntity.getFullName())
							.getMap());
		}
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "spbus", produces = "application/json", method = RequestMethod.GET)
	public List<Map> getAllSpbus() {
		final List<Map> ret = new LinkedList<>();
		final List<SpbuEntity> spbuEntities = spbuRepository.findAll();
		for (final SpbuEntity spbuEntity : spbuEntities) {
			ret.add(new MapBuilder(new HashMap<String, Object>())
							.put("id", spbuEntity.getId())
							.put("code", spbuEntity.getCode())
							.put("address", spbuEntity.getAddress())
							.put("phone", spbuEntity.getPhone())
							.getMap());
		}
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "machine_model/{modelId}/machine_model_parts", produces = "application/json", method = RequestMethod.GET)
	public List<Map> getMachineModelParts(@PathVariable("modelId") String modelId) {
		final List<Map> ret = new LinkedList<>();
		final MachineModelEntity machineModelEntity = machineModelRepository.findOne(modelId);
		if (machineModelEntity != null) {
			for (final MachineModelPartEntity machineModelPartEntity : machineModelEntity.getMachineModelPartEntityList()) {
				ret.add(new MapBuilder(new HashMap<String, Object>())
								.put("partId", machineModelPartEntity.getMachineModelPartEntityPK().getPartId())
								.put("machineModelPartIdentifier", machineModelPartEntity.getMachineModelPartEntityPK().getMachineModelPartIdentifier())
								.getMap());
			}
		}
		return ret;
	}

	@Transactional
	@ResponseBody
	@RequestMapping(value = "machine_model/{modelId}/machine_model_totalizers", produces = "application/json", method = RequestMethod.GET)
	public List<Map> getMachineModelTotalizers(@PathVariable("modelId") String modelId) {
		final List<Map> ret = new LinkedList<>();
		final MachineModelEntity machineModelEntity = machineModelRepository.findOne(modelId);
		if (machineModelEntity != null) {
			for (final MachineModelTotalizerEntity machineModelTotalizerEntity : machineModelEntity.getMachineModelTotalizerEntityList()) {
				ret.add(new MapBuilder(new HashMap<String, Object>())
								.put("totalizerId", machineModelTotalizerEntity.getMachineModelTotalizerEntityPK().getTotalizerId())
								.getMap());
			}
		}
		return ret;
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping(value = "machine_part/{partId}/failure_modes", produces = "application/json", method = RequestMethod.GET)
	public List<Map> getMachinePartFailureModes(@PathVariable("partId") String partId) {
		final List<Map> ret = new LinkedList<>();
		final MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(partId);
		if (machinePartTypeEntity != null) {
			for (final PartFailureModeEntity partFailureModeEntity : machinePartTypeEntity.getPartFailureModeEntityList()) {
				ret.add(new MapBuilder(new HashMap<String, Object>())
								.put("code", partFailureModeEntity.getPartFailureModeEntityPK().getFailureModeCode())
								.put("name", partFailureModeEntity.getName())
								.put("description", partFailureModeEntity.getDescription())
								.getMap());
			}
		}
		return ret;
	}
}

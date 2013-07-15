/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.supervisor;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.exception.InvalidDataException;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
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
	private UserRepository userRepository;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private SpbuMachineRepository spbuMachineRepository;
	@Autowired
	private SpbuMachineTotalizerRepository spbuMachineTotalizerRepository;
	@Autowired
	private MachineModelTotalizerRepository machineTotalizerRepository;

	public InputTotalizerController() {
		LOG.debug("InputTotalizerController <init>");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showInputTotalizer(Principal principal, Model model) {
		LOG.debug("showInputTotalizer principal:[{}]", principal);
		
		final UserEntity supervisor = userRepository.findByLoginId(principal.getName());
		final List<SpbuEntity> spbuEntities = supervisor.getSpbuEntityList();
		
		model.addAttribute("formData", new FormData());
		model.addAttribute("spbuOptions", getSpbuOptions(spbuEntities));
		model.addAttribute("machineSerialOptions", spbuEntities.isEmpty() ? new HashMap<>() : getMachineSerialOptions(spbuEntities.get(0)));
		return "supervisor/input_totalizer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doInputTotalizer(Principal principal, @Valid FormData formData, BindingResult bindingResult, Model model) {
		LOG.debug("doInputTotalizer formData:[{}]", formData);
		try {
			_doInputTotalizer(principal, formData, bindingResult);
			model.addAttribute("formData", formData);
			model.addAttribute("spbuOptions", getSpbuOptions(userRepository.findByLoginId(principal.getName())));
			model.addAttribute("machineSerialOptions", getMachineSerialOptions(spbuRepository.findOne(formData.getSpbuId())));
		} catch (InvalidDataException ex) {
			LOG.error("doInputTotalizer Exception:[{}]", ex.getMessage(), ex);
		}
		return "supervisor/input_totalizer";
	}

	@Transactional
	private void _doInputTotalizer(Principal principal, @Valid FormData formData, BindingResult bindingResult) {
		final UserEntity userEntity = userRepository.findByLoginId(principal.getName());
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(formData.getMachineSerial());
		if (userEntity != null && spbuMachineEntity != null && userEntity.getId() == spbuMachineEntity.getSpbuEntity().getSupervisorEntity().getId()) {
			for (int i = 0; i < formData.getTotalizerIds().size(); i++) {
				final String totalizerId = formData.getTotalizerIds().get(i);
				final double totalizerValue = formData.getTotalizerValues().get(i);

				final MachineModelTotalizerEntityPK machineModelTotalizerEntityPK = new MachineModelTotalizerEntityPK();
				machineModelTotalizerEntityPK.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
				machineModelTotalizerEntityPK.setTotalizerId(totalizerId);
				final MachineModelTotalizerEntity machineModelTotalizerEntity = machineTotalizerRepository.findOne(machineModelTotalizerEntityPK);
				if (machineModelTotalizerEntity == null) {
					bindingResult.reject("common.error.invalidData");
					throw new InvalidDataException("Invalid totalizer: " + totalizerId);
				}

				final SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK = new SpbuMachineTotalizerEntityPK();
				spbuMachineTotalizerEntityPK.setMachineSerial(formData.machineSerial);
				spbuMachineTotalizerEntityPK.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
				spbuMachineTotalizerEntityPK.setTotalizerId(totalizerId);
				SpbuMachineTotalizerEntity spbuMachineTotalizerEntity = spbuMachineTotalizerRepository.findOne(spbuMachineTotalizerEntityPK);
				if (spbuMachineTotalizerEntity == null) {
					spbuMachineTotalizerEntity = new SpbuMachineTotalizerEntity(spbuMachineTotalizerEntityPK);
					spbuMachineTotalizerEntity.setAlias(totalizerId);
					spbuMachineTotalizerEntity.setCounter(0);
					spbuMachineTotalizerEntity.setMachineModelTotalizerEntity(machineModelTotalizerEntity);
					spbuMachineTotalizerEntity.setSpbuMachineEntity(spbuMachineEntity);
				}

				if (totalizerValue < spbuMachineTotalizerEntity.getCounter()) {
					bindingResult.reject("common.error.invalidData");
					throw new InvalidDataException("Input totalizer less than current totalizer: " + totalizerValue + " < " + spbuMachineTotalizerEntity.getCounter());
				}

				spbuMachineTotalizerEntity.setCounter(totalizerValue);
				spbuMachineEntity.getSpbuMachineTotalizerEntityList().add(spbuMachineTotalizerEntity);
			}
			spbuMachineRepository.saveAndFlush(spbuMachineEntity);
		} else {
			bindingResult.reject("supervisor.error.spbuMismatch");
		}
	}

	private Map<Long, String> getSpbuOptions(UserEntity supervisorEntity) {
		return getSpbuOptions(supervisorEntity.getSpbuEntityList());
	}

	private Map<Long, String> getSpbuOptions(List<SpbuEntity> spbuEntities) {
		final Map<Long, String> ret = new LinkedHashMap<>();
		for (final SpbuEntity spbuEntity : spbuEntities) {
			ret.put(spbuEntity.getId(), spbuEntity.getCode());
		}
		return ret;
	}

	private Map<String, String> getMachineSerialOptions(SpbuEntity spbuEntity) {
		final Map<String, String> ret = new LinkedHashMap<>();
		final List<SpbuMachineEntity> spbuMachines = spbuEntity.getSpbuMachineEntityList();
		for (final SpbuMachineEntity spbuMachine : spbuMachines) {
			ret.put(spbuMachine.getMachineSerial(), spbuMachine.getMachineIdentifier());
		}
		return ret;
	}

	public static class FormData {

		private long spbuId;
		private String machineSerial;
		private List<String> totalizerIds;
		private List<Double> totalizerValues;

		public FormData() {
		}

		@Override
		public String toString() {
			return "FormData{" + "spbuId=" + spbuId + ", machineSerial=" + machineSerial + ", totalizerIds=" + totalizerIds + ", totalizerValues=" + totalizerValues + '}';
		}

		public long getSpbuId() {
			return spbuId;
		}

		public void setSpbuId(long spbuId) {
			this.spbuId = spbuId;
		}

		public String getMachineSerial() {
			return machineSerial;
		}

		public void setMachineSerial(String machineSerial) {
			this.machineSerial = machineSerial;
		}

		public List<String> getTotalizerIds() {
			return totalizerIds;
		}

		public void setTotalizerIds(List<String> totalizerIds) {
			this.totalizerIds = totalizerIds;
		}

		public List<Double> getTotalizerValues() {
			return totalizerValues;
		}

		public void setTotalizerValues(List<Double> totalizerValues) {
			this.totalizerValues = totalizerValues;
		}
	}
}

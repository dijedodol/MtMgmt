/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.technician;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.exception.InvalidDataException;
import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.FailureModeHandlingRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelPartRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.util.DateUtil;
import org.ithb.si.made.mtmgmt.web.controller.supervisor.InputTotalizerController;
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
@Controller("technicianInputServiceReportController")
@RequestMapping("/technician/input_service_report")
public class InputServiceReportController {

	private static final Logger LOG = LoggerFactory.getLogger(InputServiceReportController.class);
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
	@Autowired
	private FailureModeHandlingRepository failureModeHandlingRepository;
	@Autowired
	private ServiceReportRepository serviceReportRepository;
	@Autowired
	private MachineModelPartRepository machineModelPartRepository;

	public InputServiceReportController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showInputServiceReport(Principal principal, Model model) {
		LOG.debug("showInputTotalizer principal:[{}]", principal);
		final List<SpbuEntity> spbuEntities = spbuRepository.findAll();
		model.addAttribute("formData", new FormData());
		model.addAttribute("spbuOptions", getSpbuOptions(spbuEntities));
		model.addAttribute("machineSerialOptions", spbuEntities.isEmpty() ? new HashMap<>() : getMachineSerialOptions(spbuEntities.get(0)));
		return "technician/input_service_report";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doInputServiceReport(Principal principal, @Valid FormData formData, BindingResult bindingResult, Model model) {
		LOG.debug("doInputTotalizer formData:[{}]", formData);
		try {
			_doInputServiceReport(principal, formData, bindingResult, model);
			model.addAttribute("formData", formData);
			model.addAttribute("spbuOptions", getSpbuOptions(spbuRepository.findAll()));
			model.addAttribute("machineSerialOptions", getMachineSerialOptions(spbuRepository.findOne(formData.getSpbuId())));
		} catch (InvalidDataException ex) {
			LOG.error("doInputTotalizer Exception:[{}]", ex.getMessage(), ex);
		} catch (ConstraintViolationException ex) {
			LOG.error("doInputTotalizer ConstraintViolationException:[{}], violations:[{}]", ex.getMessage(), ex.getConstraintViolations(), ex);
		}
		model.addAttribute("formData", formData);
		return "technician/input_service_report";
	}

	@Transactional
	private void _doInputServiceReport(Principal principal, @Valid FormData formData, BindingResult bindingResult, Model model) {
		final UserEntity technicianEntity = userRepository.findByLoginId(principal.getName());
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(formData.getMachineSerial());
		if (spbuMachineEntity == null) {
			bindingResult.reject("common.error.invalidData");
			throw new InvalidDataException("SPBU machine not found: " + formData.getSpbuId());
		}

		final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
		machineModelPartEntityPK.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
		machineModelPartEntityPK.setPartId(formData.getPartId());
		machineModelPartEntityPK.setMachineModelPartIdentifier(formData.getMachineModelPartIdentifier());
		final MachineModelPartEntity machineModelPartEntity = machineModelPartRepository.findOne(machineModelPartEntityPK);
		if (machineModelPartEntity == null) {
			bindingResult.reject("common.error.invalidData");
			throw new InvalidDataException("MachineModelPart not found: " + machineModelPartEntityPK);
		}

		final FailureModeHandlingEntityPK failureModeHandlingEntityPK = new FailureModeHandlingEntityPK();
		failureModeHandlingEntityPK.setPartId(machineModelPartEntity.getMachinePartTypeEntity().getPartId());
		failureModeHandlingEntityPK.setFailureModeCode(formData.getFailureModeCode());
		failureModeHandlingEntityPK.setFailureModeHandlingCode(formData.getFailureModeHandlingCode());
		final FailureModeHandlingEntity failureModeHandlingEntity = failureModeHandlingRepository.findOne(failureModeHandlingEntityPK);
		if (failureModeHandlingEntity == null) {
			bindingResult.reject("common.error.invalidData");
			throw new InvalidDataException("FailureModeHandling not found: " + failureModeHandlingEntityPK);
		}
		try {
			final Date serviceReportDate = DateUtil.parse(formData.getDate());
			final ServiceReportEntity serviceReportEntity = new ServiceReportEntity();
			serviceReportEntity.setDate(serviceReportDate);
			serviceReportEntity.setSpbuMachineEntity(spbuMachineEntity);
			serviceReportEntity.setMachineModelPartEntity(machineModelPartEntity);
			serviceReportEntity.setFailureModeHandlingEntity(failureModeHandlingEntity);
			serviceReportEntity.setTechnicianEntity(technicianEntity);
			serviceReportRepository.saveAndFlush(serviceReportEntity);

			for (final SpbuMachineTotalizerEntity spbuMachineTotalizerEntity : spbuMachineEntity.getSpbuMachineTotalizerEntityList()) {
				final ServiceReportSpbuMachineTotalizerEntityPK tmpPk = new ServiceReportSpbuMachineTotalizerEntityPK();
				tmpPk.setServiceReportId(serviceReportEntity.getId());
				tmpPk.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
				tmpPk.setTotalizerId(spbuMachineTotalizerEntity.getSpbuMachineTotalizerEntityPK().getTotalizerId());

				final ServiceReportSpbuMachineTotalizerEntity tmp = new ServiceReportSpbuMachineTotalizerEntity(tmpPk);
				tmp.setCounter(spbuMachineTotalizerEntity.getCounter());
				tmp.setServiceReportEntity(serviceReportEntity);
				serviceReportEntity.getServiceReportSpbuMachineTotalizerEntityList().add(tmp);
			}
		} catch (ParseException ex) {
			bindingResult.rejectValue("date", "common.error.invalidDateFormat");
			throw new InvalidDataException("Invalid date format: " + formData.getDate());
		}
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
		private String date = DateUtil.format(new Date());
		private String partId;
		private String machineModelPartIdentifier;
		private String failureModeCode;
		private String failureModeHandlingCode;

		public FormData() {
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

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getPartId() {
			return partId;
		}

		public void setPartId(String partId) {
			this.partId = partId;
		}

		public String getMachineModelPartIdentifier() {
			return machineModelPartIdentifier;
		}

		public void setMachineModelPartIdentifier(String machineModelPartIdentifier) {
			this.machineModelPartIdentifier = machineModelPartIdentifier;
		}

		public String getFailureModeCode() {
			return failureModeCode;
		}

		public void setFailureModeCode(String failureModeCode) {
			this.failureModeCode = failureModeCode;
		}

		public String getFailureModeHandlingCode() {
			return failureModeHandlingCode;
		}

		public void setFailureModeHandlingCode(String failureModeHandlingCode) {
			this.failureModeHandlingCode = failureModeHandlingCode;
		}

		@Override
		public String toString() {
			return "FormData{" + "spbuId=" + spbuId + ", machineSerial=" + machineSerial + ", date=" + date + ", partId=" + partId + ", machineModelPartIdentifier=" + machineModelPartIdentifier + ", failureModeCode=" + failureModeCode + ", failureModeHandlingCode=" + failureModeHandlingCode + '}';
		}
	}
}

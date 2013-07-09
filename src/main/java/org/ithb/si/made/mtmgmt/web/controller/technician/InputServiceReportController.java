/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.technician;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.exception.InvalidDataException;
import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.FailureModeHandlingRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.util.DateUtil;
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

	public InputServiceReportController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showInputTotalizer(Principal principal, Model model) {
		LOG.debug("showInputTotalizer principal:[{}]", principal);
		model.addAttribute("formData", new FormData());
		return "technician/input_service_report";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doInputServiceReport(Principal principal, @Valid FormData formData, BindingResult bindingResult, Model model) {
		LOG.debug("doInputTotalizer formData:[{}]", formData);
		try {
			_doInputServiceReport(principal, formData, bindingResult);
		} catch (InvalidDataException ex) {
			LOG.error("doInputTotalizer Exception:[{}]", ex.getMessage(), ex);
		} catch (ConstraintViolationException ex) {
			LOG.error("doInputTotalizer ConstraintViolationException:[{}], violations:[{}]", ex.getMessage(), ex.getConstraintViolations(), ex);
		}
		model.addAttribute("formData", formData);
		return "technician/input_service_report";
	}

	@Transactional
	private void _doInputServiceReport(Principal principal, @Valid FormData formData, BindingResult bindingResult) {
		final UserEntity technicianEntity = userRepository.findByLoginId(principal.getName());
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(formData.getMachineSerial());
		if (spbuMachineEntity == null) {
			bindingResult.reject("common.error.invalidData");
			throw new InvalidDataException("SPBU machine not found: " + formData.getSpbuId());
		}

		final FailureModeHandlingEntity dbFailureModeHandling = failureModeHandlingRepository.findOne(formData.getFailureModeHandlingCode());
		if (dbFailureModeHandling == null) {
			bindingResult.reject("common.error.invalidData");
			throw new InvalidDataException("FailureModeHandling not found: " + formData.getSpbuId());
		}

		try {
			final Date serviceReportDate = DateUtil.parse(formData.getDate());
			final ServiceReportEntity serviceReportEntity = new ServiceReportEntity();
			serviceReportEntity.setDate(serviceReportDate);
			serviceReportEntity.setFailureModeHandlingEntity(dbFailureModeHandling);
			serviceReportEntity.setSpbuMachineEntity(spbuMachineEntity);
			serviceReportEntity.setTechnicianEntity(technicianEntity);
			serviceReportRepository.saveAndFlush(serviceReportEntity);

			for (final SpbuMachineTotalizerEntity spbuMachineTotalizerEntity : spbuMachineEntity.getSpbuMachineTotalizerEntityList()) {
				final ServiceReportSpbuMachineTotalizerEntityPK tmpPk = new ServiceReportSpbuMachineTotalizerEntityPK();
				tmpPk.setServiceReportId(serviceReportEntity.getId());
				tmpPk.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
				tmpPk.setTotalizerId(spbuMachineTotalizerEntity.getSpbuMachineTotalizerEntityPK().getTotalizerId());
				tmpPk.setServiceReportId(serviceReportEntity.getId());

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

	public static class FormData {

		private long spbuId;
		private String machineSerial;
		private String date = DateUtil.format(new Date());
		private long partId;
		private long failureModeCode;
		private long failureModeHandlingCode;

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

		public long getPartId() {
			return partId;
		}

		public void setPartId(long partId) {
			this.partId = partId;
		}

		public long getFailureModeCode() {
			return failureModeCode;
		}

		public void setFailureModeCode(long failureModeCode) {
			this.failureModeCode = failureModeCode;
		}

		public long getFailureModeHandlingCode() {
			return failureModeHandlingCode;
		}

		public void setFailureModeHandlingCode(long failureModeHandlingCode) {
			this.failureModeHandlingCode = failureModeHandlingCode;
		}

		@Override
		public String toString() {
			return "FormData{" + "spbuId=" + spbuId + ", machineSerial=" + machineSerial + ", date=" + date + ", partId=" + partId + ", failureModeCode=" + failureModeCode + ", failureModeHandlingCode=" + failureModeHandlingCode + '}';
		}
	}
}

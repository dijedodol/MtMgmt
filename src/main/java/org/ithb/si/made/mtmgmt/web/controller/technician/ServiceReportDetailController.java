/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.technician;

import java.security.Principal;
import java.util.Collections;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportRepository;
import org.ithb.si.made.mtmgmt.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gde
 */
@Controller("technicianServiceReportDetailController")
@RequestMapping("/technician/service_report_detail")
public class ServiceReportDetailController {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceReportDetailController.class);
	@Autowired
	private ServiceReportRepository serviceReportRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String showFailureHistoryDetail(Principal principal, Model model, @RequestParam("serviceReportId") long serviceReportId) {
		LOG.debug("showFailureHistory principal:[{}]", principal);

		final ServiceReportEntity serviceReport = serviceReportRepository.findOne(serviceReportId);
		if (serviceReport == null) {
			return "common/hacker";
		}

		final SpbuMachineEntity spbuMachine = serviceReport.getSpbuMachineEntity();
		final SpbuEntity spbu = spbuMachine.getSpbuEntity();
		final MachineModelEntity machineModel = spbuMachine.getMachineModelEntity();

		model.addAttribute("date", DateUtil.format(serviceReport.getDate()));
		model.addAttribute("spbuCode", spbu.getCode());
		model.addAttribute("supervisorName", spbu.getSupervisorEntity().getFullName());
		model.addAttribute("spbuAddress", spbu.getAddress());
		model.addAttribute("machineIdentifier", spbuMachine.getMachineIdentifier());
		model.addAttribute("machineSerial", spbuMachine.getMachineSerial());
		model.addAttribute("machineModel", machineModel.getModelId());

		final StringBuilder totalizerStringBuilder = new StringBuilder();
		for (ServiceReportSpbuMachineTotalizerEntity totalizerEntity : serviceReport.getServiceReportSpbuMachineTotalizerEntityList()) {
			totalizerStringBuilder.append(totalizerEntity.getServiceReportSpbuMachineTotalizerEntityPK().getTotalizerId())
							.append(": ")
							.append(totalizerEntity.getCounter())
							.append("<br />");
		}
		model.addAttribute("totalizerString", totalizerStringBuilder.toString());

		model.addAttribute("failureModeName", serviceReport.getFailureModeHandlingEntity().getPartFailureModeEntity().getName());
		model.addAttribute("partIds", Collections.singletonList(serviceReport.getMachineModelPartEntity().getMachineModelPartEntityPK().getPartId()));
		model.addAttribute("failureModeHandlingName", serviceReport.getFailureModeHandlingEntity().getName());

		return "common/show_service_report_detail";
	}
}

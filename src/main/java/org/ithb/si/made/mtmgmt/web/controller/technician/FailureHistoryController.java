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
 * @author Uyeee
 */
@Controller("technicianFailureHistoryController")
@RequestMapping("/technician/failure_history")
public class FailureHistoryController {

	private static final Logger LOG = LoggerFactory.getLogger(FailureHistoryController.class);
	@Autowired
	private ServiceReportRepository serviceReportRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String showFailureHistory(Principal principal, Model model) {
		LOG.debug("showFailureHistory principal:[{}]", principal);
		model.addAttribute("formData", new FormData());
		return "technician/failure_history";
	}

	public static class FormData {

		private long spbuId;

		public FormData() {
		}

		public long getSpbuId() {
			return spbuId;
		}

		public void setSpbuId(long spbuId) {
			this.spbuId = spbuId;
		}
	}
}

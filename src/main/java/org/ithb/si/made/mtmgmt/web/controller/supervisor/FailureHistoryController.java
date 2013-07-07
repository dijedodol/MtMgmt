/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.supervisor;

import java.security.Principal;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Uyeee
 */
@Controller("failureHistoryController")
@RequestMapping("/supervisor/failure_history")
public class FailureHistoryController {

	private static final Logger LOG = LoggerFactory.getLogger(FailureHistoryController.class);
	@Autowired
	private ServiceReportRepository serviceReportRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String showFailureHistory(Principal principal, Model model) {
		LOG.debug("showFailureHistory principal:[{}]", principal);
		model.addAttribute("formData", new InputTotalizerController.TotalizerFormData());
		return "supervisor/failure_history";
	}

	public static class TotalizerFormData {

		private long spbuId;
		private String machineIdentifier;

		public TotalizerFormData() {
		}

		@Override
		public String toString() {
			return "TotalizerFormData{" + "spbuId=" + spbuId + ", machineIdentifier=" + machineIdentifier + '}';
		}

		public long getSpbuId() {
			return spbuId;
		}

		public void setSpbuId(long spbuId) {
			this.spbuId = spbuId;
		}

		public String getMachineIdentifier() {
			return machineIdentifier;
		}

		public void setMachineIdentifier(String machineIdentifier) {
			this.machineIdentifier = machineIdentifier;
		}
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.web.controller.technician;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Uyeee
 */
@Controller("maintenanceScheduleController")
@RequestMapping("/technician/maintenance_schedule")
public class MaintenanceScheduleController {

	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Model model) {
		return "technician/maintenance_schedule";
	}
}

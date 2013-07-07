/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.technician;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Uyeee
 */
@Controller("technicianHomeController")
@RequestMapping("/technician/home")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String showHome() {
		return "technician/home";
	}
}

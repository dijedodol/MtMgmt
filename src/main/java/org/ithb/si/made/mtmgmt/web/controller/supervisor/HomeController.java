/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.supervisor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Uyeee
 */
@Controller("supervisorHomeController")
@RequestMapping("/supervisor/home")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String showHome() {
		return "supervisor/home";
	}
}

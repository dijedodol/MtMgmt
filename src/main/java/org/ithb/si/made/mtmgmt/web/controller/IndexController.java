/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller;

import java.security.Principal;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Uyeee
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Principal principal) {
		final UserEntity userEntity = userRepository.findByLoginId(principal.getName());
		switch (userEntity.getAccessRole()) {
			case ADMIN:
				return "redirect:/admin/home.htm";
			case TECHNICIAN:
				return "redirect:/technician/home.htm";
			case SUPERVISOR:
				return "redirect:/supervisor/home.htm";
			default:
				throw new RuntimeException("Unknown AccessRole: " + userEntity.getAccessRole());
		}
	}
}

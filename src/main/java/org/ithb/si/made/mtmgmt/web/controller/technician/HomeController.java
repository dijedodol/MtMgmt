/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.technician;

import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelPartRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportSpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Uyeee
 */
@Controller("technicianHomeController")
@RequestMapping("/technician/home")
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	private static final long SECOND = 1000l;
	private static final long MINUTE = 60 * SECOND;
	private static final long HOUR = 60 * MINUTE;
	private static final long DAY = 24 * HOUR;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private MachineModelPartRepository machineModelPartRepository;
	@Autowired
	private SpbuMachineTotalizerRepository spbuMachineTotalizerRepository;
	@Autowired
	private ServiceReportSpbuMachineTotalizerRepository serviceReportSpbuMachineTotalizerRepository;

	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Model model) {
		final List<SpbuEntity> dbSpbuEntities = spbuRepository.findAll();
		for (final SpbuEntity dbSpbuEntity : dbSpbuEntities) {
		}
		return "technician/home";
	}
}

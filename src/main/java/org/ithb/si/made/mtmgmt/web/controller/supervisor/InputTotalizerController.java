/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.supervisor;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.exception.InvalidDataException;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
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
@Controller("supervisorInputTotalizerController")
@RequestMapping("/supervisor/input_totalizer")
public class InputTotalizerController {

	private static final Logger LOG = LoggerFactory.getLogger(InputTotalizerController.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private SpbuMachineRepository spbuMachineRepository;
	@Autowired
	private SpbuMachineTotalizerRepository spbuMachineTotalizerRepository;
	@Autowired
	private MachineTotalizerRepository machineTotalizerRepository;

	public InputTotalizerController() {
		LOG.debug("InputTotalizerController <init>");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showInputTotalizer(Principal principal, Model model) {
		LOG.debug("showInputTotalizer principal:[{}]", principal);
		model.addAttribute("totalizerFormData", new TotalizerFormData());
		return "supervisor/input_totalizer";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doInputTotalizer(Principal principal, @Valid TotalizerFormData formData, BindingResult bindingResult) {
		LOG.debug("doInputTotalizer formData:[{}]", formData);
		try {
			_doInputTotalizer(principal, formData, bindingResult);
		} catch (InvalidDataException ex) {
			LOG.error("doInputTotalizer Exception:[{}]", ex.getMessage(), ex);
		}
		return "supervisor/input_totalizer";
	}

	@Transactional
	private void _doInputTotalizer(Principal principal, @Valid TotalizerFormData formData, BindingResult bindingResult) {
		final UserEntity dbUserEntity = userRepository.findByLoginId(principal.getName());
		final SpbuMachineEntity dbSpbuMachineEntity = spbuMachineRepository.findOne(new SpbuMachineEntityPK(formData.getSpbuId(), formData.getMachineIdentifier()));
		if (dbUserEntity != null && dbSpbuMachineEntity != null && dbUserEntity.getId() == dbSpbuMachineEntity.getSpbuEntity().getSupervisor().getId()) {
			for (int i = 0; i < formData.getTotalizerIds().size(); i++) {
				long totalizerId = formData.getTotalizerIds().get(i);
				double totalizerValue = formData.getTotalizerValues().get(i);

				final MachineTotalizerEntity machineTotalizerEntity = machineTotalizerRepository.findOne(totalizerId);
				if (machineTotalizerEntity == null) {
					bindingResult.reject("supervisor.error.invalidData");
					throw new InvalidDataException("Invalid totalizer: " + totalizerId);
				}

				SpbuMachineTotalizerEntity spbuMachineTotalizerEntity = spbuMachineTotalizerRepository.findOne(new SpbuMachineTotalizerEntityPK(formData.getSpbuId(), formData.getMachineIdentifier(), totalizerId));
				if (spbuMachineTotalizerEntity == null) {
					spbuMachineTotalizerEntity = new SpbuMachineTotalizerEntity(formData.getSpbuId(), formData.getMachineIdentifier(), totalizerId);
					spbuMachineTotalizerEntity.setCounter(0);
					spbuMachineTotalizerEntity.setMachineTotalizerEntity(machineTotalizerEntity);
					spbuMachineTotalizerEntity.setSpbuMachineEntity(dbSpbuMachineEntity);
				}

				if (totalizerValue < spbuMachineTotalizerEntity.getCounter()) {
					bindingResult.reject("supervisor.error.invalidData");
					throw new InvalidDataException("Input totalizer less than current totalizer: " + totalizerValue + " < " + spbuMachineTotalizerEntity.getCounter());
				}
				
				spbuMachineTotalizerEntity.setCounter(totalizerValue);
				dbSpbuMachineEntity.getSpbuMachineTotalizerEntityList().add(spbuMachineTotalizerEntity);
			}
			spbuMachineRepository.saveAndFlush(dbSpbuMachineEntity);
		} else {
			bindingResult.reject("supervisor.error.spbuMismatch");
		}
	}

	public static class TotalizerFormData {

		private long spbuId;
		private String machineIdentifier;
		private List<Long> totalizerIds;
		private List<Double> totalizerValues;

		public TotalizerFormData() {
		}

		@Override
		public String toString() {
			return "TotalizerFormData{" + "spbuId=" + spbuId + ", machineIdentifier=" + machineIdentifier + ", totalizerIds=" + totalizerIds + ", totalizerValues=" + totalizerValues + '}';
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

		public List<Long> getTotalizerIds() {
			return totalizerIds;
		}

		public void setTotalizerIds(List<Long> totalizerIds) {
			this.totalizerIds = totalizerIds;
		}

		public List<Double> getTotalizerValues() {
			return totalizerValues;
		}

		public void setTotalizerValues(List<Double> totalizerValues) {
			this.totalizerValues = totalizerValues;
		}
	}
}

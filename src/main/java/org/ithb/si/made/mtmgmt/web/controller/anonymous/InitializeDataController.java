/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.anonymous;

import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.security.AccessRole;
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
 * @author gde
 */
@Controller("anonymousInitializeDataController")
@RequestMapping("/anonymous/initialize_data")
public class InitializeDataController {

	private static final Logger LOG = LoggerFactory.getLogger(InitializeDataController.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private MachineModelRepository machineModelRepository;
	@Autowired
	private MachineTotalizerRepository machineTotalizerRepository;
	@Autowired
	private SpbuMachineRepository spbuMachineRepository;

	public InitializeDataController() {
		LOG.debug("InitializeDataController <init>");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showResult(Model model) {
		return doShowResult(model);
	}

	@Transactional
	public String doShowResult(Model model) {
		final UserEntity dbUserEntityAdm = createUserIfNotExist("adm", AccessRole.ADMIN);
		final UserEntity dbUserEntityTch = createUserIfNotExist("tch", AccessRole.TECHNICIAN);
		final UserEntity dbUserEntitySpv = createUserIfNotExist("spv", AccessRole.SUPERVISOR);
		model.addAttribute("dbUserEntityAdm", dbUserEntityAdm);
		model.addAttribute("dbUserEntityTch", dbUserEntityTch);
		model.addAttribute("dbUserEntitySpv", dbUserEntitySpv);

		try {
			final SpbuEntity dbSpbuEntity = createSpbuIfNotExist(dbUserEntitySpv, "54-801.21");
			model.addAttribute("spbuEntity", dbSpbuEntity);

			final MachineModelEntity dbMachineModel = createMachineIfNotExist("Encore S-300.1", 6);
			final MachineModelEntity dbMachineMode2 = createMachineIfNotExist("Encore S-300.2", 4);
			LOG.debug("showResult dbMachineModel.getMachineModelTotalizerEntityList:[{}]", dbMachineModel.getMachineModelTotalizerEntityList());
			model.addAttribute("dbMachineModel", dbMachineModel);
			model.addAttribute("dbMachineMode2", dbMachineMode2);

			final SpbuMachineEntity dbSpbuMachine1 = createSpbuMachineIfNotExist(dbSpbuEntity, "Mesin1", dbMachineModel);
			final SpbuMachineEntity dbSpbuMachine2 = createSpbuMachineIfNotExist(dbSpbuEntity, "Mesin2", dbMachineModel);
			final SpbuMachineEntity dbSpbuMachine3 = createSpbuMachineIfNotExist(dbSpbuEntity, "Mesin3", dbMachineMode2);
			final SpbuMachineEntity dbSpbuMachine4 = createSpbuMachineIfNotExist(dbSpbuEntity, "Mesin4", dbMachineMode2);

			model.addAttribute("dbSpbuMachine1", dbSpbuMachine1);
			model.addAttribute("dbSpbuMachine2", dbSpbuMachine2);
			model.addAttribute("dbSpbuMachine3", dbSpbuMachine3);
			model.addAttribute("dbSpbuMachine4", dbSpbuMachine4);
		} catch (javax.validation.ConstraintViolationException ex) {
			LOG.error("Exception:[{}], violations:[{}]", ex.getMessage(), ex.getConstraintViolations(), ex);
		}
		return "anonymous/initialize_data";
	}

	private UserEntity createUserIfNotExist(String loginId, AccessRole accessRole) {
		UserEntity ret = userRepository.findByLoginId(loginId);
		if (ret == null) {
			ret = new UserEntity();
			ret.setLoginId(loginId);
			ret.setFullName(loginId);
			ret.setAccessRole(accessRole);
			ret.setPasswordHash(loginId);
			ret = userRepository.saveAndFlush(ret);
		}
		return ret;
	}

	private SpbuEntity createSpbuIfNotExist(UserEntity supervisor, String code) {
		LOG.debug("createSpbuIfNotExist supervisor:[{}]", supervisor);
		SpbuEntity ret = spbuRepository.findByCode(code);
		if (ret == null) {
			ret = new SpbuEntity();
			ret.setCode(code);
			ret.setAddress("Address_" + code);
			ret.setPhone("Phone_" + code);
			ret.setSupervisorEntity(supervisor);
			ret = spbuRepository.saveAndFlush(ret);
		}
		return ret;
	}

	private MachineModelEntity createMachineIfNotExist(String code, int totalizerCount) {
		MachineModelEntity ret = machineModelRepository.findByCode(code);
		if (ret == null) {
			ret = new MachineModelEntity();
			ret.setCode(code);
			ret.setName("Machine_" + code);
			ret = machineModelRepository.saveAndFlush(ret);

			for (int i = 0; i < totalizerCount; i++) {
				MachineTotalizerEntity machineTotalizer = new MachineTotalizerEntity();
				machineTotalizer.setName("Totalizer_" + (i + 1));
				machineTotalizer = machineTotalizerRepository.saveAndFlush(machineTotalizer);

				final MachineModelTotalizerEntity machineModelTotalizer = new MachineModelTotalizerEntity(new MachineModelTotalizerEntityPK(ret.getId(), machineTotalizer.getId()));
				machineModelTotalizer.setMachineModelEntity(ret);
				machineModelTotalizer.setMachineTotalizerEntity(machineTotalizer);
				ret.getMachineModelTotalizerEntityList().add(machineModelTotalizer);
			}
			ret = machineModelRepository.saveAndFlush(ret);
		}
		return ret;
	}

	private SpbuMachineEntity createSpbuMachineIfNotExist(SpbuEntity spbuEntity, String identifier, MachineModelEntity machineModelEntity) {
		LOG.debug("createSpbuMachineIfNotExist spbuEntity:[{}], identifier:[{}], machineModelEntity:[{}]", spbuEntity, identifier, machineModelEntity);
		SpbuMachineEntity ret = spbuMachineRepository.findOne(new SpbuMachineEntityPK(spbuEntity.getId(), identifier));
		if (ret == null) {
			ret = new SpbuMachineEntity(new SpbuMachineEntityPK(spbuEntity.getId(), identifier));
			ret.setMachineModelEntity(machineModelEntity);
			ret.setSpbuEntity(spbuEntity);
			spbuMachineRepository.saveAndFlush(ret);

			LOG.debug("createSpbuMachineIfNotExist machineModelEntity.getMachineModelTotalizerEntityList:[{}]", machineModelEntity.getMachineModelTotalizerEntityList());
			for (final MachineModelTotalizerEntity machineModelTotalizerEntity : machineModelEntity.getMachineModelTotalizerEntityList()) {
				LOG.info("createSpbuMachineIfNotExist machineModelEmachineModelTotalizerEntity:[{}]", machineModelTotalizerEntity);
				LOG.info("createSpbuMachineIfNotExist machineModelTotalizerEntity.getMachineTotalizerEntity:[{}]", machineModelTotalizerEntity.getMachineTotalizerEntity());
				final SpbuMachineTotalizerEntity tmp = new SpbuMachineTotalizerEntity(spbuEntity.getId(), identifier, machineModelTotalizerEntity.getMachineModelTotalizerEntityPK().getMachineTotalizerId());
				tmp.setCounter(0);
				tmp.setMachineTotalizerEntity(machineModelTotalizerEntity.getMachineTotalizerEntity());
				tmp.setSpbuMachineEntity(ret);
				ret.getSpbuMachineTotalizerEntityList().add(tmp);
			}
			ret = spbuMachineRepository.saveAndFlush(ret);
		}
		return ret;
	}
}

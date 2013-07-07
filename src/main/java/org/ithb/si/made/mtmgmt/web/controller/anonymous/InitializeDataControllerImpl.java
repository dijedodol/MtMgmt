/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.anonymous;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
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
public class InitializeDataControllerImpl {

	private static final Logger LOG = LoggerFactory.getLogger(InitializeDataControllerImpl.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private MachineModelRepository machineModelRepository;
	@Autowired
	private MachineModelTotalizerRepository machineModelTotalizerRepository;
	@Autowired
	private MachineTotalizerRepository machineTotalizerRepository;
	@Autowired
	private SpbuMachineRepository spbuMachineRepository;
	@Autowired
	private SpbuMachineTotalizerRepository spbuMachineTotalizerRepository;
	@PersistenceContext
	private EntityManager em;

	public InitializeDataControllerImpl() {
		LOG.debug("InitializeDataController <init>");
	}

	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public String showResult(Model model) {
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

			em.refresh(dbUserEntityAdm);
			em.refresh(dbUserEntityTch);
			em.refresh(dbUserEntitySpv);
			em.refresh(dbSpbuEntity);
			em.refresh(dbMachineModel);
			em.refresh(dbMachineMode2);
			em.refresh(dbSpbuMachine1);
			em.refresh(dbSpbuMachine2);
			em.refresh(dbSpbuMachine3);
			em.refresh(dbSpbuMachine4);
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
		}
		supervisor.getSpbuEntityList().add(ret);
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

				final MachineModelTotalizerEntityPK machineModelTotalizerPk = new MachineModelTotalizerEntityPK();
				machineModelTotalizerPk.setMachineModelId(ret.getId());
				machineModelTotalizerPk.setMachineTotalizerId(machineTotalizer.getId());

				final MachineModelTotalizerEntity machineModelTotalizer = new MachineModelTotalizerEntity(machineModelTotalizerPk);
				machineModelTotalizerRepository.save(machineModelTotalizer);
			}
			machineModelTotalizerRepository.flush();
		}
		return ret;
	}

	private SpbuMachineEntity createSpbuMachineIfNotExist(SpbuEntity spbuEntity, String machineIdentifier, MachineModelEntity machineModelEntity) {
		LOG.debug("createSpbuMachineIfNotExist spbuEntity:[{}], identifier:[{}], machineModelEntity:[{}]", spbuEntity, machineIdentifier, machineModelEntity);
		SpbuMachineEntity ret = spbuMachineRepository.findOne(new SpbuMachineEntityPK(spbuEntity.getId(), machineIdentifier));
		if (ret == null) {
			ret = new SpbuMachineEntity(new SpbuMachineEntityPK(spbuEntity.getId(), machineIdentifier));
			ret.setMachineModelEntity(machineModelEntity);
			ret = spbuMachineRepository.saveAndFlush(ret);

			LOG.debug("createSpbuMachineIfNotExist machineModelEntity.getMachineModelTotalizerEntityList:[{}]", machineModelEntity.getMachineModelTotalizerEntityList());
			for (final MachineModelTotalizerEntity machineModelTotalizerEntity : machineModelEntity.getMachineModelTotalizerEntityList()) {
				LOG.info("createSpbuMachineIfNotExist machineModelEmachineModelTotalizerEntity:[{}]", machineModelTotalizerEntity);
				LOG.info("createSpbuMachineIfNotExist machineModelTotalizerEntity.getMachineTotalizerEntity:[{}]", machineModelTotalizerEntity.getMachineTotalizerEntity());

				final SpbuMachineTotalizerEntityPK tmpPk = new SpbuMachineTotalizerEntityPK();
				tmpPk.setSpbuId(spbuEntity.getId());
				tmpPk.setMachineIdentifier(ret.getSpbuMachineEntityPK().getMachineIdentifier());
				tmpPk.setMachineTotalizerId(machineModelTotalizerEntity.getMachineTotalizerEntity().getId());

				SpbuMachineTotalizerEntity tmp = new SpbuMachineTotalizerEntity(tmpPk);
				tmp.setCounter(0);
				spbuMachineTotalizerRepository.save(tmp);
			}
			spbuMachineTotalizerRepository.flush();
		}
		return ret;
	}
}

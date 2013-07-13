/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.admin;

import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Uyeee
 */
@Controller("adminCrudSpbuController")
@RequestMapping("/admin/crud/spbu")
public class CrudSpbuController {

	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/spbu";
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpbuMachineRepository spbuMachineRepository;
	@Autowired
	private MachineModelRepository machineModelRepository;

	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("spbuId") long spbuId) {
		spbuRepository.delete(spbuId);
		return list(model);
	}

	@Transactional
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Model model) {
		return list(model);
	}

	@Transactional
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String list(Model model) {
		final List<SpbuEntity> spbuEntities = spbuRepository.findAll();
		final List<ViewData> viewDatas = new LinkedList<>();

		for (final SpbuEntity spbuEntity : spbuEntities) {
			viewDatas.add(getViewData(spbuEntity));
		}

		model.addAttribute("viewDatas", viewDatas);
		return CONTROLLER_VIEW_ROOT + "/list";
	}

	@Transactional
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("partId") long spbuId) {
		final SpbuEntity spbuEntity = spbuRepository.findOne(spbuId);
		final ViewData viewData = getViewData(spbuEntity);
		model.addAttribute("viewData", viewData);
		return CONTROLLER_VIEW_ROOT + "/view";
	}

	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		final FormData formData = new FormData();
		model.addAttribute("formData", formData);
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("formData") FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			SpbuEntity spbuEntity = spbuRepository.findByCode(formData.getCode());
			if (spbuEntity == null) {
				final UserEntity supervisorEntity = userRepository.findOne(formData.getSupervisorId());
				if (supervisorEntity != null) {
					spbuEntity = new SpbuEntity();
					spbuEntity.setCode(formData.getCode());
					spbuEntity.setAddress(formData.getAddress());
					spbuEntity.setPhone(formData.getPhone());
					spbuEntity.setSupervisorEntity(supervisorEntity);
					spbuEntity = spbuRepository.saveAndFlush(spbuEntity);
					System.out.println("Supervisor Spbu: " + supervisorEntity.getSpbuEntityList().size());

					for (int i = 0; i < formData.getMachineSerials().size(); i++) {
						final String machineSerial = formData.getMachineSerials().get(i);
						final String modelId = formData.getModelIds().get(i);
						final String machineIdentifier = formData.getMachineIdentifiers().get(i);
						SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(machineSerial);
						if (spbuMachineEntity == null) {
							final MachineModelEntity machineModelEntity = machineModelRepository.findOne(modelId);
							if (machineModelEntity != null) {
								spbuMachineEntity = new SpbuMachineEntity(machineSerial);
								spbuMachineEntity.setMachineModelEntity(machineModelEntity);
								spbuMachineEntity.setMachineIdentifier(machineIdentifier);
								spbuEntity.getSpbuMachineEntityList().add(spbuMachineEntity);
							} else {
								bindingResult.rejectValue("modelIds", "common.error.invalidData");
							}
						} else {
							bindingResult.rejectValue("machineSerials", "common.error.invalidData");
						}
					}
				} else {
					bindingResult.rejectValue("supervisorId", "common.error.invalidData");
				}
			} else {
				bindingResult.rejectValue("spbuCode", "common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Model model, @RequestParam("spbuId") long spbuId) {
		final SpbuEntity spbuEntity = spbuRepository.findOne(spbuId);
		if (spbuEntity != null) {
			model.addAttribute("formData", getFormData(spbuEntity));
			return CONTROLLER_VIEW_ROOT + "/update";
		} else {
			throw new RuntimeException("Unknown partId");
		}
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			SpbuEntity spbuEntity = spbuRepository.findByCode(formData.getCode());
			if (spbuEntity != null) {
				final UserEntity supervisorEntity = userRepository.findOne(formData.getSupervisorId());
				if (supervisorEntity != null) {
					spbuEntity.setCode(formData.getCode());
					spbuEntity.setAddress(formData.getAddress());
					spbuEntity.setPhone(formData.getPhone());
					spbuEntity.setSupervisorEntity(supervisorEntity);

					spbuEntity.getSpbuMachineEntityList().clear();
					for (int i = 0; i < formData.getMachineSerials().size(); i++) {
						final String machineSerial = formData.getMachineSerials().get(i);
						final String modelId = formData.getModelIds().get(i);
						final String machineIdentifier = formData.getMachineIdentifiers().get(i);
						SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(machineSerial);
						if (spbuMachineEntity == null) {
							spbuMachineEntity = new SpbuMachineEntity(machineSerial);
							spbuMachineEntity.setSpbuEntity(spbuEntity);
						}
						
						if (spbuMachineEntity.getSpbuEntity().equals(spbuEntity)) {
							final MachineModelEntity machineModelEntity = machineModelRepository.findOne(modelId);
							if (machineModelEntity != null) {
								spbuMachineEntity.setMachineModelEntity(machineModelEntity);
								spbuMachineEntity.setMachineIdentifier(machineIdentifier);
								spbuEntity.getSpbuMachineEntityList().add(spbuMachineEntity);
							} else {
								bindingResult.rejectValue("modelIds", "common.error.invalidData");
							}
						} else {
							bindingResult.rejectValue("machineSerials", "common.error.invalidData");
						}
					}
				} else {
					bindingResult.rejectValue("supervisorId", "common.error.invalidData");
				}
			} else {
				bindingResult.rejectValue("spbuCode", "common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/update";
	}

	private FormData getFormData(SpbuEntity spbuEntity) {
		final FormData formData = new FormData();
		formData.setCode(spbuEntity.getCode());
		formData.setAddress(spbuEntity.getAddress());
		formData.setPhone(spbuEntity.getPhone());
		formData.setSupervisorId(spbuEntity.getSupervisorEntity().getId());
		formData.setMachineSerials(getMachineSerials(spbuEntity));
		formData.setModelIds(getModelIds(spbuEntity));
		formData.setMachineIdentifiers(getMachineIdentifiers(spbuEntity));
		return formData;
	}

	private ViewData getViewData(SpbuEntity spbuEntity) {
		final ViewData viewData = new ViewData();
		viewData.setId(spbuEntity.getId());
		viewData.setCode(spbuEntity.getCode());
		viewData.setAddress(spbuEntity.getAddress());
		viewData.setPhone(spbuEntity.getPhone());
		viewData.setSupervisorId(spbuEntity.getSupervisorEntity().getId());
		viewData.setMachineSerials(getMachineSerials(spbuEntity));
		viewData.setModelIds(getModelIds(spbuEntity));
		viewData.setMachineIdentifiers(getMachineIdentifiers(spbuEntity));
		return viewData;
	}

	private List<String> getMachineSerials(SpbuEntity spbuEntity) {
		final List<String> ret = new LinkedList<>();
		for (final SpbuMachineEntity spbuMachineEntity : spbuEntity.getSpbuMachineEntityList()) {
			ret.add(spbuMachineEntity.getMachineSerial());
		}
		return ret;
	}

	private List<String> getModelIds(SpbuEntity spbuEntity) {
		final List<String> ret = new LinkedList<>();
		for (final SpbuMachineEntity spbuMachineEntity : spbuEntity.getSpbuMachineEntityList()) {
			ret.add(spbuMachineEntity.getMachineModelEntity().getModelId());
		}
		return ret;
	}

	private List<String> getMachineIdentifiers(SpbuEntity spbuEntity) {
		final List<String> ret = new LinkedList<>();
		for (final SpbuMachineEntity spbuMachineEntity : spbuEntity.getSpbuMachineEntityList()) {
			ret.add(spbuMachineEntity.getMachineIdentifier());
		}
		return ret;
	}

	public static class ViewData {

		private long id;
		private String code;
		private String address;
		private String phone;
		private long supervisorId;
		private List<String> machineSerials = new LinkedList<>();
		private List<String> modelIds = new LinkedList<>();
		private List<String> machineIdentifiers = new LinkedList<>();

		public ViewData() {
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public long getSupervisorId() {
			return supervisorId;
		}

		public void setSupervisorId(long supervisorId) {
			this.supervisorId = supervisorId;
		}

		public List<String> getMachineSerials() {
			return machineSerials;
		}

		public void setMachineSerials(List<String> machineSerials) {
			this.machineSerials = machineSerials;
		}

		public List<String> getModelIds() {
			return modelIds;
		}

		public void setModelIds(List<String> modelIds) {
			this.modelIds = modelIds;
		}

		public List<String> getMachineIdentifiers() {
			return machineIdentifiers;
		}

		public void setMachineIdentifiers(List<String> machineIdentifiers) {
			this.machineIdentifiers = machineIdentifiers;
		}

		@Override
		public String toString() {
			return "ViewData{" + "id=" + id + ", code=" + code + ", address=" + address + ", phone=" + phone + ", supervisorId=" + supervisorId + ", machineSerials=" + machineSerials + ", modelIds=" + modelIds + ", machineIdentifiers=" + machineIdentifiers + '}';
		}
	}

	public static class FormData {

		private String code;
		private String address;
		private String phone;
		private long supervisorId;
		private List<String> machineSerials = new LinkedList<>();
		private List<String> modelIds = new LinkedList<>();
		private List<String> machineIdentifiers = new LinkedList<>();

		public FormData() {
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public long getSupervisorId() {
			return supervisorId;
		}

		public void setSupervisorId(long supervisorId) {
			this.supervisorId = supervisorId;
		}

		public List<String> getMachineSerials() {
			return machineSerials;
		}

		public void setMachineSerials(List<String> machineSerials) {
			this.machineSerials = machineSerials;
		}

		public List<String> getModelIds() {
			return modelIds;
		}

		public void setModelIds(List<String> modelIds) {
			this.modelIds = modelIds;
		}

		public List<String> getMachineIdentifiers() {
			return machineIdentifiers;
		}

		public void setMachineIdentifiers(List<String> machineIdentifiers) {
			this.machineIdentifiers = machineIdentifiers;
		}

		@Override
		public String toString() {
			return "FormData{" + "code=" + code + ", address=" + address + ", phone=" + phone + ", supervisorId=" + supervisorId + ", machineSerials=" + machineSerials + ", modelIds=" + modelIds + ", machineIdentifiers=" + machineIdentifiers + '}';
		}
	}
}

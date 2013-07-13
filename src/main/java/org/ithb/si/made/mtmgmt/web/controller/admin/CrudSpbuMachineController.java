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
@Controller("adminCrudSpbuMachineController")
@RequestMapping("/admin/crud/spbu_machine")
public class CrudSpbuMachineController {

	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/spbu_machine";
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
	public String delete(Model model, @RequestParam("machineSerial") String machineSerial) {
		spbuMachineRepository.delete(machineSerial);
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
		final List<SpbuMachineEntity> spbuMachineEntities = spbuMachineRepository.findAll();
		final List<ViewData> viewDatas = new LinkedList<>();

		for (final SpbuMachineEntity spbuMachineEntity : spbuMachineEntities) {
			viewDatas.add(getViewData(spbuMachineEntity));
		}

		model.addAttribute("viewDatas", viewDatas);
		return CONTROLLER_VIEW_ROOT + "/list";
	}

	@Transactional
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("machineSerial") String machineSerial) {
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(machineSerial);
		final ViewData viewData = getViewData(spbuMachineEntity);
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
			SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(formData.getMachineSerial());
			if (spbuMachineEntity == null) {
				final SpbuEntity spbuEntity = spbuRepository.findOne(formData.getSpbuId());
				final MachineModelEntity machineModelEntity = machineModelRepository.findOne(formData.getModelId());
				if (spbuEntity != null && machineModelEntity != null) {
					spbuMachineEntity = new SpbuMachineEntity(formData.getMachineSerial());
					spbuMachineEntity.setSpbuEntity(spbuEntity);
					spbuMachineEntity.setMachineModelEntity(machineModelEntity);
					spbuMachineEntity.setMachineIdentifier(formData.getMachineIdentifier());
					spbuMachineEntity = spbuMachineRepository.save(spbuMachineEntity);
					model.addAttribute("formData", getFormData(spbuMachineEntity));
				} else {
					bindingResult.reject("common.error.invalidData");
				}
			} else {
				bindingResult.rejectValue("machineSerial", "common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Model model, @RequestParam("machineSerial") String machineSerial) {
		final SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(machineSerial);
		if (spbuMachineEntity != null) {
			model.addAttribute("formData", getFormData(spbuMachineEntity));
			return CONTROLLER_VIEW_ROOT + "/update";
		} else {
			throw new RuntimeException("Unknown spbuMachineEntity");
		}
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			SpbuMachineEntity spbuMachineEntity = spbuMachineRepository.findOne(formData.getMachineSerial());
			if (spbuMachineEntity != null) {
				final SpbuEntity spbuEntity = spbuRepository.findOne(formData.getSpbuId());
				final MachineModelEntity machineModelEntity = machineModelRepository.findOne(formData.getModelId());
				if (spbuEntity != null && machineModelEntity != null) {
					spbuMachineEntity.setSpbuEntity(spbuEntity);
					spbuMachineEntity.setMachineModelEntity(machineModelEntity);
					spbuMachineEntity.setMachineIdentifier(formData.getMachineIdentifier());
					spbuMachineEntity = spbuMachineRepository.save(spbuMachineEntity);
					model.addAttribute("formData", getFormData(spbuMachineEntity));
				} else {
					bindingResult.reject("common.error.invalidData");
				}
			} else {
				bindingResult.rejectValue("machineSerial", "common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/update";
	}

	private ViewData getViewData(SpbuMachineEntity spbuMachineEntity) {
		final ViewData viewData = new ViewData();
		viewData.setMachineSerial(spbuMachineEntity.getMachineSerial());
		viewData.setSpbuId(spbuMachineEntity.getSpbuEntity().getId());
		viewData.setSpbuCode(spbuMachineEntity.getSpbuEntity().getCode());
		viewData.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
		viewData.setMachineIdentifier(spbuMachineEntity.getMachineIdentifier());
		return viewData;
	}

	private FormData getFormData(SpbuMachineEntity spbuMachineEntity) {
		final FormData formData = new FormData();
		formData.setMachineSerial(spbuMachineEntity.getMachineSerial());
		formData.setSpbuId(spbuMachineEntity.getSpbuEntity().getId());
		formData.setSpbuCode(spbuMachineEntity.getSpbuEntity().getCode());
		formData.setModelId(spbuMachineEntity.getMachineModelEntity().getModelId());
		formData.setMachineIdentifier(spbuMachineEntity.getMachineIdentifier());
		return formData;
	}

	public static class ViewData {

		private String machineSerial;
		private long spbuId;
		private String spbuCode;
		private String modelId;
		private String machineIdentifier;

		public ViewData() {
		}

		public String getMachineSerial() {
			return machineSerial;
		}

		public void setMachineSerial(String machineSerial) {
			this.machineSerial = machineSerial;
		}

		public long getSpbuId() {
			return spbuId;
		}

		public void setSpbuId(long spbuId) {
			this.spbuId = spbuId;
		}

		public String getSpbuCode() {
			return spbuCode;
		}

		public void setSpbuCode(String spbuCode) {
			this.spbuCode = spbuCode;
		}

		public String getModelId() {
			return modelId;
		}

		public void setModelId(String modelId) {
			this.modelId = modelId;
		}

		public String getMachineIdentifier() {
			return machineIdentifier;
		}

		public void setMachineIdentifier(String machineIdentifier) {
			this.machineIdentifier = machineIdentifier;
		}

		@Override
		public String toString() {
			return "ViewData{" + "machineSerial=" + machineSerial + ", spbuId=" + spbuId + ", spbuCode=" + spbuCode + ", modelId=" + modelId + ", machineIdentifier=" + machineIdentifier + '}';
		}
	}

	public static class FormData {

		private String machineSerial;
		private long spbuId;
		private String spbuCode;
		private String modelId;
		private String machineIdentifier;

		public FormData() {
		}

		public String getMachineSerial() {
			return machineSerial;
		}

		public void setMachineSerial(String machineSerial) {
			this.machineSerial = machineSerial;
		}

		public long getSpbuId() {
			return spbuId;
		}

		public void setSpbuId(long spbuId) {
			this.spbuId = spbuId;
		}

		public String getSpbuCode() {
			return spbuCode;
		}

		public void setSpbuCode(String spbuCode) {
			this.spbuCode = spbuCode;
		}

		public String getModelId() {
			return modelId;
		}

		public void setModelId(String modelId) {
			this.modelId = modelId;
		}

		public String getMachineIdentifier() {
			return machineIdentifier;
		}

		public void setMachineIdentifier(String machineIdentifier) {
			this.machineIdentifier = machineIdentifier;
		}

		@Override
		public String toString() {
			return "FormData{" + "machineSerial=" + machineSerial + ", spbuId=" + spbuId + ", spbuCode=" + spbuCode + ", modelId=" + modelId + ", machineIdentifier=" + machineIdentifier + '}';
		}
	}
}

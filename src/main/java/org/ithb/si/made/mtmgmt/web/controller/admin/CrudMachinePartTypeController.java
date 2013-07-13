/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.admin;

import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachinePartTypeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachinePartTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @author gde
 */
@Controller("adminCrudMachinePartTypeController")
@RequestMapping("/admin/crud/machine_part_type")
public class CrudMachinePartTypeController {

	private static final Logger LOG = LoggerFactory.getLogger(CrudMachinePartTypeController.class);
	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/machine_part_type";
	@Autowired
	private MachinePartTypeRepository machinePartTypeRepository;

	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("partId") String partId) {
		machinePartTypeRepository.delete(partId);
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
		final List<MachinePartTypeEntity> machinePartTypeEntities = machinePartTypeRepository.findAll();
		final List<ViewData> viewDatas = new LinkedList<>();

		for (final MachinePartTypeEntity machinePartTypeEntity : machinePartTypeEntities) {
			viewDatas.add(getViewData(machinePartTypeEntity));
		}

		model.addAttribute("viewDatas", viewDatas);
		return CONTROLLER_VIEW_ROOT + "/list";
	}

	@Transactional
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("partId") String partId) {
		final MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(partId);
		final ViewData viewData = getViewData(machinePartTypeEntity);
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
			MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(formData.getPartId());
			if (machinePartTypeEntity == null) {
				machinePartTypeEntity = new MachinePartTypeEntity(formData.getPartId());
				machinePartTypeEntity.setName(formData.getName());
				machinePartTypeEntity.setDefaultMttf(formData.getDefaultMttf());
				machinePartTypeEntity.setDefaultMttfThreshold(formData.getDefaultMttfThreshold());
				machinePartTypeEntity = machinePartTypeRepository.saveAndFlush(machinePartTypeEntity);
				model.addAttribute("formData", getFormData(machinePartTypeEntity));
			} else {
				bindingResult.rejectValue("partId", "common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Model model, @RequestParam("partId") String partId) {
		final MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(partId);
		if (machinePartTypeEntity != null) {
			model.addAttribute("formData", getFormData(machinePartTypeEntity));
			return CONTROLLER_VIEW_ROOT + "/update";
		} else {
			throw new RuntimeException("Unknown partId");
		}
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(formData.getPartId());
			if (machinePartTypeEntity != null) {
				machinePartTypeEntity.setName(formData.getName());
				machinePartTypeEntity.setDefaultMttf(formData.getDefaultMttf());
				machinePartTypeEntity.setDefaultMttfThreshold(formData.getDefaultMttfThreshold());
				machinePartTypeEntity = machinePartTypeRepository.saveAndFlush(machinePartTypeEntity);
				model.addAttribute("formData", getFormData(machinePartTypeEntity));
			} else {
				bindingResult.rejectValue("partId", "admin.crud.machinePartType.notExist");
			}
		}
		return CONTROLLER_VIEW_ROOT + "/update";
	}

	private ViewData getViewData(MachinePartTypeEntity machinePartTypeEntity) {
		final ViewData viewData = new ViewData();
		viewData.setPartId(machinePartTypeEntity.getPartId());
		viewData.setName(machinePartTypeEntity.getName());
		viewData.setDefaultMttf(machinePartTypeEntity.getDefaultMttf());
		viewData.setDefaultMttfThreshold(machinePartTypeEntity.getDefaultMttfThreshold());
		return viewData;
	}

	private FormData getFormData(MachinePartTypeEntity machinePartTypeEntity) {
		final FormData formData = new FormData();
		formData.setPartId(machinePartTypeEntity.getPartId());
		formData.setName(machinePartTypeEntity.getName());
		formData.setDefaultMttf(machinePartTypeEntity.getDefaultMttf());
		formData.setDefaultMttfThreshold(machinePartTypeEntity.getDefaultMttfThreshold());
		return formData;
	}

	public static class ViewData {

		private String partId;
		private String name;
		private double defaultMttf;
		private double defaultMttfThreshold;

		public ViewData() {
		}

		public String getPartId() {
			return partId;
		}

		public void setPartId(String partId) {
			this.partId = partId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getDefaultMttf() {
			return defaultMttf;
		}

		public void setDefaultMttf(double defaultMttf) {
			this.defaultMttf = defaultMttf;
		}

		public double getDefaultMttfThreshold() {
			return defaultMttfThreshold;
		}

		public void setDefaultMttfThreshold(double defaultMttfThreshold) {
			this.defaultMttfThreshold = defaultMttfThreshold;
		}

		@Override
		public String toString() {
			return "FormData{" + "partId=" + partId + ", name=" + name + ", defaultMttf=" + defaultMttf + ", defaultMttfThreshold=" + defaultMttfThreshold + '}';
		}
	}

	public static class FormData {

		@NotNull
		@Size(min = 1, max = 40)
		private String partId;
		@NotNull
		@Size(min = 1, max = 255)
		private String name;
		private double defaultMttf;
		private double defaultMttfThreshold;

		public FormData() {
		}

		public String getPartId() {
			return partId;
		}

		public void setPartId(String partId) {
			this.partId = partId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getDefaultMttf() {
			return defaultMttf;
		}

		public void setDefaultMttf(double defaultMttf) {
			this.defaultMttf = defaultMttf;
		}

		public double getDefaultMttfThreshold() {
			return defaultMttfThreshold;
		}

		public void setDefaultMttfThreshold(double defaultMttfThreshold) {
			this.defaultMttfThreshold = defaultMttfThreshold;
		}

		@Override
		public String toString() {
			return "FormData{" + "partId=" + partId + ", name=" + name + ", defaultMttf=" + defaultMttf + ", defaultMttfThreshold=" + defaultMttfThreshold + '}';
		}
	}
}

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
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelTotalizerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@Controller("adminCrudMachineModelController")
@RequestMapping("/admin/crud/machine_model")
public class CrudMachineModelController {

	private static final Logger LOG = LoggerFactory.getLogger(CrudMachineModelController.class);
	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/machine_model";
	@Autowired
	private MachineModelRepository machineModelRepository;

	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("modelId") String modelId) {
		machineModelRepository.delete(modelId);
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
		final List<MachineModelEntity> machineModelEntities = machineModelRepository.findAll();
		final List<ViewData> viewDatas = new LinkedList<>();

		for (final MachineModelEntity machineModelEntity : machineModelEntities) {
			viewDatas.add(getViewData(machineModelEntity));
		}

		model.addAttribute("viewDatas", viewDatas);
		return CONTROLLER_VIEW_ROOT + "/list";
	}

	@Transactional
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("modelId") String modelId) {
		final MachineModelEntity machineModelEntity = machineModelRepository.findOne(modelId);
		final ViewData viewData = getViewData(machineModelEntity);
		model.addAttribute("viewData", viewData);
		return CONTROLLER_VIEW_ROOT + "/view";
	}

	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		final FormData formData = new FormData();
		formData.getTotalizerIds().add("Totalizer_1");

		model.addAttribute("formData", formData);
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("formData") FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			MachineModelEntity machineModelEntity = machineModelRepository.findOne(formData.getModelId());
			if (machineModelEntity == null) {
				machineModelEntity = new MachineModelEntity(formData.getModelId());
				machineModelEntity.setName(formData.getName());
				machineModelEntity = machineModelRepository.saveAndFlush(machineModelEntity);

				for (final String totalizerId : formData.getTotalizerIds()) {
					final MachineModelTotalizerEntityPK machineModelTotalizerEntityPK = new MachineModelTotalizerEntityPK();
					machineModelTotalizerEntityPK.setModelId(machineModelEntity.getModelId());
					machineModelTotalizerEntityPK.setTotalizerId(totalizerId);
					final MachineModelTotalizerEntity machineModelTotalizerEntity = new MachineModelTotalizerEntity(machineModelTotalizerEntityPK);
					machineModelEntity.getMachineModelTotalizerEntityList().add(machineModelTotalizerEntity);
				}

				for (int i = 0; i < formData.getPartIds().size(); i++) {
					final String partId = formData.getPartIds().get(i);
					final String partIdentifier = formData.getPartIdentifiers().get(i);
					final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
					machineModelPartEntityPK.setModelId(machineModelEntity.getModelId());
					machineModelPartEntityPK.setPartId(partId);
					machineModelPartEntityPK.setMachineModelPartIdentifier(partIdentifier);
					machineModelEntity.getMachineModelPartEntityList().add(new MachineModelPartEntity(machineModelPartEntityPK));
				}
				machineModelEntity = machineModelRepository.saveAndFlush(machineModelEntity);
			} else {
				bindingResult.rejectValue("modelId", "common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Model model, @RequestParam("modelId") String modelId) {
		final MachineModelEntity machineModelEntity = machineModelRepository.findOne(modelId);
		if (machineModelEntity != null) {
			model.addAttribute("formData", getFormData(machineModelEntity));
			return CONTROLLER_VIEW_ROOT + "/update";
		} else {
			throw new RuntimeException("Unknown modelId");
		}
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			MachineModelEntity machineModelEntity = machineModelRepository.findOne(formData.getModelId());
			if (machineModelEntity != null) {
				machineModelEntity.setName(formData.getName());

				machineModelEntity.getMachineModelTotalizerEntityList().clear();
				for (final String totalizerId : formData.getTotalizerIds()) {
					final MachineModelTotalizerEntityPK machineModelTotalizerEntityPK = new MachineModelTotalizerEntityPK();
					machineModelTotalizerEntityPK.setModelId(machineModelEntity.getModelId());
					machineModelTotalizerEntityPK.setTotalizerId(totalizerId);
					final MachineModelTotalizerEntity machineModelTotalizerEntity = new MachineModelTotalizerEntity(machineModelTotalizerEntityPK);
					machineModelEntity.getMachineModelTotalizerEntityList().add(machineModelTotalizerEntity);
				}

				machineModelEntity.getMachineModelPartEntityList().clear();
				for (int i = 0; i < formData.getPartIds().size(); i++) {
					final String partId = formData.getPartIds().get(i);
					final String partIdentifier = formData.getPartIdentifiers().get(i);
					final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
					machineModelPartEntityPK.setModelId(machineModelEntity.getModelId());
					machineModelPartEntityPK.setPartId(partId);
					machineModelPartEntityPK.setMachineModelPartIdentifier(partIdentifier);
					machineModelEntity.getMachineModelPartEntityList().add(new MachineModelPartEntity(machineModelPartEntityPK));
				}
				machineModelEntity = machineModelRepository.saveAndFlush(machineModelEntity);
			} else {
				bindingResult.rejectValue("modelId", "admin.crud.machineModel.notExist");
			}
		}
		return CONTROLLER_VIEW_ROOT + "/update";
	}

	private ViewData getViewData(MachineModelEntity machineModelEntity) {
		final ViewData viewData = new ViewData();
		viewData.setModelId(machineModelEntity.getModelId());
		viewData.setName(machineModelEntity.getName());
		viewData.setTotalizerIds(getTotalizerIds(machineModelEntity));

		for (final MachineModelPartEntity machineModelPartEntity : machineModelEntity.getMachineModelPartEntityList()) {
			viewData.getPartIds().add(machineModelPartEntity.getMachineModelPartEntityPK().getPartId());
			viewData.getPartIdentifiers().add(machineModelPartEntity.getMachineModelPartEntityPK().getMachineModelPartIdentifier());
		}

		return viewData;
	}

	private List<String> getTotalizerIds(MachineModelEntity machineModelEntity) {
		final List<String> totalizerIds = new LinkedList<>();
		for (final MachineModelTotalizerEntity machineModelTotalizerEntity : machineModelEntity.getMachineModelTotalizerEntityList()) {
			totalizerIds.add(machineModelTotalizerEntity.getMachineModelTotalizerEntityPK().getTotalizerId());
		}
		return totalizerIds;
	}

	private FormData getFormData(MachineModelEntity machineModelEntity) {
		final FormData formData = new FormData();
		formData.setModelId(machineModelEntity.getModelId());
		formData.setName(machineModelEntity.getName());
		formData.setTotalizerIds(getTotalizerIds(machineModelEntity));

		for (final MachineModelPartEntity machineModelPartEntity : machineModelEntity.getMachineModelPartEntityList()) {
			formData.getPartIds().add(machineModelPartEntity.getMachineModelPartEntityPK().getPartId());
			formData.getPartIdentifiers().add(machineModelPartEntity.getMachineModelPartEntityPK().getMachineModelPartIdentifier());
		}

		return formData;
	}

	public static class ViewData {

		private String modelId;
		private String name;
		private List<String> totalizerIds = new LinkedList<>();
		private List<String> partIds = new LinkedList<>();
		private List<String> partIdentifiers = new LinkedList<>();

		public ViewData() {
		}

		public String getModelId() {
			return modelId;
		}

		public void setModelId(String modelId) {
			this.modelId = modelId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<String> getTotalizerIds() {
			return totalizerIds;
		}

		public void setTotalizerIds(List<String> totalizerIds) {
			this.totalizerIds = totalizerIds;
		}

		public List<String> getPartIds() {
			return partIds;
		}

		public void setPartIds(List<String> partIds) {
			this.partIds = partIds;
		}

		public List<String> getPartIdentifiers() {
			return partIdentifiers;
		}

		public void setPartIdentifiers(List<String> partIdentifiers) {
			this.partIdentifiers = partIdentifiers;
		}

		@Override
		public String toString() {
			return "ViewData{" + "modelId=" + modelId + ", name=" + name + ", totalizerIds=" + totalizerIds + ", partIds=" + partIds + ", partIdentifiers=" + partIdentifiers + '}';
		}
	}

	public static class FormData {

		@NotNull
		@Size(min = 1, max = 40)
		private String modelId;
		@NotNull
		@Size(min = 1, max = 255)
		private String name;
		@NotNull
		@Size(min = 1)
		private List<String> totalizerIds = new LinkedList<>();
		@NotNull
		@Size(min = 1)
		private List<String> partIds = new LinkedList<>();
		@NotNull
		@Size(min = 1)
		private List<String> partIdentifiers = new LinkedList<>();

		public FormData() {
		}

		public String getModelId() {
			return modelId;
		}

		public void setModelId(String modelId) {
			this.modelId = modelId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<String> getTotalizerIds() {
			return totalizerIds;
		}

		public void setTotalizerIds(List<String> totalizerIds) {
			this.totalizerIds = totalizerIds;
		}

		public List<String> getPartIds() {
			return partIds;
		}

		public void setPartIds(List<String> partIds) {
			this.partIds = partIds;
		}

		public List<String> getPartIdentifiers() {
			return partIdentifiers;
		}

		public void setPartIdentifiers(List<String> partIdentifiers) {
			this.partIdentifiers = partIdentifiers;
		}

		@Override
		public String toString() {
			return "FormData{" + "modelId=" + modelId + ", name=" + name + ", totalizerIds=" + totalizerIds + ", partIds=" + partIds + ", partIdentifiers=" + partIdentifiers + '}';
		}
	}
}

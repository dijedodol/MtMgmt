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
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelTotalizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author gde
 */
@Controller("adminCrudMachineModelController")
@RequestMapping("/admin/crud/machine_model")
public class CrudMachineModelController {

	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/machine_model";
	@Autowired
	private MachineModelRepository machineModelRepository;
	@Autowired
	private MachineModelTotalizerRepository machineModelTotalizerRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		final List<MachineModelEntity> machineModelEntities = machineModelRepository.findAll();
		final List<ViewData> viewDatas = new LinkedList<>();

		for (final MachineModelEntity machineModelEntity : machineModelEntities) {
			viewDatas.add(getViewData(machineModelEntity));
		}

		model.addAttribute("viewData", viewDatas);
		return "admin/crud/machine_model/list";
	}

	@Transactional
	@RequestMapping(value = "view/{modelId}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("modelId") String modelId) {
		final MachineModelEntity machineModelEntity = machineModelRepository.findOne(modelId);
		final ViewData viewData = getViewData(machineModelEntity);
		model.addAttribute("viewData", viewData);
		return CONTROLLER_VIEW_ROOT + "/view";
	}

	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@Valid FormData formData, BindingResult bindingResult, Model model) {
		model.addAttribute("formData", formData);

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
				machineModelTotalizerRepository.save(machineModelTotalizerEntity);
			}
			machineModelTotalizerRepository.flush();
		} else {
			bindingResult.rejectValue("modelId", "admin.crud.machineModel.alreadyExist");
		}

		return CONTROLLER_VIEW_ROOT + "/add";
	}

	private ViewData getViewData(MachineModelEntity machineModelEntity) {
		final ViewData viewData = new ViewData();
		viewData.setModelId(machineModelEntity.getModelId());
		viewData.setName(machineModelEntity.getName());
		viewData.setTotalizerIds(getTotalizerIds(machineModelEntity));
		return viewData;
	}

	private List<String> getTotalizerIds(MachineModelEntity machineModelEntity) {
		final List<String> totalizerIds = new LinkedList<>();
		for (final MachineModelTotalizerEntity machineModelTotalizerEntity : machineModelEntity.getMachineModelTotalizerEntityList()) {
			totalizerIds.add(machineModelTotalizerEntity.getMachineModelTotalizerEntityPK().getTotalizerId());
		}
		return totalizerIds;
	}

	public static class ViewData {

		private String modelId;
		private String name;
		private List<String> totalizerIds;

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

		@Override
		public String toString() {
			return "ViewData{" + "modelId=" + modelId + ", name=" + name + ", totalizerIds=" + totalizerIds + '}';
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
		private List<String> totalizerIds;

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

		@Override
		public String toString() {
			return "FormData{" + "modelId=" + modelId + ", name=" + name + ", totalizerIds=" + totalizerIds + '}';
		}
	}
}

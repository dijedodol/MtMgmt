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
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelPartRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelTotalizerRepository;
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
 * @author Uyeee
 */
@Controller("adminCrudMachineModelPartTotalizerController")
@RequestMapping("/admin/crud/machine_model_part_totalizer")
public class CrudMachineModelPartTotalizer {

	private static final Logger LOG = LoggerFactory.getLogger(CrudMachineModelPartTotalizer.class);
	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/machine_model_part_totalizer";
	@Autowired
	private MachineModelRepository machineModelRepository;
	@Autowired
	private MachineModelTotalizerRepository machineModelTotalizerRepository;
	@Autowired
	private MachineModelPartRepository machineModelPartRepository;

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
			for (final MachineModelPartEntity machineModelPartEntity : machineModelEntity.getMachineModelPartEntityList()) {
				viewDatas.add(getViewData(machineModelPartEntity));
			}
		}

		model.addAttribute("viewDatas", viewDatas);
		return CONTROLLER_VIEW_ROOT + "/list";
	}

	@Transactional
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("modelId") String modelId, @RequestParam("partId") String partId, @RequestParam("machineModelPartIdentifier") String machineModelPartIdentifier) {
		final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
		machineModelPartEntityPK.setModelId(modelId);
		machineModelPartEntityPK.setPartId(partId);
		machineModelPartEntityPK.setMachineModelPartIdentifier(machineModelPartIdentifier);
		final MachineModelPartEntity machineModelPartEntity = machineModelPartRepository.findOne(machineModelPartEntityPK);
		final ViewData viewData = getViewData(machineModelPartEntity);
		model.addAttribute("viewData", viewData);
		return CONTROLLER_VIEW_ROOT + "/view";
	}

	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		final FormData formData = new FormData();
		model.addAttribute("totalizerIdOptions", new LinkedList<>());
		model.addAttribute("formData", formData);
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("formData") FormData formData, BindingResult bindingResult, Model model) {
		final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
		machineModelPartEntityPK.setModelId(formData.getModelId());
		machineModelPartEntityPK.setPartId(formData.getPartId());
		machineModelPartEntityPK.setMachineModelPartIdentifier(formData.getMachineModelPartIdentifier());
		MachineModelPartEntity machineModelPartEntity = machineModelPartRepository.findOne(machineModelPartEntityPK);

		if (machineModelPartEntity != null) {
			for (final String totalizerId : formData.getTotalizerIds()) {
				final MachineModelTotalizerEntityPK machineModelTotalizerEntityPK = new MachineModelTotalizerEntityPK();
				machineModelTotalizerEntityPK.setModelId(formData.getModelId());
				machineModelTotalizerEntityPK.setTotalizerId(totalizerId);
				final MachineModelTotalizerEntity machineModelTotalizerEntity = machineModelTotalizerRepository.findOne(machineModelTotalizerEntityPK);
				if (machineModelTotalizerEntity != null) {
					machineModelPartEntity.getMachineModelTotalizerEntityList().add(machineModelTotalizerEntity);
					machineModelTotalizerEntity.getMachineModelPartEntityList().add(machineModelPartEntity);
				}
			}
			model.addAttribute("totalizerIdOptions", getTotalizerIds(machineModelPartEntity.getMachineModelEntity()));
			model.addAttribute("formData", getFormData(machineModelPartEntity));
		} else {
			model.addAttribute("totalizerIdOptions", new LinkedList<>());
		}
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Model model, @RequestParam("modelId") String modelId, @RequestParam("partId") String partId, @RequestParam("machineModelPartIdentifier") String machineModelPartIdentifier) {
		final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
		machineModelPartEntityPK.setModelId(modelId);
		machineModelPartEntityPK.setPartId(partId);
		machineModelPartEntityPK.setMachineModelPartIdentifier(machineModelPartIdentifier);
		final MachineModelPartEntity machineModelPartEntity = machineModelPartRepository.findOne(machineModelPartEntityPK);
		if (machineModelPartEntity != null) {
			model.addAttribute("totalizerIdOptions", getTotalizerIds(machineModelPartEntity.getMachineModelEntity()));
			model.addAttribute("formData", getFormData(machineModelPartEntity));
			return CONTROLLER_VIEW_ROOT + "/update";
		} else {
			throw new RuntimeException("Unknown machineModelPartEntityPK");
		}
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("formData") FormData formData, BindingResult bindingResult, Model model) {
		final MachineModelPartEntityPK machineModelPartEntityPK = new MachineModelPartEntityPK();
		machineModelPartEntityPK.setModelId(formData.getModelId());
		machineModelPartEntityPK.setPartId(formData.getPartId());
		machineModelPartEntityPK.setMachineModelPartIdentifier(formData.getMachineModelPartIdentifier());
		MachineModelPartEntity machineModelPartEntity = machineModelPartRepository.findOne(machineModelPartEntityPK);

		if (machineModelPartEntity != null) {
			machineModelPartEntity.getMachineModelTotalizerEntityList().clear();
			for (final String totalizerId : formData.getTotalizerIds()) {
				final MachineModelTotalizerEntityPK machineModelTotalizerEntityPK = new MachineModelTotalizerEntityPK();
				machineModelTotalizerEntityPK.setModelId(formData.getModelId());
				machineModelTotalizerEntityPK.setTotalizerId(totalizerId);
				final MachineModelTotalizerEntity machineModelTotalizerEntity = machineModelTotalizerRepository.findOne(machineModelTotalizerEntityPK);
				if (machineModelTotalizerEntity != null) {
					machineModelTotalizerEntity.getMachineModelPartEntityList().clear();
					machineModelPartEntity.getMachineModelTotalizerEntityList().add(machineModelTotalizerEntity);
					machineModelTotalizerEntity.getMachineModelPartEntityList().add(machineModelPartEntity);
				}
			}
			model.addAttribute("totalizerIdOptions", getTotalizerIds(machineModelPartEntity.getMachineModelEntity()));
			model.addAttribute("formData", getFormData(machineModelPartEntity));
		} else {
			model.addAttribute("totalizerIdOptions", new LinkedList<>());
		}
		return CONTROLLER_VIEW_ROOT + "/update";
	}

	private FormData getFormData(MachineModelPartEntity machineModelPartEntity) {
		final FormData formData = new FormData();

		formData.setModelId(machineModelPartEntity.getMachineModelPartEntityPK().getModelId());
		formData.setPartId(machineModelPartEntity.getMachineModelPartEntityPK().getPartId());
		formData.setMachineModelPartIdentifier(machineModelPartEntity.getMachineModelPartEntityPK().getMachineModelPartIdentifier());
		formData.setTotalizerIds(getTotalizerIds(machineModelPartEntity));

		return formData;
	}

	private ViewData getViewData(MachineModelPartEntity machineModelPartEntity) {
		final ViewData viewData = new ViewData();

		viewData.setModelId(machineModelPartEntity.getMachineModelPartEntityPK().getModelId());
		viewData.setPartId(machineModelPartEntity.getMachineModelPartEntityPK().getPartId());
		viewData.setMachineModelPartIdentifier(machineModelPartEntity.getMachineModelPartEntityPK().getMachineModelPartIdentifier());
		viewData.setTotalizerIds(getTotalizerIds(machineModelPartEntity));

		return viewData;
	}

	private List<String> getTotalizerIds(MachineModelPartEntity machineModelPartEntity) {
		final List<String> ret = getTotalizerIds(machineModelPartEntity.getMachineModelTotalizerEntityList());
		return ret;
	}

	private List<String> getTotalizerIds(MachineModelEntity machineModelEntity) {
		final List<String> ret = getTotalizerIds(machineModelEntity.getMachineModelTotalizerEntityList());
		return ret;
	}

	private List<String> getTotalizerIds(List<MachineModelTotalizerEntity> machineModelTotalizers) {
		final List<String> ret = new LinkedList<>();
		for (final MachineModelTotalizerEntity machineModelTotalizerEntity : machineModelTotalizers) {
			ret.add(machineModelTotalizerEntity.getMachineModelTotalizerEntityPK().getTotalizerId());
		}
		return ret;
	}

	public static class ViewData {

		private String modelId;
		private String partId;
		private String machineModelPartIdentifier;
		private List<String> totalizerIds = new LinkedList<>();

		public ViewData() {
		}

		@Override
		public String toString() {
			return "ViewData{" + "modelId=" + modelId + ", partId=" + partId + ", machineModelPartIdentifier=" + machineModelPartIdentifier + ", totalizerId=" + totalizerIds + '}';
		}

		public String getModelId() {
			return modelId;
		}

		public void setModelId(String modelId) {
			this.modelId = modelId;
		}

		public String getPartId() {
			return partId;
		}

		public void setPartId(String partId) {
			this.partId = partId;
		}

		public String getMachineModelPartIdentifier() {
			return machineModelPartIdentifier;
		}

		public void setMachineModelPartIdentifier(String machineModelPartIdentifier) {
			this.machineModelPartIdentifier = machineModelPartIdentifier;
		}

		public List<String> getTotalizerIds() {
			return totalizerIds;
		}

		public void setTotalizerIds(List<String> totalizerIds) {
			this.totalizerIds = totalizerIds;
		}
	}

	public static class FormData {

		@NotNull
		@Size(min = 1, max = 40)
		private String modelId;
		@NotNull
		@Size(min = 1, max = 40)
		private String partId;
		@NotNull
		@Size(min = 1, max = 40)
		private String machineModelPartIdentifier;
		@NotNull
		@Size(min = 1, max = 40)
		private List<String> totalizerIds = new LinkedList<>();

		public FormData() {
		}

		@Override
		public String toString() {
			return "FormData{" + "modelId=" + modelId + ", partId=" + partId + ", machineModelPartIdentifier=" + machineModelPartIdentifier + ", totalizerIds=" + totalizerIds + '}';
		}

		public String getModelId() {
			return modelId;
		}

		public void setModelId(String modelId) {
			this.modelId = modelId;
		}

		public String getPartId() {
			return partId;
		}

		public void setPartId(String partId) {
			this.partId = partId;
		}

		public String getMachineModelPartIdentifier() {
			return machineModelPartIdentifier;
		}

		public void setMachineModelPartIdentifier(String machineModelPartIdentifier) {
			this.machineModelPartIdentifier = machineModelPartIdentifier;
		}

		public List<String> getTotalizerIds() {
			return totalizerIds;
		}

		public void setTotalizerIds(List<String> totalizerIds) {
			this.totalizerIds = totalizerIds;
		}
	}
}

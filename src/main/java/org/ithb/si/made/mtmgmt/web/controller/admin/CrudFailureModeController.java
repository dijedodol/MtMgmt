/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.admin;

import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachinePartTypeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachinePartTypeRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.PartFailureModeRepository;
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
@Controller("adminCrudFailureModeController")
@RequestMapping("/admin/crud/failure_mode")
public class CrudFailureModeController {

	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/failure_mode";
	@Autowired
	private PartFailureModeRepository partFailureModeRepository;
	@Autowired
	private MachinePartTypeRepository machinePartTypeRepository;

	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("partId") String partId, @RequestParam("code") String code) {
		final PartFailureModeEntityPK partFailureModeEntityPK = new PartFailureModeEntityPK();
		partFailureModeEntityPK.setFailureModeCode(code);
		partFailureModeEntityPK.setPartId(partId);
		partFailureModeRepository.delete(partFailureModeEntityPK);
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
		final List<PartFailureModeEntity> partFailureModeEntities = partFailureModeRepository.findAll();
		final List<ViewData> viewDatas = new LinkedList<>();

		for (final PartFailureModeEntity partFailureModeEntity : partFailureModeEntities) {
			viewDatas.add(getViewData(partFailureModeEntity));
		}

		model.addAttribute("viewDatas", viewDatas);
		return CONTROLLER_VIEW_ROOT + "/list";
	}

	@Transactional
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("partId") String partId, @RequestParam("code") String code) {
		final PartFailureModeEntityPK partFailureModeEntityPK = new PartFailureModeEntityPK();
		partFailureModeEntityPK.setFailureModeCode(code);
		partFailureModeEntityPK.setPartId(partId);
		final PartFailureModeEntity partFailureModeEntity = partFailureModeRepository.findOne(partFailureModeEntityPK);
		final ViewData viewData = getViewData(partFailureModeEntity);
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
			final PartFailureModeEntityPK partFailureModeEntityPK = new PartFailureModeEntityPK();
			partFailureModeEntityPK.setFailureModeCode(formData.getCode());
			partFailureModeEntityPK.setPartId(formData.getPartId());
			PartFailureModeEntity partFailureModeEntity = partFailureModeRepository.findOne(partFailureModeEntityPK);

			if (partFailureModeEntity == null) {
				final MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(formData.getPartId());
				if (machinePartTypeEntity != null) {
					partFailureModeEntity = new PartFailureModeEntity(partFailureModeEntityPK);
					partFailureModeEntity.setName(formData.getName());
					partFailureModeEntity.setDescription(formData.getDescription());
					partFailureModeEntity = partFailureModeRepository.saveAndFlush(partFailureModeEntity);
					model.addAttribute("formData", getFormData(partFailureModeEntity));
				} else {
					bindingResult.reject("common.error.invalidData");
				}
			} else {
					bindingResult.reject("common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/add";
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Model model, @RequestParam("partId") String partId, @RequestParam("code") String code) {
		final PartFailureModeEntityPK partFailureModeEntityPK = new PartFailureModeEntityPK();
		partFailureModeEntityPK.setFailureModeCode(code);
		partFailureModeEntityPK.setPartId(partId);
		final PartFailureModeEntity partFailureModeEntity = partFailureModeRepository.findOne(partFailureModeEntityPK);
		if (partFailureModeEntity != null) {
			model.addAttribute("formData", getFormData(partFailureModeEntity));
			return CONTROLLER_VIEW_ROOT + "/update";
		} else {
			throw new RuntimeException("Unknown partFailureModeEntityPK");
		}
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			final PartFailureModeEntityPK partFailureModeEntityPK = new PartFailureModeEntityPK();
			partFailureModeEntityPK.setFailureModeCode(formData.getCode());
			partFailureModeEntityPK.setPartId(formData.getPartId());
			PartFailureModeEntity partFailureModeEntity = partFailureModeRepository.findOne(partFailureModeEntityPK);

			if (partFailureModeEntity != null) {
				final MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(formData.getPartId());
				if (machinePartTypeEntity != null) {
					partFailureModeEntity = new PartFailureModeEntity(partFailureModeEntityPK);
					partFailureModeEntity.setName(formData.getName());
					partFailureModeEntity.setDescription(formData.getDescription());
					partFailureModeEntity = partFailureModeRepository.saveAndFlush(partFailureModeEntity);
					model.addAttribute("formData", getFormData(partFailureModeEntity));
				} else {
					bindingResult.reject("common.error.invalidData");
				}
			} else {
				bindingResult.reject("common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/update";
	}

	private ViewData getViewData(PartFailureModeEntity partFailureModeEntity) {
		final ViewData viewData = new ViewData();
		viewData.setPartId(partFailureModeEntity.getPartFailureModeEntityPK().getPartId());
		viewData.setCode(partFailureModeEntity.getPartFailureModeEntityPK().getFailureModeCode());
		viewData.setName(partFailureModeEntity.getName());
		viewData.setDescription(partFailureModeEntity.getDescription());
		return viewData;
	}

	private FormData getFormData(PartFailureModeEntity partFailureModeEntity) {
		final FormData formData = new FormData();
		formData.setPartId(partFailureModeEntity.getPartFailureModeEntityPK().getPartId());
		formData.setCode(partFailureModeEntity.getPartFailureModeEntityPK().getFailureModeCode());
		formData.setName(partFailureModeEntity.getName());
		formData.setDescription(partFailureModeEntity.getDescription());
		return formData;
	}

	public static class ViewData {

		private String partId;
		private String code;
		private String name;
		private String description;

		public ViewData() {
		}

		public String getPartId() {
			return partId;
		}

		public void setPartId(String partId) {
			this.partId = partId;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "ViewData{" + "partId=" + partId + ", code=" + code + ", name=" + name + ", description=" + description + '}';
		}
	}

	public static class FormData {

		private String partId;
		private String code;
		private String name;
		private String description;

		public FormData() {
		}

		public String getPartId() {
			return partId;
		}

		public void setPartId(String partId) {
			this.partId = partId;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "FormData{" + "partId=" + partId + ", code=" + code + ", name=" + name + ", description=" + description + '}';
		}
	}
}

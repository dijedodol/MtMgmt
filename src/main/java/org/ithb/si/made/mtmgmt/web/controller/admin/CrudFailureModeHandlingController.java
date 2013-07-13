/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.admin;

import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachinePartTypeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.repository.FailureModeHandlingRepository;
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
@Controller("adminCrudFailureModeHandlingController")
@RequestMapping("/admin/crud/failure_mode_handling")
public class CrudFailureModeHandlingController {

	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/failure_mode_handling";
	@Autowired
	private PartFailureModeRepository partFailureModeRepository;
	@Autowired
	private FailureModeHandlingRepository failureModeHandlingRepository;
	@Autowired
	private MachinePartTypeRepository machinePartTypeRepository;

	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("partId") String partId, @RequestParam("code") String code, @RequestParam("handlingCode") String handlingCode) {
		final FailureModeHandlingEntityPK failureModeHandlingEntityPK = new FailureModeHandlingEntityPK();
		failureModeHandlingEntityPK.setFailureModeHandlingCode(handlingCode);
		failureModeHandlingEntityPK.setFailureModeCode(code);
		failureModeHandlingEntityPK.setPartId(partId);
		failureModeHandlingRepository.delete(failureModeHandlingEntityPK);
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
		final List<FailureModeHandlingEntity> failureModeHandlingEntitys = failureModeHandlingRepository.findAll();
		final List<ViewData> viewDatas = new LinkedList<>();

		for (final FailureModeHandlingEntity failureModeHandlingEntity : failureModeHandlingEntitys) {
			viewDatas.add(getViewData(failureModeHandlingEntity));
		}

		model.addAttribute("viewDatas", viewDatas);
		return CONTROLLER_VIEW_ROOT + "/list";
	}

	@Transactional
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model, @RequestParam("partId") String partId, @RequestParam("code") String code, @RequestParam("handlingCode") String handlingCode) {
		final FailureModeHandlingEntityPK failureModeHandlingEntityPK = new FailureModeHandlingEntityPK();
		failureModeHandlingEntityPK.setFailureModeHandlingCode(handlingCode);
		failureModeHandlingEntityPK.setFailureModeCode(code);
		failureModeHandlingEntityPK.setPartId(partId);
		final FailureModeHandlingEntity failureModeHandlingEntity = failureModeHandlingRepository.findOne(failureModeHandlingEntityPK);
		final ViewData viewData = getViewData(failureModeHandlingEntity);
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
			final FailureModeHandlingEntityPK failureModeHandlingEntityPK = new FailureModeHandlingEntityPK();
			failureModeHandlingEntityPK.setFailureModeHandlingCode(formData.getHandlingCode());
			failureModeHandlingEntityPK.setFailureModeCode(formData.getCode());
			failureModeHandlingEntityPK.setPartId(formData.getPartId());
			FailureModeHandlingEntity failureModeHandlingEntity = failureModeHandlingRepository.findOne(failureModeHandlingEntityPK);

			if (failureModeHandlingEntity == null) {
				final PartFailureModeEntityPK partFailureModeEntityPK = new PartFailureModeEntityPK();
				partFailureModeEntityPK.setFailureModeCode(formData.getCode());
				partFailureModeEntityPK.setPartId(formData.getPartId());
				final PartFailureModeEntity partFailureModeEntity = partFailureModeRepository.findOne(partFailureModeEntityPK);
				final MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(formData.getPartId());
				if (partFailureModeEntity != null && machinePartTypeEntity != null) {
					failureModeHandlingEntity = new FailureModeHandlingEntity(failureModeHandlingEntityPK);
					failureModeHandlingEntity.setName(formData.getName());
					failureModeHandlingEntity.setDescription(formData.getDescription());
					failureModeHandlingEntity.setPartFailureModeEntity(partFailureModeEntity);
					failureModeHandlingEntity = failureModeHandlingRepository.saveAndFlush(failureModeHandlingEntity);
					model.addAttribute("formData", getFormData(failureModeHandlingEntity));
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
	public String update(Model model, @RequestParam("partId") String partId, @RequestParam("code") String code, @RequestParam("handlingCode") String handlingCode) {
		final FailureModeHandlingEntityPK failureModeHandlingEntityPK = new FailureModeHandlingEntityPK();
		failureModeHandlingEntityPK.setFailureModeHandlingCode(handlingCode);
		failureModeHandlingEntityPK.setFailureModeCode(code);
		failureModeHandlingEntityPK.setPartId(partId);
		final FailureModeHandlingEntity failureModeHandlingEntity = failureModeHandlingRepository.findOne(failureModeHandlingEntityPK);
		if (failureModeHandlingEntity != null) {
			model.addAttribute("formData", getFormData(failureModeHandlingEntity));
			return CONTROLLER_VIEW_ROOT + "/update";
		} else {
			throw new RuntimeException("Unknown partFailureModeEntityPK");
		}
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			final FailureModeHandlingEntityPK failureModeHandlingEntityPK = new FailureModeHandlingEntityPK();
			failureModeHandlingEntityPK.setFailureModeHandlingCode(formData.getHandlingCode());
			failureModeHandlingEntityPK.setFailureModeCode(formData.getCode());
			failureModeHandlingEntityPK.setPartId(formData.getPartId());
			FailureModeHandlingEntity failureModeHandlingEntity = failureModeHandlingRepository.findOne(failureModeHandlingEntityPK);

			if (failureModeHandlingEntity != null) {
				final PartFailureModeEntityPK partFailureModeEntityPK = new PartFailureModeEntityPK();
				partFailureModeEntityPK.setFailureModeCode(formData.getCode());
				partFailureModeEntityPK.setPartId(formData.getPartId());
				final PartFailureModeEntity partFailureModeEntity = partFailureModeRepository.findOne(partFailureModeEntityPK);
				final MachinePartTypeEntity machinePartTypeEntity = machinePartTypeRepository.findOne(formData.getPartId());
				if (partFailureModeEntity != null && machinePartTypeEntity != null) {
					failureModeHandlingEntity = new FailureModeHandlingEntity(failureModeHandlingEntityPK);
					failureModeHandlingEntity.setName(formData.getName());
					failureModeHandlingEntity.setDescription(formData.getDescription());
					failureModeHandlingEntity.setPartFailureModeEntity(partFailureModeEntity);
					failureModeHandlingEntity = failureModeHandlingRepository.saveAndFlush(failureModeHandlingEntity);
					model.addAttribute("formData", getFormData(failureModeHandlingEntity));
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

	private ViewData getViewData(FailureModeHandlingEntity failureModeHandlingEntity) {
		final ViewData viewData = new ViewData();
		viewData.setPartId(failureModeHandlingEntity.getFailureModeHandlingEntityPK().getPartId());
		viewData.setCode(failureModeHandlingEntity.getFailureModeHandlingEntityPK().getFailureModeCode());
		viewData.setHandlingCode(failureModeHandlingEntity.getFailureModeHandlingEntityPK().getFailureModeHandlingCode());
		viewData.setName(failureModeHandlingEntity.getName());
		viewData.setDescription(failureModeHandlingEntity.getDescription());
		return viewData;
	}

	private FormData getFormData(FailureModeHandlingEntity failureModeHandlingEntity) {
		final FormData formData = new FormData();
		formData.setPartId(failureModeHandlingEntity.getFailureModeHandlingEntityPK().getPartId());
		formData.setCode(failureModeHandlingEntity.getFailureModeHandlingEntityPK().getFailureModeCode());
		formData.setHandlingCode(failureModeHandlingEntity.getFailureModeHandlingEntityPK().getFailureModeHandlingCode());
		formData.setName(failureModeHandlingEntity.getName());
		formData.setDescription(failureModeHandlingEntity.getDescription());
		return formData;
	}

	public static class ViewData {

		private String partId;
		private String code;
		private String handlingCode;
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

		public String getHandlingCode() {
			return handlingCode;
		}

		public void setHandlingCode(String handlingCode) {
			this.handlingCode = handlingCode;
		}

		@Override
		public String toString() {
			return "ViewData{" + "partId=" + partId + ", code=" + code + ", handlingCode=" + handlingCode + ", name=" + name + ", description=" + description + '}';
		}
	}

	public static class FormData {

		private String partId;
		private String code;
		private String handlingCode;
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

		public String getHandlingCode() {
			return handlingCode;
		}

		public void setHandlingCode(String handlingCode) {
			this.handlingCode = handlingCode;
		}

		@Override
		public String toString() {
			return "FormData{" + "partId=" + partId + ", code=" + code + ", handlingCode=" + handlingCode + ", name=" + name + ", description=" + description + '}';
		}
	}
}

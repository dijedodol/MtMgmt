/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.admin;

import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachinePartMttfEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachinePartMttfEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachinePartMttfRepository;
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
@Controller("adminCrudSpbuMachinePartMttfController")
@RequestMapping("/admin/crud/spbu_machine_part_mttf")
public class CrudSpbuMachinePartMttfController {

	private static final String CONTROLLER_VIEW_ROOT = "admin/crud/spbu_machine_part_mttf";
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpbuMachineRepository spbuMachineRepository;
	@Autowired
	private MachineModelRepository machineModelRepository;
	@Autowired
	private SpbuMachinePartMttfRepository spbuMachinePartMttfRepository;

	private SpbuMachinePartMttfEntityPK createKey(String machineSerial, String modelId, String partId, String machineModelPartIdentifier) {
		final SpbuMachinePartMttfEntityPK ret = new SpbuMachinePartMttfEntityPK();
		ret.setMachineSerial(machineSerial);
		ret.setModelId(modelId);
		ret.setPartId(partId);
		ret.setMachineModelPartIdentifier(machineModelPartIdentifier);
		return ret;
	}

	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Model model,
					@RequestParam("machineSerial") String machineSerial,
					@RequestParam("modelId") String modelId,
					@RequestParam("partId") String partId,
					@RequestParam("machineModelPartIdentifier") String machineModelPartIdentifier) {

		spbuMachinePartMttfRepository.delete(createKey(machineSerial, modelId, partId, machineModelPartIdentifier));
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
		final List<SpbuMachinePartMttfEntity> spbuMachinePartMttfEntities = spbuMachinePartMttfRepository.findAll();
		final List<ViewData> viewDatas = new LinkedList<>();

		for (final SpbuMachinePartMttfEntity spbuMachineEntity : spbuMachinePartMttfEntities) {
			viewDatas.add(getViewData(spbuMachineEntity));
		}

		model.addAttribute("viewDatas", viewDatas);
		return CONTROLLER_VIEW_ROOT + "/list";
	}

	@Transactional
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(Model model,
					@RequestParam("machineSerial") String machineSerial,
					@RequestParam("modelId") String modelId,
					@RequestParam("partId") String partId,
					@RequestParam("machineModelPartIdentifier") String machineModelPartIdentifier) {

		final SpbuMachinePartMttfEntity spbuMachinePartMttfEntity = spbuMachinePartMttfRepository.findOne(
						createKey(machineSerial, modelId, partId, machineModelPartIdentifier));

		final ViewData viewData = getViewData(spbuMachinePartMttfEntity);
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
			final SpbuMachinePartMttfEntityPK spbuMachinePartMttfEntityPK =
							createKey(formData.getMachineSerial(), formData.getModelId(), formData.getPartId(), formData.getMachineModelPartIdentifier());

			SpbuMachinePartMttfEntity spbuMachinePartMttfEntity = spbuMachinePartMttfRepository.findOne(spbuMachinePartMttfEntityPK);
			if (spbuMachinePartMttfEntity == null) {
				spbuMachinePartMttfEntity = new SpbuMachinePartMttfEntity(spbuMachinePartMttfEntityPK);
				spbuMachinePartMttfEntity.setMttf(formData.getMttf());
				spbuMachinePartMttfEntity.setMttfThreshold(formData.getMttfThreshold());
				spbuMachinePartMttfEntity = spbuMachinePartMttfRepository.save(spbuMachinePartMttfEntity);
				model.addAttribute("formData", getFormData(spbuMachinePartMttfEntity));
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
	public String update(Model model,
					@RequestParam("machineSerial") String machineSerial,
					@RequestParam("modelId") String modelId,
					@RequestParam("partId") String partId,
					@RequestParam("machineModelPartIdentifier") String machineModelPartIdentifier) {

		final SpbuMachinePartMttfEntity spbuMachinePartMttfEntity = spbuMachinePartMttfRepository.findOne(
						createKey(machineSerial, modelId, partId, machineModelPartIdentifier));

		if (spbuMachinePartMttfEntity != null) {
			model.addAttribute("formData", getFormData(spbuMachinePartMttfEntity));
			return CONTROLLER_VIEW_ROOT + "/update";
		} else {
			throw new RuntimeException("Unknown spbuMachineEntity");
		}
	}

	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid FormData formData, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			final SpbuMachinePartMttfEntityPK spbuMachinePartMttfEntityPK =
							createKey(formData.getMachineSerial(), formData.getModelId(), formData.getPartId(), formData.getMachineModelPartIdentifier());

			SpbuMachinePartMttfEntity spbuMachinePartMttfEntity = spbuMachinePartMttfRepository.findOne(spbuMachinePartMttfEntityPK);
			if (spbuMachinePartMttfEntity != null) {
				spbuMachinePartMttfEntity.setMttf(formData.getMttf());
				spbuMachinePartMttfEntity.setMttfThreshold(formData.getMttfThreshold());
				spbuMachinePartMttfEntity = spbuMachinePartMttfRepository.save(spbuMachinePartMttfEntity);
				model.addAttribute("formData", getFormData(spbuMachinePartMttfEntity));
			} else {
				bindingResult.reject("common.error.invalidData");
			}
		} else {
			bindingResult.reject("common.error.invalidData");
		}
		return CONTROLLER_VIEW_ROOT + "/update";
	}

	private ViewData getViewData(SpbuMachinePartMttfEntity spbuMachinePartMttfEntity) {
		final ViewData viewData = new ViewData();
		viewData.setMachineSerial(spbuMachinePartMttfEntity.getSpbuMachinePartMttfEntityPK().getMachineSerial());
		viewData.setSpbuCode(spbuMachinePartMttfEntity.getSpbuMachineEntity().getSpbuEntity().getCode());
		viewData.setSpbuMachineIdentifier(spbuMachinePartMttfEntity.getSpbuMachineEntity().getMachineIdentifier());
		viewData.setModelId(spbuMachinePartMttfEntity.getSpbuMachinePartMttfEntityPK().getModelId());
		viewData.setPartId(spbuMachinePartMttfEntity.getSpbuMachinePartMttfEntityPK().getPartId());
		viewData.setMachineModelPartIdentifier(spbuMachinePartMttfEntity.getSpbuMachinePartMttfEntityPK().getMachineModelPartIdentifier());
		viewData.setMttf(spbuMachinePartMttfEntity.getMttf());
		viewData.setMttfThreshold(spbuMachinePartMttfEntity.getMttfThreshold());
		return viewData;
	}

	private FormData getFormData(SpbuMachinePartMttfEntity spbuMachinePartMttfEntity) {
		final FormData formData = new FormData();
		formData.setMachineSerial(spbuMachinePartMttfEntity.getSpbuMachinePartMttfEntityPK().getMachineSerial());
		formData.setModelId(spbuMachinePartMttfEntity.getSpbuMachinePartMttfEntityPK().getModelId());
		formData.setPartId(spbuMachinePartMttfEntity.getSpbuMachinePartMttfEntityPK().getPartId());
		formData.setMachineModelPartIdentifier(spbuMachinePartMttfEntity.getSpbuMachinePartMttfEntityPK().getMachineModelPartIdentifier());
		formData.setMttf(spbuMachinePartMttfEntity.getMttf());
		formData.setMttfThreshold(spbuMachinePartMttfEntity.getMttfThreshold());
		return formData;
	}

	public static class ViewData {

		private String machineSerial;
		private String spbuCode;
		private String spbuMachineIdentifier;
		private String modelId;
		private String partId;
		private String machineModelPartIdentifier;
		private double mttf;
		private double mttfThreshold;

		public ViewData() {
		}

		public String getSpbuCode() {
			return spbuCode;
		}

		public void setSpbuCode(String spbuCode) {
			this.spbuCode = spbuCode;
		}

		public String getSpbuMachineIdentifier() {
			return spbuMachineIdentifier;
		}

		public void setSpbuMachineIdentifier(String spbuMachineIdentifier) {
			this.spbuMachineIdentifier = spbuMachineIdentifier;
		}

		public String getMachineSerial() {
			return machineSerial;
		}

		public void setMachineSerial(String machineSerial) {
			this.machineSerial = machineSerial;
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

		public double getMttf() {
			return mttf;
		}

		public void setMttf(double mttf) {
			this.mttf = mttf;
		}

		public double getMttfThreshold() {
			return mttfThreshold;
		}

		public void setMttfThreshold(double mttfThreshold) {
			this.mttfThreshold = mttfThreshold;
		}
	}

	public static class FormData {

		private String machineSerial;
		private String modelId;
		private String partId;
		private String machineModelPartIdentifier;
		private double mttf;
		private double mttfThreshold;

		public FormData() {
		}

		public String getMachineSerial() {
			return machineSerial;
		}

		public void setMachineSerial(String machineSerial) {
			this.machineSerial = machineSerial;
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

		public double getMttf() {
			return mttf;
		}

		public void setMttf(double mttf) {
			this.mttf = mttf;
		}

		public double getMttfThreshold() {
			return mttfThreshold;
		}

		public void setMttfThreshold(double mttfThreshold) {
			this.mttfThreshold = mttfThreshold;
		}
	}
}

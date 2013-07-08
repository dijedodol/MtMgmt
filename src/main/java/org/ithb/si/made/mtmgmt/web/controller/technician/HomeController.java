/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.web.controller.technician;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachinePartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelPartRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.ServiceReportSpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuMachineTotalizerRepository;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
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
 * @author Uyeee
 */
@Controller("technicianHomeController")
@RequestMapping("/technician/home")
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	private static final long SECOND = 1000l;
	private static final long MINUTE = 60 * SECOND;
	private static final long HOUR = 60 * MINUTE;
	private static final long DAY = 24 * HOUR;
	@Autowired
	private SpbuRepository spbuRepository;
	@Autowired
	private MachineModelPartRepository machineModelPartRepository;
	@Autowired
	private SpbuMachineTotalizerRepository spbuMachineTotalizerRepository;
	@Autowired
	private ServiceReportSpbuMachineTotalizerRepository serviceReportSpbuMachineTotalizerRepository;

	@Transactional
	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Model model) {
		final List<SpbuEntity> dbSpbuEntities = spbuRepository.findAll();
		for (final SpbuEntity dbSpbuEntity : dbSpbuEntities) {
			checkSpbu(model, dbSpbuEntity);
		}
		return "technician/home";
	}

	private void checkSpbu(Model model, SpbuEntity dbSpbuEntity) {
		final List<SpbuMachineEntity> dbSpbuMachineEntities = dbSpbuEntity.getSpbuMachineEntityList();
		for (final SpbuMachineEntity dbSpbuMachineEntity : dbSpbuMachineEntities) {
			checkSpbuMachine(model, dbSpbuMachineEntity);
		}
	}

	private void checkSpbuMachine(Model model, SpbuMachineEntity dbSpbuMachineEntity, List<MachineModelPartEntity> mttfThresholdLimit, List<MachineModelPartEntity> mttfLimit) {
		final List<MachineModelPartEntity> ret = new LinkedList<>();
		final List<ServiceReportEntity> dbServiceReports = dbSpbuMachineEntity.getServiceReportEntityList();

		// map all available machine's parts
		final Map<MachineModelPartEntity, ServiceReportEntity> machinePartServiceReports = new LinkedHashMap<>();
		for (final MachineModelPartEntity dbMachineModelPartEntity : dbSpbuMachineEntity.getMachineModelEntity().getMachineModelPartEntityList()) {
			machinePartServiceReports.put(dbMachineModelPartEntity, null);
		}

		// seek for the latest service report by iterating every service report associated with the specified SpbuMachineEntity
		for (final ServiceReportEntity dbServiceReportEntity : dbSpbuMachineEntity.getServiceReportEntityList()) {
			final MachinePartEntity dbMachinePartEntity = dbServiceReportEntity.getFailureModeHandlingEntity().getPartFailureModeEntity().getMachinePartEntity();
			final MachineModelPartEntityPK dbMachineModelPartEntityPk = new MachineModelPartEntityPK();
			dbMachineModelPartEntityPk.setMachineModelId(dbSpbuMachineEntity.getMachineModelEntity().getId());
			dbMachineModelPartEntityPk.setMachinePartId(dbMachinePartEntity.getId());
			final MachineModelPartEntity dbMachineModelPartEntity = machineModelPartRepository.findOne(dbMachineModelPartEntityPk);

			// sanity check begin
			if (dbMachineModelPartEntity == null) {
				throw new RuntimeException("Unexpected null dbMachineModelPartEntity for dbSpbuMachineEntity: " + dbSpbuMachineEntity + ", dbServiceReportEntity: " + dbServiceReportEntity);
			}
			final ServiceReportEntity latestServiceReportFound = machinePartServiceReports.get(dbMachineModelPartEntity);
			if (!machinePartServiceReports.containsKey(dbMachineModelPartEntity)) {
				throw new RuntimeException("Unexpected dbMachineModelPartEntity: " + dbMachineModelPartEntity + " from dbSpbuMachineEntity: " + dbSpbuMachineEntity + ", dbServiceReportEntity: " + dbServiceReportEntity);
			}
			// sanity check end

			if (latestServiceReportFound == null) {
				machinePartServiceReports.put(dbMachineModelPartEntity, dbServiceReportEntity);
			} else {
				if (latestServiceReportFound.getDate().getTime() < dbServiceReportEntity.getDate().getTime()) {
					machinePartServiceReports.put(dbMachineModelPartEntity, dbServiceReportEntity);
				}
			}
		}

		// iterate mapped machine's parts, check whether the specified part mttf has been expired
		for (Entry<MachineModelPartEntity, ServiceReportEntity> entry : machinePartServiceReports.entrySet()) {
			if (entry.getValue() == null) {
				// part has never been serviced yet
				if (checkByTotalizer(entry.getKey())) {
					double currentTotalizerCount = calculateTotalizerCount(entry.getKey(), dbSpbuMachineEntity);
					LOG.debug("Counted currentTotalizerCount:[{}] from MachineModelPartEntity:[{}], dbSpbuMachineEntity:[{}]", currentTotalizerCount, entry.getKey(), dbSpbuMachineEntity);
					if (currentTotalizerCount >= entry.getKey().getMttf()) {
						mttfLimit.add(entry.getKey());
					} else if (currentTotalizerCount >= entry.getKey().getMttfThreshold()) {
						mttfThresholdLimit.add(entry.getKey());
					}
				} else {
					LOG.debug("Forcing MachineModelPartEntity:[{}] from dbSpbuMachineEntity:[{}] as MTTF expired because there is no ServiceReport is found and Part is not measured by Totalizer", entry.getKey(), dbSpbuMachineEntity);
					mttfLimit.add(entry.getKey());
				}
			} else {
				if (checkByTotalizer(entry.getKey())) {
					double currentTotalizerCount = calculateTotalizerCount(entry.getKey(), dbSpbuMachineEntity);
					double lastServiceReportTotalizerCount = calculateTotalizerCount(entry.getValue(), entry.getKey(), dbSpbuMachineEntity);
					LOG.debug("Counted currentTotalizerCount:[{}] from MachineModelPartEntity:[{}], dbSpbuMachineEntity:[{}]", currentTotalizerCount, entry.getKey(), dbSpbuMachineEntity);
					LOG.debug("Counted lastServiceReportTotalizerCount:[{}] for ServiceReportEntity:[{}], MachineModelPartEntity:[{}], dbSpbuMachineEntity:[{}]", lastServiceReportTotalizerCount, entry.getValue(), entry.getKey(), dbSpbuMachineEntity);
					if ((currentTotalizerCount - lastServiceReportTotalizerCount) >= entry.getKey().getMttf()) {
						mttfLimit.add(entry.getKey());
					} else if ((currentTotalizerCount - lastServiceReportTotalizerCount) >= entry.getKey().getMttfThreshold()) {
						mttfThresholdLimit.add(entry.getKey());
					}
				} else {
					final long timeDiffWithServiceReport = System.currentTimeMillis() - entry.getValue().getDate().getTime();
					final double elapsedDaysSinceLastService = Math.round((timeDiffWithServiceReport / DAY) + 0.5d);
					if (elapsedDaysSinceLastService > entry.getKey().getMttf()) {
						
					}
				}
			}
		}
	}

	private boolean checkByTotalizer(MachineModelPartEntity dbMachineModelPartEntity) {
		return !dbMachineModelPartEntity.getMachineModelPartTotalizerEntityList().isEmpty();
	}

	private double calculateTotalizerCount(MachineModelPartEntity dbMachineModelPartEntity, SpbuMachineEntity dbSpbuMachineEntity) {
		double ret = 0;
		for (final MachineModelPartTotalizerEntity dbMachineModelPartTotalizerEntity : dbMachineModelPartEntity.getMachineModelPartTotalizerEntityList()) {
			final SpbuMachineTotalizerEntityPK dbSpbuMachineTotalizerEntityPK = new SpbuMachineTotalizerEntityPK();
			dbSpbuMachineTotalizerEntityPK.setSpbuId(dbSpbuMachineEntity.getSpbuEntity().getId());
			dbSpbuMachineTotalizerEntityPK.setMachineIdentifier(dbSpbuMachineEntity.getSpbuMachineEntityPK().getMachineIdentifier());
			dbSpbuMachineTotalizerEntityPK.setMachineTotalizerId(dbMachineModelPartTotalizerEntity.getMachineModelPartTotalizerEntityPK().getMachineTotalizerId());

			final SpbuMachineTotalizerEntity dbSpbuMachineTotalizerEntity = spbuMachineTotalizerRepository.findOne(dbSpbuMachineTotalizerEntityPK);
			if (dbSpbuMachineTotalizerEntity != null) {
				ret += dbSpbuMachineTotalizerEntity.getCounter();
			}
		}
		return ret;
	}

	private double calculateTotalizerCount(ServiceReportEntity dbServiceReportEntity, MachineModelPartEntity dbMachineModelPartEntity, SpbuMachineEntity dbSpbuMachineEntity) {
		double ret = 0;
		for (final MachineModelPartTotalizerEntity dbMachineModelPartTotalizerEntity : dbMachineModelPartEntity.getMachineModelPartTotalizerEntityList()) {
			final ServiceReportSpbuMachineTotalizerEntityPK tmpPk = new ServiceReportSpbuMachineTotalizerEntityPK();
			tmpPk.setSpbuId(dbSpbuMachineEntity.getSpbuEntity().getId());
			tmpPk.setMachineIdentifier(dbSpbuMachineEntity.getSpbuMachineEntityPK().getMachineIdentifier());
			tmpPk.setMachineTotalizerId(dbMachineModelPartTotalizerEntity.getMachineModelPartTotalizerEntityPK().getMachineTotalizerId());
			tmpPk.setServiceReportId(dbServiceReportEntity.getId());

			final ServiceReportSpbuMachineTotalizerEntity tmp = serviceReportSpbuMachineTotalizerRepository.findOne(tmpPk);
			if (tmp != null) {
				ret += tmp.getCounter();
			}
		}
		return ret;
	}
}

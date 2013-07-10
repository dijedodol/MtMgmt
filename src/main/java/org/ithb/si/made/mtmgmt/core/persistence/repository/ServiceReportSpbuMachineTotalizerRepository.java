/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Uyeee
 */
public interface ServiceReportSpbuMachineTotalizerRepository extends JpaRepository<ServiceReportSpbuMachineTotalizerEntity, ServiceReportSpbuMachineTotalizerEntityPK> {

	ServiceReportSpbuMachineTotalizerEntity findByServiceReportEntityAndMachineModelTotalizerEntity(ServiceReportEntity serviceReportEntity, MachineModelTotalizerEntity machineModelTotalizerEntity);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Uyeee
 */
public interface ServiceReportRepository extends JpaRepository<ServiceReportEntity, Long> {

	List<ServiceReportEntity> findBySpbuMachineEntity_SpbuEntity_IdOrderByDateDesc(long spbuId);

	List<ServiceReportEntity> findBySpbuMachineEntityOrderByDateDesc(SpbuMachineEntity spbuMachineEntity);
}

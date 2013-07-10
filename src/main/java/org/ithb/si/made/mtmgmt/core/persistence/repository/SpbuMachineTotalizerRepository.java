/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Uyeee
 */
public interface SpbuMachineTotalizerRepository extends JpaRepository<SpbuMachineTotalizerEntity, SpbuMachineTotalizerEntityPK> {

	SpbuMachineTotalizerEntity findBySpbuMachineEntityAndMachineModelTotalizerEntity(SpbuMachineEntity spbuMachineEntity, MachineModelTotalizerEntity machineModelTotalizerEntity);
}

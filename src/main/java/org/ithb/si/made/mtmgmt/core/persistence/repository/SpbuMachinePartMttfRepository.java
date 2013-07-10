/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import java.io.Serializable;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachinePartMttfEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachinePartMttfEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Uyeee
 */
public interface SpbuMachinePartMttfRepository extends JpaRepository<SpbuMachinePartMttfEntity, SpbuMachinePartMttfEntityPK> {

	SpbuMachinePartMttfEntity findBySpbuMachineEntityAndMachineModelPartEntity(SpbuMachineEntity spbuMachineEntity, MachineModelPartEntity machineModelPartEntity);
}

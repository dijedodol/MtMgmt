/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Uyeee
 */
public interface MachineModelPartRepository extends JpaRepository<MachineModelPartEntity, MachineModelPartEntityPK> {
}

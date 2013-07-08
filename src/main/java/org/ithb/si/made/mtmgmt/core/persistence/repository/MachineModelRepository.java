/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gde
 */
public interface MachineModelRepository extends JpaRepository<MachineModelEntity, String> {
}

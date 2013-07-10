/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Uyeee
 */
public interface FailureModeHandlingRepository extends JpaRepository<FailureModeHandlingEntity, FailureModeHandlingEntityPK> {
}

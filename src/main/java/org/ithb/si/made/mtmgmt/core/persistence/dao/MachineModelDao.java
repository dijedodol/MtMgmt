/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao;

import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;

/**
 *
 * @author gde
 */
public interface MachineModelDao extends AbstractDao<MachineModelEntity, Long> {

	MachineModelEntity findByCode(String code);
}

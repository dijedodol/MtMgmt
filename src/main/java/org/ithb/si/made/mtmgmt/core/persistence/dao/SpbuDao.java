/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao;

import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;

/**
 *
 * @author Uyeee
 */
public interface SpbuDao extends AbstractDao<SpbuEntity, Long> {

	SpbuEntity findByCode(String code);
	
	List<SpbuEntity> findBySupervisorId(long supervisorId);
}

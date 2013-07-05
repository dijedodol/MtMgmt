/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Uyeee
 */
public interface SpbuRepository extends JpaRepository<SpbuEntity, Long> {

	SpbuEntity findByCode(String code);
	
	List<SpbuEntity> findBySupervisorId(long supervisorId);
}

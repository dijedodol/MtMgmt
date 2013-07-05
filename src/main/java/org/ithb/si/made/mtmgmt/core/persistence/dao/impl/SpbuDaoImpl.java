/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.dao.SpbuDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gde
 */
@Repository
@Transactional
public class SpbuDaoImpl extends AbstractDaoImpl<SpbuEntity, Long> implements SpbuDao {

	@Autowired
	private SpbuRepository spbuRepository;

	public SpbuDaoImpl() {
	}

	@Override
	protected JpaRepository<SpbuEntity, Long> getRepository() {
		return spbuRepository;
	}

	@Override
	public SpbuEntity findByCode(String code) {
		return spbuRepository.findByCode(code);
	}

	@Override
	public List<SpbuEntity> findBySupervisorId(long supervisorId) {
		return spbuRepository.findBySupervisorId(supervisorId);
	}
}

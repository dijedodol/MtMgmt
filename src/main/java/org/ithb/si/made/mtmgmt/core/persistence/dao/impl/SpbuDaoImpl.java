/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao.impl;

import javax.transaction.Transactional;
import org.ithb.si.made.mtmgmt.core.persistence.dao.SpbuDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.SpbuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
	protected CrudRepository<SpbuEntity, Long> getRepository() {
		return spbuRepository;
	}

	@Override
	public SpbuEntity findByCode(String code) {
		return spbuRepository.findByCode(code);
	}
}

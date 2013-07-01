/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao.impl;

import java.io.Serializable;
import org.ithb.si.made.mtmgmt.core.persistence.dao.AbstractDao;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Uyeee
 */
public abstract class AbstractDaoImpl<ENTITY extends Serializable, ID extends Serializable> implements AbstractDao<ENTITY, ID> {

	protected abstract CrudRepository<ENTITY, ID> getRepository();

	@Override
	public ENTITY save(ENTITY entity) {
		return getRepository().save(entity);
	}

	@Override
	public void delete(ENTITY entity) {
		getRepository().delete(entity);
	}

	@Override
	public void deleteById(ID id) {
		getRepository().delete(id);
	}

	@Override
	public Iterable<ENTITY> findAll() {
		return getRepository().findAll();
	}

	@Override
	public ENTITY findById(ID id) {
		return getRepository().findOne(id);
	}
}

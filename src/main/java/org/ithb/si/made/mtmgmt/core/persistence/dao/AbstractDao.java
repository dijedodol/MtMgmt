/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao;

import java.io.Serializable;

/**
 *
 * @author Uyeee
 */
public interface AbstractDao<ENTITY extends Serializable, ID extends Serializable> {

	ENTITY save(ENTITY entity);

	void delete(ENTITY entity);

	void deleteById(ID id);

	Iterable<ENTITY> findAll();

	ENTITY findById(ID id);
}

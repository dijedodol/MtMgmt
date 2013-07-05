/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao.impl;

import org.ithb.si.made.mtmgmt.core.persistence.dao.MachineTotalizerDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineTotalizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Uyeee
 */
@Repository
@Transactional
public class MachineTotalizerDaoImpl extends AbstractDaoImpl<MachineTotalizerEntity, Long> implements MachineTotalizerDao {

	@Autowired
	private MachineTotalizerRepository machineTotalizerRepository;

	@Override
	protected CrudRepository<MachineTotalizerEntity, Long> getRepository() {
		return machineTotalizerRepository;
	}
}

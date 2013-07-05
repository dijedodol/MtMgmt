/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao.impl;

import org.ithb.si.made.mtmgmt.core.persistence.dao.MachineModelTotalizerDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntityPK;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelTotalizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Uyeee
 */
@Repository
@Transactional
public class MachineModelTotalizerDaoImpl extends AbstractDaoImpl<MachineModelTotalizerEntity, MachineModelTotalizerEntityPK> implements MachineModelTotalizerDao {

	@Autowired
	private MachineModelTotalizerRepository machineModelTotalizerRepository;

	@Override
	protected JpaRepository<MachineModelTotalizerEntity, MachineModelTotalizerEntityPK> getRepository() {
		return machineModelTotalizerRepository;
	}
}

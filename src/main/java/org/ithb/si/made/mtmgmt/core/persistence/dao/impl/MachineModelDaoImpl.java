/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao.impl;

import org.ithb.si.made.mtmgmt.core.persistence.dao.MachineModelDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity;
import org.ithb.si.made.mtmgmt.core.persistence.repository.MachineModelRepository;
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
public class MachineModelDaoImpl extends AbstractDaoImpl<MachineModelEntity, Long> implements MachineModelDao {

	@Autowired
	private MachineModelRepository machineModelRepository;

	public MachineModelDaoImpl() {
	}

	@Override
	protected JpaRepository<MachineModelEntity, Long> getRepository() {
		return machineModelRepository;
	}

	@Override
	public MachineModelEntity findByCode(String code) {
		return machineModelRepository.findByCode(code);
	}
}

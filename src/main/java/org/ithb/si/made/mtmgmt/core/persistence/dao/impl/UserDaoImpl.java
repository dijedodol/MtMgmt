/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao.impl;

import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Uyeee
 */
@Repository
@Transactional
public class UserDaoImpl extends AbstractDaoImpl<UserEntity, Long> implements UserDao {

	@Autowired
	private UserRepository userRepository;

	public UserDaoImpl() {
	}

	@Override
	protected JpaRepository<UserEntity, Long> getRepository() {
		return userRepository;
	}

	@Override
	public UserEntity findByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
}

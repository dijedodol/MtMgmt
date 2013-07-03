/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao.impl;

import javax.transaction.Transactional;
import org.ithb.si.made.mtmgmt.core.persistence.repository.UserRepository;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
	protected CrudRepository<UserEntity, Long> getRepository() {
		return userRepository;
	}

	@Override
	public UserEntity findByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}

	@Override
	public UserEntity getSpbuList(String loginId) {
		final UserEntity ret = findByLoginId(loginId);
		ret.getSpbuList().size();
		return ret;
	}

	@Override
	public UserEntity getSpbuList(UserEntity dbUserEntity) {
		final UserEntity ret = findByLoginId(dbUserEntity.getLoginId());
		ret.getSpbuList().size();
		return ret;
	}
}

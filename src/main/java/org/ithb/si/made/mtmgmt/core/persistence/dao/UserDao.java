/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.dao;

import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;

/**
 *
 * @author Uyeee
 */
public interface UserDao extends AbstractDao<UserEntity, Long> {

	UserEntity findByLoginId(String loginId);
	
	UserEntity getSpbuList(String loginId);
	
	UserEntity getSpbuList(UserEntity dbUserEntity);
}

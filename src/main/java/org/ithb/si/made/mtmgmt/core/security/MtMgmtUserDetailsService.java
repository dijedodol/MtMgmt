/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.security;

import java.util.Collections;
import org.ithb.si.made.mtmgmt.core.persistence.dao.UserDao;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author gde
 */
public class MtMgmtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	public MtMgmtUserDetailsService() {
	}
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final UserEntity dbUserEntity = userDao.findByLoginId(username);
		if (dbUserEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		
		final String loginId = dbUserEntity.getLoginId();
		final String passwordHash = dbUserEntity.getPasswordHash();
		final GrantedAuthority authority = new SimpleGrantedAuthority(dbUserEntity.getAccessRole().name());
		final User ret = new User(loginId, passwordHash, Collections.singletonList(authority));
		return ret;
	}
}

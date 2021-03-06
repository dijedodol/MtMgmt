/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.repository;

import java.util.List;
import org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity;
import org.ithb.si.made.mtmgmt.core.security.AccessRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Uyeee
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByLoginId(String loginId);

	List<UserEntity> findByAccessRole(AccessRole accessRole);
}

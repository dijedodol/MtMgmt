/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "login_id", unique = true, nullable = false)
	private String loginId;
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	@Column(name = "full_name", nullable = false)
	private String fullName;
	@Column(name = "access_role", nullable = false)
	private AccessRole accessRole;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supervisor", fetch = FetchType.LAZY)
	private List<SpbuEntity> spbuList;

	public UserEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public AccessRole getAccessRole() {
		return accessRole;
	}

	public void setAccessRole(AccessRole accessRole) {
		this.accessRole = accessRole;
	}

	public List<SpbuEntity> getSpbuList() {
		return spbuList;
	}

	public void setSpbuList(List<SpbuEntity> spbuList) {
		this.spbuList = spbuList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserEntity)) {
			return false;
		}
		UserEntity other = (UserEntity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserEntity{" + "id=" + id + ", loginId=" + loginId + ", passwordHash=" + passwordHash + ", fullName=" + fullName + ", accessRole=" + accessRole + ", spbuList=" + spbuList + '}';
	}

	public static enum AccessRole {

		ADMIN,
		TECHNICIAN,
		SUPERVISOR
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.ithb.si.made.mtmgmt.core.security.AccessRole;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "users", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"login_id"})})
@NamedQueries({
	@NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u")})
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID", nullable = false)
	private Long id;
	@Basic(optional = false)
  @NotNull
  @Column(name = "access_role", nullable = false)
	private AccessRole accessRole;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "full_name", nullable = false, length = 255)
	private String fullName;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "login_id", nullable = false, length = 255)
	private String loginId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "password_hash", nullable = false, length = 255)
	private String passwordHash;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "technicianEntity", fetch = FetchType.LAZY)
	private List<ServiceReportEntity> serviceReportEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supervisorEntity", fetch = FetchType.LAZY)
	private List<SpbuEntity> spbuEntityList;

	public UserEntity() {
	}

	public UserEntity(Long id) {
		this.id = id;
	}

	public UserEntity(Long id, AccessRole accessRole, String fullName, String loginId, String passwordHash) {
		this.id = id;
		this.accessRole = accessRole;
		this.fullName = fullName;
		this.loginId = loginId;
		this.passwordHash = passwordHash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccessRole getAccessRole() {
		return accessRole;
	}

	public void setAccessRole(AccessRole accessRole) {
		this.accessRole = accessRole;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public List<ServiceReportEntity> getServiceReportEntityList() {
		return serviceReportEntityList;
	}

	public void setServiceReportEntityList(List<ServiceReportEntity> serviceReportEntityList) {
		this.serviceReportEntityList = serviceReportEntityList;
	}

	public List<SpbuEntity> getSpbuEntityList() {
		return spbuEntityList;
	}

	public void setSpbuEntityList(List<SpbuEntity> spbuEntityList) {
		this.spbuEntityList = spbuEntityList;
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
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.UserEntity[ id=" + id + " ]";
	}

}

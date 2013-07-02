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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "spbus")
public class SpbuEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "code", unique = true, nullable = false, length = 20)
	private String code;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "address", nullable = false, length = 255)
	private String address;
	// @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "phone", nullable = false, length = 20)
	private String phone;
	@JoinColumn(name = "supervisor_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEntity supervisor;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "spbu", fetch = FetchType.LAZY)
	private List<SpbuMachineEntity> spbuMachinesList;

	public SpbuEntity() {
	}

	public SpbuEntity(Long id) {
		this.id = id;
	}

	public SpbuEntity(Long id, String code, String address, String phone) {
		this.id = id;
		this.code = code;
		this.address = address;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<SpbuMachineEntity> getSpbuMachinesList() {
		return spbuMachinesList;
	}

	public void setSpbuMachinesList(List<SpbuMachineEntity> spbuMachinesList) {
		this.spbuMachinesList = spbuMachinesList;
	}

	public UserEntity getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(UserEntity supervisor) {
		this.supervisor = supervisor;
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
		if (!(object instanceof SpbuEntity)) {
			return false;
		}
		SpbuEntity other = (SpbuEntity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SpbuEntity{" + "id=" + id + ", code=" + code + ", address=" + address + ", phone=" + phone + ", supervisor=" + supervisor + ", spbuMachinesList=" + spbuMachinesList + '}';
	}
}

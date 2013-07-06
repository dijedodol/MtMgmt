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

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "machine_models", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"code"})})
@NamedQueries({
	@NamedQuery(name = "MachineModelEntity.findAll", query = "SELECT m FROM MachineModelEntity m")})
public class MachineModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id", nullable = false)
	private Long id;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "code", nullable = false, length = 20)
	private String code;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name", nullable = false, length = 255)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelEntity", fetch = FetchType.LAZY)
	private List<MachineModelTotalizerEntity> machineModelTotalizerEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelEntity", fetch = FetchType.LAZY)
	private List<MachineModelPartEntity> machineModelPartEntityList;
	@OneToMany(mappedBy = "machineModelEntity", fetch = FetchType.LAZY)
	private List<SpbuMachineEntity> spbuMachineEntityList;

	public MachineModelEntity() {
	}

	public MachineModelEntity(Long id) {
		this.id = id;
	}

	public MachineModelEntity(Long id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MachineModelTotalizerEntity> getMachineModelTotalizerEntityList() {
		return machineModelTotalizerEntityList;
	}

	public void setMachineModelTotalizerEntityList(List<MachineModelTotalizerEntity> machineModelTotalizerEntityList) {
		this.machineModelTotalizerEntityList = machineModelTotalizerEntityList;
	}

	public List<MachineModelPartEntity> getMachineModelPartEntityList() {
		return machineModelPartEntityList;
	}

	public void setMachineModelPartEntityList(List<MachineModelPartEntity> machineModelPartEntityList) {
		this.machineModelPartEntityList = machineModelPartEntityList;
	}

	public List<SpbuMachineEntity> getSpbuMachineEntityList() {
		return spbuMachineEntityList;
	}

	public void setSpbuMachineEntityList(List<SpbuMachineEntity> spbuMachineEntityList) {
		this.spbuMachineEntityList = spbuMachineEntityList;
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
		if (!(object instanceof MachineModelEntity)) {
			return false;
		}
		MachineModelEntity other = (MachineModelEntity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity[ id=" + id + " ]";
	}

}

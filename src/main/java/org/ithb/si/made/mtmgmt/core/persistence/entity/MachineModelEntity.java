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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "machine_models")
@NamedQueries({
	@NamedQuery(name = "MachineModelEntity.findAll", query = "SELECT m FROM MachineModelEntity m")})
public class MachineModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "model_id", nullable = false, length = 40)
	private String modelId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name", nullable = false, length = 255)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelEntity", fetch = FetchType.LAZY)
	private List<MachineModelTotalizerEntity> machineModelTotalizerEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelEntity", fetch = FetchType.LAZY)
	private List<MachineModelPartEntity> machineModelPartEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelEntity", fetch = FetchType.LAZY)
	private List<SpbuMachineEntity> spbuMachineEntityList;

	public MachineModelEntity() {
	}

	public MachineModelEntity(String modelId) {
		this.modelId = modelId;
	}

	public MachineModelEntity(String modelId, String name) {
		this.modelId = modelId;
		this.name = name;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
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
		hash += (modelId != null ? modelId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MachineModelEntity)) {
			return false;
		}
		MachineModelEntity other = (MachineModelEntity) object;
		if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelEntity[ modelId=" + modelId + " ]";
	}

}

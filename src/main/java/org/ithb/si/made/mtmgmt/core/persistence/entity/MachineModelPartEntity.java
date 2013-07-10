/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "machine_model_parts")
@NamedQueries({
	@NamedQuery(name = "MachineModelPartEntity.findAll", query = "SELECT m FROM MachineModelPartEntity m")})
public class MachineModelPartEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected MachineModelPartEntityPK machineModelPartEntityPK;
	@ManyToMany(mappedBy = "machineModelPartEntityList", fetch = FetchType.LAZY)
	private List<MachineModelTotalizerEntity> machineModelTotalizerEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelPartEntity", fetch = FetchType.LAZY)
	private List<ServiceReportEntity> serviceReportEntityList;
	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelEntity machineModelEntity;
	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachinePartTypeEntity machinePartTypeEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelPartEntity", fetch = FetchType.LAZY)
	private List<SpbuMachinePartMttfEntity> spbuMachinePartMttfEntityList;

	public MachineModelPartEntity() {
	}

	public MachineModelPartEntity(MachineModelPartEntityPK machineModelPartEntityPK) {
		this.machineModelPartEntityPK = machineModelPartEntityPK;
	}

	public MachineModelPartEntity(String modelId, String partId, String machineModelPartIdentifier) {
		this.machineModelPartEntityPK = new MachineModelPartEntityPK(modelId, partId, machineModelPartIdentifier);
	}

	public MachineModelPartEntityPK getMachineModelPartEntityPK() {
		return machineModelPartEntityPK;
	}

	public void setMachineModelPartEntityPK(MachineModelPartEntityPK machineModelPartEntityPK) {
		this.machineModelPartEntityPK = machineModelPartEntityPK;
	}

	public List<MachineModelTotalizerEntity> getMachineModelTotalizerEntityList() {
		return machineModelTotalizerEntityList;
	}

	public void setMachineModelTotalizerEntityList(List<MachineModelTotalizerEntity> machineModelTotalizerEntityList) {
		this.machineModelTotalizerEntityList = machineModelTotalizerEntityList;
	}

	public List<ServiceReportEntity> getServiceReportEntityList() {
		return serviceReportEntityList;
	}

	public void setServiceReportEntityList(List<ServiceReportEntity> serviceReportEntityList) {
		this.serviceReportEntityList = serviceReportEntityList;
	}

	public MachineModelEntity getMachineModelEntity() {
		return machineModelEntity;
	}

	public void setMachineModelEntity(MachineModelEntity machineModelEntity) {
		this.machineModelEntity = machineModelEntity;
	}

	public MachinePartTypeEntity getMachinePartTypeEntity() {
		return machinePartTypeEntity;
	}

	public void setMachinePartTypeEntity(MachinePartTypeEntity machinePartTypeEntity) {
		this.machinePartTypeEntity = machinePartTypeEntity;
	}

	public List<SpbuMachinePartMttfEntity> getSpbuMachinePartMttfEntityList() {
		return spbuMachinePartMttfEntityList;
	}

	public void setSpbuMachinePartMttfEntityList(List<SpbuMachinePartMttfEntity> spbuMachinePartMttfEntityList) {
		this.spbuMachinePartMttfEntityList = spbuMachinePartMttfEntityList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (machineModelPartEntityPK != null ? machineModelPartEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MachineModelPartEntity)) {
			return false;
		}
		MachineModelPartEntity other = (MachineModelPartEntity) object;
		if ((this.machineModelPartEntityPK == null && other.machineModelPartEntityPK != null) || (this.machineModelPartEntityPK != null && !this.machineModelPartEntityPK.equals(other.machineModelPartEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity[ machineModelPartEntityPK=" + machineModelPartEntityPK + " ]";
	}

}

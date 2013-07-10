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
import javax.persistence.JoinTable;
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
@Table(name = "machine_model_totalizers")
@NamedQueries({
	@NamedQuery(name = "MachineModelTotalizerEntity.findAll", query = "SELECT m FROM MachineModelTotalizerEntity m")})
public class MachineModelTotalizerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected MachineModelTotalizerEntityPK machineModelTotalizerEntityPK;
	@JoinTable(name = "machine_model_part_totalizers", joinColumns = {
  	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false),
  	@JoinColumn(name = "totalizer_id", referencedColumnName = "totalizer_id", nullable = false)}, inverseJoinColumns = {
  	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false),
  	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false),
  	@JoinColumn(name = "machine_model_part_identifier", referencedColumnName = "machine_model_part_identifier", nullable = false)})
  @ManyToMany(fetch = FetchType.LAZY)
	private List<MachineModelPartEntity> machineModelPartEntityList;
	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelEntity machineModelEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelTotalizerEntity", fetch = FetchType.LAZY)
	private List<ServiceReportSpbuMachineTotalizerEntity> serviceReportSpbuMachineTotalizerEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelTotalizerEntity", fetch = FetchType.LAZY)
	private List<SpbuMachineTotalizerEntity> spbuMachineTotalizerEntityList;

	public MachineModelTotalizerEntity() {
	}

	public MachineModelTotalizerEntity(MachineModelTotalizerEntityPK machineModelTotalizerEntityPK) {
		this.machineModelTotalizerEntityPK = machineModelTotalizerEntityPK;
	}

	public MachineModelTotalizerEntity(String modelId, String totalizerId) {
		this.machineModelTotalizerEntityPK = new MachineModelTotalizerEntityPK(modelId, totalizerId);
	}

	public MachineModelTotalizerEntityPK getMachineModelTotalizerEntityPK() {
		return machineModelTotalizerEntityPK;
	}

	public void setMachineModelTotalizerEntityPK(MachineModelTotalizerEntityPK machineModelTotalizerEntityPK) {
		this.machineModelTotalizerEntityPK = machineModelTotalizerEntityPK;
	}

	public List<MachineModelPartEntity> getMachineModelPartEntityList() {
		return machineModelPartEntityList;
	}

	public void setMachineModelPartEntityList(List<MachineModelPartEntity> machineModelPartEntityList) {
		this.machineModelPartEntityList = machineModelPartEntityList;
	}

	public MachineModelEntity getMachineModelEntity() {
		return machineModelEntity;
	}

	public void setMachineModelEntity(MachineModelEntity machineModelEntity) {
		this.machineModelEntity = machineModelEntity;
	}

	public List<ServiceReportSpbuMachineTotalizerEntity> getServiceReportSpbuMachineTotalizerEntityList() {
		return serviceReportSpbuMachineTotalizerEntityList;
	}

	public void setServiceReportSpbuMachineTotalizerEntityList(List<ServiceReportSpbuMachineTotalizerEntity> serviceReportSpbuMachineTotalizerEntityList) {
		this.serviceReportSpbuMachineTotalizerEntityList = serviceReportSpbuMachineTotalizerEntityList;
	}

	public List<SpbuMachineTotalizerEntity> getSpbuMachineTotalizerEntityList() {
		return spbuMachineTotalizerEntityList;
	}

	public void setSpbuMachineTotalizerEntityList(List<SpbuMachineTotalizerEntity> spbuMachineTotalizerEntityList) {
		this.spbuMachineTotalizerEntityList = spbuMachineTotalizerEntityList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (machineModelTotalizerEntityPK != null ? machineModelTotalizerEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MachineModelTotalizerEntity)) {
			return false;
		}
		MachineModelTotalizerEntity other = (MachineModelTotalizerEntity) object;
		if ((this.machineModelTotalizerEntityPK == null && other.machineModelTotalizerEntityPK != null) || (this.machineModelTotalizerEntityPK != null && !this.machineModelTotalizerEntityPK.equals(other.machineModelTotalizerEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity[ machineModelTotalizerEntityPK=" + machineModelTotalizerEntityPK + " ]";
	}

}

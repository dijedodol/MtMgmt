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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Entity
@Table(name = "machine_model_totalizers")
@NamedQueries({
	@NamedQuery(name = "MachineModelTotalizerEntity.findAll", query = "SELECT m FROM MachineModelTotalizerEntity m")})
public class MachineModelTotalizerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected MachineModelTotalizerEntityPK machineModelTotalizerEntityPK;
	@ManyToMany(mappedBy = "machineModelTotalizerEntityList", fetch = FetchType.LAZY)
	private List<MachineModelPartEntity> machineModelPartEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelTotalizerEntity", fetch = FetchType.LAZY)
	private List<ServiceReportSpbuMachineTotalizerEntity> serviceReportSpbuMachineTotalizerEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelTotalizerEntity", fetch = FetchType.LAZY)
	private List<SpbuMachineTotalizerEntity> spbuMachineTotalizerEntityList;
	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelEntity machineModelEntity;

    private static final Logger LOG = LoggerFactory.getLogger(MachineModelTotalizerEntity.class);

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

	public MachineModelEntity getMachineModelEntity() {
		return machineModelEntity;
	}

	public void setMachineModelEntity(MachineModelEntity machineModelEntity) {
		this.machineModelEntity = machineModelEntity;
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

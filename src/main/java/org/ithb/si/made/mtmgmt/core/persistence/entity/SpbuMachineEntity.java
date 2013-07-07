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
@Table(name = "spbu_machines")
@NamedQueries({
	@NamedQuery(name = "SpbuMachineEntity.findAll", query = "SELECT s FROM SpbuMachineEntity s")})
public class SpbuMachineEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SpbuMachineEntityPK spbuMachineEntityPK;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "spbuMachineEntity", fetch = FetchType.LAZY)
	private List<ServiceReportEntity> serviceReportEntityList;
	@JoinColumn(name = "machine_model_id", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY)
	private MachineModelEntity machineModelEntity;
	@JoinColumn(name = "spbu_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuEntity spbuEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "spbuMachineEntity", fetch = FetchType.LAZY)
	private List<SpbuMachineTotalizerEntity> spbuMachineTotalizerEntityList;

	public SpbuMachineEntity() {
	}

	public SpbuMachineEntity(SpbuMachineEntityPK spbuMachineEntityPK) {
		this.spbuMachineEntityPK = spbuMachineEntityPK;
	}

	public SpbuMachineEntity(long spbuId, String machineIdentifier) {
		this.spbuMachineEntityPK = new SpbuMachineEntityPK(spbuId, machineIdentifier);
	}

	public SpbuMachineEntityPK getSpbuMachineEntityPK() {
		return spbuMachineEntityPK;
	}

	public void setSpbuMachineEntityPK(SpbuMachineEntityPK spbuMachineEntityPK) {
		this.spbuMachineEntityPK = spbuMachineEntityPK;
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

	public SpbuEntity getSpbuEntity() {
		return spbuEntity;
	}

	public void setSpbuEntity(SpbuEntity spbuEntity) {
		this.spbuEntity = spbuEntity;
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
		hash += (spbuMachineEntityPK != null ? spbuMachineEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SpbuMachineEntity)) {
			return false;
		}
		SpbuMachineEntity other = (SpbuMachineEntity) object;
		if ((this.spbuMachineEntityPK == null && other.spbuMachineEntityPK != null) || (this.spbuMachineEntityPK != null && !this.spbuMachineEntityPK.equals(other.spbuMachineEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity[ spbuMachineEntityPK=" + spbuMachineEntityPK + " ]";
	}

}

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Entity
@Table(name = "machine_model_parts")
@NamedQueries({
	@NamedQuery(name = "MachineModelPartEntity.findAll", query = "SELECT m FROM MachineModelPartEntity m")})
public class MachineModelPartEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected MachineModelPartEntityPK machineModelPartEntityPK;
	@JoinTable(name = "machine_model_part_totalizers", joinColumns = {
  	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false),
  	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false),
  	@JoinColumn(name = "machine_model_part_identifier", referencedColumnName = "machine_model_part_identifier", nullable = false)}, inverseJoinColumns = {
  	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false),
  	@JoinColumn(name = "totalizer_id", referencedColumnName = "totalizer_id", nullable = false)})
  @ManyToMany(fetch = FetchType.LAZY)
	private List<MachineModelTotalizerEntity> machineModelTotalizerEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelPartEntity", fetch = FetchType.LAZY)
	private List<ServiceReportEntity> serviceReportEntityList;
	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachinePartTypeEntity machinePartTypeEntity;
	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelEntity machineModelEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelPartEntity", fetch = FetchType.LAZY)
	private List<SpbuMachinePartMttfEntity> spbuMachinePartMttfEntityList;

    private static final Logger LOG = LoggerFactory.getLogger(MachineModelPartEntity.class);

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

	public MachinePartTypeEntity getMachinePartTypeEntity() {
		return machinePartTypeEntity;
	}

	public void setMachinePartTypeEntity(MachinePartTypeEntity machinePartTypeEntity) {
		this.machinePartTypeEntity = machinePartTypeEntity;
	}

	public MachineModelEntity getMachineModelEntity() {
		return machineModelEntity;
	}

	public void setMachineModelEntity(MachineModelEntity machineModelEntity) {
		this.machineModelEntity = machineModelEntity;
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

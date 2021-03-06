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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "spbu_machines")
@NamedQueries({
	@NamedQuery(name = "SpbuMachineEntity.findAll", query = "SELECT s FROM SpbuMachineEntity s")})
public class SpbuMachineEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "machine_serial", nullable = false, length = 40)
	private String machineSerial;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "machine_identifier", nullable = false, length = 40)
	private String machineIdentifier;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "spbuMachineEntity", fetch = FetchType.LAZY)
	private List<ServiceReportEntity> serviceReportEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "spbuMachineEntity", fetch = FetchType.LAZY)
	private List<SpbuMachinePartMttfEntity> spbuMachinePartMttfEntityList;
	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelEntity machineModelEntity;
	@JoinColumn(name = "spbu_id", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuEntity spbuEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "spbuMachineEntity", fetch = FetchType.LAZY)
	private List<SpbuMachineTotalizerEntity> spbuMachineTotalizerEntityList;

	public SpbuMachineEntity() {
	}

	public SpbuMachineEntity(String machineSerial) {
		this.machineSerial = machineSerial;
	}

	public SpbuMachineEntity(String machineSerial, String machineIdentifier) {
		this.machineSerial = machineSerial;
		this.machineIdentifier = machineIdentifier;
	}

	public String getMachineSerial() {
		return machineSerial;
	}

	public void setMachineSerial(String machineSerial) {
		this.machineSerial = machineSerial;
	}

	public String getMachineIdentifier() {
		return machineIdentifier;
	}

	public void setMachineIdentifier(String machineIdentifier) {
		this.machineIdentifier = machineIdentifier;
	}

	public List<ServiceReportEntity> getServiceReportEntityList() {
		return serviceReportEntityList;
	}

	public void setServiceReportEntityList(List<ServiceReportEntity> serviceReportEntityList) {
		this.serviceReportEntityList = serviceReportEntityList;
	}

	public List<SpbuMachinePartMttfEntity> getSpbuMachinePartMttfEntityList() {
		return spbuMachinePartMttfEntityList;
	}

	public void setSpbuMachinePartMttfEntityList(List<SpbuMachinePartMttfEntity> spbuMachinePartMttfEntityList) {
		this.spbuMachinePartMttfEntityList = spbuMachinePartMttfEntityList;
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
		hash += (machineSerial != null ? machineSerial.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SpbuMachineEntity)) {
			return false;
		}
		SpbuMachineEntity other = (SpbuMachineEntity) object;
		if ((this.machineSerial == null && other.machineSerial != null) || (this.machineSerial != null && !this.machineSerial.equals(other.machineSerial))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity[ machineSerial=" + machineSerial + " ]";
	}

}

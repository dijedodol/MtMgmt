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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Entity
@Table(name = "machine_part_types")
@NamedQueries({
	@NamedQuery(name = "MachinePartTypeEntity.findAll", query = "SELECT m FROM MachinePartTypeEntity m")})
public class MachinePartTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "part_id", nullable = false, length = 40)
	private String partId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name", nullable = false, length = 255)
	private String name;
	@Basic(optional = false)
  @NotNull
  @Column(name = "default_mttf", nullable = false)
	private double defaultMttf;
	@Basic(optional = false)
  @NotNull
  @Column(name = "default_mttf_threshold", nullable = false)
	private double defaultMttfThreshold;
	@Basic(optional = false)
  @NotNull
  @Column(name = "mttf_by_totalizer", nullable = false)
	private boolean mttfByTotalizer;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machinePartTypeEntity", fetch = FetchType.LAZY)
	private List<MachineModelPartEntity> machineModelPartEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machinePartTypeEntity", fetch = FetchType.LAZY)
	private List<PartFailureModeEntity> partFailureModeEntityList;

    private static final Logger LOG = LoggerFactory.getLogger(MachinePartTypeEntity.class);

	public MachinePartTypeEntity() {
	}

	public MachinePartTypeEntity(String partId) {
		this.partId = partId;
	}

	public MachinePartTypeEntity(String partId, String name, double defaultMttf, double defaultMttfThreshold, boolean mttfByTotalizer) {
		this.partId = partId;
		this.name = name;
		this.defaultMttf = defaultMttf;
		this.defaultMttfThreshold = defaultMttfThreshold;
		this.mttfByTotalizer = mttfByTotalizer;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDefaultMttf() {
		return defaultMttf;
	}

	public void setDefaultMttf(double defaultMttf) {
		this.defaultMttf = defaultMttf;
	}

	public double getDefaultMttfThreshold() {
		return defaultMttfThreshold;
	}

	public void setDefaultMttfThreshold(double defaultMttfThreshold) {
		this.defaultMttfThreshold = defaultMttfThreshold;
	}

	public boolean getMttfByTotalizer() {
		return mttfByTotalizer;
	}

	public void setMttfByTotalizer(boolean mttfByTotalizer) {
		this.mttfByTotalizer = mttfByTotalizer;
	}

	public List<MachineModelPartEntity> getMachineModelPartEntityList() {
		return machineModelPartEntityList;
	}

	public void setMachineModelPartEntityList(List<MachineModelPartEntity> machineModelPartEntityList) {
		this.machineModelPartEntityList = machineModelPartEntityList;
	}

	public List<PartFailureModeEntity> getPartFailureModeEntityList() {
		return partFailureModeEntityList;
	}

	public void setPartFailureModeEntityList(List<PartFailureModeEntity> partFailureModeEntityList) {
		this.partFailureModeEntityList = partFailureModeEntityList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (partId != null ? partId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MachinePartTypeEntity)) {
			return false;
		}
		MachinePartTypeEntity other = (MachinePartTypeEntity) object;
		if ((this.partId == null && other.partId != null) || (this.partId != null && !this.partId.equals(other.partId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachinePartTypeEntity[ partId=" + partId + " ]";
	}
}

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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	@Basic(optional = false)
  @NotNull
  @Column(name = "mttf", nullable = false)
	private double mttf;
	@Basic(optional = false)
  @NotNull
  @Column(name = "mttf_threshold", nullable = false)
	private double mttfThreshold;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelPartEntity", fetch = FetchType.LAZY)
	private List<MachineModelPartTotalizerEntity> machineModelPartTotalizerEntityList;
	@JoinColumn(name = "machine_model_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelEntity machineModelEntity;
	@JoinColumn(name = "machine_part_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachinePartEntity machinePartEntity;

	public MachineModelPartEntity() {
	}

	public MachineModelPartEntity(MachineModelPartEntityPK machineModelPartEntityPK) {
		this.machineModelPartEntityPK = machineModelPartEntityPK;
	}

	public MachineModelPartEntity(MachineModelPartEntityPK machineModelPartEntityPK, double mttf, double mttfThreshold) {
		this.machineModelPartEntityPK = machineModelPartEntityPK;
		this.mttf = mttf;
		this.mttfThreshold = mttfThreshold;
	}

	public MachineModelPartEntity(long machinePartId, long machineModelId) {
		this.machineModelPartEntityPK = new MachineModelPartEntityPK(machinePartId, machineModelId);
	}

	public MachineModelPartEntityPK getMachineModelPartEntityPK() {
		return machineModelPartEntityPK;
	}

	public void setMachineModelPartEntityPK(MachineModelPartEntityPK machineModelPartEntityPK) {
		this.machineModelPartEntityPK = machineModelPartEntityPK;
	}

	public double getMttf() {
		return mttf;
	}

	public void setMttf(double mttf) {
		this.mttf = mttf;
	}

	public double getMttfThreshold() {
		return mttfThreshold;
	}

	public void setMttfThreshold(double mttfThreshold) {
		this.mttfThreshold = mttfThreshold;
	}

	public List<MachineModelPartTotalizerEntity> getMachineModelPartTotalizerEntityList() {
		return machineModelPartTotalizerEntityList;
	}

	public void setMachineModelPartTotalizerEntityList(List<MachineModelPartTotalizerEntity> machineModelPartTotalizerEntityList) {
		this.machineModelPartTotalizerEntityList = machineModelPartTotalizerEntityList;
	}

	public MachineModelEntity getMachineModelEntity() {
		return machineModelEntity;
	}

	public void setMachineModelEntity(MachineModelEntity machineModelEntity) {
		this.machineModelEntity = machineModelEntity;
	}

	public MachinePartEntity getMachinePartEntity() {
		return machinePartEntity;
	}

	public void setMachinePartEntity(MachinePartEntity machinePartEntity) {
		this.machinePartEntity = machinePartEntity;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Uyeee
 */
@Embeddable
public class MachineModelPartEntityPK implements Serializable {
	@Basic(optional = false)
  @NotNull
  @Column(name = "machine_part_id", nullable = false)
	private long machinePartId;
	@Basic(optional = false)
  @NotNull
  @Column(name = "machine_model_id", nullable = false)
	private long machineModelId;

	public MachineModelPartEntityPK() {
	}

	public MachineModelPartEntityPK(long machinePartId, long machineModelId) {
		this.machinePartId = machinePartId;
		this.machineModelId = machineModelId;
	}

	public long getMachinePartId() {
		return machinePartId;
	}

	public void setMachinePartId(long machinePartId) {
		this.machinePartId = machinePartId;
	}

	public long getMachineModelId() {
		return machineModelId;
	}

	public void setMachineModelId(long machineModelId) {
		this.machineModelId = machineModelId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) machinePartId;
		hash += (int) machineModelId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MachineModelPartEntityPK)) {
			return false;
		}
		MachineModelPartEntityPK other = (MachineModelPartEntityPK) object;
		if (this.machinePartId != other.machinePartId) {
			return false;
		}
		if (this.machineModelId != other.machineModelId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntityPK[ machinePartId=" + machinePartId + ", machineModelId=" + machineModelId + " ]";
	}

}

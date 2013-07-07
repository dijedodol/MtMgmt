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
public class MachineModelTotalizerEntityPK implements Serializable {
	@Basic(optional = false)
  @NotNull
  @Column(name = "machine_totalizer_id", nullable = false)
	private long machineTotalizerId;
	@Basic(optional = false)
  @NotNull
  @Column(name = "machine_model_id", nullable = false)
	private long machineModelId;

	public MachineModelTotalizerEntityPK() {
	}

	public MachineModelTotalizerEntityPK(long machineTotalizerId, long machineModelId) {
		this.machineTotalizerId = machineTotalizerId;
		this.machineModelId = machineModelId;
	}

	public long getMachineTotalizerId() {
		return machineTotalizerId;
	}

	public void setMachineTotalizerId(long machineTotalizerId) {
		this.machineTotalizerId = machineTotalizerId;
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
		hash += (int) machineTotalizerId;
		hash += (int) machineModelId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MachineModelTotalizerEntityPK)) {
			return false;
		}
		MachineModelTotalizerEntityPK other = (MachineModelTotalizerEntityPK) object;
		if (this.machineTotalizerId != other.machineTotalizerId) {
			return false;
		}
		if (this.machineModelId != other.machineModelId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntityPK[ machineTotalizerId=" + machineTotalizerId + ", machineModelId=" + machineModelId + " ]";
	}

}

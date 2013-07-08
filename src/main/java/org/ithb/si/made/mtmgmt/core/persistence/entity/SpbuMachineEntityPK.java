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
import javax.validation.constraints.Size;

/**
 *
 * @author Uyeee
 */
@Embeddable
public class SpbuMachineEntityPK implements Serializable {
	@Basic(optional = false)
  @NotNull
  @Column(name = "spbu_id", nullable = false)
	private long spbuId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "model_id", nullable = false, length = 40)
	private String modelId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "serial_number", nullable = false, length = 40)
	private String serialNumber;

	public SpbuMachineEntityPK() {
	}

	public SpbuMachineEntityPK(long spbuId, String modelId, String serialNumber) {
		this.spbuId = spbuId;
		this.modelId = modelId;
		this.serialNumber = serialNumber;
	}

	public long getSpbuId() {
		return spbuId;
	}

	public void setSpbuId(long spbuId) {
		this.spbuId = spbuId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) spbuId;
		hash += (modelId != null ? modelId.hashCode() : 0);
		hash += (serialNumber != null ? serialNumber.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SpbuMachineEntityPK)) {
			return false;
		}
		SpbuMachineEntityPK other = (SpbuMachineEntityPK) object;
		if (this.spbuId != other.spbuId) {
			return false;
		}
		if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
			return false;
		}
		if ((this.serialNumber == null && other.serialNumber != null) || (this.serialNumber != null && !this.serialNumber.equals(other.serialNumber))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntityPK[ spbuId=" + spbuId + ", modelId=" + modelId + ", serialNumber=" + serialNumber + " ]";
	}

}

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
public class SpbuMachinePartMttfEntityPK implements Serializable {
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
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "part_id", nullable = false, length = 40)
	private String partId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "machine_model_part_identifier", nullable = false, length = 40)
	private String machineModelPartIdentifier;

	public SpbuMachinePartMttfEntityPK() {
	}

	public SpbuMachinePartMttfEntityPK(long spbuId, String modelId, String serialNumber, String partId, String machineModelPartIdentifier) {
		this.spbuId = spbuId;
		this.modelId = modelId;
		this.serialNumber = serialNumber;
		this.partId = partId;
		this.machineModelPartIdentifier = machineModelPartIdentifier;
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

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getMachineModelPartIdentifier() {
		return machineModelPartIdentifier;
	}

	public void setMachineModelPartIdentifier(String machineModelPartIdentifier) {
		this.machineModelPartIdentifier = machineModelPartIdentifier;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) spbuId;
		hash += (modelId != null ? modelId.hashCode() : 0);
		hash += (serialNumber != null ? serialNumber.hashCode() : 0);
		hash += (partId != null ? partId.hashCode() : 0);
		hash += (machineModelPartIdentifier != null ? machineModelPartIdentifier.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SpbuMachinePartMttfEntityPK)) {
			return false;
		}
		SpbuMachinePartMttfEntityPK other = (SpbuMachinePartMttfEntityPK) object;
		if (this.spbuId != other.spbuId) {
			return false;
		}
		if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
			return false;
		}
		if ((this.serialNumber == null && other.serialNumber != null) || (this.serialNumber != null && !this.serialNumber.equals(other.serialNumber))) {
			return false;
		}
		if ((this.partId == null && other.partId != null) || (this.partId != null && !this.partId.equals(other.partId))) {
			return false;
		}
		if ((this.machineModelPartIdentifier == null && other.machineModelPartIdentifier != null) || (this.machineModelPartIdentifier != null && !this.machineModelPartIdentifier.equals(other.machineModelPartIdentifier))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachinePartMttfEntityPK[ spbuId=" + spbuId + ", modelId=" + modelId + ", serialNumber=" + serialNumber + ", partId=" + partId + ", machineModelPartIdentifier=" + machineModelPartIdentifier + " ]";
	}

}

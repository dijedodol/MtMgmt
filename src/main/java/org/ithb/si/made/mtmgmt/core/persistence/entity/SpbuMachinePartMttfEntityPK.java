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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Embeddable
public class SpbuMachinePartMttfEntityPK implements Serializable {
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "machine_serial", nullable = false, length = 40)
	private String machineSerial;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "model_id", nullable = false, length = 40)
	private String modelId;
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

    private static final Logger LOG = LoggerFactory.getLogger(SpbuMachinePartMttfEntityPK.class);

	public SpbuMachinePartMttfEntityPK() {
	}

	public SpbuMachinePartMttfEntityPK(String machineSerial, String modelId, String partId, String machineModelPartIdentifier) {
		this.machineSerial = machineSerial;
		this.modelId = modelId;
		this.partId = partId;
		this.machineModelPartIdentifier = machineModelPartIdentifier;
	}

	public String getMachineSerial() {
		return machineSerial;
	}

	public void setMachineSerial(String machineSerial) {
		this.machineSerial = machineSerial;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
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
		hash += (machineSerial != null ? machineSerial.hashCode() : 0);
		hash += (modelId != null ? modelId.hashCode() : 0);
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
		if ((this.machineSerial == null && other.machineSerial != null) || (this.machineSerial != null && !this.machineSerial.equals(other.machineSerial))) {
			return false;
		}
		if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
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
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachinePartMttfEntityPK[ machineSerial=" + machineSerial + ", modelId=" + modelId + ", partId=" + partId + ", machineModelPartIdentifier=" + machineModelPartIdentifier + " ]";
	}
}

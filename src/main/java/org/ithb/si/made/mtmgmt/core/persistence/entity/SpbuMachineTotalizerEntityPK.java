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
public class SpbuMachineTotalizerEntityPK implements Serializable {
	@Basic(optional = false)
  @NotNull
  @Column(name = "spbu_id", nullable = false)
	private long spbuId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "identifier", nullable = false, length = 20)
	private String identifier;
	@Basic(optional = false)
  @NotNull
  @Column(name = "machine_totalizer_id", nullable = false)
	private long machineTotalizerId;

	public SpbuMachineTotalizerEntityPK() {
	}

	public SpbuMachineTotalizerEntityPK(long spbuId, String identifier, long machineTotalizerId) {
		this.spbuId = spbuId;
		this.identifier = identifier;
		this.machineTotalizerId = machineTotalizerId;
	}

	public long getSpbuId() {
		return spbuId;
	}

	public void setSpbuId(long spbuId) {
		this.spbuId = spbuId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public long getMachineTotalizerId() {
		return machineTotalizerId;
	}

	public void setMachineTotalizerId(long machineTotalizerId) {
		this.machineTotalizerId = machineTotalizerId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) spbuId;
		hash += (identifier != null ? identifier.hashCode() : 0);
		hash += (int) machineTotalizerId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SpbuMachineTotalizerEntityPK)) {
			return false;
		}
		SpbuMachineTotalizerEntityPK other = (SpbuMachineTotalizerEntityPK) object;
		if (this.spbuId != other.spbuId) {
			return false;
		}
		if ((this.identifier == null && other.identifier != null) || (this.identifier != null && !this.identifier.equals(other.identifier))) {
			return false;
		}
		if (this.machineTotalizerId != other.machineTotalizerId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntityPK[ spbuId=" + spbuId + ", identifier=" + identifier + ", machineTotalizerId=" + machineTotalizerId + " ]";
	}

}

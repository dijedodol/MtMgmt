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
public class FailureModeHandlingEntityPK implements Serializable {
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "part_id", nullable = false, length = 40)
	private String partId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "failure_mode_code", nullable = false, length = 40)
	private String failureModeCode;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "failure_mode_handling_code", nullable = false, length = 40)
	private String failureModeHandlingCode;

	public FailureModeHandlingEntityPK() {
	}

	public FailureModeHandlingEntityPK(String partId, String failureModeCode, String failureModeHandlingCode) {
		this.partId = partId;
		this.failureModeCode = failureModeCode;
		this.failureModeHandlingCode = failureModeHandlingCode;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getFailureModeCode() {
		return failureModeCode;
	}

	public void setFailureModeCode(String failureModeCode) {
		this.failureModeCode = failureModeCode;
	}

	public String getFailureModeHandlingCode() {
		return failureModeHandlingCode;
	}

	public void setFailureModeHandlingCode(String failureModeHandlingCode) {
		this.failureModeHandlingCode = failureModeHandlingCode;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (partId != null ? partId.hashCode() : 0);
		hash += (failureModeCode != null ? failureModeCode.hashCode() : 0);
		hash += (failureModeHandlingCode != null ? failureModeHandlingCode.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof FailureModeHandlingEntityPK)) {
			return false;
		}
		FailureModeHandlingEntityPK other = (FailureModeHandlingEntityPK) object;
		if ((this.partId == null && other.partId != null) || (this.partId != null && !this.partId.equals(other.partId))) {
			return false;
		}
		if ((this.failureModeCode == null && other.failureModeCode != null) || (this.failureModeCode != null && !this.failureModeCode.equals(other.failureModeCode))) {
			return false;
		}
		if ((this.failureModeHandlingCode == null && other.failureModeHandlingCode != null) || (this.failureModeHandlingCode != null && !this.failureModeHandlingCode.equals(other.failureModeHandlingCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntityPK[ partId=" + partId + ", failureModeCode=" + failureModeCode + ", failureModeHandlingCode=" + failureModeHandlingCode + " ]";
	}

}

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
public class ServiceReportSpbuMachineTotalizerEntityPK implements Serializable {
	@Basic(optional = false)
  @NotNull
  @Column(name = "service_report_id", nullable = false)
	private long serviceReportId;
	@Basic(optional = false)
  @NotNull
  @Column(name = "spbu_id", nullable = false)
	private long spbuId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "machine_identifier", nullable = false, length = 20)
	private String machineIdentifier;
	@Basic(optional = false)
  @NotNull
  @Column(name = "machine_totalizer_id", nullable = false)
	private long machineTotalizerId;

	public ServiceReportSpbuMachineTotalizerEntityPK() {
	}

	public ServiceReportSpbuMachineTotalizerEntityPK(long serviceReportId, long spbuId, String machineIdentifier, long machineTotalizerId) {
		this.serviceReportId = serviceReportId;
		this.spbuId = spbuId;
		this.machineIdentifier = machineIdentifier;
		this.machineTotalizerId = machineTotalizerId;
	}

	public long getServiceReportId() {
		return serviceReportId;
	}

	public void setServiceReportId(long serviceReportId) {
		this.serviceReportId = serviceReportId;
	}

	public long getSpbuId() {
		return spbuId;
	}

	public void setSpbuId(long spbuId) {
		this.spbuId = spbuId;
	}

	public String getMachineIdentifier() {
		return machineIdentifier;
	}

	public void setMachineIdentifier(String machineIdentifier) {
		this.machineIdentifier = machineIdentifier;
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
		hash += (int) serviceReportId;
		hash += (int) spbuId;
		hash += (machineIdentifier != null ? machineIdentifier.hashCode() : 0);
		hash += (int) machineTotalizerId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ServiceReportSpbuMachineTotalizerEntityPK)) {
			return false;
		}
		ServiceReportSpbuMachineTotalizerEntityPK other = (ServiceReportSpbuMachineTotalizerEntityPK) object;
		if (this.serviceReportId != other.serviceReportId) {
			return false;
		}
		if (this.spbuId != other.spbuId) {
			return false;
		}
		if ((this.machineIdentifier == null && other.machineIdentifier != null) || (this.machineIdentifier != null && !this.machineIdentifier.equals(other.machineIdentifier))) {
			return false;
		}
		if (this.machineTotalizerId != other.machineTotalizerId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntityPK[ serviceReportId=" + serviceReportId + ", spbuId=" + spbuId + ", machineIdentifier=" + machineIdentifier + ", machineTotalizerId=" + machineTotalizerId + " ]";
	}

}

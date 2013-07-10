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
  @Size(min = 1, max = 40)
  @Column(name = "model_id", nullable = false, length = 40)
	private String modelId;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 40)
  @Column(name = "totalizer_id", nullable = false, length = 40)
	private String totalizerId;

	public ServiceReportSpbuMachineTotalizerEntityPK() {
	}

	public ServiceReportSpbuMachineTotalizerEntityPK(long serviceReportId, String modelId, String totalizerId) {
		this.serviceReportId = serviceReportId;
		this.modelId = modelId;
		this.totalizerId = totalizerId;
	}

	public long getServiceReportId() {
		return serviceReportId;
	}

	public void setServiceReportId(long serviceReportId) {
		this.serviceReportId = serviceReportId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getTotalizerId() {
		return totalizerId;
	}

	public void setTotalizerId(String totalizerId) {
		this.totalizerId = totalizerId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) serviceReportId;
		hash += (modelId != null ? modelId.hashCode() : 0);
		hash += (totalizerId != null ? totalizerId.hashCode() : 0);
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
		if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
			return false;
		}
		if ((this.totalizerId == null && other.totalizerId != null) || (this.totalizerId != null && !this.totalizerId.equals(other.totalizerId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntityPK[ serviceReportId=" + serviceReportId + ", modelId=" + modelId + ", totalizerId=" + totalizerId + " ]";
	}

}

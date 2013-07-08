/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "service_report_spbu_machine_totalizers")
@NamedQueries({
	@NamedQuery(name = "ServiceReportSpbuMachineTotalizerEntity.findAll", query = "SELECT s FROM ServiceReportSpbuMachineTotalizerEntity s")})
public class ServiceReportSpbuMachineTotalizerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ServiceReportSpbuMachineTotalizerEntityPK serviceReportSpbuMachineTotalizerEntityPK;
	@Basic(optional = false)
  @NotNull
  @Column(name = "counter", nullable = false)
	private double counter;
	@JoinColumn(name = "service_report_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ServiceReportEntity serviceReportEntity;
	@JoinColumns({
  	@JoinColumn(name = "spbu_id", referencedColumnName = "spbu_id", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "machine_identifier", referencedColumnName = "machine_identifier", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "machine_totalizer_id", referencedColumnName = "machine_totalizer_id", nullable = false, insertable = false, updatable = false)})
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuMachineTotalizerEntity spbuMachineTotalizerEntity;

	public ServiceReportSpbuMachineTotalizerEntity() {
	}

	public ServiceReportSpbuMachineTotalizerEntity(ServiceReportSpbuMachineTotalizerEntityPK serviceReportSpbuMachineTotalizerEntityPK) {
		this.serviceReportSpbuMachineTotalizerEntityPK = serviceReportSpbuMachineTotalizerEntityPK;
	}

	public ServiceReportSpbuMachineTotalizerEntity(ServiceReportSpbuMachineTotalizerEntityPK serviceReportSpbuMachineTotalizerEntityPK, double counter) {
		this.serviceReportSpbuMachineTotalizerEntityPK = serviceReportSpbuMachineTotalizerEntityPK;
		this.counter = counter;
	}

	public ServiceReportSpbuMachineTotalizerEntity(long spbuId, String machineIdentifier, long machineTotalizerId, long serviceReportId) {
		this.serviceReportSpbuMachineTotalizerEntityPK = new ServiceReportSpbuMachineTotalizerEntityPK(spbuId, machineIdentifier, machineTotalizerId, serviceReportId);
	}

	public ServiceReportSpbuMachineTotalizerEntityPK getServiceReportSpbuMachineTotalizerEntityPK() {
		return serviceReportSpbuMachineTotalizerEntityPK;
	}

	public void setServiceReportSpbuMachineTotalizerEntityPK(ServiceReportSpbuMachineTotalizerEntityPK serviceReportSpbuMachineTotalizerEntityPK) {
		this.serviceReportSpbuMachineTotalizerEntityPK = serviceReportSpbuMachineTotalizerEntityPK;
	}

	public double getCounter() {
		return counter;
	}

	public void setCounter(double counter) {
		this.counter = counter;
	}

	public ServiceReportEntity getServiceReportEntity() {
		return serviceReportEntity;
	}

	public void setServiceReportEntity(ServiceReportEntity serviceReportEntity) {
		this.serviceReportEntity = serviceReportEntity;
	}

	public SpbuMachineTotalizerEntity getSpbuMachineTotalizerEntity() {
		return spbuMachineTotalizerEntity;
	}

	public void setSpbuMachineTotalizerEntity(SpbuMachineTotalizerEntity spbuMachineTotalizerEntity) {
		this.spbuMachineTotalizerEntity = spbuMachineTotalizerEntity;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (serviceReportSpbuMachineTotalizerEntityPK != null ? serviceReportSpbuMachineTotalizerEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ServiceReportSpbuMachineTotalizerEntity)) {
			return false;
		}
		ServiceReportSpbuMachineTotalizerEntity other = (ServiceReportSpbuMachineTotalizerEntity) object;
		if ((this.serviceReportSpbuMachineTotalizerEntityPK == null && other.serviceReportSpbuMachineTotalizerEntityPK != null) || (this.serviceReportSpbuMachineTotalizerEntityPK != null && !this.serviceReportSpbuMachineTotalizerEntityPK.equals(other.serviceReportSpbuMachineTotalizerEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportSpbuMachineTotalizerEntity[ serviceReportSpbuMachineTotalizerEntityPK=" + serviceReportSpbuMachineTotalizerEntityPK + " ]";
	}

}

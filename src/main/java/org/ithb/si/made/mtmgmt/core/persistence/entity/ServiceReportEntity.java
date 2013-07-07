/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "service_reports")
@NamedQueries({
	@NamedQuery(name = "ServiceReportEntity.findAll", query = "SELECT s FROM ServiceReportEntity s")})
public class ServiceReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "id", nullable = false)
	private Long id;
	@Basic(optional = false)
  @NotNull
  @Column(name = "date", nullable = false)
  @Temporal(TemporalType.DATE)
	private Date date;
	@JoinColumns({
  	@JoinColumn(name = "spbu_id", referencedColumnName = "spbu_id", nullable = false),
  	@JoinColumn(name = "machine_identifier", referencedColumnName = "machine_identifier", nullable = false)})
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuMachineEntity spbuMachineEntity;
	@JoinColumn(name = "failure_mode_handling_id", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private FailureModeHandlingEntity failureModeHandlingEntity;
	@JoinColumn(name = "technician_id", referencedColumnName = "ID", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEntity userEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceReportEntity", fetch = FetchType.LAZY)
	private List<ServiceReportSpbuMachineTotalizerEntity> serviceReportSpbuMachineTotalizerEntityList;

	public ServiceReportEntity() {
	}

	public ServiceReportEntity(Long id) {
		this.id = id;
	}

	public ServiceReportEntity(Long id, Date date) {
		this.id = id;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SpbuMachineEntity getSpbuMachineEntity() {
		return spbuMachineEntity;
	}

	public void setSpbuMachineEntity(SpbuMachineEntity spbuMachineEntity) {
		this.spbuMachineEntity = spbuMachineEntity;
	}

	public FailureModeHandlingEntity getFailureModeHandlingEntity() {
		return failureModeHandlingEntity;
	}

	public void setFailureModeHandlingEntity(FailureModeHandlingEntity failureModeHandlingEntity) {
		this.failureModeHandlingEntity = failureModeHandlingEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<ServiceReportSpbuMachineTotalizerEntity> getServiceReportSpbuMachineTotalizerEntityList() {
		return serviceReportSpbuMachineTotalizerEntityList;
	}

	public void setServiceReportSpbuMachineTotalizerEntityList(List<ServiceReportSpbuMachineTotalizerEntity> serviceReportSpbuMachineTotalizerEntityList) {
		this.serviceReportSpbuMachineTotalizerEntityList = serviceReportSpbuMachineTotalizerEntityList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ServiceReportEntity)) {
			return false;
		}
		ServiceReportEntity other = (ServiceReportEntity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.ServiceReportEntity[ id=" + id + " ]";
	}

}

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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Entity
@Table(name = "service_reports")
@NamedQueries({
	@NamedQuery(name = "ServiceReportEntity.findAll", query = "SELECT s FROM ServiceReportEntity s")})
public class ServiceReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id", nullable = false)
	private Long id;
	@Basic(optional = false)
  @NotNull
  @Column(name = "date", nullable = false)
  @Temporal(TemporalType.DATE)
	private Date date;
	@JoinColumn(name = "machine_serial", referencedColumnName = "machine_serial", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuMachineEntity spbuMachineEntity;
	@JoinColumns({
  	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false),
  	@JoinColumn(name = "failure_mode_code", referencedColumnName = "failure_mode_code", nullable = false),
  	@JoinColumn(name = "failure_mode_handling_code", referencedColumnName = "failure_mode_handling_code", nullable = false)})
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private FailureModeHandlingEntity failureModeHandlingEntity;
	@JoinColumns({
  	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false),
  	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false),
  	@JoinColumn(name = "machine_model_part_identifier", referencedColumnName = "machine_model_part_identifier", nullable = false)})
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelPartEntity machineModelPartEntity;
	@JoinColumn(name = "technician_id", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEntity technicianEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceReportEntity", fetch = FetchType.LAZY)
	private List<ServiceReportSpbuMachineTotalizerEntity> serviceReportSpbuMachineTotalizerEntityList;

    private static final Logger LOG = LoggerFactory.getLogger(ServiceReportEntity.class);

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

	public MachineModelPartEntity getMachineModelPartEntity() {
		return machineModelPartEntity;
	}

	public void setMachineModelPartEntity(MachineModelPartEntity machineModelPartEntity) {
		this.machineModelPartEntity = machineModelPartEntity;
	}

	public UserEntity getTechnicianEntity() {
		return technicianEntity;
	}

	public void setTechnicianEntity(UserEntity technicianEntity) {
		this.technicianEntity = technicianEntity;
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

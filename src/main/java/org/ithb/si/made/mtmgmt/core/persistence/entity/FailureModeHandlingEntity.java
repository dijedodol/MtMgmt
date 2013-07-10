/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "failure_mode_handlings")
@NamedQueries({
	@NamedQuery(name = "FailureModeHandlingEntity.findAll", query = "SELECT f FROM FailureModeHandlingEntity f")})
public class FailureModeHandlingEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected FailureModeHandlingEntityPK failureModeHandlingEntityPK;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name", nullable = false, length = 255)
	private String name;
	@Basic(optional = false)
  @NotNull
  @Lob
  @Size(min = 1, max = 65535)
  @Column(name = "description", nullable = false, length = 65535)
	private String description;
	@JoinColumns({
  	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "failure_mode_code", referencedColumnName = "failure_mode_code", nullable = false, insertable = false, updatable = false)})
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PartFailureModeEntity partFailureModeEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "failureModeHandlingEntity", fetch = FetchType.LAZY)
	private List<ServiceReportEntity> serviceReportEntityList;

	public FailureModeHandlingEntity() {
	}

	public FailureModeHandlingEntity(FailureModeHandlingEntityPK failureModeHandlingEntityPK) {
		this.failureModeHandlingEntityPK = failureModeHandlingEntityPK;
	}

	public FailureModeHandlingEntity(FailureModeHandlingEntityPK failureModeHandlingEntityPK, String name, String description) {
		this.failureModeHandlingEntityPK = failureModeHandlingEntityPK;
		this.name = name;
		this.description = description;
	}

	public FailureModeHandlingEntity(String partId, String failureModeCode, String failureModeHandlingCode) {
		this.failureModeHandlingEntityPK = new FailureModeHandlingEntityPK(partId, failureModeCode, failureModeHandlingCode);
	}

	public FailureModeHandlingEntityPK getFailureModeHandlingEntityPK() {
		return failureModeHandlingEntityPK;
	}

	public void setFailureModeHandlingEntityPK(FailureModeHandlingEntityPK failureModeHandlingEntityPK) {
		this.failureModeHandlingEntityPK = failureModeHandlingEntityPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PartFailureModeEntity getPartFailureModeEntity() {
		return partFailureModeEntity;
	}

	public void setPartFailureModeEntity(PartFailureModeEntity partFailureModeEntity) {
		this.partFailureModeEntity = partFailureModeEntity;
	}

	public List<ServiceReportEntity> getServiceReportEntityList() {
		return serviceReportEntityList;
	}

	public void setServiceReportEntityList(List<ServiceReportEntity> serviceReportEntityList) {
		this.serviceReportEntityList = serviceReportEntityList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (failureModeHandlingEntityPK != null ? failureModeHandlingEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof FailureModeHandlingEntity)) {
			return false;
		}
		FailureModeHandlingEntity other = (FailureModeHandlingEntity) object;
		if ((this.failureModeHandlingEntityPK == null && other.failureModeHandlingEntityPK != null) || (this.failureModeHandlingEntityPK != null && !this.failureModeHandlingEntityPK.equals(other.failureModeHandlingEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntity[ failureModeHandlingEntityPK=" + failureModeHandlingEntityPK + " ]";
	}

}

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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id", nullable = false)
	private Long id;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name", nullable = false, length = 255)
	private String name;
	@JoinColumn(name = "failure_mode_id", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private PartFailureModeEntity partFailureModeEntity;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "failureModeHandlingEntity", fetch = FetchType.LAZY)
	private List<ServiceReportEntity> serviceReportEntityList;

	public FailureModeHandlingEntity() {
	}

	public FailureModeHandlingEntity(Long id) {
		this.id = id;
	}

	public FailureModeHandlingEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof FailureModeHandlingEntity)) {
			return false;
		}
		FailureModeHandlingEntity other = (FailureModeHandlingEntity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.FailureModeHandlingEntity[ id=" + id + " ]";
	}

}

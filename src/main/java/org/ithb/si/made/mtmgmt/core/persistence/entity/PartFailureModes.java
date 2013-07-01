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
@Table(name = "part_failure_modes")
@NamedQueries({
	@NamedQuery(name = "PartFailureModes.findAll", query = "SELECT p FROM PartFailureModes p")})
public class PartFailureModes implements Serializable {
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "partFailureModes", fetch = FetchType.LAZY)
	private List<FailureModeHandlings> failureModeHandlingsList;
	@JoinColumn(name = "part_id", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineParts machineParts;

	public PartFailureModes() {
	}

	public PartFailureModes(Long id) {
		this.id = id;
	}

	public PartFailureModes(Long id, String name) {
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

	public List<FailureModeHandlings> getFailureModeHandlingsList() {
		return failureModeHandlingsList;
	}

	public void setFailureModeHandlingsList(List<FailureModeHandlings> failureModeHandlingsList) {
		this.failureModeHandlingsList = failureModeHandlingsList;
	}

	public MachineParts getMachineParts() {
		return machineParts;
	}

	public void setMachineParts(MachineParts machineParts) {
		this.machineParts = machineParts;
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
		if (!(object instanceof PartFailureModes)) {
			return false;
		}
		PartFailureModes other = (PartFailureModes) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModes[ id=" + id + " ]";
	}

}

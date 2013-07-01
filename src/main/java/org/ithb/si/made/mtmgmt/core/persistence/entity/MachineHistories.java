/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "machine_histories")
@NamedQueries({
	@NamedQuery(name = "MachineHistories.findAll", query = "SELECT m FROM MachineHistories m")})
public class MachineHistories implements Serializable {
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
	@JoinColumn(name = "machine_id", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuMachines spbuMachines;
	@JoinColumn(name = "failure_mode_handling_id", referencedColumnName = "id", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private FailureModeHandlings failureModeHandlings;

	public MachineHistories() {
	}

	public MachineHistories(Long id) {
		this.id = id;
	}

	public MachineHistories(Long id, Date date) {
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

	public SpbuMachines getSpbuMachines() {
		return spbuMachines;
	}

	public void setSpbuMachines(SpbuMachines spbuMachines) {
		this.spbuMachines = spbuMachines;
	}

	public FailureModeHandlings getFailureModeHandlings() {
		return failureModeHandlings;
	}

	public void setFailureModeHandlings(FailureModeHandlings failureModeHandlings) {
		this.failureModeHandlings = failureModeHandlings;
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
		if (!(object instanceof MachineHistories)) {
			return false;
		}
		MachineHistories other = (MachineHistories) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineHistories[ id=" + id + " ]";
	}

}

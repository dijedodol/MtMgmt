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
public class MachineHistoryEntity implements Serializable {

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
	private SpbuMachineEntity spbuMachine;
	@JoinColumn(name = "failure_mode_handling_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private FailureModeHandlingEntity failureModeHandling;

	public MachineHistoryEntity() {
	}

	public MachineHistoryEntity(Long id) {
		this.id = id;
	}

	public MachineHistoryEntity(Long id, Date date) {
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

	public SpbuMachineEntity getSpbuMachine() {
		return spbuMachine;
	}

	public void setSpbuMachine(SpbuMachineEntity spbuMachine) {
		this.spbuMachine = spbuMachine;
	}

	public FailureModeHandlingEntity getFailureModeHandling() {
		return failureModeHandling;
	}

	public void setFailureModeHandling(FailureModeHandlingEntity failureModeHandling) {
		this.failureModeHandling = failureModeHandling;
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
		if (!(object instanceof MachineHistoryEntity)) {
			return false;
		}
		MachineHistoryEntity other = (MachineHistoryEntity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MachineHistoryEntity{" + "id=" + id + ", date=" + date + ", spbuMachine=" + spbuMachine + ", failureModeHandling=" + failureModeHandling + '}';
	}
}

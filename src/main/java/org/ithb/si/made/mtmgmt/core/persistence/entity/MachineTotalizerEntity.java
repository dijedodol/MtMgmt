/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Uyeee
 */
@Entity
@Table(name = "machine_totalizers")
public class MachineTotalizerEntity implements Serializable {

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
	@Basic(optional = false)
	@NotNull
	@Column(name = "total", nullable = false)
	private boolean total;
	@JoinColumn(name = "machine_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuMachineEntity spbuMachine;

	public MachineTotalizerEntity() {
	}

	public MachineTotalizerEntity(Long id) {
		this.id = id;
	}

	public MachineTotalizerEntity(Long id, String name, boolean total) {
		this.id = id;
		this.name = name;
		this.total = total;
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

	public boolean getTotal() {
		return total;
	}

	public void setTotal(boolean total) {
		this.total = total;
	}

	public SpbuMachineEntity getSpbuMachine() {
		return spbuMachine;
	}

	public void setSpbuMachine(SpbuMachineEntity spbuMachine) {
		this.spbuMachine = spbuMachine;
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
		if (!(object instanceof MachineTotalizerEntity)) {
			return false;
		}
		MachineTotalizerEntity other = (MachineTotalizerEntity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MachineTotalizerEntity{" + "id=" + id + ", name=" + name + ", total=" + total + ", spbuMachine=" + spbuMachine + '}';
	}
}

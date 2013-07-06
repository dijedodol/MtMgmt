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
@Table(name = "spbu_machine_totalizers")
@NamedQueries({
	@NamedQuery(name = "SpbuMachineTotalizerEntity.findAll", query = "SELECT s FROM SpbuMachineTotalizerEntity s")})
public class SpbuMachineTotalizerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK;
	@Basic(optional = false)
  @NotNull
  @Column(name = "counter", nullable = false)
	private long counter;
	@JoinColumns({
  	@JoinColumn(name = "spbu_id", referencedColumnName = "spbu_id", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "identifier", referencedColumnName = "identifier", nullable = false, insertable = false, updatable = false)})
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private SpbuMachineEntity spbuMachineEntity;
	@JoinColumn(name = "machine_totalizer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private MachineTotalizerEntity machineTotalizerEntity;

	public SpbuMachineTotalizerEntity() {
	}

	public SpbuMachineTotalizerEntity(SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK) {
		this.spbuMachineTotalizerEntityPK = spbuMachineTotalizerEntityPK;
	}

	public SpbuMachineTotalizerEntity(SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK, long counter) {
		this.spbuMachineTotalizerEntityPK = spbuMachineTotalizerEntityPK;
		this.counter = counter;
	}

	public SpbuMachineTotalizerEntity(long spbuId, String identifier, long machineTotalizerId) {
		this.spbuMachineTotalizerEntityPK = new SpbuMachineTotalizerEntityPK(spbuId, identifier, machineTotalizerId);
	}

	public SpbuMachineTotalizerEntityPK getSpbuMachineTotalizerEntityPK() {
		return spbuMachineTotalizerEntityPK;
	}

	public void setSpbuMachineTotalizerEntityPK(SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK) {
		this.spbuMachineTotalizerEntityPK = spbuMachineTotalizerEntityPK;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public SpbuMachineEntity getSpbuMachineEntity() {
		return spbuMachineEntity;
	}

	public void setSpbuMachineEntity(SpbuMachineEntity spbuMachineEntity) {
		this.spbuMachineEntity = spbuMachineEntity;
	}

	public MachineTotalizerEntity getMachineTotalizerEntity() {
		return machineTotalizerEntity;
	}

	public void setMachineTotalizerEntity(MachineTotalizerEntity machineTotalizerEntity) {
		this.machineTotalizerEntity = machineTotalizerEntity;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (spbuMachineTotalizerEntityPK != null ? spbuMachineTotalizerEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SpbuMachineTotalizerEntity)) {
			return false;
		}
		SpbuMachineTotalizerEntity other = (SpbuMachineTotalizerEntity) object;
		if ((this.spbuMachineTotalizerEntityPK == null && other.spbuMachineTotalizerEntityPK != null) || (this.spbuMachineTotalizerEntityPK != null && !this.spbuMachineTotalizerEntityPK.equals(other.spbuMachineTotalizerEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineTotalizerEntity[ spbuMachineTotalizerEntityPK=" + spbuMachineTotalizerEntityPK + " ]";
	}

}

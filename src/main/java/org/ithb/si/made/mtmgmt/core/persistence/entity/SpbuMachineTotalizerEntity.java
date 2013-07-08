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
import javax.validation.constraints.Size;

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
  @Size(min = 1, max = 40)
  @Column(name = "alias", nullable = false, length = 40)
	private String alias;
	@Basic(optional = false)
  @NotNull
  @Column(name = "counter", nullable = false)
	private double counter;
	@JoinColumns({
  	@JoinColumn(name = "spbu_id", referencedColumnName = "spbu_id", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "serial_number", referencedColumnName = "serial_number", nullable = false, insertable = false, updatable = false)})
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuMachineEntity spbuMachineEntity;
	@JoinColumns({
  	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "totalizer_id", referencedColumnName = "totalizer_id", nullable = false, insertable = false, updatable = false)})
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelTotalizerEntity machineModelTotalizerEntity;

	public SpbuMachineTotalizerEntity() {
	}

	public SpbuMachineTotalizerEntity(SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK) {
		this.spbuMachineTotalizerEntityPK = spbuMachineTotalizerEntityPK;
	}

	public SpbuMachineTotalizerEntity(SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK, String alias, double counter) {
		this.spbuMachineTotalizerEntityPK = spbuMachineTotalizerEntityPK;
		this.alias = alias;
		this.counter = counter;
	}

	public SpbuMachineTotalizerEntity(long spbuId, String modelId, String serialNumber, String totalizerId) {
		this.spbuMachineTotalizerEntityPK = new SpbuMachineTotalizerEntityPK(spbuId, modelId, serialNumber, totalizerId);
	}

	public SpbuMachineTotalizerEntityPK getSpbuMachineTotalizerEntityPK() {
		return spbuMachineTotalizerEntityPK;
	}

	public void setSpbuMachineTotalizerEntityPK(SpbuMachineTotalizerEntityPK spbuMachineTotalizerEntityPK) {
		this.spbuMachineTotalizerEntityPK = spbuMachineTotalizerEntityPK;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public double getCounter() {
		return counter;
	}

	public void setCounter(double counter) {
		this.counter = counter;
	}

	public SpbuMachineEntity getSpbuMachineEntity() {
		return spbuMachineEntity;
	}

	public void setSpbuMachineEntity(SpbuMachineEntity spbuMachineEntity) {
		this.spbuMachineEntity = spbuMachineEntity;
	}

	public MachineModelTotalizerEntity getMachineModelTotalizerEntity() {
		return machineModelTotalizerEntity;
	}

	public void setMachineModelTotalizerEntity(MachineModelTotalizerEntity machineModelTotalizerEntity) {
		this.machineModelTotalizerEntity = machineModelTotalizerEntity;
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

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Entity
@Table(name = "spbu_machine_part_mttfs")
@NamedQueries({
	@NamedQuery(name = "SpbuMachinePartMttfEntity.findAll", query = "SELECT s FROM SpbuMachinePartMttfEntity s")})
public class SpbuMachinePartMttfEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SpbuMachinePartMttfEntityPK spbuMachinePartMttfEntityPK;
	@Basic(optional = false)
  @NotNull
  @Column(name = "mttf", nullable = false)
	private double mttf;
	@Basic(optional = false)
  @NotNull
  @Column(name = "mttf_threshold", nullable = false)
	private double mttfThreshold;
	@JoinColumns({
  	@JoinColumn(name = "model_id", referencedColumnName = "model_id", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false, insertable = false, updatable = false),
  	@JoinColumn(name = "machine_model_part_identifier", referencedColumnName = "machine_model_part_identifier", nullable = false, insertable = false, updatable = false)})
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachineModelPartEntity machineModelPartEntity;
	@JoinColumn(name = "machine_serial", referencedColumnName = "machine_serial", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private SpbuMachineEntity spbuMachineEntity;

    private static final Logger LOG = LoggerFactory.getLogger(SpbuMachinePartMttfEntity.class);

	public SpbuMachinePartMttfEntity() {
	}

	public SpbuMachinePartMttfEntity(SpbuMachinePartMttfEntityPK spbuMachinePartMttfEntityPK) {
		this.spbuMachinePartMttfEntityPK = spbuMachinePartMttfEntityPK;
	}

	public SpbuMachinePartMttfEntity(SpbuMachinePartMttfEntityPK spbuMachinePartMttfEntityPK, double mttf, double mttfThreshold) {
		this.spbuMachinePartMttfEntityPK = spbuMachinePartMttfEntityPK;
		this.mttf = mttf;
		this.mttfThreshold = mttfThreshold;
	}

	public SpbuMachinePartMttfEntity(String machineSerial, String modelId, String partId, String machineModelPartIdentifier) {
		this.spbuMachinePartMttfEntityPK = new SpbuMachinePartMttfEntityPK(machineSerial, modelId, partId, machineModelPartIdentifier);
	}

	public SpbuMachinePartMttfEntityPK getSpbuMachinePartMttfEntityPK() {
		return spbuMachinePartMttfEntityPK;
	}

	public void setSpbuMachinePartMttfEntityPK(SpbuMachinePartMttfEntityPK spbuMachinePartMttfEntityPK) {
		this.spbuMachinePartMttfEntityPK = spbuMachinePartMttfEntityPK;
	}

	public double getMttf() {
		return mttf;
	}

	public void setMttf(double mttf) {
		this.mttf = mttf;
	}

	public double getMttfThreshold() {
		return mttfThreshold;
	}

	public void setMttfThreshold(double mttfThreshold) {
		this.mttfThreshold = mttfThreshold;
	}

	public MachineModelPartEntity getMachineModelPartEntity() {
		return machineModelPartEntity;
	}

	public void setMachineModelPartEntity(MachineModelPartEntity machineModelPartEntity) {
		this.machineModelPartEntity = machineModelPartEntity;
	}

	public SpbuMachineEntity getSpbuMachineEntity() {
		return spbuMachineEntity;
	}

	public void setSpbuMachineEntity(SpbuMachineEntity spbuMachineEntity) {
		this.spbuMachineEntity = spbuMachineEntity;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (spbuMachinePartMttfEntityPK != null ? spbuMachinePartMttfEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SpbuMachinePartMttfEntity)) {
			return false;
		}
		SpbuMachinePartMttfEntity other = (SpbuMachinePartMttfEntity) object;
		if ((this.spbuMachinePartMttfEntityPK == null && other.spbuMachinePartMttfEntityPK != null) || (this.spbuMachinePartMttfEntityPK != null && !this.spbuMachinePartMttfEntityPK.equals(other.spbuMachinePartMttfEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachinePartMttfEntity[ spbuMachinePartMttfEntityPK=" + spbuMachinePartMttfEntityPK + " ]";
	}
}

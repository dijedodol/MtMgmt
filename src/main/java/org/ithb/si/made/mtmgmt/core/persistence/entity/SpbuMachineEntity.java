/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Entity
@Table(name = "spbu_machines")
@NamedQueries({
	@NamedQuery(name = "SpbuMachineEntity.findAll", query = "SELECT s FROM SpbuMachineEntity s")})
public class SpbuMachineEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SpbuMachineEntityPK spbuMachineEntityPK;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	@ManyToMany(mappedBy = "spbuMachineEntityList", fetch = FetchType.LAZY)
	private List<MachineModelTotalizerEntity> machineModelTotalizerEntityList;
	@JoinColumn(name = "spbu_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private SpbuEntity spbuEntity;
	@JoinColumn(name = "machine_model_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private MachineModelEntity machineModelEntity;
	private static final Logger LOG = LoggerFactory.getLogger(SpbuMachineEntity.class);

	public SpbuMachineEntity() {
	}

	public SpbuMachineEntity(SpbuMachineEntityPK spbuMachineEntityPK) {
		this.spbuMachineEntityPK = spbuMachineEntityPK;
	}

	public SpbuMachineEntity(SpbuMachineEntityPK spbuMachineEntityPK, String name) {
		this.spbuMachineEntityPK = spbuMachineEntityPK;
		this.name = name;
	}

	public SpbuMachineEntity(long spbuId, long machineModelId) {
		this.spbuMachineEntityPK = new SpbuMachineEntityPK(spbuId, machineModelId);
	}

	public SpbuMachineEntityPK getSpbuMachineEntityPK() {
		return spbuMachineEntityPK;
	}

	public void setSpbuMachineEntityPK(SpbuMachineEntityPK spbuMachineEntityPK) {
		this.spbuMachineEntityPK = spbuMachineEntityPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MachineModelTotalizerEntity> getMachineModelTotalizerEntityList() {
		return machineModelTotalizerEntityList;
	}

	public void setMachineModelTotalizerEntityList(List<MachineModelTotalizerEntity> machineModelTotalizerEntityList) {
		this.machineModelTotalizerEntityList = machineModelTotalizerEntityList;
	}

	public SpbuEntity getSpbuEntity() {
		return spbuEntity;
	}

	public void setSpbuEntity(SpbuEntity spbuEntity) {
		this.spbuEntity = spbuEntity;
	}

	public MachineModelEntity getMachineModelEntity() {
		return machineModelEntity;
	}

	public void setMachineModelEntity(MachineModelEntity machineModelEntity) {
		this.machineModelEntity = machineModelEntity;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (spbuMachineEntityPK != null ? spbuMachineEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SpbuMachineEntity)) {
			return false;
		}
		SpbuMachineEntity other = (SpbuMachineEntity) object;
		if ((this.spbuMachineEntityPK == null && other.spbuMachineEntityPK != null) || (this.spbuMachineEntityPK != null && !this.spbuMachineEntityPK.equals(other.spbuMachineEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntity[ spbuMachineEntityPK=" + spbuMachineEntityPK + " ]";
	}
}

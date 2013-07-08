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
@Table(name = "part_failure_modes")
@NamedQueries({
	@NamedQuery(name = "PartFailureModeEntity.findAll", query = "SELECT p FROM PartFailureModeEntity p")})
public class PartFailureModeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected PartFailureModeEntityPK partFailureModeEntityPK;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "partFailureModeEntity", fetch = FetchType.LAZY)
	private List<FailureModeHandlingEntity> failureModeHandlingEntityList;
	@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private MachinePartTypeEntity machinePartTypeEntity;

	public PartFailureModeEntity() {
	}

	public PartFailureModeEntity(PartFailureModeEntityPK partFailureModeEntityPK) {
		this.partFailureModeEntityPK = partFailureModeEntityPK;
	}

	public PartFailureModeEntity(PartFailureModeEntityPK partFailureModeEntityPK, String name, String description) {
		this.partFailureModeEntityPK = partFailureModeEntityPK;
		this.name = name;
		this.description = description;
	}

	public PartFailureModeEntity(String partId, String failureModeCode) {
		this.partFailureModeEntityPK = new PartFailureModeEntityPK(partId, failureModeCode);
	}

	public PartFailureModeEntityPK getPartFailureModeEntityPK() {
		return partFailureModeEntityPK;
	}

	public void setPartFailureModeEntityPK(PartFailureModeEntityPK partFailureModeEntityPK) {
		this.partFailureModeEntityPK = partFailureModeEntityPK;
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

	public List<FailureModeHandlingEntity> getFailureModeHandlingEntityList() {
		return failureModeHandlingEntityList;
	}

	public void setFailureModeHandlingEntityList(List<FailureModeHandlingEntity> failureModeHandlingEntityList) {
		this.failureModeHandlingEntityList = failureModeHandlingEntityList;
	}

	public MachinePartTypeEntity getMachinePartTypeEntity() {
		return machinePartTypeEntity;
	}

	public void setMachinePartTypeEntity(MachinePartTypeEntity machinePartTypeEntity) {
		this.machinePartTypeEntity = machinePartTypeEntity;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (partFailureModeEntityPK != null ? partFailureModeEntityPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof PartFailureModeEntity)) {
			return false;
		}
		PartFailureModeEntity other = (PartFailureModeEntity) object;
		if ((this.partFailureModeEntityPK == null && other.partFailureModeEntityPK != null) || (this.partFailureModeEntityPK != null && !this.partFailureModeEntityPK.equals(other.partFailureModeEntityPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntity[ partFailureModeEntityPK=" + partFailureModeEntityPK + " ]";
	}

}

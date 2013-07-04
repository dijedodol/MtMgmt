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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "machine_parts")
@NamedQueries({
	@NamedQuery(name = "MachinePartEntity.findAll", query = "SELECT m FROM MachinePartEntity m")})
public class MachinePartEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "code", nullable = false, length = 20)
	private String code;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machinePartEntity", fetch = FetchType.LAZY)
	private List<MachineModelPartEntity> machineModelPartEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machinePartEntity", fetch = FetchType.LAZY)
	private List<PartFailureModeEntity> partFailureModeEntityList;
	private static final Logger LOG = LoggerFactory.getLogger(MachinePartEntity.class);

	public MachinePartEntity() {
	}

	public MachinePartEntity(Long id) {
		this.id = id;
	}

	public MachinePartEntity(Long id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MachineModelPartEntity> getMachineModelPartEntityList() {
		return machineModelPartEntityList;
	}

	public void setMachineModelPartEntityList(List<MachineModelPartEntity> machineModelPartEntityList) {
		this.machineModelPartEntityList = machineModelPartEntityList;
	}

	public List<PartFailureModeEntity> getPartFailureModeEntityList() {
		return partFailureModeEntityList;
	}

	public void setPartFailureModeEntityList(List<PartFailureModeEntity> partFailureModeEntityList) {
		this.partFailureModeEntityList = partFailureModeEntityList;
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
		if (!(object instanceof MachinePartEntity)) {
			return false;
		}
		MachinePartEntity other = (MachinePartEntity) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachinePartEntity[ id=" + id + " ]";
	}
}

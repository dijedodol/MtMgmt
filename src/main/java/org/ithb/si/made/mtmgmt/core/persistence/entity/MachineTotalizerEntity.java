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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "machine_totalizers")
@NamedQueries({
	@NamedQuery(name = "MachineTotalizerEntity.findAll", query = "SELECT m FROM MachineTotalizerEntity m")})
public class MachineTotalizerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id", nullable = false)
	private Long id;
	@Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "name", nullable = false, length = 20)
	private String name;
	@JoinTable(name = "machine_model_totalizers", joinColumns = {
  	@JoinColumn(name = "machine_totalizer_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
  	@JoinColumn(name = "machine_model_id", referencedColumnName = "id", nullable = false)})
  @ManyToMany(fetch = FetchType.LAZY)
	private List<MachineModelEntity> machineModelEntityList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "machineTotalizerEntity", fetch = FetchType.LAZY)
	private List<SpbuMachineTotalizerEntity> spbuMachineTotalizerEntityList;

	public MachineTotalizerEntity() {
	}

	public MachineTotalizerEntity(Long id) {
		this.id = id;
	}

	public MachineTotalizerEntity(Long id, String name) {
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

	public List<MachineModelEntity> getMachineModelEntityList() {
		return machineModelEntityList;
	}

	public void setMachineModelEntityList(List<MachineModelEntity> machineModelEntityList) {
		this.machineModelEntityList = machineModelEntityList;
	}

	public List<SpbuMachineTotalizerEntity> getSpbuMachineTotalizerEntityList() {
		return spbuMachineTotalizerEntityList;
	}

	public void setSpbuMachineTotalizerEntityList(List<SpbuMachineTotalizerEntity> spbuMachineTotalizerEntityList) {
		this.spbuMachineTotalizerEntityList = spbuMachineTotalizerEntityList;
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
		return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineTotalizerEntity[ id=" + id + " ]";
	}

}

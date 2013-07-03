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
@Table(name = "machine_totalizers")
@NamedQueries({
    @NamedQuery(name = "MachineTotalizerEntity.findAll", query = "SELECT m FROM MachineTotalizerEntity m")})
public class MachineTotalizerEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machineTotalizerEntity", fetch = FetchType.EAGER)
    private List<MachineModelTotalizerEntity> machineModelTotalizerEntityList;

    private static final Logger LOG = LoggerFactory.getLogger(MachineTotalizerEntity.class);

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

    public List<MachineModelTotalizerEntity> getMachineModelTotalizerEntityList() {
        return machineModelTotalizerEntityList;
    }

    public void setMachineModelTotalizerEntityList(List<MachineModelTotalizerEntity> machineModelTotalizerEntityList) {
        this.machineModelTotalizerEntityList = machineModelTotalizerEntityList;
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

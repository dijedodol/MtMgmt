/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Entity
@Table(name = "machine_model_totalizers")
@NamedQueries({
    @NamedQuery(name = "MachineModelTotalizerEntity.findAll", query = "SELECT m FROM MachineModelTotalizerEntity m")})
public class MachineModelTotalizerEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MachineModelTotalizerEntityPK machineModelTotalizerEntityPK;
    @JoinTable(name = "spbu_machine_totalizers", joinColumns = {
        @JoinColumn(name = "machine_model_id", referencedColumnName = "machine_model_id", nullable = false),
        @JoinColumn(name = "machine_totalizer_id", referencedColumnName = "machine_totalizer_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "spbu_id", referencedColumnName = "spbu_id", nullable = false),
        @JoinColumn(name = "machine_model_id", referencedColumnName = "machine_model_id", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<SpbuMachineEntity> spbuMachineEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelTotalizerEntity", fetch = FetchType.EAGER)
    private List<MachineModelPartTotalizerEntity> machineModelPartTotalizerEntityList;
    @JoinColumn(name = "machine_model_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachineModelEntity machineModelEntity;
    @JoinColumn(name = "machine_totalizer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachineTotalizerEntity machineTotalizerEntity;

    private static final Logger LOG = LoggerFactory.getLogger(MachineModelTotalizerEntity.class);

    public MachineModelTotalizerEntity() {
    }

    public MachineModelTotalizerEntity(MachineModelTotalizerEntityPK machineModelTotalizerEntityPK) {
        this.machineModelTotalizerEntityPK = machineModelTotalizerEntityPK;
    }

    public MachineModelTotalizerEntity(long machineModelId, long machineTotalizerId) {
        this.machineModelTotalizerEntityPK = new MachineModelTotalizerEntityPK(machineModelId, machineTotalizerId);
    }

    public MachineModelTotalizerEntityPK getMachineModelTotalizerEntityPK() {
        return machineModelTotalizerEntityPK;
    }

    public void setMachineModelTotalizerEntityPK(MachineModelTotalizerEntityPK machineModelTotalizerEntityPK) {
        this.machineModelTotalizerEntityPK = machineModelTotalizerEntityPK;
    }

    public List<SpbuMachineEntity> getSpbuMachineEntityList() {
        return spbuMachineEntityList;
    }

    public void setSpbuMachineEntityList(List<SpbuMachineEntity> spbuMachineEntityList) {
        this.spbuMachineEntityList = spbuMachineEntityList;
    }

    public List<MachineModelPartTotalizerEntity> getMachineModelPartTotalizerEntityList() {
        return machineModelPartTotalizerEntityList;
    }

    public void setMachineModelPartTotalizerEntityList(List<MachineModelPartTotalizerEntity> machineModelPartTotalizerEntityList) {
        this.machineModelPartTotalizerEntityList = machineModelPartTotalizerEntityList;
    }

    public MachineModelEntity getMachineModelEntity() {
        return machineModelEntity;
    }

    public void setMachineModelEntity(MachineModelEntity machineModelEntity) {
        this.machineModelEntity = machineModelEntity;
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
        hash += (machineModelTotalizerEntityPK != null ? machineModelTotalizerEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MachineModelTotalizerEntity)) {
            return false;
        }
        MachineModelTotalizerEntity other = (MachineModelTotalizerEntity) object;
        if ((this.machineModelTotalizerEntityPK == null && other.machineModelTotalizerEntityPK != null) || (this.machineModelTotalizerEntityPK != null && !this.machineModelTotalizerEntityPK.equals(other.machineModelTotalizerEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelTotalizerEntity[ machineModelTotalizerEntityPK=" + machineModelTotalizerEntityPK + " ]";
    }
}

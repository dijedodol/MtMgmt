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
@Table(name = "machine_model_parts")
@NamedQueries({
    @NamedQuery(name = "MachineModelPartEntity.findAll", query = "SELECT m FROM MachineModelPartEntity m")})
public class MachineModelPartEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MachineModelPartEntityPK machineModelPartEntityPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelPartEntity", fetch = FetchType.EAGER)
    private List<MachineModelPartTotalizerEntity> machineModelPartTotalizerEntityList;
    @JoinColumn(name = "part_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachinePartEntity machinePartEntity;
    @JoinColumn(name = "machine_model_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachineModelEntity machineModelEntity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelPartEntity", fetch = FetchType.EAGER)
    private List<MachinePartFailureHistoryEntity> machinePartFailureHistoryEntityList;

    private static final Logger LOG = LoggerFactory.getLogger(MachineModelPartEntity.class);

    public MachineModelPartEntity() {
    }

    public MachineModelPartEntity(MachineModelPartEntityPK machineModelPartEntityPK) {
        this.machineModelPartEntityPK = machineModelPartEntityPK;
    }

    public MachineModelPartEntity(long machineModelId, long partId) {
        this.machineModelPartEntityPK = new MachineModelPartEntityPK(machineModelId, partId);
    }

    public MachineModelPartEntityPK getMachineModelPartEntityPK() {
        return machineModelPartEntityPK;
    }

    public void setMachineModelPartEntityPK(MachineModelPartEntityPK machineModelPartEntityPK) {
        this.machineModelPartEntityPK = machineModelPartEntityPK;
    }

    public List<MachineModelPartTotalizerEntity> getMachineModelPartTotalizerEntityList() {
        return machineModelPartTotalizerEntityList;
    }

    public void setMachineModelPartTotalizerEntityList(List<MachineModelPartTotalizerEntity> machineModelPartTotalizerEntityList) {
        this.machineModelPartTotalizerEntityList = machineModelPartTotalizerEntityList;
    }

    public MachinePartEntity getMachinePartEntity() {
        return machinePartEntity;
    }

    public void setMachinePartEntity(MachinePartEntity machinePartEntity) {
        this.machinePartEntity = machinePartEntity;
    }

    public MachineModelEntity getMachineModelEntity() {
        return machineModelEntity;
    }

    public void setMachineModelEntity(MachineModelEntity machineModelEntity) {
        this.machineModelEntity = machineModelEntity;
    }

    public List<MachinePartFailureHistoryEntity> getMachinePartFailureHistoryEntityList() {
        return machinePartFailureHistoryEntityList;
    }

    public void setMachinePartFailureHistoryEntityList(List<MachinePartFailureHistoryEntity> machinePartFailureHistoryEntityList) {
        this.machinePartFailureHistoryEntityList = machinePartFailureHistoryEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (machineModelPartEntityPK != null ? machineModelPartEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MachineModelPartEntity)) {
            return false;
        }
        MachineModelPartEntity other = (MachineModelPartEntity) object;
        if ((this.machineModelPartEntityPK == null && other.machineModelPartEntityPK != null) || (this.machineModelPartEntityPK != null && !this.machineModelPartEntityPK.equals(other.machineModelPartEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartEntity[ machineModelPartEntityPK=" + machineModelPartEntityPK + " ]";
    }
}

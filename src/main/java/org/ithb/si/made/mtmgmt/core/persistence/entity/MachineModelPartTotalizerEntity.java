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
import javax.persistence.JoinColumns;
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
@Table(name = "machine_model_part_totalizers")
@NamedQueries({
    @NamedQuery(name = "MachineModelPartTotalizerEntity.findAll", query = "SELECT m FROM MachineModelPartTotalizerEntity m")})
public class MachineModelPartTotalizerEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MachineModelPartTotalizerEntityPK machineModelPartTotalizerEntityPK;
    @JoinColumns({
        @JoinColumn(name = "machine_model_id", referencedColumnName = "machine_model_id", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachineModelPartEntity machineModelPartEntity;
    @JoinColumns({
        @JoinColumn(name = "machine_model_id", referencedColumnName = "machine_model_id", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "machine_totalizer_id", referencedColumnName = "machine_totalizer_id", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachineModelTotalizerEntity machineModelTotalizerEntity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machineModelPartTotalizerEntity", fetch = FetchType.EAGER)
    private List<MachinePartFailureHistoryEntity> machinePartFailureHistoryEntityList;

    private static final Logger LOG = LoggerFactory.getLogger(MachineModelPartTotalizerEntity.class);

    public MachineModelPartTotalizerEntity() {
    }

    public MachineModelPartTotalizerEntity(MachineModelPartTotalizerEntityPK machineModelPartTotalizerEntityPK) {
        this.machineModelPartTotalizerEntityPK = machineModelPartTotalizerEntityPK;
    }

    public MachineModelPartTotalizerEntity(long machineModelId, long partId, long machineTotalizerId) {
        this.machineModelPartTotalizerEntityPK = new MachineModelPartTotalizerEntityPK(machineModelId, partId, machineTotalizerId);
    }

    public MachineModelPartTotalizerEntityPK getMachineModelPartTotalizerEntityPK() {
        return machineModelPartTotalizerEntityPK;
    }

    public void setMachineModelPartTotalizerEntityPK(MachineModelPartTotalizerEntityPK machineModelPartTotalizerEntityPK) {
        this.machineModelPartTotalizerEntityPK = machineModelPartTotalizerEntityPK;
    }

    public MachineModelPartEntity getMachineModelPartEntity() {
        return machineModelPartEntity;
    }

    public void setMachineModelPartEntity(MachineModelPartEntity machineModelPartEntity) {
        this.machineModelPartEntity = machineModelPartEntity;
    }

    public MachineModelTotalizerEntity getMachineModelTotalizerEntity() {
        return machineModelTotalizerEntity;
    }

    public void setMachineModelTotalizerEntity(MachineModelTotalizerEntity machineModelTotalizerEntity) {
        this.machineModelTotalizerEntity = machineModelTotalizerEntity;
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
        hash += (machineModelPartTotalizerEntityPK != null ? machineModelPartTotalizerEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MachineModelPartTotalizerEntity)) {
            return false;
        }
        MachineModelPartTotalizerEntity other = (MachineModelPartTotalizerEntity) object;
        if ((this.machineModelPartTotalizerEntityPK == null && other.machineModelPartTotalizerEntityPK != null) || (this.machineModelPartTotalizerEntityPK != null && !this.machineModelPartTotalizerEntityPK.equals(other.machineModelPartTotalizerEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartTotalizerEntity[ machineModelPartTotalizerEntityPK=" + machineModelPartTotalizerEntityPK + " ]";
    }
}

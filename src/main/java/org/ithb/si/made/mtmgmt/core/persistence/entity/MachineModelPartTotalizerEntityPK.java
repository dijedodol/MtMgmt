/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Embeddable
public class MachineModelPartTotalizerEntityPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "machine_model_id", nullable = false)
    private long machineModelId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "part_id", nullable = false)
    private long partId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "machine_totalizer_id", nullable = false)
    private long machineTotalizerId;

    private static final Logger LOG = LoggerFactory.getLogger(MachineModelPartTotalizerEntityPK.class);

    public MachineModelPartTotalizerEntityPK() {
    }

    public MachineModelPartTotalizerEntityPK(long machineModelId, long partId, long machineTotalizerId) {
        this.machineModelId = machineModelId;
        this.partId = partId;
        this.machineTotalizerId = machineTotalizerId;
    }

    public long getMachineModelId() {
        return machineModelId;
    }

    public void setMachineModelId(long machineModelId) {
        this.machineModelId = machineModelId;
    }

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public long getMachineTotalizerId() {
        return machineTotalizerId;
    }

    public void setMachineTotalizerId(long machineTotalizerId) {
        this.machineTotalizerId = machineTotalizerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) machineModelId;
        hash += (int) partId;
        hash += (int) machineTotalizerId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MachineModelPartTotalizerEntityPK)) {
            return false;
        }
        MachineModelPartTotalizerEntityPK other = (MachineModelPartTotalizerEntityPK) object;
        if (this.machineModelId != other.machineModelId) {
            return false;
        }
        if (this.partId != other.partId) {
            return false;
        }
        if (this.machineTotalizerId != other.machineTotalizerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachineModelPartTotalizerEntityPK[ machineModelId=" + machineModelId + ", partId=" + partId + ", machineTotalizerId=" + machineTotalizerId + " ]";
    }
}

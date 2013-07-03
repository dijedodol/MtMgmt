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
public class SpbuMachineEntityPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "spbu_id", nullable = false)
    private long spbuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "machine_model_id", nullable = false)
    private long machineModelId;

    private static final Logger LOG = LoggerFactory.getLogger(SpbuMachineEntityPK.class);

    public SpbuMachineEntityPK() {
    }

    public SpbuMachineEntityPK(long spbuId, long machineModelId) {
        this.spbuId = spbuId;
        this.machineModelId = machineModelId;
    }

    public long getSpbuId() {
        return spbuId;
    }

    public void setSpbuId(long spbuId) {
        this.spbuId = spbuId;
    }

    public long getMachineModelId() {
        return machineModelId;
    }

    public void setMachineModelId(long machineModelId) {
        this.machineModelId = machineModelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) spbuId;
        hash += (int) machineModelId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpbuMachineEntityPK)) {
            return false;
        }
        SpbuMachineEntityPK other = (SpbuMachineEntityPK) object;
        if (this.spbuId != other.spbuId) {
            return false;
        }
        if (this.machineModelId != other.machineModelId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ithb.si.made.mtmgmt.core.persistence.entity.SpbuMachineEntityPK[ spbuId=" + spbuId + ", machineModelId=" + machineModelId + " ]";
    }
}

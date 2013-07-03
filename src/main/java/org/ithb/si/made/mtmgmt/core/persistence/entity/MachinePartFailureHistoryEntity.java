/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ithb.si.made.mtmgmt.core.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gde.satrigraha
 */
@Entity
@Table(name = "machine_part_failure_histories")
@NamedQueries({
    @NamedQuery(name = "MachinePartFailureHistoryEntity.findAll", query = "SELECT m FROM MachinePartFailureHistoryEntity m")})
public class MachinePartFailureHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalizer_counter", nullable = false)
    private long totalizerCounter;
    @JoinColumns({
        @JoinColumn(name = "machine_model_id", referencedColumnName = "machine_model_id", nullable = false),
        @JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachineModelPartEntity machineModelPartEntity;
    @JoinColumns({
        @JoinColumn(name = "spbu_id", referencedColumnName = "spbu_id", nullable = false),
        @JoinColumn(name = "machine_model_id", referencedColumnName = "machine_model_id", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private SpbuMachineEntity spbuMachineEntity;
    @JoinColumns({
        @JoinColumn(name = "machine_model_id", referencedColumnName = "machine_model_id", nullable = false),
        @JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false),
        @JoinColumn(name = "machine_totalizer_id", referencedColumnName = "machine_totalizer_id")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachineModelPartTotalizerEntity machineModelPartTotalizerEntity;

    private static final Logger LOG = LoggerFactory.getLogger(MachinePartFailureHistoryEntity.class);

    public MachinePartFailureHistoryEntity() {
    }

    public MachinePartFailureHistoryEntity(Long id) {
        this.id = id;
    }

    public MachinePartFailureHistoryEntity(Long id, Date date, long totalizerCounter) {
        this.id = id;
        this.date = date;
        this.totalizerCounter = totalizerCounter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTotalizerCounter() {
        return totalizerCounter;
    }

    public void setTotalizerCounter(long totalizerCounter) {
        this.totalizerCounter = totalizerCounter;
    }

    public MachineModelPartEntity getMachineModelPartEntity() {
        return machineModelPartEntity;
    }

    public void setMachineModelPartEntity(MachineModelPartEntity machineModelPartEntity) {
        this.machineModelPartEntity = machineModelPartEntity;
    }

    public SpbuMachineEntity getSpbuMachineEntity() {
        return spbuMachineEntity;
    }

    public void setSpbuMachineEntity(SpbuMachineEntity spbuMachineEntity) {
        this.spbuMachineEntity = spbuMachineEntity;
    }

    public MachineModelPartTotalizerEntity getMachineModelPartTotalizerEntity() {
        return machineModelPartTotalizerEntity;
    }

    public void setMachineModelPartTotalizerEntity(MachineModelPartTotalizerEntity machineModelPartTotalizerEntity) {
        this.machineModelPartTotalizerEntity = machineModelPartTotalizerEntity;
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
        if (!(object instanceof MachinePartFailureHistoryEntity)) {
            return false;
        }
        MachinePartFailureHistoryEntity other = (MachinePartFailureHistoryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ithb.si.made.mtmgmt.core.persistence.entity.MachinePartFailureHistoryEntity[ id=" + id + " ]";
    }
}

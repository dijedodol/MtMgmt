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
import javax.persistence.ManyToOne;
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
@Table(name = "part_failure_modes")
@NamedQueries({
    @NamedQuery(name = "PartFailureModeEntity.findAll", query = "SELECT p FROM PartFailureModeEntity p")})
public class PartFailureModeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partFailureModeEntity", fetch = FetchType.EAGER)
    private List<FailureModeHandlingEntity> failureModeHandlingEntityList;
    @JoinColumn(name = "part_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MachinePartEntity machinePartEntity;

    private static final Logger LOG = LoggerFactory.getLogger(PartFailureModeEntity.class);

    public PartFailureModeEntity() {
    }

    public PartFailureModeEntity(Long id) {
        this.id = id;
    }

    public PartFailureModeEntity(Long id, String name) {
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

    public List<FailureModeHandlingEntity> getFailureModeHandlingEntityList() {
        return failureModeHandlingEntityList;
    }

    public void setFailureModeHandlingEntityList(List<FailureModeHandlingEntity> failureModeHandlingEntityList) {
        this.failureModeHandlingEntityList = failureModeHandlingEntityList;
    }

    public MachinePartEntity getMachinePartEntity() {
        return machinePartEntity;
    }

    public void setMachinePartEntity(MachinePartEntity machinePartEntity) {
        this.machinePartEntity = machinePartEntity;
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
        if (!(object instanceof PartFailureModeEntity)) {
            return false;
        }
        PartFailureModeEntity other = (PartFailureModeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ithb.si.made.mtmgmt.core.persistence.entity.PartFailureModeEntity[ id=" + id + " ]";
    }
}

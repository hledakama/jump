/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.order;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity
@Table(name = "POSTPONE_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postpone.findAll", query = "SELECT p FROM Postpone p")
    , @NamedQuery(name = "Postpone.findByPostponeTId", query = "SELECT p FROM Postpone p WHERE p.postponeTId = :postponeTId")
    , @NamedQuery(name = "Postpone.findByCreation", query = "SELECT p FROM Postpone p WHERE p.creation = :creation")
    , @NamedQuery(name = "Postpone.findByStart", query = "SELECT p FROM Postpone p WHERE p.start = :start")
    , @NamedQuery(name = "Postpone.findByEnd", query = "SELECT p FROM Postpone p WHERE p.end = :end")
    , @NamedQuery(name = "Postpone.findByAddress", query = "SELECT p FROM Postpone p WHERE p.address = :address")
    , @NamedQuery(name = "Postpone.findByReason", query = "SELECT p FROM Postpone p WHERE p.reason = :reason")
    , @NamedQuery(name = "Postpone.findByInitiatedBy", query = "SELECT p FROM Postpone p WHERE p.initiatedBy = :initiatedBy")
    , @NamedQuery(name = "Postpone.findByStatus", query = "SELECT p FROM Postpone p WHERE p.status = :status")})
public class Postpone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_postpone", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "POSTPONE_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_postpone" ) 
    @Column(name = "POSTPONE_T_ID")
    private Long postponeTId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;
    @Size(max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 255)
    @Column(name = "REASON")
    private String reason;
    @Column(name = "INITIATED_BY")
    private Short initiatedBy;
    @Column(name = "STATUS")
    private Short status;
    @JoinColumn(name = "APPOINTMENT_FK", referencedColumnName = "APPOINTMENT_T_ID")
    @ManyToOne
    private Appointment appointmentFk;

    public Postpone() {
    }

    public Postpone(Long postponeTId) {
        this.postponeTId = postponeTId;
    }

    public Postpone(Long postponeTId, Date creation, Date start, Date end) {
        this.postponeTId = postponeTId;
        this.creation = creation;
        this.start = start;
        this.end = end;
    }

    public Long getPostponeTId() {
        return postponeTId;
    }

    public void setPostponeTId(Long postponeTId) {
        this.postponeTId = postponeTId;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Short getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(Short initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Appointment getAppointmentFk() {
        return appointmentFk;
    }

    public void setAppointmentFk(Appointment appointmentFk) {
        this.appointmentFk = appointmentFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postponeTId != null ? postponeTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postpone)) {
            return false;
        }
        Postpone other = (Postpone) object;
        if ((this.postponeTId == null && other.postponeTId != null) || (this.postponeTId != null && !this.postponeTId.equals(other.postponeTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.order.Postpone[ postponeTId=" + postponeTId + " ]";
    }
    
}

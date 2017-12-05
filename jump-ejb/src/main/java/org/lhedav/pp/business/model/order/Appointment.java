/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Itemdata;

/**
 *
 * @author client
 */
@Entity
@Table(name = "APPOINTMENT_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
    , @NamedQuery(name = "Appointment.findByAppointmentTId", query = "SELECT a FROM Appointment a WHERE a.appointmentTId = :appointmentTId")
    , @NamedQuery(name = "Appointment.findByCreation", query = "SELECT a FROM Appointment a WHERE a.creation = :creation")
    , @NamedQuery(name = "Appointment.findByStart", query = "SELECT a FROM Appointment a WHERE a.start = :start")
    , @NamedQuery(name = "Appointment.findByEnd", query = "SELECT a FROM Appointment a WHERE a.end = :end")
    , @NamedQuery(name = "Appointment.findByAddress", query = "SELECT a FROM Appointment a WHERE a.address = :address")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_appointment", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "APPOINTMENT_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_appointment" ) 
    @Column(name = "APPOINTMENT_T_ID")
    private Long appointmentTId;
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
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "appointmentFk")
    private List<Postpone> postponeList;
    @JoinColumn(name = "ORDER_FK", referencedColumnName = "ORDER_T_ID")
    @ManyToOne
    private Order_ orderFk;
    @JoinColumn(name = "ORDER_LINE_FK", referencedColumnName = "ORDER_LINE_T_ID")
    @ManyToOne
    private OrderLine orderLineFk;

    public Appointment() {
        postponeList = new ArrayList();
    }

    public Appointment(Long appointmentTId) {
        this.appointmentTId = appointmentTId;
        postponeList = new ArrayList();
    }

    public Appointment(Long appointmentTId, Date creation, Date start, Date end) {
        this.appointmentTId = appointmentTId;
        this.creation = creation;
        this.start = start;
        this.end = end;
        postponeList = new ArrayList();
    }

    public Long getAppointmentTId() {
        return appointmentTId;
    }

    public void setAppointmentTId(Long appointmentTId) {
        this.appointmentTId = appointmentTId;
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

    @XmlTransient
    public List<Postpone> getPostponeList() {
        return postponeList;
    }

    public void setPostponeList(List<Postpone> postponeList) {
        this.postponeList = postponeList;
    }
    
    public void addPostponeToList(Postpone aPostpone) {
        if (!postponeList.contains(aPostpone)) {
            postponeList.add(aPostpone);
            if (aPostpone.getAppointmentFk() != null) {
                aPostpone.getAppointmentFk().getPostponeList().remove(aPostpone);
            }
            aPostpone.setAppointmentFk(this);
        }
        else{
            Postpone theOne = null;
            for(Postpone theItem : postponeList){
                if(Objects.equals(theItem.getPostponeTId(), aPostpone.getPostponeTId())){
                    theOne = theItem;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setAddress(aPostpone.getAddress());
                theOne.setCreation(aPostpone.getCreation());
                theOne.setEnd(aPostpone.getEnd());
                theOne.setInitiatedBy(aPostpone.getInitiatedBy());
                theOne.setReason(aPostpone.getReason());
                theOne.setStart(aPostpone.getStart());
                theOne.setStatus(aPostpone.getStatus());
            }
        }
    }
    
 public boolean removePostponeFromList(Postpone aPostpone) {
    if (postponeList.contains(aPostpone)) {
        postponeList.remove(aPostpone);
        return true;
    }  
    return false;
}

    public Order_ getOrderFk() {
        return orderFk;
    }

    public void setOrderFk(Order_ orderFk) {
        this.orderFk = orderFk;
    }

    public OrderLine getOrderLineFk() {
        return orderLineFk;
    }

    public void setOrderLineFk(OrderLine orderLineFk) {
        this.orderLineFk = orderLineFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentTId != null ? appointmentTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.appointmentTId == null && other.appointmentTId != null) || (this.appointmentTId != null && !this.appointmentTId.equals(other.appointmentTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.order.Appointment[ appointmentTId=" + appointmentTId + " ]";
    }
    
}

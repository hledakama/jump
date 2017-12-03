/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.order;

import org.lhedav.pp.business.model.user.Account;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author client
 */
@Entity
@Table(name = "ORDER_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order_.findAll", query = "SELECT o FROM Order_ o")
    , @NamedQuery(name = "Order_.findByOrderTId", query = "SELECT o FROM Order_ o WHERE o.orderTId = :orderTId")
    , @NamedQuery(name = "Order_.findByCreation", query = "SELECT o FROM Order_ o WHERE o.creation = :creation")
    , @NamedQuery(name = "Order_.findByShipping", query = "SELECT o FROM Order_ o WHERE o.shipping = :shipping")
    , @NamedQuery(name = "Order_.findByCancellation", query = "SELECT o FROM Order_ o WHERE o.cancellation = :cancellation")
    , @NamedQuery(name = "Order_.findByService", query = "SELECT o FROM Order_ o WHERE o.service = :service")
    , @NamedQuery(name = "Order_.findByGranted", query = "SELECT o FROM Order_ o WHERE o.granted = :granted")
    , @NamedQuery(name = "Order_.findByPayment", query = "SELECT o FROM Order_ o WHERE o.payment = :payment")
    , @NamedQuery(name = "Order_.findByStatus", query = "SELECT o FROM Order_ o WHERE o.status = :status")})
public class Order_ implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_order", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ORDER_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_order" ) 
    @Column(name = "ORDER_T_ID")
    private Long orderTId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SHIPPING")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipping;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANCELLATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancellation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERVICE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date service;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRANTED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date granted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAYMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payment;
    @Column(name = "STATUS")
    private Short status;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderFk")
    private List<Inmail> inmailList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderFk")
    private List<Chat> chatList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderFk")
    private List<OrderLine> orderLineList;
    @JoinColumn(name = "ACCOUNT_FK", referencedColumnName = "ACCOUNT_T_ID")
    @ManyToOne
    private Account accountFk;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderFk")
    private List<Rate> rateList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderFk")
    private List<Appointment> appointmentList;

    public Order_() {
    }

    public Order_(Long orderTId) {
        this.orderTId = orderTId;
    }

    public Order_(Long orderTId, Date creation, Date shipping, Date cancellation, Date service, Date granted, Date payment) {
        this.orderTId = orderTId;
        this.creation = creation;
        this.shipping = shipping;
        this.cancellation = cancellation;
        this.service = service;
        this.granted = granted;
        this.payment = payment;
    }

    public Long getOrderTId() {
        return orderTId;
    }

    public void setOrderTId(Long orderTId) {
        this.orderTId = orderTId;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getShipping() {
        return shipping;
    }

    public void setShipping(Date shipping) {
        this.shipping = shipping;
    }

    public Date getCancellation() {
        return cancellation;
    }

    public void setCancellation(Date cancellation) {
        this.cancellation = cancellation;
    }

    public Date getService() {
        return service;
    }

    public void setService(Date service) {
        this.service = service;
    }

    public Date getGranted() {
        return granted;
    }

    public void setGranted(Date granted) {
        this.granted = granted;
    }

    public Date getPayment() {
        return payment;
    }

    public void setPayment(Date payment) {
        this.payment = payment;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @XmlTransient
    public List<Inmail> getInmailList() {
        return inmailList;
    }

    public void setInmailList(List<Inmail> inmailList) {
        this.inmailList = inmailList;
    }

    @XmlTransient
    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    @XmlTransient
    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public Account getAccountFk() {
        return accountFk;
    }

    public void setAccountFk(Account accountFk) {
        this.accountFk = accountFk;
    }

    @XmlTransient
    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderTId != null ? orderTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order_)) {
            return false;
        }
        Order_ other = (Order_) object;
        if ((this.orderTId == null && other.orderTId != null) || (this.orderTId != null && !this.orderTId.equals(other.orderTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.order.Order_[ orderTId=" + orderTId + " ]";
    }
    
}

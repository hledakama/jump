/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.order;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author client
 */
@Entity
@Table(name = "ORDER_LINE_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderLine.findAll", query = "SELECT o FROM OrderLine o")
    , @NamedQuery(name = "OrderLine.findByOrderLineTId", query = "SELECT o FROM OrderLine o WHERE o.orderLineTId = :orderLineTId")
    , @NamedQuery(name = "OrderLine.findByItem", query = "SELECT o FROM OrderLine o WHERE o.item = :item")
    , @NamedQuery(name = "OrderLine.findByQty", query = "SELECT o FROM OrderLine o WHERE o.qty = :qty")
    , @NamedQuery(name = "OrderLine.findByUnitPrice", query = "SELECT o FROM OrderLine o WHERE o.unitPrice = :unitPrice")
    , @NamedQuery(name = "OrderLine.findByServiceReference", query = "SELECT o FROM OrderLine o WHERE o.serviceReference = :serviceReference")
    , @NamedQuery(name = "OrderLine.findByItemReference", query = "SELECT o FROM OrderLine o WHERE o.itemReference = :itemReference")
    , @NamedQuery(name = "OrderLine.findByProvider", query = "SELECT o FROM OrderLine o WHERE o.provider = :provider")})
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_orderline", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ORDER_LINE_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_orderline" ) 
    @Column(name = "ORDER_LINE_T_ID")
    private Long orderLineTId;
    @Size(max = 255)
    @Column(name = "ITEM")
    private String item;
    @Column(name = "QTY")
    private Long qty;
    @Column(name = "UNIT_PRICE")
    private Long unitPrice;
    @Size(max = 50)
    @Column(name = "SERVICE_REFERENCE")
    private String serviceReference;
    @Size(max = 50)
    @Column(name = "ITEM_REFERENCE")
    private String itemReference;
    @Column(name = "PROVIDER")
    private BigInteger provider;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderLineFk")
    private List<Inmail> inmailList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderLineFk")
    private List<Chat> chatList;
    @JoinColumn(name = "ORDER_FK", referencedColumnName = "ORDER_T_ID")
    @ManyToOne
    private Order_ orderFk;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderLineFk")
    private List<Rate> rateList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderLineFk")
    private List<Appointment> appointmentList;

    public OrderLine() {
    }

    public OrderLine(Long orderLineTId) {
        this.orderLineTId = orderLineTId;
    }

    public Long getOrderLineTId() {
        return orderLineTId;
    }

    public void setOrderLineTId(Long orderLineTId) {
        this.orderLineTId = orderLineTId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getServiceReference() {
        return serviceReference;
    }

    public void setServiceReference(String serviceReference) {
        this.serviceReference = serviceReference;
    }

    public String getItemReference() {
        return itemReference;
    }

    public void setItemReference(String itemReference) {
        this.itemReference = itemReference;
    }

    public BigInteger getProvider() {
        return provider;
    }

    public void setProvider(BigInteger provider) {
        this.provider = provider;
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

    public Order_ getOrderFk() {
        return orderFk;
    }

    public void setOrderFk(Order_ orderFk) {
        this.orderFk = orderFk;
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
        hash += (orderLineTId != null ? orderLineTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLine)) {
            return false;
        }
        OrderLine other = (OrderLine) object;
        if ((this.orderLineTId == null && other.orderLineTId != null) || (this.orderLineTId != null && !this.orderLineTId.equals(other.orderLineTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.order.OrderLine[ orderLineTId=" + orderLineTId + " ]";
    }
    
}

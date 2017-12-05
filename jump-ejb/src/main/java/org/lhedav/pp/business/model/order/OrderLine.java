/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.order;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
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
        inmailList = new ArrayList();
        chatList = new ArrayList();
        rateList = new ArrayList();
        appointmentList = new ArrayList();
    }

    public OrderLine(Long orderLineTId) {
        this.orderLineTId = orderLineTId;
        inmailList = new ArrayList();
        chatList = new ArrayList();
        rateList = new ArrayList();
        appointmentList = new ArrayList();
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
    
    public void addInmailToList(Inmail anInmail) {
        if (!inmailList.contains(anInmail)) {
            inmailList.add(anInmail);
            if (anInmail.getOrderLineFk() != null) {
                anInmail.getOrderLineFk().getInmailList().remove(anInmail);
            }
            anInmail.setOrderLineFk(this);
        }
        else{
            Inmail theOne = null;
            for(Inmail theInmail : inmailList){
                if(Objects.equals(theInmail.getInmailTId(), anInmail.getInmailTId())){
                    theOne = anInmail;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setContent(anInmail.getContent());
                theOne.setReceive(anInmail.getReceive());
                theOne.setSent(anInmail.getSent());
                theOne.setSubject(anInmail.getSubject());
                theOne.setToOtherUserId(anInmail.getToOtherUserId());
                theOne.setOrderFk(anInmail.getOrderFk());
            }
        }
    }
 public boolean removeInmailFromList(Inmail anInmail) {
    if (inmailList.contains(anInmail)) {
        inmailList.remove(anInmail);
        return true;
    }  
    return false;
}
    
    

    @XmlTransient
    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }
    
    
    public void addChatToList(Chat aChat) {
        if (!chatList.contains(aChat)) {
            chatList.add(aChat);
            if (aChat.getOrderLineFk() != null) {
                aChat.getOrderLineFk().getChatList().remove(aChat);
            }
            aChat.setOrderLineFk(this);
        }
        else{
            Chat theOne = null;
            for(Chat theChat : chatList){
                if(Objects.equals(theChat.getChatTId(), aChat.getChatTId())){
                    theOne = theChat;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setContent(aChat.getContent());
                theOne.setFromUserId(aChat.getFromUserId());
                theOne.setOrderFk(aChat.getOrderFk());
                theOne.setReceive(aChat.getReceive());
                theOne.setSent(aChat.getSent());
                theOne.setSubject(aChat.getSubject());
                theOne.setToOtherUserId(aChat.getToOtherUserId());
            }
        }
    }
    
 public boolean removeChatFromList(Chat aChat) {
    if (chatList.contains(aChat)) {
        chatList.remove(aChat);
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

    @XmlTransient
    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }
    
    public void addRateToList(Rate aRate) {
        if (!rateList.contains(aRate)) {
            rateList.add(aRate);
            if (aRate.getOrderLineFk() != null) {
                aRate.getOrderLineFk().rateList.remove(aRate);
            }
            aRate.setOrderLineFk(this);
        }
        else{
            Rate theOne = null;
            for(Rate theRate : rateList){
                if(Objects.equals(theRate.getRateTId(), aRate.getRateTId())){
                    theOne = theRate;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setCreation(aRate.getCreation());
                theOne.setEnvironment(aRate.getEnvironment());
                theOne.setInput(aRate.getInput());
                theOne.setOnTime(aRate.getOnTime());
                theOne.setOrderFk(aRate.getOrderFk());
                theOne.setQuality(aRate.getQuality());
                theOne.setRespect(aRate.getRespect());
                theOne.setTools(aRate.getTools());
            }
        }
    }
 public boolean removeRateToList(Rate aRate) {
    if (rateList.contains(aRate)) {
        rateList.remove(aRate);
        return true;
    }  
    return false;
}

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
    
    public void addAppointmentToList(Appointment aRdv) {
        if (!appointmentList.contains(aRdv)) {
            appointmentList.add(aRdv);
            if (aRdv.getOrderLineFk() != null) {
                aRdv.getOrderLineFk().getAppointmentList().remove(aRdv);
            }
            aRdv.setOrderLineFk(this);
        }
        else{
            Appointment theOne = null;
            for(Appointment theItem : appointmentList){
                if(Objects.equals(theItem.getAppointmentTId(), aRdv.getAppointmentTId())){
                    theOne = theItem;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setAddress(aRdv.getAddress());
                theOne.setCreation(aRdv.getCreation());
                theOne.setEnd(aRdv.getEnd());
                theOne.setOrderFk(aRdv.getOrderFk());
                theOne.setStart(aRdv.getStart());
                for(Postpone thePostpone: aRdv.getPostponeList()){
                    if(!theOne.getPostponeList().contains(thePostpone)){
                        theOne.addPostponeToList(thePostpone);
                    }
                }
            }
        }
    }
    
 public boolean removeAppointmentFromList(Appointment aRdv) {
    if (getAppointmentList().contains(aRdv)) {
        getAppointmentList().remove(aRdv);
        return true;
    }  
    return false;
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

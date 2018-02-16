/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.order;

import org.lhedav.pp.business.model.user.Account;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.lhedav.pp.business.bean_validation.class_level.ChronologicalDates;
import org.lhedav.pp.business.model.feedback.Recommendation;

/**
 *
 * @author client
 */
@ChronologicalDates
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
    /*@JoinColumn(name = "ACCOUNT_FK", referencedColumnName = "ACCOUNT_T_ID")
    @ManyToOne*/
    @Column(name = "ACCOUNT_ID")
    private long accountId;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderFk")
    private List<Rate> rateList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderFk")
    private List<Appointment> appointmentList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderFk")
    private List<Recommendation> recommendationList;

    public Order_() {
        inmailList = new ArrayList();
        chatList = new ArrayList();
        orderLineList = new ArrayList();
        rateList = new ArrayList();
        appointmentList = new ArrayList();
        recommendationList = new ArrayList();
    }

    public Order_(Long orderTId) {
        this.orderTId = orderTId;
        inmailList = new ArrayList();
        chatList = new ArrayList();
        orderLineList = new ArrayList();
        rateList = new ArrayList();
        appointmentList = new ArrayList();
        recommendationList = new ArrayList();
    }

    public Order_(Long orderTId, Date creation, Date shipping, Date cancellation, Date service, Date granted, Date payment) {
        this.orderTId      = orderTId;
        this.creation      = creation;
        this.shipping      = shipping;
        this.cancellation  = cancellation;
        this.service       = service;
        this.granted       = granted;
        this.payment       = payment;
        inmailList         = new ArrayList();
        chatList           = new ArrayList();
        orderLineList      = new ArrayList();
        rateList           = new ArrayList();
        appointmentList    = new ArrayList();
        recommendationList = new ArrayList();
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
    
    public void addInmailToList(Inmail anInmail) {
        if (!inmailList.contains(anInmail)) {
            inmailList.add(anInmail);
            if (anInmail.getOrderFk() != null) {
                anInmail.getOrderFk().getInmailList().remove(anInmail);
            }
            anInmail.setOrderFk(this);
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
                theOne.setOrderLineFk(anInmail.getOrderLineFk());
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
            if (aChat.getOrderFk() != null) {
                aChat.getOrderFk().getChatList().remove(aChat);
            }
            aChat.setOrderFk(this);
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
                theOne.setOrderLineFk(aChat.getOrderLineFk());
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

    @XmlTransient
    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }
    
    public void addOrderLineToList(OrderLine aLine) {
        if (!orderLineList.contains(aLine)) {
            orderLineList.add(aLine);
            if (aLine.getOrderFk()!= null) {
                aLine.getOrderFk().getOrderLineList().remove(aLine);
            }
            aLine.setOrderFk(this);
        }
        else{
            OrderLine theOne = null;
            for(OrderLine theLine : orderLineList){
                if(Objects.equals(theLine.getOrderLineTId(), aLine.getOrderLineTId())){
                    theOne = theLine;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setItem(aLine.getItem());
                theOne.setItemReference(aLine.getItemReference());
                theOne.setServiceReference(aLine.getServiceReference());
                theOne.setProvider(aLine.getProvider());
                theOne.setQty(aLine.getQty());
                theOne.setUnitPrice(aLine.getUnitPrice());
                
            }
        }
    }
 public boolean removeOrderLineFromList(OrderLine aLine) {
    if (orderLineList.contains(aLine)) {
        orderLineList.remove(aLine);
        return true;
    }  
    return false;
}

    public long getAccountId() {
        return accountId;
    }

    public void setAccountFk(long anAccountId) {
        this.accountId = anAccountId;
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
            if (aRate.getOrderFk() != null) {
                aRate.getOrderFk().rateList.remove(aRate);
            }
            aRate.setOrderFk(this);
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
                theOne.setOrderLineFk(aRate.getOrderLineFk());
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
            if (aRdv.getOrderFk() != null) {
                aRdv.getOrderFk().getAppointmentList().remove(aRdv);
            }
            aRdv.setOrderFk(this);
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
                theOne.setOrderLineFk(aRdv.getOrderLineFk());
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

    @XmlTransient
    public List<Recommendation> getRecommendationList() {
        return recommendationList;
    }

    public void setRecommendationList(List<Recommendation> recommendationList) {
        this.recommendationList = recommendationList;
    }
    
    public void addRecommendationToList(Recommendation aRecommendation) {
        if (!recommendationList.contains(aRecommendation)) {
            recommendationList.add(aRecommendation);
            if (aRecommendation.getOrderFk() != null) {
                aRecommendation.getOrderFk().getRecommendationList().remove(aRecommendation);
            }
            aRecommendation.setOrderFk(this);
        }
        else{
            Recommendation theOne = null;
            for(Recommendation theRecommendation : recommendationList){
                if(Objects.equals(theRecommendation.getRecommendationTId(), aRecommendation.getRecommendationTId())){
                    theOne = theRecommendation;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setItemdataFk(aRecommendation.getItemdataFk());
                theOne.setRecommendationDate(aRecommendation.getRecommendationDate());
                theOne.setStatus(aRecommendation.getStatus());
            }
        }
    }
    
 public boolean removeRecommendationFromList(Recommendation aRecommendation) {
    if (recommendationList.contains(aRecommendation)) {
        recommendationList.remove(aRecommendation);
        return true;
    }  
    return false;
}
    
}

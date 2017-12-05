/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.user;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.lhedav.pp.business.model.feedback.Recommendation;
import org.lhedav.pp.business.model.order.Appointment;
import org.lhedav.pp.business.model.order.Chat;
import org.lhedav.pp.business.model.order.Inmail;
import org.lhedav.pp.business.model.order.OrderLine;
import org.lhedav.pp.business.model.order.Order_;
import org.lhedav.pp.business.model.order.Rate;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Service;

/**
 *
 * @author client
 */
@Entity
@Table(name = "ACCOUNT_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByAccountTId", query = "SELECT a FROM Account a WHERE a.accountTId = :accountTId")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_account", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ACCOUNT_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_account" ) 
    @Column(name = "ACCOUNT_T_ID")
    private Long accountTId;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "accountFk")
    private List<Service> serviceList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "accountFk")
    private List<Order_> orderList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "accountFk")
    private List<Profile> profileList;

    public Account() {
        serviceList = new ArrayList();
        orderList   = new ArrayList();
        profileList = new ArrayList();
    }

    public Account(Long accountTId) {
        this.accountTId = accountTId;
        serviceList = new ArrayList();
        orderList   = new ArrayList();
        profileList = new ArrayList();
    }

    public Long getAccountTId() {
        return accountTId;
    }

    public void setAccountTId(Long accountTId) {
        this.accountTId = accountTId;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
    
    public void addServiceToList(Service aService) {
        if (!serviceList.contains(aService)) {
            serviceList.add(aService);
            if (aService.getAccountFk() != null) {
                aService.getAccountFk().serviceList.remove(aService);
            }
            aService.setAccountFk(this);
        }
        else{
            Service theOne = null;
            for(Service theService : serviceList){
                if(Objects.equals(theService.getServiceTId(), aService.getServiceTId())){
                    theOne = theService;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setCategory(aService.getCategory());
                theOne.setKind(aService.getKind());
                theOne.setEdited(aService.isEdited());
                theOne.setMerged(aService.isMerged());
                theOne.setPublished(aService.getPublished());
                theOne.setServicename(aService.getServicename());
                theOne.setType(aService.getType());
                theOne.setServicereference();
                for(Item theItem: aService.getItemList()){
                    if(!theOne.getItemList().contains(theItem)){
                        theOne.addItemToList(theItem);
                    }
                }
            }
        }
    }
    
 public boolean removeServiceFromList(Service aService) {
    if (serviceList.contains(aService)) {
        serviceList.remove(aService);
        return true;
    }  
    return false;
}

    @XmlTransient
    public List<Order_> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order_> orderList) {
        this.orderList = orderList;
    }
    
    public void addOrderToList(Order_ anOrder) {
        if (!orderList.contains(anOrder)) {
            orderList.add(anOrder);
            if (anOrder.getAccountFk() != null) {
                anOrder.getAccountFk().orderList.remove(anOrder);
            }
            anOrder.setAccountFk(this);
        }
        else{
            Order_ theOne = null;
            for(Order_ theOrder : orderList){
                if(Objects.equals(anOrder.getOrderTId(), anOrder.getOrderTId())){
                    theOne = theOrder;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setCancellation(theOne.getCancellation());
                theOne.setCreation(theOne.getCreation());
                theOne.setGranted(theOne.getGranted());
                theOne.setPayment(theOne.getPayment());
                theOne.setService(theOne.getService());
                theOne.setShipping(theOne.getShipping());
                theOne.setStatus(theOne.getStatus());
                
                for(Inmail theInmail: anOrder.getInmailList()){
                    if(!theOne.getInmailList().contains(theInmail)){
                        theOne.addInmailToList(theInmail);
                    }
                }
                for(Chat theChat: anOrder.getChatList()){
                    if(!theOne.getChatList().contains(theChat)){
                        theOne.addChatToList(theChat);
                    }
                }
                 for(OrderLine theOrderLine: anOrder.getOrderLineList()){
                    if(!theOne.getOrderLineList().contains(theOrderLine)){
                        theOne.addOrderLineToList(theOrderLine);
                    }
                }
                for(Rate theRate: anOrder.getRateList()){
                    if(!theOne.getRateList().contains(theRate)){
                        theOne.addRateToList(theRate);
                    }
                }
                for(Appointment theAppointment: anOrder.getAppointmentList()){
                    if(!theOne.getAppointmentList().contains(theAppointment)){
                        theOne.addAppointmentToList(theAppointment);
                    }
                }
                for(Recommendation theRecommendation: anOrder.getRecommendationList()){
                    if(!theOne.getRecommendationList().contains(theRecommendation)){
                        theOne.addRecommendationToList(theRecommendation);
                    }
                }
            }
        }
    }
 public boolean removeOrderFromList(Order_ anOrder) {
    if (orderList.contains(anOrder)) {
        orderList.remove(anOrder);
        return true;
    }  
    return false;
}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountTId != null ? accountTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountTId == null && other.accountTId != null) || (this.accountTId != null && !this.accountTId.equals(other.accountTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.order.Account[ accountTId=" + accountTId + " ]";
    }

    @XmlTransient
    public List<Profile> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Profile> profileList) {
        this.profileList = profileList;
    }
    
    public void addProfileToList(Profile aProfile) {
        if (!profileList.contains(aProfile)) {
            profileList.add(aProfile);
            if (aProfile.getAccountFk() != null) {
                aProfile.getAccountFk().profileList.remove(aProfile);
            }
            aProfile.setAccountFk(this);
        }
        else{
            Profile theOne = null;
            for(Profile theProfile : profileList){
                if(Objects.equals(theProfile.getProfileTId(), aProfile.getProfileTId())){
                    theOne = theProfile;
                    break;
                }
            }
            if(theOne!= null){
                theOne.setCreation(theOne.getCreation());
                theOne.setEmail(theOne.getEmail());
                theOne.setFirstName(theOne.getFirstName());
                theOne.setLastName(theOne.getLastName());
                theOne.setMobilePhone(theOne.getMobilePhone());
                theOne.setPassword(theOne.getPassword());
                for(Avatar theAvatar: aProfile.getAvatarList()){
                    if(!theOne.getAvatarList().contains(theAvatar)){
                        theOne.addAvatarToList(theAvatar);
                    }
                }
                for(Address theAddress: aProfile.getAddressList()){
                    if(!theOne.getAddressList().contains(theAddress)){
                        theOne.addAddressToList(theAddress);
                    }
                }
            }
        }
    }
 public boolean removeProfileFromList(Profile aProfile) {
    if (profileList.contains(aProfile)) {
        profileList.remove(aProfile);
        return true;
    }  
    return false;
}
    
}

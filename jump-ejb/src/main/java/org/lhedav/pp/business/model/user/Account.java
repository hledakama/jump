/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.user;

import java.io.Serializable;
import java.util.List;
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
import org.lhedav.pp.business.model.order.Order_;
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
    }

    public Account(Long accountTId) {
        this.accountTId = accountTId;
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

    @XmlTransient
    public List<Order_> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order_> orderList) {
        this.orderList = orderList;
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.persistence.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity
@Table(name = "ITEM_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
    , @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id")  
    , @NamedQuery(name = "Item.findByItemreference", query = "SELECT i FROM Item i WHERE i.itemreference = :itemreference")
    , @NamedQuery(name = "Item.findByItemname", query = "SELECT i FROM Item i WHERE i.itemname = :itemname")
    , @NamedQuery(name = "Item.findByPrice", query = "SELECT i FROM Item i WHERE i.price = :price")
    , @NamedQuery(name = "Item.findByVirtual_", query = "SELECT i FROM Item i WHERE i.virtual_ = :virtual_")
    , @NamedQuery(name = "Item.findByQty", query = "SELECT i FROM Item i WHERE i.qty = :qty")
    , @NamedQuery(name = "Item.findByCdate", query = "SELECT i FROM Item i WHERE i.cdate = :cdate")
    , @NamedQuery(name = "Item.findByServiceFk", query = "SELECT i FROM Item i WHERE i.serviceFk = :serviceFk")
        })

public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    
        
    @Column(name = "CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdate;    
        
    @Size(max = 50)
    @Column(name = "ITEMNAME")
    private String itemname;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ITEMREFERENCE")
    private String itemreference;

    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private Long price;    
        
    @Column(name = "QTY")
    private Long qty; 
    
    @Column(name = "SERVICE_FK")
    private Long serviceFk;
    
    @Column(name = "VIRTUAL_")
    private boolean virtual_;


    
    @Transient
    private boolean edited;

    public Item() {
    }

    public Item(Long id) {
        this.id = id;
    }

    public Item(Long id, String itemreference, Long price) {
        this.id = id;
        this.itemreference = itemreference;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemreference() {
        return itemreference;
    }

    public void setItemreference(String itemreference) {
        this.itemreference = itemreference;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean getVirtual_() {
        return virtual_;
    }

    public void setVirtual_(boolean virtual) {
        this.virtual_ = virtual;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Long getServiceFk() {
        return serviceFk;
    }

    public void setServiceFk(Long serviceFk) {
        this.serviceFk = serviceFk;
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.persistence.service.Item[ id=" + id + " ]";
    }
    
    public boolean isEdited(){
        return edited;
    }
    
    public void setEdited(boolean aBool){
        edited = aBool;
    }
    
}

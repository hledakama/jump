/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity(name = "Item")
@Table(name = "ITEM_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
    , @NamedQuery(name = "Item.findByItemTId", query = "SELECT i FROM Item i WHERE i.itemTId = :itemTId")
    , @NamedQuery(name = "Item.findByCdate", query = "SELECT i FROM Item i WHERE i.cdate = :cdate")
    , @NamedQuery(name = "Item.findByItemname", query = "SELECT i FROM Item i WHERE i.itemname = :itemname")
    , @NamedQuery(name = "Item.findByItemreference", query = "SELECT i FROM Item i WHERE i.itemreference = :itemreference")
    , @NamedQuery(name = "Item.findByPrice", query = "SELECT i FROM Item i WHERE i.price = :price")
    , @NamedQuery(name = "Item.findByQty", query = "SELECT i FROM Item i WHERE i.qty = :qty")
    , @NamedQuery(name = "Item.findByVirtual", query = "SELECT i FROM Item i WHERE i.virtual = :virtual")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_item", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ITEM_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_item" )    
    @Column(name = "ITEM_T_ID")
    private Long itemTId;
    @Basic(optional = false)
    @Column(name = "CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdate;
    @Size(max = 50)
    @Column(name = "ITEMNAME")
    private String itemname;
    @Size(max = 50)
    @Column(name = "ITEMREFERENCE")
    private String itemreference;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "QTY")
    private Long qty;
    @Column(name = "VIRTUAL_")
    private Short virtual;
    @JoinColumn(name = "SERVICE_FK", referencedColumnName = "SERVICE_T_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Service serviceFk;
    @Transient
    private boolean edited;

    public Item() {
    }

    public Item(Long itemTId) {
        this.itemTId = itemTId;
    }

    public Item(Long itemTId, Date cdate) {
        this.itemTId = itemTId;
        this.cdate = cdate;
    }

    public Long getItemTId() {
        return itemTId;
    }

    public void setItemTId(Long itemTId) {
        this.itemTId = itemTId;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemreference() {
        return itemreference;
    }

    public void setItemreference(String itemreference) {
        this.itemreference = itemreference;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Short getVirtual() {
        return virtual;
    }

    public void setVirtual(Short virtual) {
        this.virtual = virtual;
    }

    public Service getServiceFk() {
        return serviceFk;
    }

    public void setServiceFk(Service serviceFk) {
        this.serviceFk = serviceFk;
    }
    
    public boolean isEdited(){
        return edited;
    }
    
    public void setEdited(boolean aBool){
        edited = aBool;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemTId != null ? itemTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemTId == null && other.itemTId != null) || (this.itemTId != null && !this.itemTId.equals(other.itemTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.service.Item[ itemTId=" + itemTId + " ]";
    }
    
}

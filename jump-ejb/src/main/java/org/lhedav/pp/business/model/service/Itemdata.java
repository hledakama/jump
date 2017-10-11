/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.CollectionDataModel;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.lhedav.pp.business.model.common.Global;

/**
 *
 * @author client
 */
@Entity(name = "Itemdata")
@Table(name = "ITEMDATA_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemdata.findAll", query = "SELECT i FROM Itemdata i")
    , @NamedQuery(name = "Itemdata.findByItemdataTId", query = "SELECT i FROM Itemdata i WHERE i.itemdataTId = :itemdataTId")
    , @NamedQuery(name = "Itemdata.findByMdate", query = "SELECT i FROM Itemdata i WHERE i.mdate = :mdate")
    , @NamedQuery(name = "Itemdata.findByAvatar", query = "SELECT i FROM Itemdata i WHERE i.avatar = :avatar")
    , @NamedQuery(name = "Itemdata.findByComment", query = "SELECT i FROM Itemdata i WHERE i.comment = :comment")
    , @NamedQuery(name = "Itemdata.findByDuration", query = "SELECT i FROM Itemdata i WHERE i.duration = :duration")
    , @NamedQuery(name = "Itemdata.findByUnit", query = "SELECT i FROM Itemdata i WHERE i.unit = :unit")
})
public class Itemdata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_itemdata", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ITEMDATA_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_itemdata" )   
    @Column(name = "ITEMDATA_T_ID")
    private Long itemdataTId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mdate;
    @Size(max = 255)
    @Column(name = "AVATAR")
    private String avatar;
    @Size(max = 255)
    @Column(name = "COMMENT_")
    private String comment;
    @Column(name = "DURATION")
    private Long duration;
    @Size(max = 3)
    @Column(name = "UNIT")
    private String unit;
    @JoinColumn(name = "ITEM_FK", referencedColumnName = "ITEM_T_ID")
    @ManyToOne
    private Item itemFk;
    @OneToMany(mappedBy = "itemdataFk")
    private List<ProviderAddress> providerAddressList;
        @Transient
    private boolean edited = false;
        @Transient
    private String addressSring = Global.STR_EMPTY;

    public Itemdata() {
        providerAddressList = new ArrayList();
    }

    public Itemdata(Long itemdataTId) {
        this.itemdataTId = itemdataTId;
        providerAddressList = new ArrayList();
    }

    public Itemdata(Long itemdataTId, Date mdate) {
        providerAddressList = new ArrayList();
        this.itemdataTId = itemdataTId;
        this.mdate = mdate;
    }

    public Long getItemdataTId() {
        return itemdataTId;
    }

    public void setItemdataTId(Long itemdataTId) {
        this.itemdataTId = itemdataTId;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Item getItemFk() {
        return itemFk;
    }

    public void setItemFk(Item itemFk) {
        this.itemFk = itemFk;
    }

    @XmlTransient
    public List<ProviderAddress> getProviderAddressList() {
        return providerAddressList;
    }

    public void setProviderAddressList(List<ProviderAddress> providerAddressList) {
        this.providerAddressList = providerAddressList;
    }
    
    public void addProviderAddressToList(ProviderAddress anAddress) {
        if (!getProviderAddressList().contains(anAddress)) {
            getProviderAddressList().add(anAddress);
            if (anAddress.getItemdataFk() != null) {
                anAddress.getItemdataFk().getProviderAddressList().remove(anAddress);
            }
            anAddress.setItemdataFk(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemdataTId != null ? itemdataTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemdata)) {
            return false;
        }
        Itemdata other = (Itemdata) object;
        if ((this.itemdataTId == null && other.itemdataTId != null) || (this.itemdataTId != null && !this.itemdataTId.equals(other.itemdataTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.service.Itemdata[ itemdataTId=" + itemdataTId + " ]";
    }
    
    public boolean isEdited(){
        return edited;
    }
    
    public void setEdited(boolean aBool){
        edited = aBool;
    }
    
    public String getAddressString(){
        return addressSring;
    }
    
    public void setAddressString(){        
        if(providerAddressList == null) return;
        addressSring = "";
        for(ProviderAddress theAddress: providerAddressList){
           addressSring +=  theAddress.toString();
        }
    }
    
}

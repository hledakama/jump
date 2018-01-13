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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient; 
import org.lhedav.pp.business.model.common.CRC32StringCollection;
import org.lhedav.pp.business.model.common.Global;

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
})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_item", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ITEM_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_item" ) 
    @Column(name = "ITEM_T_ID")
    private Long itemTId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdate;
    @Size(max = 50, min = 3)
    @Column(name = "ITEMNAME")
    private String itemname;
    @Size(max = 50)
    @Column(name = "ITEMREFERENCE")
    private String itemreference;    
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemFk", orphanRemoval = true)
    private List<Itemdata> itemdataList;
    @JoinColumn(name = "SERVICE_FK", referencedColumnName = "SERVICE_T_ID")
    @ManyToOne
    private Service serviceFk;
        @Transient
    private boolean edited = false;

    public Item() {
        itemdataList = new ArrayList();
    }

    public Item(Long itemTId) {
        itemdataList = new ArrayList();
        this.itemTId = itemTId;
    }

    public Item(Long itemTId, Date cdate) {
        itemdataList = new ArrayList();
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

    public void setItemreference(String aKind, String aType, String aService, String aCategory) {
        //System.out.println("In setItemreference, aKind: "+aKind+ ", aType: "+aType+ ", aService: "+ aService+ ", aCategory: "+aCategory + ", itemname: "+itemname);
        List<String> theList = new ArrayList();
          theList.add(aKind);
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(aType);
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(aService);
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(aCategory);
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(getItemname());
          this.itemreference = ((new CRC32StringCollection(theList)).hashCode())+Global.STR_EMPTY; 
          //System.out.println("In setItemreference: "+itemreference);
    }
       
    public List<Itemdata> getItemdataList() {
        return itemdataList;
    }

    public void setItemdataList(List<Itemdata> itemdataList) {
        this.itemdataList = itemdataList;
    }
    
    public void addItemDataToList(Itemdata anItemData) {
        if (!getItemdataList().contains(anItemData)) {
            getItemdataList().add(anItemData);
            /*if (anItemData.getItemFk() != null) {
                anItemData.getItemFk().getItemdataList().remove(anItemData);
            }*/
            anItemData.setItemFk(this);
        }
    }
    
    public boolean removeItemDataFromList(Itemdata anItemData) {
        System.out.println("removeItemDataToList, this: "+this+", getItemdataList().contains(anItemData): "+getItemdataList().contains(anItemData)+ ", getComment: "+anItemData.getComment());
        if (getItemdataList().contains(anItemData)) {
            getItemdataList().remove(anItemData);
            System.out.println("removeItemDataToList true");
            return true;
        }
        System.out.println("removeItemDataToList false");
        return false;
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

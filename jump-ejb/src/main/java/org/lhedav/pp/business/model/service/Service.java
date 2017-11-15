/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.lhedav.pp.business.model.common.CRC32StringCollection;
import org.lhedav.pp.business.model.common.Global;

/**
 *
 * @author client
 */
@Entity(name = "Service")
@Table(name = "SERVICE_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
    , @NamedQuery(name = "Service.findByServiceTId", query = "SELECT s FROM Service s WHERE s.serviceTId = :serviceTId")
    , @NamedQuery(name = "Service.findByCategory", query = "SELECT s FROM Service s WHERE s.category = :category")
    , @NamedQuery(name = "Service.findByKind", query = "SELECT s FROM Service s WHERE s.kind = :kind")
    , @NamedQuery(name = "Service.findByPublished", query = "SELECT s FROM Service s WHERE s.published = :published")
    , @NamedQuery(name = "Service.findByServicename", query = "SELECT s FROM Service s WHERE s.servicename = :servicename")
    , @NamedQuery(name = "Service.findByServicereference", query = "SELECT s FROM Service s WHERE s.servicereference = :servicereference")
    , @NamedQuery(name = "Service.findByType", query = "SELECT s FROM Service s WHERE s.type = :type")
    , @NamedQuery(name = "Service.findByServiceNameKindTypeCategory", query = "SELECT s FROM Service s WHERE ((s.servicename = :servicename) AND (s.kind = :kind) AND (s.type = :type) AND (s.category = :category))")
})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_service", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "SERVICE_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_service" )   
    @Column(name = "SERVICE_T_ID")
    private Long serviceTId;
    @Size(max = 50)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 10)
    @Column(name = "KIND")
    private String kind;
    @Column(name = "PUBLISHED")
    private Short published;
    @Size(max = 50)
    @Column(name = "SERVICENAME")
    private String servicename;
    @Size(max = 50)
    @Column(name = "SERVICEREFERENCE")
    private String servicereference;
    @Size(max = 20)
    @Column(name = "TYPE_")
    private String type;
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceFk", orphanRemoval = true)
    private List<Item> itemList;
    @Transient
    private boolean edited = false;

    
        public final static String KIND_HOUSEHOLD      = "HOUSE_HOLD";
	public final static String KIND_WORKPLACE      = "WORK_PLACE";	
        
	public final static String TYPE_ADMINISTRATION = "ADMINISTRATION";
	public final static String TYPE_BEAUTY         = "BEAUTY";
	public final static String TYPE_EDUCATION      = "EDUCATION";
	public final static String TYPE_ENTERTAINMENT  = "ENTERTAINMENT";
	public final static String TYPE_FOOD           = "FOOD";
	public final static String TYPE_HEALTH         = "HEALTH";
	public final static String TYPE_MAINTENANCE    = "MAINTENANCE";
	public final static String TYPE_SECURITY       = "SECURITY";
	public final static String TYPE_TRANSPORTATION = "TRANSPORTATION";

    public Service() {
        itemList = new ArrayList();
    }

    public Service(Long serviceTId) {
        itemList = new ArrayList();
        this.serviceTId = serviceTId;
    }

    public Long getServiceTId() {
        return serviceTId;
    }

    public void setServiceTId(Long serviceTId) {
        this.serviceTId = serviceTId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Short getPublished() {
        return published;
    }

    public void setPublished(Short published) {
        this.published = published;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServicereference() {
        return servicereference;
    }

    public void setServicereference() {
        System.out.println("In setServicereference, kind: "+kind+ ", type: "+type+ ", servicename: "+ servicename+ ", category: "+category);
          List the_crc_key = new ArrayList();
          the_crc_key.add(getKind());
          the_crc_key.add(Global.REFERENCE_SPLITTER);
          the_crc_key.add(getType());
          the_crc_key.add(Global.REFERENCE_SPLITTER);
          the_crc_key.add(getServicename());
          the_crc_key.add(Global.REFERENCE_SPLITTER);
          the_crc_key.add(getCategory());
          this.servicereference = (new CRC32StringCollection(the_crc_key)).hashCode() + Global.STR_EMPTY;   
          System.out.println("In setServicereference, servicereference: "+servicereference);
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    public List<Item> getItemList() {
        for(Item theitem: itemList){
            System.out.println("getItemList-->theitem.getServiceFk(): "+theitem.getServiceFk());
        }
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    
      public void addItemToList(Item anItem) {
          System.out.println("addItemToList, this: "+this);
        if (!getItemList().contains(anItem)) {
            getItemList().add(anItem);
            if (anItem.getServiceFk() != null) {
                anItem.getServiceFk().getItemList().remove(anItem);
            }
            anItem.setServiceFk(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceTId != null ? serviceTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceTId == null && other.serviceTId != null) || (this.serviceTId != null && !this.serviceTId.equals(other.serviceTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.service.Service[ serviceTId=" + serviceTId + " ]";
    }
    
    public boolean isEdited(){
        return edited;
    }
    
    public void setEdited(boolean aBool){
        edited = aBool;
    }
    
}

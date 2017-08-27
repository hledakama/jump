/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author client
 */
@Entity
@Table( name = "SERVICE_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
    , @NamedQuery(name = "Service.findById", query = "SELECT s FROM Service s WHERE s.id = :id")
    , @NamedQuery(name = "Service.findByCategory", query = "SELECT s FROM Service s WHERE s.category = :category")
    , @NamedQuery(name = "Service.findByKind", query = "SELECT s FROM Service s WHERE s.kind = :kind")
    , @NamedQuery(name = "Service.findByPublished", query = "SELECT s FROM Service s WHERE s.published = :published")
    , @NamedQuery(name = "Service.findByServicename", query = "SELECT s FROM Service s WHERE s.servicename = :servicename")
    , @NamedQuery(name = "Service.findByServicereference", query = "SELECT s FROM Service s WHERE s.servicereference = :servicereference")
    , @NamedQuery(name = "Service.findBySubcategory", query = "SELECT s FROM Service s WHERE s.subcategory = :subcategory")
    , @NamedQuery(name = "Service.findByType", query = "SELECT s FROM Service s WHERE s.type = :type")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @TableGenerator( name = "sequence", table = "SEQUENCE", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "SERVICE_T.ID", valueColumnName = "APP_SEQ_COUNT", initialValue = 1, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence" )
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CATEGORY")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "KIND")
    private String kind;
    @Column(name = "PUBLISHED")
    private Short published;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SERVICENAME")
    private String servicename;
    @Size(max = 50)
    @Column(name = "SERVICEREFERENCE")
    private String servicereference;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SUBCATEGORY")
    private String subcategory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TYPE_")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceFk")
    private Collection<Item> itemCollection;
    
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
        this.itemCollection = new ArrayList();
    }

    public Service(Long id) {
        this.itemCollection = new ArrayList();
        this.id = id;
    }

    public Service(Long id, String category, String kind, String servicename, String subcategory, String type) {
        this.itemCollection = new ArrayList();
        this.id = id;
        this.category = category;
        this.kind = kind;
        this.servicename = servicename;
        this.subcategory = subcategory;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setServicereference(String servicereference) {
        this.servicereference = servicereference;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }
    
    public void addItem(Item anItem){
        if(anItem != null){
            this.itemCollection.add(anItem);
        }
    }
    
        public void removeItem(Item anItem){
        if((this.itemCollection.contains(anItem))){
            this.itemCollection.remove(anItem);
        }
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
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.service.Service[ id=" + id + " ]";
    }
    
}

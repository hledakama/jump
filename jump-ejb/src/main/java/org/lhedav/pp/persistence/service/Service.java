/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.persistence.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity
@Table(name = "SERVICE_T")
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    
    @Size(max = 255)
    @Column(name = "KIND")
    private String kind;
    
    @Column(name = "PUBLISHED")
    private boolean published;
    
    @Size(max = 255)
    @Column(name = "SERVICENAME")
    private String servicename;
    
    @Size(max = 255)
    @Column(name = "SERVICEREFERENCE")
    private String servicereference;
    
    @Size(max = 255)
    @Column(name = "SUBCATEGORY")
    private String subcategory;
    
    @Size(max = 255)
    @Column(name = "TYPE_")
    private String type;
    
     @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "serviceFk")
    private List<Item> items = new ArrayList();
    
    
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
    }

    public Service(Long id) {
        this.id = id;
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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
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
    
        public List<Item> getItems(){
        return items;
    }
    
    public void setItems(List<Item> aList){
        items = aList;
    }
    
    public void addItem(Item anItem){
        if(!items.contains(anItem))
        items.add(anItem);
    }
    
    public void removeItem(Item anItem){
        if(items.contains(anItem))
        items.remove(anItem);
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
        return "org.lhedav.pp.persistence.service.Service[ id=" + id + " ]";
    }
    
}

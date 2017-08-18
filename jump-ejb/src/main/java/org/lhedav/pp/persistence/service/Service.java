/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.persistence.service;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    , @NamedQuery(name = "Service.findByKind", query = "SELECT s FROM Service s WHERE s.kind = :kind")
    , @NamedQuery(name = "Service.findByType", query = "SELECT s FROM Service s WHERE s.type = :type")
    , @NamedQuery(name = "Service.findByCategory", query = "SELECT s FROM Service s WHERE s.category = :category")
    , @NamedQuery(name = "Service.findBySubcategory", query = "SELECT s FROM Service s WHERE s.subcategory = :subcategory")
    , @NamedQuery(name = "Service.findByServicename", query = "SELECT s FROM Service s WHERE s.servicename = :servicename")
    , @NamedQuery(name = "Service.findByPublished", query = "SELECT s FROM Service s WHERE s.published = :published")
    , @NamedQuery(name = "Service.findByServicereference", query = "SELECT s FROM Service s WHERE s.servicereference = :servicereference")
    , @NamedQuery(name = "Service.findByRateFk", query = "SELECT s FROM Service s WHERE s.rateFk = :rateFk")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "KIND")
    private String kind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TYPE_")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CATEGORY")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SUBCATEGORY")
    private String subcategory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SERVICENAME")
    private String servicename;
    @Column(name = "PUBLISHED")
    private boolean published;
    @Size(max = 50)
    @Column(name = "SERVICEREFERENCE")
    private String servicereference;
    @Column(name = "RATE_FK")
    private Long rateFk;
    
    
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

    public Service(Long id, String kind, String type, String category, String subcategory, String servicename) {
        this.id = id;
        this.kind = kind;
        this.type = type;
        this.category = category;
        this.subcategory = subcategory;
        this.servicename = servicename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getServicereference() {
        return servicereference;
    }

    public void setServicereference(String servicereference) {
        this.servicereference = servicereference;
    }

    public Long getRateFk() {
        return rateFk;
    }

    public void setRateFk(Long rateFk) {
        this.rateFk = rateFk;
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

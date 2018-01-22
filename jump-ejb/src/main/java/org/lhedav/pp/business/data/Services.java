/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.data;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.lhedav.pp.business.model.common.Global;

/**
 *
 * @author client
 */
@Entity
@Table(name = "SERVICES_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s")
    , @NamedQuery(name = "Services.findByServicesTId", query = "SELECT s FROM Services s WHERE s.servicesTId = :servicesTId")
    , @NamedQuery(name = "Services.findByServicesType", query = "SELECT s FROM Services s WHERE s.serviceTypeFk = :serviceTypeFk")
    , @NamedQuery(name = "Services.findByName", query = "SELECT s FROM Services s WHERE s.name = :name")})
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_services", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "SERVICES_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_services" )
    @Column(name = "SERVICES_T_ID")
    private Long servicesTId;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "servicesFk",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categories> categoriesList;
    @JoinColumn(name = "SERVICE_TYPE_FK", referencedColumnName = "SERVICE_TYPE_T_ID")
    @ManyToOne
    private ServiceType serviceTypeFk;

    public Services() {
        categoriesList = new ArrayList();
    }

    public Services(Long servicesTId) {
        this.servicesTId = servicesTId;
        categoriesList = new ArrayList();
    }

    public Long getServicesTId() {
        return servicesTId;
    }

    public void setServicesTId(Long servicesTId) {
        this.servicesTId = servicesTId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public ServiceType getServiceTypeFk() {
        return serviceTypeFk;
    }

    public void setServiceTypeFk(ServiceType serviceTypeFk) {
        this.serviceTypeFk = serviceTypeFk;
    }
    
     public void addCategoriesToList(Categories someCategories) {
        if (!Global.isThereMatching(null, null, null, categoriesList, null, someCategories.getName(),Global.CATEGORIES)) {
            getCategoriesList().add(someCategories);
            /*if (someCategories.getServicesFk() != null) {
                someCategories.getServicesFk().getCategoriesList().remove(getServicesFk);
            }*/
            someCategories.setServicesFk(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicesTId != null ? servicesTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.servicesTId == null && other.servicesTId != null) || (this.servicesTId != null && !this.servicesTId.equals(other.servicesTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.data.Services[ servicesTId=" + servicesTId + " ]";
    }
    
}

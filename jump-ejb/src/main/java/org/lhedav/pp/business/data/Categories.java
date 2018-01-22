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
@Table(name = "CATEGORIES_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c")
    , @NamedQuery(name = "Categories.findByCategoriesTId", query = "SELECT c FROM Categories c WHERE c.categoriesTId = :categoriesTId")
    , @NamedQuery(name = "Categories.findByServices", query = "SELECT c FROM Categories c WHERE c.servicesFk = :servicesFk")
    , @NamedQuery(name = "Categories.findByName", query = "SELECT c FROM Categories c WHERE c.name = :name")})
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_categories", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "CATEGORIES_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_categories" )
    @Column(name = "CATEGORIES_T_ID")
    private Long categoriesTId;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriesFk", orphanRemoval = true)
    private List<Items> itemsList;
    @JoinColumn(name = "SERVICES_FK", referencedColumnName = "SERVICES_T_ID")
    @ManyToOne
    private Services servicesFk;

    public Categories() {
        itemsList = new ArrayList();
    }

    public Categories(Long categoriesTId) {
        this.categoriesTId = categoriesTId;
        itemsList = new ArrayList();
    }

    public Long getCategoriesTId() {
        return categoriesTId;
    }

    public void setCategoriesTId(Long categoriesTId) {
        this.categoriesTId = categoriesTId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public Services getServicesFk() {
        return servicesFk;
    }

    public void setServicesFk(Services servicesFk) {
        this.servicesFk = servicesFk;
    }
    
     public void addItemsToList(Items someItems) {
        System.out.println("cat toString: "+toString());
        if (!Global.isThereMatching(null, null, null, null, itemsList, someItems.getName(),Global.ITEMS)) {
            System.out.println("added someItems.getItemsTId(): "+someItems.getItemsTId() + ", someItems.getName(): "+someItems.getName());
            getItemsList().add(someItems);
            /*if (someItems.getCategoriesFk() != null) {
                someItems.getCategoriesFk().getItemsList().remove(someItems);
            }*/
            someItems.setCategoriesFk(this);
        }else{
            System.out.println("not added someItems.getItemsTId(): "+someItems.getItemsTId() + ", someItems.getName(): "+someItems.getName());
            System.out.println("not added getItemsList().get(0).getItemsTId(): "+getItemsList().get(0).getItemsTId() + ", getItemsList().get(0).getName(): "+getItemsList().get(0).getName());
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoriesTId != null ? categoriesTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.categoriesTId == null && other.categoriesTId != null) || (this.categoriesTId != null && !this.categoriesTId.equals(other.categoriesTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.data.Categories[ categoriesTId=" + categoriesTId + " ]";
    }
    
}

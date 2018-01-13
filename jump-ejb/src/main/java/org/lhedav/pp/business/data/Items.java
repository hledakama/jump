/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.data;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity
@Table(name = "ITEMS_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i")
    , @NamedQuery(name = "Items.findByItemsTId", query = "SELECT i FROM Items i WHERE i.itemsTId = :itemsTId")
    , @NamedQuery(name = "Items.findByCategories", query = "SELECT i FROM Items i WHERE i.categoriesFk = :categoriesFk")
    , @NamedQuery(name = "Items.findByName", query = "SELECT i FROM Items i WHERE i.name = :name")})
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_items", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ITEMS_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_items" )
    @Column(name = "ITEMS_T_ID")
    private Long itemsTId;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "CATEGORIES_FK", referencedColumnName = "CATEGORIES_T_ID")
    @ManyToOne
    private Categories categoriesFk;

    public Items() {
    }

    public Items(Long itemsTId) {
        this.itemsTId = itemsTId;
    }

    public Long getItemsTId() {
        return itemsTId;
    }

    public void setItemsTId(Long itemsTId) {
        this.itemsTId = itemsTId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategoriesFk() {
        return categoriesFk;
    }

    public void setCategoriesFk(Categories categoriesFk) {
        this.categoriesFk = categoriesFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemsTId != null ? itemsTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.itemsTId == null && other.itemsTId != null) || (this.itemsTId != null && !this.itemsTId.equals(other.itemsTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.data.Items[ itemsTId=" + itemsTId + " ]";
    }
    
}

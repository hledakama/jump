/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.persistence.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity
@Table(name = "ITEMDATA_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemData.findAll", query = "SELECT i FROM ItemData i")
    , @NamedQuery(name = "ItemData.findById", query = "SELECT i FROM ItemData i WHERE i.id = :id")
    , @NamedQuery(name = "ItemData.findByMdate", query = "SELECT i FROM ItemData i WHERE i.mdate = :mdate")
    , @NamedQuery(name = "ItemData.findByAddress", query = "SELECT i FROM ItemData i WHERE i.address = :address")
    , @NamedQuery(name = "ItemData.findByAvatar", query = "SELECT i FROM ItemData i WHERE i.avatar = :avatar")
    , @NamedQuery(name = "ItemData.findByComment", query = "SELECT i FROM ItemData i WHERE i.comment = :comment")
    , @NamedQuery(name = "ItemData.findByDuration", query = "SELECT i FROM ItemData i WHERE i.duration = :duration")
    , @NamedQuery(name = "ItemData.findByUnit", query = "SELECT i FROM ItemData i WHERE i.unit = :unit")
    , @NamedQuery(name = "ItemData.findByItemdatareference", query = "SELECT i FROM ItemData i WHERE i.itemdatareference = :itemdatareference")
    , @NamedQuery(name = "ItemData.findByItemreferenceFk", query = "SELECT i FROM ItemData i WHERE i.itemreferenceFk = :itemreferenceFk")
    , @NamedQuery(name = "ItemData.findByItemFk", query = "SELECT i FROM ItemData i WHERE i.itemFk = :itemFk")})
public class ItemData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 255)
    @Column(name = "AVATAR")
    private String avatar;
    @Size(max = 255)
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "DURATION")
    private Integer duration;
    @Column(name = "UNIT")
    private Integer unit;
    @Size(max = 50)
    @Column(name = "ITEMDATAREFERENCE")
    private String itemdatareference;
    @Size(max = 50)
    @Column(name = "ITEMREFERENCE_FK")
    private String itemreferenceFk;
    @Column(name = "ITEM_FK")
    private Long itemFk;

    public ItemData() {
    }

    public ItemData(Long id) {
        this.id = id;
    }

    public ItemData(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getItemdatareference() {
        return itemdatareference;
    }

    public void setItemdatareference(String itemdatareference) {
        this.itemdatareference = itemdatareference;
    }

    public String getItemreferenceFk() {
        return itemreferenceFk;
    }

    public void setItemreferenceFk(String itemreferenceFk) {
        this.itemreferenceFk = itemreferenceFk;
    }

    public Long getItemFk() {
        return itemFk;
    }

    public void setItemFk(Long itemFk) {
        this.itemFk = itemFk;
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
        if (!(object instanceof ItemData)) {
            return false;
        }
        ItemData other = (ItemData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.persistence.service.ItemData[ id=" + id + " ]";
    }
    
}

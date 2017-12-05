/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.feedback;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.lhedav.pp.business.model.service.Itemdata;

/**
 *
 * @author client
 */
@Entity
@Table(name = "WISH_LIST_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WishList.findAll", query = "SELECT w FROM WishList w")
    , @NamedQuery(name = "WishList.findByWishListTId", query = "SELECT w FROM WishList w WHERE w.wishListTId = :wishListTId")
    , @NamedQuery(name = "WishList.findByWisher", query = "SELECT w FROM WishList w WHERE w.wisher = :wisher")
    , @NamedQuery(name = "WishList.findByWishDate", query = "SELECT w FROM WishList w WHERE w.wishDate = :wishDate")
    , @NamedQuery(name = "WishList.findByStatus", query = "SELECT w FROM WishList w WHERE w.status = :status")})
public class WishList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_wish_list", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "WISH_LIST_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_wish_list" ) 
    @Column(name = "WISH_LIST_T_ID")
    private Long wishListTId;
    @Column(name = "WISHER")
    private BigInteger wisher;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wishDate;
    @Column(name = "STATUS")
    private Short status;
    @JoinColumn(name = "ITEMDATA_FK", referencedColumnName = "ITEMDATA_T_ID")
    @ManyToOne
    private Itemdata itemdataFk;

    public WishList() {
    }

    public WishList(Long wishListTId) {
        this.wishListTId = wishListTId;
    }

    public WishList(Long wishListTId, Date wishDate) {
        this.wishListTId = wishListTId;
        this.wishDate = wishDate;
    }

    public Long getWishListTId() {
        return wishListTId;
    }

    public void setWishListTId(Long wishListTId) {
        this.wishListTId = wishListTId;
    }

    public BigInteger getWisher() {
        return wisher;
    }

    public void setWisher(BigInteger wisher) {
        this.wisher = wisher;
    }

    public Date getWishDate() {
        return wishDate;
    }

    public void setWishDate(Date wishDate) {
        this.wishDate = wishDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Itemdata getItemdataFk() {
        return itemdataFk;
    }

    public void setItemdataFk(Itemdata itemdataFk) {
        this.itemdataFk = itemdataFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wishListTId != null ? wishListTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WishList)) {
            return false;
        }
        WishList other = (WishList) object;
        if ((this.wishListTId == null && other.wishListTId != null) || (this.wishListTId != null && !this.wishListTId.equals(other.wishListTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.feedback.WishList[ wishListTId=" + wishListTId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

import org.lhedav.pp.business.model.user.Avatar;
import org.lhedav.pp.business.model.user.Address;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.feedback.MarketView;
import org.lhedav.pp.business.model.feedback.Recommendation;
import org.lhedav.pp.business.model.feedback.WishList;

/**
 *
 * @author client
 */
@Entity(name = "Itemdata")
@Table(name = "ITEMDATA_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(  name = "Itemdata.findAll", query = "SELECT i FROM Itemdata i")
    , @NamedQuery(name = "Itemdata.findByItemdataTId", query = "SELECT i FROM Itemdata i WHERE i.itemdataTId = :itemdataTId")
    , @NamedQuery(name = "Itemdata.findByCurrentAvatar", query = "SELECT i FROM Itemdata i WHERE i.currentAvatar = :currentAvatar")
    , @NamedQuery(name = "Itemdata.findByMdate", query = "SELECT i FROM Itemdata i WHERE i.mdate = :mdate")
    , @NamedQuery(name = "Itemdata.findBySdate", query = "SELECT i FROM Itemdata i WHERE i.sdate = :sdate")
    , @NamedQuery(name = "Itemdata.findByComment", query = "SELECT i FROM Itemdata i WHERE i.comment = :comment")
    , @NamedQuery(name = "Itemdata.findByDuration", query = "SELECT i FROM Itemdata i WHERE i.duration = :duration")
    , @NamedQuery(name = "Itemdata.findByUnit", query = "SELECT i FROM Itemdata i WHERE i.unit = :unit")
    , @NamedQuery(name = "Itemdata.findByPrice", query = "SELECT i FROM Itemdata i WHERE i.price = :price")
    , @NamedQuery(name = "Itemdata.findByQty", query = "SELECT i FROM Itemdata i WHERE i.qty = :qty")
    , @NamedQuery(name = "Itemdata.findByVirtual", query = "SELECT i FROM Itemdata i WHERE i.virtual = :virtual")
})
public class Itemdata implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_itemdata", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ITEMDATA_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_itemdata" ) 
    @Column(name = "ITEMDATA_T_ID")    
    private Long itemdataTId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "MDATE")
    @Temporal(TemporalType.TIMESTAMP)      
    private Date mdate;  // main date when created the first time. Never modified.
    
    @Basic(optional = false)    
    @Column(name = "SDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sdate;     // secondary date, enabled once data is modified
        
    @Size(max = 255)
    @Column(name = "COMMENT_")
    @NotNull
    private String comment;
    @NotNull
    @Column(name = "DURATION")
    private Long duration;
    @Size(max = 10)
    @Column(name = "UNIT")
    @NotNull
    private String unit;    
    @Column(name = "CURRENT_AVATAR")
    private String currentAvatar = "Current Avatar";
    @JoinColumn(name = "ITEM_FK", referencedColumnName = "ITEM_T_ID")
    @ManyToOne
    private Item itemFk;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "QTY")
    private Long qty;
    @Column(name = "VIRTUAL_")
    private Short virtual;
    @Column(name = "PUBLISH_")
    private Short publish;
    @Column(name = "EDITED")
    private boolean edited = false;
    @XmlTransient
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "itemdataFk")
    private List<Address> providerAddressList;
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemdataFk", orphanRemoval = true )
    private List<Avatar> providerAvatarList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "itemdataFk")
    private List<WishList> wishListList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "itemdataFk")
    private List<Recommendation> recommendationList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "itemdataFk")
    private List<MarketView> marketViewList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "itemdataFk")
    private List<AdsConfiguration> adsConfigurationList;    
        @Transient
    private String addressSring = Global.STR_EMPTY;
        @Transient
    private Long numOfOrders;
        @Transient
    private Long numOfViews;
        @Transient
    private Long numOfWishes;
        @Transient
    private Long numOfRecommendations;
        @Transient
    private Avatar lastAvatar;
        @Transient
    private int avatarRank;
        
        
        
        
//http://www.hostettler.net/blog/2012/03/22/one-to-one-relations-in-jpa-2-dot-0/
    public Itemdata() {
        providerAddressList = new ArrayList();
        providerAvatarList = new ArrayList();
        wishListList = new ArrayList();
        recommendationList = new ArrayList();
        marketViewList = new ArrayList();
        adsConfigurationList = new ArrayList();
    }

    public Itemdata(Long itemdataTId) {
        this.itemdataTId = itemdataTId;
        providerAddressList = new ArrayList();
        providerAvatarList = new ArrayList();
        wishListList = new ArrayList();
        recommendationList = new ArrayList();
        marketViewList = new ArrayList();
        adsConfigurationList = new ArrayList();
    }

    public Itemdata(Long itemdataTId, Date mdate) {
        this.itemdataTId = itemdataTId;
        this.mdate = mdate;
        providerAddressList = new ArrayList();
        providerAvatarList = new ArrayList();
        wishListList = new ArrayList();
        recommendationList = new ArrayList();
        marketViewList = new ArrayList();
        adsConfigurationList = new ArrayList();
    }

    public Long getItemdataTId() {
        return itemdataTId;
    }

    public void setItemdataTId(Long itemdataTId) {
        this.itemdataTId = itemdataTId;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date aDate) {
        this.mdate = aDate;
    }
    
    
    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date aDate) {
        this.sdate = aDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Item getItemFk() {
        return itemFk;
    }

    public void setItemFk(Item itemFk) {
        this.itemFk = itemFk;
    }
    
    public String getCurrentAvatar() {
        return currentAvatar;
    }

    public void setCurrentAvatar(String anAvatar) {
        this.currentAvatar = anAvatar;
    }
    
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Short getVirtual() {
        return virtual;
    }

    public void setVirtual(Short aVirtual) {
        this.virtual = aVirtual;
    }
    
    public Short getPublish() {
        return this.publish;
    }

    public void setPublish(Short aPublish) {
        this.publish = aPublish;
    }

    
    public List<Address> getProviderAddressList() {
        return providerAddressList;
    }

    public void setProviderAddressList(List<Address> providerAddressList) {
        this.providerAddressList = providerAddressList;
    }
    
    public void addProviderAddressToList(Address anAddress) {
        if (!getProviderAddressList().contains(anAddress)) {
            getProviderAddressList().add(anAddress);
            if (anAddress.getItemdataFk() != null) {
                anAddress.getItemdataFk().getProviderAddressList().remove(anAddress);
            }
            anAddress.setItemdataFk(this);
        }
    }
    
    public int getAvatarIndex(){
        return avatarRank;
    }
    
        public int setAvatarIndex(int aRank){
        return avatarRank = aRank;
    }
        
    public Avatar getAvatarFromRank(){
        return providerAvatarList.get(avatarRank);
    }
    
    public List<Avatar> getProviderAvatarList() {
        for(Avatar theavatar: providerAvatarList){
            //System.out.println("getProviderAvatarList-->theavatar.getItemdataFk(): "+theavatar.getItemdataFk());
        }
        return providerAvatarList;
    }

    public void setProviderAvatarList(List<Avatar> providerAvatarList) {
        this.providerAvatarList = providerAvatarList;
    }
    
    public Avatar getLastAvatar(){
        if(providerAvatarList.isEmpty()){
            lastAvatar = null;
        }
        else{
           lastAvatar =  providerAvatarList.get(providerAvatarList.size()-1); 
        } 
        return lastAvatar;
    }
    
    public void addProviderAvatarToList(Avatar anAvatar) {
        //System.out.println("addProviderAvatarToList, this: "+this);
        if (!getProviderAvatarList().contains(anAvatar)) {
            getProviderAvatarList().add(anAvatar);
            /*if (anAvatar.getItemdataFk() != null) {
                anAvatar.getItemdataFk().getProviderAvatarList().remove(anAvatar);
            }*/
            anAvatar.setItemdataFk(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemdataTId != null ? itemdataTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemdata)) {
            return false;
        }
        Itemdata other = (Itemdata) object;
        return !((this.itemdataTId == null && other.itemdataTId != null) || (this.itemdataTId != null && !this.itemdataTId.equals(other.itemdataTId)));
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.service.Itemdata[ itemdataTId=" + itemdataTId + " ]";
    }
    
    public boolean isEdited(){
        //System.out.println("isEdited: "+edited+", toString(): "+toString());
        return edited;
    }
    
    public void setEdited(boolean aBool){
        //System.out.println("aBool: "+aBool+", toString(): "+toString());
        edited = aBool;
    }
    
    public Long getNumOfOrders(){
        return numOfOrders;
    }
    
    public void setNumOfOrders(Long aLong){
        numOfOrders = aLong;
    }
    

    public Long getNumOfViews(){
        return numOfViews;
    }
    
    public void setNumOfViews(Long aLong){
        numOfViews = aLong;
    }
    
    public Long getNumOfWishes(){
        return numOfWishes;
    }
    
    public void setNumOfWishes(Long aLong){
        numOfWishes = aLong;
    }
    
    public Long getNumOfRecommendations(){
        return numOfRecommendations;
    }
    
    public void setEdited(Long aLong){
        numOfRecommendations = aLong;
    }
    
    public String getAddressString(){
        return addressSring;
    }
    
    public void setAddressString(){        
        if(providerAddressList == null) return;
        addressSring = "";
        for(Address theAddress: providerAddressList){
           addressSring +=  theAddress.toString();
        }
    }

    
    @XmlTransient
    public List<AdsConfiguration> getAdsConfigurationList() {
        return adsConfigurationList;
    }

    public void setAdsConfigurationList(List<AdsConfiguration> adsConfigurationList) {
        this.adsConfigurationList = adsConfigurationList;
    }

    @XmlTransient
    public List<WishList> getWishListList() {
        return wishListList;
    }

    public void setWishListList(List<WishList> wishListList) {
        this.wishListList = wishListList;
    }

    @XmlTransient
    public List<Recommendation> getRecommendationList() {
        return recommendationList;
    }

    public void setRecommendationList(List<Recommendation> recommendationList) {
        this.recommendationList = recommendationList;
    }

    @XmlTransient
    public List<MarketView> getMarketViewList() {
        return marketViewList;
    }

    public void setMarketViewList(List<MarketView> marketViewList) {
        this.marketViewList = marketViewList;
    }
}
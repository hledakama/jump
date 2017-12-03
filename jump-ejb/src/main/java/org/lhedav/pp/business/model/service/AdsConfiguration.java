/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity
@Table(name = "ADS_CONFIGURATION_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdsConfiguration.findAll", query = "SELECT a FROM AdsConfiguration a")
    , @NamedQuery(name = "AdsConfiguration.findByAdsConfigurationTId", query = "SELECT a FROM AdsConfiguration a WHERE a.adsConfigurationTId = :adsConfigurationTId")
    , @NamedQuery(name = "AdsConfiguration.findByShowSchedule", query = "SELECT a FROM AdsConfiguration a WHERE a.showSchedule = :showSchedule")
    , @NamedQuery(name = "AdsConfiguration.findByAcceptPostpone", query = "SELECT a FROM AdsConfiguration a WHERE a.acceptPostpone = :acceptPostpone")
    , @NamedQuery(name = "AdsConfiguration.findByAcceptInmail", query = "SELECT a FROM AdsConfiguration a WHERE a.acceptInmail = :acceptInmail")
    , @NamedQuery(name = "AdsConfiguration.findByAcceptChat", query = "SELECT a FROM AdsConfiguration a WHERE a.acceptChat = :acceptChat")
    , @NamedQuery(name = "AdsConfiguration.findByUseOnlinePayment", query = "SELECT a FROM AdsConfiguration a WHERE a.useOnlinePayment = :useOnlinePayment")
    , @NamedQuery(name = "AdsConfiguration.findByShareEmail", query = "SELECT a FROM AdsConfiguration a WHERE a.shareEmail = :shareEmail")
    , @NamedQuery(name = "AdsConfiguration.findByShareTelephone", query = "SELECT a FROM AdsConfiguration a WHERE a.shareTelephone = :shareTelephone")
    , @NamedQuery(name = "AdsConfiguration.findByAdsDisplay", query = "SELECT a FROM AdsConfiguration a WHERE a.adsDisplay = :adsDisplay")})
public class AdsConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_ads_config", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ADS_CONFIGURATION_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_ads_config" ) 
    @Column(name = "ADS_CONFIGURATION_T_ID")
    private Long adsConfigurationTId;
    @Column(name = "SHOW_SCHEDULE")
    private Short showSchedule;
    @Column(name = "ACCEPT_POSTPONE")
    private Short acceptPostpone;
    @Column(name = "ACCEPT_INMAIL")
    private Short acceptInmail;
    @Column(name = "ACCEPT_CHAT")
    private Short acceptChat;
    @Column(name = "USE_ONLINE_PAYMENT")
    private Short useOnlinePayment;
    @Column(name = "SHARE_EMAIL")
    private Short shareEmail;
    @Column(name = "SHARE_TELEPHONE")
    private Short shareTelephone;
    @Column(name = "ADS_DISPLAY")
    private Short adsDisplay;
    @JoinColumn(name = "ITEMDATA_FK", referencedColumnName = "ITEMDATA_T_ID")
    @ManyToOne
    private Itemdata itemdataFk;

    public AdsConfiguration() {
    }

    public AdsConfiguration(Long adsConfigurationTId) {
        this.adsConfigurationTId = adsConfigurationTId;
    }

    public Long getAdsConfigurationTId() {
        return adsConfigurationTId;
    }

    public void setAdsConfigurationTId(Long adsConfigurationTId) {
        this.adsConfigurationTId = adsConfigurationTId;
    }

    public Short getShowSchedule() {
        return showSchedule;
    }

    public void setShowSchedule(Short showSchedule) {
        this.showSchedule = showSchedule;
    }

    public Short getAcceptPostpone() {
        return acceptPostpone;
    }

    public void setAcceptPostpone(Short acceptPostpone) {
        this.acceptPostpone = acceptPostpone;
    }

    public Short getAcceptInmail() {
        return acceptInmail;
    }

    public void setAcceptInmail(Short acceptInmail) {
        this.acceptInmail = acceptInmail;
    }

    public Short getAcceptChat() {
        return acceptChat;
    }

    public void setAcceptChat(Short acceptChat) {
        this.acceptChat = acceptChat;
    }

    public Short getUseOnlinePayment() {
        return useOnlinePayment;
    }

    public void setUseOnlinePayment(Short useOnlinePayment) {
        this.useOnlinePayment = useOnlinePayment;
    }

    public Short getShareEmail() {
        return shareEmail;
    }

    public void setShareEmail(Short shareEmail) {
        this.shareEmail = shareEmail;
    }

    public Short getShareTelephone() {
        return shareTelephone;
    }

    public void setShareTelephone(Short shareTelephone) {
        this.shareTelephone = shareTelephone;
    }

    public Short getAdsDisplay() {
        return adsDisplay;
    }

    public void setAdsDisplay(Short adsDisplay) {
        this.adsDisplay = adsDisplay;
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
        hash += (adsConfigurationTId != null ? adsConfigurationTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdsConfiguration)) {
            return false;
        }
        AdsConfiguration other = (AdsConfiguration) object;
        if ((this.adsConfigurationTId == null && other.adsConfigurationTId != null) || (this.adsConfigurationTId != null && !this.adsConfigurationTId.equals(other.adsConfigurationTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.order.AdsConfiguration[ adsConfigurationTId=" + adsConfigurationTId + " ]";
    }
    
}

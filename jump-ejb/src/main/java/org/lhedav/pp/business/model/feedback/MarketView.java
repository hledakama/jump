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
@Table(name = "MARKET_VIEW_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarketView.findAll", query = "SELECT m FROM MarketView m")
    , @NamedQuery(name = "MarketView.findByMarketViewTId", query = "SELECT m FROM MarketView m WHERE m.marketViewTId = :marketViewTId")
    , @NamedQuery(name = "MarketView.findByViewer", query = "SELECT m FROM MarketView m WHERE m.viewer = :viewer")
    , @NamedQuery(name = "MarketView.findByViewDate", query = "SELECT m FROM MarketView m WHERE m.viewDate = :viewDate")})
public class MarketView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_view", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "MARKET_VIEW_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_view" ) 
    @Column(name = "MARKET_VIEW_T_ID")
    private Long marketViewTId;
    @Column(name = "VIEWER")
    private BigInteger viewer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIEW_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewDate;
    @JoinColumn(name = "ITEMDATA_FK", referencedColumnName = "ITEMDATA_T_ID")
    @ManyToOne
    private Itemdata itemdataFk;

    public MarketView() {
    }

    public MarketView(Long marketViewTId) {
        this.marketViewTId = marketViewTId;
    }

    public MarketView(Long marketViewTId, Date viewDate) {
        this.marketViewTId = marketViewTId;
        this.viewDate = viewDate;
    }

    public Long getMarketViewTId() {
        return marketViewTId;
    }

    public void setMarketViewTId(Long marketViewTId) {
        this.marketViewTId = marketViewTId;
    }

    public BigInteger getViewer() {
        return viewer;
    }

    public void setViewer(BigInteger viewer) {
        this.viewer = viewer;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
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
        hash += (marketViewTId != null ? marketViewTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarketView)) {
            return false;
        }
        MarketView other = (MarketView) object;
        if ((this.marketViewTId == null && other.marketViewTId != null) || (this.marketViewTId != null && !this.marketViewTId.equals(other.marketViewTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.feedback.MarketView[ marketViewTId=" + marketViewTId + " ]";
    }
    
}

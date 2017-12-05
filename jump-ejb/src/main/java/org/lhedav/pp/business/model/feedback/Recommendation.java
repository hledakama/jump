/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.feedback;

import java.io.Serializable;
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
import org.lhedav.pp.business.model.order.Order_;
import org.lhedav.pp.business.model.service.Itemdata;

/**
 *
 * @author client
 */
@Entity
@Table(name = "RECOMMENDATION_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recommendation.findAll", query = "SELECT r FROM Recommendation r")
    , @NamedQuery(name = "Recommendation.findByRecommendationTId", query = "SELECT r FROM Recommendation r WHERE r.recommendationTId = :recommendationTId")
    , @NamedQuery(name = "Recommendation.findByRecommendationDate", query = "SELECT r FROM Recommendation r WHERE r.recommendationDate = :recommendationDate")
    , @NamedQuery(name = "Recommendation.findByStatus", query = "SELECT r FROM Recommendation r WHERE r.status = :status")})
public class Recommendation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_recommendation", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "RECOMMENDATION_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_recommendation" ) 
    @Column(name = "RECOMMENDATION_T_ID")
    private Long recommendationTId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECOMMENDATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recommendationDate;
    @Column(name = "STATUS")
    private Short status;
    @JoinColumn(name = "ORDER_FK", referencedColumnName = "ORDER_T_ID")
    @ManyToOne
    private Order_ orderFk;
    @JoinColumn(name = "ITEMDATA_FK", referencedColumnName = "ITEMDATA_T_ID")
    @ManyToOne
    private Itemdata itemdataFk;

    public Recommendation() {
    }

    public Recommendation(Long recommendationTId) {
        this.recommendationTId = recommendationTId;
    }

    public Recommendation(Long recommendationTId, Date recommendationDate) {
        this.recommendationTId = recommendationTId;
        this.recommendationDate = recommendationDate;
    }

    public Long getRecommendationTId() {
        return recommendationTId;
    }

    public void setRecommendationTId(Long recommendationTId) {
        this.recommendationTId = recommendationTId;
    }

    public Date getRecommendationDate() {
        return recommendationDate;
    }

    public void setRecommendationDate(Date recommendationDate) {
        this.recommendationDate = recommendationDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Order_ getOrderFk() {
        return orderFk;
    }

    public void setOrderFk(Order_ orderFk) {
        this.orderFk = orderFk;
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
        hash += (recommendationTId != null ? recommendationTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recommendation)) {
            return false;
        }
        Recommendation other = (Recommendation) object;
        if ((this.recommendationTId == null && other.recommendationTId != null) || (this.recommendationTId != null && !this.recommendationTId.equals(other.recommendationTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.feedback.Recommendation[ recommendationTId=" + recommendationTId + " ]";
    }
    
}

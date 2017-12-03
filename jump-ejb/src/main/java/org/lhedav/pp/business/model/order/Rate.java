/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.order;

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

/**
 *
 * @author client
 */
@Entity
@Table(name = "RATE_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r")
    , @NamedQuery(name = "Rate.findByRateTId", query = "SELECT r FROM Rate r WHERE r.rateTId = :rateTId")
    , @NamedQuery(name = "Rate.findByCreation", query = "SELECT r FROM Rate r WHERE r.creation = :creation")
    , @NamedQuery(name = "Rate.findByQuality", query = "SELECT r FROM Rate r WHERE r.quality = :quality")
    , @NamedQuery(name = "Rate.findByEnvironment", query = "SELECT r FROM Rate r WHERE r.environment = :environment")
    , @NamedQuery(name = "Rate.findByTools", query = "SELECT r FROM Rate r WHERE r.tools = :tools")
    , @NamedQuery(name = "Rate.findByRespect", query = "SELECT r FROM Rate r WHERE r.respect = :respect")
    , @NamedQuery(name = "Rate.findByOnTime", query = "SELECT r FROM Rate r WHERE r.onTime = :onTime")
    , @NamedQuery(name = "Rate.findByInput", query = "SELECT r FROM Rate r WHERE r.input = :input")})
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_rate", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "RATE_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_rate" ) 
    @Column(name = "RATE_T_ID")
    private Long rateTId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Column(name = "QUALITY")
    private Short quality;
    @Column(name = "ENVIRONMENT")
    private Short environment;
    @Column(name = "TOOLS")
    private Short tools;
    @Column(name = "RESPECT")
    private Short respect;
    @Column(name = "ON_TIME")
    private Short onTime;
    @Column(name = "INPUT_")
    private Short input;
    @JoinColumn(name = "ORDER_FK", referencedColumnName = "ORDER_T_ID")
    @ManyToOne
    private Order_ orderFk;
    @JoinColumn(name = "ORDER_LINE_FK", referencedColumnName = "ORDER_LINE_T_ID")
    @ManyToOne
    private OrderLine orderLineFk;

    public Rate() {
    }

    public Rate(Long rateTId) {
        this.rateTId = rateTId;
    }

    public Rate(Long rateTId, Date creation) {
        this.rateTId = rateTId;
        this.creation = creation;
    }

    public Long getRateTId() {
        return rateTId;
    }

    public void setRateTId(Long rateTId) {
        this.rateTId = rateTId;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Short getQuality() {
        return quality;
    }

    public void setQuality(Short quality) {
        this.quality = quality;
    }

    public Short getEnvironment() {
        return environment;
    }

    public void setEnvironment(Short environment) {
        this.environment = environment;
    }

    public Short getTools() {
        return tools;
    }

    public void setTools(Short tools) {
        this.tools = tools;
    }

    public Short getRespect() {
        return respect;
    }

    public void setRespect(Short respect) {
        this.respect = respect;
    }

    public Short getOnTime() {
        return onTime;
    }

    public void setOnTime(Short onTime) {
        this.onTime = onTime;
    }

    public Short getInput() {
        return input;
    }

    public void setInput(Short input) {
        this.input = input;
    }

    public Order_ getOrderFk() {
        return orderFk;
    }

    public void setOrderFk(Order_ orderFk) {
        this.orderFk = orderFk;
    }

    public OrderLine getOrderLineFk() {
        return orderLineFk;
    }

    public void setOrderLineFk(OrderLine orderLineFk) {
        this.orderLineFk = orderLineFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rateTId != null ? rateTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.rateTId == null && other.rateTId != null) || (this.rateTId != null && !this.rateTId.equals(other.rateTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.order.Rate[ rateTId=" + rateTId + " ]";
    }
    
}

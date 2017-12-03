/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.order;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity
@Table(name = "INMAIL_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inmail.findAll", query = "SELECT i FROM Inmail i")
    , @NamedQuery(name = "Inmail.findByInmailTId", query = "SELECT i FROM Inmail i WHERE i.inmailTId = :inmailTId")
    , @NamedQuery(name = "Inmail.findByFromUserId", query = "SELECT i FROM Inmail i WHERE i.fromUserId = :fromUserId")
    , @NamedQuery(name = "Inmail.findByToOtherUserId", query = "SELECT i FROM Inmail i WHERE i.toOtherUserId = :toOtherUserId")
    , @NamedQuery(name = "Inmail.findBySent", query = "SELECT i FROM Inmail i WHERE i.sent = :sent")
    , @NamedQuery(name = "Inmail.findByReceive", query = "SELECT i FROM Inmail i WHERE i.receive = :receive")
    , @NamedQuery(name = "Inmail.findBySubject", query = "SELECT i FROM Inmail i WHERE i.subject = :subject")
    , @NamedQuery(name = "Inmail.findByContent", query = "SELECT i FROM Inmail i WHERE i.content = :content")})
public class Inmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_inmail", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "INMAIL_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_inmail" ) 
    @Column(name = "INMAIL_T_ID")
    private Long inmailTId;
    @Column(name = "FROM_USER_ID")
    private BigInteger fromUserId;
    @Column(name = "TO_OTHER_USER_ID")
    private BigInteger toOtherUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECEIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receive;
    @Size(max = 50)
    @Column(name = "SUBJECT")
    private String subject;
    @Size(max = 255)
    @Column(name = "CONTENT")
    private String content;
    @JoinColumn(name = "ORDER_FK", referencedColumnName = "ORDER_T_ID")
    @ManyToOne
    private Order_ orderFk;
    @JoinColumn(name = "ORDER_LINE_FK", referencedColumnName = "ORDER_LINE_T_ID")
    @ManyToOne
    private OrderLine orderLineFk;

    public Inmail() {
    }

    public Inmail(Long inmailTId) {
        this.inmailTId = inmailTId;
    }

    public Inmail(Long inmailTId, Date sent, Date receive) {
        this.inmailTId = inmailTId;
        this.sent = sent;
        this.receive = receive;
    }

    public Long getInmailTId() {
        return inmailTId;
    }

    public void setInmailTId(Long inmailTId) {
        this.inmailTId = inmailTId;
    }

    public BigInteger getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(BigInteger fromUserId) {
        this.fromUserId = fromUserId;
    }

    public BigInteger getToOtherUserId() {
        return toOtherUserId;
    }

    public void setToOtherUserId(BigInteger toOtherUserId) {
        this.toOtherUserId = toOtherUserId;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public Date getReceive() {
        return receive;
    }

    public void setReceive(Date receive) {
        this.receive = receive;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        hash += (inmailTId != null ? inmailTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inmail)) {
            return false;
        }
        Inmail other = (Inmail) object;
        if ((this.inmailTId == null && other.inmailTId != null) || (this.inmailTId != null && !this.inmailTId.equals(other.inmailTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.order.Inmail[ inmailTId=" + inmailTId + " ]";
    }
    
}

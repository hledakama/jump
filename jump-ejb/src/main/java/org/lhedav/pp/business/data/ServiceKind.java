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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author client
 */
@Entity
@Table(name = "SERVICE_KIND_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceKind.findAll", query = "SELECT s FROM ServiceKind s")
    , @NamedQuery(name = "ServiceKind.findByServiceKindTId", query = "SELECT s FROM ServiceKind s WHERE s.serviceKindTId = :serviceKindTId")
    , @NamedQuery(name = "ServiceKind.findByKind", query = "SELECT s FROM ServiceKind s WHERE s.kind = :kind")})
public class ServiceKind implements Serializable {

    @OneToMany(mappedBy = "serviceKindFk", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceType> serviceTypeList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_kind", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "SERVICE_KIND_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_kind" ) 
    @Column(name = "SERVICE_KIND_T_ID")
    private Long serviceKindTId;
    @Size(max = 50)
    @Column(name = "KIND")
    private String kind;

    public ServiceKind() {
        serviceTypeList = new ArrayList();
    }

    public ServiceKind(Long serviceKindTId) {
        this.serviceKindTId = serviceKindTId;
        serviceTypeList = new ArrayList();
    }

    public Long getServiceKindTId() {
        return serviceKindTId;
    }

    public void setServiceKindTId(Long serviceKindTId) {
        this.serviceKindTId = serviceKindTId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceKindTId != null ? serviceKindTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceKind)) {
            return false;
        }
        ServiceKind other = (ServiceKind) object;
        if ((this.serviceKindTId == null && other.serviceKindTId != null) || (this.serviceKindTId != null && !this.serviceKindTId.equals(other.serviceKindTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.data.ServiceKind[ serviceKindTId=" + serviceKindTId + " ]";
    }

    @XmlTransient
    public List<ServiceType> getServiceTypeList() {
        return serviceTypeList;
    }

    public void setServiceTypeList(List<ServiceType> serviceTypeList) {
        this.serviceTypeList = serviceTypeList;
    }
    
    public void addServiceTypeToList(ServiceType aServiceType) {
        if (!getServiceTypeList().contains(aServiceType)) {
            getServiceTypeList().add(aServiceType);
            /*if (aServiceType.getServiceKindFk() != null) {
                aServiceType.getServiceKindFk().getServiceTypeList().remove(aServiceType);
            }*/
            aServiceType.setServiceKindFk(this);
        }
    }
    
}

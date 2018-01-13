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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author client
 */
@Entity
@Table(name = "SERVICE_TYPE_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceType.findAll", query = "SELECT s FROM ServiceType s")
    , @NamedQuery(name = "ServiceType.findByServiceTypeTId", query = "SELECT s FROM ServiceType s WHERE s.serviceTypeTId = :serviceTypeTId")
    , @NamedQuery(name = "ServiceType.findByServiceKind", query = "SELECT s FROM ServiceType s WHERE s.serviceKindFk = :serviceKindFk")
    , @NamedQuery(name = "ServiceType.findByType", query = "SELECT s FROM ServiceType s WHERE s.type = :type")})
public class ServiceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_service_type", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "SERVICE_TYPE_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_service_type" )
    @Column(name = "SERVICE_TYPE_T_ID")
    private Long serviceTypeTId;
    @Size(max = 50)
    @Column(name = "TYPE")
    private String type;
    @JoinColumn(name = "SERVICE_KIND_FK", referencedColumnName = "SERVICE_KIND_T_ID")
    @ManyToOne
    private ServiceKind serviceKindFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceTypeFk", orphanRemoval = true)
    private List<Services> servicesList;

    public ServiceType() {
        servicesList = new ArrayList();
    }

    public ServiceType(Long serviceTypeTId) {
        this.serviceTypeTId = serviceTypeTId;
        servicesList = new ArrayList();
    }

    public Long getServiceTypeTId() {
        return serviceTypeTId;
    }

    public void setServiceTypeTId(Long serviceTypeTId) {
        this.serviceTypeTId = serviceTypeTId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ServiceKind getServiceKindFk() {
        return serviceKindFk;
    }

    public void setServiceKindFk(ServiceKind serviceKindFk) {
        this.serviceKindFk = serviceKindFk;
    }

    @XmlTransient
    public List<Services> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }
    
     public void addServicesToList(Services someServices) {
        if (!getServicesList().contains(someServices)) {
            getServicesList().add(someServices);
            /*if (someServices.getServiceTypeFk() != null) {
                someServices.getServiceTypeFk().getServicesList().remove(someServices);
            }*/
            someServices.setServiceTypeFk(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceTypeTId != null ? serviceTypeTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceType)) {
            return false;
        }
        ServiceType other = (ServiceType) object;
        if ((this.serviceTypeTId == null && other.serviceTypeTId != null) || (this.serviceTypeTId != null && !this.serviceTypeTId.equals(other.serviceTypeTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.data.ServiceType[ serviceTypeTId=" + serviceTypeTId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author client
 */
@Entity(name = "ProviderAddress")
@Table(name = "PROVIDER_ADDRESS_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProviderAddress.findAll", query = "SELECT p FROM ProviderAddress p")
    , @NamedQuery(name = "ProviderAddress.findByProviderAddressTId", query = "SELECT p FROM ProviderAddress p WHERE p.providerAddressTId = :providerAddressTId")
    , @NamedQuery(name = "ProviderAddress.findByStreet1", query = "SELECT p FROM ProviderAddress p WHERE p.street1 = :street1")
    , @NamedQuery(name = "ProviderAddress.findByStreet2", query = "SELECT p FROM ProviderAddress p WHERE p.street2 = :street2")
    , @NamedQuery(name = "ProviderAddress.findByStreetNumber", query = "SELECT p FROM ProviderAddress p WHERE p.streetNumber = :streetNumber")
    , @NamedQuery(name = "ProviderAddress.findByCity", query = "SELECT p FROM ProviderAddress p WHERE p.city = :city")
    , @NamedQuery(name = "ProviderAddress.findByState", query = "SELECT p FROM ProviderAddress p WHERE p.state = :state")
    , @NamedQuery(name = "ProviderAddress.findByZipcode", query = "SELECT p FROM ProviderAddress p WHERE p.zipcode = :zipcode")
    , @NamedQuery(name = "ProviderAddress.findByCountry", query = "SELECT p FROM ProviderAddress p WHERE p.country = :country")
})
public class ProviderAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_provider_address", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "PROVIDER_ADDRESS_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_provider_address" ) 
    @Column(name = "PROVIDER_ADDRESS_T_ID")
    private Long providerAddressTId;
    @Size(max = 50)
    @Column(name = "STREET1")
    private String street1;
    @Size(max = 50)
    @Column(name = "STREET2")
    private String street2;
    @Size(max = 6)
    @Column(name = "STREET_NUMBER")
    private String streetNumber;
    @Size(max = 50)
    @Column(name = "CITY")
    private String city;
    @Size(max = 50)
    @Column(name = "STATE")
    private String state;
    @Size(max = 50)
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Size(max = 50)
    @Column(name = "COUNTRY")
    private String country;
    @JoinColumn(name = "ITEMDATA_FK", referencedColumnName = "ITEMDATA_T_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Itemdata itemdataFk;
    private boolean edited = false;

    public ProviderAddress() {
    }

    public ProviderAddress(Long providerAddressTId) {
        this.providerAddressTId = providerAddressTId;
    }

    public Long getProviderAddressTId() {
        return providerAddressTId;
    }

    public void setProviderAddressTId(Long providerAddressTId) {
        this.providerAddressTId = providerAddressTId;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        hash += (providerAddressTId != null ? providerAddressTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProviderAddress)) {
            return false;
        }
        ProviderAddress other = (ProviderAddress) object;
        if ((this.providerAddressTId == null && other.providerAddressTId != null) || (this.providerAddressTId != null && !this.providerAddressTId.equals(other.providerAddressTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.lhedav.pp.business.model.service.ProviderAddress[ providerAddressTId=" + providerAddressTId + " ]";
        return streetNumber +", "+ street1 + ", "+ city + ", "+ state + ", "+ "\n"+ country+ ", "+ zipcode + "\n";
        
    }
    
    public boolean isEdited(){
        return edited;
    }
    
    public void setEdited(boolean aBool){
        edited = aBool;
    }
    
}

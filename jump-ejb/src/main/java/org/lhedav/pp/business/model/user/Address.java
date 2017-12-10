/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.user;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.lhedav.pp.business.bean_validation.generic.ZipCode;
import org.lhedav.pp.business.model.service.Itemdata;

/**
 *
 * @author client
 */
@Entity(name = "Address")
@Table(name = "ADDRESS_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT p FROM Address p")
    , @NamedQuery(name = "Address.findByAddressTId", query = "SELECT p FROM Address p WHERE p.addressTId = :addressTId")
    , @NamedQuery(name = "Address.findByStreet1", query = "SELECT p FROM Address p WHERE p.street1 = :street1")
    , @NamedQuery(name = "Address.findByStreet2", query = "SELECT p FROM Address p WHERE p.street2 = :street2")
    , @NamedQuery(name = "Address.findByStreetNumber", query = "SELECT p FROM Address p WHERE p.streetNumber = :streetNumber")
    , @NamedQuery(name = "Address.findByCity", query = "SELECT p FROM Address p WHERE p.city = :city")
    , @NamedQuery(name = "Address.findByState", query = "SELECT p FROM Address p WHERE p.state = :state")
    , @NamedQuery(name = "Address.findByZipcode", query = "SELECT p FROM Address p WHERE p.zipcode = :zipcode")
    , @NamedQuery(name = "Address.findByCountry", query = "SELECT p FROM Address p WHERE p.country = :country")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_address", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ADDRESS_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_address" )     
    @Column(name = "ADDRESS_T_ID")
    private Long addressTId;
    @Size(max = 50, min = 2)
    @NotNull
    @Column(name = "STREET1")
    private String street1;
    @Size(max = 50, min = 2)
    @Column(name = "STREET2")
    private String street2;
    @Size(max = 6, min = 2)
    @Column(name = "STREET_NUMBER")
    private String streetNumber;
    @Size(max = 50, min = 2)
    @Column(name = "CITY")
    @NotNull
    private String city;
    @Size(max = 50, min = 2)
    @Column(name = "STATE")
    private String state;
    @Size(max = 50)
    @Column(name = "ZIPCODE")
    @ZipCode
    @NotNull
    private String zipcode;
    @Size(max = 50, min = 3)
    @Column(name = "COUNTRY")
    private String country;
    @JoinColumn(name = "ITEMDATA_FK", referencedColumnName = "ITEMDATA_T_ID")
    @ManyToOne
    private Itemdata itemdataFk;
    @JoinColumn(name = "PROFILE_FK", referencedColumnName = "PROFILE_T_ID")
    @ManyToOne
    private Profile profileFk;
    @Transient
    private boolean edited = false;

    public Address() {
    }

    public Address(Long providerAddressTId) {
        this.addressTId = providerAddressTId;
    }

    public Long getAddressTId() {
        return addressTId;
    }

    public void setAddressTId(Long providerAddressTId) {
        this.addressTId = providerAddressTId;
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
        hash += (addressTId != null ? addressTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.addressTId == null && other.addressTId != null) || (this.addressTId != null && !this.addressTId.equals(other.addressTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.lhedav.pp.business.model.service.Address[ providerAddressTId=" + providerAddressTId + " ]";
        return streetNumber +", "+ street1 + ", "+ city + ", "+ state + ", "+ "\n"+ country+ ", "+ zipcode + "\n";
        
    }
    
    public boolean isEdited(){
        return edited;
    }
    
    public void setEdited(boolean aBool){
        edited = aBool;
    }

    public Profile getProfileFk() {
        return profileFk;
    }

    public void setProfileFk(Profile profileFk) {
        this.profileFk = profileFk;
    }

    
}

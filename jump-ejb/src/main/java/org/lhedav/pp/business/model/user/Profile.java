/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.user;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author client
 */
@Entity
@Table(name = "PROFILE_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
    , @NamedQuery(name = "Profile.findByProfileTId", query = "SELECT p FROM Profile p WHERE p.profileTId = :profileTId")
    , @NamedQuery(name = "Profile.findByCreation", query = "SELECT p FROM Profile p WHERE p.creation = :creation")
    , @NamedQuery(name = "Profile.findByFirstName", query = "SELECT p FROM Profile p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Profile.findByLastName", query = "SELECT p FROM Profile p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Profile.findByEmail", query = "SELECT p FROM Profile p WHERE p.email = :email")
    , @NamedQuery(name = "Profile.findByPassword", query = "SELECT p FROM Profile p WHERE p.password = :password")
    , @NamedQuery(name = "Profile.findByMobilePhone", query = "SELECT p FROM Profile p WHERE p.mobilePhone = :mobilePhone")})
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
     @TableGenerator( name = "sequence_profile", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "PROFILE_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_profile" )
    @Column(name = "PROFILE_T_ID")
    private Long profileTId;
    @Basic(optional = false)
    @Column(name = "CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Size(max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "PASSWORD_")
    private String password;
    @Size(max = 10)
    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;
    @JoinColumn(name = "ACCOUNT_FK", referencedColumnName = "ACCOUNT_T_ID")
    @ManyToOne
    private Account accountFk;    
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profileFk")
    private List<Address> addressList;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profileFk")
    private List<Avatar> avatarList;

    public Profile() {
    }

    public Profile(Long profileTId) {
        this.profileTId = profileTId;
    }

    public Profile(Long profileTId, Date creation) {
        this.profileTId = profileTId;
        this.creation = creation;
    }

    public Long getProfileTId() {
        return profileTId;
    }

    public void setProfileTId(Long profileTId) {
        this.profileTId = profileTId;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Account getAccountFk() {
        return accountFk;
    }

    public void setAccountFk(Account accountFk) {
        this.accountFk = accountFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profileTId != null ? profileTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.profileTId == null && other.profileTId != null) || (this.profileTId != null && !this.profileTId.equals(other.profileTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.user.Profile[ profileTId=" + profileTId + " ]";
    }

    @XmlTransient
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @XmlTransient
    public List<Avatar> getAvatarList() {
        return avatarList;
    }

    public void setAvatarList(List<Avatar> avatarList) {
        this.avatarList = avatarList;
    }
    
}

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.lhedav.pp.business.model.service.Itemdata;

/**
 *
 * @author client
 */
@Entity(name = "Avatar")
@Table(name = "AVATAR_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avatar.findAll", query = "SELECT p FROM Avatar p")
    , @NamedQuery(name = "Avatar.findByAvatarTId", query = "SELECT p FROM Avatar p WHERE p.avatarTId = :avatarTId")
    , @NamedQuery(name = "Avatar.findByFileName", query = "SELECT p FROM Avatar p WHERE p.fileName = :fileName")
    , @NamedQuery(name = "Avatar.findBySubmitedFileName", query = "SELECT p FROM Avatar p WHERE p.submitedFileName = :submitedFileName")
    , @NamedQuery(name = "Avatar.findByMimeType", query = "SELECT p FROM Avatar p WHERE p.mimeType = :mimeType")
    , @NamedQuery(name = "Avatar.findByFileSize", query = "SELECT p FROM Avatar p WHERE p.fileSize = :fileSize")
    , @NamedQuery(name = "Avatar.findByLocation", query = "SELECT p FROM Avatar p WHERE p.location = :location")})
public class Avatar implements Serializable {

    @JoinColumn(name = "PROFILE_FK", referencedColumnName = "PROFILE_T_ID")
    @ManyToOne
    private Profile profileFk;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_avatar", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "AVATAR_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_avatar" )
    @Column(name = "AVATAR_T_ID")
    private Long avatarTId;
    @Size(max = 255)
    @Column(name = "FILE_NAME")
    private String fileName;
    @Size(max = 255)
    @Column(name = "SUBMITED_FILE_NAME")
    private String submitedFileName;
    @Size(max = 50)
    @Column(name = "MIME_TYPE")
    private String mimeType;
    @Column(name = "FILE_SIZE")
    private Long fileSize;
    @Size(max = 255)
    @Column(name = "LOCATION")
    private String location;
    @JoinColumn(name = "ITEMDATA_FK", referencedColumnName = "ITEMDATA_T_ID")
    @ManyToOne
    private Itemdata itemdataFk;

    public Avatar() {
    }

    public Avatar(Long providerAvatarTId) {
        this.avatarTId = providerAvatarTId;
    }

    public Long getAvatarTId() {
        return avatarTId;
    }

    public void setAvatarTId(Long providerAvatarTId) {
        this.avatarTId = providerAvatarTId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubmitedFileName() {
        return submitedFileName;
    }

    public void setSubmitedFileName(String submitedFileName) {
        this.submitedFileName = submitedFileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        hash += (avatarTId != null ? avatarTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avatar)) {
            return false;
        }
        Avatar other = (Avatar) object;
        if ((this.avatarTId == null && other.avatarTId != null) || (this.avatarTId != null && !this.avatarTId.equals(other.avatarTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.service.Avatar[ providerAvatarTId=" + avatarTId + " ]";
    }

    public Profile getProfileFk() {
        return profileFk;
    }

    public void setProfileFk(Profile profileFk) {
        this.profileFk = profileFk;
    }

}

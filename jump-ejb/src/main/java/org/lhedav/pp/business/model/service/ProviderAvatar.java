/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

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

/**
 *
 * @author client
 */
@Entity(name = "ProviderAvatar")
@Table(name = "PROVIDER_AVATAR_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProviderAvatar.findAll", query = "SELECT p FROM ProviderAvatar p")
    , @NamedQuery(name = "ProviderAvatar.findByProviderAvatarTId", query = "SELECT p FROM ProviderAvatar p WHERE p.providerAvatarTId = :providerAvatarTId")
    , @NamedQuery(name = "ProviderAvatar.findByFileName", query = "SELECT p FROM ProviderAvatar p WHERE p.fileName = :fileName")
    , @NamedQuery(name = "ProviderAvatar.findBySubmitedFileName", query = "SELECT p FROM ProviderAvatar p WHERE p.submitedFileName = :submitedFileName")
    , @NamedQuery(name = "ProviderAvatar.findByMimeType", query = "SELECT p FROM ProviderAvatar p WHERE p.mimeType = :mimeType")
    , @NamedQuery(name = "ProviderAvatar.findByFileSize", query = "SELECT p FROM ProviderAvatar p WHERE p.fileSize = :fileSize")
    , @NamedQuery(name = "ProviderAvatar.findByLocation", query = "SELECT p FROM ProviderAvatar p WHERE p.location = :location")})
public class ProviderAvatar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_provider_avatar", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "PROVIDER_AVATAR_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_provider_avatar" )
    @Column(name = "PROVIDER_AVATAR_T_ID")
    private Long providerAvatarTId;
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

    public ProviderAvatar() {
    }

    public ProviderAvatar(Long providerAvatarTId) {
        this.providerAvatarTId = providerAvatarTId;
    }

    public Long getProviderAvatarTId() {
        return providerAvatarTId;
    }

    public void setProviderAvatarTId(Long providerAvatarTId) {
        this.providerAvatarTId = providerAvatarTId;
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
        hash += (providerAvatarTId != null ? providerAvatarTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProviderAvatar)) {
            return false;
        }
        ProviderAvatar other = (ProviderAvatar) object;
        if ((this.providerAvatarTId == null && other.providerAvatarTId != null) || (this.providerAvatarTId != null && !this.providerAvatarTId.equals(other.providerAvatarTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.service.ProviderAvatar[ providerAvatarTId=" + providerAvatarTId + " ]";
    }
    
}

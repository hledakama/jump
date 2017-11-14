/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import javax.persistence.Transient;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.lhedav.pp.business.model.common.Global;

/**
 *
 * @author client
 */
@Entity(name = "Itemdata")
@Table(name = "ITEMDATA_T")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemdata.findAll", query = "SELECT i FROM Itemdata i")
    , @NamedQuery(name = "Itemdata.findByItemdataTId", query = "SELECT i FROM Itemdata i WHERE i.itemdataTId = :itemdataTId")
    , @NamedQuery(name = "Itemdata.findByMdate", query = "SELECT i FROM Itemdata i WHERE i.mdate = :mdate")
    , @NamedQuery(name = "Itemdata.findByComment", query = "SELECT i FROM Itemdata i WHERE i.comment = :comment")
    , @NamedQuery(name = "Itemdata.findByDuration", query = "SELECT i FROM Itemdata i WHERE i.duration = :duration")
    , @NamedQuery(name = "Itemdata.findByUnit", query = "SELECT i FROM Itemdata i WHERE i.unit = :unit")})
public class Itemdata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @TableGenerator( name = "sequence_itemdata", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "ITEMDATA_T_ID", valueColumnName = "SEQ_COUNT", initialValue = 0, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "sequence_itemdata" ) 
    @Column(name = "ITEMDATA_T_ID")
    private Long itemdataTId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mdate;
    @Size(max = 255)
    @Column(name = "COMMENT_")
    private String comment;
    @Column(name = "DURATION")
    private Long duration;
    @Size(max = 10)
    @Column(name = "UNIT")
    private String unit;
    @JoinColumn(name = "ITEM_FK", referencedColumnName = "ITEM_T_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Item itemFk;
    @XmlTransient
    @OneToMany(mappedBy = "itemdataFk", cascade = CascadeType.ALL)
    private List<ProviderAddress> providerAddressList;
    @OneToMany(mappedBy = "itemdataFk", cascade = CascadeType.ALL)
    private List<ProviderAvatar> providerAvatarList;
        @Transient
    private boolean edited = false;
        @Transient
    private String addressSring = Global.STR_EMPTY;
        @Transient
    private Part file;
    @Transient
    private String currentAvatar = "Current Avatar";
            @Transient
    private boolean uploadValidated = false;

    public Itemdata() {
        providerAddressList = new ArrayList();
        providerAvatarList = new ArrayList();
    }

    public Itemdata(Long itemdataTId) {
        this.itemdataTId = itemdataTId;
        providerAddressList = new ArrayList();
        providerAvatarList = new ArrayList();
    }

    public Itemdata(Long itemdataTId, Date mdate) {
        this.itemdataTId = itemdataTId;
        this.mdate = mdate;
        providerAddressList = new ArrayList();
        providerAvatarList = new ArrayList();
    }

    public Long getItemdataTId() {
        return itemdataTId;
    }

    public void setItemdataTId(Long itemdataTId) {
        this.itemdataTId = itemdataTId;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Item getItemFk() {
        return itemFk;
    }

    public void setItemFk(Item itemFk) {
        this.itemFk = itemFk;
    }
    
    public String getCurrentAvatar() {
        return currentAvatar;
    }

    public void setCurrentAvatar(String anAvatar) {
        this.currentAvatar = anAvatar;
    }

    
    public List<ProviderAddress> getProviderAddressList() {
        return providerAddressList;
    }

    public void setProviderAddressList(List<ProviderAddress> providerAddressList) {
        this.providerAddressList = providerAddressList;
    }
    
    public void addProviderAddressToList(ProviderAddress anAddress) {
        if (!getProviderAddressList().contains(anAddress)) {
            getProviderAddressList().add(anAddress);
            if (anAddress.getItemdataFk() != null) {
                anAddress.getItemdataFk().getProviderAddressList().remove(anAddress);
            }
            anAddress.setItemdataFk(this);
        }
    }

    @XmlTransient
    public List<ProviderAvatar> getProviderAvatarList() {
        for(ProviderAvatar theavatar: providerAvatarList){
            System.out.println("getProviderAvatarList-->theavatar.getItemdataFk(): "+theavatar.getItemdataFk());
        }
        return providerAvatarList;
    }

    public void setProviderAvatarList(List<ProviderAvatar> providerAvatarList) {
        this.providerAvatarList = providerAvatarList;
    }
    
    public void addProviderAvatarToList(ProviderAvatar anAvatar) {
        System.out.println("addProviderAvatarToList, this: "+this);
        if (!getProviderAvatarList().contains(anAvatar)) {
            getProviderAvatarList().add(anAvatar);
            if (anAvatar.getItemdataFk() != null) {
                anAvatar.getItemdataFk().getProviderAvatarList().remove(anAvatar);
            }
            anAvatar.setItemdataFk(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemdataTId != null ? itemdataTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemdata)) {
            return false;
        }
        Itemdata other = (Itemdata) object;
        if ((this.itemdataTId == null && other.itemdataTId != null) || (this.itemdataTId != null && !this.itemdataTId.equals(other.itemdataTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.lhedav.pp.business.model.service.Itemdata[ itemdataTId=" + itemdataTId + " ]";
    }
    
    public boolean isEdited(){
        return edited;
    }
    
    public void setEdited(boolean aBool){
        edited = aBool;
    }
            
    public boolean isUploadValidated(){
        return uploadValidated;
    }
    
    public void setUploadValidated(boolean aBool){
        uploadValidated = aBool;
    }
    
    public String getAddressString(){
        return addressSring;
    }
    
    public void setAddressString(){        
        if(providerAddressList == null) return;
        addressSring = "";
        for(ProviderAddress theAddress: providerAddressList){
           addressSring +=  theAddress.toString();
        }
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public boolean validateFile() {
        String name = file.getSubmittedFileName();
        if (name.length() == 0) {
            resetFile();
            setUploadValidated(false);
            return isUploadValidated();           
        } else if (name.length() > Global.MAX_IMAGE_FILE_NAME_LENGTH) {
            resetFile();
            setUploadValidated(false);
            return isUploadValidated();
        } else if ((!Global.PNG_FILE_TYPE.equals(file.getContentType())) && (!Global.JPEG_FILE_TYPE.equals(file.getContentType()))) {
            resetFile();
            setUploadValidated(false);            
            return isUploadValidated();
        } else if (file.getSize() > Global.MAX_IMAGE_FILE_SIZE) {
            resetFile();
            setUploadValidated(false);
            return isUploadValidated();
        }
        setUploadValidated(true);        
        return isUploadValidated();
    }

    public ProviderAvatar saveFileToDisk() {
        ProviderAvatar theAvatar = new ProviderAvatar();
        if (file != null) {                                    
            try { 
                //https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
                Global.CheckCreateDirectories();
                //https://stackoverflow.com/questions/6233541/java-set-file-permissions-to-777-while-creating-a-file-object
                InputStream inputStream = file.getInputStream();
                String theSubmitedFileName = (new Date()).getTime()+Global.FILE_SPITTER+file.getSubmittedFileName();
                try (FileOutputStream outputStream = Global.openItemdataForWrite( theSubmitedFileName )) {
                    int bytesRead;
                    final byte[] chunck = new byte[1024];
                    while ((bytesRead = inputStream.read(chunck)) != -1){
                        outputStream.write(chunck, 0, bytesRead);
                    }
                }                
                theAvatar.setFileName(file.getName());
                theAvatar.setFileSize(file.getSize());
                theAvatar.setLocation(Global.diritemdata.getAbsolutePath());
                theAvatar.setMimeType(file.getContentType());
                theAvatar.setSubmitedFileName(theSubmitedFileName);
                addProviderAvatarToList(theAvatar);
                resetFile();
                setMdate(new Date());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload successfully ended!"));
            } catch (IOException e) {                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
            }
        }
        else{   
                theAvatar.setLocation(Global.diritemdata.getAbsolutePath());
                theAvatar.setSubmitedFileName(Global.DEFAULT_SHOPPING_IMAGE_NAME);
                addProviderAvatarToList(theAvatar);
        }
        return theAvatar;
    }

    public void resetFile() {
        try {
            if (file != null) {
                file.delete();
            }
        } catch (IOException ex) {
            Logger.getLogger(Itemdata.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = null;
    }    
}

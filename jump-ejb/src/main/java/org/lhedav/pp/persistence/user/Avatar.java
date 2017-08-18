package org.lhedav.pp.persistence.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.lhedav.pp.persistence.common.Shareable;

@Entity
public class Avatar implements Shareable, Serializable{
private Long m_id;
private Integer m_version;
private String m_file_name;
private String m_mime_type;
private int file_size;

	public Avatar(){
		
	}
	
	Avatar(String aFileName,
		   String aMimeType,
		   int aFileSize){
		
		setFileName(aFileName);
		setMimeType(aMimeType);
		setFileSize(aFileSize);
	}
	
	public void setId(Long anId){
		m_id = anId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return m_id;
	}
	
	@Version
	public Integer getVersion(){
		return m_version;
	}
        
        public void setVersion(Integer aVersion){
	     m_version = aVersion;
	}
	
	public void setFileName(String aFileName){
		m_file_name = aFileName;
	}
	
	public String getMimeType(){
		return m_mime_type;
	}
	
	public void setMimeType(String aMimeType){
		m_mime_type = aMimeType;
	}
	
	public String getFileName(){
		return m_file_name;
	}
	
	public void setFileSize(int aFileSize){
		file_size = aFileSize;
	}
	
	public int getFileSize(){
		return file_size;
	}
	
	@Override
	public boolean SendTo(DataOutputStream os) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean RecvFrom(DataInputStream is, int aVersion) {
		// TODO Auto-generated method stub
		return false;
	}

}

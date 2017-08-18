package org.lhedav.pp.persistence.service;

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
public class Consumer implements Shareable, Serializable{	
	
	private Long m_id;
	private Integer m_version;		
	
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

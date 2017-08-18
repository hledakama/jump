package org.lhedav.pp.persistence.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.lhedav.pp.persistence.common.Shareable;

@Entity
public class Membership implements Shareable, Serializable{
	private Long m_id;
	private Integer m_version;
	private String m_type;
	private Date m_starting;
	private Date m_ending;
	private String m_promotion_code;
	
	public Membership(){
		
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
	
	public void setType(String aType){
		m_type = aType;
	}
	
	public String getType(){
		return m_type;
	}
	
	public void setStartingDate(Date aDate){
		m_starting = aDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartingDate(){
		return m_starting;
	}
	
	public void setEndingDate(Date aDate){
		m_ending = aDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndingDate(){
		return m_ending;
	}
	
	public void setPromotionCode(String aCode){
		m_promotion_code = aCode;
	}
	
	public String getPromotionCode(){
		return m_promotion_code;
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

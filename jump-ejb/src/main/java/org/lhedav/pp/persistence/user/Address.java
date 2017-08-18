package org.lhedav.pp.persistence.user;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import org.lhedav.pp.persistence.common.Shareable;

// to use in customers and providers

/**
 *
 * @author client
 */
@Entity
/*@NamedQueries({
	@NamedQuery(name = Address.FIND_ALL_ADDRESS,  query="SELECT p.m_id, p.m_username, "
			+ "									 a.m_zip_code "
			+ "									 FROM Profile p "
			+ "									 INNER JOIN Address a ON p.m_id = a.profile_fk"),
	
	@NamedQuery(name = Address.FIND_BY_ZIP_CODE, query="SELECT p.m_id, p.m_username, "
			+ "											a.m_zip_code "
			+ "											FROM Profile p "
			+ "											INNER JOIN Address a ON p.m_id = a.profile_fk "
			+ "											WHERE a.m_zip_code = :zip_code"),
	
	@NamedQuery(name = Address.FIND_BY_STREET_AND_ZIP_CODE, query="SELECT p.m_id, p.m_username, "
			+ "														a.m_zip_code, a.m_street1 "
			+ "														FROM Profile p "
			+ "														INNER JOIN Address a ON p.m_id = a.profile_fk "
			+ "														WHERE a.m_zip_code = :zip_code AND a.m_street1 = :street1"),
	@NamedQuery(name = Address.FIND_BY_CITY_AND_STATE,     query="SELECT p.m_id, p.m_username, "
			+ "														a.m_zip_code, a.m_city, a.m_state "
			+ "														FROM Profile p "
			+ "														INNER JOIN Address a ON p.m_id = a.profile_fk "
			+ "														WHERE a.m_city = :city AND a.m_state = :state"),
	
	@NamedQuery(name = Address.FIND_BY_STATE_AND_COUNTRY,    query="SELECT p.m_id, p.m_username, "
			+ "														a.m_zip_code, a.m_city, a.m_state, a.m_country "
			+ "														FROM Profile p INNER JOIN Address a ON p.m_id = a.profile_fk "
			+ "														WHERE a.m_state = :sate AND a.m_country = :country"),
	
	@NamedQuery(name = Address.FIND_BY_COUNTRY,  query="SELECT p.m_id, p.m_username, "
			+ "											a.m_zip_code, a.m_city, a.m_state, a.m_country "
			+ "											FROM Profile p INNER JOIN Address a ON p.m_id = a.profile_fk "
			+ "											WHERE a.m_country = :country"),
	
	@NamedQuery(name = Address.CHECK_FOR_MATCH,  query="SELECT a "
			+ "											FROM Address a "
			+ "											WHERE (a.m_street1 = :street1) AND (a.m_street2 = :street2) AND (a.m_city = :city) AND (a.m_state = :state) AND (a.m_zip_code = :zip_code) AND (a.m_country = :country)"),
	
	@NamedQuery(name = Address.FIND_ADDRESSES_BY_PROFILE_ID,  query="SELECT a "
			+ "														FROM Address a "
			+ "														WHERE a.ptofile_fk = :profile_id") // ptofile_fk is also stored in PROFILE table
})*/
public class Address implements Shareable, Serializable{
	private Long m_id;
	private Integer m_version;
	private String m_street1;
	private String m_street2;
	private String m_city;
	private String m_state;
	private String m_zip_code;
	private String m_country;
	
	public final static String FIND_ALL_ADDRESS                     = "FIND_ALL_ADDRESS";
	public final static String FIND_BY_ZIP_CODE             = "FIND_BY_ZIP_CODE";
	public final static String FIND_BY_STREET_AND_ZIP_CODE  = "FIND_BY_STREET_AND_ZIP_CODE";
	public final static String FIND_BY_CITY_AND_STATE       = "FIND_BY_CITY_AND_STATE";
	public final static String FIND_BY_STATE_AND_COUNTRY    = "FIND_BY_STATE_AND_COUNTRY";
	public final static String FIND_BY_COUNTRY              = "FIND_BY_COUNTRY";
	public final static String FIND_ADDRESSES_BY_PROFILE_ID = "FIND_ADDRESSES_BY_PROFILE_ID";
	public final static String CHECK_FOR_MATCH              = "CHECK_FOR_MATCH";

	public Address(){

	}
	
	Address(String aStreet1,
			String aStreet2,
			String aCity,
			String aState,
			String aZipCode,
			String aCountry){
		
		setFirstStreet(aStreet1);
		setSecondStreet(aStreet2);
		setCity(aCity);
		setState(aState);
		setZipCode(aZipCode);
		setCountry(aCountry);		

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

	public void setFirstStreet(String aStreet){
		m_street1 = aStreet;
	}

	public String getFirstStreet(){
		return m_street1;
	}

	public void setSecondStreet(String aStreet){
		m_street2 = aStreet;
	}

	public String getSecondStreet(){
		return m_street2;
	}

	public void setCity(String aCity){
		m_city = aCity;
	}

	public String getCity(){
		return m_city;
	}

	public void setState(String aState){
		m_state = aState;
	}

	public String getState(){
		return m_state;
	}

	public void setZipCode(String aZipCode){
		m_zip_code = aZipCode;
	}

	public String getZipCode(){
		return m_zip_code;
	}

	public void setCountry(String aCountry){
		m_country = aCountry;
	}

	public String getCountry(){
		return m_country;
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

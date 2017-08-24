package org.lhedav.pp.business.model.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.lhedav.pp.business.model.common.Shareable;




@Entity
/*@NamedQueries({
	@NamedQuery(name = Connection_.FIND_ALL_CONNECTION,  query="SELECT u.m_id, u.profile_fk, "
			+ "									    c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city,"
			+ "									    p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender "
			+ "									    FROM User_ u INNER JOIN Connection_ c ON u.m_id = c.user_fk"
			+ "                                     INNER JOIN profile p ON p.m_id = u.profile_fk"),
	
	@NamedQuery(name = Connection_.FIND_CONNECTION_BETWEEN, query="SELECT u.m_id, u.profile_fk, "
			+ "									      c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city,"
			+ "									      p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender "
			+ "									      FROM User_ u INNER JOIN Connection_ c ON u.m_id = c.user_fk"
			+ "                                       INNER JOIN profile p ON p.m_id = u.profile_fk WHERE c.m_date BETWEEN  :starting_date and :ending_date"),
	
	@NamedQuery(name = Connection_.FIND_CONNECTION_BY_USER_BETWEEN, query="SELECT u.m_id, u.profile_fk, "
			+ "													c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city,"
			+ "													p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender "
			+ "													FROM User_ u INNER JOIN Connection_ c ON u.m_id = c.user_fk"
			+ "                                  				INNER JOIN profile p ON p.m_id = u.profile_fk WHERE (c.m_date BETWEEN  :starting_date and :ending_date) AND (p.m_username = :username)"),
	
	@NamedQuery(name = Connection_.FIND_CONNECTION_BY_CITY_AND_STATE_BETWEEN,     query="SELECT u.m_id, u.profile_fk, "
			+ "																c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city,"
			+ "																p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender  "
			+ "									 							FROM User_ u INNER JOIN Connection_ c ON u.m_id = c.user_fk"
			+ "                                  							INNER JOIN profile p ON p.m_id = u.profile_fk WHERE (c.m_date BETWEEN  :starting_date and :ending_date) AND (c.m_city = :city)AND (c.m_state = :state)"),
	
	@NamedQuery(name = Connection_.FIND_CONNECTION_BY_COUNTRY_AND_STATE_BETWEEN,    query="SELECT u.m_id, u.profile_fk, "
			+ "																	c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city,"
			+ "																	p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender  "
			+ "									 								FROM User_ u INNER JOIN Connection_ c ON u.m_id = c.user_fk"
			+ "                                  								INNER JOIN profile p ON p.m_id = u.profile_fk WHERE (c.m_date BETWEEN  :starting_date and :ending_date) AND (c.m_country = :country)AND (c.m_state = :state)")
})*/
public class Connection_ implements Shareable, Serializable{
	private Long m_id;
	private Integer m_version;
	private String m_ip_address;
	private Date m_date;
	private String m_country;
	private String m_state;
	private String m_city;
	private Configuration m_configuration;
	
	public static final String FIND_ALL_CONNECTION = "FIND_ALL_CONNECTION";
	public static final String FIND_CONNECTION_BETWEEN  = "FIND_CONNECTION_BETWEEN";
	public static final String FIND_CONNECTION_BY_USER_BETWEEN  = "FIND_CONNECTION_BY_USER_BETWEEN";
	public static final String FIND_CONNECTION_BY_CITY_AND_STATE_BETWEEN  = "FIND_CONNECTION_BY_CITY_AND_STATE_BETWEEN";
	public static final String FIND_CONNECTION_BY_COUNTRY_AND_STATE_BETWEEN  = "FIND_CONNECTION_BY_COUNTRY_AND_STATE_BETWEEN";
	
	
	public Connection_(){
		
	}
	
	Connection_(String anIpAddress,
			   Date aDate,
			   String aCountry,
			   String aState,
			   String aCity,
			   Configuration aConfiguration){
		
		setIpAddress(anIpAddress);
		setDate(aDate);
		setCountry(aCountry);
		setState(aState);
		setCity(aCity);
		setConfiguration(aConfiguration);
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
	
	public void setIpAddress(String anIpAddress){
		m_ip_address = anIpAddress;
	}
	
	public String getIpAddress(){
		return m_ip_address;
	}
	
	public void setDate(Date aDate){
		m_date = aDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate(){
		return m_date;
	}
	
	public void setCountry(String aCountry){
		m_country = aCountry;
	}
	
	public String getCountry(){
		return m_country;
	}
	
	public void setState(String aState){
		m_state = aState;
	}
	
	public String getState(){
		return m_state;
	}
	
	public void setCity(String aCity){
		m_city = aCity;
	}
	
	public String getCity(){
		return m_city;
	}
	
	public void setConfiguration(Configuration aConfiguration){
		m_configuration = aConfiguration;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "configuration_fk", nullable = false) //configuration_fk connection foreign key is mapped in the owner table i.e. CONNECTION
	public Configuration getConfiguration(){
		return m_configuration;
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

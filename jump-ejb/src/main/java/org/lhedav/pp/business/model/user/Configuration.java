package org.lhedav.pp.business.model.user;

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
import org.lhedav.pp.business.model.common.Shareable;



@Entity
/*@NamedQueries({
	@NamedQuery(name = Configuration.FIND_ALL_CONFIGUTRATION,  query="SELECT u.m_id, u.profile_fk, "
			+ "												  c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city, "
			+ "												  g.m_platform_id, g.m_device_kind, g.m_browser, g.m_browser_version, g.m_requested_page, "
			+ "												  p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender  "
			+ "										  FROM User u INNER JOIN Connection c ON u.m_id = c.user_fk"
			+ "                                       INNER JOIN profile p ON p.m_id = u.profile_fk "
			+ "										  INNER JOIN Configuration g ON c.configuration_fk = g.m_id"),
	
	@NamedQuery(name = Configuration.FIND_ALL_CONFIGURATION_BETWEEN, query="SELECT u.m_id, u.profile_fk, "
			+ "												  c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city, "
			+ "												  g.m_platform_id, g.m_device_kind, g.m_browser, g.m_browser_version, g.m_requested_page, "
			+ "												  p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender "
			+ "												  FROM User u INNER JOIN Connection c ON u.m_id = c.user_fk"
			+ "                                               INNER JOIN profile p ON p.m_id = u.profile_fk "
			+ "												  INNER JOIN Configuration g ON c.configuration_fk = g.m_id "
			+ "												  WHERE c.m_date BETWEEN  :starting_date and :ending_date"),
	
	@NamedQuery(name = Configuration.FIND_CONFIGURATION_BY_USER_BETWEEN, query="SELECT u.m_id, u.profile_fk, "
			+ "													 c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city, "
			+ "													 g.m_platform_id, g.m_device_kind, g.m_browser, g.m_browser_version, g.m_requested_page, "
			+ "													 p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender "
			+ "												     FROM User u INNER JOIN Connection c ON u.m_id = c.user_fk"
			+ "                                                  INNER JOIN profile p ON p.m_id = u.profile_fk "
			+ "													 INNER JOIN Configuration g ON c.configuration_fk = g.m_id "
			+ "													 WHERE (c.m_date BETWEEN  :starting_date and :ending_date) AND (p.m_username = :username)"),
	
	@NamedQuery(name = Configuration.FIND_CONFIGURATION_BY_CITY_AND_STATE_BETWEEN, query="SELECT u.m_id, u.profile_fk, "
			+ "																c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city, "
			+ "																g.m_platform_id, g.m_device_kind, g.m_browser, g.m_browser_version, g.m_requested_page, "
			+ "															    p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender "
			+ "														        FROM User u INNER JOIN Connection c ON u.m_id = c.user_fk"
			+ "                                                             INNER JOIN profile p ON p.m_id = u.profile_fk "
			+ "																INNER JOIN Configuration g ON c.configuration_fk = g.m_id "
			+ "																WHERE (c.m_date BETWEEN  :starting_date and :ending_date) AND (c.m_city = :city)AND (c.m_state = :state)"),
	
	@NamedQuery(name = Configuration.FIND_CONFIGURATION_BY_COUNTRY_AND_STATE_BETWEEN, query="SELECT u.m_id, u.profile_fk, "
			+ "																	c.m_ip_address, c.m_date, c.m_country, c.m_state, c.m_city, "
			+ "																	g.m_platform_id, g.m_device_kind, g.m_browser, g.m_browser_version, g.m_requested_page,"
			+ "																	p.m_telphone_number, p.m_first_name, p.m_last_name, p.m_email, p.m_username, p.m_gender  "
			+ "																	FROM User u INNER JOIN Connection c ON u.m_id = c.user_fk"
			+ "                                  								INNER JOIN profile p ON p.m_id = u.profile_fk "
			+ "																	INNER JOIN Configuration g ON c.configuration_fk = g.m_id "
			+ "																	WHERE (c.m_date BETWEEN  :starting_date and :ending_date) AND (c.m_country = :country)AND (c.m_state = :state)")
})*/
public class Configuration implements Shareable, Serializable{
	private Long m_id;
	private Integer m_version;
	private String m_platform_id;
	private String m_device_kind;		
	private String m_browser;
	private String m_browser_version;
	private String m_requested_page;
	
	public static final String FIND_ALL_CONFIGUTRATION = "FIND_ALL_CONFIGUTRATION";
	public static final String FIND_ALL_CONFIGURATION_BETWEEN  = "FIND_ALL_CONFIGURATION_BETWEEN";
	public static final String FIND_CONFIGURATION_BY_USER_BETWEEN  = "FIND_CONFIGURATION_BY_USER_BETWEEN";
	public static final String FIND_CONFIGURATION_BY_CITY_AND_STATE_BETWEEN  = "FIND_CONFIGURATION_BY_CITY_AND_STATE_BETWEEN";
	public static final String FIND_CONFIGURATION_BY_COUNTRY_AND_STATE_BETWEEN  = "FIND_BY_COUNTRY_AND_STATE_BETWEEN";
	
	public Configuration(){
		
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
	
	public void setPlatform(String aPlatform){
		m_platform_id = aPlatform;
	}
	
	public String getPlatform(){
		return m_platform_id;
	}
	
	public void setDeviceKind(String aDeviceKind){
		m_device_kind = aDeviceKind;
	}
	
	public String getDeviceKind(){
		return m_device_kind;
	}
	
	public void setBrowser(String aBrowser){
		m_browser = aBrowser;
	}
	
	public String getBrowser(){
		return m_browser;
	}
	
	public void setBrowserVersion(String aVersion){
		m_browser_version = aVersion;
	}
	
	public String getBrowserVersion(){
		return m_browser_version;
	}
	
	public void setRequestedPage(String aUrl){
		m_requested_page = aUrl;
	}
	
	public String getRequestedPage(){
		return m_requested_page;
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

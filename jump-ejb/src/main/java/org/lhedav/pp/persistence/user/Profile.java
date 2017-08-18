package org.lhedav.pp.persistence.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.lhedav.pp.persistence.common.Shareable;

@Entity
public class Profile implements Shareable, Serializable{	
	private Long m_id;
	private Integer m_version;
	private String m_telphone_number;
	private String m_first_name;
	private String m_last_name;
	private String m_email;	
	private String m_username;
	private String m_password;
	private char m_gender;	
	private Date m_login_date;
	private boolean m_is_logged_in;
	private List<Avatar> m_avatars;	
	private List<Address> m_addresses;
	
	public Profile(){
		
	}
	
	Profile(String aTelphone_number,
			String aFirst_name,
			String aLast_name,
			String anEmail,
			String aUsername,
			String aPassword,
			char aGender,
			Date aConnection_date){
		
		setTelephoneNumber(aTelphone_number);
		setFirstName(aFirst_name);
		setLastName(aLast_name);
		setEmail(anEmail);
		setUsername(aUsername);
		setPassword(aPassword);
		setGender(aGender);
		setConnectionDate(aConnection_date);
		setAvatar(new ArrayList<Avatar>());
		setAddress(new ArrayList<Address>());		
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
	
	public void setTelephoneNumber(String anId){
		m_telphone_number = anId;
	}
	
	public String getTelephoneNumber(){
		return m_telphone_number;
	}
	
	public void setFirstName(String aFirstName){
		m_first_name = aFirstName;
	}
	
	public String getFisrtName(){
		return m_first_name;
	}
	
	public void setLastName(String aFirstName){
		m_last_name = aFirstName;
	}
	
	public String getLastName(){
		return m_last_name;
	}
	
	public void setEmail(String anEmail){
		m_email = anEmail;
	}
	
	public String getEmail(){
		return m_email;
	}
	
	public void setUsername(String aUsername){
		m_username = aUsername;
	}
	
	public String getUsername(){
		return m_username;
	}
	
	public void setPassword(String aPassword){
		m_password = aPassword;
	}
	
	public String getPassword(){
		return m_password;
	}
	
	public void setGender(char aGender){
		m_gender = aGender;
	}
	
	public char getGender(){
		return m_gender;
	}
	
	public void setConnectionDate(Date aLastConnectionDate){
		m_login_date = aLastConnectionDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getConnectionDate(){
		return m_login_date;
	}
	

	public void setLogin(boolean aBool){
		m_is_logged_in = aBool;
	}
	
	public boolean isLoggedIn(){
		return m_is_logged_in;
	}
	
	public void setAddress(List<Address> someAddresses){
		m_addresses = someAddresses;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ptofile_fk", nullable = false)  //profile_fk profile foreign key is mapped in the target table i.e. Address
	//@OrderBy("m_id ASC")// m_id is the id of an Address
	public List<Address> getAddress(){
		return m_addresses;
	}	
	
	public void setAvatar(List<Avatar> someAvatars){
		m_avatars = someAvatars;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ptofile_fk", nullable = false)  //profile_fk profile foreign key is mapped in the target table i.e. Avatar
	//@OrderBy("m_id ASC")// m_id is the id of an Avatar
	public List<Avatar> getAvatar(){
		return m_avatars;
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

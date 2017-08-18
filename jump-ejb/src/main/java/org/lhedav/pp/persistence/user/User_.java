package org.lhedav.pp.persistence.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

//import persistence.chat.Chat;
//import persistence.chat.Community;
import org.lhedav.pp.persistence.common.Shareable;
import org.lhedav.pp.persistence.service.Consumer;
import org.lhedav.pp.persistence.service.Provider;

@Entity
/*@NamedQueries({
@NamedQuery(name = User_.FIND_ALL_CONNECTED_USERS, query="SELECT u FROM User_ u WHERE u.m_is_logged_in = 'true' "),

@NamedQuery(name = User_.FIND_ALL_USERS_CONNECTED_BETWEEN, query="SELECT u "
		+ "						  FROM User_ u "
		+ "						  WHERE u.m_profile.m_Connection_date BETWEEN :startingDate AND :endingDate"),

@NamedQuery(name = User_.FIND_ALL_USER, query="SELECT u FROM User_ u"),

@NamedQuery(name = User_.FIND_USERS_BY_USERNAME, query="SELECT u "
		+ "					FROM User_ u "
		+ "					WHERE u.m_profile.m_username = :username"),

@NamedQuery(name = User_.FIND_USRES_BY_CREDENTIAL, query="SELECT u "
		+ "					  FROM User_ u "
		+ "					  WHERE (u.m_profile.m_username = :username) AND (u.m_profile.m_password = :password)"),

@NamedQuery(name = User_.FIND_USERS_BY_EMAIL, query="SELECT u "
		+ "				     FROM User_ u "
		+ "				     WHERE u.m_profile.m_email = :email"),

@NamedQuery(name = User_.FIND_USERS_BY_PHONE, query="SELECT u "
		+ "				     FROM User_ u "
		+ "				     WHERE u.m_profile.m_telphone_number = :telephone"),

@NamedQuery(name = User_.CHECK_USER_UNICITY, query="SELECT u "
		+ "				    FROM User_ u "
		+ "				    WHERE (u.m_profile.m_username = :username) AND (u.m_profile.m_email = :email) AND (u.m_profile.m_telphone_number = :telephone)"),

@NamedQuery(name = User_.FIND_USER_BY_PROFILE_FK, query="SELECT u "
		+ "					 FROM User_ u "
		+ "					 WHERE u.profile_fk = :profile_id")
})*/
public class User_ implements Shareable, Serializable{	
	private Long m_id;	 
	private Integer m_version;
	private Profile m_profile;	
	private Settings m_settings;
	private Provider m_provider;
	private Consumer m_consumer;	
	private List<Connection_> m_connections; // archive connections per month and reset table
//	private List<Community> m_community;
//	private List<Chat> m_chats;	
	
	public final static String FIND_ALL_CONNECTED_USERS = "FIND_ALL_CONNECTED_USERS";
	public final static String FIND_ALL_USERS_CONNECTED_BETWEEN = "FIND_ALL_USERS_CONNECTED_BETWEEN";
	public final static String FIND_ALL_USER = "FIND_ALL_USER";
	public final static String FIND_USERS_BY_USERNAME = "FIND_USERS_BY_USERNAME";
	public final static String FIND_USRES_BY_CREDENTIAL = "FIND_USRES_BY_CREDENTIAL";
	public final static String FIND_USERS_BY_EMAIL = "FIND_USERS_BY_EMAIL";
	public final static String FIND_USERS_BY_PHONE = "FIND_USERS_BY_PHONE";
	public final static String CHECK_USER_UNICITY = "CHECK_USER_UNICITY";
	public final static String FIND_USER_BY_PROFILE_FK = "FIND_USER_BY_PROFILE_FK";

	public User_(){

	}
	
	User_(Profile aProfile, Settings someSettings, Connection_ aConnection){
		setProfile(aProfile);
		setSettings(someSettings);
		//setProvider(null);
		//setConsumer(null);
		setConnections(new ArrayList<Connection_>());
		/*setCommunities(new ArrayList<Community>());
		setChats(new ArrayList<Chat>());*/
		if(aConnection != null){
			m_connections.add(aConnection);
		}
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
	
	public void setProfile(Profile aProfile){
		m_profile = aProfile;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_fk", nullable = false) //profile_fk profile foreign key is mapped in the owner table i.e. USER
	public Profile getProfile(){
		return m_profile;
	}
	
	public void setSettings(Settings someSettings){
		m_settings = someSettings;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "settings_fk", nullable = false) //settings_fk settings foreign key is mapped in the owner table i.e. USER
	public Settings getSettings(){
		return m_settings;
	}
	
	public void setProvider(Provider aProvider){
		m_provider = aProvider;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_fk", nullable = false) //provider_fk profile foreign key is mapped in the owner table i.e. USER
	public Provider getProvider(){
		return m_provider;
	}
	
	public void setConsumer(Consumer aConsumer){
		m_consumer = aConsumer;
	}	
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "consumer_fk", nullable = false) //consumer_fk consumer foreign key is mapped in the owner table i.e. USER
	public Consumer getConsumer(){
		return m_consumer;
	}

	public void setConnections(List<Connection_> aConnection){
		m_connections = aConnection;
	}
	
	@OneToMany (fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk", nullable = false) //user_fk user foreign key is mapped in the target table i.e. CONNECTION
	//@OrderBy("m_id ASC")// m_id is the id of Connection
	public List<Connection_> getConnections(){    
		return m_connections;
	}

/*	public void setCommunities(List<Community> aCommunityNetwork){
		m_community = aCommunityNetwork;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk", nullable = false) //user_fk user foreign key is mapped in the target table i.e. GROUP
	@OrderBy("m_id ASC")// m_id is the id of Group
	public List<Community> getCommunities(){ 
		return m_community;
	}

	public void setChats(List<Chat> aListOfChats){
		m_chats = aListOfChats;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk", nullable = false) //user_fk user foreign key is mapped in the target table i.e. CHAT
	@OrderBy("m_id ASC")// m_id is the id of Chat
	public List<Chat> getChats(){
		return m_chats;
	}*/	

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

package org.lhedav.pp.business.model.user;

import java.util.List;
import org.lhedav.pp.business.model.service.Consumer;
import org.lhedav.pp.business.model.service.Provider;




public class UserManager {
	
	public static Profile getProfile(User_ aUser){
		if(aUser != null){
			return aUser.getProfile();
		}
		return null;
	}
	
	public static Settings getSettings(User_ aUser){
		if(aUser != null){
			return aUser.getSettings();
		}
		return null;
	}
	
	public static Provider getProvider(User_ aUser){
		if(aUser != null){
			return aUser.getProvider();
		}
		return null;
	}
	
	public static Consumer getConsumer(User_ aUser){
		if(aUser != null){
			return aUser.getConsumer();
		}
		return null;
	}
	
	public static List<Connection_> getConnection(User_ aUser){
		if(aUser != null){
			return aUser.getConnections();
		}
		return null;
	}
	
/*	public static List<Community> getCommunities(User aUser){
		if(aUser != null){
			return aUser.getCommunities();
		}
		return null;
	}
	
	public static List<Chat> getChats(User aUser){
		if(aUser != null){
			return aUser.getChats();
		}
		return null;
	}*/
}

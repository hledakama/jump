package org.lhedav.pp.business.model.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import org.lhedav.pp.business.model.user.User_;


@Entity
public class Provider implements org.lhedav.pp.business.model.common.Shareable, Serializable{

	private Long id;
	private Integer version;
        private User_ user;
        private boolean status;        
        private List <Selling> sellings;
        private List <Subscriber> subscribers;
        //private List<Order> m_orders; fos customer only
        //private List <ViewList> m_view_list;
        //private List <Cart> m_carts;
        //private List <Favorite> m_favoristes;
        
	

	public void setId(Long anId){
		id = anId;
	}
	
        @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}
	
	@Version
	public Integer getVersion(){
		return version;
	}
        
        public void setVersion(Integer aVersion){
            version = aVersion;
	}
        
        @OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_fk", nullable = false) //providern_fk Provider foreign key is mapped in the owner table i.e. Provider
        public User_ getUser(){
            return user;
        }
        
        public void setUser(User_ aUser){
            user = aUser;
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
    
    public boolean isActive(){
        return status;
    }
    
    public void setActive(boolean aStatus){
        status = aStatus;
    }
	
}

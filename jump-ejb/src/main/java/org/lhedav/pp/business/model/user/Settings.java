package org.lhedav.pp.business.model.user;

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
import javax.persistence.OneToMany;
import javax.persistence.Version;
import org.lhedav.pp.business.model.common.Shareable;



@Entity
public class Settings implements Shareable, Serializable{
	private Long m_id;
	private Integer m_version;
	private String m_default_language;
	private String m_chat_category;
	private int m_community_size;
	private int m_max_msg_per_page;
	private int m_max_avatar_size;
	private boolean m_allow_talk_with_my_community_members;
	private boolean m_allow_talk_with_my_community_owner;
	private boolean m_share_my_community_members_msg;
	private boolean m_receive_chat_from_community;
	private boolean m_discard_community_invitation_request;
	private boolean m_enable_chat_scheduling;
	private boolean m_enable_service_scheduling;
	private boolean m_do_not_display_old_appointments;
	private boolean m_allow_provider_to_access_my_appointments;
	private boolean m_allow_customers_to_access_my_schedule;
	private boolean m_allow_provider_postpone;
	private boolean m_allow_customer_postpone;
	private boolean m_allow_customer_chat;
	private boolean m_allow_provider_chat;
	private boolean m_allow_customer_in_mail;
	private boolean m_allow_provider_in_mail;
	private boolean m_accept_online_payment;
	private boolean m_is_black_listed;	
	private List<Membership> m_memberships;
	
	public Settings(){
		
	}
	
	Settings(String aDefault_language,
			 String aChat_category,
			 int community_size,
			 int max_msg_per_page,
			 int max_avatar_size,
			 boolean aBool1,
			 boolean aBool2,
			 boolean aBool3,
			 boolean aBool4,
			 boolean aBool5,
			 boolean aBool6,
			 boolean aBool7,
			 boolean aBool8,
			 boolean aBool9,
			 boolean aBool10,
			 boolean aBool11,
			 boolean aBool12,
			 boolean aBool13,
			 boolean aBool14,
			 boolean aBool15,
			 boolean aBool16,
			 boolean aBool17,
			 boolean aBool18,
			 Membership aMembership){
		
		setLanguage_(aDefault_language);
		setChatCategory(aChat_category);
		setCommunityeSize(community_size);
		setMaxMsgPerPage(max_msg_per_page);
		setMaxAvatarSize(max_avatar_size);
		setTalkWithCommunityMembers(aBool1);
		setTalkWithCommunityOwner(aBool2);
		setShareMembersMsg(aBool3);
		setReceiveChatFromCommunity(aBool4);
		setDiscardCommunityInvitationMsg(aBool5);
		setEnableChatScheduling(aBool6);
		setEnableServiceSchedulings(aBool7);
		setDisplayOldAppointments(aBool8);
		setMyAppointmentsAccessByProvider(aBool9);
		setCustomerAccessMySchedule(aBool10);
		setProviderPostpone(aBool11);
		setCustomerPostpone(aBool12);
		setCustomerChat(aBool13);
		setProviderChat(aBool14);
		setInMailCustomer(aBool15);
		setInMailProvider(aBool16);
		setAcceptOnlinePayment(aBool17);
		setBlackListStatus(aBool18);
		
		m_memberships = new ArrayList<Membership>();
		if(aMembership != null){
			m_memberships.add(aMembership);
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
	
	public void setLanguage_(String aLanguage){
		m_default_language = aLanguage;
	}
	
	public String getLanguage_(){
		return m_default_language;
	}
	
	public void setChatCategory(String aCategory){
		m_chat_category = aCategory;
	}
	
	public String getChatCategory(){
		return m_chat_category;
	}
	
	
	
	public void setCommunityeSize(int aSize){
		m_community_size = aSize;
	}
	
	public int getCommunitySize(){
		return m_community_size;
	}
	
	public void setMaxMsgPerPage(int aSize){
		m_max_msg_per_page = aSize;
	}
	
	public int getMaxMsgPerPage(){
		return m_max_msg_per_page;
	}
	
	public void setMaxAvatarSize(int aSize){
		m_max_avatar_size = aSize;
	}
	
	public int getMaxAvatarSize(){
		return m_max_avatar_size;
	}
	
	public void setTalkWithCommunityMembers(boolean aBool){
		m_allow_talk_with_my_community_members = aBool;
	}
	
	public boolean isTalkWithCommunityMembers(){
		return m_allow_talk_with_my_community_members;
	}
	
	public void setTalkWithCommunityOwner(boolean aBool){
		m_allow_talk_with_my_community_owner = aBool;
	}
	
	public boolean isTalkWithCommunityOwner(){
		return m_allow_talk_with_my_community_owner;
	}
	public void setShareMembersMsg(boolean aBool){
		m_share_my_community_members_msg = aBool;
	}
	
	public boolean isShareMembersMsg(){
		return m_share_my_community_members_msg;
	}
	public void setReceiveChatFromCommunity(boolean aBool){
		m_receive_chat_from_community = aBool;
	}
	
	public boolean isReceiveChatFromCommunity(){
		return m_receive_chat_from_community;
	}
	public void setDiscardCommunityInvitationMsg(boolean aBool){
		m_discard_community_invitation_request = aBool;
	}
	
	public boolean isDiscardCommunityInvitationMsg(){
		return m_discard_community_invitation_request;
	}
	public void setEnableChatScheduling(boolean aBool){
		m_enable_chat_scheduling = aBool;
	}
	
	public boolean isChatSchedulingEnabled(){
		return m_enable_chat_scheduling;
	}
	public void setEnableServiceSchedulings(boolean aBool){
		m_enable_service_scheduling = aBool;
	}
	
	public boolean isServiceSchedulingsEnabled(){
		return m_enable_service_scheduling;
	}
	public void setDisplayOldAppointments(boolean aBool){
		m_do_not_display_old_appointments = aBool;
	}
	
	public boolean isDisplayOldAppointments(){
		return m_do_not_display_old_appointments;
	}
	public void setMyAppointmentsAccessByProvider(boolean aBool){
		m_allow_provider_to_access_my_appointments = aBool;
	}
	
	public boolean isProviderAccessMyAppointments(){
		return m_allow_provider_to_access_my_appointments;
	}
	public void setCustomerAccessMySchedule(boolean aBool){
		m_allow_customers_to_access_my_schedule = aBool;
	}
	
	public boolean isCustomerAccessMySchedule(){
		return m_allow_customers_to_access_my_schedule;
	}
	public void setProviderPostpone(boolean aBool){
		m_allow_provider_postpone = aBool;
	}
	
	public boolean isProviderPostpone(){
		return m_allow_provider_postpone;
	}
	public void setCustomerPostpone(boolean aBool){
		m_allow_customer_postpone = aBool;
	}
	
	public boolean isCustomerPostpone(){
		return m_allow_customer_postpone;
	}
	public void setCustomerChat(boolean aBool){
		m_allow_customer_chat = aBool;
	}
	
	public boolean isCustomerChat(){
		return m_allow_customer_chat;
	}
	public void setProviderChat(boolean aBool){
		m_allow_provider_chat = aBool;
	}
	
	public boolean isProviderChat(){
		return m_allow_provider_chat;
	}
	public void setInMailCustomer(boolean aBool){
		m_allow_customer_in_mail = aBool;
	}
	
	public boolean isInMailCustomer(){
		return m_allow_customer_in_mail;
	}
	public void setInMailProvider(boolean aBool){
		m_allow_provider_in_mail = aBool;
	}
	
	public boolean isInMailProvider(){
		return m_allow_provider_in_mail;
	}
	public void setAcceptOnlinePayment(boolean aBool){
		m_accept_online_payment = aBool;
	}
	
	public boolean isAcceptingOnlinePayment(){
		return m_accept_online_payment;
	}		
	
	public void setBlackListStatus(boolean aBool){
		m_is_black_listed = aBool;
	}
	
	public boolean isBlackListed(){
		return m_is_black_listed;
	}	
	
	public void setMembership(List<Membership> someMemberships){
		m_memberships = someMemberships;
	}
	
	@OneToMany (fetch = FetchType.LAZY)
	@JoinColumn(name = "settings_fk", nullable = false) //settings_fk settings foreign key is mapped in the target table i.e. MEMBERSHIP
	//@OrderBy("m_id DESC")// m_id is the id of Membership
	public List<Membership> getMembership(){
		return m_memberships;
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

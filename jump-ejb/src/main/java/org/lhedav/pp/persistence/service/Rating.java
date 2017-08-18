package org.lhedav.pp.persistence.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.lhedav.pp.persistence.common.Shareable;

@Entity
public class Rating implements Shareable, Serializable{

	private Long id;
	private Integer version;	
	private String type; // customer or provider
	
	private Map<String, Integer> criteriamark;
	private Date ratingdate;
	private Date targetdate;
	private String commenttitle;
	private String wholecomment;
	private String ratername;
	
	//protected String[] criterias;	
	//protected int numberofcriterias;
	
	public Rating(){
        this.criteriamark = new HashMap<>();
		
	}
	
	public void setId(Long anId){
		id = anId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return id;
	}
	
	public void setVersion(Integer aVersion){
		version = aVersion;
	}
	
	@Version
	public Integer getVersion(){
		return version;
	}
	
	public void setType(String aType){
		type = aType;
	}
	
	public String getType(){
		return type;
	}
	
	/*public void addRate(String aCriteriaKey, Integer aMark){
		criteriamark.put(aCriteriaKey, aMark);			
	}
	
        @ElementCollection
	@CollectionTable(name="rating")
	@MapKeyColumn (name = "criteria")
	@Column(name = "mark")
	public Integer getMark(String aCriteriaKey){
		return criteriamark.get(aCriteriaKey);
	}*/
	
	public void setRatingDate(Date aDate){
		ratingdate = aDate;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getRatingDate(){
		return ratingdate;
	}
	
	public void setTargetDate(Date aDate){
		targetdate = aDate;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getTargetDate(){
		return targetdate;
	}
	
	public void setCommentTitle(String aCommentTitle){
		commenttitle = aCommentTitle;
	}
	
	public String getCommentTitle(){
		return commenttitle;
	}
	
	public void setWholeComment(String aComment){
		wholecomment = aComment;
	}
	
	public String getWholeComment(){
		return wholecomment;
	}
	
	public void setRaterName(String aRaterName){
		ratername = aRaterName;
	}
	
	public String getRaterName(){
		return ratername;
	}
	
	/*public void setCriterias(int position, String aValue){
		criterias[position] = aValue;
	}
	
        @Transient
	public String getCriterias(int position){
		return criterias[position];
	}*/
	
	/*public void setNumberofCriterias(int aLength){
		numberofcriterias = aLength;
	}
	
        @Transient
	public int getNumberofCriterias(){
		return numberofcriterias;
	}
	
	protected void createCriterias(int aLength){
		setNumberofCriterias(aLength);
		criterias = new String[numberofcriterias];
		for(int criteria_index = 0; criteria_index < numberofcriterias; criteria_index++){
			criterias[criteria_index] = Global.STR_EMPTY;
		}
	}
	
	protected void setCriterias(String[] someCriterias){
		criterias = someCriterias;
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

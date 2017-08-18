package org.lhedav.pp.persistence.service.Education;


import org.lhedav.pp.persistence.common.Global;
import org.lhedav.pp.persistence.service.Administration.Administration;

//@Entity
public class DayCare extends Administration{

	protected int number_of_kids;	
	
	protected DayCare(){
		super();
		setCategory(Global.STR_EMPTY);
	}
}

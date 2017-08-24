package org.lhedav.pp.business.model.service.Education;

import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.service.Administration.Administration;



//@Entity
public class DayCare extends Administration{

	protected int number_of_kids;	
	
	protected DayCare(){
		super();
		setCategory(Global.STR_EMPTY);
	}
}

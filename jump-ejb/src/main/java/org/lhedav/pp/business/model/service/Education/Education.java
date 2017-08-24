package org.lhedav.pp.business.model.service.Education;

import javax.persistence.Entity;
import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;



//@Entity
public class Education extends HouseHoldService{
	protected Education(){
		super();
		setType(Service.TYPE_EDUCATION);
	}
}

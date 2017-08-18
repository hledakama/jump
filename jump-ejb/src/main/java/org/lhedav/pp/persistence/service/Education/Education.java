package org.lhedav.pp.persistence.service.Education;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;

//@Entity
public class Education extends HouseHoldService{
	protected Education(){
		super();
		setType(Service.TYPE_EDUCATION);
	}
}

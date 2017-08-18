package org.lhedav.pp.persistence.service.Entertainment;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;


//@Entity
public class Entertainment extends HouseHoldService{
	protected Entertainment(){
		super();
		setType(Service.TYPE_ENTERTAINMENT);
	}
}

package org.lhedav.pp.persistence.service.Transportation;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;

//@Entity
public class Transportation extends HouseHoldService{

	protected Transportation(){
		super();
		setType(Service.TYPE_TRANSPORTATION);
	}
}

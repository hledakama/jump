package org.lhedav.pp.persistence.service.Administration;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;

//@Entity
public class Administration extends HouseHoldService {
	
	protected Administration(){
		super();
		setType(Service.TYPE_ADMINISTRATION);
	}
}

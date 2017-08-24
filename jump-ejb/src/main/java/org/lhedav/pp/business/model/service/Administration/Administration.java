package org.lhedav.pp.business.model.service.Administration;

import javax.persistence.Entity;
import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;


//@Entity
public class Administration extends HouseHoldService {
	
	protected Administration(){
		super();
		setType(Service.TYPE_ADMINISTRATION);
	}
}

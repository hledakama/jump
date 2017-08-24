package org.lhedav.pp.business.model.service.Entertainment;

import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;



//@Entity
public class Entertainment extends HouseHoldService{
	protected Entertainment(){
		super();
		setType(Service.TYPE_ENTERTAINMENT);
	}
}

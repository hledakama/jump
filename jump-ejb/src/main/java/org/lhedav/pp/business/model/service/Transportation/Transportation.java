package org.lhedav.pp.business.model.service.Transportation;

import javax.persistence.Entity;
import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;


//@Entity
public class Transportation extends HouseHoldService{

	protected Transportation(){
		super();
		setType(Service.TYPE_TRANSPORTATION);
	}
}

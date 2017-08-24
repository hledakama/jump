package org.lhedav.pp.business.model.service.Health;

import javax.persistence.Entity;
import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;


//@Entity
public class Health extends HouseHoldService{
protected Health(){
	super();
	setType(Service.TYPE_HEALTH);
}
}

package org.lhedav.pp.persistence.service.Health;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;

//@Entity
public class Health extends HouseHoldService{
protected Health(){
	super();
	setType(Service.TYPE_HEALTH);
}
}

package org.lhedav.pp.persistence.service.Security;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;

//@Entity
public class Security extends HouseHoldService{
protected Security(){
	super();
	setType(Service.TYPE_SECURITY);
}
}

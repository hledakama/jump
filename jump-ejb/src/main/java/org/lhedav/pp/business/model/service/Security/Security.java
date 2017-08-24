package org.lhedav.pp.business.model.service.Security;

import javax.persistence.Entity;
import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;



//@Entity
public class Security extends HouseHoldService{
protected Security(){
	super();
	setType(Service.TYPE_SECURITY);
}
}

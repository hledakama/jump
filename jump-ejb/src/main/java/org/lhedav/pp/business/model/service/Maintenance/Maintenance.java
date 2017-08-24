package org.lhedav.pp.business.model.service.Maintenance;

import javax.persistence.Entity;
import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;



//@Entity
public class Maintenance extends HouseHoldService{
protected Maintenance(){
	super();
	setType(Service.TYPE_MAINTENANCE);
}
}

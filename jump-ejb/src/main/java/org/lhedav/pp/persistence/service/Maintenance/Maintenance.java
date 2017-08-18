package org.lhedav.pp.persistence.service.Maintenance;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;

//@Entity
public class Maintenance extends HouseHoldService{
protected Maintenance(){
	super();
	setType(Service.TYPE_MAINTENANCE);
}
}

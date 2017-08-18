package org.lhedav.pp.persistence.service.Beauty;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;

//@Entity
public class Beauty extends HouseHoldService{
	protected Beauty(){
		super();
		setType(Service.TYPE_BEAUTY);
	}
}

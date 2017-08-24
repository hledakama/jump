package org.lhedav.pp.business.model.service.Beauty;

import javax.persistence.Entity;
import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;


//@Entity
public class Beauty extends HouseHoldService{
	protected Beauty(){
		super();
		setType(Service.TYPE_BEAUTY);
	}
}

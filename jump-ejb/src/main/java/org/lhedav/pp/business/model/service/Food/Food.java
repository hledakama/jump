package org.lhedav.pp.business.model.service.Food;

import javax.persistence.Entity;
import org.lhedav.pp.business.model.service.HouseHoldService;
import org.lhedav.pp.business.model.service.Service;


//@Entity
public class Food extends HouseHoldService{
	
	protected Food(){
		super();
		setType(Service.TYPE_FOOD);
	}
}

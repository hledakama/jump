package org.lhedav.pp.persistence.service.Food;

import javax.persistence.Entity;

import org.lhedav.pp.persistence.service.HouseHoldService;
import org.lhedav.pp.persistence.service.Service;

//@Entity
public class Food extends HouseHoldService{
	
	protected Food(){
		super();
		setType(Service.TYPE_FOOD);
	}
}

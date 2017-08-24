package org.lhedav.pp.business.model.service;

import javax.persistence.Entity;

//@Entity
public class WorkplaceService extends Service{
	
	WorkplaceService(){
		setKind(Service.KIND_WORKPLACE);
	}

}

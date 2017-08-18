package org.lhedav.pp.persistence.service;

import javax.persistence.Entity;

//@Entity
public class WorkplaceService extends Service{
	
	WorkplaceService(){
		setKind(Service.KIND_WORKPLACE);
	}

}

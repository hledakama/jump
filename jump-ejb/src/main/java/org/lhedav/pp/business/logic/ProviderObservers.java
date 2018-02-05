/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.logic;

import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.lhedav.pp.business.cdi.event.ProviderEvent;
import org.lhedav.pp.business.data.ServiceKind;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Itemdata;
import org.lhedav.pp.business.model.service.Service;

/**
 *
 * @author client
 */
public class ProviderObservers {
    //@Inject
//private Logger logger;
    
    public void addService(@Observes @ProviderEvent(name = "serviceAddedEvent") Service aService){
        
    }
    
    public void removeService(@Observes @ProviderEvent(name = "serviceRemovedEvent") Service aService){
        
    }
    
    public void mergeService(@Observes @ProviderEvent(name = "serviceMergedEvent") Service aService){
        
    }
    
    
    
    
    
    public void addItem(@Observes @ProviderEvent(name = "itemAddedEvent") Item anItem){
        
    }
    
    public void removeItem(@Observes @ProviderEvent(name = "itemMergedEvent") Item anItem){
        
    }
    
    public void mergeItem(@Observes @ProviderEvent(name = "itemRemovedEvent") Item anItem){
        
    }
    
    
    
    public void addItemdata(@Observes @ProviderEvent(name = "itemAddedEvent") Itemdata anItemdata){
        
    }
    
    public void removeItemdata(@Observes @ProviderEvent(name = "itemMergedEvent") Itemdata anItemdata){
        
    }
    
    public void mergeItemdata(@Observes @ProviderEvent(name = "itemRemovedEvent") Itemdata anItemdata){
        
    }
    
    
    
    public void addItemdata(@Observes @ProviderEvent(name = "serviceKindAddedEvent") ServiceKind aKind){
        
    }
    
    public void mergeItemdata(@Observes @ProviderEvent(name = "serviceKindMergedEvent") ServiceKind aKind){
        
    }
    
}

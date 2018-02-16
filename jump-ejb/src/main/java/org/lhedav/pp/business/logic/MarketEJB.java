/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.logic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lhedav.pp.business.cdi.event.ProviderEvent;
import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.service.Service;
import org.lhedav.pp.business.model.user.Account;

/**
 *
 * @author client
 */
//https://www.tutorialspoint.com/ejb/ejb_transactions.htm
//Severe:   ERROR StatusLogger No log4j2 configuration file found. Using default configuration: 
//logging only errors to the console. Set system property 'org.apache.logging.log4j.simplelog.StatusLogger.level' 
//to TRACE to show Log4j2 internal initialization logging.

@Stateless
@LocalBean
public class MarketEJB {
    @PersistenceContext(unitName = Global.PERSISTENCE_UNIT)
    private EntityManager em;
    
    
    
    
    
    
    
    
    
    
    public void addService(@Observes @ProviderEvent(name = "serviceAddedEvent") Service aService){
        System.out.println("serviceAddedEvent to market");
    }
    
    public void removeService(@Observes @ProviderEvent(name = "serviceRemovedEvent") Service aService){
        System.out.println("serviceRemovedEvent to market");
    }
    
    public void mergeService(@Observes @ProviderEvent(name = "serviceMergedEvent") Service aService){
        System.out.println("serviceMergedEvent to market");
    }
    
    public void addAccount(@Observes @ProviderEvent(name = "accountAddedEvent") Account anAccount){
        System.out.println("accountAddedEvent to market");
    }
    
     public void mergeAccount(@Observes @ProviderEvent(name = "acountMergedEvent") Account anAccount){
        System.out.println("acountMergedEvent to market");
    }
    
}

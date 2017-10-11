/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.logic;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Itemdata;
import org.lhedav.pp.business.model.service.Service;
import org.lhedav.pp.business.data.ServiceKind;
import org.lhedav.pp.business.data.ServiceType;
import org.lhedav.pp.business.data.Services;

/**
 *
 * @author client
 */

@Stateless
@LocalBean
public class ProviderEJB {
    
    
    @PersistenceContext(unitName = Global.PERSISTENCE_UNIT)
    private EntityManager em;
    //https://stackoverflow.com/questions/17231535/java-lang-classcastexception-sametype-cannot-be-cast-to-sametype
    public List<ServiceKind> getServiceKinds(){ 
        List<ServiceKind> theKinds;
        try{
        TypedQuery<ServiceKind> theQuery;
        theQuery = em.createNamedQuery("ServiceKind.findAll", ServiceKind.class);
        theKinds = theQuery.getResultList();
        }        
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return theKinds;
    }
    
    public List<ServiceType> getServiceTypes(){
        List<ServiceType> theTypes;
        try{        
        TypedQuery<ServiceType> theQuery;
        theQuery = em.createNamedQuery("ServiceType.findAll", ServiceType.class);
        theTypes = theQuery.getResultList();
    }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theTypes;
    }
        
    public List<Services> getServicesData(){
       List<Services> theServices ;
        try{
        TypedQuery<Services> theQuery;
        theQuery = em.createNamedQuery("Services.findAll", Services.class);
        theServices = theQuery.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theServices;
    }
    
    public List<String> getItemUnits(){
        List<String> theResult = new ArrayList();
        return theResult;
    }
    
    
     //***************************  Service   *********************************************
    public boolean createService(@NotNull Service aService, @NotNull Item anItem, @NotNull Itemdata anItemFata){
        /*anItemFata.setItemFk(anItem.getId());
        anItem.setServiceFk(aService.getId());
        em.persist(anItemFata);
        em.persist(anItem);
        em.persist(aService);*/
        return true;
    }
    
        public boolean createService(@NotNull Service aService, @NotNull Item anItem){
            
            em.persist(aService);
            em.persist(anItem);
            em.flush(); //force insert to receive the id of the aService
        
        return true;
    }
        
    public boolean createService(@NotNull Service aService){
           
            System.out.println("getCategory: "+aService.getCategory());
            System.out.println("getKind: "+aService.getKind());
            System.out.println("getServicename: "+aService.getServicename());
            System.out.println("getServicereference: "+aService.getServicereference());
            System.out.println("getType: "+aService.getType());
            System.out.println("toString: "+aService.toString());
            System.out.println("getId: "+aService.getServiceTId());
            System.out.println("-----------------isPublished------------: "+aService.getPublished());
            
            
            em.persist(aService);            
            em.flush(); 
        
        return true;
    }   
     
    
    public Service getServiceByServiceReference(@NotNull String aReference){
         try{
        TypedQuery<Service> theQuery;
        theQuery = em.createNamedQuery("Service.findByServicereference", Service.class);
        theQuery.setParameter("servicereference", aReference);
        return theQuery.getSingleResult();
         }
         catch(javax.persistence.NoResultException e){
             return null;
         }
         catch(Exception e){
             e.printStackTrace();
             return null;
         }
    }
     
    public List<Service> getItemsFromServices(@NotNull String aKind, @NotNull String aType, @NotNull String aServiceName, @NotNull String aCategory){
       try{
        TypedQuery<Service> theQuery;
        theQuery = em.createNamedQuery("Service.findByServiceNameKindTypeCategory", Service.class);        
        theQuery.setParameter("kind", aKind);
        theQuery.setParameter("type", aType);
        theQuery.setParameter("servicename", aServiceName);
        theQuery.setParameter("category", aCategory);
        return theQuery.getResultList();
         }
         catch(javax.persistence.NoResultException e){
             return null;
         }
         catch(Exception e){
             e.printStackTrace();
             return null;
         }
    }
    
    public boolean PersistService(@NotNull Service aService){
        em.persist(aService);
        return true;
    }
    
    public boolean updateService(@NotNull Service aService){
            em.merge(aService);
            return true;
    }
    
    
    //***************************  Item   *********************************************
    
        
    public Item getItemByItemReference(@NotNull String aReference){
        TypedQuery<Item> theQuery;
        try{        
        theQuery = em.createNamedQuery("Item.findByItemreference", Item.class);
        theQuery.setParameter("itemreference", aReference);
        return theQuery.getSingleResult();
        }
        
        catch(javax.persistence.NoResultException e){
             return null;
        }
         
        catch(Exception e){
             e.printStackTrace();
             return null;
        }        
    }
    
    public List<Item> getItemsListByServiceReference(@NotNull String aReference){
       java.util.List<Service> theList = null;
        try{
        TypedQuery<Service> theQuery;
        System.out.println("@@@aReference: "+aReference);
        theQuery = em.createNamedQuery("Service.findByServicereference", Service.class);
        theList = theQuery.setParameter("servicereference", aReference).getResultList();

        
        if(theList == null) return null;
        
        List<Item> theResult = new ArrayList();
        
        System.out.println("theList,size(): "+theList.size());
        for(int index = 0; index< theList.size();index++) {
            Service theService = theList.get(index);
           List<Item> theTempResult = theService.getItemList();
           if(theTempResult == null) continue;
           
            for(Item theItem : theTempResult) {
                if(theItem == null) continue;
            theResult.add(theItem);           
                }           
        }      
        return theResult;
                }
         
        catch(javax.persistence.NoResultException e){
             return null;
        }
         
        catch(Exception e){
             e.printStackTrace();
             return null;
        }
    }
    
    public boolean updateItemdata(@NotNull Itemdata anItemdata){
        em.merge(anItemdata);            
            return true;
    }
    
    public boolean deleteItemdata(@NotNull Itemdata anItemdata){
        try{em.remove(em.merge(anItemdata));
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    
    public boolean updateItem(@NotNull Item anItem){
        em.merge(anItem);            
            return true;
    }
    
    public boolean deleteItem(@NotNull Item anItem){
        try{em.remove(em.merge(anItem));
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

        public boolean deleteService(@NotNull Service aService){
        try{em.remove(em.merge(aService));
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    
    public boolean PersistItem(@NotNull Item anItem){
        em.persist(anItem);
        return true;
    }
    
    
        //***************************  ItemData   *********************************************
    
    public boolean updateItemData(@NotNull Itemdata anItemData){
        em.merge(anItemData);
            return true;
    }
    
    public boolean deleteItemData(Itemdata aData){
        em.remove(em.merge(aData));
        return false;
    }
    
    public Itemdata getItemdataByReference(@NotNull String aReference){
        TypedQuery<Itemdata> theQuery;
        try{
        theQuery = em.createNamedQuery("Itemdata.findByItemdatareference", Itemdata.class);
        theQuery.setParameter("itemdatareference", aReference);
        return theQuery.getSingleResult();
        }
        catch(javax.persistence.NoResultException e){
             return null;
        }
         
        catch(Exception e){
             e.printStackTrace();
             return null;
        }
    }
    
    public Itemdata getItemdataById(@NotNull String anItemDataId){
        TypedQuery<Itemdata> theQuery;
        try{
        Long theId = Long.parseLong(anItemDataId);
        theQuery = em.createNamedQuery("Itemdata.findByItemdataTId", Itemdata.class);
        theQuery.setParameter("itemdataTId", theId);
        return theQuery.getSingleResult();
        }
         catch(javax.persistence.NoResultException e){
             return null;
        }         
        catch(Exception e){
             e.printStackTrace();
             return null;
        }
    }
    
    public boolean PersistItemdata(@NotNull Itemdata anItemData){
        em.persist(anItemData);
        return true;
    }    
    
    public EntityManager getEm(){
        return em;
    }
    
    public void setEm(EntityManager anEm){
        em = anEm;
    }
    
    
    
}

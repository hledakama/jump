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
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Itemdata;
import org.lhedav.pp.business.model.service.Service;
import org.lhedav.pp.business.data.ServiceKind;
import org.lhedav.pp.business.data.ServiceType;
import org.lhedav.pp.business.data.Services;
import org.lhedav.pp.business.data.Unit;
import org.lhedav.pp.business.model.service.ProviderAvatar;

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
    
    public List<Unit> getItemUnits(){
       List<Unit> theUnits ;
        try{
        TypedQuery<Unit> theQuery;
        theQuery = em.createNamedQuery("Unit.findAll", Unit.class);
        theUnits = theQuery.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theUnits;
    } 
    
    public List<String> getItemUnits(List<Unit> aList){
        if(aList == null) return null;
        List<String> theResult = new ArrayList();
        for(Unit aUnit: aList){
            theResult.add(aUnit.getUnit());
        }
        return theResult;
    }        
    
     //***************************  Service   *********************************************
    public boolean createService(@NotNull Service aService, 
                                 @NotNull Item anItem, 
                                 @NotNull Itemdata anItemData,
                                 @NotNull ProviderAvatar anAvatar){ 
       
        PersistService(aService);       
        /*System.out.println("createService.getItemname: "+anItem.getItemname());
        System.out.println("createService.getItemreference: "+anItem.getItemreference());
        System.out.println("createService.getCdate: "+anItem.getCdate());
        System.out.println("createService.getItemTId: "+anItem.getItemTId());
        System.out.println("createService.getPrice: "+anItem.getPrice());
        System.out.println("createService.getQty: "+anItem.getQty());
        System.out.println("createService.getServiceFk: "+anItem.getServiceFk());
        System.out.println("createService.getVirtual: "+anItem.getVirtual());
        System.out.println("createService.isEdited: "+anItem.isEdited());*/
       PersistItem(anItem);
        /*System.out.println("createService.getAddressString: "+anItemFata.getAddressString());
        System.out.println("createService.getComment: "+anItemFata.getComment());
        System.out.println("createService.getUnit: "+anItemFata.getUnit());
        System.out.println("createService.getDuration: "+anItemFata.getDuration());
        System.out.println("createService.getItemFk: "+anItemFata.getItemFk());
        System.out.println("createService.getItemdataTId: "+anItemFata.getItemdataTId());
        System.out.println("createService.getMdate: "+anItemFata.getMdate());
        System.out.println("createService.isEdited: "+anItemFata.isEdited());
        System.out.println("createService.isUploadValidated: "+anItemFata.isUploadValidated());*/
        System.out.println("anItemData persist, anItemData.getItemdataTId(): "+anItemData.getItemdataTId());
        em.persist(anItemData);
        System.out.println("anAvatar persist, anAvatar.getProviderAvatarTId(): "+anAvatar.getProviderAvatarTId());
        em.persist(anAvatar);
        em.flush(); 
        return true;
    }
    
    public boolean createService(@NotNull Service aService, @NotNull Item anItem){
        PersistService(aService);         
        PersistItem(anItem);
        em.flush();       
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
            
        PersistService(aService);
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
        Service theSavedService = getServiceByServiceReference(aService.getServicereference());
        if(theSavedService == null){
             System.out.println("aService persist, aService.getServiceTId(): "+aService.getServiceTId());
            em.persist(aService);
        }
        else{
            System.out.println("EntityExistsException aService merge, theSavedService.getServiceTId(): "+theSavedService.getServiceTId());
            aService.setServiceTId(theSavedService.getServiceTId());
            em.merge(aService);
        }
       /* try{
        //Try to insert your entity by calling persist method 
            System.out.println("aService persist, aService.getServiceTId(): "+aService.getServiceTId());
            em.persist(aService);
        }
        catch(EntityExistsException e){
            //Entity you are trying to insert already exist, then call merge method
            System.out.println("EntityExistsException aService merge");
            em.merge(aService);
        }
        catch(PersistenceException e){
            //Entity you are trying to insert already exist, then call merge method
            System.out.println("PersistenceException aService merge");            
            em.merge(aService);
        }*/
        /*Service theSavedService = getServiceByServiceReference(aService.getServicereference());
        if(theSavedService == null){
            System.out.println("aService persist");
            em.persist(aService);
        }
        else{
            System.out.println("aService merge");
            em.merge(aService);
        } */
        return true;
    }
    
    public boolean PersistItem(@NotNull Item anItem){
        //https://stackoverflow.com/questions/15643732/duplicate-entry-for-key-primary-using-jpa-to-persist-into-database
        Item theSavedItem = getItemByItemReference(anItem.getItemreference());
        if(theSavedItem == null){
            System.out.println("anItem persist, anItem.getItemTId()"+ anItem.getItemTId());
            em.persist(anItem);
        } 
        else{
            System.out.println("anItem merge, anItem.getItemTId()"+ anItem.getItemTId()+", theSavedItem.getItemTId(): "+theSavedItem.getItemTId());
            anItem.setItemTId(theSavedItem.getItemTId());
            em.merge(anItem);
        }
        /*try{
        //Try to insert your entity by calling persist method    
            System.out.println("anItem persist, anItem.getItemTId()"+ anItem.getItemTId());
            em.persist(anItem);
        }
        catch(EntityExistsException e){
            //Entity you are trying to insert already exist, then call merge method
            System.out.println("EntityExistsException anItem merge");
            em.merge(anItem);
        }
        catch(PersistenceException e){
            //Entity you are trying to insert already exist, then call merge method
            System.out.println("PersistenceException anItem merge");
            em.merge(anItem);
        }*/
        /*Item theSavedItem = getItemByItemReference(anItem.getItemreference());
        if(theSavedItem == null){
            System.out.println("anItem persist");
            em.persist(anItem);
        } 
        else{
            System.out.println("anItem merge");
            em.merge(anItem);
        }*/
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

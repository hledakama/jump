/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.logic;

import java.util.Collection;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.ItemData;
import org.lhedav.pp.business.model.service.Service;

/**
 *
 * @author client
 */

@Stateless
@LocalBean
public class ServiceEJB {
    
    
    @PersistenceContext(unitName = "org.lhedav.pp_jump-ejb_PU")
    private EntityManager em;
    
    
     //***************************  Service   *********************************************
    public boolean createService(@NotNull Service aService, @NotNull Item anItem, @NotNull ItemData anItemFata){
        /*anItemFata.setItemFk(anItem.getId());
        anItem.setServiceFk(aService.getId());
        em.persist(anItemFata);
        em.persist(anItem);
        em.persist(aService);*/
        return true;
    }
    
        public boolean createService(@NotNull Service aService, @NotNull Item anItem){
            
            em.persist(aService);
            em.flush(); //force insert to receive the id of the aService
            //anItem.setServiceFk(aService.getServiceTId());
            em.persist(anItem);
        
        return true;
    }
        
          public boolean createService(@NotNull Service aService){
            System.out.println("getCategory: "+aService.getCategory());
            System.out.println("getKind: "+aService.getKind());
            System.out.println("getServicename: "+aService.getServicename());
            System.out.println("getServicereference: "+aService.getServicereference());
            System.out.println("getSubcategory: "+aService.getSubcategory());
            System.out.println("getType: "+aService.getType());
            System.out.println("toString: "+aService.toString());
            System.out.println("getId: "+aService.getServiceTId());
            System.out.println("-----------------isPublished------------: "+aService.getPublished());
            
            Item anItem = aService.retrieveItem();
            System.out.println("getItemname: "+anItem.getItemname());
            System.out.println("getItemreference: "+anItem.getItemreference());
            System.out.println("toString: "+anItem.toString());
            System.out.println("getCdate: "+anItem.getCdate());
            System.out.println("getgetItemTId: "+anItem.getItemTId());
            System.out.println("getPrice: "+anItem.getPrice());
            System.out.println("getQty: "+anItem.getQty());
            System.out.println("getServiceFk: "+anItem.getServiceFk());
            System.out.println("getVirtual_: "+anItem.getVirtual());
            System.out.println("isEdited: "+anItem.isEdited());
            em.persist(aService);
            em.persist(anItem);
              
           // em.flush(); 
          //  anItem.setServiceFk(aService);
           // System.out.println("getServiceFk++: "+anItem.getServiceFk());
            
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
        theQuery = em.createNamedQuery("Item.findByItemreference", Item.class);
        theQuery.setParameter("itemreference", aReference);
        return theQuery.getSingleResult();
    }
    
    public Collection<Item> getItemsListByServiceReference(@NotNull String aReference){
        Service theService = null;
        try{
        TypedQuery<Service> theQuery;
        System.out.println("@@@aReference: "+aReference);
        theQuery = em.createNamedQuery("Service.findByServicereference", Service.class);
        theService = theQuery.setParameter("servicereference", aReference).getSingleResult();
        }
         catch(javax.persistence.NoResultException e){
             return null;
         }
         catch(Exception e){
             e.printStackTrace();
             return null;
         }
        if(theService != null){
            return theService.getItemCollection();
        }
        return null;
    }
    
    
        public boolean updateItem(@NotNull Item anItem){
        em.merge(anItem);            
            return true;
    }
    
    public boolean deleteItem(@NotNull Item anItem){
        em.remove(em.merge(anItem));
        return false;
    }
    
    
    public boolean PersistItem(@NotNull Item anItem){
        em.persist(anItem);
        return true;
    }
    
    
        //***************************  ItemData   *********************************************
    
    public boolean updateItemData(@NotNull ItemData anItemData){
        em.merge(anItemData);
            return true;
    }
    
    public boolean deleteItemData(ItemData aData){
        em.remove(em.merge(aData));
        return false;
    }
    
    public ItemData getItemDataByReference(@NotNull String aReference){
        TypedQuery<ItemData> theQuery;
        theQuery = em.createNamedQuery("ItemData.findByItemdatareference", ItemData.class);
        theQuery.setParameter("itemdatareference", aReference);
        return theQuery.getSingleResult();
    }
    
    public boolean PersistItemData(@NotNull ItemData anItemData){
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

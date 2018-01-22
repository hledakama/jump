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
import org.lhedav.pp.business.data.Categories;
import org.lhedav.pp.business.data.Items;
import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Itemdata;
import org.lhedav.pp.business.model.service.Service;
import org.lhedav.pp.business.data.ServiceKind;
import org.lhedav.pp.business.data.ServiceType;
import org.lhedav.pp.business.data.Services;
import org.lhedav.pp.business.data.Unit;

/**
 *
 * @author client
 */

@Stateless
@LocalBean
public class SellerEJB {  
    
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
    
    public ServiceKind getServiceKindByName(String aKind){ 
        ServiceKind theResult;
        try{
        TypedQuery<ServiceKind> theQuery;
        theQuery = em.createNamedQuery("ServiceKind.findByKind", ServiceKind.class);
        theQuery.setParameter("kind", aKind);
        theResult = theQuery.getSingleResult();
        }        
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return theResult;
    }
    
    
    public List<ServiceType> getServiceTypes(ServiceKind aKind){
        List<ServiceType> theTypes;
        try{        
        TypedQuery<ServiceType> theQuery;
        theQuery = em.createNamedQuery("ServiceType.findByServiceKind", ServiceType.class);
        theQuery.setParameter("serviceKindFk", aKind);
        theTypes = theQuery.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theTypes;
    }
    
    public ServiceType getServiceTypeByName(String aType){
        ServiceType theResult;
        try{        
        TypedQuery<ServiceType> theQuery;
        theQuery = em.createNamedQuery("ServiceType.findByType", ServiceType.class);
        theQuery.setParameter("type", aType);
        theResult = theQuery.getSingleResult();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theResult;
    }
        
    public List<Services> getServicesData(ServiceType aType, boolean all){
       List<Services> theServices ;
        try{
        TypedQuery<Services> theQuery;
        
        if(all){
            theQuery = em.createNamedQuery("Services.findAll", Services.class);
        }else{
            theQuery = em.createNamedQuery("Services.findByServicesType", Services.class);
            theQuery.setParameter("serviceTypeFk", aType);
        }
        theServices = theQuery.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theServices;
    }
    
    public Services getServicesDataByName(String someServicesName){
       Services theResult ;
        try{
        TypedQuery<Services> theQuery;
        theQuery = em.createNamedQuery("Services.findByName", Services.class);
        theQuery.setParameter("name", someServicesName);
        theResult = theQuery.getSingleResult();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theResult;
    }
    
    public List<Categories> getCategoriesData(Services someServices){
       List<Categories> theCategories ;
        try{
        TypedQuery<Categories> theQuery;
        theQuery = em.createNamedQuery("Categories.findByServices", Categories.class);
        theQuery.setParameter("servicesFk", someServices);
        theCategories = theQuery.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theCategories;
    }
        
    public List<Items> getItemsData(Categories someCategories, boolean all){
       List<Items> theItems ;
        try{
        TypedQuery<Items> theQuery;
        if(all){
            theQuery = em.createNamedQuery("Items.findAll", Items.class);
        }else{
           theQuery = em.createNamedQuery("Items.findByCategories", Items.class);
           theQuery.setParameter("categoriesFk", someCategories); 
        }
        
        theItems = theQuery.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return theItems;
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
     
    public Service getItemsFromService(@NotNull String aKind, @NotNull String aType, @NotNull String aServiceName, @NotNull String aCategory){
       try{
        TypedQuery<Service> theQuery;
        theQuery = em.createNamedQuery("Service.findByServiceNameKindTypeCategory", Service.class);        
        theQuery.setParameter("kind", aKind);
        theQuery.setParameter("type", aType);
        theQuery.setParameter("servicename", aServiceName);
        theQuery.setParameter("category", aCategory);
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
        if(aService.isMerged()){
            System.out.println("aService merge ");
            //aService.setServiceTId(theSavedService.getServiceTId());
            em.merge(aService);
            return false;
        }
        else{
             System.out.println("aService persist, aService.getServiceTId(): "+aService.getServiceTId());
            em.persist(aService);
            return true;
        }
    }
    
       
    public boolean PersistServiceKind(@NotNull ServiceKind aServiceKind){
        List<ServiceKind> theKinds = getServiceKinds();
        System.out.println("theKinds == null: "+ (theKinds == null));
        ServiceKind theKind = null;
        for(ServiceKind aKind: theKinds){
            if(aKind == null) continue;
            System.out.println("aKind.getKind(): "+aKind.getKind()+", aServiceKind.getKind(): "+aServiceKind.getKind()+ " theKinds.size(): "+theKinds.size());
            if(aKind.getKind().equals(aServiceKind.getKind())){
                theKind = aKind;
                theKind.setMerged(true);                
                break;
            }
        }
        if((theKind != null) && theKind.isMerged()){
            System.out.println("aServiceKind merge ");
            theKind.setServiceTypeList(aServiceKind.getServiceTypeList());
            em.merge(theKind);
            //Global.mergeItemsStructures(aServiceKind.getServiceTypeList(), theKinds);
            return false;
        }
        else{
             System.out.println("aServiceKind persist, getServiceKindTId: "+aServiceKind.getServiceKindTId());
            em.persist(aServiceKind);
            return true;
        }
    }
    
    public boolean PersistServiceUnits(@NotNull Unit aUnit){
        List<Unit> theUnits = getItemUnits();
        Unit theUnit = null;
        for(Unit someUnit: theUnits){
            if(someUnit == null) continue;
            System.out.println("aUnit.getUnit(): "+aUnit.getUnit()+", someUnit.getUnit(): "+someUnit.getUnit());       
            if(someUnit.getUnit().equals(aUnit.getUnit())){
                theUnit = someUnit;
                theUnit.setMerged(true);
                break;
            }
        }
        if((theUnit != null) && theUnit.isMerged()){
            // enable the merge later if thelist of settings is created within Items
            /*System.out.println("aUnit merge ");
            em.merge(aUnit);*/
            return false;
        }
        else{
             System.out.println("aUnit persist, getUnitTId: "+aUnit.getUnitTId());
            em.persist(aUnit);
            return true;
        }
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
       // System.out.println("@@@aReference: "+aReference);
        theQuery = em.createNamedQuery("Service.findByServicereference", Service.class);
        theList = theQuery.setParameter("servicereference", aReference).getResultList();

        
        if(theList == null) return null;
        
        List<Item> theResult = new ArrayList();
        
       // System.out.println("theList,size(): "+theList.size());
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
    
    public boolean deleteItemdata(@NotNull Service aService, @NotNull Itemdata anItemdata){
        try{//em.remove(em.merge(anItemdata));
            
        }
        catch(Exception e){
            e.printStackTrace();
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
    public boolean Updatetemdata(@NotNull Itemdata anItemData){
        em.merge(anItemData);
        return true;
    }
    public EntityManager getEm(){
        return em;
    }
    public void setEm(EntityManager anEm){
        em = anEm;
    }    
}

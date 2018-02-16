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
import javax.enterprise.event.Event;
import javax.inject.Inject;
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
import org.lhedav.pp.business.cdi.event.ProviderEvent;
import org.lhedav.pp.business.model.user.Account;

/**
 *
 * @author client
 */

@Stateless
@LocalBean
//@Transactional//(Transactional.TxType.REQUIRES_NEW)
//@Interceptors(LoggingInterceptor.class)
public class ProviderEJB {  
    
    @PersistenceContext(unitName = Global.PERSISTENCE_UNIT)
    private EntityManager em;
    
    @Inject
    @ProviderEvent(name = "serviceAddedEvent")
    private Event<Service> serviceAddedEvent;
    
    @Inject
    @ProviderEvent(name = "serviceRemovedEvent")
    private Event<Service> serviceRemovedEvent;
    
    @Inject
    @ProviderEvent(name = "serviceMergedEvent")
    private Event<Service> serviceMergedEvent;
    
        @Inject
    @ProviderEvent(name = "itemAddedEvent")
    private Event<Item> itemAddedEvent;
    
    @Inject
    @ProviderEvent(name = "itemMergedEvent")
    private Event<Item> itemMergedEvent;
    
    @Inject
    @ProviderEvent(name = "itemRemovedEvent")
    private Event<Item> itemRemovedEvent;
    
        @Inject
    @ProviderEvent(name = "itemdataAddedEvent")
    private Event<Itemdata> itemdataAddedEvent;
    
    @Inject
    @ProviderEvent(name = "itemdataMergedEvent")
    private Event<Itemdata> itemdataMergedEvent;
    
    @Inject
    @ProviderEvent(name = "itemdataRemovedEvent")
    private Event<Itemdata> itemdataRemovedEvent;
    
    
    
    
    
    @Inject
    @ProviderEvent(name = "serviceKindAddedEvent")
    private Event<ServiceKind> serviceKindAddedEvent;
    
    @Inject
    @ProviderEvent(name = "serviceKindMergedEvent")
    private Event<ServiceKind> serviceKindMergedEvent;
    
    @Inject
    @ProviderEvent(name = "unitAddedEvent")
    private Event<Unit> unitAddedEvent;
    
    @Inject
    @ProviderEvent(name = "unitMergedEvent")
    private Event<Unit> unitMergedEvent;
    
    @Inject
    @ProviderEvent(name = "accountAddedEvent")
    private Event<Account> accountAddedEvent;
    
    @Inject
    @ProviderEvent(name = "accountMergedEvent")
    private Event<Account> accountMergedEvent;
    
    
    
    //https://stackoverflow.com/questions/17231535/java-lang-classcastexception-sametype-cannot-be-cast-to-sametype
   // @ExcludeClassInterceptors
    public List<ServiceKind> getServiceKinds(){ 
        List<ServiceKind> theKinds;
        try{
        TypedQuery<ServiceKind> theQuery;
        theQuery = em.createNamedQuery("ServiceKind.findAll", ServiceKind.class);
        theKinds = theQuery.getResultList();
        }        
        catch(Exception e){
           // e.printStackTrace();
            return null;
        }
        return theKinds;
    }
    
    //@ExcludeClassInterceptors
    public ServiceKind getServiceKindByName(String aKind){ 
        ServiceKind theResult;
        try{
        TypedQuery<ServiceKind> theQuery;
        theQuery = em.createNamedQuery("ServiceKind.findByKind", ServiceKind.class);
        theQuery.setParameter("kind", aKind);
        theResult = theQuery.getSingleResult();
        }        
        catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return theResult;
    }
    
   // @ExcludeClassInterceptors
    public List<Categories> getCategories(@NotNull String someKind, @NotNull String someType, @NotNull String someServices){ 
        ServiceKind theKind = getServiceKindByName(someKind);
        List<Categories> theResult = null;
        if(theKind != null){
            theResult = theKind.getListOfCategories(someType, someServices);
        }
        return theResult;
    }
    
    
   // @ExcludeClassInterceptors
    public List<ServiceType> getServiceTypes(ServiceKind aKind){
        List<ServiceType> theTypes;
        try{        
        TypedQuery<ServiceType> theQuery;
        theQuery = em.createNamedQuery("ServiceType.findByServiceKind", ServiceType.class);
        theQuery.setParameter("serviceKindFk", aKind);
        theTypes = theQuery.getResultList();
        }
        catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return theTypes;
    }
    
   // @ExcludeClassInterceptors
    public ServiceType getServiceTypeByName(String aType){
        ServiceType theResult;
        try{        
        TypedQuery<ServiceType> theQuery;
        theQuery = em.createNamedQuery("ServiceType.findByType", ServiceType.class);
        theQuery.setParameter("type", aType);
        theResult = theQuery.getSingleResult();
        }
        catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return theResult;
    }
        
   // @ExcludeClassInterceptors
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
            //e.printStackTrace();
            return null;
        }
        return theServices;
    }    
    
    
   // @ExcludeClassInterceptors
    public Services getServicesDataByName(String someServicesName){
       Services theResult ;
        try{
        TypedQuery<Services> theQuery;
        theQuery = em.createNamedQuery("Services.findByName", Services.class);
        theQuery.setParameter("name", someServicesName);
        theResult = theQuery.getSingleResult();
        }
        catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return theResult;
    }
    
   // @ExcludeClassInterceptors
    public List<Categories> getCategoriesData(Services someServices){
       List<Categories> theCategories ;
        try{
        TypedQuery<Categories> theQuery;
        theQuery = em.createNamedQuery("Categories.findByServices", Categories.class);
        theQuery.setParameter("servicesFk", someServices);
        theCategories = theQuery.getResultList();
        }
        catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return theCategories;
    }
    
   // @ExcludeClassInterceptors
    public Categories getCategoryByName(String aCategory){
       Categories theCategory ;
        try{
        TypedQuery<Categories> theQuery;
        theQuery = em.createNamedQuery("Categories.findByName", Categories.class);
        theQuery.setParameter("name", aCategory);
        theCategory = theQuery.getSingleResult();
        }
        catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return theCategory;
    }
        
   // @ExcludeClassInterceptors
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
            //e.printStackTrace();
            return null;
        }
        return theItems;
    }
    
    
    //@ExcludeClassInterceptors
    public List<Unit> getItemUnits(){
       List<Unit> theUnits ;
        try{
        TypedQuery<Unit> theQuery;
        theQuery = em.createNamedQuery("Unit.findAll", Unit.class);
        theUnits = theQuery.getResultList();
        }
        catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return theUnits;
    } 
    
    //@ExcludeClassInterceptors
    public List<String> getItemUnits(List<Unit> aList){
        if(aList == null) return null;
        List<String> theResult = new ArrayList();
        for(Unit aUnit: aList){
            theResult.add(aUnit.getUnit());
        }
        return theResult;
    } 
    
    //@ExcludeClassInterceptors
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
     
    //@ExcludeClassInterceptors
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
    
        public Account getAccountById(@NotNull Account anAccount){
            try{
            TypedQuery<Account> theQuery;
            theQuery = em.createNamedQuery("Account.findByAccountTId", Account.class);
            theQuery.setParameter("accountTId", anAccount.getAccountTId());
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
            //System.out.println("aService merge ");
            //aService.setServiceTId(theSavedService.getServiceTId());
            em.merge(aService);
            serviceMergedEvent.fire(aService);
            return false;
        }
        else{
             //System.out.println("aService persist, aService.getServiceTId(): "+aService.getServiceTId());
            em.persist(aService);
            serviceAddedEvent.fire(aService);
            return true;
        }
    } 
    
    public boolean PersistService(@NotNull Account anAccount){ 
        Account someAccount = getAccountById(anAccount);
        if(someAccount != null){
            em.merge(anAccount);
            accountMergedEvent.fire(anAccount);
            return false;
        }
        else{
             //System.out.println("aService persist, aService.getServiceTId(): "+aService.getServiceTId());
            em.persist(anAccount);
            accountAddedEvent.fire(anAccount);
            return true;
        }
    }    
       
    public boolean PersistServiceKind(@NotNull ServiceKind aServiceKind){
        List<ServiceKind> theKinds = getServiceKinds();
        //System.out.println("theKinds == null: "+ (theKinds == null));
        if(aServiceKind.isMerged()){
            //System.out.println("aServiceKind merge ");
            em.merge(aServiceKind);
            serviceKindMergedEvent.fire(aServiceKind);
            return false;
        }
        else{
             //System.out.println("aServiceKind persist, getServiceKindTId: "+aServiceKind.getServiceKindTId());
            em.persist(aServiceKind);
            serviceKindAddedEvent.fire(aServiceKind);
            return true;
        }
    }
    
    public boolean PersistServiceUnits(@NotNull Unit aUnit){
        List<Unit> theUnits = getItemUnits();
        Unit theUnit = null;
        for(Unit someUnit: theUnits){
            if(someUnit == null) continue;
            //System.out.println("aUnit.getUnit(): "+aUnit.getUnit()+", someUnit.getUnit(): "+someUnit.getUnit());       
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
            unitMergedEvent.fire(theUnit);
            return false;
        }
        else{
             //System.out.println("aUnit persist, getUnitTId: "+aUnit.getUnitTId());
            em.persist(aUnit);
            unitAddedEvent.fire(theUnit);
            return true;
        }
    }
    
    
    //***************************  Item   *********************************************
    
    //@ExcludeClassInterceptors    
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
    
    //@ExcludeClassInterceptors
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
    
    //@ExcludeClassInterceptors
    public boolean updateItemdata(@NotNull Itemdata anItemdata){
        em.merge(anItemdata);  
        itemdataMergedEvent.fire(anItemdata);
        return true;
    }
      
    //@ExcludeClassInterceptors
    public boolean updateItem(@NotNull Item anItem){
        em.merge(anItem); 
        itemRemovedEvent.fire(anItem);
        return true;
    }
    
    
    public boolean deleteItem(@NotNull Item anItem){
        try{
            em.remove(em.merge(anItem));
            itemRemovedEvent.fire(anItem);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

        public boolean deleteService(@NotNull Service aService){
        try{
            em.remove(em.merge(aService));
        }
        catch(Exception e){
            return false;
        }
        return true;
    } 
    
    //***************************  ItemData   *********************************************
    
        //@ExcludeClassInterceptors
    public boolean updateItemData(@NotNull Itemdata anItemData){
        em.merge(anItemData);
        itemdataMergedEvent.fire(anItemData);
        return true;
    }
    
    //@ExcludeClassInterceptors
    public boolean deleteItemData(Itemdata aData){
        em.remove(em.merge(aData));
        itemdataRemovedEvent.fire(aData);
        return false;
    }
    
    //@ExcludeClassInterceptors
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
    
    //@ExcludeClassInterceptors
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
        itemdataAddedEvent.fire(anItemData);
        return true;
    }  
    
    //@ExcludeClassInterceptors
    public boolean Updatetemdata(@NotNull Itemdata anItemData){
        em.merge(anItemData);
        itemdataMergedEvent.fire(anItemData);
        return true;
    }
    
    //@ExcludeClassInterceptors
    public EntityManager getEm(){
        return em;
    }
    
    //@ExcludeClassInterceptors
    public void setEm(EntityManager anEm){
        em = anEm;
    }    
}

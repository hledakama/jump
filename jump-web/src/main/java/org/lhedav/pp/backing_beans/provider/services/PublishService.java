/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.backing_beans.provider.services;

import org.lhedav.pp.business.logic.ProviderEJB;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.CollectionDataModel;
import javax.inject.Named;
import org.lhedav.pp.business.data.ServiceKind;
import org.lhedav.pp.business.data.ServiceType;
import org.lhedav.pp.business.data.Services;
import org.lhedav.pp.business.model.common.Global;
import static org.lhedav.pp.business.model.common.Global.GLOBAL_DISPLAY_MESSAGE;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Service;
import org.lhedav.pp.business.model.service.SortedDataModel;

/**
 *
 * @author client
 */

@Named
@RequestScoped
public class PublishService {
    private Service service = new Service();
    private List<String> servicesKinds   = new ArrayList();
    private List<String> servicesTypes   = new ArrayList();
    private List<String> servicesNames   = new ArrayList();
    private List<String> categoriesNames = new ArrayList();
    // make a HashMap servicesNames/categoriesNames from the parsed XML 
    private String pageTitle = "Publishing service";
    private String nav1 = "User account";
    private String nav2 = "Services";
    private String nav3 = "Provider";
    private String nav4 = "Publish service";
    private String kind = "Kind";
    private String type = "Type";
    private String name = "Service";
    private String category = "Category";
    private String status = "Subscribed";
    private String itemReference = "Item";  
    private String serviceReference = "Service"; 
    private String itemName = "Name";
    private String itemDate = "Date";
    private String itemPrice = "Price";
    private String itemVirtual = "Virtual";
    private String itemQty = "Qty";
    private String addServiceButtonLabel = "Add service";
    private String modifyServiceButtonLabel = "Modify service";
    private String publishButtonLabel = "Publish service";
            @EJB
    private ProviderEJB m_provider_services;
    private SortedDataModel<Item> sortedItemModel;
    private SortedDataModel<Service> sortedServiceModel;
    private boolean publishChecked;
    
    
    PublishService(){ 
    }
    //https://community.oracle.com/thread/1726468
    @PostConstruct
    public void init() {
        List<ServiceKind>  theKinds    = m_provider_services.getServiceKinds();
        List<ServiceType> theTypes     = m_provider_services.getServiceTypes();
        List<Services> theServicesData = m_provider_services.getServicesData();
        
        int theKindSize = theKinds.size(); 
           for(int index = 0; index < theKindSize; index++){
               String theString = theKinds.get(index).getKind();
               if(servicesKinds.contains(theString)) continue;
               servicesKinds.add(theString);
           }
        int theTypeSize = theTypes.size();
            for(int index = 0; index < theTypeSize; index++){
                String theString = theTypes.get(index).getType();
               if(servicesTypes.contains(theString)) continue;
                servicesTypes.add(theTypes.get(index).getType());
            } 
         
        int theServicesSize = theServicesData.size();
            for(int index = 0; index < theServicesSize; index++){
                String theString = theServicesData.get(index).getName();
               if(servicesNames.contains(theString)) continue;
                servicesNames.add(theServicesData.get(index).getName());
            }
            
        
            for(int index = 0; index < theServicesSize; index++){
               String theString = theServicesData.get(index).getCategory();
               if(categoriesNames.contains(theString)) continue;
                categoriesNames.add(theServicesData.get(index).getCategory());
            }
        
        service.setKind(servicesKinds.get(0));
        service.setType(servicesTypes.get(0));
        service.setServicename(servicesNames.get(0));
        service.setCategory(categoriesNames.get(0));
        System.out.println("setServicereference from PublishService.init");
        service.setServicereference();
        
        updateAddModifyButton();
    }
    
    public Service getService(){
        return service;
    }
    
    public void setService(Service aService){
        service = aService;
    }
    
    public boolean isServiceEmpty(){
        System.out.println("setServicereference from PublishService.isServiceEmpty");
        service.setServicereference();
        Collection<Item>   theItems = m_provider_services.getItemsListByServiceReference(service.getServicereference());
        boolean isNotEmpty = (theItems != null) && (theItems.size() > 0);
        if(theItems != null){
            java.lang.System.out.println("listSize: "+theItems.size()+", ");
        }   
        java.lang.System.out.println("isNotEmpty: "+isNotEmpty);
        return !isNotEmpty;
    }
    
     public List<String> getServicesKinds(){
        return servicesKinds;
    }
        
        public void setServicesKinds(List<String> someKinds){
            servicesKinds = someKinds;            
        }
        
         public List<String> getServicesTypes(){
        return servicesTypes;
    }
        
        public void setServicesTypes(List<String> someTypes){
            servicesTypes = someTypes;
        }
      
    public List<String> getServicesNames(){
        return servicesNames;
    }
        
        public void setServicesNames(List<String> someNames){
           servicesNames =  someNames;
        }
	
	 
        public List<String> getCategoriesNames(){
            return categoriesNames;
        }
        
        public void setCategoriesNames(List<String> someNames){            
            categoriesNames = someNames;
        }
        
        public String getPageTitle(){
            return pageTitle;
        }
        
       public void setPageTitle(String aTitle){
           pageTitle = aTitle;
       }
       
        public String getNav1(){
            return nav1;
        }
        
       public void setNav1(String aNavigation){
           nav1 = aNavigation;
       }
       
        public String getNav2(){
            return nav2;
        }
        
       public void setNav2(String aNavigation){
           nav2 = aNavigation;
       }
       
        public String getNav3(){
            return nav3;
        }
        
       public void setNav3(String aNavigation){
           nav3 = aNavigation;
       }
       
       public String getNav4(){
            return nav4;
        }
        
       public void setNav4(String aNavigation){
           nav4 = aNavigation;
       }
       
        public String getKind(){
            return kind;
        }
        
       public void setKind(String aKind){
           kind = aKind;
       }
       
        public String getType(){
            return type;
        }
        
       public void setType(String aType){
           type = aType;
       }
       
       public String getName(){
            return name;
        }
        
       public void setName(String aName){
           name = aName;
       }
          
       public String getCategory(){
            return category;
        }
        
       public void setCategory(String aCategory){
           category = aCategory;
       }
       
       public String getStatus(){
            return status;
        }
        
       public void setStatus(String aStatus){
           status = aStatus;
       }
       
       public String getItemReference(){
            return itemReference;
       }
        
       public void setItemReference(String aReference){
           itemReference = aReference;
       }
       
       public String getServiceReference(){
            return serviceReference;
       }
        
       public void setServiceReference(String aReference){
           serviceReference = aReference;
       }
       
       public String getItemDate(){
            return itemDate;
       }
        
       public void setItemDate(String aDate){
           itemDate = aDate;
       }
       
       public String getItemName(){
            return itemName;
       }
        
       public void setItemName(String aName){
           itemName = aName;
       }
       
       public String getItemPrice(){
            return itemPrice;
       }
        
       public void setItemPrice(String aPrice){
           itemPrice = aPrice;
       }
       
       public String getItemVirtual(){
            return itemVirtual;
       }
        
       public void setItemVirtual(String aVirtual){
           itemVirtual = aVirtual;
       }
       
       public String getItemQty(){
            return itemQty;
       }
        
       public void setItemQty(String aQty){
           itemQty = aQty;
       }  
               
       public String getAddServiceButtonLabel(){
          return addServiceButtonLabel;
       }
        
       public void setAddServiceButtonLabel(String aLabel){
           addServiceButtonLabel = aLabel;
       }
       
       public String getModifyServiceButtonLabel(){
          return modifyServiceButtonLabel;
       }
        
       public void setModifyServiceButtonLabel(String aLabel){
           modifyServiceButtonLabel = aLabel;
       }
       
       public String getPublishButtonLabel(){
            return publishButtonLabel;
       }
        
       public void setPublishButtonLabel(String aLabel){
           publishButtonLabel = aLabel;
       }
        
   /*    public void setServiceKindChanged(ValueChangeEvent anEvent){
        if(anEvent == null) return;
        serviceKindChanged = anEvent.getNewValue().toString();
    }
       public void isServiceTypeChanged(ValueChangeEvent anEvent){
        if(anEvent == null) return;
        serviceKindChanged = anEvent.getNewValue().toString();
    }
       public void setServiceNameChanged(ValueChangeEvent anEvent){
        if(anEvent == null) return;
        serviceNameChanged = anEvent.getNewValue().toString();
    }
       
         public void isServiceCategoryChanged(ValueChangeEvent anEvent){
        if(anEvent == null) return;
        serviceNameChanged = anEvent.getNewValue().toString();
    }*/
         
           public SortedDataModel<Item> getSortedItemModel() {
           List<Service>   theServices;
           System.out.println("service.getServicename(): "+service.getServicename());
           System.out.println("service.getKind(): "+service.getKind());
           System.out.println("service.getType(): "+service.getType());
           theServices = m_provider_services.getItemsFromServices(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
           List<Item> theItems = new ArrayList();
           if(theServices != null) {
              for(Service theService: theServices) {
              List<Item> theTempResult = theService.getItemList();
              if(theTempResult == null) continue;
           
              for(Item theItem : theTempResult) {
                if(theItem == null) continue;
                   theItems.add(theItem);           
              }           
            }
           }
           //System.out.println("theServices.size(): "+theServices.size()+ ", theItems.size(): "+theItems.size());;
           sortedItemModel = new SortedDataModel<>(new CollectionDataModel<>(theItems));
           /* } catch (ParseException ex) {
            Logger.getLogger(AddModifyItem.class.getName()).log(Level.SEVERE, null, ex);
           }*/
           return sortedItemModel;
    }
    
           public SortedDataModel<Service> getSortedServiceModel() {
           List<Service>   theServices;
           System.out.println("--service.getServicename(): "+service.getServicename());
           System.out.println("--service.getKind(): "+service.getKind());
           System.out.println("--service.getType(): "+service.getType());
           theServices = m_provider_services.getItemsFromServices(service.getKind(), service.getType(), service.getServicename(), service.getCategory());

           //System.out.println("theServices.size(): "+theServices.size()+ ", theServices.size(): "+theServices.size());;
           sortedServiceModel = new SortedDataModel<>(new CollectionDataModel<>(theServices));
           /* } catch (ParseException ex) {
            Logger.getLogger(AddModifyItem.class.getName()).log(Level.SEVERE, null, ex);
           }*/
           return sortedServiceModel;
    }
       
    public String publish(){
        FacesContext theCtx = FacesContext.getCurrentInstance();
         if((service.getPublished() == 1) || isServiceEmpty()){
             theCtx.addMessage(GLOBAL_DISPLAY_MESSAGE, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                                                        "Service is already published or no itemdatis created.",
                                                                        "Current service is empty or already published!"));
             return Global.STAY_ON_CURRENT_PAGE;
         }
         System.out.println("setServicereference from PublishService.Publish");
         service.setServicereference();
         Service theRetrived = m_provider_services.getServiceByServiceReference(service.getServicereference());
         theRetrived.setPublished((short)1);
         
         if(m_provider_services.PersistService(service)){
             theCtx.addMessage(GLOBAL_DISPLAY_MESSAGE, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                                                                        "Publishing service.",
                                                                        "The service is published successfully"));
         }
         else{
             theCtx.addMessage(GLOBAL_DISPLAY_MESSAGE, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                                                                        "Publishing service",
                                                                        "The service failed while publishing!"));
         }         
         // create publisher herer
         return Global.STAY_ON_CURRENT_PAGE;
         
    } 

          
    public boolean isPublishChecked() {
        return publishChecked;
    }
    
    //https://stackoverflow.com/questions/5706513/bind-hselectbooleancheckbox-value-to-int-integer-instead-of-boolean-boolean
   
    public void setPublishChecked(boolean checked) {
        if(service == null){
            publishChecked = false;
        }
        else{
            publishChecked = checked;        
            service.setPublished(publishChecked ? (short)1 : (short)0);
        }        
    }
    
    
    public void onKindChanged(ValueChangeEvent anEvent){
       System.out.println("onKindChanged start, getServicereference: "+service.getServicereference()+ ", anEvent.getOldValue(): "+anEvent.getOldValue());
       String theNewKind = (String) anEvent.getNewValue();
       service.setKind(theNewKind);
       System.out.println("setServicereference from PublishService.onKindChanged");
       service.setServicereference();
       System.out.println("onKindChanged end, getServicereference: "+service.getServicereference()+ ", theNewKind: "+theNewKind);
       updateAddModifyButton();
    }
    //In setServicereference, kind: BEAUTY, type: ADMINISTRATION, servicename: COIFFURE HOMME, category: AFRICAINE
     public void onTypeChanged(ValueChangeEvent anEvent){
       System.out.println("onTypeChanged start, getServicereference: "+service.getServicereference()+ ", anEvent.getOldValue(): "+anEvent.getOldValue());
       String theNewType = (String) anEvent.getNewValue();
       service.setType(theNewType);
       System.out.println("setServicereference from PublishService.onTypeChanged");
       service.setServicereference();
       System.out.println("onTypeChanged end, getServicereference: "+service.getServicereference()+", theNewType: "+theNewType);
       updateAddModifyButton();
    }
     
     
    public void onNameChanged(ValueChangeEvent anEvent){
       System.out.println("onNameChanged start, getServicereference: "+service.getServicereference()+ ", anEvent.getOldValue(): "+anEvent.getOldValue());
       String theNewName = (String) anEvent.getNewValue();
       service.setServicename(theNewName);
       System.out.println("setServicereference from PublishService.onNameChanged");
       service.setServicereference();
       System.out.println("onNameChanged end, getServicereference: "+service.getServicereference()+", theNewName: "+theNewName);
       updateAddModifyButton();
    }    
    
    public void onCategoryChanged(ValueChangeEvent anEvent){
       System.out.println("onCategoryChanged start, getServicereference: "+service.getServicereference()+ ", anEvent.getOldValue(): "+anEvent.getOldValue());
       String theNewCategory = (String) anEvent.getNewValue();
       service.setCategory(theNewCategory);
       System.out.println("setServicereference from PublishService.onCategoryChanged");
       service.setServicereference();
       System.out.println("onCategoryChanged end, getServicereference: "+service.getServicereference()+", theNewCategory: "+theNewCategory);
       updateAddModifyButton();
    }
    
    public String editItemRow(Item anItem) {
        anItem.setEdited(true);
        m_provider_services.updateItem(anItem);
      return Global.STAY_ON_CURRENT_PAGE;        
    }
       
    public String deleteItemRow(Item anItem) {
        m_provider_services.deleteItem(anItem);
        m_provider_services.updateItem(anItem);
      return Global.STAY_ON_CURRENT_PAGE;       
    }
    
    
    public String editServiceRow(Service aService) {
        aService.setEdited(true);
        m_provider_services.PersistService(aService);
      return Global.STAY_ON_CURRENT_PAGE;        
    }
       
    public String deleteServiceRow(Service aService) {
        m_provider_services.deleteService(aService);
        m_provider_services.PersistService(aService);
      return Global.STAY_ON_CURRENT_PAGE;       
    }
    
    public void updateAddModifyButton(){
        service.setServicereference();
        Service theRegistered = m_provider_services.getServiceByServiceReference(service.getServicereference());
        if(theRegistered == null){
            // display add label
        }
        else{
            // display modify label
        }
                
    }
    
}

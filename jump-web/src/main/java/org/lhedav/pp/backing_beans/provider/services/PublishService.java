/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.backing_beans.provider.services;

import org.lhedav.pp.business.ServiceEJB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.faces.event.ValueChangeEvent;
import javax.faces.model.CollectionDataModel;
import javax.inject.Named;
import org.lhedav.pp.persistence.common.CRC32StringCollection;
import org.lhedav.pp.persistence.common.Global;
import static org.lhedav.pp.persistence.common.Global.GLOBAL_DISPLAY_MESSAGE;
import org.lhedav.pp.persistence.service.Item;
import org.lhedav.pp.persistence.service.Service;

/**
 *
 * @author client
 */

@Named
@RequestScoped
public class PublishService {
    private Service service = new Service();
    static private String[] servicesKinds = {Service.KIND_HOUSEHOLD, Service.KIND_WORKPLACE};//= new String[3];      // to be xml populated at boot up
    static private String[] servicesTypes = {Service.TYPE_ADMINISTRATION, Service.TYPE_BEAUTY, Service.TYPE_EDUCATION, Service.TYPE_ENTERTAINMENT, Service.TYPE_FOOD, Service.TYPE_HEALTH, Service.TYPE_MAINTENANCE, Service.TYPE_SECURITY, Service.TYPE_TRANSPORTATION};//= new String[3]; // to be xml populated at boot up
    static private String[] servicesNames = {"Coiffure homme", "Coiffure dame", "Manicure", "Pédicure", "Manucure"};//= new String[3];      // to be xml populated at boot up
    static private String[] categoriesNames = {"Africaine", "Occidentale"};//categories pour coiffures dames
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
    private String itemReference = "Reference";    
    private String itemName = "Name";
    private String itemPrice = "Price";
    private String itemVirtual = "Virtual";
    private String itemQty = "Qty";
    private String addItemButtonLabel = "Add an item";
    private String publishButtonLabel = "Publish service";
    private List<String> m_crc_key;
            @EJB
    private ServiceEJB m_service_ejb;
    private SortedDataModel<Item> sortedDataModel;
    private String reference_;
    
    
    
    PublishService(){ 
        service.setKind(servicesKinds[0]);
        service.setType(servicesTypes[0]);
        service.setServicename(servicesNames[0]);
        service.setCategory(categoriesNames[0]);
    }
    
    public Service getService(){
        return service;
    }
    
    public void setService(Service aService){
        service = aService;
    }
    
    public boolean isServiceEmpty(){
        setReference_();
        java.lang.System.out.println("getReference_(): "+getReference_());
        List<Item>   theItems = m_service_ejb.getItemsListByServiceReference(getReference_());
        boolean isNotEmpty = (theItems != null) && (theItems.size() > 0);
        if(theItems != null){
            java.lang.System.out.println("listSize: "+theItems.size()+", ");
        }   
        java.lang.System.out.println("isNotEmpty: "+isNotEmpty);
        return !isNotEmpty;
    }
    
     public String[] getServicesKinds(){
        return servicesKinds;
    }
        
        public void setServicesKinds(String[] someKinds){
            servicesKinds = someKinds;            
        }
        
         public String[] getServicesTypes(){
        return servicesTypes;
    }
        
        public void setServicesTypes(String[] someTypes){
            servicesTypes = someTypes;
        }
      
    public String[] getServicesNames(){
        return servicesNames;
    }
        
        public void setServicesNames(String[] someNames){
            setName(servicesNames[0]);
        }
	
	 
        public String[] getCategoriesNames(){
            return categoriesNames;
        }
        
        public void setCategoriesNames(String[] someNames){            
            setCategory(categoriesNames[0]);
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
       
       public String getAddItemButtonLabel(){
          return addItemButtonLabel;
       }
        
       public void setAddItemButtonLabel(String aLabel){
           addItemButtonLabel = aLabel;
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
         
           public SortedDataModel<Item> getSortedDataModel() {
               setReference_();
           List<Item>   theItems = m_service_ejb.getItemsListByServiceReference(getReference_());
           
           sortedDataModel = new SortedDataModel<>(new CollectionDataModel<>(theItems));
           /* } catch (ParseException ex) {
            Logger.getLogger(AddModifyItem.class.getName()).log(Level.SEVERE, null, ex);
           }*/
           return sortedDataModel;
    }
           
           public String getReference_(){
           return reference_;
       }
        
       public void setReference_(){
          m_crc_key = new ArrayList();
          m_crc_key.add(service.getKind());
          m_crc_key.add(Global.REFERENCE_SPLITTER);
          m_crc_key.add(service.getType());
          m_crc_key.add(Global.REFERENCE_SPLITTER);
          m_crc_key.add(service.getServicename());
          m_crc_key.add(Global.REFERENCE_SPLITTER);
          m_crc_key.add(service.getCategory());
          m_crc_key.add(Global.REFERENCE_SPLITTER);
          m_crc_key.add("todo username");
          reference_ = (new CRC32StringCollection(m_crc_key)).hashCode() + Global.STR_EMPTY;
          System.out.println("getkind(): "+service.getKind()+", getType: "+service.getType());
          System.out.println("getName_(): "+service.getServicename()+", getCategory: "+service.getCategory());
          System.out.println("reference_: "+reference_);
       }
       
    public String publish(){
        FacesContext theCtx = FacesContext.getCurrentInstance();
         if(service.isPublished() || isServiceEmpty()){
             theCtx.addMessage(GLOBAL_DISPLAY_MESSAGE, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                                                        "Service is already published or no itemdatis created.",
                                                                        "Current service is empty or already published!"));
             return Global.STAY_ON_CURRENT_PAGE;
         }
         setReference_();
         Service theRetrived = m_service_ejb.getServiceByServiceReference(getReference_());
         theRetrived.setPublished(true);
         
         if(m_service_ejb.updateService(service)){
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

          
       
    
}

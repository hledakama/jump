/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.backing_beans.provider.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.CollectionDataModel;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.logic.ServiceEJB;
import org.lhedav.pp.business.model.common.CRC32StringCollection;
import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Service;


/**
 *
 * @author client
 */
@Named
@RequestScoped
public class AddItem {
    private String pageTitle = "Add or modify item";   
    private String Nav1 = "User account";
    private String Nav2 = "Services";
    private String Nav3 = "Provider";
    private String Nav4 = "Publish service";
    private String Nav5 = "Add or modify item";
    private String address = "Address";
    private String avatar = "Avatar";
    private String browse = "Browse";
    private String edit = "Edit";
    private String comment = "Comment";
    private String duration = "Duration";
    private String unit = "Unit";
    private static Service service = new Service();
    private String strService = "Service";
    private String strCategory = "Category";
    private static Item item = new Item();
    private String serviceReference = "ServiceReference";
    private String itemReference = "ItemReference";
    private String name = "Name";    
    private String price = "Price";
    private String qty = "Qty";
    private String virtual = "Virtual";
    private String remove = "Remove";
    private String nextItemLabel = "Add new line";
    private String saveItemLabel = "Save item";
    private String date_ = "Date";
    private SortedDataModel<Item> sortitemmodel;
    private String sortType;
    private boolean virtualItem;

    private String itemCRC;
            @EJB
    private ServiceEJB serviceEjb;
    private boolean itemNameChanged = false;
    private static String[] itemsNames = { "Boule-boole","Meche","Nattes","Batonnets","Tresse enfant", "Tissage"};      // to be xml populated at boot up
    private static final String[] providerAddresses = {"37 Duhamel, Gatineau, QC Canada", "11-1 Tasse, Gatineau, QC Canada", "52 Isabelle, Gatineau, QC Canada", "52 Des scouts, Gatineau, QC Canada"}; 
    private static final String[] unitsList = { "day(s)", "hour(s)", "min(s)", "week(s)", "month(s)"}; 
    
    AddItem(){
        item.setItemname(itemsNames[0]); 
        //item.setItemreference(getItemCRC()); 
    }

    
      public String getNav1(){
            return Nav1;
      }
        
       public void setNav1(String aNavigation){
           Nav1 = aNavigation;
       }
       
        public String getNav2(){
            return Nav2;
        }
        
       public void setNav2(String aNavigation){
           Nav2 = aNavigation;
       }
       
        public String getNav3(){
            return Nav3;
        }
        
       public void setNav3(String aNavigation){
           Nav3 = aNavigation;
       }
       
       public String getNav4(){
            return Nav4;
        }
        
       public void setNav4(String aNavigation){
           Nav4 = aNavigation;
       }
       
       public String getNav5(){
            return Nav5;
        }
        
       public void setNav5(String aNavigation){
           Nav5 = aNavigation;
       }
       
       public String getPageTitle(){
            return pageTitle;
       }
        
       public void setPageTitle(String aTitle){
           pageTitle = aTitle;
       }
       
              
        public Service getService(){
            return service;
        }
       
       public void setService(Service aService){
           service = aService;
       }
       
       
        public Item getItem(){
            return item;
        }
       
       public void setItem(Item anItem){
           item = anItem;
       }
       
        public String getStrService(){
            return strService;
        }
       
       public void setStrService(String aService){
           strService = aService;
       }
       
        public String getStrCategory(){
            return strCategory;
        }
       
       public void setStrCategory(String aCategory){
           strCategory = aCategory;
       }
            
       public String getItemCRC(){
           setItemCRC(null);
           return itemCRC;
       }
        
       public void setItemCRC(String aValue){
           List<String> theList = new ArrayList();
          theList.add(service.getKind());
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(service.getType());
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(service.getServicename());
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(service.getCategory());
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(item.getItemname());
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add("todo username");
          itemCRC = ((new CRC32StringCollection(theList)).hashCode())+Global.STR_EMPTY;
         /* System.out.println("service.getKind(): "+service.getKind());
          System.out.println("service.getType(): "+service.getType());
          System.out.println("service.getServicename(): "+service.getServicename());
          System.out.println("service.getCategory(): "+service.getCategory());
          System.out.println("item.getItemname()++: "+item.getItemname());
          System.out.println("CRC: "+itemCRC);*/
          
       }
       
       public String getServiceReference(){
           return serviceReference;
       }
       
       public void setServiceReference(String aServiceReference){
           serviceReference = aServiceReference;
       }
       
       public String getItemReference(){
           return itemReference;
       }
       
       public void setItemReference(String anItemReference){
           itemReference = anItemReference;
       }
       
         public String getName(){
            return name;
        }
        
       public void setName(String aName){
           name = aName;
       }
       
         public String getPrice(){
            return price;
        }
        
       public void setPrice(String aPrice){
           price = aPrice;
       }
       
         public String getQty(){
            return qty;
        }
        
       public void setQty(String aQty){
           qty = aQty;
       }
       
       public String getVirtual(){
            return virtual;
        }
        
       public void setVirtual(String aStatus){
           virtual = aStatus;
       }
       
       public String[] getItemsNames(){
          
        return itemsNames;
       }
       
              public void setItemsNames(String[] someNames){
            itemsNames = someNames;
       }
       

        
        public String getAddress(){
            return address;
        }
        
       public void setAddress(String anAddress){
           address = anAddress;
       }
       
       public String getAvatar(){
            return avatar;
        }
        
       public void setAvatar(String anAvatar){
           avatar = anAvatar;
       }
       
       public String getEdit(){
            return edit;
        }
        
       public void setEdit(String anEdit){
           edit = anEdit;
       }
       
       public String getComment(){
            return comment;
        }
        
       public void setComment(String aComment){
           comment = aComment;
       }
       
       public String getDuration(){
            return duration;
        }
        
       public void setDuration(String aDuration){
           duration = aDuration;
       }
       
       public String getUnit(){
            return unit;
        }
        
       public void setUnit(String aUnit){
           unit = aUnit;
       }
       
       public String getRemove(){
            return remove;
        }
        
       public void setRemove(String aRemove){
           remove = aRemove;
       }
       
       public String[] getProviderAddresses(){           
        return providerAddresses;
       }
        
       public void setProviderAddresses(String[] someAddresses){
           // providerAddresses = someAddresses;
       }
       
       public String[] getUnitsList(){
           
        return unitsList;
       }
        
       public void setUnitsList(String[] someUnits){          
          //  unitsList = someUnits;
       }
       
       public String getNextItemLabel(){
          return nextItemLabel;
       }
        
       public void setNextItemLabel(String aRemove){
           nextItemLabel = aRemove;
       }
       
        public String getSaveItemLabel(){
          return saveItemLabel;
       }
        
       public void setSaveItemLabel(String aRemove){
           saveItemLabel = aRemove;
       }
       
       
       public String getDate_(){
            return date_;
       }
        
       public void setDate_(String aDate){
           date_ = aDate;
       }
       
       public String getBrowse(){
            return browse;
       }
        
       public void setBrowse(String anImage){
           browse = anImage;
       }
       
      /* public String sortItemByRanking(final int dir) {
            Collections.sort(serviceEjb.getItemsListByServiceReference(service.getServicereference()), (Item key_1, Item key_2) -> (int) (dir * (key_1.getId() - key_2.getId())));
            return null;
       }*/
       
       public String sortItemByReference(final String dir) {
           Collection<Item> theCollection = serviceEjb.getItemsListByServiceReference(service.getServicereference());
           List theList = new ArrayList(theCollection);
           Collections.sort(theList, new Comparator<Item>() {
            @Override
            public int compare(Item key_1, Item key_2) {
                if (dir.equals("asc")) {
                return key_1.getItemreference().
                compareTo(key_2.getItemreference());
                } else {
                return key_2.getItemreference().
                compareTo(key_1.getItemreference());
                }
            }
          });
       return null;
       }

       /*
       public String sortItemByDate(final String dir) {
            Collections.sort(serviceEjb.getItemsListByServiceReference(service.getServicereference()), (Item key_1, Item key_2) -> {
                if (dir.equals("asc")) {
                    return key_1.getCdate().compareTo(key_2.getCdate());
                } else {
                    return key_2.getCdate().compareTo(key_1.getCdate());
                }
            });
            return null;
      }*/
       
       public SortedDataModel<Item> getSortitemmodel() {
           setSortitemmodel();
           return sortitemmodel;
    }
       
        public void setSortitemmodel() {
            Collection<Item> theList;
            theList = serviceEjb.getItemsListByServiceReference(service.getServicereference());
            System.out.println("theList == null: "+(theList == null) +", service.getServicereference(): "+service.getServicereference());
            if((theList != null) && (!theList.isEmpty())){
                //System.out.println("getItemname from theList: "+(theList.get(0).getItemname())+", theSize: "+theList.size());
                sortitemmodel = new SortedDataModel<>(new CollectionDataModel<>(theList));
            }
           
           
    }
       
        public String sortItemById() {

        sortitemmodel.sortThis(new Comparator<Item>() {
            @Override
            public int compare(Item key_1, Item key_2) {
                if (sortType.equals("asc")) {
                    return (int) (key_1.getItemTId()- key_2.getItemTId());
                } else {
                    return (-1) * (int) (key_1.getItemTId() - key_2.getItemTId());
                }
            }
        });
        sortType = (sortType.equals("asc")) ? "dsc" : "asc";
        return sortType;
    }
        
    public String removeRowList(@NotNull Item anItem) {
            serviceEjb.deleteItem(anItem);
        return Global.STAY_ON_CURRENT_PAGE;
    }
    
       
    public String editRowItem(@NotNull Item anItem) { 
          anItem.setEdited(false);
          serviceEjb.updateItem(anItem);
      return Global.STAY_ON_CURRENT_PAGE;         
    }
    
    
    private boolean isThereAnyEditRequest(){
        SortedDataModel<Item> theModel = getSortitemmodel();
        int theSize = theModel.getRowCount(); 
        if(theSize == 0) return false;
        for(Item theItem : theModel) {
           if(theItem.isEdited()){
                return true;
            }
        }
        return false;
    }
    
          
          
            
       public void removeItem(){
           
       }
       
       public void newItemLine(){
           
       }
       
       public String saveItem(){
           itemNameChanged = false;
           addItem();
           return Global.STAY_ON_CURRENT_PAGE;
       }
       
       private String addItem(){ 
          item.setItemreference(getItemCRC());
          service.setSubcategory(item.getItemname());
          service.addItemToList(item);
          serviceEjb.createService(service, item); 
          return Global.STAY_ON_CURRENT_PAGE;
  
       }
       
       
       public String modifyItems(){
           if(!isThereAnyEditRequest()) return Global.STAY_ON_CURRENT_PAGE;
           CollectionDataModel<Item> theModel = getSortitemmodel();
        for (Item theItem : theModel) {
            if(theItem.isEdited()){               
                theItem.setEdited(false);
                serviceEjb.updateItem(theItem);
            }        
       }
        return Global.STAY_ON_CURRENT_PAGE;
       }
       
       
    public void ItemNameChanged(ValueChangeEvent anEvent){
        if(anEvent == null) return;
       itemNameChanged = true;
    }
    
       public void ItemAddressChanged(ValueChangeEvent anEvent){
    }

    public boolean isVirtualItem() {
    return virtualItem;
    }
    
    //https://stackoverflow.com/questions/5706513/bind-hselectbooleancheckbox-value-to-int-integer-instead-of-boolean-boolean
   
    public void setVirtualItem(boolean virtual) {
         if(item == null){
             virtualItem = false;
         }
         else{
            virtualItem = virtual;        
            item.setVirtual(virtualItem ? (short)1 : (short)0);
         }
    } 
}

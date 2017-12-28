/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.backing_beans.provider.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.CollectionDataModel;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.json.ItemdataJsonBuilder;
import org.lhedav.pp.business.logic.SellerEJB;
import org.lhedav.pp.business.model.common.CRC32StringCollection;
import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Itemdata;
import org.lhedav.pp.business.model.user.Address;
import org.lhedav.pp.business.model.user.Avatar;
import org.lhedav.pp.business.model.service.Service;
import org.lhedav.pp.business.model.service.SortedDataModel;

/**
 *
 * @author client
 * 
 */
@Named
//@RequestScoped
@SessionScoped
public class ModifyItem implements Serializable{

    private String pageTitle = "Modify";
    private String Nav1 = "User account";
    private String Nav2 = "Services";
    private String Nav3 = "Provider";
    private String Nav4 = "Publish service";
    private String Nav5 = "Modify";
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
    private static Itemdata itemdata = new Itemdata();
    private String serviceKind = "Service Kind";
    private String serviceType = "ServiceType";
    private String serviceReference = "Service Reference";
    private String itemReference = "Item Reference";
    private String name = "Name";
    private String price = "Price";
    private String qty = "Qty";
    private String virtual = "Virtual";
    private String remove = "Remove";
    private String nextItemLabel = "Add new line";
    private String saveItemLabel = "Save item";
    private String date_ = "Date";
    private SortedDataModel<Itemdata> sortitemdatamodel;
    private String sortType;
    private boolean virtualItemdata;
    @EJB
    private SellerEJB provider_services;
    private boolean itemNameChanged = false; 
    private List<String> itemsNames = new ArrayList();
    private List<String> unitsList = new ArrayList();
    private SortedDataModel<Address> sortAddressesPerProvider;
    private String street1 = "Street1";
    private String street2 = "Street2";
    private String streetNumber = "streetNumber";
    private String city = "city";
    private String state = "state";
    private String zipcode = "zipcode";
    private String country = "country";
    private String showhide = "ShowHide";
    private String upload = "Upload";
    private String submitRow = "submitRow";
    private boolean isInitiated = false;  
    private String strDeletion = Global.STR_EMPTY;
    private int numOfItems = -1;

    ModifyItem() {
    }
    
    public synchronized void init() {
        //System.out.println("init");
        Service theService  = provider_services.getItemsFromService(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        List<Item> theItems = theService.getItemList();
       
        if(itemsNames.contains(strDeletion)){
                itemsNames.remove(strDeletion);                
                //System.out.println("init removing strDeletion : "+strDeletion);
                strDeletion = Global.STR_EMPTY;
            }
           
        if(null != theItems){
            numOfItems = theItems.size();
            for (Item theItem: theItems) {
                String theItemName = theItem.getItemname();                 
                if(!itemsNames.contains(theItemName)){
                    itemsNames.add(theItemName);
                }
                //System.out.println("init adding : "+theItemName +", numOfItems: "+numOfItems);
            }
        
            if(!isItemNameEmpty()){
                resetItem(itemsNames.get(0));
            }
        }
    }
    //https://stackoverflow.com/questions/6341462/initializng-a-backing-bean-with-parameters-on-page-load-with-jsf-2-0
    public void loadService(){
        Service theService  = provider_services.getItemsFromService(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        List<Item> theItems = theService.getItemList();
        
        if(!isInitiated || isItemNameEmpty() || (theItems != null) && (numOfItems != (theItems.size())) ) {
            init();
            isInitiated = true;
            numOfItems  = theItems.size();
            //System.out.println("numOfItems: "+numOfItems+ ", theItems.size(): "+theItems.size());
        }
        loadService_(); 
        setSortitemdatamodel();
    }    
   
    public String sortItemByReference(final String dir) {
        service.setServicereference();
        Collection<Item> theCollection = provider_services.getItemsListByServiceReference(service.getServicereference());
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

    public String sortItemById() {

        sortitemdatamodel.sortThis(new Comparator<Itemdata>() {
            @Override
            public int compare(Itemdata key_1, Itemdata key_2) {
                if (sortType.equals("asc")) {
                    return (int) (key_1.getItemdataTId() - key_2.getItemdataTId());
                } else {
                    return (-1) * (int) (key_1.getItemdataTId() - key_2.getItemdataTId());
                }
            }
        });
        sortType = (sortType.equals("asc")) ? "dsc" : "asc";
        return sortType;
    }

    public String removeRowItemdata(@NotNull Itemdata anItemdata) {
       loadService_();
       System.out.println("=========== before removeRowItemdata start  =============");
        List<Itemdata> theData = item.getItemdataList();
        for(Itemdata aData: theData){
            System.out.println("comment: "+aData.getComment()+ ", date: "+aData.getMdate());
        }
        System.out.println("=========== before removeRowItemdata end =============");
        if(item.removeItemDataFromList(anItemdata)){            
            if(item.getItemdataList().isEmpty()){                
                strDeletion = item.getItemname();
                if(service.removeItemFromList(item)){
                    //System.out.println("removeRowItemdata, strDeletion: "+strDeletion);
                }
            }
            service.replaceItem(item);
            provider_services.PersistService(service);
            init();
        }
        System.out.println("=========== after removeRowItemdata start  =============");
         theData = item.getItemdataList();
        for(Itemdata aData: theData){
            System.out.println("comment: "+aData.getComment()+ ", date: "+aData.getMdate());
        }
        System.out.println("=========== after removeRowItemdata end =============");
        return Global.STAY_ON_CURRENT_PAGE;
    }
    
    public void resetItem(String anItemName){
        item = new Item();
        item.setItemname(anItemName);
        item.setItemreference(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
    }
    //https://www.mkyong.com/jsf2/how-to-update-row-in-jsf-datatable/

    public String editRowItemdata(@NotNull Itemdata anItemdata) {
        anItemdata.setEdited(true);
        return Global.STAY_ON_CURRENT_PAGE;
    }

    public String submitRowItemdata(@NotNull Itemdata anItemdata) {
        // validation must be performed before here
        provider_services.updateItemdata(anItemdata);
        anItemdata.setEdited(false);
        return Global.STAY_ON_CURRENT_PAGE;
    }

     public String editRowAddress(@NotNull Address anAddress) {
        anAddress.setEdited(true);
        // provider_services.updateItemdata(anAddress);
        return Global.STAY_ON_CURRENT_PAGE;
    }

    public String saveItem() {
        itemNameChanged = false;
        modifyItem();
        return Global.STAY_ON_CURRENT_PAGE;
    }

    private String modifyItem() {
       loadService_();
       Avatar theAvatar = itemdata.saveFileToDisk();
        if (sortitemdatamodel != null) {
            for (Itemdata theItemdata : sortitemdatamodel) {
                if(theItemdata.isEdited()){
                   theItemdata.setEdited(false); 
                }
                if(theItemdata.isUploadValidated()){
                    theItemdata.saveFileToDisk();
                    theItemdata.setUploadValidated(false);
                }
            }
        }
        itemdata.addProviderAvatarToList(theAvatar);
        item.addItemDataToList(itemdata);
        item.setCdate(new Date());
        item.setItemreference(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        service.addItemToList(item);
        System.out.println("=========== modifyItem start  =============");
        List<Itemdata> theData = item.getItemdataList();
        for(Itemdata aData: theData){
            System.out.println("comment: "+aData.getComment()+ ", date: "+aData.getMdate());
        }
        System.out.println("=========== modifyItem end =============");
        service.setServicereference();
        provider_services.PersistService(service);        
        //after persisting
        itemdata = new Itemdata();
        return Global.STAY_ON_CURRENT_PAGE;
    }

    public String modifyItems() {
        if (!isThereAnyEditRequest()) {
            return Global.STAY_ON_CURRENT_PAGE;
        }
        CollectionDataModel<Itemdata> theModel = getSortitemdatamodel();
        for (Itemdata theItemdata : theModel) {
            if (theItemdata.isEdited()) {
                theItemdata.setEdited(false);
                provider_services.updateItemdata(theItemdata);
            }
        }
        return Global.STAY_ON_CURRENT_PAGE;
    }

    //https://www.mkyong.com/jsf2/jsf-2-valuechangelistener-example/   
    public void onNameChanged(ValueChangeEvent anEvent) {
        String theNewItemName = (String) anEvent.getNewValue();
        resetItem(theNewItemName);
        itemNameChanged = true;
    }

    public String ModifyAddresses() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String theItemId = facesContext.getExternalContext().getRequestParameterMap().get("itemId");
        return null;
    }

    /*https://stackoverflow.com/questions/15332733/how-to-convert-list-data-into-json-in-java
    https://roneiv.wordpress.com/2007/12/21/using-json-with-jsf/
    https://netbeans.org/kb/docs/web/ajax-quickstart.html
    https://www.journaldev.com/2315/java-json-example
    https://gist.github.com/madan712/4000063
    http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/HomeWebsocket/WebsocketHome.html
    http://hmkcode.com/java-servlet-send-receive-json-using-jquery-ajax/
    http://www.mysamplecode.com/2012/04/jquery-ajax-request-response-java.html
    http://jsfiddle.net/mjaric/sEwM6/
    https://www.javacodegeeks.com/2013/02/jquery-datatables-and-java-integration.html
    http://zetcode.com/articles/javaservletjson/
    https://stackoverflow.com/questions/14800398/why-does-ajax-and-jsf-work-nicely-together-how-do-ajax-lifecycle-plug-in-with-n
    http://memorynotfound.com/jsf-2-2-table-crud-ajax-example/
    <!--h:outputScript library="javax.faces" name="jsf.js" target="body" /-- not needed when h:head exists
        http://memorynotfound.com/jsf-2-2-basic-ajax-example/-->
    https://showcase.bootsfaces.net/forms/DataTable.jsf  
    
     */
    public String ShowHideDetails(@NotNull Itemdata anItemdata) {
        provider_services.updateItemData(anItemdata);
        JsonArray jsonDetails = ItemdataJsonBuilder.buildProviderAddress(anItemdata);
        return Global.STAY_ON_CURRENT_PAGE;
    }

    public SortedDataModel<Address> getSortAddressesPerProvider(@NotNull Itemdata anItemdata) {
        setSortAddressesPerProvider(anItemdata);
        return sortAddressesPerProvider;
    }

    public void setSortAddressesPerProvider(@NotNull Itemdata anItemdata) {
        List<Address> theAddressesList = anItemdata.getProviderAddressList();
        if ((theAddressesList != null) && (!theAddressesList.isEmpty())) {
            sortAddressesPerProvider = new SortedDataModel<>(new CollectionDataModel<>(theAddressesList));
        }
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String setStreetNumber(String aNumber) {
        return streetNumber = aNumber;
    }

    public String getStreet1() {
        return street1;
    }

    public String setStreet1(String aStreet) {
        return street1 = aStreet;
    }

    public String getStreet2() {
        return street2;
    }

    public String setStreet2(String aStreet) {
        return street2 = aStreet;
    }

    public String getCity() {
        return city;
    }

    public String setCity(String aCity) {
        return city = aCity;
    }

    public String getState() {
        return state;
    }

    public String setState(String aState) {
        return state = aState;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String setZipCode(String aZipCode) {
        return zipcode = aZipCode;
    }

    public String getCountry() {
        return country;
    }

    public String setCountry(String aCountry) {
        return country = aCountry;
    }

    public String getShowhide() {
        return showhide;
    }

    public void setShowhide(String aShow) {
        showhide = aShow;
    }

    public String getUpload() {
        return upload;
    }

    public String setUpload(String anUpload) {
        return upload = anUpload;
    }

    public String getSubmitRow() {
        return submitRow;
    }

    public String setSubmitRow(String aRowSubmit) {
        return submitRow = aRowSubmit;
    }
    
    
    public String getNav1() {
        return Nav1;
    }

    public void setNav1(String aNavigation) {
        Nav1 = aNavigation;
    }

    public String getNav2() {
        return Nav2;
    }

    public void setNav2(String aNavigation) {
        Nav2 = aNavigation;
    }

    public String getNav3() {
        return Nav3;
    }

    public void setNav3(String aNavigation) {
        Nav3 = aNavigation;
    }

    public String getNav4() {
        return Nav4;
    }

    public void setNav4(String aNavigation) {
        Nav4 = aNavigation;
    }

    public String getNav5() {
        return Nav5;
    }

    public void setNav5(String aNavigation) {
        Nav5 = aNavigation;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String aTitle) {
        pageTitle = aTitle;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service aService) {
        service = aService;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item anItem) {
        item = anItem;
    }
    
    public Itemdata getItemdata() {
        return itemdata;
    }

    public void setItemdata(Itemdata anItemdata) {
        itemdata = anItemdata;
    }

    public String getStrService() {
        return strService;
    }

    public void setStrService(String aService) {
        strService = aService;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String aCategory) {
        strCategory = aCategory;
    }

    public String getServiceKind() {
        return serviceKind;
    }

    public void setServiceKind(String aServiceKind) {
        serviceKind = aServiceKind;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String aServiceType) {
        serviceType = aServiceType;
    }

    public String getServiceReference() {
        return serviceReference;
    }

    public void setServiceReference(String aServiceReference) {
        serviceReference = aServiceReference;
    }

    public String getItemReference() {
        return itemReference;
    }

    public void setItemReference(String anItemReference) {
        itemReference = anItemReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String aPrice) {
        price = aPrice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String aQty) {
        qty = aQty;
    }

    public String getVirtual() {
        return virtual;
    }

    public void setVirtual(String aStatus) {
        virtual = aStatus;
    }

    public List<String> getItemsNames() {
      return itemsNames;
    }

    public void setItemsNames(List<String> someNames) {
        itemsNames = someNames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String anAddress) {
        address = anAddress;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String anAvatar) {
        avatar = anAvatar;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String anEdit) {
        edit = anEdit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String aComment) {
        comment = aComment;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String aDuration) {
        duration = aDuration;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String aUnit) {
        unit = aUnit;
    }

    public String getRemove() {
        return remove;
    }

    public void setRemove(String aRemove) {
        remove = aRemove;
    }

    public void setProviderAddresses(String[] someAddresses) {        
    }

    public List<String> getUnitsList() {
        return unitsList;
    }

    public void setUnitsList(List<String> someUnits) {
        unitsList = someUnits;
    }

    public String getNextItemLabel() {
        return nextItemLabel;
    }

    public void setNextItemLabel(String aRemove) {
        nextItemLabel = aRemove;
    }

    public String getSaveItemLabel() {
        return saveItemLabel;
    }

    public void setSaveItemLabel(String aRemove) {
        saveItemLabel = aRemove;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String aDate) {
        date_ = aDate;
    }

    public String getBrowse() {
        return browse;
    }

    public void setBrowse(String anImage) {
        browse = anImage;
    }
    
    public SortedDataModel<Itemdata> getSortitemdatamodel() {
        setSortitemdatamodel();
        return sortitemdatamodel;
    }
//https://stackoverflow.com/tags/jsf/info
    public void setSortitemdatamodel() {
        List<Itemdata> theList = null;
        //System.out.println("yyyyy item.getItemname(): "+item.getItemname()+", isItemNameEmpty(): "+isItemNameEmpty());
        item.setItemreference(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        if((item.getItemname() == null) && (!isItemNameEmpty())){
            resetItem(itemsNames.get(0));
        }
        Item theItem = null;
        String theItemName = item.getItemname();
        if(service.getItemList().size() > 0){
           theItem = service.getItemByName(theItemName); 
        }        
        if(theItem != null){
            theList = theItem.getItemdataList(); 
            item = theItem;
        }
        //System.out.println("theList == null: "+(theList == null)+", item.getItemname(): "+item.getItemname()+", theItemName: "+theItemName);
        sortitemdatamodel = new SortedDataModel<>(new CollectionDataModel<>(theList));
    }
    
    public boolean isItemExists(@NotNull String anItemName){
        List<Item> theItems = provider_services.getItemsListByServiceReference(service.getServicereference());
        if(theItems == null) return false;
        for(Item theItem: theItems){
            if(theItem.getItemname().equals(anItemName)){
                return true;
            }                
        }
        return false;
    }
        
        
    private boolean isThereAnyEditRequest() {
        SortedDataModel<Itemdata> theModel = getSortitemdatamodel();
        int theSize = theModel.getRowCount();
        if (theSize == 0) {
            return false;
        }
        for (Itemdata theItem : theModel) {
            if (theItem.isEdited()) {
                return true;
            }
        }
        return false;
    }
    
    
    public boolean isVirtualItemdata() {
        return virtualItemdata;
    }

    //https://stackoverflow.com/questions/5706513/bind-hselectbooleancheckbox-value-to-int-integer-instead-of-boolean-boolean
    public void setVirtualItemdata(boolean virtual) {
        if (itemdata == null) {
            virtualItemdata = false;
        } else {
            virtualItemdata = virtual;
            itemdata.setVirtual(virtualItemdata ? (short) 1 : (short) 0);
        }
    }
    
    public boolean isItemNameEmpty(){
        return (itemsNames.isEmpty() || (itemsNames.get(0)).equals(Global.STR_EMPTY));
    }
    
    public void loadService_(){
            
        String theCrc = CRC32StringCollection.getServicereference(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        //System.out.println("loadService from AddItem.init, service-->theCrc: "+theCrc);
        Service theSavedService = provider_services.getServiceByServiceReference(theCrc);
        if(theSavedService == null){
            service.setServicereference();
        }
        else{
            //System.out.println("service != null");
            service = theSavedService;
            service.setMerged(true);            
            //System.out.println("service.getItemList().size(): "+service.getItemList().size());
    
    }
    }

}

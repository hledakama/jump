/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.backing_beans.provider.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.CollectionDataModel;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.data.Services;
import org.lhedav.pp.business.json.ItemdataJsonBuilder;
import org.lhedav.pp.business.logic.ProviderEJB;
import org.lhedav.pp.business.model.common.Global;
import org.lhedav.pp.business.model.service.Item;
import org.lhedav.pp.business.model.service.Itemdata;
import org.lhedav.pp.business.model.service.ProviderAddress;
import org.lhedav.pp.business.model.service.ProviderAvatar;
import org.lhedav.pp.business.model.service.Service;
import org.lhedav.pp.business.model.service.SortedDataModel;

/**
 *
 * @author client
 */
@Named
//@RequestScoped
@SessionScoped
public class AddItem implements Serializable{

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
    private boolean virtualItem;
    @EJB
    private ProviderEJB provider_services;
    private boolean itemNameChanged = false;
    //private static String[] itemsNames = { "Boule-boole","Meche","Nattes","Batonnets","Tresse enfant", "Tissage"};      // to be xml populated at boot up
    private static final String[] providerAddresses = {"37 Duhamel, Gatineau, QC Canada", "11-1 Tasse, Gatineau, QC Canada", "52 Isabelle, Gatineau, QC Canada", "52 Des scouts, Gatineau, QC Canada"};
    //private static final String[] unitsList = { "day(s)", "hour(s)", "min(s)", "week(s)", "month(s)"}; 
    private List<String> itemsNames = new ArrayList();
    private List<String> unitsList = new ArrayList();
    private SortedDataModel<ProviderAddress> sortAddressesPerProvider;
    private String street1 = "Street1";
    private String street2 = "Street2";
    private String streetNumber = "streetNumber";
    private String city = "city";
    private String state = "state";
    private String zipcode = "zipcode";
    private String country = "country";
    private String showhide = "ShowHide";
    //private Part file;
    private String upload = "Upload";
    private String submitRow = "submitRow";

    AddItem() {
    }

    @PostConstruct
    public void init() {
        List<Services> theServicesData = provider_services.getServicesData();

        int theServicesSize = theServicesData.size();
        for (int index = 0; index < theServicesSize; index++) {
            String theString = theServicesData.get(index).getItem();
            if (itemsNames.contains(theString)) {
                continue;
            }
            itemsNames.add(theString);
        }

        //unitsList  = provider_services.getItemUnits();
        /*item.setItemname(itemsNames.get(0)); 
        System.out.println("setItemreference from AddItem.init");
        item.setItemreference(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        System.out.println("setServicereference from AddItem.init");
        service.setServicereference();*/
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

    public String[] getProviderAddresses() {
        return providerAddresses;
    }

    public void setProviderAddresses(String[] someAddresses) {
        // providerAddresses = someAddresses;
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

    public String sortItemByReference(final String dir) {
        System.out.println("setServicereference from AddItem.sortItemByReference");
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

    public SortedDataModel<Itemdata> getSortitemdatamodel() {
        setSortitemdatamodel();
        return sortitemdatamodel;
    }

    public void setSortitemdatamodel() {
        List<Itemdata> theList = null;
        System.out.println("setServicereference from AddItem.setSortitemmodel");
        item.setItemreference(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        Item theItem = provider_services.getItemByItemReference(item.getItemreference());
        if (theItem != null) {
            theList = theItem.getItemdataList();
        }
        if ((theList != null) && (!theList.isEmpty())) {
            sortitemdatamodel = new SortedDataModel<>(new CollectionDataModel<>(theList));
        }

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
        provider_services.deleteItemdata(anItemdata);
        return Global.STAY_ON_CURRENT_PAGE;
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

    public String removeRowAddress(@NotNull ProviderAddress anAddress) {
        //provider_services.deleteItemdata(anAddress);
        return Global.STAY_ON_CURRENT_PAGE;
    }

    public String editRowAddress(@NotNull ProviderAddress anAddress) {
        anAddress.setEdited(true);
        // provider_services.updateItemdata(anAddress);
        return Global.STAY_ON_CURRENT_PAGE;
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

    public void removeItem() {

    }

    public void newItemLine() {

    }

    public String saveItem() {
        itemNameChanged = false;
        addItem();
        return Global.STAY_ON_CURRENT_PAGE;
    }

    private String addItem() {
        // Single upload management
       ProviderAvatar theAvatar = itemdata.saveFileToDisk();
       if(theAvatar != null){ 
           itemdata.addProviderAvatarToList(theAvatar);
       }
       // Reset datatable to default display
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
        // Persisting ProviderAvatar, Itemdata, Item and Service
        item.addItemDataToList(itemdata);
        System.out.println("setItemreference from AddItem.addItem");
        item.setItemreference(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        service.addItemToList(item);
        provider_services.createService(service, item, itemdata, theAvatar);
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
        System.out.println("ItemNameChanged start, getItemreference: " + item.getItemreference() + ", anEvent.getOldValue(): " + anEvent.getOldValue());
        String theNewItemName = (String) anEvent.getNewValue();
        item.setItemname(theNewItemName);
        System.out.println("setItemreference from AddItem.onNameChanged");
        item.setItemreference(service.getKind(), service.getType(), service.getServicename(), service.getCategory());
        itemNameChanged = true;
        System.out.println("ItemNameChanged end, getItemreference: " + item.getItemreference() + ", theNewItemName: " + theNewItemName);
    }

    public void ItemAddressChanged(ValueChangeEvent anEvent) {
    }

    public boolean isVirtualItem() {
        return virtualItem;
    }

    //https://stackoverflow.com/questions/5706513/bind-hselectbooleancheckbox-value-to-int-integer-instead-of-boolean-boolean
    public void setVirtualItem(boolean virtual) {
        if (item == null) {
            virtualItem = false;
        } else {
            virtualItem = virtual;
            item.setVirtual(virtualItem ? (short) 1 : (short) 0);
        }
    }

    public String ModifyAddresses() {
        //returns the list of address modification page for a given item
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

    public SortedDataModel<ProviderAddress> getSortAddressesPerProvider(@NotNull Itemdata anItemdata) {
        setSortAddressesPerProvider(anItemdata);
        return sortAddressesPerProvider;
    }

    public void setSortAddressesPerProvider(@NotNull Itemdata anItemdata) {
        List<ProviderAddress> theAddressesList = anItemdata.getProviderAddressList();
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
/*
    public void UploadingFile() {

    }

    public ProviderAvatar upload(@NotNull Itemdata anItemdata) {
        ProviderAvatar theAvatar = null;
        Logger logger = Logger.getLogger(AddItem.class.getName());
        if (file != null) {

            logger.info("File Details:");
            logger.log(Level.INFO, "File component id:{0}", file.getName());
            logger.log(Level.INFO, "Content type:{0}", file.getContentType());
            logger.log(Level.INFO, "Submitted file name:{0}", file.getSubmittedFileName());
            logger.log(Level.INFO, "File size:{0}", file.getSize());
            try {
                InputStream inputStream = file.getInputStream();
                String theLocation = "images" + File.separator + "todo_user_name" + File.separator + "provider" + File.separator + "itemdata" + File.separator + "todo_time_stamp" + File.separator + file.getSubmittedFileName();
                FileOutputStream outputStream = new FileOutputStream(theLocation);
                int bytesRead = 0;
                final byte[] chunck = new byte[1024];
                while ((bytesRead = inputStream.read(chunck)) != -1) {
                    outputStream.write(chunck, 0, bytesRead);
                }
                theAvatar = new ProviderAvatar();
                theAvatar.setFileName(file.getName());
                theAvatar.setFileSize(file.getSize());
                theAvatar.setLocation(theLocation);
                theAvatar.setMimeType(file.getContentType());
                theAvatar.setSubmitedFileName(file.getSubmittedFileName());
                // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload successfully ended!"));
            } catch (IOException e) {
                e.printStackTrace();
                // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
            }
        }
        return theAvatar;
    }

    public void upload() {

    }*/

    public String getSubmitRow() {
        return submitRow;
    }

    public String setSubmitRow(String aRowSubmit) {
        return submitRow = aRowSubmit;
    }

   /* public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public void validateFile() {
        //VALIDATE FILE NAME LENGTH
        String name = file.getSubmittedFileName();
        if (name.length() == 0) {
            resetFile();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Error: Cannot determine the file name !"));
        } else if (name.length() > 25) {
            resetFile();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Error: The file name is to long !"));
        } else //VALIDATE FILE CONTENT TYPE
        if ((!"image/png".equals(file.getContentType())) && (!"image/jpeg".equals(file.getContentType()))) {
            resetFile();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Error: Only images can be uploaded (PNGs and JPGs) !"));
        } else //VALIDATE FILE SIZE (not bigger than 1 MB)        
        if (file.getSize() > 1048576) {
            resetFile();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload Error: Cannot upload files larger than 1 MB !"));
        }
    }

    public ProviderAvatar saveFileToDisk() {
        ProviderAvatar theAvatar = null;
        if (file != null) {
            String theLocation = "images" + File.separator + "todo_user_name" + File.separator + "provider" + File.separator + "itemdata" + File.separator + "todo_time_stamp" + File.separator + file.getSubmittedFileName();
            try (InputStream inputStream = file.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(theLocation)) {
                int bytesRead;
                final byte[] chunck = new byte[1024];
                while ((bytesRead = inputStream.read(chunck)) != -1) {
                    outputStream.write(chunck, 0, bytesRead);
                }
                theAvatar = new ProviderAvatar();
                theAvatar.setFileName(file.getName());
                theAvatar.setFileSize(file.getSize());
                theAvatar.setLocation(theLocation);
                theAvatar.setMimeType(file.getContentType());
                theAvatar.setSubmitedFileName(file.getSubmittedFileName());
                resetFile();                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload successfully ended!"));
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
            }
        }
        return theAvatar;
    }

    public void resetFile() {
        try {
            if (file != null) {
                file.delete();
            }
        } catch (IOException ex) {
            Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = null;
    }*/
}

package org.lhedav.pp.business.model.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
import org.lhedav.pp.business.data.Categories;
import org.lhedav.pp.business.data.Items;
import org.lhedav.pp.business.data.ServiceKind;
import org.lhedav.pp.business.data.ServiceType;
import org.lhedav.pp.business.data.Services;
import org.lhedav.pp.business.model.service.Itemdata;
import org.lhedav.pp.business.model.user.Avatar;

public class Global {
	
	public static final String STR_EMPTY = "";
	public static final String PERSISTENCE_UNIT = "org.lhedav.pp_jump-ejb_PU"; // if changed, please update the persitence.xml too
	public static final String CUSTOMER_RATING = "CUSTOMER_RATING";
	public static final String PROVIDER_RATING = "PROVIDER_RATING";
	public static final int NUMBER_OF_PROVIDER_RATING_CRITERIAS = 6;
	public static final int NUMBER_OF_CUSTOMER_RATING_CRITERIAS = 5;
	public static String[] CUSTOMER_RATING_CRITERIAS;  // populated from xml
	public static String CORRECT_TOOLS_CRITERIA = "CORRECT_TOOLS"; // provider only
	public static String ENVIRONMENT_CRITERIA   = "ENVIRONMENT";   // @hoster, can be both
	public static String INPUT_CRITERIA         = "INPUT";         // provider only
	public static String ON_TIME_CRITERIA       = "ON_TIME";       // both
	public static String QUALITY_CRITERIA       = "QUALITY";       // provider only
	public static String RESPECT_CRITERIA       = "RESPECT";       // both	
	public static String PAYMENT_CRITERIA       = "PAYMENT";       // customer only	
	public static String[] PROVIDER_RATING_CRITERIAS;  // populated from xml
        public static final String REFERENCE_SPLITTER = "#";
        public static String GLOBAL_DISPLAY_MESSAGE = null;
        public static String STAY_ON_CURRENT_PAGE = null;
        public static String FILE_DOT = ".";
        public static File diropt = new File(File.separator + "opt");
        public static File dirlhedav;
        public static File dirimages;
        public static File dirtmp;
        public static File dirprovider;
        public static File diritemdata;
        public static File dirprofile;
        public static String FILE_SPITTER = "_";
        public static String PNG_FILE_TYPE = "image/png";
        public static String JPEG_FILE_TYPE = "image/jpeg";
        public static int MAX_IMAGE_FILE_SIZE = 1048576;// 1Mbytes
        public static int MAX_IMAGE_FILE_NAME_LENGTH = 255;
        public static String DEFAULT_PROFILE_IMAGE_NAME = "default_profile_image.jpg";
        public static String DEFAULT_SHOPPING_IMAGE_NAME = "default_shopping_image.jpg";
        public static File default_shopping_file;
        public static File default_profile_file;
        private static boolean uploadValidated = false;
        //http://blog4j.free.fr/index.php/2008/02/12/glassfish-les-alternate-docroot/
        public static String PROVIDER_IMAGES_FOLDER = "http://localhost:8080/jump-web-1.0-SNAPSHOT/itemdata/";
        public static int IMAGE_WIDTH  = 100;
        public static int IMAGE_HEIGTH = 120;
        
        public static int IMAGE_MIN_WIDTH  = 50;
        public static int IMAGE_MIN_HEIGTH = 60;
        public final static String KIND = "KIND";
        public final static String TYPE = "TYPE";
        public final static String SERVICES = "SERVICES";
        public final static String CATEGORIES = "CATEGORIES";
        public final static String ITEMS = "ITEMS";
        public static String PROVIDER_XML_CONFIG_URL = "http://localhost:8080/jump-web-1.0-SNAPSHOT/resources/app_config/globalConfiguration.xml";
        
        public static byte[] getHash(String aRawData){
            MessageDigest m=null;
            try {
              m=MessageDigest.getInstance("SHA-256");
              m.update(aRawData.getBytes());
            }
           catch (  NoSuchAlgorithmException e) {
              e.printStackTrace();
            }
          catch (  NullPointerException e) {
              e.printStackTrace();
            }
            return m.digest();
        }
        
        private static File CheckDirCreateDir(File container,  String strPathName ){         
            File theDir = new File( container, strPathName );
            if( !theDir.exists() ){
                    theDir.mkdirs();
            }
            return theDir;
	}
        
        public static void CheckCreateDirectories(){            
            //System.out.println("dirlhedav creation");
            dirlhedav   = CheckDirCreateDir( diropt, "lhedav");
            //System.out.println("dirimages creation");
            dirimages   = CheckDirCreateDir( dirlhedav, "images");
            //System.out.println("dirprovider creation");
            dirtmp   = CheckDirCreateDir( dirimages, "tmp");            
            dirprovider = CheckDirCreateDir( dirimages, "provider");
            //System.out.println("diritemdata creation");            
            diritemdata = CheckDirCreateDir( dirprovider, "itemdata");
            dirprofile = CheckDirCreateDir( dirprovider, "profile");
            //https://www.mkyong.com/java/how-to-get-file-size-in-java/
            default_shopping_file =new File(diritemdata.getAbsolutePath()+DEFAULT_SHOPPING_IMAGE_NAME);
            default_profile_file =new File(dirprofile.getAbsolutePath()+DEFAULT_PROFILE_IMAGE_NAME);
            //System.out.println("CheckCreateDirectories end");
        }
        
        public static FileOutputStream openItemdataForWrite( String aName ){
            //System.out.println("openItemdataForWrite, aName: "+aName);
            FileOutputStream Filos;
            String thePath = diritemdata.getPath();
            try{
                Filos = new FileOutputStream( thePath+File.separator+aName );
            } catch( IOException e ){
                    return null;
            }
            //System.out.println("openItemdataForWrite, thePath: "+thePath);
            return Filos;
	}
    
    public static boolean validateFile(Part aFile) {
        if(aFile == null) return false;
        String name = aFile.getSubmittedFileName();
        if (name.length() == 0) {
            resetFile(aFile);
            setUploadValidated(false);
            return isUploadValidated();           
        } else if (name.length() > Global.MAX_IMAGE_FILE_NAME_LENGTH) {
            resetFile(aFile);
            setUploadValidated(false);
            return isUploadValidated();
        } else if ((!Global.PNG_FILE_TYPE.equals(aFile.getContentType())) && (!Global.JPEG_FILE_TYPE.equals(aFile.getContentType()))) {
            resetFile(aFile);
            setUploadValidated(false);            
            return isUploadValidated();
        } else if (aFile.getSize() > Global.MAX_IMAGE_FILE_SIZE) {
            resetFile(aFile);
            setUploadValidated(false);
            return isUploadValidated();
        }
        setUploadValidated(true);        
        return isUploadValidated();
    }

    public static Avatar saveFileToDisk(@NotNull Itemdata anItemdata, boolean aNewData, @NotNull Part aFile) {
        Avatar theAvatar;
        if(aNewData){
            theAvatar = new Avatar();
        }
        else{
            theAvatar = anItemdata.getAvatarFromRank();
        }                                
            try { 
                //https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
                Global.CheckCreateDirectories();
                //https://stackoverflow.com/questions/6233541/java-set-file-permissions-to-777-while-creating-a-file-object
                InputStream inputStream = aFile.getInputStream();
                String theSubmitedFileName = (new Date()).getTime()+ 
                                              Global.FILE_SPITTER  + 
                                              aFile.getSubmittedFileName();
                try (FileOutputStream outputStream = Global.openItemdataForWrite( theSubmitedFileName )) {
                    int bytesRead;
                    final byte[] chunck = new byte[1024];
                    while ((bytesRead = inputStream.read(chunck)) != -1){
                        outputStream.write(chunck, 0, bytesRead);
                    }
                }                
                theAvatar.setFileName(aFile.getName());
                theAvatar.setFileSize(aFile.getSize());                
                theAvatar.setMimeType(aFile.getContentType());
                theAvatar.setSubmitedFileName(theSubmitedFileName);
                anItemdata.setCurrentAvatar(Global.diritemdata.getAbsolutePath() + File.separator + theSubmitedFileName);
                theAvatar.setLocation(Global.PROVIDER_IMAGES_FOLDER+theSubmitedFileName);
                anItemdata.addProviderAvatarToList(theAvatar);
                resetFile(aFile);
                Date theNewDate = new Date();
                if(aNewData){
                    anItemdata.setMdate(theNewDate);
                    anItemdata.setSdate(theNewDate);
                }
                else{
                    //System.out.println(" not new, nItemdata.getMdate():  "+anItemdata.getMdate()+", new Date(): "+(new Date()));
                    anItemdata.setMdate(anItemdata.getMdate());// never change. It is a keu used in market
                    anItemdata.setSdate(theNewDate); 
                }
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload successfully ended!"));
            } catch (IOException e) {                  
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
            }

        return theAvatar;
    }
    
    public static void resetFile(Part aFile) {
        setUploadValidated(false);
        if(aFile == null) return;
        try {           
                aFile.delete();
        } catch (IOException ex) {
            Logger.getLogger(Itemdata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public static boolean isUploadValidated(){
        return uploadValidated;
    }
    
    public static void setUploadValidated(boolean aBool){
        uploadValidated = aBool;
    }
    //https://www.w3resource.com/java-tutorial/arraylist/arraylist_removeall.php
    public static void cleanList(List<String> someComboContent){
        if((someComboContent == null) || (someComboContent.isEmpty())) return;
        /*int theSize = someComboContent.size();
        for(int index = 0; index < theSize; index++){
            someComboContent.remove(index);
        }*/
        someComboContent.removeAll(someComboContent);
    }
    
    public static List<String> buildComboBoxContent(List<ServiceKind> aKindList, 
                                            List<ServiceType> aTypeList, 
                                            List<Services> someServicesList,
                                            List<Categories> someCategories,
                                            List<Items> someItems,
                                            @NotNull List<String> someComboContent,
                                            @NotNull String whatType,
                                            String inFirstRank){
        
        
        if(!someComboContent.isEmpty()){
            cleanList(someComboContent);
        }
        if(inFirstRank != null){
            someComboContent.add(inFirstRank);
        }
        
        switch(whatType){
            case KIND:
                //System.out.println("buildComboBoxContent whatType: "+whatType+", someComboContent.size(): "+someComboContent.size()+ " aKindList.size(): "+ (aKindList.size()));
                if(aKindList.isEmpty()) return null;                
                for(ServiceKind aKind : aKindList){
                    if((aKind == null) || (someComboContent.contains(aKind.getKind()))) continue;
                    someComboContent.add(aKind.getKind());
                   // System.out.println("buildComboBoxContent aKind.getKind(): "+aKind.getKind());
                }
                break;
            case TYPE:
                //System.out.println("buildComboBoxContent whatType: "+whatType+", someComboContent.size(): "+someComboContent.size()+ " aTypeList.size(): "+ (aTypeList.size()));
                if(aTypeList.isEmpty()) return null;
                for(ServiceType aType : aTypeList){
                    if((aType == null) || (someComboContent.contains(aType.getType()))) continue;
                    someComboContent.add(aType.getType());
                    //System.out.println("buildComboBoxContent aType.getType(): "+aType.getType());
                }
                break;
            case SERVICES:
                //System.out.println("buildComboBoxContent whatType: "+whatType+", someComboContent.size(): "+someComboContent.size()+ " someServicesList.size(): "+ (someServicesList.size()));
                if(someServicesList.isEmpty()) return null;
                for(Services someServices : someServicesList){
                    if((someServices == null) || (someComboContent.contains(someServices.getName()))) continue;
                    someComboContent.add(someServices.getName());
                    //System.out.println("buildComboBoxContent someServices.getName(): "+someServices.getName());
                }
                break;
            case CATEGORIES:
                //System.out.println("buildComboBoxContent whatType: "+whatType+", someComboContent.size(): "+someComboContent.size()+ " someCategories.size(): "+ (someCategories.size()));
                if(someCategories.isEmpty()) return null;
                for(Categories aCategory : someCategories){
                    if((aCategory == null) || (someComboContent.contains(aCategory.getName()))) continue;
                    someComboContent.add(aCategory.getName());
                    //System.out.println("buildComboBoxContent aCategory.getName(): "+aCategory.getName());
                }
                break;
            case ITEMS:
                //System.out.println("buildComboBoxContent whatType: "+whatType+", someComboContent.size(): "+someComboContent.size()+ " someItems.size() "+ (someItems.size()));
                if(someItems.isEmpty()) return null;
                for(Items aData : someItems){
                    if((aData == null) || (someComboContent.contains(aData.getName()))) continue;
                    someComboContent.add(aData.getName());
                    //System.out.println("buildComboBoxContent aData.getName(): "+aData.getName());
                }
                break;
            default:
                //System.out.println("buildComboBoxContent default-->whatType: "+whatType+", someComboContent.size(): "+someComboContent.size());
                break;
        }
        return someComboContent;
    }
    
    public static boolean isThereMatching(List<ServiceKind> aKindList, 
                                            List<ServiceType> aTypeList, 
                                            List<Services> someServicesList,
                                            List<Categories> someCategories,
                                            List<Items> someItems,
                                            @NotNull String strToCheck,
                                            @NotNull String whatType){
        
        switch(whatType){
            case KIND:
                //System.out.println("isThereMatching whatType: "+whatType+ " aKindList == null "+ (aKindList == null)+ ", strToCheck: "+strToCheck);
                if((aKindList == null) || aKindList.isEmpty()) return false;
                for(ServiceKind aKind : aKindList){
                    if(aKind == null) continue;
                    if(aKind.getKind().equals(strToCheck)){
                        return true;
                    }                    
                }
                return false;
            case TYPE:
                //System.out.println("isThereMatching whatType: "+whatType+ " aTypeList == null "+ (aTypeList == null)+ ", strToCheck: "+strToCheck);
                if((aTypeList == null)  || aTypeList.isEmpty()) return false;
                for(ServiceType aType : aTypeList){
                    if(aType == null) continue;
                    if(aType.getType().equals(strToCheck)){
                        return true;
                    }                   
                }
                return false;
            case SERVICES:
                //System.out.println("isThereMatching whatType: "+whatType+ " someServicesList == null "+ (someServicesList == null)+ ", strToCheck: "+strToCheck);
                if((someServicesList == null)  || someServicesList.isEmpty()) return false ;
                for(Services someServices : someServicesList){
                    if(someServices == null) continue;
                    if(someServices.getName().equals(strToCheck)){
                        return true;
                    }
                }
                return false;
            case CATEGORIES:
                //System.out.println("isThereMatching whatType: "+whatType+ " someCategories == null "+ (someCategories == null)+ ", strToCheck: "+strToCheck);
                if((someCategories == null)|| someCategories.isEmpty()) return false;
                for(Categories someCategory : someCategories){
                    if(someCategory == null) continue;
                    if(someCategory.getName().equals(strToCheck)){
                        return true;
                    }
                }
                return false;
            case ITEMS:
                //System.out.println("isThereMatching whatType: "+whatType+ " someItems == null "+ (someItems == null)+ ", strToCheck: "+strToCheck);
                if((someItems == null)|| someItems.isEmpty()) return false;
                for(Items someElement : someItems){
                    if(someElement == null) continue;
                    if(someElement.getName().equals(strToCheck)){
                        return true;
                    }
                }
                return false;
            default:
                //System.out.println("isThereMatching default-->whatType: "+whatType);
                return false;
        }
    }
    
    public static List<ServiceKind> mergeKindStructures(List<ServiceKind> parsedKindList, List<ServiceKind> dbParsedList){
        if((parsedKindList == null) || parsedKindList.isEmpty()) return null;                
        if(dbParsedList == null){
           dbParsedList = new ArrayList();
       }
        for(ServiceKind aKind : parsedKindList){
            if(Global.isThereMatching(dbParsedList, null, null, null, null, aKind.getKind(),Global.KIND)) {
                //System.out.println("mergeKindStructures continue aKind.getKind(): "+aKind.getKind());
                continue;
            }
            dbParsedList.add(aKind);
            //System.out.println("mergeKindStructures aKind.getKind(): "+aKind.getKind());
        }
        return dbParsedList;
    }
    
    public static List<ServiceType> mergeTypeStructures(List<ServiceType> parsedTypeList, List<ServiceType> dbParsedList){
        if((parsedTypeList == null) || parsedTypeList.isEmpty() ) return null;                
        if(dbParsedList == null){
           dbParsedList = new ArrayList();
       }
        for(ServiceType aType : parsedTypeList){
            if(Global.isThereMatching(null, dbParsedList, null, null, null, aType.getType(),Global.TYPE)) {
                //System.out.println("mergeTypeStructures aType.getType(): "+aType.getType());
                continue;
            }
            dbParsedList.add(aType);
            //System.out.println("mergeTypeStructures aType.getType(): "+aType.getType());
        }
        return dbParsedList;
    }
    
    public static List<Services> mergeServicesStructures(List<Services> parsedServicesList, List<Services> dbParsedList){
        if((parsedServicesList == null) || parsedServicesList.isEmpty()) return null;                
        if(dbParsedList == null){
           dbParsedList = new ArrayList();
       }
        for(Services someServices : parsedServicesList){
            if(Global.isThereMatching(null, null, dbParsedList, null, null, someServices.getName(),Global.SERVICES)) {
                //System.out.println("mergeServicesStructures continue someServices.getName: "+someServices.getName());
                continue;
            }
            dbParsedList.add(someServices);
            //System.out.println("mergeServicesStructures someServices.getName: "+someServices.getName());
        }
        return dbParsedList;
    }
    
    public static List<Categories> mergeCategoriesStructures(List<Categories> parsedCategoriesList, List<Categories> dbParsedList){
       if((parsedCategoriesList == null) || parsedCategoriesList.isEmpty()) return null; 
       if(dbParsedList == null){
           dbParsedList = new ArrayList();
       }
        for(Categories someCategory : parsedCategoriesList){
            if(Global.isThereMatching(null, null, null, dbParsedList, null, someCategory.getName(),Global.CATEGORIES)) {
                //System.out.println("mergeCategoriesStructures continue someCategory.getName: "+someCategory.getName());
                continue;
            }
            dbParsedList.add(someCategory);
            //System.out.println("mergeCategoriesStructures someCategory.getName: "+someCategory.getName());
        } 
        return dbParsedList;
    }
    
    public static List<Items> mergeItemsStructures(List<Items> parsedItemsList, List<Items> dbParsedList){
      if((parsedItemsList == null) || parsedItemsList.isEmpty() ) return null;                
        for(Items someItem : parsedItemsList){
            if(Global.isThereMatching(null, null, null, null, dbParsedList, someItem.getName(),Global.ITEMS)) {
                //System.out.println("mergeItemsStructures continue someItem.getName(): "+someItem.getName());
                continue;
            }
            dbParsedList.add(someItem);
            //System.out.println("mergeItemsStructures someItem.getName(): "+someItem.getName());
        } 
        return dbParsedList;
    }        
}


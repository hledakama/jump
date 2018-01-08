package org.lhedav.pp.business.model.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;
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
            System.out.println("dirlhedav creation");
            dirlhedav   = CheckDirCreateDir( diropt, "lhedav");
            System.out.println("dirimages creation");
            dirimages   = CheckDirCreateDir( dirlhedav, "images");
            System.out.println("dirprovider creation");
            dirtmp   = CheckDirCreateDir( dirimages, "tmp");            
            dirprovider = CheckDirCreateDir( dirimages, "provider");
            System.out.println("diritemdata creation");            
            diritemdata = CheckDirCreateDir( dirprovider, "itemdata");
            dirprofile = CheckDirCreateDir( dirprovider, "profile");
            //https://www.mkyong.com/java/how-to-get-file-size-in-java/
            default_shopping_file =new File(diritemdata.getAbsolutePath()+DEFAULT_SHOPPING_IMAGE_NAME);
            default_profile_file =new File(dirprofile.getAbsolutePath()+DEFAULT_PROFILE_IMAGE_NAME);
            System.out.println("CheckCreateDirectories end");
        }
        
        public static FileOutputStream openItemdataForWrite( String aName ){
            System.out.println("openItemdataForWrite, aName: "+aName);
            FileOutputStream Filos;
            String thePath = diritemdata.getPath();
            try{
                Filos = new FileOutputStream( thePath+File.separator+aName );
            } catch( IOException e ){
                    return null;
            }
            System.out.println("openItemdataForWrite, thePath: "+thePath);
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
                    System.out.println(" not new, nItemdata.getMdate():  "+anItemdata.getMdate()+", new Date(): "+(new Date()));
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
        
}


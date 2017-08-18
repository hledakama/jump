package org.lhedav.pp.persistence.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Global {
	
	public static final String STR_EMPTY = "";
	public static final String PERSISTENCE_UNIT = "lhe_PU_2017"; // if changed, please update the persitence.xml too
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
}

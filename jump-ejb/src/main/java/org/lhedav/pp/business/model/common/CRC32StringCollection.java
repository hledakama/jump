/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.zip.CRC32;

/**
 *
 * @author client
 */
public class CRC32StringCollection {
     private Collection<String> m_strings_list;
     public final static int INVALID_CRC = -1;

    public CRC32StringCollection(Collection<String> theList) {
        m_strings_list = theList;
    }

     @Override
    public  int hashCode() {        
        CRC32 theCrc = new CRC32();
        if(m_strings_list == null) return INVALID_CRC;
        for (String theString : m_strings_list) {
            if(theString == null) return INVALID_CRC;
            char[] theArray = theString.toCharArray();
            if(theArray == null) return INVALID_CRC;
            int theLength = theArray.length;
            for(int theCharIndex = 0; theCharIndex < theLength; theCharIndex++){
                theCrc.update(theArray[theCharIndex]);
            } 
        }
        return (int)( theCrc.getValue() );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CRC32StringCollection other = (CRC32StringCollection) obj;
        if (!Objects.equals(this.m_strings_list, other.m_strings_list)) {
            return false;
        }
        return true;
    }
    
    public static String getServicereference(String aKind, String aType, String aServiceName, String aCategory) {
        //System.out.println("In CRC32StringCollection.getServicereference, kind: "+aKind+ ", type: "+aType+ ", servicename: "+ aServiceName+ ", category: "+aCategory);
          List the_crc_key = new ArrayList();
          the_crc_key.add(aKind);
          the_crc_key.add(Global.REFERENCE_SPLITTER);
          the_crc_key.add(aType);
          the_crc_key.add(Global.REFERENCE_SPLITTER);
          the_crc_key.add(aServiceName);
          the_crc_key.add(Global.REFERENCE_SPLITTER);
          the_crc_key.add(aCategory);
          return (new CRC32StringCollection(the_crc_key)).hashCode() + Global.STR_EMPTY; 
    }
    
        public static String getItemreference(String aKind, String aType, String aService, String aCategory, String anItemName) {
        //System.out.println("In CRC32StringCollection.getItemreference, aKind: "+aKind+ ", aType: "+aType+ ", aService: "+ aService+ ", aCategory: "+aCategory + ", anItemName: "+anItemName);
        List<String> theList = new ArrayList();
          theList.add(aKind);
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(aType);
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(aService);
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(aCategory);
          theList.add(Global.REFERENCE_SPLITTER);
          theList.add(anItemName);
          return ((new CRC32StringCollection(theList)).hashCode())+ Global.STR_EMPTY;           
    } 
}

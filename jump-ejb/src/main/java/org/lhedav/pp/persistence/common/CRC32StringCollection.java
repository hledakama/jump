/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.persistence.common;

import java.util.Collection;
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
}

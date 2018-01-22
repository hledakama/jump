/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.common.parsing.sax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.lhedav.pp.business.data.ServiceKind;
import org.lhedav.pp.business.data.Unit;
import org.lhedav.pp.business.model.common.Global;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author client
 * This XML file does not appear to have any style information associated with it. The document tree is shown below.
 */
public class ConfigurationParser {
  public static List<ServiceKind> objListOfKinds;    
  public static List<Unit> objListOfUnits;
  public final static String KIND_KEY = "KIND_KEY";
  public final static String UNIT_KEY = "UNIT_KEY";
  

    public static HashMap<String, List> parse(FacesContext aFacesContext){
        if(aFacesContext == null) return null;
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(true);
        
        XMLReader reader = null;
        try {
            SAXParser parser = spf.newSAXParser();
            reader = parser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            System.err.println(e);
            System.exit(1);
        }
        reader.setErrorHandler(new ConfigurationErrorHandler());
        reader.setContentHandler(new ConfigurationContentHandler());
        try {
            //https://stackoverflow.com/questions/13242782/reading-a-text-file-in-war-archive
            //https://haveacafe.wordpress.com/2008/10/19/how-to-read-a-file-from-jar-and-war-files-java-and-webapp-archive/
            //InputStream inputStream = aFacesContext.getClass().getClassLoader().getResourceAsStream(Global.PROVIDER_XML_CONFIG_URL);
            //InputSource is = new InputSource(inputStream);
            InputSource is = new InputSource(Global.PROVIDER_XML_CONFIG_URL);
            reader.parse(is);
            HashMap<String, List> theResult = new HashMap();
            theResult.put(KIND_KEY, objListOfKinds);
            theResult.put(UNIT_KEY, objListOfUnits);
            return theResult;
        } catch (SAXException e) {
            System.exit(1);
            return null;
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
            return null;
        }    
    } 
}

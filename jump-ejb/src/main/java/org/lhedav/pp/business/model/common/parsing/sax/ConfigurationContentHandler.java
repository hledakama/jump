/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.common.parsing.sax;

import java.util.ArrayList;
import javax.ejb.EJB;
import org.lhedav.pp.business.data.Categories;
import org.lhedav.pp.business.data.Items;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.lhedav.pp.business.data.ServiceKind;
import org.lhedav.pp.business.data.ServiceType;
import org.lhedav.pp.business.data.Services;
import org.lhedav.pp.business.data.Unit;
import org.lhedav.pp.business.logic.SellerEJB;

/**
 *
 * @author client
 */
public class ConfigurationContentHandler implements ContentHandler{    
  
  private ServiceKind objServiceKind;
  private ServiceType objServiceType;
  private Services objServices;
  private Categories objCategories;
  private Items objItems;
  private Unit objUnit;
  private Locator objLocator;  
    @EJB
  private SellerEJB provider_services;
  
  public ConfigurationContentHandler(){
      
  }

        /*This method is called just as the parser gets itself set up
            ready to start reading the XML document. The SAX specification does not strictly
            require that this method be called but, if it is, it is the first one to be called. The
            Locator object contains the name of the document being parsed along with a
            running line number, which can be used by an application for status messages.*/
    @Override
    public void setDocumentLocator(Locator lctr) {
        objLocator = lctr;
    }

        /*This method is called just to notify the application that the
            parsing has begun. It is always called at the very beginning of the parse, after
            setDocumentLocator() but before any of the methods that supply data.*/
    @Override
    public void startDocument() throws SAXException {
        
    }

        /*This method is called at the very end to notify the application that
            parsing is complete and no more data will be arriving.*/
    @Override
    public void endDocument() throws SAXException {
    }
        /*This method is called whenever there is an internal
            namespace name being associated with its URI. In most cases this information is
            not necessary because the other methods—the ones that are called with the tag
            names—always include the namespace prefix and its URI along with the name of
            the tag. However, this method is called because there could be a situation where
            namespace scoping needs to be tracked. A call to this method will mark the point
            at which the namespace is first declared.*/
    @Override
    public void startPrefixMapping(String prefix,String uri) throws SAXException {
    }
        /*This method is called when exiting the scope of a previously
            declared start of a prefix mapping. For each call to startPrefixMapping() there
            is a matching call to endPrefixMapping(). The calls that start and end prefix
            mapping do not necessarily occur in properly nested order. For example, if three
            calls to startPrefixMapping() open the scope of aa, bb, and cc, it is possible
            that the call to endPrefixMapping() would close their scopes in some other order,
            such as bb, cc, and aa.*/
    @Override
    public void endPrefixMapping(String string) throws SAXException {
        
    }
        /*This method is called for each opening tag. The arguments
            passed to the method include the name of the tag and all of its attributes. The
            namespace is included in the form of its URI, which means that it is possible for
            an application to process an XML document and completely ignore the internal
            names it uses for the namespaces; the URI names can be used to guarantee iden-
            tity of the appropriate namespace. The list of attributes includes only the attrib-
            utes that were explicitly specified; that is, #IMPLIED attributes are not included.*/
    @Override
    public void startElement(String namespaceURI,String localName,String qName,Attributes atts) throws SAXException {
       //System.out.println(" start namespaceURI: "+namespaceURI+", localName: "+localName + ", qName: "+qName + " atts.getQName(0): "+atts.getQName(0));
        switch(qName){
            case "kinds": 
                ConfigurationParser.objListOfKinds  = new ArrayList();
                break;
            case "kind":
                objServiceKind  = new ServiceKind();
                objServiceKind.setKind(atts.getValue(atts.getQName(0)));
                break;
            case "types":                
                break;
            case "type":
                    objServiceType = new ServiceType();
                    objServiceType.setType(atts.getValue(atts.getQName(0)));
                break;
            case "services":                
                break;
            case "service":
                objServices     = new Services();
                objServices.setName(atts.getValue(atts.getQName(0)));
                //System.out.println("cat name: "+objServices.getName());
                break;
            case "categories":                
                break;
            case "category":
                objCategories = new Categories();
                objCategories.setName(atts.getValue(atts.getQName(0)));
                //System.out.println("cat name: "+objCategories.getName());
                break;
            case "items":                
                break;
            case "item":
                objItems = new Items();
                objItems.setName(atts.getValue(atts.getQName(0)));
                //System.out.println("itemname: "+objItems.getName());
                break;
            case "units":
                ConfigurationParser.objListOfUnits = new ArrayList();
                break;
            case "unit":
                objUnit = new Unit();
                objUnit.setUnit(atts.getValue(atts.getQName(0)));
               //System.out.println("unit: "+objUnit.getUnit());
                break;
            default: 
                
                break;                
        }
    }
        /*This method is called for each closing tag. There is a matching call
            to endElement() for each call to startElement(). Any data contained in theSAX Document Parse and Read
            element will have already been passed to the application; that is, proper nesting
            of tags is maintained. This method is called even when the element is empty.*/
    @Override
    public void endElement(String namespaceURI,String localName,String qName) throws SAXException {
        //System.out.println(" end namespaceURI: "+namespaceURI+", localName: "+localName + ", qName: "+qName); 
        switch(qName){
            case "kinds":                 
                break;
            case "kind":
                ConfigurationParser.objListOfKinds.add(objServiceKind);
                //System.out.println();
                break;
            case "types":
                break;
            case "type":
                objServiceKind.addServiceTypeToList(objServiceType);
                break;
            case "services":
                break;
            case "service":
                objServiceType.addServicesToList(objServices);
                break;
            case "categories":
                break;
            case "category":
                objServices.addCategoriesToList(objCategories);
                break;
            case "items":
                break;
            case "item":
                objCategories.addItemsToList(objItems);
                break;
            case "units":                
                break;
            case "unit":
                ConfigurationParser.objListOfUnits.add(objUnit);
                break;
            default: 
                
                break;                
        }
    }
        /*This method is called with any text found inside an element. It
            normally comes as a single block of text, but it is possible that multiple calls
            to characters() may be executed to deliver large blocks of text in pieces.
            The string is passed in as an array of characters with two integers that specify
            the index to the first character and a count of the number of characters. For
            example, if the characters in the array are a, b, c, d, e, and f, and the two inte-
            ger values are 2 and 4, the only characters being passed in are c, d, and e. Charac-
            ters in the array outside of those specified as being part of string should be con-
            sidered off limits to the callback method. Some character arrays may also be sent
            to the application with a call to ignorableWhitespace() instead of a call to
            characters().*/
    @Override
    public void characters(char[] ch,int start,int length) throws SAXException {
        
    }
        /*This method is called with whitespace strings. If the
            parser is not validating, there is no way it can determine whether any particular
            string of whitespace can be ignored, so it will probably send all of the strings to
            the application by calling characters(). If the parser is validating, and it dis-
            covers a string of whitespace that is not defined in the DTD, it is required to call
            this method with the whitespace string. That means, for a validating parser, only
            validated strings are passed to characters().*/
    @Override
    public void ignorableWhitespace(char[] ch,int start,int length) throws SAXException {
        
    }
        /*This method is called for each PI. Recall that the con-
            tent of a PI is a name followed by some free-form text. The parser breaks up the
            content of the PI into two strings—all characters up to the first space are assumed
            to be the name—and passes each of the two pieces, otherwise intact, to the call-
            back method.*/
    @Override
    public void processingInstruction(String target,String data) throws SAXException {
        
    }

        /*This method may be called by some parsers when they
            encounter an entity that is not defined. Say, for example, a parser is not set to do
            validating and it encounters the use of an entity that is defined in a remote
            DTD. The parser will not know what to do with it, so it can choose to call the
            skippedEntity() method.*/
    @Override
    public void skippedEntity(String string) throws SAXException {
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.common.parsing.sax;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author client
 */
public class ConfigurationErrorHandler implements ErrorHandler{
    
    public ConfigurationErrorHandler(){
        System.out.println("ConfigurationErrorHandler");
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("ConfigurationErrorHandler warning");
        exception.printStackTrace();
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("ConfigurationErrorHandler error");
        exception.printStackTrace();
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("ConfigurationErrorHandler fatalError");
        exception.printStackTrace();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.cdi.producer;

import java.util.Date;
import javax.enterprise.inject.Produces;

/**
 *
 * @author client
 */
public class DateProducer {
    
    @Produces @WholeDate
    public Date getCurrentDate(){
        return new Date();
    }
}

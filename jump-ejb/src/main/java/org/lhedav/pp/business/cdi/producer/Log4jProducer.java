/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.cdi.producer;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author client
 */
public class Log4jProducer {
    @Produces @Log_(name = "Log4j")
  public Logger producer(InjectionPoint ip){
    return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
  }
}

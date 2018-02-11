/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.cdi.producer;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.LoggerFactory;
//https://stackoverflow.com/questions/20430780/cannot-get-slf4j-working-with-glassfish-4
/**
 *
 * @author client
 */
public class Slf4jProducer {
      @Produces @Log_(name = "Slf4j")
  public Logger producer(InjectionPoint ip){
    return (Logger) LoggerFactory.getLogger(ip.getMember().getDeclaringClass().getName());
  }
}

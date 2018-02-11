/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.cdi.interceptor;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.lhedav.pp.business.cdi.producer.Log_;

/**
 *
 * @author client
 */
@Interceptor
public class LoggingInterceptor {
    
@Inject @Log_(name = "Slf4j")
 private Logger logger;
 
    //init() method which is annotated with @AroundConstruct and
//will be invoked only when the constructor of the bean is called.
    
LoggingInterceptor(){
        
}
    
@AroundConstruct
private void init(InvocationContext ic) throws Exception {
    logger.fine("Entering constructor");
    try {
    ic.proceed();
    } finally {
    logger.fine("Exiting constructor");
    }
}


@AroundInvoke
public Object logMethod(InvocationContext ic) throws Exception {
    logger.entering(ic.getTarget().toString()+ "hugues", ic.getMethod().getName()+ "edakama");
    try {
    return ic.proceed();
    } finally {
    logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
    }
}

}

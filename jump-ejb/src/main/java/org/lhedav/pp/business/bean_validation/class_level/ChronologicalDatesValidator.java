/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.bean_validation.class_level;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.lhedav.pp.business.model.order.Order_;

/**
 *
 * @author client
 */
public class ChronologicalDatesValidator implements ConstraintValidator<ChronologicalDates, Order_>{

    @Override
    public void initialize(ChronologicalDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(Order_ anOrder, ConstraintValidatorContext context) {
        if(anOrder == null) return false;
        return ((anOrder.getCreation().getTime() < anOrder.getPayment().getTime()) &&
                (anOrder.getPayment().getTime() < anOrder.getShipping().getTime()));
        
    }    
}

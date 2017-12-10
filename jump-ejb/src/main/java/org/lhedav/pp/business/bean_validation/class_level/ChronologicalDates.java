/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.bean_validation.class_level;

import java.lang.annotation.ElementType;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author client
 */
@Constraint(validatedBy = ChronologicalDatesValidator.class)
//@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface ChronologicalDates {
   String message() default "{org.lhedav.pp.business.bean_validation.class_level.ChronologicalDates.message}";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}

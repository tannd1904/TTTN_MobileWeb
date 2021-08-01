package com.tannd.commercemanager.services;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author LyQuyDuong
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ServiceHelper {
	ServiceHelperConstant value() default ServiceHelperConstant.ALL;
}

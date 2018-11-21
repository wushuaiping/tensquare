package io.wooo.tensquare.spit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wushuaiping
 * @date: 2018/11/21 3:38 PM
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ThumupAnnotation {
}

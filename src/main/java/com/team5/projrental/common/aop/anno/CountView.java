package com.team5.projrental.common.aop.anno;

import com.team5.projrental.common.aop.category.CountCategory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CountView {
    CountCategory value();
}





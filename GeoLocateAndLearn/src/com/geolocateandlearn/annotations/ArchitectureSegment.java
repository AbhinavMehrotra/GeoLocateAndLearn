package com.geolocateandlearn.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(value = { ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
public @interface ArchitectureSegment {
	String segment();

	int sequence() default -2;
}

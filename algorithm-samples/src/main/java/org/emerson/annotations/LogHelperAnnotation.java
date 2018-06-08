/**
 * 
 */
package org.emerson.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(CLASS)
@Target(METHOD)
/**
 * @author memerson
 *
 */
public @interface LogHelperAnnotation {

}

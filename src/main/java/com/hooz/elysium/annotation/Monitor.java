package com.hooz.elysium.annotation;

import java.lang.annotation.*;

/**
 * Method invocation monitor
 *
 * @author Kidd Zhou
 * @date 2022-01-20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Monitor {
}

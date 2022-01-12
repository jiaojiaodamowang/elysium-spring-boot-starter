package com.hooz.elysium.annotation;

import com.hooz.elysium.config.ElysiumAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ElysiumAutoConfiguration.class)
public @interface EnableElysium {
}

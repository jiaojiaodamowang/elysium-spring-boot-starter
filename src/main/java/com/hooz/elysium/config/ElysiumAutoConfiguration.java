package com.hooz.elysium.config;

import com.hooz.elysium.properties.ElysiumProperties;
import com.hooz.elysium.service.ElysiumService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Elysium AutoConfiguration
 *
 * @author Kidd Zhou
 * @date 2022-01-01
 */
@Configuration
@EnableConfigurationProperties(ElysiumProperties.class)
@ConditionalOnProperty(prefix = "elysium", name = "enabled", havingValue = "true")
public class ElysiumAutoConfiguration {

    @Bean
    public ElysiumService elysiumService() {
        return new ElysiumService();
    }
}

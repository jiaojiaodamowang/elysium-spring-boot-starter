package com.hooz.elysium.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * elysium properties
 *
 * @author Kidd Zhou
 * @date 2022-01-01
 */
@ConfigurationProperties(prefix = "elysium")
public class ElysiumProperties {

    private boolean enabled;

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}

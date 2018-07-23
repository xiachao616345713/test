package com.manager.healthindicator.customer;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.cloud.client.discovery.health.DiscoveryHealthIndicator;
import org.springframework.stereotype.Component;

/**
 * health check handler
 *
 * TODO detail useless
 *
 * @author chao
 * @since 2018-07-23
 */
@Component
public class MyHealthIndicator implements DiscoveryHealthIndicator {

    @Override
    public String getName() {
        return "health-indicator";
    }

    @Override
    public Health health() {
        Runtime runtime = Runtime.getRuntime();
        Builder builder = Health.up();

        long freeMemory = runtime.freeMemory();
        builder.withDetail("freeMemory", freeMemory);
        long totalMemory = runtime.totalMemory();
        builder.withDetail("totalMemory", totalMemory);
        builder.withDetail("leftoverMemory", totalMemory - freeMemory);

        return builder.build();
    }
}

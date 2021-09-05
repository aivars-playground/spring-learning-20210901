package com.example.bugtracker.custom_metrics;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    /*
      depends on config
        endpoint:
            health:
               show-components: always
                show-details: always

        if enabled, details are here:
        http://localhost:8080/actuator/health/
     */

    @Override
    public Health health() {
        return Health.down().withDetail("CustomHealthIndicatorKEY", "some random text").build();
    }
}

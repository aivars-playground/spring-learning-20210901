package io.schultz.dustin.todoapp.customization;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomProjectHealth implements HealthIndicator {


    @Override
    public Health getHealth(boolean includeDetails) {
        //no need to override, uses "management.endpoint.health.show-details"
        System.out.println("----CustomProjectHealth-includeDetails:"+includeDetails);
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        System.out.println("----CustomProjectHealth-default");
        return Health
                .up()
                .withDetail("healthdetail","value")
                .build();
    }
}

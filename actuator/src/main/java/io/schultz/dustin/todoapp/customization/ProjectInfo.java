package io.schultz.dustin.todoapp.customization;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ProjectInfo implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder
                .withDetail("key1","value1")
                .withDetail("key2","value2");

        //do we need to add .build()?
    }
}

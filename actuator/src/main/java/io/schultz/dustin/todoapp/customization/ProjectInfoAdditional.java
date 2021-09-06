package io.schultz.dustin.todoapp.customization;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ProjectInfoAdditional implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder
                .withDetail("key1additional","value1")
                .withDetail("key2additional","value2");

        //do we need to add .build()?
    }
}

package com.example.aspect.service.aopintroductionfunction;

import com.example.aspect.model.MixinForPerson;
import com.example.aspect.model.MixinForPersonImpl;
import com.example.aspect.model.Person;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DeclarativePersonMixinAspect {
    @DeclareParents(
            value = "com.example.aspect.model.Person",
            defaultImpl = MixinForPersonImpl.class
    )
    private MixinForPerson person;
}

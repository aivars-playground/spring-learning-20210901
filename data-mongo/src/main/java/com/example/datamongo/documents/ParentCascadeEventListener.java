package com.example.datamongo.documents;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ParentCascadeEventListener extends AbstractMongoEventListener<Parent> {
    @Resource
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Parent> event) {
        System.out.println("----------------pre-save:"+event.getSource());
        if (event.getSource().getChild()!= null) {
            System.out.println("    \\---saving child object:"+event.getSource().getChild());
            mongoOperations.save(event.getSource().getChild());
        }
    }
}
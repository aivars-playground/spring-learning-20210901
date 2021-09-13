package com.example.datamongo.documents;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;

@Component
public class GenericCascadeSaveRefListener extends AbstractMongoEventListener<Object> {
    @Resource
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        var src = event.getSource();
        System.out.println("----------------pre-save-generic:"+src.getClass().getAnnotations().length);

        if(src.getClass().isAnnotationPresent(AnnotationToCascadeSaveRefs.class)) {
            System.out.println("----------------parsing class");
            for (Field f: src.getClass().getDeclaredFields()) {
                if (f.isAnnotationPresent(DBRef.class)) {
                    System.out.println("-------------------parsing field"+f.getName());
                    try{
                        f.setAccessible(true);
                        final Object item = f.get(src);
                        mongoOperations.save(item);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

    }
}

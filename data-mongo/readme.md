setup
=
start mongo image
```shell
docker-compose up -d
```

note
-
Both MongoRepository and Crud repository interfaces are working  
Query dsl is supported

Mongo Template
-
some functionality without creating repo
```java
 @Resource
    MongoTemplate mongoTemplate;

 mongoTemplate.save(manufacturer);
```

Cascading
=
Mongo does not cascade, use 
```
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
```
Note
-
Generic listener could be created with reflection...
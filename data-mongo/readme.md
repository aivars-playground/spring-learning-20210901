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
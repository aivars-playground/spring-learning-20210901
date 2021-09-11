Setup
=
```shell
docker create --name=postgesdemo -e POSTGRES_PASSWORD=secret -p 5432:5432 postgres:latest
docker start postgesdemo
```
or
```shell
docker-compose up
```

Best Practices
-
Do not directly extend JPA repositories
use, for example, proxy strategy pattern
```java
@Repository
public class MyExtendedFlightRepositoryProxyStrategy {

    @Resource
    FlightRepository flightRepository;
    
    void doSomething() {
        flightRepository.doSomethingElse();
    }
}
```
or mixin
```java
MyExtendedFlightRepositoryMixinStrategy
```

derived queries / query dsl
-
```java
class FlightJpaRepository{
        //Derived Query / Query DSL //compiles to JPQL -> SQL
        List<Flight> findByOrigin(String origin);
        int countAllByDestination();
        Flight findTopByDestination();
}
```

JPQL examples
-
```
AirplaneRepositoryWithJPQL
```


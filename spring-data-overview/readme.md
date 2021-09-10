Intro to Sprig Data
=
@SpringBootTest creates a full testing environment for the whole spring project
```java
@SpringBootTest
class SpringDataOverviewApplicationTests { }
```

Spring testing time could be reduced by using annotation i.e.  
```java
@DataJpaTest //loads only subset of Spring functionality, quicker testing...
class FlightTest { }
```

Note
=
Double brace initialisation creates an anonymous class derived from the specified class  
The following code broke the app...
```java
        Flight flight = new Flight() {{
            setOrigin("RIX");
            setOrigin("NCL");
            setScheduledAt(LocalDateTime.of(2020,1,1,1,1));
        }}
        entityManager.save(flight)
```
do not be lazy, use:
```java
        Flight flight = new Flight();
        flight.setOrigin("RIX");
        flight.setOrigin("NCL");
        flight.setScheduledAt(LocalDateTime.of(2020,1,1,1,1));
```

Derived Queries
=
CrudRepository can translate simple method names (in interface), to database proper queries
```java
public interface FlightRepository extends CrudRepository<Flight, Long> {
    
    //select flight from Flight flight where flight.origin=?
    List<Flight> findByOrigin(String origin);
    
    //select flight from Flight flight where flight.origin=? or flight.destination=?
    List<Flight> findByOriginOrDestination(String origin, String destination);

    //select flight from Flight flight where flight scheduled between ? and ?
    List<Flight> findByScheduledAtBetween(LocalDateTime from, LocalDateTime to);
}
```

Paginated repository
=
Paginated repository supports derived queries

!IMPORTANT! Use interface Pageable, not PageRequest!!!!!
```java
public interface PaginatedFlightRepository extends PagingAndSortingRepository<Flight, Long> {
    Page<Flight> findByOriginOrderByScheduledAtDesc(String origin, Pageable pageable);
}
```

Note!!!
=
All derived (and standard) van be added to a top level item - Repository
```java
public interface FlightRep extends Repository<Flight, Long> {
    <S extends Flight> S save(S entity); //use correct signature!!!!
    List<Flight> findAll();
    List<Flight> findByOrigin(String origin);
    Page<Flight> findByOriginOrderByScheduledAtDesc(String origin, Pageable pageable); //use correct signature!!!!
}
```

Derived Query capabilities
=
Derived Queries can be used to remove data as well
```java
void removeFlightByScheduledAt(LocalDateTime scheduledAt);
```
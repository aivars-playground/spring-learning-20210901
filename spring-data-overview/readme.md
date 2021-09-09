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


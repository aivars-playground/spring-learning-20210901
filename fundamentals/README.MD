add VM option to run the app with stub data
```
    -Dspring.profiles.active=stub
```

```java
@Repository("personRepository")
@Profile("stub")
public class StubPersonRepositoryImpl implements PersonRepository {
    //....
}
```

or for live (not implemented)

```
    -Dspring.profiles.active=live
```

```java
@Repository("personRepository")
@Profile("live")
public class LivePersonRepositoryImpl implements PersonRepository {
    //....
}
```
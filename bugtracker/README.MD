Actuator
===
Healthcheck, monitoring, etc...  
http://localhost:8080/actuator


GRAPHQL
===
http://localhost:8080/graphiql

count
```graphql
query {
    countApplications
}
```

find by id
```graphql
query {
    findAllApplications {
        id
        owner
    }
}

```



update
```graphql
mutation {
    updateApplicationOwner(newOwner: "you", id: 1) {
        id
        name
        owner
    }
}
```

error
```graphql
mutation {
  updateApplicationOwner(newOwner: "you", id: -1) {
    id
    name
    owner
  }
}
```

create
```graphql
mutation {
  newApplication(name: "a", owner: "b", description: "c") {
    id
    name
    owner
    description
  }
}
```

delete 
```graphql
mutation {
  deleteApplication(id: 1)
}
```
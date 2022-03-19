https://www.youtube.com/watch?v=UJesCn731G4

Testing
=

ProducerApplication
-
```shell
docker exec broker \
  kafka-topics \
  --bootstrap-server broker:9092 \
  --create \
  --topic customers
```

```shell
kafkacat -b localhost:9092 -t customers
```



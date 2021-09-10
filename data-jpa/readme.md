```shell
docker create --name=postgesdemo -e POSTGRES_PASSWORD=secret -p 5432:5432 postgres:latest
docker start postgesdemo
```
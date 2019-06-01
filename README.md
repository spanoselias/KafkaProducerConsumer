# ScalaProject

## Docker Configuration
docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 --env TOPICS=test --env GROUP_ID=test1 spotify/kafka

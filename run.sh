#! /bin/sh
echo Stop and Remove
docker-compose -f docker-compose.yml down
docker network rm pm-network
echo Build
 ./gradlew clean assemble
docker-compose -f docker-compose.yml build --force-rm
echo Run
docker network create --attachable --subnet 172.22.0.0/24 pm-network
docker-compose -f docker-compose.yml up -d
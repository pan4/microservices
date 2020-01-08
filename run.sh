#! /bin/sh
echo Stop and Remove
docker-compose -f docker-compose.yml down
echo Build
 ./gradlew clean assemble
docker-compose -f docker-compose.yml build --force-rm
echo Run
docker-compose -f docker-compose.yml up -d
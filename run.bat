echo Stop and Remove
docker-compose -f docker_compose.yml down
docker network rm pm-network
echo Build
call gradlew.bat clean assemble
echo Run
docker network create --attachable --subnet 172.22.0.0/24 pm-network
docker-compose -f docker_compose.yml up --build --force-recreate -d
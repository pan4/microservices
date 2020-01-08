echo Stop and Remove
docker-compose -f docker_compose.yml down
echo Build
call gradlew.bat clean assemble
echo Run
docker-compose -f docker_compose.yml up --build --force-recreate -d
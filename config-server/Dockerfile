FROM openjdk:12-jdk-alpine
EXPOSE 8888
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
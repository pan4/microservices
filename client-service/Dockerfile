FROM openjdk:12-jdk-alpine
EXPOSE 8380
EXPOSE 5305
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5305","-jar","/app.jar"]
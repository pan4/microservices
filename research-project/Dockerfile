FROM openjdk:12-jdk-alpine
EXPOSE 8480
EXPOSE 5405
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5405","-jar","/app.jar"]
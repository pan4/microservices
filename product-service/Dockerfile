FROM openjdk:12-jdk-alpine
EXPOSE 8280
EXPOSE 5205
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5205","-jar","/app.jar"]
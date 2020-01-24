FROM openjdk:12-jdk-alpine
EXPOSE 8580
EXPOSE 5505
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5505","-jar","/app.jar"]
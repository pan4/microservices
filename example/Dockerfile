FROM openjdk:12-jdk-alpine

LABEL maintainer="DevOps"
LABEL app_version="0.1"

ENV usr_name='Mr.Nobody'

COPY sample_project.jar /root/hello.jar

RUN echo "Nice to meet you, $usr_name"

ENTRYPOINT ["java","-jar","/root/hello.jar"]

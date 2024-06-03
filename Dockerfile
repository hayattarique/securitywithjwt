FROM ubuntu:latest
RUN mkdir -p /u01/apps
RUN apt update -y && apt install -y openjdk-17-jdk
COPY /target/restjwt-1.0.jar /u01/apps
ENTRYPOINT ["java","-jar","/u01/apps/restjwt-1.0.jar"]
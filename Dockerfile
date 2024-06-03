FROM ubuntu:latest
RUN mkdir -p /u01/apps
RUN apt update -y && apt install -y openjdk-17-jdk
RUN apt install -y curl
COPY /target/restjwt-1.0.jar /u01/apps
EXPOSE 8081
HEALTHCHECK --interval=30s --timeout=20s --start-period=5s --retries=3 CMD curl -f http://localhost:8081/management/health
ENTRYPOINT ["java","-jar","/u01/apps/restjwt-1.0.jar"]
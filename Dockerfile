FROM openjdk:8-alpine
ENV SERVICE_NAME=dr-gateway

MAINTAINER Kugatov Maxim <maximkugatov@gmail.com>

EXPOSE 8080

COPY /target/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar /app/lib/

ENTRYPOINT ["java", "-jar", "/app/lib/dr-gateway-0.0.1-SNAPSHOT.jar"]


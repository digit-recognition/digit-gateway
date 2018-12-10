FROM openjdk:8-alpine
ENV SERVICE_NAME=digit-gateway

MAINTAINER Kugatov Maxim <maximkugatov@gmail.com>

EXPOSE 8080

COPY /target/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar /app/lib/

RUN ls /app/lib

ENTRYPOINT ["java", "-jar", "/app/lib/digit-gateway-0.0.1-SNAPSHOT.jar"]


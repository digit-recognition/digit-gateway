FROM maven:3.5-jdk-8 as BUILD

ARG SERVICE_NAME=digit-gateway

COPY /src "/usr/src/${SERVICE_NAME}/src"
COPY pom.xml "/usr/src/${SERVICE_NAME}"

RUN mvn -f "/usr/src/${SERVICE_NAME}/pom.xml" clean package

FROM openjdk:8-alpine

MAINTAINER Kugatov Maxim <maximkugatov@gmail.com>

EXPOSE 8080

COPY --from=BUILD "/usr/src/$SERVICE_NAME/target/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar" /app/lib/

CMD ["java", "-jar", "/app/lib/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar"]


FROM openjdk:8-jre-alpine
MAINTAINER SE API Market

ARG JAR_FILE

ADD ${JAR_FILE} /opt/tlt-service/tomo-api-service.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/opt/tlt-service/tomo-api-service.jar"]
EXPOSE 8080

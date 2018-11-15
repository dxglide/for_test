FROM openjdk:8-jre-alpine
MAINTAINER SE API Market

ARG JAR_FILE

ADD ${JAR_FILE} /opt/tlt-service/tlt-service.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/opt/tlt-service/tlt-service.jar"]
EXPOSE 8080

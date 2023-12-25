FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY /target/financeflow_api-0.0.1-SNAPSHOT.jar financeflow_api.jar

EXPOSE 9091
ENTRYPOINT [ "java", "-jar", "financeflow_api.jar" ]
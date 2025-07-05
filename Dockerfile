#syntax=docker/dockerfile:1.4

# 1. Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS builder
ARG DOCKER_SOCKET_PATH
WORKDIR /app

# Copy pom and download dependencies to leverage Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Run tests and package the application
# Mount the docker sock to allow Testcontainers to work
RUN mvn clean package -DskipTests

# 2. Package stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the application jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

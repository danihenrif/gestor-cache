# First stage: Build the JAR using Maven
FROM maven:3.9.8-eclipse-temurin-17-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Second stage: Use a slim JRE image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file from the build stage
COPY --from=build /app/target/cache-manager-0.0.1-SNAPSHOT.jar /app/cache-manager.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/cache-manager.jar"]

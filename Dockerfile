# Step 1: Use a Maven image to build the app
FROM maven:3.8.1-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source files only when necessary
COPY src ./src

# Build the application (skip tests to speed up the process)
RUN mvn clean package -DskipTests

# Step 2: Use a lighter OpenJDK image to run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Install necessary utilities (like curl, git, vim)
RUN apt-get update && apt-get install -y curl git vim && apt-get clean

# Copy the built .jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the app runs on
#EXPOSE 8080

# Run the application (Spring Boot will handle the hot reload with DevTools)
CMD ["java", "-jar", "/app/app.jar"]
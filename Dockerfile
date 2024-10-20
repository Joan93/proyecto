# Use a base image with OpenJDK
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/my-spring-app-0.0.1-SNAPSHOT.jar my-spring-app-0.0.1-SNAPSHOT.jar

# Expose the application port (change if necessary)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "my-spring-app-0.0.1-SNAPSHOT.jar"]

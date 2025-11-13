# Use a base image with Java installed (e.g., OpenJDK)
FROM amazoncorretto:21-alpine-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar app.jar

# Expose the port your Spring Boot application listens on (default is 8080)
EXPOSE 8082

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
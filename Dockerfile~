# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from target folder to the container
COPY target/LoginSignUp-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (or whatever your app uses)
EXPOSE 1010

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]

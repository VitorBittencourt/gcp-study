FROM openjdk:12-jdk-alpine
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=default","-jar","app.jar"]
FROM openjdk:8-jdk-alpine
COPY . app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=default","-jar","/app.jar"]
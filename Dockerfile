FROM x86_64-alpine-jdk-12.0.1_12
COPY . app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=default","-jar","/app.jar"]
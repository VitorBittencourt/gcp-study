FROM alpine
COPY . app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=default","-jar","/app.jar"]
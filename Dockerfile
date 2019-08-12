FROM openjdk:12-jdk-alpine as build
WORKDIR /workspace/app

ENV APP_HOME=/root/dev/myapp/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
# download dependencies
RUN ./gradlew build -x :bootRepackage -x test --continue
COPY . .
RUN ./gradlew build

FROM openjdk:12-jdk-alpine
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=default","-jar","app.jar"]
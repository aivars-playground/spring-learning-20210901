#
# Build stage
#
FROM maven:3.8.2-openjdk-16-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:16-jdk
COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]

FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.war"]
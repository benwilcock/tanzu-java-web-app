FROM openjdk:11-ea-11-jdk-slim
RUN addgroup --system spring
RUN adduser --system spring 
RUN adduser spring spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# Spring app Dockerfile...
FROM openjdk:11-ea-11-jdk-slim
RUN addgroup --system spring
RUN adduser --system spring 
RUN adduser spring spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# Test the Dockerfile builds OK
docker build -t wilcockb200/tanzu-java-web-app-0.0.1-snapshot.jar .

# Test the dockerized app runs ok
docker run -p8080:8080 wilcockb200/tanzu-java-web-app-0.0.1-snapshot.jar

# Create a workload from a Dockerfile...
tanzu apps workload create tanzu-java-web-app --local-path . --source-image index.docker.io/wilcockb200/tanzu-java-web-app-source --type web --param dockerfile=./Dockerfile --tail

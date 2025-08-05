FROM openjdk:17-jdk-slim
WORKDIR /deutsche-springboot-demo-aug-2025-main
COPY target/deutsche-springboot-demo-0.0.1-SNAPSHOT.jar deutsche-springboot-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","deutsche-springboot-demo-0.0.1-SNAPSHOT.jar"]

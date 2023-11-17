FROM eclipse-temurin:17-alpine
EXPOSE 8089
COPY target/kaddem-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/kaddem-0.0.1-SNAPSHOT.jar"]

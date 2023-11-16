FROM eclipse-temurin:17-alpine
COPY target/kaddem-0.0.1-SNAPSHOT.jar .
EXPOSE 8089
ENTRYPOINT ["java","-jar","/kaddem-0.0.1-SNAPSHOT.jar"]
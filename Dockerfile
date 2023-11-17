FROM eclipse-temurin:17-alpine
COPY target/kaddem-1.0.jar .
EXPOSE 8089
ENTRYPOINT ["java","-jar","/kaddem-1.0.jar"]
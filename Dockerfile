FROM amazoncorretto:17-alpine
LABEL maintainer="Everton Rocha <imevertonrch@gmail.com>"
WORKDIR /app
COPY target/transacoes-api-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
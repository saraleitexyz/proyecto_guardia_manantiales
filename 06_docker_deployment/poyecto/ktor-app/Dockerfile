# Etapa de compilación
FROM gradle:8.7.0-jdk21-alpine AS build
WORKDIR /app
COPY . /app
RUN chmod +x gradlew
RUN ./gradlew installDist

# Etapa de ejecución
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
COPY --from=build /app/build/install/* /app
EXPOSE 8080
CMD /app/bin/*
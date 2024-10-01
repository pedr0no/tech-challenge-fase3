# Realiza a build do executável (.jar)
FROM maven:3.9.8-eclipse-temurin-17 AS build
RUN mkdir /opt/app
COPY . /opt/app
WORKDIR /opt/app
RUN mvn clean package
# Cria a imagem da aplicação copiando o (.jar) da build
FROM eclipse-temurin:17-jre-alpine
RUN mkdir /opt/app
COPY --from=build  /opt/app/target/tablereservation-0.0.1-SNAPSHOT.jar /opt/app/app.jar
WORKDIR /opt/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# --- Build stage ---
FROM maven:3.9.4-eclipse-temurin-17 AS builder
LABEL stage=builder

WORKDIR /workspace

# Kopioidaan riippuvuudet erikseen
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Rakennetaan projekti
ARG SKIP_TESTS=true
COPY src ./src
RUN mvn -B -DskipTests=${SKIP_TESTS} clean package

# --- Runtime stage ---
FROM eclipse-temurin:17-jre
LABEL maintainer="you@example.com"

WORKDIR /app

# Kopioidaan valmiiksi rakennettu .jar tiedosto edellisestä vaiheesta
COPY --from=builder /workspace/target/*.jar ./app.jar

# Portti Renderin ja Spring Bootin yhteensopivaksi
ENV PORT=8080
EXPOSE $PORT

# Java-ajon optimoinnit ja palvelimen portin asetus
ENV JAVA_OPTS="-Xms128m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]

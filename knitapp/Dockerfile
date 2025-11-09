FROM maven:3.9.4-eclipse-temurin-17 AS builder
LABEL stage=builder

WORKDIR /workspace

COPY pom.xml .
RUN mvn -B dependency:go-offline

ARG SKIP_TESTS=true
COPY src ./src
RUN mvn -B -DskipTests=${SKIP_TESTS} package

FROM eclipse-temurin:17-jre
LABEL maintainer="you@example.com"

WORKDIR /app

COPY --from=builder /workspace/target/*.jar ./app.jar

EXPOSE 8080
ENV PORT=8080
ENV JAVA_OPTS="-Xms128m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
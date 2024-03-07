FROM gradle:jdk17-jammy as builder
WORKDIR /build

COPY build.gradle.kts settings.gradle.kts /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

COPY . /build

ENTRYPOINT ["tail", "-f", "/dev/null"]

RUN gradle build -x test --parallel


FROM openjdk:17.0
WORKDIR /app

COPY --from=builder /build/build/libs/receipt_writer-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE="prod"

USER nobody
CMD ["java", "-jar", "receipt_writer-0.0.1-SNAPSHOT.jar"]
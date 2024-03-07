FROM gradle:jdk17-jammy as builder
WORKDIR /build

COPY . /build

RUN gradle build -x test --parallel


FROM openjdk:17.0
WORKDIR /app

COPY --from=builder /build/build/libs/receipt_writer-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ARG RECEIPT_USER
ARG RECEIPT_PASSWORD

ENV RECEIPT_USER=${RECEIPT_USER}
ENV RECEIPT_PASSWORD=${RECEIPT_PASSWORD}

ENV SPRING_PROFILES_ACTIVE="prod"

RUN useradd ms
USER ms
CMD ["java", "-jar", "receipt_writer-0.0.1-SNAPSHOT.jar"]
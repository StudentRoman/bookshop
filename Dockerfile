FROM maven:3.8.5-openjdk-17 as builder

WORKDIR /app

COPY . .

RUN mvn clean package

FROM openjdk:17-jdk-alpine AS prod

RUN apk update && apk upgrade && apk add bash

WORKDIR /app

COPY ./scripts/docker/wait-for-it.sh .

COPY --from=builder /app/target/*.jar /app/*.jar
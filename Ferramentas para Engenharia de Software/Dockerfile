FROM maven AS builder
WORKDIR /work/
COPY ./alpha_system_api/ .
RUN mvn package

FROM openjdk
WORKDIR /app/
COPY --from=builder /work/target/spring_boot_com_postgres.jar .
ENTRYPOINT ["java" , "-jar" , "spring_boot_com_postgres.jar"]
FROM openjdk:17-alpine

COPY build/libs/Banco-1.1.jar /app/transaccion.jar

WORKDIR /app

EXPOSE 8082

CMD ["java", "-jar", "transaccion.jar", "--server.port=8082"]
FROM gradle:8

WORKDIR /app
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/nmap2-0.0.1-SNAPSHOT.jar"]


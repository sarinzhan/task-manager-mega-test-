FROM eclipse-temurin:23-jdk
ARG DEPENDENCY=target/dependency
WORKDIR /app
COPY ${DEPENDENCY}/BOOT-INF/lib         /app/lib
COPY ${DEPENDENCY}/META-INF             /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes     /app
ENTRYPOINT ["java", "-cp", ".:/app/lib/*"]
CMD ["kg.kazbekov.megatesttask.MegaTestTaskApplication"]


# to build image of this project run following
# ./mvnw clean package && mkdir -p target/dependency && (cd target/dependency && jar -xf ../*.jar) && docker buildx build --build-arg DEPENDENCY=target/dependency -t sarinzhan/task-manager-app:latest .



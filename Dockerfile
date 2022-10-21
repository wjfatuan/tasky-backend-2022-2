FROM eclipse-temurin:17
RUN addgroup --system spring 
RUN useradd --system -g spring spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# Build using:
# docker build -t username/tasky-backend-docker .

# Run using:
# docker run -p 8080:8080 username/tasky-backend-docker

# Push using 
# docker push username/tasky-backend-docker
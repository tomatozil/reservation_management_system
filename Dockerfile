FROM openjdk:17-jdk
ARG JAR_FILE=core/build/libs/*.jar
COPY ${JAR_FILE} purchase.jar
ENTRYPOINT ["java","-jar","purchase.jar"]

#CMD sleep infinity
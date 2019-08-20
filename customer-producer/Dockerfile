### BUILD image
FROM maven:3-jdk-8 as builder
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#RUN mvn dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application
RUN mvn package -DskipTests

### RUN image
FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8081
COPY --from=builder /build/target/*.jar customer-producer.jar
#ADD target/customer-producer.jar customer-producer.jar
RUN sh -c 'touch /customer-producer.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /customer-producer.jar" ]

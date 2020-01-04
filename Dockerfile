FROM openjdk:8
VOLUME /tmp
EXPOSE 8003
ADD ./target/springboot-servicio-cuenta-ahorro-0.0.1-SNAPSHOT.jar savings-account.jar
ENTRYPOINT ["java","-jar","/savings-account.jar"]

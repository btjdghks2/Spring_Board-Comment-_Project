FROM openjdk:11
 VOLUME /tmp
 ADD /build/libs/*.jar app.jar
 ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app.jar"]
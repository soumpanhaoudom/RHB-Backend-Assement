FROM adoptopenjdk/openjdk11:jre

WORKDIR /application

COPY target/*.jar /application/app.jar

CMD [ "java", "-jar", "-Duser.timezone=UTC", "/application/app.jar"]
FROM adoptopenjdk/openjdk11:jre

RUN chmod -R 777 /opt

WORKDIR /application

COPY target/*.jar /application/app.jar

CMD [ "java", "-jar", "-Duser.timezone=UTC", "/application/app.jar"]
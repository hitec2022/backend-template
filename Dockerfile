FROM openjdk:11.0.16-jre

ARG APP_FILE

RUN echo ${APP_FILE}
RUN mkdir /logs
RUN chown 1000:1000 /logs
RUN mkdir /app
RUN chown 1000:1000 /app

USER 1000:1000
WORKDIR /app
COPY  --chown=1000:1000 ${APP_FILE} /app/app.jar

EXPOSE 8899
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
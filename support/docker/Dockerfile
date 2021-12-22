FROM openjdk:17.0.1-slim

USER root

EXPOSE 8080

ENV DIRPATH /qsa-storage/qsa-api
RUN mkdir -p $DIRPATH

COPY ./qsa-api-server/build/libs/qsa-api-final.jar $DIRPATH/qsa-api.jar
COPY ./support/docker/init.sh $DIRPATH/init.sh
RUN chmod 755 $DIRPATH/init.sh
WORKDIR $DIRPATH

HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "/qsa-storage/qsa-api/init.sh"]
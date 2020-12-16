FROM adoptopenjdk:11-jre-openj9

USER root

RUN mkdir /opt/app
COPY build/libs/authapp*.jar /opt/app

EXPOSE 8080

COPY docker/docker-entrypoint.sh /root/
RUN chmod u+x /root/docker-entrypoint.sh

ENTRYPOINT [ "/root/docker-entrypoint.sh" ]
#ENTRYPOINT ["tail", "-f", "/dev/null"]

#!/bin/sh

cd /opt/app || exit

JAR=$(ls authapp*.jar)

java -jar "$JAR" --spring.profiles.active=prod

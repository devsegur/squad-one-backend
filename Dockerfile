FROM openjdk:8-jdk-slim
MAINTAINER evertonsegur5@gmail.com
RUN mkdir /opt/logsone
COPY . /opt/logsone/
WORKDIR /opt/logsone
RUN bash /opt/logsone/gradlew build
CMD bash /opt/logsone/gradlew bootRun

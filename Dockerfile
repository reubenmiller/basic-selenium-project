FROM maven:3.6.0-jdk-8-alpine
COPY . /root/app
COPY ./settings.xml /root/.m2/
WORKDIR /root/app
ENTRYPOINT [ "mvn" ]
CMD [ "clean", "verify", "-Pbrowser-chrome" ]

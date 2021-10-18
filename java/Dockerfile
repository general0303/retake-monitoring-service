FROM maven:3.8.2-jdk-11


# cd /opt/app
WORKDIR /opt/app

COPY ./src ./src
COPY ./pom.xml ./
RUN mvn clean install

CMD mvn spring-boot:run
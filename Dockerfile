FROM maven:3.5.4-jdk-8
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
VOLUME /tmp
ADD target/betterday-0.0.1-SNAPSHOT.jar freelee/betterday.jar
ENTRYPOINT ["java","-Djava.securityegd=file:/dev/./urandom","-jar","/freelee/betterday.jar","--spring.profiles.active=docker"]
EXPOSE 8080

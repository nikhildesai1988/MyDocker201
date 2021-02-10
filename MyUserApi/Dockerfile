FROM openjdk:8-jdk-alpine
MAINTAINER NikhilDesai "nikhildesainy@gmail.com"
EXPOSE 8080
WORKDIR  /home/nikhildesaisd/MyDocker201/MyUserApi/target/
COPY  MyUserApi-0.0.1-SNAPSHOT.war  app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

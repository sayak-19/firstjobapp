From openjdk:17-alpine
copy ./target/firstjobapp-0.0.1-SNAPSHOT.jar firstjobapp-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","firstjobapp-0.0.1-SNAPSHOT.jar"]
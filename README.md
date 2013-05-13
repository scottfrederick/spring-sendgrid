spring-sendgrid
===============

A sample application for using Spring and SendGrid on Cloud Foundry.

To test the application running on Cloud Foundry:

* edit the file `src/main/resources/application.properties` and set your SendGrid host (user name and password will be read from the Cloud Foundry environment)
* `./gradlew assemble`
* `cf push`
* browse to `http://spring-sendgrid.<cloud-foundry-domain>/`

To run the application locally: 

* edit the file `src/main/resources/application.properties` and set your SendGrid host, user name, and password
* `./gradlew tomcatRun`
* browse to `http://localhost:8080/spring-sendgrid`

The first time `./gradlew` runs, it will take a while to download the build tool. Subsequent runs will be much faster. 

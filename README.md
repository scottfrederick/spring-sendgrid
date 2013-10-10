spring-sendgrid
===============

A sample application for using Spring Framework and [SendGrid](http://www.sendgrid.com) on
[Cloud Foundry](http://www.cloudfoundry.com).

## Running the application on Cloud Foundry

After installing in the 'cf' [command-line interface for Cloud Foundry](http://docs.cloudfoundry.com/docs/using/managing-apps/cf/),
targeting a Cloud Foundry instance, and logging in, the application can be pushed using these commands:

~~~
$ ./gradlew assemble
$ cf push --path=build/libs/spring-sendgrid.war
~~~

If prompted to create a service for your app, select `y` and choose the SendGrid service. This will provision an
account on SendGrid and bind it to your app, which stores credentials for the account in the VCAP_SERVICES environment
variable. This application will read those credentials and use them when it sends e-mails.

The application will be pushed using settings in the provided manifest.yml file. The settings include some random 
characters in the host to make sure the URL for the app is unique in the Cloud Foundry environment. Once the app is 
uploaded and finishes staging, run the following command to see the URL that was assigned:

~~~
$ cf app
Using manifest file manifest.yml

spring-sendgrid: running
  usage: 512M × 1 instance
  urls: spring-sendgrid-7acab.cfapps.io
  services: sendgrid
~~~

Using the provided URL in the urls field displayed, you can browse to the running application.

You can see what environment variables are available to the application (including the VCAP_ENVIRONMENT variable) by
navigating to `http://<app-url>/env`.

You can override the SendGrid credentials in the environment by configuring them in the file
`src/main/resources/application.properties`.

## Running the application locally

When running locally, the SendGrid credentials must be set manually in the file
`src/main/resources/application.properties`.

After editing the file and adding the credentials, run this command to start the app:

~~~
./gradlew tomcatRun
~~~

Once the app is running you can browse to the URL `http://localhost:8080/spring-sendgrid`.

### A note on `./gradlew'

The first time `./gradlew` runs, it will take a while to download the build tool. Subsequent runs will be much faster. 

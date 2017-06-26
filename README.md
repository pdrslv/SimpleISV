Simple ISV
==========

Very simple Spring Boot application to exercise AppDirect integration APIs. 
It doesn't do anything fancy... really 

Configuration
-------------

-  Make sure to configure the OAuth key and secret in the application.yml file.

Running the project
--------------------

Package the project run:

> mvnw clean package

Deploy the project to Cloud Foundry

> cf push {your-app-name-on-cf} -p {PATH_TO_JAR}

Configure Your Product Integration
> url: https://{your-app}.cfapps.io/integration/processEvent?eventUrl={eventUrl}

The following Integration can be configured with the above url:
- Subscription Create Notification URL
- Subscription Change Notification URL
- Subscription Cancel Notification URL 
- Subscription Status Notification URL 
- User Assignment Notification URL 
- User Unassignment Notification URL 
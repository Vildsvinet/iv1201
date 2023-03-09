# iv1201
Project work for KTH course IV1201, recruitment app for an amusement park.

## About the application
* The application is developed in Java, based on Spring Boot's version 3.0.2
* It utilizes server-side rendering with ThymeLeaf, and JPA and PostgreSQL for database access.
* Dependencies are included in the pom.xml file
* Maven is the choice of build-tool.
* The source code is available at https://github.com/Vildsvinet/iv1201.
* The application is live at https://rocky-brushlands.herokuapp.com/


## Supported IDE:s
This application has been developed using IntelliJ 2022.3 Ultimate Edition.
The Ultimate Edition is free of charge for students registered at a valid institution.
Use of other IDE:s might affect organization of project files, with unknown consequences.

## Supported Browsers
The application is supported in the following browsers:
* Google Chrome
* Firefox
* Microsoft Edge
* Safari

## Download Project

Once you have been added as a collaborator to the project, please clone the project to a local folder on your machine.

You can follow instructions here:
https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository

Now you can commit new code to the remote repo.

## How to run with IntelliJ on your local machine:
Please make sure you have postgreSQL 14 installed and running.

Set environment variables on your local machine.
POSTGRES_TCP_ADDR = localhost
POSTGRES_TCP_PORT = 5432
SPRING_DATASOURCE_PASSWORD = "your password to postgres"

Run the SQL script provided in the folder main/scripts in eg pgAdmin or a console.

* Download the project
* Reload Maven project
* Build
* Run

The application can then be tested on http://localhost:8080/

## Future deployment to Heroku
For deployments to Heroku, you must have an Heroku account. Please register an account at Heroku first. Then you will be setup as a collaborator for the application.

Future deployments to Heroku is dependent on installing the Heroku CLI on your local machine.
Please follow the link on https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku to install the Heroku CLI on your local machine.

Once you have been setup as a collaborator for the application registered at Heroku, please follow the below steps for future deployments to Heroku.

* As the deployment to Heroku is dependent on the Github repo your are working on, it is assumed your have followed the previous steps.
* In the folder where the local repo is located, log in to Heroku using, heroku login
* Deploy the main branch of the Github remote repo using: git push heroku main

Heroku automatically detects the application as a Maven/Java app due to the presence of a pom.xml file.
The system.properties file in the project determines the runtime version of Java and is currently set as Java 19.


## Making changes to the database at Heroku
You can interact with the Mini Postgres database in several different ways.
For read-only queries, you can log in to heroku.com and use Dataclips.

For write permission, please use a tool such as pgAdmin, where you can connect to the database running at Heroku, bu using connection details
found once logged in to Heroku.

# Mood Tracker Backend

This API is responsable for saving and listing Moods 

## Project setup

```
Import the project as a Maven project into Eclipse using the pom.xml file to import
Run mvn install to execute all JUnits, compile and generate the jar artifact on ./target directory
```

### Database setup

```
The database used is MariaDB
database: fuel50
port: 9041
username: root
password: mariadb123

You need to create the database fuel50 before running the project
The table will be created when you start the application

In case you want to use another port, username or password, just go on src/main/resources
and open the file application.yml and do the changes
```

## Running the project

```
Running the project via command line
On the project base directory, run java -jar ./target/mood-tracker-backend-0.0.1-SNAPSHOT.jar

Running the project via Eclipse IDE
After importing the project, wait until all dependencies have been downloaded
Then, go on src/main/java, open the package com.fuel50.mood.tracker and run the class MoodTrackerApplication as a Java Application

When the project is running, open the web browser on http://localhost:8081/mood-tracker/api/v1/ping to verify that everything is okay
You should see "API is working" in the browser
```


### API Documentation - Swagger UI

```
To download the yaml file
http://localhost:8081/mood-tracker/api-docs.yaml


swagger-ui
http://localhost:8081/mood-tracker/swagger-ui.html
```
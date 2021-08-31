# nutcache-challenge-KayroPereira
Challenge for create application management of people

### Nutcache Challenge - Delivery

The project consists of implementing an API for managing people. It was also asked to develop a desktop and web application to consume the API.

### First step:

Based on the documentation requests, the necessary requirements for the construction of the database were analyzed.

### Second stage:
With all the requirements raised, the next step was to build the database. Conceptual, logical and physical models were implemented, with all attributes and relationships. The tables implemented according to the proposal are: Person and Gender. The chosen database was postgresql. The template files are in GitHub's nutcache-challenge-KayroPereira / db / folder.

### Third step:
Then API development started. This API was developed in Java using the Spring Boot framework. The function of this API is to interface between the applications and the database. The resources that can be consumed are: Table Genre - GET all. Person Table - GET all, GET by ID, POST, PUT, PATCH and DELETE. The API development files are in the nutcache-challenge-KayroPereira / challenge_people_management / folder on GitHub.

### Fourth step:
This step consists of developing the desktop application. This application was developed in Java using the Swing API used to implement the graphical interface. This interface is responsible for consuming the API from the previous step and making the API functions available to the end user so that they can be easily used by anyone. Desktop application development files are in GitHub's nutcache-challenge-KayroPereira / desktopApplication / folder.

### Fifth step:
Finally, a web application was developed. This application has the same features and purposes as the desktop application, with the difference of being able to perform interactions with API remotely over the internet. Desktop app development files are in GitHub's nutcache-challenge-KayroPereira / web-app / folder.

### How to use?
Two ways to use the system were made available:

#### Manual mode:
To use the system in this format, you must download the project from GitHub at https://github.com/KayroPereira/nutcache-challenge-KayroPereira, unzip the file and follow the steps below:

Create a database on postgres with the name dbNutcacheChallenge, user postgres and password admin. Use the PhysicalDataModel.sql file to create all database configurations. Resources found in the DB folder.

Run the challenge_people_management-0.0.1-SNAPSHOT.jar file to initialize the API. Resources found in the ApplicationLocal folder.

To use the desktop application, you must run the desktopApplication.jar file. Resources found in the ApplicationLocal folder.

To use the web app, you must open the web app folder in VsCode or similar. In the terminal, execute the commands [npm install] and [ng serve] in the project folder (the Angular CLI must be installed on the computer). Use your preferred browser and go to the localhost address: 4200. Resources found in the web application folder.

#### Online mode:
To use the system in this format, you must download the GitHub project from https://github.com/KayroPereira/nutcache-challenge-KayroPereira, unzip the file and perform the steps below:
To use the desktop application, you must run the desktopApplication.jar file. Resources found in the ApplicationRemote folder.

To use the web app, go to http://nutcache-frontend-kayropereira.herokuapp.com/.

### Pendency
The implementation of unit tests in all phases of the project was pending. Notifications requested in web application operations were also pending.

### Improvements
#### API
Format / customize the success and error responses of requests.

#### Web application

* Implement the necessary validations

* improve the layout

### Resources used

#### IDE’s

* Visual Studio Code

* Eclipse

* IntelliJ IDEA

#### SGBD

* PGAdmin 4

#### Editor .md

* Typora

#### Frameworks

* Spring Boot

* Angular

#### API’s

* Swing

#### Dependency management

* Maven

#### Main libraries

* Lombok

* Jcalendar

* Mapstruct

* Angular Material

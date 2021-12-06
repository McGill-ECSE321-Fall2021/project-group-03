# project-group-03
project-group-03 created by GitHub Classroom

Welcome to group 3's github repository!

Allow us to introduce ourselves! 
Our team members are: Adam Geenen, Tara Ginsberg, Abraham Somech, Liam Serour and Samuel-Ryan Vasserman.
We are all second year students at McGill and are very happy to finally be studying in person!
We actually worked in a group together last semester when it was all virtual and are looking forward to 
working on this project together in person. 

The main scope of this project is to, as a team, create a library management system with the following functionality. The
library management system will be made up of users, librarians, head-librarians as well as different titles (books, music albums,
newspapers and movies). In addition, the library will have study rooms and party rooms that can be reserved for events. Our library
management system will allow for users to create accounts, use the library systems and manage all the different schedules (library, staff 
and room schedules). Finally, the most important parts of the system should be accesible via a web frontend and a mobile android application. 


| Team member (name)     | Role                  |  Effort for Deliv 1 (hours) |  Effort for Deliv 2 (hours) |  Effort for Deliv 3 (hours) | Effort for Deliv 4
| ---------------------- | --------------------- | ----------------------------| ----------------------------| ----------------------------|----------------------
| Adam Geenen            | Communications Manager| 30                          | 70                          | 60                          | 35
| Tara Ginsberg          | Project Manager       | 30                          | 70                          | 60                          | 35
| Abraham Somech         | Testing Architect     | 30                          | 70                          | 60                          | 35
| Liam Serour            | Project Designer      | 30                          | 70                          | 60                          | 35
| Samuel-Ryan Vasserman  | Software Architect    | 30                          | 70                          | 60                          | 35
 

# Roles and Responsibilities

Liam - Project Designer
Responsible for checking in with the original specifications, feedback from deliverable 1 during the development and testing phase. Additionally, look over the model and ensuring the appropriate changes are made to fit criteria for this deliverable. Finally, will assign tasks in the project Kan ban and create meaningful milestones and user stories.

Sam - Software Architect
Making sure the software and system architectures are in synchronization and resolving software technical problems. Sam will also oversee that the different code uploaded by team member is synched properly through Github and that the code is working smoothly.

Avi - Software Quality Assurance and Testing Architect
In charge of quality assurance of the software and service methods created. Will lead the team in terms of which tests need to be created, static analysis of the code and will help debug any issues with related to the database and the project build.

Adam - Support and Communications Manager
Responsible for assisting team member with linking the software to the documentation and ensuring there is consistency throughout the entire project. Will communicate with members working on designing the models and the testing team to ensure the proper test cases are covered. Also, will help document progress made between project manager and software team and resolve technical GitHub issues and the GitHub CI actions.

Tara - Project Manager and Documentation Leader
Ensure that all meetings, design decisions and other major events of the design process are documented in the README as well as the project wiki. Organize meeting for the team and follow up with members on the roles and tasks accomplished thus far.


[Click this link to see our Project Report](https://github.com/McGill-ECSE321-Fall2021/project-group-03/wiki/Project-Report)

## Deploying the Front End
In order to deploy the front-end, run npm run dev. This will deploy a session of the front end to the designated host configured in the config files of the project. By default it will be deployed on a local server with port 8087 (differs from port 8080 which is used for back-end calls). Note that on occasion, when being redirected to a page it can show a blank screen. Refreshing the page fixes this issue.

## Deploying the Back End
In order to run our application, follow these steps:

run the libraryManagementApplication.java file as a spring boot application. This can be found in the ca.mcgill.ecse321.librarymanagement package.
Once running, sign into postman. This application can be accessed using the server port 8080.

## Deploying the Android Application
In order to deploy the Android application, open the folder found in the Android branch in Android studio and run the AVD emulator. The Android emulator is connected to the Heroku url.


## A Guideline to Using Our Website
When the application is first launched, the website is created with only a head librarian account. The credentials for this account are username: "headLibrarian", password: "head". As specified by the client, the library has 3 rooms that can be booked for reservations. To sign in, navigate to the "Login" button on the top right of the window. From there, you can sign in as the head librarian or create a new account and log in from that accounts perspective. Note: librarians cannot create their own account, the head librarian must do so from the management  tab where they can hire a librarian.

All librarians have an extra tab in the menu bar named "library tools" which is where they can access their special privileges such as creating titles, checking out titles for clients and viewing their staff schedules. As the head librarian you may also create a time slots for any librarian that was hired.

As a client, you may browse available titles, press a title to view information about it or reserve it. You may also reserve a room by pressing on a room and then pressing on an available time slot. To view title and room reservations, press the reservations tab which is only available to clients.

# 18653-Team8
# AskMe
* [ATAM](https://drive.google.com/open?id=1DBdHQdeko5G_63ZAR2x4Ot52fxxOgNLxWEsyBYzU8Ng)
* [API Specification](https://drive.google.com/open?id=1pTtn7knjzpbAbVUFs--CvBJJm4JUCDqP)
* [Iteration 1 recording](https://drive.google.com/open?id=1SQxIeoqaEXmk3kdzDr2isTb-6oJv4YQk)
# Functional requirements

❖	Users from across countries should be able to use this forum for discussion purposes.

❖	The forum mandates user authentication before accessing any listed functionality.

  ➢	Login : 
      Allow users to log in using username and password.
      
  ➢	Registration : 
    ■	Users will enter the desired username and password along with optional fields - first name and last name. 
    ■	Specify if admin or not. 
    ■	The system should notify the user if the username is available or not. 
    ■	All the registering users will by default follow a system channel NewsUpdates.
    
❖	The users can create/edit/delete/subscribe to a channel (Future sprints)
  
❖	The users can create/edit/delete a post, comment or vote another thread or rate the same (depending upon his role).
  
  ➢	View Post
    ■	Once the user clicks on the post (from the feed/channel page), the user should see the post details like - author, post creation    date, channel name, post content etc.
    ■	View comments on the posts by users and add more comments.
  ➢	Post Creation
    Allow the user to create a post in the default channel created. 
    For post creation, the user will provide 
    ■	the channel where the post is being posted
    ■	Content of the text
    ■	Hashtags - If the hashtag is not present in the system, the system should create the hashtag and store it
    ■	Profanity filtering - yes/no
    
❖	Feed should be visible to all users - logged in/anonymous. This will show latest post and top posts (as filtered by the algorithm/specified by the moderators)

  ➢	Home screen : 
  On the landing screen, the user should see the posts from the channel the user is subscribed to. For this sprint, there is only one channel. 
  
❖	Any logged in user should be able to view his profile details and site related activities.

  ➢	View Profile
    ■	Allow the user to access the user’s profile. 
    ■	The user should be able to see - user’s name, last name, active since (date created), posts a user has posted, channels user is   following.
    ■	Future sprints 
      ●	View owned channels, interests, followers etc.
      ●	Admin can allocate roles and change user status.
      ●	Separate GUI exists for administrators and moderators
      ●	Moderators have the additional capabilities of deleting (post/comment), marking (useful/on fire) any post in the allotted topic. 

❖	Users should have the ability to search for keywords on the website

  ➢	Search
      The user should be able to search through the posts and hashtags.
      
# Design patterns
1.	Observer
2.	Memento
3.	Facade
4.	Mediator
5.	Command Pattern
6.	Filter
7.	Flyweight and Factory
8.	Builder
9.	Template
10.	Singleton
11.	Factory

Download and Install required softwares
1. Java
  Download and install JDK 1.8 for the OS type
  https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
2. Intellij IDEA
  Download and install Intellij IDEA Ultimate Edition 2019.3 and above
  https://www.jetbrains.com/idea/download/#section=windows
3. Node.js
  Download and install NodeJS appropriately for the OS
  https://nodejs.org/en/download/
4. NPM
  Download and install NPM appropriately for the OS
  https://www.npmjs.com/get-npm
5. Plugins & SDK 
  Open Intellij IDEA, File->Settings and ensure that the following plugins are installed:
  Scala, SBT, Play, Ebean
  Ensure that sdk is appropriately set to 1.8, File->Project Structure


Download/Setup & StartUp Application
1. The website codebase is available at the following github location:
  https://github.com/jiazhang-class/18653-Team8
2. The codebase has two separate folders (projects) for frontend and backend.
3. FrontEnd project set-up/startup
    Open Intellij IDEA , File-> Open the frontend->ask-me folder
    Wait a few moments for the project to load and indexing to complete


Once done, verify the project structure:

Navigate to ask-me folder on terminal (in INTELLIJ IDEA) and execute the following command to download and install the required project modules
npm install -g @angular/cli

Once done, start the server by executing the following command from the same location:
ng serve -o

This should take a few moments to compile and start the application. 
The application startup can be monitored from the Run window:

The home page of the application should load automatically in the default browser on the url :http://localhost:4220

Do not start navigating the application yet because we still have the backend server to setup & start

Server (Backend) project setup/start

Open Intellij IDEA , File-> Open folder server

This will take about a minute to load the project, index files, download/sync sbt dependencies etc. While this happens go ahead and create the database schema




		
Create a database/schema in mysql
Can use mysql shell for database creation
CREATE DATABASE sad
Or can execute the command from MySql workbench command line tool

Or create a new view from MySql workbench


The project in Intellij should now be available for edit and configurations.

Open file conf/application.conf and update the database connection details and credentials to match the local environment

Goto file conf/routes and update the value of the following parameter to reflect the number of posts one wishes to be displayed on the feed page.

The application can be started in either of the following two ways:
SBT SHELL 
Open SBT Shell and wait for a few moments to allow initialization

Run clean

Once done, execute the following command
run <desired-port> (recommended : 9000)

RUN as PLAY APPLICATION
GOTO Run -> Edit Configurations
Select + -> PLAY APPLICATION
Edit the following fields with the mentioned values

Click Apply
Now, run the application by clicking the following button
                                    
The application startup can be monitored from the Run windows :

Somewhere during startup the default web browser will open up with the following page, prompting the user to “Execute Evolution”
Click on the button to allow DB Evolution to execute, this might take a few moments.
Once done, verify the database to ensure that the following tables have been created.
Verify the tables

Verify the data

Verify the ER Diagram

Once the DB evolution completes, the backend application will start on the browser with the following default page.
http://localhost:9000

Navigate back to the front end application on the url
http://localhost:4220
                       
Application Demo/Execution steps
Start navigating and exploring as suggested in this Demo video.
AskMe Demo
Steps for application navigation
Register as a regular/admin user. Use the Save/Undo/Reset buttons to save the form information, undo and reset to an empty form

Login with the newly created user or one of the existing users

User lands on the feed page where he can see the the top most feed of the website.

User navigates to the channel, hashtag, post or user by clicking on the below highlighted links.

Channel Details page.

Post Details page

User chooses to add a comment on the post, which shows up immediately on submit.

Profile details page.

User clicks on “Payment amount” to trigger payment, pop up shows up on success.

User creates a new post in any channel that he is subscribed to.

User creates a new channel

User searches for a post or a hashtag

User views the list of channels and navigates to view further details.

On the channels list page, the user can select a channel and subscribe/delete (if he is the owner) a channel.


The navigation bar has buttons to take users to Home page, Search, New Channel, New Post, Channel List, Profile & Logout.

 

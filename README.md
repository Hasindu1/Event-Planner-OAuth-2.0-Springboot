# Event-Planner-OAuth-2.0-Springboot

## Introduction
An educational puropse web application which was mainly developed to illustrate the OAuth2.0 framework
usage.The  web application is developed to provide the event planning services to users using Google Calendar and Google Drive 
Services.Application uses OAuth2.0 framework for authentication and  grant required autorization permissions for resources.

## Technology Stack

![Sprigboot](https://img.shields.io/badge/Framework-SpringMVC-blue)
![OAuth2.0](https://img.shields.io/badge/Framework-OAuth2.0-blue)
![SpringSecurity](https://img.shields.io/badge/Framework-SpringSecurity-blue)
![JQuery](https://img.shields.io/badge/Library-JQuery-blue) 

![Java](https://img.shields.io/badge/Language-Java-red)
![javaScript](https://img.shields.io/badge/Language-javaScript-red) 
![HTML5](https://img.shields.io/badge/Language-HTML5-red) 
![CSS](https://img.shields.io/badge/Language-CSS-red) 
![Ajax](https://img.shields.io/badge/Language-Ajax-red) 

## Features
* Quick user login with Google Login(User doesn't need to signup for the application by creating a seperate account).
* Application endpoints have been secured using Spring Security(User cannot bypass and visit for other pages before proceed with a 
* sucessful login)
* User can add new events to Google Calendar and view the added events along with event details.
* User can upload the event related files(ex: flyer) to Google Drive.
* User's basic profile details like profile picture,email and name will be displayed on the top navigation bar by obtaining from the Google.


## OAuth2.0 flow(Grant type: Authorization code)

 <p align="left">
  <img src="../master/ui-images/oauth2-flow.PNG"/>
 </p>

## Main User Interfaces
 ### Home 
 
 <p align="left">
  <img src="../master/ui-images/home.PNG"/>
 </p>
 
 ### Validations
 
 <p align="left">
  <img src="../master/ui-images/form-validation.PNG"/>
 </p>
 
  ### Login
 
 <p align="left">
  <img src="../master/ui-images/login.PNG"/>
 </p>
 
 ### Google Login
 
 <p align="left">
  <img src="../master/ui-images/google-login-one.PNG"/>
 </p>
 
  <p align="left">
  <img src="../master/ui-images/google-login-two.PNG"/>
 </p>
 

## Build And Run the Application

Clone the project using following URL : https://github.com/Hasindu1/OAuth-2.0-Framework.git

import existing project as maven project

Build the project using mvn clean install command

Run using mvn spring-boot:run command

## OR

Clone the project using following URL : https://github.com/Hasindu1/OAuth-2.0-Framework.git

open Command Prompt

Navigate to the project root folder

Build the project using mvn clean install command

Run using mvn spring-boot:run command

The Web application is accessible via http://localhost:8081.

You can also follow my GitHub Profile to stay updated about my latest projects: [![GitHub Follow](https://img.shields.io/badge/Connect-Hasindu1-blue.svg?logo=Github&longCache=true&style=social&label=Follow)](https://github.com/Hasindu1)

If you liked the repo then please support it by giving it a star ⭐!



 ## License
[![MIT](https://img.shields.io/cocoapods/l/AFNetworking.svg?style=style&label=License&maxAge=2592000)](../master/LICENSE)


Copyright (c) 2021-present,Hasindu Dahanayake


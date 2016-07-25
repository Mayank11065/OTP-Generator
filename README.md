# otp_generator
Webapp to manage user database and generate OTP using Twilio

Installation steps:
*	Copy otp_generator folder to your desired location. This is the tomcat web server where your web app is deployed.
*	Before starting the server, execute setup.sql file to create mysql tables to store the data.
*	The mysql username and password are assumed to be root/root.
**	If you wish to modify that, edit the constants.java file (in the servlets folder) and compile the java-servlet project and replace the .class file in <tomcat-home>/webapp/Web-Inf/Classes.
* Start the server by running catalina.bat (or in command line type : <tomcat_home>/catalina.bat run)
**	The tomcat is configured to listen at port 8090. Webapp name is message. So, use http://localhost:8090/message/ to access the otp generator web app.

Limitations

I have used the Twilio API to send messages. Unfortunately, it gives SSL certificate issue as the API runs on https. I have also tried sending direct ajax request, but that gives CORS issue. (Ajax code is commented out in main.js)
I am trying to resolve this issue ASAP.


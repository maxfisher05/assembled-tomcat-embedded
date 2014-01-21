assembled-tomcat-embedded
=========================

Example maven project that uses embedded tomcat 8 and appassembler plugin to create an easy to run distribution.


Project Goals
=========================
The goal was to combine embedded tomcat, and the app assembler plugin to create a single distribution that could be run easily on any machine with little setup.

I ran into a number of issues when doing this, but I was able to get around them by combining different maven plugins together.

Problems solved:

1. appassembler seems to want a self contained jar, but actually wanted a self-contained war file with webapp resources included as well, uses maven-war-plugin and maven-assembly-plugin to package war resources with appassembler scripts.

2. appassembler creates strange directory structure, use maven-assembly-plugin to move directories

3. got tired of having to chmod the bin directory after each build in order to make the files executable, fixed in maven-assembly-plugin

4. create empty logs directory as that seems to be required for the jsw deamon

Requirements
=========================
-Maven

-Tomcat 8 requires Java 7.  Must have Java 7 installed and JAVA_HOME environment variable set.

Tested on OSX Mavericks with Java version 1.7.0_51.


Usage
=========================
//run build

$ mvn clean package

$ cd target/example-server-assembly/example-server/bin/

//start up program type server

$ ./example-server

//verify at:
http://localhost:8080/hello

//can also run jsw deamon

//start in background

$ ./example-server-deamon start

//check status

$ ./example-server-deamon status

//restart deamon

$ ./example-server-deamon restart

//stop deamon

$ ./example-server-deamon stop

//start in foreground

$ ./example-server-deamon console

//To distribute to your servers use target/example-server-assembly.zip

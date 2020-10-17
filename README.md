# Internet-Shop

## Purpose

Small project based on N-Tier architecture pattern which can be used for commercial uses. In this project Tomcat is used as a server for executing requests and MySQL as data storage. Accessibility model here is RBAC.

Technologies used: 
* Servlets 
* JDBC
* MySQL
* JSP
* JSTL
* Tomcat
* Log4j
* Reflection API
* SHA-512 hashing

## How-To-Run

### Install list below
* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Tomcat](https://tomcat.apache.org/download-90.cgi)
* [PostgreSQL 12](https://www.postgresql.org/download/)

Additionally configure TomCat: 
* Add artifact 
*    ↳ Add Java SDK 11

### For set up DB:
* Use file src/main/resources/init_db.sql to create the DataBase all the tables required by this app.

* At src/main/resources/db.properties specify username and password for your DB to create a Connection.

* If your DB is not PosgreSQL add the dependency for the connector to your DBMS to POM.XML. You should write in your DBMS,
 establish a connection, having downloaded the driver for it and specify the version of the DBMS.



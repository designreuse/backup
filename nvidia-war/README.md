Follow the steps to start with nVidia Project

#Database configuration
 
 we have to change the database specific configuration in apache-tomcat-7.0.37/config/db.properties
 following are the properties that you need to change
 
 1) jdbc.databaseName=nVidia(Your database name)
 
 2) jdbc.connectionUrl=jdbc:mysql://localhost:3306/${jdbc.databaseName}?useUnicode=yes&amp;characterEncoding=UTF-8
 
 (change above if you are using only remote mysql server or different port)
 
 3) jdbc.username=root  (username  for database)
 
 4) jdbc.password=root  (password  for database)
 
 5) Import the db.sql file into your mysqlserver.
 
#change the logging level
 To change log level go to apache-tomcat-7.0.37/config/log4j.xml and modify log level 
 
#Compile and run the server
 open terminal and redirect to folder where pom.xml file is exists
 
 1) mvn clean install 
  Will compile the all code and packages into war file named as nvidia.war
  and nvidia.war will be copied to apache-tomcat-7.0.37/webapps/
  
 2) start server on Windows
 server.bat start (this will start your tomcat with debugging mode)
 
 3) start server on Linux or Mac 
 server.sh start (this will start your tomcat with debugging mode)
 
 you can observe the logs in apache-tomcat-7.0.37/logs/nvidia.log 
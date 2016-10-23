@ECHO OFF
set "CURRENT_DIR=%cd%"
set "CATALINA_HOME=%CURRENT_DIR%/apache-tomcat-7.0.37"
set "JAVA_OPTS=-Xms256m -Xmx1024m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=%CATALINA_HOME%\logs -XX:PermSize=512m -XX:MaxPermSize=512m -verbosegc -XX:+PrintGCDateStamps -Ddisable.logs.transaction=true -Dlog4j.configuration=%CATALINA_HOME%/config/log4j.xml -DAPN.cert="$CATALINA_HOME"/ClidentityProdKey.p12 -DAPN.pass=pwd -DGCM_KEY=AIzaSyBHLkZ07ljNz95-OY5v-_mvp_KQn6fdevQ"
set "TITLE=nVidia Application"
if "%1"=="start" goto start_server
if "%1"=="start" goto run_server
if "%1"=="stop"  goto stop_server
if "%1"=="" goto begin

:begin

goto end

:begin
ECHO ******************************************************************
ECHO server.bat firstParam [secondParam]
ECHO WHERE firstParam = start/stop (start or stopping server)
ECHO      secondParam = yes/no (close the command prompt or not [firstParam])
ECHO *******************************************************************
goto end

:start_server
ECHO Activating Apache Server Service. . .
"%CATALINA_HOME%"/bin/catalina.bat jpda start
ECHO Server is starting..!
goto end

:run_server
ECHO Activating Apache Server Service. . .
"%CATALINA_HOME%"/bin/catalina.bat jpda run
ECHO Server is starting..!
goto end

:stop_server
ECHO Stopping Apache Server Service...
"%CATALINA_HOME%"/bin/catalina.bat stop
ECHO Server is shutting down!
goto end

:end
if "%2"=="yes" (
  exit
)
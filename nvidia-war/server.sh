set +v
# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`

# Only set CATALINA_HOME if not already set
[ -z "$CATALINA_HOME" ] && CATALINA_HOME=`cd "$PRGDIR/apache-tomcat-7.0.37" >/dev/null; pwd`

#echo "Catalina Home  >>>"  $CATALINA_HOME

chmod 777 $CATALINA_HOME/bin/*.sh
export JRE_HOME=/usr/lib/jvm/java-8-oracle/jre
export JAVA_OPTS="-Xms256m -Xmx1024m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=%CATALINA_HOME%\logs -XX:PermSize=512m -XX:MaxPermSize=512m -verbosegc -XX:+PrintGCDateStamps -Ddisable.logs.transaction=true -Dlog4j.configuration="$CATALINA_HOME"/config/log4j.xml -Ddb.configurations="$CATALINA_HOME"/config/db.properties"
#echo "Java Opts   >>> " $JAVA_OPTS

if [ "$1" = "start" ] ; then
   $CATALINA_HOME/bin/catalina.sh jpda $1
elif [ "$1" = "stop" ]; then
   $CATALINA_HOME/bin/catalina.sh $1
   elif [ "$1" = "run" ]; then
   	$CATALINA_HOME/bin/catalina.sh jpda $1
else 
   echo "Set param as start"
   exit 1  
fi

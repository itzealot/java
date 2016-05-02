#! /bin/sh

HOME=`dirname $0`

echo $HOME

JAR_HOME=$HOME/bin/logger-demo-0.0.1-SNAPSHOT.jar

MAIN_CLASS=com.sky.projects.jar.App

java -classpath $JAR_HOME $MAIN_CLASS
#! /bin/sh

cd `dirname $0`

BIN_HOME=`pwd`
# echo $BIN_HOME

cd ../

HOME=`pwd`
# echo $HOME

MAIN_CLASS="com.surfilter.mass.App"
# echo $MAIN_CLASS

LIB_PATH=$HOME/lib/*
# echo $LIB_PATH

LIB_JARS=.
for jar in `ls $LIB_PATH`
do
    LIB_JARS=$LIB_JARS:$jar
done
# echo $LIB_JARS

CONF_PATH=$HOME/conf/*
for jar in `ls $CONF_PATH`
do
    LIB_JARS=$LIB_JARS:$jar
done
# echo $LIB_JARS

JAVA_OPTS="-server -Xmx2g -Xms2g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC"
# echo $JAVA_OPTS

DIR_LOG=$HOME/logs/log.log

java $JAVA_OPTS -classpath $LIB_JARS $MAIN_CLASS $1> $DIR_LOG 2>&1 &

# PIDS=`ps -f | grep java | awk '{print $2}'`
# echo "PID: $PIDS"

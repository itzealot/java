#! /bin/sh
DIR=`dirname $0`

cd $DIR

BIN_HOME=`pwd`

# echo $BIN_HOME

cd ..

HOME=`pwd`

# echo $HOME

MAIN_CLASS="com.sky.projects.logger.App"

echo $MAIN_CLASS

LIB_PATH="$HOME/lib/*"

# for each the jars and append
export CLASSPATH
for jarpath in `ls $HOME/lib/*.jar`
do
	CLASSPATH=$CLASSPATH:$jarpath
done
export CLASSPATH=$CLASSPATH

echo $CLASSPATH

CONF_DIR="$HOME/conf/*"

echo $CONF_DIR

java -classpath $CLASSPATH $MAIN_CLASS

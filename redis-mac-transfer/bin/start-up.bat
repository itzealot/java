@echo off

set BIN_HOME=%~dp0

echo %BIN_HOME%

cd ../

set HOME=%cd%

echo %HOME%

set JAR_HOME=%HOME%/lib/*

echo %JAR_HOME%

set CONF_HOME=%HOME%/conf/*

set MAIN_CLASS=com.surfilter.mass.App

java -classpath "%JAR_HOME%;%CONF_HOME%" %MAIN_CLASS%

cd bin

pause
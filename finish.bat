@ECHO OFF
:: So it turns out, most likely due to some stupid bug in Windows, that this file does not work.
:: Rather than doing something useful like print an error message to tell you what the hell is wrong
:: the batch script simply runs build.bat and then quietly terminates, never actually building the jar like it is supposed to.
:: Now, I could go ahead and try to fix this, but the project deadline has already passed and I simply can't be bothered.
:: In order to finish turning the project into a jar, simply cd into the bin directory and copy paste the jar command below.


ECHO Sorry, but this script has been disabled due to Windows batch stupidity.
ECHO Please copy and paste the following commands into your terminal:
ECHO .\build.bat
ECHO cd bin
ECHO jar cfe .\DungeonVania.jar DungeonVania .\*.class
EXIT :: Terminate the application prematurely to prevent stupidity fest.

.\build.bat

CD bin

jar cfe .\DungeonVania.jar DungeonVania .\*.class

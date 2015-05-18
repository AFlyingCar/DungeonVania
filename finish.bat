@ECHO OFF

.\build.bat

CD bin

jar cfe .\DungeonVania.jar DungeonVania .\*.class

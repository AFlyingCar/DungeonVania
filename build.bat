@ECHO OFF

MKDIR .\bin

"C:\Program Files (x86)\Java\jdk1.7.0_80\bin\javac.exe" -cp .;lib\commons-lang3-3.4.jar -d .\bin .\*.java 

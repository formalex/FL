set INI=D:\P\tesis\proyecto\tesis-jpb\config.ini
set CP=./clases
set CP=%CP%;jar/log4j.jar
set CP=%CP%;jar/velocity-1.7-dep.jar

java -Dfile.encoding=ISO-8859-1 -classpath %CP% ar.uba.dc.formalex.ui.Main %INI%
pause
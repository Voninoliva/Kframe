javac -d ./bin --release 8  etu1790/framework/*.java
javac -d ./bin --release 8  etu1790/framework/annotations/*.java
javac -d ./bin --release 8  etu1790/framework/servlet/*.java
javac -d ./bin --release 8  fonctions/*.java

cd bin
jar cvf FW.jar ./
copy FW.jar "D:\SystemeI\test-framework\lib"

jar cvf project.war "D:\SystemeI\test-framework\build\web\WEB-INF"
copy project.war "C:\Program Files\apache-tomcat-10\webapps" 
pause
javac web/WEB-INF/classes/*/*.java -classpath "./web/WEB-INF/classes:/home/whitefox/dev/glassfish-6.2.5/glassfish6/glassfish/lib/javaee.jar"
cd web
jar -cf ../target/web.war *
cp ../target/web.war /home/whitefox/dev/glassfish-6.2.5/glassfish6/glassfish/domains/domain1/autodeploy/
sleep 3
google-chrome http://localhost:8080/web
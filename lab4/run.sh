echo "[*] Компиляция"
javac web/WEB-INF/classes/*/*.java -classpath "./web/WEB-INF/classes:/home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/lib/javaee.jar:/home/whitefox/dev/lib/jsoup-1.15.3.jar"
echo "[*] Сборка war-архива"
cd web
jar -cf ../target/web.war *
echo "[*] Деплой"
cp ../target/web.war /home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/domains/domain1/autodeploy/
sleep 5
echo "[*] Запуск"
chromium-freeworld --incognito http://localhost:8080/web
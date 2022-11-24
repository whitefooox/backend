clear
echo "[*] Компиляция"
find -name "*.java" > sources.txt
javac @sources.txt -classpath "./web/WEB-INF/classes:/home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/lib/javaee.jar:/home/whitefox/dev/lib/jsoup-1.15.3.jar:/home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/modules/jakarta.enterprise.cdi-api.jar"
echo "[*] Сборка war-архива"
cd web
jar -cf ../target/web.war *
echo "[*] Очистка"
find ./ -type f -name "*.class" -delete
#cat /dev/null > /home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/domains/domain1/logs/server.log
echo "[*] Деплой"
cp ../target/web.war /home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/domains/domain1/autodeploy/
sleep 5
if [[ -n $(find /home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/domains/domain1/ -name "*.war_deployed") ]]
then
    echo "[*] О-о-о работает..."
else 
if [[ -n $(find /home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/domains/domain1/ -name "*.war_deployFailed") ]]
then
    echo "[*] Что-то пошло не по плану"
    echo "[*] Почекаем логи..."
    xdg-open "/home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/domains/domain1/logs/server.log"
else
    echo "[*] Загадочные обстоятельства"
fi
fi
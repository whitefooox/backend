clear
echo "[*] Запуск базы данных"
docker start postgres
echo "[*] Запуск домена"
/home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/bin/asadmin start-domain
#echo "[*] Запуск браузера"
#google-chrome --incognito http://localhost:8080/web
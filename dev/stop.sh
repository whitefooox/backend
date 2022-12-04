clear
echo "[*] Остановка базы данных"
docker stop postgres
echo "[*] Остановка домена"
/home/whitefox/dev/lib/glassfish-6.2.5/glassfish6/glassfish/bin/asadmin stop-domain
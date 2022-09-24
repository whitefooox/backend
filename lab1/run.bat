@echo off
cls

setlocal
call :setESC

echo %ESC%[33m[*] Компиляция%ESC%[0m

javac web\WEB-INF\classes\*.java -classpath ".\web\WEB-INF\classes;C:\dev\glassfish6\glassfish\lib\javaee.jar;C:\dev\jdbc\postgresql-42.5.0.jar"

echo %ESC%[35m[*] Создание war-архива%ESC%[0m

cd web
jar -cf ../target/web.war *
copy ..\target\web.war c:\dev\glassfish6\glassfish\domains\domain1\autodeploy > nul

echo %ESC%[32m[*] Запуск%ESC%[0m

timeout 3 > nul
start chrome http://localhost:8080/web/login.jsp

:setESC
for /F "tokens=1,2 delims=#" %%a in ('"prompt #$H#$E# & echo on & for %%b in (1) do rem"') do (
  set ESC=%%b
  exit /B 0
)
exit /B 0
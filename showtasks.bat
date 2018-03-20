call runcrud.bat
if "%ERRORLEVEL%" == "0" goto website
echo.
echo RUNCLUD has errors - breaking work
goto message

:message
echo.
echo There were error with compilation

:website
start https://kodilla.com/pl
start http://localhost:8080/crud/v1/task/getTask

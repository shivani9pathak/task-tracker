@echo on
setlocal EnableExtensions EnableDelayedExpansion

set ERROR_CODE=0

echo Checking JAVA_HOME: %JAVA_HOME%
if not "%JAVA_HOME%" == "" goto validateJavaHome

echo.
echo Error: JAVA_HOME not found in your environment.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.
echo.
goto error

:validateJavaHome
echo Validating JAVA_HOME: %JAVA_HOME%
if exist "%JAVA_HOME%\bin\java.exe" goto setWrapper

echo.
echo Error: JAVA_HOME is set to an invalid directory.
echo JAVA_HOME = "%JAVA_HOME%"
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.
echo.
goto error

:setWrapper
set MAVEN_PROJECTBASEDIR=%CD%
set WRAPPER_JAR="%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

echo Project base dir: %MAVEN_PROJECTBASEDIR%
echo Wrapper JAR: %WRAPPER_JAR%
echo Java exe: "%JAVA_HOME%\bin\java.exe"

"%JAVA_HOME%\bin\java.exe" ^
  -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" ^
  -classpath %WRAPPER_JAR% ^
  %WRAPPER_LAUNCHER% %*

if ERRORLEVEL 1 goto error
goto end

:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%

exit /B %ERROR_CODE%
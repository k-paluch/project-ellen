@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  project-ellen startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and PROJECT_ELLEN_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\project-ellen-1.0.jar;%APP_HOME%\lib\gamelib-framework-2.0.0.jar;%APP_HOME%\lib\gamelib-inspector-2.0.0.jar;%APP_HOME%\lib\gamelib-core-2.0.0.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.2.51.jar;%APP_HOME%\lib\inspector-1.0.1.jar;%APP_HOME%\lib\inspector-annotations-1.0.1.jar;%APP_HOME%\lib\gdx-freetype-1.9.8.jar;%APP_HOME%\lib\gdx-backend-lwjgl3-1.9.8.jar;%APP_HOME%\lib\gdx-backend-headless-1.9.8.jar;%APP_HOME%\lib\gdx-1.9.8.jar;%APP_HOME%\lib\ktx-graphics-1.9.8-b4.jar;%APP_HOME%\lib\fast-classpath-scanner-3.1.9.jar;%APP_HOME%\lib\gdx-platform-1.9.8-natives-desktop.jar;%APP_HOME%\lib\gdx-freetype-platform-1.9.8-natives-desktop.jar;%APP_HOME%\lib\kotlinx-coroutines-core-0.24.0.jar;%APP_HOME%\lib\ktx-app-1.9.8-b4.jar;%APP_HOME%\lib\ktx-assets-1.9.8-b4.jar;%APP_HOME%\lib\ktx-async-1.9.8-b4.jar;%APP_HOME%\lib\ktx-collections-1.9.8-b4.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.2.51.jar;%APP_HOME%\lib\kotlin-stdlib-1.2.51.jar;%APP_HOME%\lib\lwjgl-glfw-3.1.3.jar;%APP_HOME%\lib\lwjgl-glfw-3.1.3-natives-windows.jar;%APP_HOME%\lib\lwjgl-glfw-3.1.3-natives-linux.jar;%APP_HOME%\lib\lwjgl-glfw-3.1.3-natives-macos.jar;%APP_HOME%\lib\lwjgl-jemalloc-3.1.3.jar;%APP_HOME%\lib\lwjgl-jemalloc-3.1.3-natives-windows.jar;%APP_HOME%\lib\lwjgl-jemalloc-3.1.3-natives-linux.jar;%APP_HOME%\lib\lwjgl-jemalloc-3.1.3-natives-macos.jar;%APP_HOME%\lib\lwjgl-opengl-3.1.3.jar;%APP_HOME%\lib\lwjgl-opengl-3.1.3-natives-windows.jar;%APP_HOME%\lib\lwjgl-opengl-3.1.3-natives-linux.jar;%APP_HOME%\lib\lwjgl-opengl-3.1.3-natives-macos.jar;%APP_HOME%\lib\lwjgl-openal-3.1.3.jar;%APP_HOME%\lib\lwjgl-openal-3.1.3-natives-windows.jar;%APP_HOME%\lib\lwjgl-openal-3.1.3-natives-linux.jar;%APP_HOME%\lib\lwjgl-openal-3.1.3-natives-macos.jar;%APP_HOME%\lib\lwjgl-3.1.3.jar;%APP_HOME%\lib\lwjgl-3.1.3-natives-windows.jar;%APP_HOME%\lib\lwjgl-3.1.3-natives-linux.jar;%APP_HOME%\lib\lwjgl-3.1.3-natives-macos.jar;%APP_HOME%\lib\jlayer-1.0.1-gdx.jar;%APP_HOME%\lib\jorbis-0.0.17.jar;%APP_HOME%\lib\kotlinx-coroutines-core-common-0.24.0.jar;%APP_HOME%\lib\qdox-2.0-M2.jar;%APP_HOME%\lib\swingx-all-1.6.4.jar;%APP_HOME%\lib\atomicfu-common-0.11.0.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.2.51.jar;%APP_HOME%\lib\annotations-13.0.jar

@rem Execute project-ellen
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %PROJECT_ELLEN_OPTS%  -classpath "%CLASSPATH%" sk.tuke.kpi.gamelib.framework.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable PROJECT_ELLEN_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%PROJECT_ELLEN_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega

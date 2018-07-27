set Path=%Path%;D:\Software\jdk\jdk1.8.0_101\bin
java -version
java -cp ".\bin;.\lib\*" org.testng.TestNG ".\testng_coast_smoke.xml"
echo start copy smoke result to Jenkins server
cd /d D:\Smoke
xcopy /F /R /Y SmokeResult.txt \\CANGZDWATS01\JenkinsCI\SVNTools\export
echo finish copying Smoke test result
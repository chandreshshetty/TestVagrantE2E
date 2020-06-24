# TestVagrantE2E

This Framework developed using Page Object Model stracture and this framework can be used to automate both API and UI applications

#####Selenium,RestAssured,TestNG,log4j

Setup all required data in the globalConfig properties file or we can pass 'city' and 'browser' value as arguments while executing through commands and also defined variance value for comparing Temp and Humidity in globalConfig file

Use below command to run through command prompt or we can run through testng.xml file inside the project (If parameteres are not passed, then it will take parameters mentioned in the globalConfig properties file)

mvn test -Dcity=Bengaluru -Dbrowser=chrome

Used WebdriverManager to manage drivers related to each browser, so no need to download or configure driver exe in the project.

Used apache log4j for logging purpose and we can customize logging requirements as per our requirement in log4j.properties and generated log will write into both console and log file(WeatherLogFile.log)

You can check for latest execution report in the test-output/index.html file and used default TestNg for reporting

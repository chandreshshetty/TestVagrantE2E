# TestVagrantE2E

This Framework developed using Page Object Model stracture and this framework can be used to automate both API and UI applications

Setup all required data in the globalConfig properties file or we can pass 'city' and 'browser' value as arguments while executing through commands

mvn test -Dcity=Bengaluru -Dbrowser=chrome

We used WebdriverManager to setup driver related to each browser, so no need to download or configure driver exe in the project.

Used apache lgo4j for logging purpose and we can customize logging requirements as per our requirement in log4j.properties and generated log will write into both console and log file

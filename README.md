My PROG5121 POE Part 1 - Registration and Login



Project description



This project is a Java console application created for Part 1 of the

Programming 1A Portfolio of Evidence.



This application will allow a user to:



1 Register an account

2 Validate a username

3 Validate a password

4 Validate a South African cellphone number

5 Log in using the registered username and password

6 Receive a successful or unsuccessful login message



The application utilizes a console interface only and there is no graphical user interface

or JOptionPane  is being used to.



&#x09;	## Username requirements



The username must:



1 Contain an underscore

2 Be no more than five characters long



Example of a validating usernames:



```text

kyl\_1



The cellphone number must contain an international country code.



Example of a valid cellphone number:

+27838968976



Password requirements

The password must:



Contain at least eight characters

Contain a capital letter

Contain a number

Contain a special character



Example of a valid password:

Ch\&\&sec@ke99!



Example of an invalid cellphone number:

08966553







**Project classes**



&#x09;	*LoginTest.java*

This class contains the JUnit tests for the registration and login

functionality.



&#x09;	*Prog5121PoePart1.java*

This class contains the main method and runs the console application.





&#x09;	*Login.java*

The Login class contains the registration and login functionality,

including:



&#x09;	checkUserName()

&#x09;	checkPasswordComplexity()

&#x09;	checkCellPhoneNumber()

&#x09;	registerUser()

&#x09;	loginUser()

&#x09;	returnLoginStatus()



**Running the tests**

The project uses JUnit tests to test:



&#x09;	1Valid usernames

&#x09;		2Invalid usernames

&#x09;		3Valid passwords

&#x09;	4Invalid passwords

&#x09;		5Valid cellphone numbers

&#x09;		6Invalid cellphone numbers

&#x09;	7Successful login

&#x09;		8Failed login

&#x09;		9Registration messages

&#x09;	10Login status messages

The tests can be run by right-clicking the project in NetBeans and selecting

Test.





**How to run the application**

&#x09;1Open the project in Apache NetBeans.

&#x09;2Right-click Prog5121PoePart1.java.

&#x09;3Select Run File.

&#x09;4Enter the requested registration details.

&#x09;5Enter the username and password again when prompted to log in.



**Regex attribution**

The regular-expression syntax was referenced from the Oracle Java Pattern

documentation:



*https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html*


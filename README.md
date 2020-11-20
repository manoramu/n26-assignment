# N26 Assignment

## TestCases for Monefy Android App

You can find the test cases in n26-test-case.md to test the Monefy Android App

## Rest API - API Playground TestCases

Please find below the steps to setup this project in eclipse and to run the project in the same

### Basic Requirements
- Java 1.8 or greater
- eclipse ide with maven

### Project Setup

  1. Clone this repository in your local machine
  2. Open eclipse and import this project into your eclipse workspace as below
        1. File Menu > Import > Projects from Folder or Archive
        2. Click on Directory button in Import wizard and select the path where you have cloned this repository
        3. Click on Finish to import the repository as project in eclipse
  3. Either right click on the project and select **Maven > Update project...** or go to command prompt to this project location and type `mvn clean install`
  
### Run Project

1. Open App.java file in eclipse from the project imported from above step
2. Right click and select **Run As > JUnit Test**

You will be able to see the program running successfully with all the test cases

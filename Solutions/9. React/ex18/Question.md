Objectives
Explain the need for Unit Testing in React
Working with Jest and Enzyme in React
List the types of Router Components
In this hands-on lab, you will learn how to:
Install and configure Enzyme
Create unit test using describe(), test()
Mount component and test them using matchers
Capture snapshots of the React components
Prerequisites
The following is required to complete this hands-on lab:
Node.js
NPM
Visual Studio Code
Notes
Estimated time to complete this lab: 90 minutes.
My Academy team at Cognizant want to create a dashboard containing the details of ongoing and completed cohorts. A react application is created which displays the detail of the cohorts using React component. You are assigned the task of unit testing the component to ensure it’s free of bugs.
Download the following attachment to find the application code.
Unzip the attachment and open it in visual studio code. 
Open the terminal and execute the following command to restore the node modules
Figure 1: Packages Restore
Execute the following commands to install Enzyme and Enzyme test adapter
Figure 2: Installing Enzyme support
Go to file explorer and modify the setupTests.js file to make it look like the following
Figure 3: Configuring Enzyme support
Add a new unit test file with the name as CohortDetails.test.js to write the unit tests for the CohortDetails component defined inside CohortDetails.js file.
Import the following into the unit test
mount, shallow from Enzyme library
CohortDetails component
CohortData array from Cohort.js file
Define a test suite using describe() and name it as “Cohort Details Component”
Define the following unit tests using test()
Table 1: Unit Test - 1
Test - 1
Name: should create the component
A unit test which will load the CohortDetails component in isolation
           Table 2: Unit Test - 2
Test - 2
Name: should initialize the props
This test should mount the component and assign a cohort object to the props. Using matchers ensure that the props are assigned a given cohort object
           Table 3: Unit Test - 3
Test - 3
Name: should display cohort code in h3
This test should mount the component and search for an h3 element using find() and verify that it displays the proper cohort code of the object passed in props
           Table 4: Unit Test - 4
Test - 4
Name: should always render same html
This test should be responsible for testing the snapshot of the component.
Build and run the tests using npm test command.

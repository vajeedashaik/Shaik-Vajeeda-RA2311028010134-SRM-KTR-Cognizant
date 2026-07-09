Objectives
Understanding the need for styling react component
Working with CSS Module and inline styles
In this hands-on lab, you will learn how to:
Style a react component
Define styles using the CSS Module
Apply styles to components using className and style properties
Prerequisites
The following is required to complete this hands-on lab:
Node.js
NPM
Visual Studio Code
Notes
Estimated time to complete this lab: 30 minutes.
My Academy team at Cognizant want to create a dashboard containing the details of ongoing and completed cohorts. A react application is created which displays the detail of the cohorts using react component. You are assigned the task of styling these react components.
Download and build the attached react application.
Unzip the react application in a folder
Open command prompt and switch to the react application folder
Restore the node packages using the following commands
Figure 1: Restore packages
Open the application using VS Code
Create a new CSS Module in a file called “CohortDetails.module.css”
Define a css class with the name as “box” with following properties
Width = 300px;
Display = inline block;
Overall 10px margin
Top and bottom padding as 10px
Left and right padding as 20px
1 px border in black color
A border radius of 10px
Define a css style for html &lt;dt&gt; element using tag selector. Set the font weight to 500.
Open the cohort details component and import the CSS Module
Apply the box class to the container div
Define the style for &lt;h3&gt; element to use “green” color font when cohort status is “ongoing” and “blue” color in all other scenarios.
Final result should look similar to the below image
Figure 2: Final Result

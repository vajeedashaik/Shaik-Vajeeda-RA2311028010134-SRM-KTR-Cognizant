Objectives
Explain the need and benefits of React Router
Identify the Components in React Router
List the types of Router Components
Parameter passing via url
In this hands-on lab, you will learn how to:
Implement a Simple Navigation Menu
Add Basic Routes (install, configure)
Use Routes in React Applications
Prerequisites
The following is required to complete this hands-on lab:
Node.js
NPM
Visual Studio Code
Notes
Estimated time to complete this lab: 60 minutes.
Cognizant Academy teams want to maintain a list of trainers along with their expertise in a SPA using React as the technology. You are assigned the task of creating this React app. 
The following trainers’ data application will deal.
T-ID
Name
Phone
Email
Stream
Skills
Create a new React app using create-react-app tool with the as “TrainersApp”
Open the application using the VS Code
Add a new file called trainer.js inside the src folder and define a class named as “Trainer”  with the following properties
TrainerId
Name
Email
Phone
Technology
Skills
Figure 2: Trainer.js
Create a new TrainersMock.js file which will contain the mock trainer data. Refer the following screenshot for mock data
Figure 3: TrainersMock.js
Install the support for React router for the dom. Execute the following command.
Figure 4: Install React Router
Create new component named as TrainersList inside Trainerlist.js file. The component should accept the trainer’s data as parameter and render it as a list. The list should display names of each trainers which must be clickable like a hyper link. Refer the following screenshot for the component layout.
Figure 5: TrainersList Component
Create a new component named as Home inside Home.js which will be responsible for displaying the following 
Figure 6: Home Component
Modify the App component to add support for routing and defining the navigation links to Home component and TrainersList component. Use BrowserRouter, Routes, Route and Link components from the react-router-dom library. 
Define the following URL
/  - must redirect to home component
/trainers – must redirect to trainers list component.
The layout of the page must be similar to the following
Figure 7: App Component
Create a new component named TrainerDetail in TrainerDetails.js file. 
The component should retrieve a parameter named id from the URL with the help of “useParams” hook from the React router DOM library. 
It should query the mock trainer data using the id and display the trainer details as show in screenshot. 
Modify the TrainersList component to add Links to TrainerDetail component while passing the ID. Define a route in App component for the same.
Figure 8: Trainers Detail Component
Build and run the application. The complete layout of the application will look as follows.
Figure 9: Home
Figure 10: Trainers List
Figure 11: Trainer Details

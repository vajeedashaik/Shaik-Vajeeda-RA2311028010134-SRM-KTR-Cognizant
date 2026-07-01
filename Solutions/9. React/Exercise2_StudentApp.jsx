// Exercise 2: StudentApp - Home, About, Contact class components
// App: StudentApp | Command: npx create-react-app StudentApp

// src/Components/Home.js
import React, { Component } from 'react';

export class Home extends Component {
  render() {
    return <h2>Welcome to the Home page of Student Management Portal</h2>;
  }
}

// src/Components/About.js
export class About extends Component {
  render() {
    return <h2>Welcome to the About page of the Student Management Portal</h2>;
  }
}

// src/Components/Contact.js
export class Contact extends Component {
  render() {
    return <h2>Welcome to the Contact page of the Student Management Portal</h2>;
  }
}

// src/App.js
import React from 'react';

function App() {
  return (
    <div>
      <Home />
      <About />
      <Contact />
    </div>
  );
}

export default App;

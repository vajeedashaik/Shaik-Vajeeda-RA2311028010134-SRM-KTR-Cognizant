// Exercise 14: Employee management app - Convert props to React Context API
// Shares theme (light/dark) via Context instead of prop drilling

// src/ThemeContext.js
import { createContext } from 'react';

const ThemeContext = createContext('light');
export default ThemeContext;

// src/EmployeeCard.js
import React, { useContext } from 'react';
import ThemeContext from './ThemeContext';

function EmployeeCard({ employee }) {
  const theme = useContext(ThemeContext);

  return (
    <div style={{ border: '1px solid', margin: '10px', padding: '10px' }}>
      <p><strong>Name:</strong> {employee.name}</p>
      <p><strong>Dept:</strong> {employee.dept}</p>
      <button className={theme}>Theme: {theme}</button>
    </div>
  );
}

export default EmployeeCard;

// src/EmployeesList.js
import React from 'react';
import EmployeeCard from './EmployeeCard';

function EmployeesList({ employees }) {
  return (
    <div>
      <h2>Employees List</h2>
      {employees.map((emp, i) => (
        <EmployeeCard key={i} employee={emp} />
      ))}
    </div>
  );
}

export default EmployeesList;

// src/App.js
import React, { Component } from 'react';
import ThemeContext from './ThemeContext';
import EmployeesList from './EmployeesList';

const employees = [
  { name: 'Alice', dept: 'Engineering' },
  { name: 'Bob', dept: 'HR' },
  { name: 'Carol', dept: 'Finance' }
];

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { theme: 'light' };
  }

  toggleTheme() {
    this.setState(prev => ({ theme: prev.theme === 'light' ? 'dark' : 'light' }));
  }

  render() {
    return (
      <ThemeContext.Provider value={this.state.theme}>
        <div>
          <h1>Employee Management</h1>
          <p>Current Theme: {this.state.theme}</p>
          <button onClick={() => this.toggleTheme()}>Toggle Theme</button>
          <EmployeesList employees={employees} />
        </div>
      </ThemeContext.Provider>
    );
  }
}

export default App;

// Exercise 15: ticketraisingapp - React forms with ComplaintRegister

// src/ComplaintRegister.js
import React, { Component } from 'react';

class ComplaintRegister extends Component {
  constructor(props) {
    super(props);
    this.state = {
      employeeName: '',
      complaint: ''
    };
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(e) {
    e.preventDefault();
    const refNumber = 'REF-' + Math.floor(Math.random() * 100000);
    alert(`Complaint submitted successfully!\nReference Number: ${refNumber}`);
    this.setState({ employeeName: '', complaint: '' });
  }

  render() {
    return (
      <div>
        <h2>Complaint Register</h2>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Employee Name: </label>
            <input
              type="text"
              value={this.state.employeeName}
              onChange={e => this.setState({ employeeName: e.target.value })}
              placeholder="Enter your name"
              required
            />
          </div>
          <br />
          <div>
            <label>Complaint: </label>
            <textarea
              value={this.state.complaint}
              onChange={e => this.setState({ complaint: e.target.value })}
              placeholder="Describe your complaint"
              rows={5}
              cols={40}
              required
            />
          </div>
          <br />
          <button type="submit">Submit Complaint</button>
        </form>
      </div>
    );
  }
}

export default ComplaintRegister;

// src/App.js
import React from 'react';
import ComplaintRegister from './ComplaintRegister';

function App() {
  return (
    <div>
      <h1>Ticket Raising App</h1>
      <ComplaintRegister />
    </div>
  );
}

export default App;

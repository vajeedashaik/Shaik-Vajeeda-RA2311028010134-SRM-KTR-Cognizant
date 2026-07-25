// Exercise 17: fetchuserapp - Fetch user data from REST API (https://api.randomuser.me/)
// Displays title, firstname, image of user

// src/Getuser.js
import React, { Component } from 'react';

class Getuser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      error: null
    };
  }

  async componentDidMount() {
    try {
      const response = await fetch('https://api.randomuser.me/');
      const data = await response.json();
      this.setState({ user: data.results[0] });
    } catch (err) {
      this.setState({ error: err.message });
    }
  }

  render() {
    const { user, error } = this.state;

    if (error) return <p>Error: {error}</p>;
    if (!user) return <p>Loading...</p>;

    return (
      <div>
        <h2>User Details</h2>
        <img src={user.picture.large} alt={user.name.first} style={{ width: 100, borderRadius: '50%' }} />
        <p><strong>Title:</strong> {user.name.title}</p>
        <p><strong>First Name:</strong> {user.name.first}</p>
        <p><strong>Last Name:</strong> {user.name.last}</p>
        <p><strong>Email:</strong> {user.email}</p>
      </div>
    );
  }
}

export default Getuser;

// src/App.js
import React from 'react';
import Getuser from './Getuser';

function App() {
  return (
    <div>
      <h1>Fetch User App</h1>
      <Getuser />
    </div>
  );
}

export default App;

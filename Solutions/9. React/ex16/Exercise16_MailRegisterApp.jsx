// Exercise 16: mailregisterapp - Form validation (name ≥5 chars, email has @/., password ≥8 chars)

// src/register.js
import React, { Component } from 'react';

class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
      email: '',
      password: '',
      errors: {}
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  validate() {
    const { name, email, password } = this.state;
    const errors = {};

    if (name.length < 5) {
      errors.name = 'Name must have at least 5 characters.';
    }
    if (!email.includes('@') || !email.includes('.')) {
      errors.email = 'Email must contain @ and .';
    }
    if (password.length < 8) {
      errors.password = 'Password must have at least 8 characters.';
    }

    return errors;
  }

  handleChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  handleSubmit(e) {
    e.preventDefault();
    const errors = this.validate();
    if (Object.keys(errors).length > 0) {
      this.setState({ errors });
    } else {
      this.setState({ errors: {} });
      alert('Registration successful!');
    }
  }

  render() {
    const { name, email, password, errors } = this.state;
    return (
      <div>
        <h2>Mail Register</h2>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Name: </label>
            <input
              type="text"
              name="name"
              value={name}
              onChange={this.handleChange}
              placeholder="Enter name (min 5 chars)"
            />
            {errors.name && <span style={{ color: 'red' }}> {errors.name}</span>}
          </div>
          <br />
          <div>
            <label>Email: </label>
            <input
              type="text"
              name="email"
              value={email}
              onChange={this.handleChange}
              placeholder="Enter email"
            />
            {errors.email && <span style={{ color: 'red' }}> {errors.email}</span>}
          </div>
          <br />
          <div>
            <label>Password: </label>
            <input
              type="password"
              name="password"
              value={password}
              onChange={this.handleChange}
              placeholder="Enter password (min 8 chars)"
            />
            {errors.password && <span style={{ color: 'red' }}> {errors.password}</span>}
          </div>
          <br />
          <button type="submit">Register</button>
        </form>
      </div>
    );
  }
}

export default Register;

// src/App.js
import React from 'react';
import Register from './register';

function App() {
  return (
    <div>
      <h1>Mail Register App</h1>
      <Register />
    </div>
  );
}

export default App;

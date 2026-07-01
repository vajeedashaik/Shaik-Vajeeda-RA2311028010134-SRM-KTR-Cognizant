// Exercise 12: ticketbookingapp - Conditional rendering (Login/Logout, Guest/User pages)

// src/App.js
import React, { Component } from 'react';

function GuestPage() {
  return (
    <div>
      <h2>Guest Page - Flight Details</h2>
      <table border="1" cellPadding="10">
        <thead>
          <tr><th>Flight</th><th>From</th><th>To</th><th>Departure</th><th>Price</th></tr>
        </thead>
        <tbody>
          <tr><td>AI-101</td><td>Bangalore</td><td>Mumbai</td><td>10:00 AM</td><td>₹5000</td></tr>
          <tr><td>6E-202</td><td>Delhi</td><td>Chennai</td><td>2:00 PM</td><td>₹4500</td></tr>
          <tr><td>SG-303</td><td>Hyderabad</td><td>Kolkata</td><td>6:00 PM</td><td>₹6000</td></tr>
        </tbody>
      </table>
      <p><em>Please login to book tickets.</em></p>
    </div>
  );
}

function UserPage() {
  return (
    <div>
      <h2>Book Your Ticket</h2>
      <form>
        <label>Select Flight: </label>
        <select>
          <option>AI-101 - Bangalore to Mumbai - ₹5000</option>
          <option>6E-202 - Delhi to Chennai - ₹4500</option>
          <option>SG-303 - Hyderabad to Kolkata - ₹6000</option>
        </select>
        <br /><br />
        <label>Passenger Name: </label>
        <input type="text" placeholder="Enter your name" />
        <br /><br />
        <button type="button">Book Ticket</button>
      </form>
    </div>
  );
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { isLoggedIn: false };
  }

  render() {
    const { isLoggedIn } = this.state;
    return (
      <div>
        <h1>Ticket Booking App</h1>
        {isLoggedIn
          ? <button onClick={() => this.setState({ isLoggedIn: false })}>Logout</button>
          : <button onClick={() => this.setState({ isLoggedIn: true })}>Login</button>
        }
        <hr />
        {isLoggedIn ? <UserPage /> : <GuestPage />}
      </div>
    );
  }
}

export default App;

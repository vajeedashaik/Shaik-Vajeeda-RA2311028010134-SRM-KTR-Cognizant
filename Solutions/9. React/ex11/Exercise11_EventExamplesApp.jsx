// Exercise 11: eventexamplesapp - Event handling, synthetic events, CurrencyConvertor

// src/App.js
import React, { Component } from 'react';

// CurrencyConvertor component - converts INR to EUR and EUR to INR
const INR_PER_EUR = 89.5;

class CurrencyConvertor extends Component {
  constructor(props) {
    super(props);
    this.state = { inr: '', eur: '', inrResult: '', eurResult: '' };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleReverseSubmit = this.handleReverseSubmit.bind(this);
  }

  // Converts Indian Rupees to Euro
  handleSubmit(e) {
    e.preventDefault();
    const eurResult = (this.state.inr / INR_PER_EUR).toFixed(2);
    this.setState({ eurResult });
  }

  // Converts Euro to Indian Rupees
  handleReverseSubmit(e) {
    e.preventDefault();
    const inrResult = (this.state.eur * INR_PER_EUR).toFixed(2);
    this.setState({ inrResult });
  }

  render() {
    return (
      <div>
        <h3>Currency Convertor (INR → EUR)</h3>
        <form onSubmit={this.handleSubmit}>
          <input
            type="number"
            placeholder="Enter INR amount"
            value={this.state.inr}
            onChange={e => this.setState({ inr: e.target.value })}
          />
          <button type="submit">Convert</button>
        </form>
        {this.state.eurResult && <p>EUR: €{this.state.eurResult}</p>}

        <h3>Currency Convertor (EUR → INR)</h3>
        <form onSubmit={this.handleReverseSubmit}>
          <input
            type="number"
            placeholder="Enter EUR amount"
            value={this.state.eur}
            onChange={e => this.setState({ eur: e.target.value })}
          />
          <button type="submit">Convert</button>
        </form>
        {this.state.inrResult && <p>INR: ₹{this.state.inrResult}</p>}
      </div>
    );
  }
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { counter: 0 };
  }

  increment() {
    this.setState(prev => ({ counter: prev.counter + 1 }));
  }

  sayHello() {
    alert('Hello! This is a static message.');
  }

  handleMultiple() {
    this.increment();
    this.sayHello();
  }

  decrement() {
    this.setState(prev => ({ counter: prev.counter - 1 }));
  }

  sayWelcome(msg) {
    alert(msg);
  }

  handleSyntheticEvent(e) {
    alert('I was clicked');
  }

  render() {
    return (
      <div>
        <h1>Event Examples App</h1>

        <h3>Counter: {this.state.counter}</h3>
        <button onClick={() => this.handleMultiple()}>Increment (Multiple Methods)</button>
        &nbsp;
        <button onClick={() => this.decrement()}>Decrement</button>

        <br /><br />
        <button onClick={() => this.sayWelcome('welcome')}>Say Welcome</button>

        <br /><br />
        <button onClick={this.handleSyntheticEvent}>OnPress (Synthetic Event)</button>

        <br /><br />
        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;

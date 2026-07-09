// Exercise 11: eventexamplesapp - Event handling, synthetic events, CurrencyConvertor

// src/App.js
import React, { Component } from 'react';

// CurrencyConvertor component - converts INR to EUR
class CurrencyConvertor extends Component {
  constructor(props) {
    super(props);
    this.state = { inr: '', eur: '' };
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(e) {
    e.preventDefault();
    const eur = (this.state.inr / 89.5).toFixed(2);
    this.setState({ eur });
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
        {this.state.eur && <p>EUR: €{this.state.eur}</p>}
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
        <button onClickCapture={this.handleSyntheticEvent}>OnPress (Synthetic Event)</button>

        <br /><br />
        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;

// Exercise 8: counterapp - State with CountPeople (entry/exit counters)

// src/CountPeople.js
import React, { Component } from 'react';

class CountPeople extends Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
  }

  UpdateEntry() {
    this.setState(prev => ({ entrycount: prev.entrycount + 1 }));
  }

  UpdateExit() {
    this.setState(prev => ({ exitcount: prev.exitcount + 1 }));
  }

  render() {
    const { entrycount, exitcount } = this.state;
    return (
      <div>
        <h1>Mall Entry/Exit Counter</h1>
        <p>People Entered: <strong>{entrycount}</strong></p>
        <p>People Exited: <strong>{exitcount}</strong></p>
        <button onClick={() => this.UpdateEntry()}>Login (Entry)</button>
        &nbsp;
        <button onClick={() => this.UpdateExit()}>Exit</button>
      </div>
    );
  }
}

export default CountPeople;

// src/App.js
import React from 'react';
import CountPeople from './CountPeople';

function App() {
  return <CountPeople />;
}

export default App;

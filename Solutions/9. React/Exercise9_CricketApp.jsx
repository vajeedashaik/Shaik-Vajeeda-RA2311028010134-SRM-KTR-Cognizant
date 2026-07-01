// Exercise 9: cricketapp - ES6 features: map, arrow functions, destructuring, spread

// src/ListofPlayers.js
import React from 'react';

function ListofPlayers() {
  const players = [
    { name: 'Rohit Sharma', score: 85 },
    { name: 'Virat Kohli', score: 62 },
    { name: 'KL Rahul', score: 91 },
    { name: 'Shikhar Dhawan', score: 55 },
    { name: 'Suryakumar Yadav', score: 78 },
    { name: 'Hardik Pandya', score: 45 },
    { name: 'Rishabh Pant', score: 68 },
    { name: 'Ravindra Jadeja', score: 40 },
    { name: 'Jasprit Bumrah', score: 15 },
    { name: 'Mohammed Shami', score: 20 },
    { name: 'Yuzvendra Chahal', score: 12 }
  ];

  // arrow function + filter for players below 70
  const lowScorers = players.filter(p => p.score < 70);

  return (
    <div>
      <h2>All Players</h2>
      <ul>
        {players.map((p, i) => (
          <li key={i}>{p.name}: {p.score}</li>
        ))}
      </ul>
      <h2>Players with Score Below 70</h2>
      <ul>
        {lowScorers.map((p, i) => (
          <li key={i}>{p.name}: {p.score}</li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;

// src/IndianPlayers.js
import React from 'react';

function IndianPlayers() {
  const T20players = ['Rohit', 'Virat', 'KL Rahul', 'Suryakumar', 'Hardik'];
  const RanjiTrophyPlayers = ['Prithvi Shaw', 'Sarfaraz Khan', 'Yashasvi Jaiswal'];

  // Destructuring to get odd/even indexed players
  const [first, second, third, fourth, fifth] = T20players;
  const oddPlayers = [first, third, fifth];
  const evenPlayers = [second, fourth];

  // Merge using spread operator
  const allPlayers = [...T20players, ...RanjiTrophyPlayers];

  return (
    <div>
      <h2>Odd Team Players (Destructuring)</h2>
      <ul>{oddPlayers.filter(Boolean).map((p, i) => <li key={i}>{p}</li>)}</ul>

      <h2>Even Team Players (Destructuring)</h2>
      <ul>{evenPlayers.filter(Boolean).map((p, i) => <li key={i}>{p}</li>)}</ul>

      <h2>All Players Combined (Spread)</h2>
      <ul>{allPlayers.map((p, i) => <li key={i}>{p}</li>)}</ul>
    </div>
  );
}

export default IndianPlayers;

// src/App.js
import React from 'react';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

const flag = true;

function App() {
  return (
    <div>
      <h1>Cricket App</h1>
      {flag ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default App;

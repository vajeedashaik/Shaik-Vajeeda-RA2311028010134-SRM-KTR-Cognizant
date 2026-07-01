// Exercise 3: scorecalculatorapp - CalculateScore function component
// App: scorecalculatorapp | Command: npx create-react-app scorecalculatorapp

// src/Stylesheets/mystyle.css
/*
.scoreCard {
  font-family: Arial, sans-serif;
  border: 1px solid #ccc;
  padding: 20px;
  max-width: 400px;
  margin: 20px auto;
  border-radius: 8px;
}
.scoreCard h2 { color: #333; }
.scoreCard p { font-size: 16px; }
*/

// src/Components/CalculateScore.js
import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore({ name, school, total, goal }) {
  const average = (total / goal) * 100;

  return (
    <div className="scoreCard">
      <h2>Student Score Report</h2>
      <p><strong>Name:</strong> {name}</p>
      <p><strong>School:</strong> {school}</p>
      <p><strong>Total Score:</strong> {total}</p>
      <p><strong>Goal:</strong> {goal}</p>
      <p><strong>Average Score:</strong> {average.toFixed(2)}%</p>
    </div>
  );
}

export default CalculateScore;

// src/App.js
import React from 'react';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div>
      <CalculateScore name="John Doe" school="Springfield High" total={85} goal={100} />
      <CalculateScore name="Jane Smith" school="Shelbyville Academy" total={92} goal={100} />
    </div>
  );
}

export default App;

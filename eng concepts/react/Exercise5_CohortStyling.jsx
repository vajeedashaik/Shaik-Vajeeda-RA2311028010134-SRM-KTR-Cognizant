// Exercise 5: Styling React components with CSS Modules and inline styles
// Cohort dashboard with ongoing/completed status styling

// src/CohortDetails.module.css
/*
.box {
  width: 300px;
  display: inline-block;
  margin: 10px;
  padding: 10px 20px;
  border: 1px solid black;
  border-radius: 10px;
}
dt { font-weight: 500; }
*/

// src/CohortDetails.js
import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails({ cohort }) {
  const headingStyle = {
    color: cohort.status === 'ongoing' ? 'green' : 'blue'
  };

  return (
    <div className={styles.box}>
      <h3 style={headingStyle}>{cohort.name}</h3>
      <dl>
        <dt>Status</dt>
        <dd>{cohort.status}</dd>
        <dt>Start Date</dt>
        <dd>{cohort.startDate}</dd>
        <dt>End Date</dt>
        <dd>{cohort.endDate}</dd>
        <dt>Trainer</dt>
        <dd>{cohort.trainer}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;

// src/App.js
import React from 'react';
import CohortDetails from './CohortDetails';

const cohorts = [
  { name: 'React Batch 1', status: 'ongoing', startDate: '2024-01-01', endDate: '2024-03-31', trainer: 'Alice' },
  { name: 'Java Batch 5', status: 'completed', startDate: '2023-06-01', endDate: '2023-08-31', trainer: 'Bob' },
  { name: 'Spring Boot Batch 2', status: 'ongoing', startDate: '2024-02-01', endDate: '2024-04-30', trainer: 'Carol' }
];

function App() {
  return (
    <div>
      <h1>Cohort Dashboard</h1>
      {cohorts.map((c, i) => <CohortDetails key={i} cohort={c} />)}
    </div>
  );
}

export default App;

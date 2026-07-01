// Exercise 6: TrainersApp - React Router with TrainersList, Home, TrainerDetail
// Install: npm install react-router-dom

// src/trainer.js
export class Trainer {
  constructor(trainerId, name, email, phone, technology, skills) {
    this.trainerId = trainerId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.technology = technology;
    this.skills = skills;
  }
}

// src/TrainersMock.js
import { Trainer } from './trainer';

export const trainers = [
  new Trainer('T001', 'Alice Johnson', 'alice@example.com', '9876543210', 'React', 'JSX, Hooks, Redux'),
  new Trainer('T002', 'Bob Smith', 'bob@example.com', '9876543211', 'Java', 'Spring Boot, Hibernate'),
  new Trainer('T003', 'Carol White', 'carol@example.com', '9876543212', 'Python', 'Django, Flask'),
  new Trainer('T004', 'David Brown', 'david@example.com', '9876543213', 'Angular', 'TypeScript, RxJS'),
  new Trainer('T005', 'Eve Davis', 'eve@example.com', '9876543214', 'Node.js', 'Express, MongoDB')
];

// src/Home.js
import React from 'react';

function Home() {
  return (
    <div>
      <h2>Welcome to Trainers Portal</h2>
      <p>Browse our expert trainers and their skills.</p>
    </div>
  );
}

export default Home;

// src/TrainerList.js
import React from 'react';
import { Link } from 'react-router-dom';

function TrainersList({ trainers }) {
  return (
    <div>
      <h2>Trainers List</h2>
      <ul>
        {trainers.map(t => (
          <li key={t.trainerId}>
            <Link to={`/trainers/${t.trainerId}`}>{t.name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TrainersList;

// src/TrainerDetails.js
import React from 'react';
import { useParams } from 'react-router-dom';
import { trainers } from './TrainersMock';

function TrainerDetail() {
  const { id } = useParams();
  const trainer = trainers.find(t => t.trainerId === id);

  if (!trainer) return <p>Trainer not found.</p>;

  return (
    <div>
      <h2>Trainer Details</h2>
      <p><strong>ID:</strong> {trainer.trainerId}</p>
      <p><strong>Name:</strong> {trainer.name}</p>
      <p><strong>Email:</strong> {trainer.email}</p>
      <p><strong>Phone:</strong> {trainer.phone}</p>
      <p><strong>Technology:</strong> {trainer.technology}</p>
      <p><strong>Skills:</strong> {trainer.skills}</p>
    </div>
  );
}

export default TrainerDetail;

// src/App.js
import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import TrainersList from './TrainerList';
import TrainerDetail from './TrainerDetails';
import { trainers } from './TrainersMock';

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Home</Link> | <Link to="/trainers">Trainers</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/trainers" element={<TrainersList trainers={trainers} />} />
        <Route path="/trainers/:id" element={<TrainerDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;

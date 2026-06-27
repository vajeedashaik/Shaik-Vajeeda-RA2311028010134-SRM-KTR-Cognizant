// Exercise 10: officespacerentalapp - JSX elements, attributes, inline CSS, conditional styling

// src/App.js
import React from 'react';

const officeSpaces = [
  { name: 'TechHub Office', rent: 45000, address: '12 MG Road, Bangalore', image: 'https://via.placeholder.com/150' },
  { name: 'Business Bay', rent: 75000, address: '5 Nariman Point, Mumbai', image: 'https://via.placeholder.com/150' },
  { name: 'StartUp Square', rent: 30000, address: '22 Connaught Place, Delhi', image: 'https://via.placeholder.com/150' },
  { name: 'Prime Tower', rent: 90000, address: '8 Anna Salai, Chennai', image: 'https://via.placeholder.com/150' }
];

function App() {
  return (
    <div>
      <h1>Office Space Rental App</h1>
      <img src={officeSpaces[0].image} alt="Office Space" />
      <div>
        {officeSpaces.map((office, index) => (
          <div key={index} style={{ border: '1px solid #ccc', margin: '10px', padding: '10px' }}>
            <h2>{office.name}</h2>
            <img src={office.image} alt={office.name} />
            <p>
              <strong>Rent: </strong>
              <span style={{ color: office.rent < 60000 ? 'red' : 'green' }}>
                ₹{office.rent}
              </span>
            </p>
            <p><strong>Address:</strong> {office.address}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;

// Exercise 18: Unit testing CohortDetails with Jest and Enzyme
// Install: npm install --save-dev enzyme @wojtekmaj/enzyme-adapter-react-17

// src/Cohort.js
export const CohortData = [
  {
    id: 1,
    name: 'React Batch 1',
    status: 'ongoing',
    startDate: '2024-01-01',
    endDate: '2024-03-31',
    trainer: 'Alice'
  },
  {
    id: 2,
    name: 'Java Batch 5',
    status: 'completed',
    startDate: '2023-06-01',
    endDate: '2023-08-31',
    trainer: 'Bob'
  }
];

// src/CohortDetails.js
import React from 'react';

function CohortDetails({ cohort }) {
  return (
    <div className="cohort-details">
      <h3>{cohort.name}</h3>
      <p className="status">{cohort.status}</p>
      <p>{cohort.startDate} - {cohort.endDate}</p>
      <p>Trainer: {cohort.trainer}</p>
    </div>
  );
}

export default CohortDetails;

// src/setupTests.js
/*
import Enzyme from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
Enzyme.configure({ adapter: new Adapter() });
*/

// src/CohortDetails.test.js
/*
import React from 'react';
import { mount, shallow } from 'enzyme';
import CohortDetails from './CohortDetails';
import { CohortData } from './Cohort';

describe('Cohort Details Component', () => {
  // Test 1: Component renders without crashing
  test('renders CohortDetails component without errors', () => {
    const wrapper = shallow(<CohortDetails cohort={CohortData[0]} />);
    expect(wrapper).toBeTruthy();
  });

  // Test 2: Cohort name is displayed
  test('displays the cohort name correctly', () => {
    const wrapper = mount(<CohortDetails cohort={CohortData[0]} />);
    expect(wrapper.find('h3').text()).toEqual(CohortData[0].name);
  });

  // Test 3: Cohort status is displayed
  test('displays the cohort status correctly', () => {
    const wrapper = mount(<CohortDetails cohort={CohortData[1]} />);
    expect(wrapper.find('.status').text()).toEqual(CohortData[1].status);
  });

  // Test 4: Snapshot test
  test('matches snapshot', () => {
    const wrapper = shallow(<CohortDetails cohort={CohortData[0]} />);
    expect(wrapper).toMatchSnapshot();
  });
});
*/

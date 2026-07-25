// Exercise 18: Unit testing CohortDetails with Jest and Enzyme
// Install: npm install --save-dev enzyme @wojtekmaj/enzyme-adapter-react-17

// src/Cohort.js
export const CohortData = [
  {
    id: 1,
    code: 'RB1',
    name: 'React Batch 1',
    status: 'ongoing',
    startDate: '2024-01-01',
    endDate: '2024-03-31',
    trainer: 'Alice'
  },
  {
    id: 2,
    code: 'JB5',
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
      <h3>{cohort.code}</h3>
      <h4>{cohort.name}</h4>
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
  // Test 1: should create the component (load in isolation)
  test('should create the component', () => {
    const wrapper = shallow(<CohortDetails cohort={CohortData[0]} />);
    expect(wrapper).toBeTruthy();
    expect(wrapper.exists()).toBe(true);
  });

  // Test 2: should initialize the props
  test('should initialize the props', () => {
    const wrapper = mount(<CohortDetails cohort={CohortData[0]} />);
    expect(wrapper.props().cohort).toEqual(CohortData[0]);
  });

  // Test 3: should display cohort code in h3
  test('should display cohort code in h3', () => {
    const wrapper = mount(<CohortDetails cohort={CohortData[1]} />);
    expect(wrapper.find('h3').text()).toEqual(CohortData[1].code);
  });

  // Test 4: should always render same html
  test('should always render same html', () => {
    const wrapper = shallow(<CohortDetails cohort={CohortData[0]} />);
    expect(wrapper).toMatchSnapshot();
  });
});
*/

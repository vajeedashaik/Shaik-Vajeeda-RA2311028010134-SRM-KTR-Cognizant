// Exercise 19: gitclientapp - GitHub API client with Jest mocking
// Install: npm install axios | npm install --save-dev jest

// src/GitClient.js
import axios from 'axios';

class GitClient {
  async getRepositories(username) {
    const response = await axios.get(`https://api.github.com/users/${username}/repos`);
    return response.data.map(repo => repo.name);
  }
}

export default GitClient;

// src/App.js
import React, { Component } from 'react';
import GitClient from './GitClient';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { repos: [], username: 'techiesyed', loading: false };
    this.client = new GitClient();
  }

  async componentDidMount() {
    this.setState({ loading: true });
    try {
      const repos = await this.client.getRepositories(this.state.username);
      this.setState({ repos, loading: false });
    } catch (err) {
      this.setState({ loading: false });
    }
  }

  render() {
    const { repos, loading, username } = this.state;
    return (
      <div>
        <h1>Git Client App</h1>
        <h2>Repositories for: {username}</h2>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <ul>
            {repos.map((repo, i) => <li key={i}>{repo}</li>)}
          </ul>
        )}
      </div>
    );
  }
}

export default App;

// src/GitClient.test.js
/*
import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {
  test('should return repository names for techiesyed', async () => {
    const mockData = {
      data: [
        { name: 'repo-one' },
        { name: 'repo-two' },
        { name: 'repo-three' }
      ]
    };

    axios.get.mockResolvedValue(mockData);

    const client = new GitClient();
    const repos = await client.getRepositories('techiesyed');

    expect(repos).toEqual(['repo-one', 'repo-two', 'repo-three']);
    expect(axios.get).toHaveBeenCalledWith(
      'https://api.github.com/users/techiesyed/repos'
    );
  });
});
*/

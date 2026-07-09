// Exercise 4: blogapp - lifecycle methods componentDidMount, componentDidCatch
// App: blogapp | API: https://jsonplaceholder.typicode.com/posts

// src/Post.js
class Post {
  constructor(id, title, body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }
}

// src/Posts.js
import React, { Component } from 'react';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null
    };
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => response.json())
      .then(data => {
        const posts = data.map(p => new Post(p.id, p.title, p.body));
        this.setState({ posts });
      })
      .catch(err => this.setState({ error: err.message }));
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    alert('An error occurred: ' + error.message);
  }

  render() {
    const { posts, error } = this.state;
    if (error) return <p>Error: {error}</p>;
    return (
      <div>
        <h1>Blog Posts</h1>
        {posts.map(post => (
          <div key={post.id}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;

// src/App.js
import React from 'react';
import Posts from './Posts';

function App() {
  return (
    <div>
      <Posts />
    </div>
  );
}

export default App;

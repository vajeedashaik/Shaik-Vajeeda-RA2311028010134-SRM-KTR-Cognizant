// Exercise 13: bloggerapp - Multiple ways of conditional rendering
// 3 components: BookDetails, BlogDetails, CourseDetails

// src/App.js
import React, { Component } from 'react';

function BookDetails() {
  return (
    <div>
      <h2>Book Details</h2>
      <p>Title: Clean Code by Robert C. Martin</p>
      <p>Author: Robert C. Martin</p>
      <p>Pages: 431</p>
    </div>
  );
}

function BlogDetails() {
  return (
    <div>
      <h2>Blog Details</h2>
      <p>Title: Understanding React Hooks</p>
      <p>Author: Jane Doe</p>
      <p>Published: 2024-01-15</p>
    </div>
  );
}

function CourseDetails() {
  return (
    <div>
      <h2>Course Details</h2>
      <p>Course: Full Stack React Development</p>
      <p>Instructor: John Smith</p>
      <p>Duration: 40 hours</p>
    </div>
  );
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { activeView: 'book' };
  }

  render() {
    const { activeView } = this.state;

    // WAY 1: if-else using element variable
    let component;
    if (activeView === 'book') {
      component = <BookDetails />;
    } else if (activeView === 'blog') {
      component = <BlogDetails />;
    } else {
      component = <CourseDetails />;
    }

    return (
      <div>
        <h1>Blogger App</h1>
        <button onClick={() => this.setState({ activeView: 'book' })}>Books</button>
        &nbsp;
        <button onClick={() => this.setState({ activeView: 'blog' })}>Blogs</button>
        &nbsp;
        <button onClick={() => this.setState({ activeView: 'course' })}>Courses</button>
        <hr />

        {/* WAY 1: element variable */}
        <section>
          <h3>Way 1: Element Variable</h3>
          {component}
        </section>

        {/* WAY 2: ternary operator */}
        <section>
          <h3>Way 2: Ternary Operator</h3>
          {activeView === 'book'
            ? <BookDetails />
            : activeView === 'blog'
              ? <BlogDetails />
              : <CourseDetails />}
        </section>

        {/* WAY 3: && short-circuit evaluation */}
        <section>
          <h3>Way 3: Short-Circuit (&&)</h3>
          {activeView === 'book' && <BookDetails />}
          {activeView === 'blog' && <BlogDetails />}
          {activeView === 'course' && <CourseDetails />}
        </section>
      </div>
    );
  }
}

export default App;

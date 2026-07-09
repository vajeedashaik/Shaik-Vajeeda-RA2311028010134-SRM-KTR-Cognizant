// Exercise 7: shoppingapp - Props with OnlineShopping and Cart class components

// src/App.js
import React, { Component } from 'react';

class Cart {
  constructor(itemname, price) {
    this.itemname = itemname;
    this.price = price;
  }
}

class OnlineShopping extends Component {
  constructor(props) {
    super(props);
    this.items = [
      new Cart('Laptop', 75000),
      new Cart('Mobile', 25000),
      new Cart('Headphones', 3500),
      new Cart('Keyboard', 2000),
      new Cart('Mouse', 1500)
    ];
  }

  render() {
    return (
      <div>
        <h1>Online Shopping Cart</h1>
        <table border="1" cellPadding="10">
          <thead>
            <tr>
              <th>Item Name</th>
              <th>Price (₹)</th>
            </tr>
          </thead>
          <tbody>
            {this.items.map((item, index) => (
              <tr key={index}>
                <td>{item.itemname}</td>
                <td>{item.price}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default OnlineShopping;

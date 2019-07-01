import React, { Component } from 'react';
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import Dashboard from './components/Dashboard';
import Header from './components/layout/Header';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import AddProject from './components/project/AddProject';
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from './components/project/UpdateProject';


class App extends Component {

  constructor(props) {
    super(props);
    console.log("App.js is started.");
  }
  componentDidMount() {
    console.log("App.js is ended.");
  }
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/addProject" component={AddProject} />
            <Route exact path="/updateProject/:id" component={UpdateProject} />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;

import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavigationBar from './components/NavigationBar';
import NavigationContent from './components/NavigationContent';
import FooterContent from './components/FooterContent';


function App() {
  return (
    <div className="App">

      <div className="app-header header container">
        <NavigationBar/>
      </div>

      <div className="app-body body container">
        <NavigationContent/>
      </div>

      <div className="app-footer footer container">
          <FooterContent/>
      </div>

    </div>
  );
}

export default App;

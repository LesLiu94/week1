import React from "react";
import {
  BrowserRouter as Router,
  Link
} from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faHome,
    faUsers,
    faSearch,
    faNotEqual
} from '@fortawesome/free-solid-svg-icons';

class NavigationBar extends React.Component {
    render() {
       return (
        <Router>
            <div>
                <nav className="navbar navbar-expand-lg navbar-light">
                    <Link className="navbar-brand pt-2" to="/">  <FontAwesomeIcon icon={faHome}/>  EmployeeProject</Link>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item">
                                <Link className="nav-link" to="/employee-list"> <FontAwesomeIcon icon={faUsers}/>  Employee List</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/employee-lookup"> <FontAwesomeIcon icon={faSearch}/> Employee Lookup</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/unequally-paid"> <FontAwesomeIcon icon={faNotEqual}/>  Unequally Paid</Link>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </Router>
       );
    }
 }

 export default NavigationBar;
import React from "react";
import {
  BrowserRouter as Router,
  Link
} from "react-router-dom";

class NavigationBar extends React.Component {
    render() {
       return (
        <Router>
            <div>
                <nav class="navbar navbar-expand-lg navbar-light">
                    <Link class="navbar-brand pt-2" to="/"> <i class="fa fa-home"></i>  EmployeeProject</Link>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <Link class="nav-link" to="/employee-list"> <i class="fa fa-users"></i>  Employee List</Link>
                            </li>
                            <li class="nav-item">
                                <Link class="nav-link" to="/employee-lookup"> <i class="fa fa-search"></i> Employee Lookup</Link>
                            </li>
                            <li class="nav-item">
                                <Link class="nav-link" to="/unequally-paid"> <i class="fa fa-not-equal"></i>  Unequally Paid</Link>
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
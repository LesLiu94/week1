import React from "react";
import {
    Switch,
    Route,
    Redirect
} from "react-router-dom";
import Home from './body-contents/Home';
import EmployeeList from './body-contents/EmployeeList';
import EmployeeLookup from './body-contents/EmployeeLookup';
import UnequallyPaid from './body-contents/UnequallyPaid';


class NavigationContent extends React.Component {
    render() {
        return (
            <Switch>
                <Route exact path="/"><Redirect to="/Home" /></Route>
                <Route exact path="/Home" component={Home}/>
                <Route exact path="/employee-list" component={EmployeeList}/>
                <Route path="/employee-lookup" component={EmployeeLookup}/>
                <Route path="/unequally-paid" component={UnequallyPaid}/>
                
            </Switch>
        );
    }
}

export default NavigationContent;
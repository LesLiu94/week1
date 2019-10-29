import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";
import Home from './body-contents/Home';
import EmployeeList from './body-contents/EmployeeList';

class NavigationContent extends React.Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/">
                        <Home />
                    </Route>
                    <Route path="/employee-list">
                        <EmployeeList />
                    </Route>
                    <Route path="/employee-lookup">
                        {/* <EmployeeLookup /> */}
                    </Route>
                    <Route path="/unequally-paid">
                        {/* <UnequallyPaid /> */}
                    </Route>
                </Switch>
            </Router>
        );
    }
}

export default NavigationContent;
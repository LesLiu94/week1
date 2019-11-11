import React from "react";


class UnequallyPaid extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: []
        };
    }
    render() {
        return (
            <div>
                <h1>UnequallyPaid Employees:</h1>
                <div className="card-deck"> 
                    <div className="card">
                        <ul className="list-group">
                            {
                                this.state.employees.map((employee, index)=>{
                                    return (
                                        <li key={index} className="list-group-item card border border-dark">
                                            <div className="card-header">
                                                    Name: {employee.firstName} {employee.lastName}
                                            </div>
                                            <br/>
                                            <div className="card-body">
                                                    Salary: {employee.salary}
                                                    <br/> Hire Date: {employee.hireDate}
                                                    <br/> Birth Date: {employee.birthDate}
                                            </div>
                                        </li>
                                    )
                                })
                            }
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
    componentDidMount() {
        fetch('http://localhost:8080/api/PayLookup/unequalEmployees')
        .then(res => res.json())
        .then((data) => {
          this.setState({employees: data});
        })
        .catch(console.log)
    }
}

export default UnequallyPaid;
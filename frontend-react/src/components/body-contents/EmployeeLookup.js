import React from "react";

const API = 'http://localhost:8080/api/EmployeeLookup/findEmployee';
class EmployeeLookup extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            form: {
                firstName: '',
                lastName: '',
                dob: ''
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const name = event.target.name;
        let newState = Object.assign({}, this.state);
        newState.form[name] = event.target.value;
        this.setState(newState);
    }
    
    handleSubmit(event) {
        event.preventDefault();
        var params = 
            '?first=' + this.state.form.firstName +
            '&last=' + this.state.form.lastName + 
            '&dobString='+ this.state.form.dob;

        var url = API + params;

        fetch(url)
        .then(res => res.json())
        .then((data) => {
          this.setState(data);
        })
        .catch(console.log)
    }

    render() {
        return (
            <div>
                <div className="card border-info mb-3">
                <h1 className="card-header">Employee Lookup</h1>
                <div className="card-body">
                    <p className="card-text" >Enter the details below to lookup an employee.</p>
                    <form onSubmit={this.handleSubmit}>
                        <label> First Name:</label> &nbsp;
                        <input  type="text" name="firstName" placeholder="John" value={this.state.form.firstName} onChange={this.handleChange}/>
                        <br/>
                        <label >Last Name:</label> &nbsp;
                        <input  type="text" name="lastName" placeholder="Doe" value={this.state.form.lastName} onChange={this.handleChange}/>
                        <br/>
                        <label className="lastItem" >Date of Birth:</label> &nbsp;
                        <input type="date" name="dob" placeholder="yyyy-mm-dd" value={this.state.form.dob} onChange={this.handleChange}/>
                        <br/>
                        <input type="submit" className="lastItem btn btn-secondary btn-lg" value="Submit"/>
                    </form>
                </div>
                </div>
                <br/>
                <br/>
                <div className="card border-info mb-3">
                    <h1 className="card-header">Lookup Result:</h1>
                    <div className="card-body">
                        <h2 className="firstItem">Name: {this.state.firstName} {this.state.lastName} </h2>
                        <br/>
                        <h4>Department(s) = {this.state.departments} </h4>
                        <br/>
                        <h4>Title = {this.state.employeeTitle} </h4>
                        <br/>
                        <h4>Salary = $ {this.state.salary} </h4>
                        <br/>
                    </div>
                </div>
                <br/>
            </div>
        );
    }
}

export default EmployeeLookup;
import React from "react";
import { MDBDataTable } from 'mdbreact';


class EmployeeList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: []
        };
    }
    render() {
        const data = {
            columns: [
              {
                label: 'First Name',
                field: 'firstName',
                sort: 'asc',
                width: 100
              },
              {
                label: 'Last Name',
                field: 'lastName',
                sort: 'asc',
                width: 100
              },
              {
                label: 'Date of Birth',
                field: 'dob',
                sort: 'asc',
                width: 100
              },
              {
                label: 'Job Title',
                field: 'employeeTitle',
                sort: 'asc',
                width: 100
              },
              {
                label: 'Salary',
                field: 'salary',
                sort: 'asc',
                width: 100
              }
            ],
            rows: this.state.employees
        }
        return (
            <div>
                <MDBDataTable
                    striped
                    bordered
                    small
                    data={data}
                />
            </div>
        );
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/EmployeeListLookup/allEmployees')
        .then(res => res.json())
        .then((data) => {
          this.setState({employees: data});
        })
        .catch(console.log)
    }
}

export default EmployeeList;
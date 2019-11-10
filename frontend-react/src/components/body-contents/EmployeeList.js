import React from "react";

class EmployeeList extends React.Component {
    
    render() {
        return (
            <div>
                EmployeeList
            </div>
        );
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/EmployeeListLookup/allEmployees')
        .then(res => res.json())
        .then((data) => {
          this.setState({ contacts: data })
        })
        .catch(console.log)
    }
}

export default EmployeeList;
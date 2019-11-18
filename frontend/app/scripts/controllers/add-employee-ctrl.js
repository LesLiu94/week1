'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:AddEmployeeCtrl
 * @description
 * # AddEmployeeCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('AddEmployeeCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.title = "Add Employee";
    $scope.addEmployeeForm = {
      fname: '',
      lname: '',
      dob: '',
      title: '',
      salary: '',
      hdate: '',
      gender: '',
      fdate: '',
      tdate: ''
    }
    $scope.addEmployee = function(addEmployeeCriteria) {
      $http({
        method: 'POST',
        url: "http://localhost:8080/api/AddEmployee/employee",
        data: {
            departments: "",
            firstName: addEmployeeCriteria.firstName,
            lastName: addEmployeeCriteria.lastName,
            dob: addEmployeeCriteria.dob,
            employeeTitle: addEmployeeCriteria.employeeTitle,
            salary: addEmployeeCriteria.salary,
            hireDate: addEmployeeCriteria.hdate,
            gender: addEmployeeCriteria.gender,
            fromDate: addEmployeeCriteria.fdate,
            toDate: addEmployeeCriteria.tdate
        }
      })
    }
  }]);
  


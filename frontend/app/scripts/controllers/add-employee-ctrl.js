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
    $scope.validateForm = function(addEmployeeCriteria) {
      if(addEmployeeCriteria.fname == null || addEmployeeCriteria.fname == "" || addEmployeeCriteria.lname == null || addEmployeeCriteria.lname == "" || 
      addEmployeeCriteria.dob == null || addEmployeeCriteria.dob == "" || addEmployeeCriteria.title == null || addEmployeeCriteria.title == "" ||
      addEmployeeCriteria.salary == null || addEmployeeCriteria.salary == "" || addEmployeeCriteria.hdate == null || addEmployeeCriteria.hdate == "" ||
      addEmployeeCriteria.gender == null || addEmployeeCriteria.gender == "" || addEmployeeCriteria.tdate == null ||addEmployeeCriteria.tdate == "" ||
      addEmployeeCriteria.fdate == null || addEmployeeCriteria.fdate == ""){
        alert("Please fill out all required fields.")
        return;
      }
      $scope.addEmployee(addEmployeeCriteria);
    }
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
            departments: [],
            firstName: addEmployeeCriteria.fname,
            lastName: addEmployeeCriteria.lname,
            dob: addEmployeeCriteria.dob,
            employeeTitle: addEmployeeCriteria.title,
            salary: addEmployeeCriteria.salary,
            hireDate: addEmployeeCriteria.hdate,
            gender: addEmployeeCriteria.gender,
            fromDate: addEmployeeCriteria.fdate,
            toDate: addEmployeeCriteria.tdate
        }
      })
      $('#addEmployeeModal').modal('hide');
      $('.modal-backdrop').remove();
    }
  }]);
  


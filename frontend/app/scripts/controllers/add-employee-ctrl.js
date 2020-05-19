'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:AddEmployeeCtrl
 * @description
 * # AddEmployeeCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('AddEmployeeCtrl', ['$scope', '$http', '$timeout', function ($scope, $http, $timeout) {
    $scope.title = "Add Employee";
    $scope.titles = ["EMPLOYEE", "MANAGER", "JANITOR", "NONE"];
    $scope.theDepartments = ["PRODUCTION AND OPERATIONS", "RESEARCH AND DEVELOPMENT", "PURCHASING", "MARKETING", "HUMAN RESOURCES", "ACCOUNTING AND FINANCE"];
    $scope.validateForm = function(addEmployeeCriteria) {
      if(addEmployeeCriteria.fname == null || addEmployeeCriteria.fname == "" || addEmployeeCriteria.lname == null || addEmployeeCriteria.lname == "" || 
      addEmployeeCriteria.dob == null || addEmployeeCriteria.dob == "" || addEmployeeCriteria.title == null || addEmployeeCriteria.title == "" ||
      addEmployeeCriteria.departments == null || addEmployeeCriteria.departments == "" || addEmployeeCriteria.salary == null || addEmployeeCriteria.salary == "" 
      || addEmployeeCriteria.hdate == null || addEmployeeCriteria.hdate == "" || addEmployeeCriteria.gender == null || addEmployeeCriteria.gender == "" 
      || addEmployeeCriteria.tdate == null ||addEmployeeCriteria.tdate == "" || addEmployeeCriteria.fdate == null || addEmployeeCriteria.fdate == ""){
        alert("Please fill out all required fields.")
        return;
      }

      addEmployeeCriteria.dob = $scope.formatDate(addEmployeeCriteria.dob);
      addEmployeeCriteria.hdate = $scope.formatDate(addEmployeeCriteria.hdate);
      addEmployeeCriteria.fdate = $scope.formatDate(addEmployeeCriteria.fdate);
      addEmployeeCriteria.tdate = $scope.formatDate(addEmployeeCriteria.tdate);
      $scope.addEmployee(addEmployeeCriteria);
    }
    $scope.addEmployeeForm = {
      fname: "Apple",
      lname: "Banana",
      dob: '',
      title: "EMPLOYEE",
      departments: "MARKETING",
      salary: '',
      hdate: '',
      gender: 'M',
      fdate: '',
      tdate: ''
    }
    $scope.addEmployee = function(addEmployeeCriteria) {
      $http({
        method: 'POST',
        url: "http://localhost:8080/api/AddEmployee/employee",
        data: {
            firstName: addEmployeeCriteria.fname,
            lastName: addEmployeeCriteria.lname,
            dob: addEmployeeCriteria.dob,
            employeeTitle: addEmployeeCriteria.title,
            departments: [addEmployeeCriteria.departments],
            salary: addEmployeeCriteria.salary,
            hireDate: addEmployeeCriteria.hdate,
            gender: addEmployeeCriteria.gender,
            fromDate: addEmployeeCriteria.fdate,
            toDate: addEmployeeCriteria.tdate
        }
      }).then(function(response){
        $scope.successMessage = "Form submitted successfully and employee added !";
                $scope.successMessagebool = true;
                $timeout(function () {
                    $scope.successMessagebool = false;}, 5000);
      })
      $('#addEmployeeModal').modal('hide');
      $('.modal-backdrop').remove()
      ;
    }

    $scope.formatDate = function(date) {
      var fixedDate;
      fixedDate = date.substring(5,7) + '/' + date.substring(8,10) + '/' + date.substring(0,4);
      return fixedDate;
    }

  }]);
  


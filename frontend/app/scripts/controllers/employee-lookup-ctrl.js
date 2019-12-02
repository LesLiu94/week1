'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:EmployeeLookupCtrl
 * @description
 * # EmployeeLookupCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('EmployeeLookupCtrl', ['$scope', '$http', 'NgTableParams', function ($scope, $http, NgTableParams) {
    $scope.title = "Employee Lookup";
    $scope.titles = [
      "EMPLOYEE",
      "MANAGER",
      "JANITOR",
      "NONE"
    ]
    $scope.lookupResult = false;
    $scope.employeeLookupForm = {
        fname: '',
        lname: ''
    }
    $scope.lookup = function(employeeSearchCriteria) {
        $http({
            method: 'GET',
            url: "http://localhost:8080/api/EmployeeLookup/findEmployee",
            params: {
                first: employeeSearchCriteria.fname, 
                last: employeeSearchCriteria.lname
            }
        }).then(function(response){
            $scope.lookupResult = true;
            $scope.employeeLookupList = response.data.sort(function(a,b)
            { 
              if(a.firstName.localeCompare(b.firstName)==0){
                return a.lastName.localeCompare(b.lastName);
              }
              return a.firstName.localeCompare(b.firstName)});
            $scope.employeeLookupTable = new NgTableParams({
                count: 5
            },{
                dataset: $scope.employeeLookupList,
                counts:[]
            })
        })
    };
    $scope.editEmployee = function(employee){
      $scope.selected_employee = employee;
      angular.element('#editModal').modal();
      $('#editFirstName').attr('value',employee.firstName);
      $('#editLastName').attr('value',employee.lastName);
      $('#editTitle').attr('value',employee.employeeTitle);
      $('#editSalary').attr('value',employee.salary);
    }
    $scope.updateEmployee = function(updateParams, employeeNum){
      $http({
        method: 'PUT',
        url: "http://localhost:8080/api/Edit/employee",
        header: "Access-Control-Allow-Origin: http://localhost:9000/", 
        params: {
            firstName : updateParams.fname,
            lastName : updateParams.lname,
            title : updateParams.title,
            salary : updateParams.salary,
            empNo : employeeNum
        }
      }).then(function(response){
        if(response.data)
          $scope.msg = "Successfully editted employee!";
          $scope.successEmployee = reponse.data;
          $('.toast').toast("show");
      })
      
    }
  }]);
  


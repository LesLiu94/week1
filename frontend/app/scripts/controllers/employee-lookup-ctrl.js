'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:EmployeeLookupCtrl
 * @description
 * # EmployeeLookupCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('EmployeeLookupCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.title = "Employee Lookup";
    $scope.employeeLookupForm = {
        fname: '',
        lname: '',
        dob: ''
    }
    $scope.lookup = function(employeeSearchCriteria) {
        $http({
            method: 'GET',
            url: "http://localhost:8080/api/EmployeeLookup/findEmployee",
            params: {
                first: employeeSearchCriteria.fname, 
                last: employeeSearchCriteria.lname, 
                dobString: employeeSearchCriteria.dob
            }
        }).then(function(response){
            $scope.firstName = response.data.firstName;
            $scope.lastName = response.data.lastName;
            $scope.departments = response.data.departments;
            $scope.employeeTitle = response.data.employeeTitle;
            $scope.salary = response.data.salary;
        })
    };
  }]);
  


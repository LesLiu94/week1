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
  }]);
  


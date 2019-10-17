'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:EmployeeLookupCtrl
 * @description
 * # EmployeeLookupCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('EmployeeLookupCtrl', ['$scope', function ($scope) {
    $scope.title = "Employee Lookup";
    $scope.lookup = ('$scope', '$http', function($scope, $http) {
        $http({
            method: 'GET',
            url: "http://localhost:8080/api/EmployeeLookup/findEmployee",
            params: {first: fname, last: lname, dobstring: dob}
        }).then(function(response){
            $scope.employeeTitle = response.employeeTitle;
            
        })
    });
    $scope.lookup();
  }]);
  


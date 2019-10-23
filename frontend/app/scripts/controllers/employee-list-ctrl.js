'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:EmployeeListCtrl
 * @description
 * # EmployeeListCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('EmployeeListCtrl', ['$scope', 'employeeList', function ($scope, employeeList) {
    $scope.title = "Employee List";
    $scope.employeeList = employeeList.sort(function(a,b)
    { 
      if(a.firstName.localeCompare(b.firstName)==0){
        return a.lastName.localeCompare(b.lastName);
      }
      return a.firstName.localeCompare(b.firstName)});
  }]);
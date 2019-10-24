'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:EmployeeListCtrl
 * @description
 * # EmployeeListCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('EmployeeListCtrl', ['$scope', 'employeeList','NgTableParams', function ($scope, employeeList, NgTableParams) {
    $scope.title = "Employee List";
    //Sort employeeList by firstName and if they are the same, sort by lastName
    $scope.employeeList = employeeList.sort(function(a,b)
    { 
      if(a.firstName.localeCompare(b.firstName)==0){
        return a.lastName.localeCompare(b.lastName);
      }
      return a.firstName.localeCompare(b.firstName)});
    $scope.employeeTable = new NgTableParams({
      count: 5
    },{ 
      dataset: $scope.employeeList,
      counts: []});  
  }]);
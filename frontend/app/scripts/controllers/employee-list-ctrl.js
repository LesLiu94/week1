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
    $scope.employeeList = employeeList;
  }]);
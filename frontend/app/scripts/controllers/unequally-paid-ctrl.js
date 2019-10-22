'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:UnequallyPaidCtrl
 * @description
 * # EmployeeListCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('UnequallyPaidCtrl',['$scope', 'unequalList', function($scope, unequalList){
    $scope.title = "Unequally Paid Employee(s)";
    $scope.unequalList = unequalList;
  }]);

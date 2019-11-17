'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:AddEmployeeCtrl
 * @description
 * # AddEmployeeCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('AddEmployeeCtrl', ['$scope', '$uibModal', function ($scope, $uibModal) {
    $scope.title = "Add Employee";
    $scope.open = function () {
        var modalInstance = $uibModal.open({
            templateUrl: 'addEmployeeModalContent.html',
            controller: 'AddEmployeeModalInstanceCtrl'
        })
    }
  }]);
  


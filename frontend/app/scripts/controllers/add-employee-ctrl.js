'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:AddEmployeeCtrl
 * @description
 * # AddEmployeeCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('AddEmployeeCtrl', ['$scope', '$modal', function ($scope, $modal) {
    $scope.title = "Add Employee";
    $scope.open = function () {
        var modalInstance = $modal.open({
            templateUrl: 'addEmployeeModalContent.html',
            controller: 'AddEmployeeModalInstanceCtrl'
        })
    }
  }]);
  


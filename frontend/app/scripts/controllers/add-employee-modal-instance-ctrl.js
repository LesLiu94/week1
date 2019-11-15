'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:AddEmployeeModalInstanceCtrl
 * @description
 * # AddEmployeeModalInstanceCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('AddEmployeeModalInstanceCtrl', ['$scope', '$modal', function ($scope, $modal) {
    
    $scope.ok = function(){
        $modalInstance.close();
    }
    $scope.cancel = function(){
        $modalInstance.dismiss('cancel');
    }
  }]);
  


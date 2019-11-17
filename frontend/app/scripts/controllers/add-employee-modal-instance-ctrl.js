'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:AddEmployeeModalInstanceCtrl
 * @description
 * # AddEmployeeModalInstanceCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('AddEmployeeModalInstanceCtrl', ['$scope', '$uibModalInstance', '$http', function ($scope, $uibModalInstance, $http) {
    $scope.ok = function(){
        $ubiModalInstance.close();
    }
    $scope.cancel = function(){
        $ubiModalInstance.dismiss('cancel');
    }

    $scope.addEmployee = function(addEmployeeCriteria) {
      $http({
        method: 'POST',
        url: "http://localhost:8080/api/AddEmployee/employee",
        
      })
    }

  }]);
  


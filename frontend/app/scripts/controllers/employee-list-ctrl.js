'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:EmployeeListCtrl
 * @description
 * # EmployeeListCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('EmployeeListCtrl', ['$scope', 'employeeList','NgTableParams', '$http', '$route', function ($scope, employeeList, NgTableParams, $http, $route) {
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

    $scope.fireEmployee = function(employee){
      if (confirm("Are you sure you want to delete this employee?"))
      {
        $http({
          method: 'PUT',
          url: "http://localhost:8080/api/Fire/employee/" + employee.empNo,
        }).then(function(response){
          if(response.data) {
            //TODO Call Employee List to refresh the data.
            $route.reload();
          }
        })
      }
    }
  }]);
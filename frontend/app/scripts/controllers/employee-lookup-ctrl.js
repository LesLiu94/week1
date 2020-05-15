'use strict';

/**
 * @ngdoc function
 * @name employeeProjectApp.controller:EmployeeLookupCtrl
 * @description
 * # EmployeeLookupCtrl
 * Controller of the employeeProjectApp
 */
angular.module('employeeProjectApp')
  .controller('EmployeeLookupCtrl', ['$scope', '$http', 'NgTableParams', function ($scope, $http, NgTableParams) {
    $scope.title = "Employee Lookup";
    $scope.lookupResult = false;
    $scope.employeeLookupForm = {
        fname: '',
        lname: ''
    }
    $scope.lookup = function(employeeSearchCriteria) {
        $http({
            method: 'GET',
            url: "http://localhost:8080/api/EmployeeLookup/findEmployee",
            params: {
                first: employeeSearchCriteria.fname, 
                last: employeeSearchCriteria.lname
            }
        }).then(function(response){
            $scope.lookupResult = true;
            $scope.employeeLookupList = response.data.sort(function(a,b)
            { 
              if(a.firstName.localeCompare(b.firstName)==0){
                return a.lastName.localeCompare(b.lastName);
              }
              return a.firstName.localeCompare(b.firstName)});
            $scope.employeeLookupTable = new NgTableParams({
                count: 5
            },{
                dataset: $scope.employeeLookupList,
                counts:[]
            })
        })
    };
    $scope.editEmployee = function(employee){
      $scope.selected_employee = employee;
      $('#editModal').modal();
      $scope.updateForm= {};
      $scope.updateForm.fname = employee.firstName;
      $scope.updateForm.lname = employee.lastName;
      $scope.updateForm.title = employee.employeeTitle;
      $scope.updateForm.salary = employee.salary;
      $scope.updateForm.empNo = employee.empNo;
      $scope.updateForm.hdate = employee.hireDate;
      $scope.updateForm.fdate = employee.fromDate;
      $scope.updateForm.tdate = employee.toDate;
    }

    $scope.updateEmployee = function(updateParams){
      $http({
        method: 'PUT',
        url: "http://localhost:8080/api/Edit/employee",
        data: {
            firstName : updateParams.fname,
            lastName : updateParams.lname,
            title : updateParams.title,
            salary : updateParams.salary,
            empNo : updateParams.empNo,
            hireDate : $scope.formatDate(updateParams.hdate),
            fromDate : $scope.formatDate(updateParams.fdate),
            toDate : $scope.formatDate(updateParams.tdate)
        }
      }).then(function(response){
        if(response.data) {
          $('#editModal').modal('hide');
          $scope.lookup($scope.employeeLookupForm);
          $scope.msg = "Successfully editted employee!";
          $scope.successEmployee = response.data;
          $('.toast').toast("show");
        }
      })
      
    }

    //this is to attempt to fix the 1 day off from the correct date as well as reformat from mm/dd/yyyy to yyyy-mm-dd
    $scope.formatDate = function(date) {
      var fixedDate;
      var day = Number(date.substring(3,5)) + 1;
      var month = Number(date.substring(0,2));
      var year = Number(date.substring(6));

      if(day<10){
        day = '0' + day;
      }
      else if(day > 30){
        //checking to see if it is a month that allows for 31 days
        if(month == 1 || month == 3 || month == 5 || month == 7 || month  == 8 || month == 10 || month == 12){
          if(day > 31){
            day = '0' + 1;
            month = month + 1;
            if(month > 12){
              year = year + 1;
              month = 1;
            }
          }
        }
        else{
          month = month + 1;
          if(month > 12){
            year = year + 1;
            month = 1;
          }
        }
      }

      if(month<10){
        month = '0' + month;
      }

      fixedDate = year + '-' + month + '-' + day;
      return fixedDate;
    }

  }]);
  


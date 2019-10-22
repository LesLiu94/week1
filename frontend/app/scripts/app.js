'use strict';

/**
 * @ngdoc overview
 * @name employeeProjectApp
 * @description
 * # employeeProjectApp
 *
 * Main module of the application.
 */
angular
  .module('employeeProjectApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
    $httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = '*';
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/employee-list', {
        templateUrl: 'views/employee-list.html',
        controller: 'EmployeeListCtrl',
        resolve: {
          employeeList: ['$http', function($http){
            return $http.get("http://localhost:8080/api/EmployeeListLookup/allEmployees").then(function(response){
              return response.data;
            })
          }]
        }
        }
      )
      .when('/employee-lookup', {
        templateUrl: 'views/employee-lookup.html',
        controller: 'EmployeeLookupCtrl'
      })
      .when('/unequally-paid', {
        templateUrl: 'views/unequally-paid.html',
        controller: 'UnequallyPaidCtrl',
        resolve: {
          unequalList: ['$http', function($http){
            return $http.get("http://localhost:8080/api/PayLookup/unequalEmployees").then(function(response){
              return response.data;
            })
          }]
        }
        }
      )
      .otherwise({
        redirectTo: '/'
      });
  }]);

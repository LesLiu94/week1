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
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/employeeList', {
        templateUrl: 'views/employeeList.html',
        controller: 'EmployeeListCtrl', function($scope){
          $scope.title = 'EmployeeList';
        }
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);

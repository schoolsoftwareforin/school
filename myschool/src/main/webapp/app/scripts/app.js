'use strict';

angular.module('myschoolapp', ['ngRoute', 'ngResource'])
.controller('LandingPageController', function LandingPageController($scope, MemberResource) {
  $scope.results = MemberResource.queryAll();
});
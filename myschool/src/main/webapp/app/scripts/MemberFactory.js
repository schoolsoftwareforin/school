angular.module('myschoolapp').factory('MemberResource', function ($resource) {
  var resource = $resource('http://localhost:8080/myschool/members/:MemberId', {MemberId: '@id'}, {
    'queryAll': {method: 'GET', isArray: true}, 'query': {method: 'GET', isArray: false}, 'update': {method: 'PUT'}
  });
  return resource;
});
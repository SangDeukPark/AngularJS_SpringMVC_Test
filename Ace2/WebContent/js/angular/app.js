  var app = angular.module('Admin', ['ngRoute']);
 
  app.config(function($routeProvider) {
	  $routeProvider.when('/list',
	  {
	    controller: 'AdminInfoController',
	    templateUrl: '/view/adminInfo/adminInfoList.html'
	  }).otherwise(
	    {
	      redirectTo: '/list'
	    });
	});
  
  	app.controller('AdminInfoController', ['$scope', '$http', '$location', function($scope, $http, $location) {
		  $scope.lists = [];
			var res = $http.get('/adminInfo/adminInfoList');			
			res.success(function(data, status, headers, config) {
				console.log(data);
				$scope.lists = data;				
			});
			res.error(function(data, status, headers, config) {
				console.log( "failure message: " + JSON.stringify({data: data}));
			});
	  }]);
  



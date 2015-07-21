  var app = angular.module('Admin', ['ngRoute']);
 
    app.config(function($routeProvider) {
	  $routeProvider.when('/list',
	  {
	    controller: 'ListController',
	    templateUrl: '/view/adminInfo/adminInfoList.html'
	  }).when('/add',
	    {
		    controller: 'AddController',
		    templateUrl: '/view/adminInfo/adminInfoReg.html'
		  }).otherwise(
	    {
	      redirectTo: '/list'
	    });
	});
    
    app.factory('PaginatonService', function($rootScope) {
	     return {
	  	       Pagination: function(maxCount, $rootScope) {
	  	         return new Pagination(maxCount);
	  	       }
	  	     };
	  	});
  
  	app.controller('ListController', ['$scope', '$http', '$location', 'PaginatonService', function($scope, $http, $location, PaginatonService) {
		    $scope.lists = [];
		    
		    $scope.getList = function(pageIndex) {//
		    	console.log($scope.pagination.current() + "pageIndex : " + pageIndex);		    	
		    	
				var dataObj = {
					pageIndex : pageIndex
				};	
				
				// get Member List
				var res = $http.post('/adminInfo/adminInfoList', dataObj);			
				res.success(function(data, status, headers, config) {
					console.log(data);
					$scope.lists = data;
				});
				res.error(function(data, status, headers, config) {
					console.log( "failure message: " + JSON.stringify({data: data}));
				});
				
				// get Total Count
				res = $http.post('/adminInfo/adminInfoTotal', dataObj);
				res.success(function(data, status, headers, config) {
					console.log("Total : " + data);
					$scope.lists = data;				
				});
				res.error(function(data, status, headers, config) {
					console.log( "failure message: " + JSON.stringify({data: data}));
				});
				
				$scope.pagination.setCurrent(pageIndex);
				console.log($scope.pagination.current() + "current : ");		    	
		    	
		    };
		    
			$scope.pagination = PaginatonService.Pagination(10);
	  }]);
  	
  	var Pagination = function(maxCount, $rootScope) {
	    this.maxCount = maxCount;
	    this.counter = 1;
	     
	    this.max = function() {
	        return this.maxCount;
	    };
	    this.setCurrent = function(counter) {
	        this.counter = counter;
	    };
	    this.current = function() {
	        return this.counter;
	    };
	    this.next = function() {
	        if (this.hasNext()) {
	            this.counter++;
	            //$rootScope.broadcast("pagination:next");
	        }
	    };
	    this.previous = function() {
	        if (this.hasPrevious()) {
	            this.counter--;
	            //$rootScope.broadcast("pagination:previous");
	        }
	    };
	    this.hasPrevious = function() {
	        return this.counter > 1;
	    };
	    this.hasNext = function() {
	        return this.counter < this.maxCount;
	    };
	};  	


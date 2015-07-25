  var app = angular.module('Admin', ['ngRoute']);
  
  app.run(function($rootScope, $location) {
	    $rootScope.location = $location;
	});
 
    app.config(function($routeProvider) {
	  $routeProvider.when('/list',
	  {
	    controller: 'ListController',
	    templateUrl: '/view/adminInfo/adminInfoList.html'
	  }).when('/add',
	    {
		    controller: 'AddController',
		    templateUrl: '/view/adminInfo/adminInfoReg.html'
		  }).when('/edit/:id',
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
  	
  	var Pagination = function(totalRecord, maxPageCount, $rootScope) {
	    this.maxPageCount = maxPageCount;
	    this.totalRecord = totalRecord;
	    this.counter = 1;
	     
	    this.max = function() {
	        return this.maxPageCount;
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
	        return (this.counter < this.maxPageCount)
	        	&& (this.counter * 10) > total;
	    };
	};  	
	
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
		
		$scope.deleteRecord = function(id) {//
			if(confirm('Will you delete this?')) {
				console.log(id);
				var res = $http.get('/adminInfo/deleteAdminInfo/' + id);
				res.success(function(data, status, headers, config) {
					console.log(data);	
					$scope.lists = [];
					$scope.getList($scope.pagination.current());
				});
				res.error(function(data, status, headers, config) {
					console.log( "failure message: " + JSON.stringify({data: data}));
				});
			}
		};
  }]);	
	
	app.controller('AddController', ['$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
		$scope.title = "Add";
		
	  $scope.add = function() {
 			var dataObj = {
					email : $scope.email,
					password : $scope.password,
					name : $scope.name,
					mobileNumber : $scope.mobileNumber,					
			};	
 			console.log(dataObj);
 			
 			
			var res = $http.post('/adminInfo/add/' + $scope.title + '/' + $routeParams.id, dataObj);			
			res.success(function(data, status, headers, config) {
				console.log(data);
				$location.path('/list');
			});
			res.error(function(data, status, headers, config) {
				console.log( "failure message: " + JSON.stringify({data: data}));
			});		 
	    };
	    
	    // Call User Data
	  if($routeParams.id) {
		  
			var res = $http.get('/adminInfo/adminInfoView/' + $routeParams.id);	
			res.success(function(data, status, headers, config) {
				console.log(data);
				$scope.email = data.email;
				$scope.name = data.name;
				$scope.mobileNumber = data.mobileNumber;
				$scope.title = "Edit";
			});
			res.error(function(data, status, headers, config) {
				console.log( "failure message: " + JSON.stringify({data: data}));
			});			  
	  }
	}]);



var app = angular.module("myApp", []);
app.controller("myController", function($scope) {
	$scope.collection = [
		{name:'abc', roll:20},
		{name:'xyz', roll:21}
	];
	$scope.addEntry = function() {
		$scope.collection.push($scope.newData);
		$scope.newData = {};
	}
});
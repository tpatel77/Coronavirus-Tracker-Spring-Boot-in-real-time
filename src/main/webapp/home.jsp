<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
    
<html>

<head>
    <title>Coronavirus Tracker Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
</head>

<body>
<div class="container">
<h1>Coronavirus Tracker Application</h1>
<p>This application lists the current number of cases reported across the globe</p>

<div class="jumbotron">
    <h1 class="display-4">${totalReportedCases}</h1>
    <p class="lead">Total cases reported as of today</p>
    <hr class="my-4">
    <p>
        <span>New cases reported since previous day:</span>
        <span>${totalNewCases}</span>
    </p>

</div>
<script>
var app = angular.module('myApp', []);
app.controller('corona', function($scope, $http) {
  $http.get("http://localhost:8085/json").then(function(response) {
    $scope.myData = response.data.records;
  });
});
</script>

    <table class="table" ng-app ="myApp" ng-controller="corona">
        <tr>
            <th>State</th>
            <th>Country</th>
            <th>Total cases reported</th>
            <th>Changes since last day</th>
        </tr>
        
        <tr ng-repeat="locationStat in locationStats">
        <td><p>{{locationStat.state}}</p></td>
        <td><p>{{locationStat.country}}</p></td>
        <td><p>{{locationStat.latestTotalCases}}</p></td>
        <td><p>{{locationStat.diffFromPrevDay}}</p></td>
      
      </tr>
      
        
    </table>
</div>
</body>

</html>
    
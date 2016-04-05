'use strict';

angular.module('jasApp').controller(
		'CreateStokvelController',
		function($scope, $filter, BanksService, Stokvel) {
			$scope.success = null;
			$scope.error = null;

			$scope.errorStokvelExists = false;
			
			$scope.stokvel = {};

			$scope.searchTerm = "";
			
			//Need to put this in a db table and create a resource to get the list, Create an issue for this
			
			$scope.stokvelTypes = [ {
				name : 'Funeral Savings',
				field : 'funeral'
			}, {
				name : 'Monthly',
				field : 'monthly'
			}, {
				name : 'Savings',
				field : 'savings'
			} ];

			//Need to put this in a db table and create a resource to get the list
			
			$scope.paymentFrequencies = [ {
				name : 'Monthly',
				field : 'monthly'
			}, {
				name : 'Quarterly',
				field : 'quarterly'
			}, {
				name : 'Bi-Annually',
				field : 'biannually'
			} ];
			
			//Need to put this in a db table and create a resource to get the list

			$scope.privacyOptions = [ {
				name : 'Private',
				field : 'private'
			}, {
				name : 'Open to public',
				field : 'open'
			} ];

			$scope.contributionDates = [ {
				name : '1st of every month',
				field : '1'
			}, {
				name : '2nd of every month',
				field : '2'
			} ];

			
			//Need to put this in a db table and create a resource to get the list
			$scope.accountTypes = [ {
				name : 'Current/Cheque',
				field : 'current/cheque'
			}, {
				name : 'Savings',
				field : 'savings'
			}, {
				name : 'Transmission',
				field : 'transmission'
			}, {
				name : 'Bond/Mortgage',
				field : 'bond/mortgage'
			} ];


			$scope.onChangeDate = function() {
				var dateFormat = 'yyyy-MM-dd';
				var startDate = $filter('date')($scope.startDate, dateFormat);
			};

			$scope.today = function() {
				var today = new Date();
			/*	$scope.toDate = new Date(today.getFullYear(), today.getMonth(),
						today.getDate() + 1); */
			};

			$scope.previousMonth = function() {
				var startDate = new Date();
				if (startDate.getMonth() === 0) {
					startDate = new Date(startDate.getFullYear() - 1, 0,
							startDate.getDate());
				} else {
					startDate = new Date(startDate.getFullYear(), startDate
							.getMonth() - 1, startDate.getDate());
				}

				$scope.startDate = startDate;
			};

			$scope.today();
			$scope.previousMonth();
			$scope.onChangeDate();

			BanksService.findAll().then(function(data) {
				$scope.banks = data;
			});
			
	        $scope.save = function () {
	        	
	        	Stokvel.createStokvel($scope.stokvel).then(function () {
                    $scope.success = 'OK';
                }).catch(function (response) {
                    $scope.success = null;
                    if (response.status === 400 && response.data === 'stokvel already exists') {
                        $scope.errorStokvelExists = 'ERROR';
                    } else if (response.status === 400 && response.data === 'stokvel name already in use') {
                        $scope.errorStokvelExists = 'ERROR';
                    } else {
                        $scope.error = 'ERROR';
                    }
                });
	        	
	        }

		});

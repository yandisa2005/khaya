'use strict';

angular.module('jasApp')
    .controller('LoginController', function ($rootScope, $scope, $state, $timeout, Auth) {
        $scope.user = {};
        $scope.errors = {};

        $scope.rememberMe = true;
        $timeout(function (){angular.element('[ng-model="username"]').focus();});
        $scope.login = function (event) {
            console.log( ' *********************in login controller');
            event.preventDefault();
            Auth.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;
               /* if ($rootScope.previousStateName === 'register') {
                    $state.go('home');
                } else {
                    $rootScope.back();
                }
                */
                $scope.authenticationError = false;
                console.log($rootScope.requestUrl + 'herererere ' +   $rootScope.previousStateName);
                console.log($rootScope.toState.name + ' *********************');
                if ($rootScope.requestUrl !== "") {
                    Auth.redirectToAttemptedUrl($rootScope.requestUrl);
                    $rootScope.requestUrl = "";
                }
                else if ($rootScope.toState.name === "login") {
                    $state.go('home');
                }
                else {
                    $state.go($rootScope.toState, {uuid: $rootScope.toStateParams.uuid});
                }
            }).catch(function () {
                console.log(' **************Auth Error *******');
                $scope.authenticationError = true;
            });
        };
    });

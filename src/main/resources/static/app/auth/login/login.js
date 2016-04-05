(function () {
    'use strict';
    angular.module('jasApp')
        .controller('LoginCtrl', function ($rootScope, $scope, $state, $timeout, Auth, toaster) {
            $scope.user = {};
            $scope.errors = {};

            $scope.rememberMe = true;
            $timeout(function () {
                angular.element('[ng-model="username"]').focus();
            });
            $scope.login = function () {
                Auth.login({
                    username: $scope.username,
                    password: $scope.password,
                    rememberMe: $scope.rememberMe
                }).then(function () {
                    $scope.authenticationError = false;
                    if ($rootScope.requestUrl !== "") {
                        Auth.redirectToAttemptedUrl($rootScope.requestUrl);
                        $rootScope.requestUrl = "";
                    }
                    else if ($rootScope.toState.name === "login") {
                        $state.go('main.home');
                    }
                    else {
                        $state.go($rootScope.toState, {uuid: $rootScope.toStateParams.uuid});
                    }
                    toaster.pop("success", "Authentication", "Successfully logged in to ODIN");
                }).catch(function (err) {
                    $scope.authenticationError = true;
                    $scope.authMsg = "Invalid login, please check username/password.";
                    toaster.pop("error", "Authentication", "Auth failed, please try again");
                });
            };
        });
}());

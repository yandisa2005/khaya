(function () {
    'use strict';
    angular.module('jasApp')
        .controller('LogoutCtrl', function ($scope, Auth, $state) {
            Auth.logout();
            $state.go('main.home');
        });
}());

'use strict';

angular.module('jasApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });

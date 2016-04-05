'use strict';

angular.module('jasApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });



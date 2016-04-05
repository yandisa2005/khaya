'use strict';

angular.module('jasApp')
    .factory('StokvelCreate', function ($resource) {
        return $resource('stokvel/create', {}, {
        });
    });

angular.module('jasApp')
    .factory('StokvelEdit', function ($resource) {
        return $resource('stokvel/edit', {}, {
        })
    });


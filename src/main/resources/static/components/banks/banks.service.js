'use strict';

angular.module('jasApp')
    .factory('BanksService', function ($http) {
        return {
            findAll: function () {
                return $http.get('banks/all').then(function (response) {
                    return response.data;
                });
            }
        };
    });

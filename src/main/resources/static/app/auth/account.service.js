(function () {
    'use strict';
    angular.module('jasApp')
        .factory('Account', function Account(RestService, API_URL, $q) {
            return {
                get: function () {
                    var deferred = $q.defer();
                    RestService.get(API_URL + 'account', function (response) {
                        return deferred.resolve(response);
                    }, function (error) {
                        return $q.reject(error);
                    });

                    return deferred.promise;
                }
            };
        });
}());

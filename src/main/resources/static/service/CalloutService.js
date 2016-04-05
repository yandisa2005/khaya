(function () {
    'use strict';
    angular.module('jasApp')
        .factory('CalloutService', function Account($resource) {
            return $resource('callouts/Meridian/', {}, {
                'get': {
                    method: 'GET', params: {}, isArray: true,
                    interceptor: {
                        response: function (response) {
                            // expose response
                            return response;
                        }
                    }
                }
            });
        });
}());

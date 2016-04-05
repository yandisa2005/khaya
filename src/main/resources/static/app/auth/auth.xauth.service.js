(function () {
    'use strict';
    angular.module('jasApp')
        .factory('AuthServerProvider', function loginService(RestService, localStorageService, API_URL) {
            return {
                login: function (credentials) {
                    var data = "username=" + credentials.username + "&password=" + credentials.password;
                    return RestService.postAsForm(API_URL + 'authenticate', data, function (response) {
                            localStorageService.set('token', response);
                            return response;
                        });
                },
                logout: function () {
                    //Stateless API : No server logout
                    localStorageService.clearAll();
                },
                getToken: function () {
                    return localStorageService.get('token');
                },
                hasValidToken: function () {
                    var token = this.getToken();
                    return token && token.expires && token.expires > new Date().getTime();
                }
            };
        });
}());

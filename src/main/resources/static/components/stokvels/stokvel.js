'use strict';

angular.module('jasApp')
    .factory('Stokvel', function Stokvel($rootScope, $state, $q, StokvelCreate) {
        return {
            createStokvel: function (stokvel, callback) {
                var cb = callback || angular.noop;
                return StokvelCreate.save(stokvel,
                    function () {
                        return cb(stokvel);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            }
        };
    });

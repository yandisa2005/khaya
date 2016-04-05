/**
 * Created by livious on 2015/12/23.
 */
angular.module('jasApp').factory('ClientsFactory', function ($resource) {
    return $resource('/clients', {
            name: '@name',
            page: '@page',
            size: '@size',
            sort: '@sort',
            order:'@order'
        },
        {
            query: {
                method: 'GET',
                params: {},
                isArray: false
            }
        })
});

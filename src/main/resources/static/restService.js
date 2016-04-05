angular.module('jasApp').factory('EventFactory', function ($resource) {
    return $resource('/events', {
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

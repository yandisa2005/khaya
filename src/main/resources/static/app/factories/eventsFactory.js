angular.module('jasApp').factory('EventsFactory', function ($resource) {
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

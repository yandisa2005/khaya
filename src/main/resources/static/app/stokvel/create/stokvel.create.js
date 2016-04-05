'use strict';

angular.module('jasApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('create', {
                parent: 'stokvel',
                url: '/create/stokvel',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'Create Stokvel'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/stokvel/create/stokvel.create.html',
                        controller: 'CreateStokvelController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('create');
                        return $translate.refresh();
                    }]
                }
            });
    });

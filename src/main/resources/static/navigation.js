'use strict';
    angular.module('jasApp')
    .config(function ($stateProvider, $urlRouterProvider, $locationProvider) {

        $stateProvider.state('main', {
                url: '/main', templateUrl: 'app/main.html', abstract: true, controller: 'MainCtrl',
                //resolve: {
                //    authorize: ['Auth',
                //        function (Auth, Principal) {
                //            return Auth.authorize();
                //        }
                //    ]
                //}
            }
        );

        $stateProvider.state('main.home', {
            data: {
                roles: ['ROLE_USER', 'ROLE_ADMIN']
            },
            url: '/home',
            templateUrl: 'app/home/home.html',
            controller: 'HomeCtrl'
        });

        $stateProvider.state('main.login', {
                url: 'app/login',

                templateUrl: 'app/account/login/login.html',
                controller: 'LoginController'

            });

        $stateProvider.state('main.events', {
             url: 'app/events',

             templateUrl: 'app/events/events.html',
             controller: 'eventsController'

        });

        $stateProvider.state('main.clients', {
            url: 'app/clients',

            templateUrl: 'app/clients/clients.html',
            controller: 'clientsController'

        });

        $stateProvider.state('main.viewMessage', {
            url: 'app/msg',

            templateUrl: 'viewMessages.html',
            controller: 'msgController'

        });

        $stateProvider.state('main.register', {
            url: 'app/register',

            templateUrl: 'app/account/register/register.html',
            controller: 'RegisterController'

        });

        $stateProvider
            .state('logout', {
                parent: 'account',
                url: '/logout',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'app/main/main.html',
                        controller: 'LogoutController'
                    }
                }
            });


        console.log('*******************Home***************');
            //
            // Single Page Routes
            // -----------------------------------
            $stateProvider.state('page', {
                url: '/page',
                templateUrl: 'app/auth/page.html', abstract: true
            })
                .state('login', {
                    url: '/login',
                    parent: 'page',
                    title: "Login",
                    templateUrl: 'app/auth/login/login.html'
                })
                .state('accessdenied', {
                    url: '/accessdenied',
                    parent: 'page',
                    title: "Access Denied",
                    templateUrl: 'app/auth/login/accessdenied.html'
                })
                .state('page.lock', {
                    url: '/lock',
                    title: "Lock",
                    templateUrl: 'app/auth/login/lock.html'
                })
                .state('page.404', {
                    url: '/404',
                    title: "Not Found",
                    templateUrl: 'app/404.html'
                });

            $urlRouterProvider.otherwise('/main/home');

    });

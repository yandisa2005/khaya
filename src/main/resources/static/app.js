(function () {
    'use strict';

    var app = angular.module('jasApp',
        [
            'ui.grid',
            'ui.bootstrap',
            'ui.grid.selection',
            'ui.grid.pagination',
            'ngResource',
            'ngRoute',
            'ngAnimate',
            'ui.router',
            'ngStorage',
            'ngCookies',
            'ngSanitize',
            'ui.utils',

            //3rd party
            'pascalprecht.translate',
             'cfp.loadingBar',
            'restangular',
            //'angularjs-gravatardirective',
            'toaster',
            'angularMoment',
            'LocalStorageModule',

            'tmh.dynamicLocale',
            'infinite-scroll'

        ]);
})();

(function () {
    'use strict';
    angular.module('jasApp')
        .config(function ($translateProvider) {
            $translateProvider.useStaticFilesLoader({
                prefix: 'app/i18n/',
                suffix: '.json'
            });
            $translateProvider.preferredLanguage('en');
            $translateProvider.useLocalStorage();
        })

        .config(function (cfpLoadingBarProvider) {
            cfpLoadingBarProvider.includeBar = true;
            cfpLoadingBarProvider.includeSpinner = false;
            cfpLoadingBarProvider.latencyThreshold = 500;
            cfpLoadingBarProvider.parentSelector = '.wrapper > section';
        });

    angular.module('jasApp')
        .run(function ($rootScope, $state, $stateParams, $window, $templateCache, RestService, Auth, Principal, $location) {
            // Set reference to access them from any scope
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
            $rootScope.$storage = $window.localStorage;

            $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
                $rootScope.toState = toState;
                $rootScope.toStateParams = toStateParams;

                if (Principal.isIdentityResolved()) {
                    Auth.authorize();
                }
            });

            $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
                $rootScope.previousStateName = fromState.name;
                $rootScope.previousStateParams = fromParams;
            });

            $rootScope.back = function () {
                // If previous state is 'activate' or do not exist go to 'home'
                if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                    $state.go('main.home');
                } else {
                    $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
                }
            };

            // Scope Globals
            // -----------------------------------
            $rootScope.app = {
                name: 'Odin',
                description: 'Seeing it all',
                year: ((new Date()).getFullYear()),
                layout: {
                    isFixed: true,
                    isCollapsed: false,
                    isBoxed: false,
                    isRTL: false
                },
                viewAnimation: 'ng-fadeInUp'
            };

            $rootScope.safeApply = function (fn) {
                var phase = $rootScope.$$phase;
                if (phase === '$apply' || phase === '$digest') {
                    if (fn && (typeof(fn) === 'function')) {
                        fn();
                    }
                } else {
                    this.$apply(fn);
                }
            };

            RestService.setErrorInterceptor(function (response) {
                switch (response.status) {
                    case 401:
                        $rootScope.$broadcast("$auth:logout", "Authentication failed for requested resource.");
                        Auth.logout();
                        $rootScope.requestUrl = $location.path();
                        $location.path('/page/login');
                        break;
                    default:
                        break;
                }
            });
        });
}());

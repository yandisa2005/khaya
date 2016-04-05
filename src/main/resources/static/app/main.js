angular.module('jasApp')
    .controller('MainCtrl', function ($rootScope, $scope, $state, $translate, $window, $interval, $localStorage, $timeout, toggleStateService, colors, browser, cfpLoadingBar, RestService, Auth, Principal) {
        "use strict";
        //auth details
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.isInRole = Principal.isInRole;
        $scope.isInAnyRole = Principal.isInAnyRole;
        $scope.$state = $state;

        $scope.logout = function () {
            Auth.logout();
            $state.go('main.home');
        };

        Principal.identity().then(function (account) {
            $scope.user = account;
        });

        // Loading bar transition
        // -----------------------------------
        var thBar;
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            if ($('.wrapper > section').length) { // check if bar container exists
                thBar = $timeout(function () {
                    cfpLoadingBar.start();
                }, 0); // sets a latency Threshold
            }
        });

        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            event.targetScope.$watch("$viewContentLoaded", function () {
                $timeout.cancel(thBar);
                cfpLoadingBar.complete();
            });
        });

        // Hook success
        $rootScope.$on('$stateChangeSuccess',
            function (event, toState, toParams, fromState, fromParams) {
                // display new view from top
                $window.scrollTo(0, 0);
                // Save the route title
                $rootScope.currTitle = $state.current.title;
            });

        $rootScope.currTitle = $state.current.title;
        $rootScope.pageTitle = function () {
            return $rootScope.app.name + ' - ' + ($rootScope.currTitle || $rootScope.app.description);
        };

        // iPad may presents ghost click issues
        // if( ! browser.ipad )
        // FastClick.attach(document.body);

        // Close submenu when sidebar change from collapsed to normal
        $rootScope.$watch('app.layout.isCollapsed', function (newValue, oldValue) {
            if (newValue === false) {
                $rootScope.$broadcast('closeSidebarMenu');
            }
        });

        // Restore layout settings
        if (angular.isDefined($localStorage.layout)) {
            $scope.app.layout = $localStorage.layout;
        } else {
            $localStorage.layout = $scope.app.layout;
        }
        $rootScope.$watch("app.layout", function () {
            $localStorage.layout = $scope.app.layout;
        }, true);


        // Allows to use branding color with interpolation
        // {{ colorByName('primary') }}
        $scope.colorByName = colors.byName;

        // Hides/show user avatar on sidebar
        $scope.toggleUserBlock = function () {
            $scope.$broadcast('toggleUserBlock');
        };

        // Internationalization
        // ----------------------

        $scope.language = {
            // Handles language dropdown
            listIsOpen: false,
            // list of available languages
            available: {
                'en': 'English',
                'es_AR': 'Español'
            },
            // display always the current ui language
            init: function () {
                var proposedLanguage = $translate.proposedLanguage() || $translate.use();
                var preferredLanguage = $translate.preferredLanguage(); // we know we have set a preferred one in app.config
                $scope.language.selected = $scope.language.available[(proposedLanguage || preferredLanguage)];
            },
            set: function (localeId, ev) {
                // Set the new idiom
                $translate.use(localeId);
                // save a reference for the current language
                $scope.language.selected = $scope.language.available[localeId];
                // finally toggle dropdown
                $scope.language.listIsOpen = !$scope.language.listIsOpen;
            }
        };

        $scope.language.init();

        // Restore application classes state
        toggleStateService.restoreState($(document.body));

        // Applies animation to main view for the next pages to load
        $timeout(function () {
            $rootScope.mainViewAnimation = $rootScope.app.viewAnimation;
        });

        // cancel click event easily
        $rootScope.cancel = function ($event) {
            $event.stopPropagation();
        };
    });

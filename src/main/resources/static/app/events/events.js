angular.module('jasApp')
    .controller('eventsController', ['$scope', 'EventsFactory', function ($scope, EventsFactory) {

        $scope.title = "** Events List ***";

        var paginationOptions = {
            page: 1,
            size: 5,
            sort: null,
            orderBy: 'DESC'
        };

        $scope.searchString = "";

        $scope.events = {};

        $scope.gridOptions = {
            data: 'events',

            showGridFooter: true,
            showColumnFooter: true,
            enableFiltering: true,
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            multiSelect: false,

            enablePaginationControls: true,
            enablePagination: true,
            paginationPageSizes: [5, 10, 25, 50, 75],
            paginationPageSize: 5,
            useExternalPagination: true,
            useExternalSorting: true,
            useExternalFiltering: true,
            enableColumnMenus: false,

            columnDefs: [
                {field: 'id', displayName: 'ID'},
                {field: 'eventName', displayName: 'EventName'},
                {field: 'venue', displayName: 'Venue'},
                {field: 'eventDate', displayName: 'EventDate', cellFilter: 'date:\'yyyy-MM-dd HH:mm:ss\''},
                {field: 'amount', displayName: 'Amount'},
                {field: 'expenditure', displayName: 'Expenditure'},
                {field: 'taxCharge', displayName: 'TaxCharge'},
                {field: 'status', displayName: 'Status'}],

            onRegisterApi: function (gridApi) {
                $scope.gridApi = gridApi;

                $scope.gridApi.core.on.sortChanged($scope, function (grid, sortColumns) {
                    if (sortColumns.length === 0) {
                        paginationOptions.sort = null;
                    }
                    else {
                        paginationOptions.sort = sortColumns[0].field;
                        paginationOptions.orderBy= sortColumns[0].sort.direction.toUpperCase();
                        findEvents();
                    }
                });

                gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                    paginationOptions.page = newPage;
                    paginationOptions.size = pageSize;
                    findEvents();
                });
            }
        };

        $scope.today = function () {
            $scope.fromDate = new Date(new Date().setHours(0));
            $scope.toDate = new Date();
        };

        $scope.today();

        $scope.clear = function () {
            $scope.fromDate = null;
            $scope.toDate = null;
        };

        $scope.openFrom = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.isFromCalendarOpened = true;
        };

        $scope.openTo = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.isToCalendarOpened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.formats = ['dd-MMMM-yyyy HH:mm:ss', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];

        $scope.search = function () {
            findEvents();
        };

        EventsFactory.query(function (response) {
            $scope.events = response.results;
            $scope.gridOptions.totalItems = response.totalResults;

        });

        function findEvents() {
            EventsFactory.query(
                {
                    name: $scope.searchString,
                    page: paginationOptions.page,
                    size: paginationOptions.size,
                    sort:paginationOptions.sort,
                    order: paginationOptions.orderBy
                },
                function (response) {
                    if (response.totalResults !=0) {
                        $scope.clients = response.results;
                        $scope.gridOptions.totalItems = response.totalResults;
                    }
                    else {
                        $scope.clients = response.results;
                        $scope.gridOptions.totalItems = 1;
                    }
                });
        }
    }]);


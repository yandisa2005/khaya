angular.module('jasApp')
.controller('msgController', ['$scope', 'EventFactory', function ($scope, EventFactory) {

    $scope.title = "** Clients List ***";

    var paginationOptions = {
        page: 1,
        size: 5,
        sort: null,
        orderBy: 'DESC'
    };

    $scope.searchString = "";

    $scope.clients = {};

    $scope.gridOptions = {
        data: 'clients',

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
            {field: 'name', displayName: 'Name'}],

        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;

            $scope.gridApi.core.on.sortChanged($scope, function (grid, sortColumns) {
                if (sortColumns.length === 0) {
                    paginationOptions.sort = null;
                }
                else {
                    paginationOptions.sort = sortColumns[0].field;
                    paginationOptions.orderBy= sortColumns[0].sort.direction.toUpperCase();
                    findClients();
                }
            });

            gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                paginationOptions.page = newPage;
                paginationOptions.size = pageSize;
                findClients();
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
        findClients();
    };

    EventFactory.query(function (response) {
        $scope.clients = response.results;
        $scope.gridOptions.totalItems = response.totalResults;

    });

    function findClients() {
        EventFactory.query(
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


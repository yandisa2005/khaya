(function () {
    'use strict';
    angular.module('jasApp')
        .controller('HomeCtrl', function ($scope, RestService, $interval) {
            $scope.overview = {
                totalThisWeek: 0,
                totalToday: 0,
                totalTodayProcessed: 0,
                totalTodayUnprocessed: 0,
                data: []
            };

            //
            // Global Stats
            //
            var aDay = (1000 * 60 * 60 * 24);
            var today = new Date().getTime();
            var week = moment().startOf('week').toDate().getTime();
            var daysAgo14 = moment().subtract(14, 'days').startOf('day').toDate().getTime();

            //this should be a push model via websockets longer term
            //$interval(function(){
            //get dashboard data from top section
            RestService.get("processes/statistics?from=" + week + "&to=" + today + "&size=500", function (stats) {
                $scope.overview.totalThisWeek = stats.totalCompletedPayments + stats.totalActivePayments;
                $scope.overview.totalToday = 0;
                $scope.overview.totalTodayProcessed = 0;
                $scope.overview.totalTodayUnprocessed = 0;

                for (var i = 0; i < stats.statistics.length; i++) {
                    if (stats.statistics[i].day == moment().format('D')) {
                        $scope.overview.totalToday = stats.statistics[i].totalCompletedPayments + stats.statistics[i].totalActivePayments;
                        $scope.overview.totalTodayProcessed = stats.statistics[i].totalCompletedPayments;
                        $scope.overview.totalTodayUnprocessed = stats.statistics[i].totalActivePayments;
                    }
                }

                //get dashboard data for bottom section
                if ($scope.overview.totalTodayUnprocessed > 0) {
                    RestService.get("processes?completed=false&from=" + daysAgo14 + "&to=" + today + "&size=500", function (stats) {
                        $scope.overview.data = stats.results;
                    });
                }
            });

            //get dashboard data from top section
            RestService.get("processes/statistics?from=" + daysAgo14 + "&to=" + today + "&size=500", function (statistics) {
                var processed = [];
                var unprocessed = [];
                var days = [];

                var stats = statistics.statistics;
                var fullStats = [];

                //FILL IN MISSING DATA DATA
                var dayFiller = [];

                for (var idx = 0; idx < 14; idx++) {
                    dayFiller[idx] = ({"day": moment().subtract(idx, 'days').format('Do'), "totalCompletedPayments": 0, "totalActivePayments": 0});
                    for (var statIdx = 0; statIdx < stats.length; statIdx++) {
                        var stat = stats[statIdx];
                        if (moment().subtract(idx, 'days').format('D') == stat.day) {
                            dayFiller[idx] = ({
                                "day": moment().subtract(idx, 'days').format('Do'),
                                "totalCompletedPayments": stat.totalCompletedPayments,
                                "totalActivePayments": stat.totalActivePayments
                            });
                        }
                    }
                }

                dayFiller.forEach(function (element) {
                    var stat = element;
                    days.push(stat.day);
                    processed.push(stat.totalCompletedPayments);
                    unprocessed.push(stat.totalActivePayments);
                });

                $scope.chartConfig.series[0].data = unprocessed.reverse();
                $scope.chartConfig.series[1].data = processed.reverse();
                $scope.chartConfig.xAxis.categories = days.reverse();
                $scope.chartConfig.loading = false;
            });
            //},5000);

            $scope.chartConfig = {
                options: {
                    chart: {
                        type: 'line',
                        zoomType: 'x'
                    }
                },
                series: [{
                    name: 'Unprocessed',
                    color: '#FF7878',
                    data: []
                }, {
                    name: 'Processed',
                    color: '#5A8CBD',
                    data: []
                }],
                title: {
                    text: ''
                },
                xAxis: {
                    categories: []
                },
                yAxis: {
                    title: {
                        text: 'Count per day'
                    }
                },
                loading: true
            };

            var refreshTimelineData = $interval(function () {
                var url = "processes?completed=false&startedAtFrom=" + daysAgo14 + "&startedAtTo=" + today + "&size=25";
                RestService.get(url, function (stats) {
                    $scope.topData = stats.results;
                });
            }, 5000);

            $scope.$on('$destroy', function () {
                $interval.cancel(refreshTimelineData);
            });
        });

}());

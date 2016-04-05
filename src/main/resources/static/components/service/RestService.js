(function () {
    'use strict';
    angular.module('jasApp')
        .factory('RestService', function (Restangular, localStorageService, SERVICE_URL, $q) {

            Restangular = Restangular.withConfig(function (RestangularConfigurer) {
                RestangularConfigurer.setBaseUrl(SERVICE_URL);

                RestangularConfigurer.addFullRequestInterceptor(function (element, operation, route, url, headers, params, httpConfig) {
                    var token = localStorageService.get('token');
                    if (token && token.expires && token.expires > new Date().getTime()) {
                        headers['x-auth-token'] = token.token;
                    }

                    return {
                        element: element,
                        headers: headers,
                        params: params,
                        httpConfig: httpConfig
                    };
                });
            });

            return {
                setErrorInterceptor: function (callback) {
                    if ($.isFunction(callback)) {
                        Restangular.setErrorInterceptor(function (response) {
                            callback(response);
                        });
                    }
                },
                getAll: function (path, callback) {
                    return Restangular.all(path).getList()
                        .then(function (_futureModels) {
                            ngProgress.complete();
                            if ($.isFunction(callback)) {
                                callback(_futureModels);
                            }
                        }, function (reason) {
                            console.error(reason);
                        });
                },
                get: function (path, callback) {
                    return Restangular.one(path, null).get()
                        .then(function (_futureModel) {
                            if ($.isFunction(callback)) {
                                callback(_futureModel);
                            }
                            return _futureModel;
                        }, function (reason) {
                            console.error(reason);
                            $q.reject(reason);
                        });
                },
                getById: function (path, _id, callback) {
                    return Restangular.one(path, _id).get().then(function (_futureModel) {
                        if ($.isFunction(callback)) {
                            callback(_futureModel);
                        }
                        return _futureModel;
                    }, function (reason) {
                        console.error(reason);
                    });
                },
                delete: function (_model, element, callback) {
                    return _model.remove().then(function (_futureModel) {
                        if ($.isFunction(callback)) {
                            callback(_futureModel);
                        }
                    }, function (reason) {
                        console.error(reason);
                    });
                },
                customDelete: function (path, callback){
                    return Restangular.one(path).remove().then(function (_futureModel) {
                        if ($.isFunction(callback)) {
                            callback(_futureModel);
                            return _futureModel;
                        }
                    }, function (reason) {
                        return reason;
                    });

                },
                post: function (path, data, callback) {
                    return Restangular.all(path, null).customPOST(
                        data,
                        undefined, // put your path here
                        undefined // params here, e.g. {format: "json"
                    ).then(function (_futureModel) {
                            if ($.isFunction(callback)) {
                                callback(_futureModel);
                            }
                            return _futureModel;
                        }, function (reason) {
                            console.error(reason);
                            return reason;
                        });
                },
                postAsForm: function (path, data, callback) {
                    return Restangular.one(path, null).customPOST(
                        data,
                        undefined, // put your path here
                        undefined, // params here, e.g. {format: "json"}
                        {'Content-Type': "application/x-www-form-urlencoded; charset=UTF-8"}
                    ).then(function (_futureModel) {
                            if ($.isFunction(callback)) {
                                callback(_futureModel);
                            }
                            return _futureModel;
                        }, function (reason) {
                            console.error(reason);
                            throw reason;
                        });
                },
                persist: function (_model, form, callback) {
                    $(form).find(".has-error").removeClass("has-error");
                    $(form).find(".help-block").remove();

                    return (_model.id === null ? _service.post(_model) : _model.put()).then(function (_futureModel) {
                        if ($.isFunction(callback)) {
                            callback(_futureModel);
                        }
                    }, function (reason) {
                        console.error(reason);
                        var failedReason = "Failed to" + (_model.id === null ? " create new " : " update ") + " record in database. Please fix below issues";
                        $.each(reason.data, function (idx, element) {
                            $(document).find("#" + idx).each(function (subIdx, subElement) {
                                var formGroup = $(subElement).closest(".form-group");
                                $(formGroup).addClass("has-error");
                                $(formGroup).append("<span class='help-block'>" + element[0] + "</span>");
                                $(subElement).focus();

                            });
                        });
                    });
                },
                update: function (path, data, form, callback) {
                    return Restangular.one(path, null).customPOST(
                        data,
                        undefined, // put your path here
                        undefined, // params here, e.g. {format: "json"}
                        {'Content-Type': "application/json;"}
                    ).then(function (_futureModel) {
                            if ($.isFunction(callback)) {
                                callback(_futureModel);
                            }
                            return _futureModel;
                        }, function (reason) {
                            //console.error(reason);
                            return reason;
                        });
                },
                clone: function (path, _id) {
                    Restangular.one(path, _id).then(function (_model) {
                        return Restangular.copy(_model);
                    });
                }
            };
        });
}());

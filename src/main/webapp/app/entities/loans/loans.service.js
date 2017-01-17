(function() {
    'use strict';
    angular
        .module('labradorApp')
        .factory('Loans', Loans);

    Loans.$inject = ['$resource', 'DateUtils'];

    function Loans ($resource, DateUtils) {
        var resourceUrl =  'api/loans/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.loanDate = DateUtils.convertDateTimeFromServer(data.loanDate);
                        data.returnDate = DateUtils.convertDateTimeFromServer(data.returnDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

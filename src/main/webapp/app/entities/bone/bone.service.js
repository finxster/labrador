(function() {
    'use strict';
    angular
        .module('labradorApp')
        .factory('Bone', Bone);

    Bone.$inject = ['$resource'];

    function Bone ($resource) {
        var resourceUrl =  'api/bones/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

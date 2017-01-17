(function() {
    'use strict';

    angular
        .module('labradorApp')
        .controller('BoneDetailController', BoneDetailController);

    BoneDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Bone', 'User'];

    function BoneDetailController($scope, $rootScope, $stateParams, previousState, entity, Bone, User) {
        var vm = this;

        vm.bone = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('labradorApp:boneUpdate', function(event, result) {
            vm.bone = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

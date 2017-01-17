(function() {
    'use strict';

    angular
        .module('labradorApp')
        .controller('LoansDetailController', LoansDetailController);

    LoansDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Loans', 'Bone', 'User'];

    function LoansDetailController($scope, $rootScope, $stateParams, previousState, entity, Loans, Bone, User) {
        var vm = this;

        vm.loans = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('labradorApp:loansUpdate', function(event, result) {
            vm.loans = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

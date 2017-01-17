(function() {
    'use strict';

    angular
        .module('labradorApp')
        .controller('LoansDeleteController',LoansDeleteController);

    LoansDeleteController.$inject = ['$uibModalInstance', 'entity', 'Loans'];

    function LoansDeleteController($uibModalInstance, entity, Loans) {
        var vm = this;

        vm.loans = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Loans.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

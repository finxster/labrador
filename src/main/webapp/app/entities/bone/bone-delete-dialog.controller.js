(function() {
    'use strict';

    angular
        .module('labradorApp')
        .controller('BoneDeleteController',BoneDeleteController);

    BoneDeleteController.$inject = ['$uibModalInstance', 'entity', 'Bone'];

    function BoneDeleteController($uibModalInstance, entity, Bone) {
        var vm = this;

        vm.bone = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Bone.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

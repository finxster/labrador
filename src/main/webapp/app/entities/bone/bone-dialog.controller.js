(function() {
    'use strict';

    angular
        .module('labradorApp')
        .controller('BoneDialogController', BoneDialogController);

    BoneDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Bone', 'User'];

    function BoneDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Bone, User) {
        var vm = this;

        vm.bone = entity;
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.bone.id !== null) {
                Bone.update(vm.bone, onSaveSuccess, onSaveError);
            } else {
                Bone.save(vm.bone, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('labradorApp:boneUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

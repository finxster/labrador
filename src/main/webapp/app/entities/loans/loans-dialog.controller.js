(function() {
    'use strict';

    angular
        .module('labradorApp')
        .controller('LoansDialogController', LoansDialogController);

    LoansDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Loans', 'Bone', 'User'];

    function LoansDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Loans, Bone, User) {
        var vm = this;

        vm.loans = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.bones = Bone.query({filter: 'loans-is-null'});
        $q.all([vm.loans.$promise, vm.bones.$promise]).then(function() {
            if (!vm.loans.bone || !vm.loans.bone.id) {
                return $q.reject();
            }
            return Bone.get({id : vm.loans.bone.id}).$promise;
        }).then(function(bone) {
            vm.bones.push(bone);
        });
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.loans.id !== null) {
                Loans.update(vm.loans, onSaveSuccess, onSaveError);
            } else {
                Loans.save(vm.loans, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('labradorApp:loansUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.loanDate = false;
        vm.datePickerOpenStatus.returnDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();

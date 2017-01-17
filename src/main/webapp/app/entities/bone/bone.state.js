(function() {
    'use strict';

    angular
        .module('labradorApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('bone', {
            parent: 'entity',
            url: '/bone',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'labradorApp.bone.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bone/bones.html',
                    controller: 'BoneController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('bone');
                    $translatePartialLoader.addPart('boneStatus');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('bone-detail', {
            parent: 'entity',
            url: '/bone/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'labradorApp.bone.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/bone/bone-detail.html',
                    controller: 'BoneDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('bone');
                    $translatePartialLoader.addPart('boneStatus');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Bone', function($stateParams, Bone) {
                    return Bone.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'bone',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('bone-detail.edit', {
            parent: 'bone-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bone/bone-dialog.html',
                    controller: 'BoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Bone', function(Bone) {
                            return Bone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bone.new', {
            parent: 'bone',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bone/bone-dialog.html',
                    controller: 'BoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('bone', null, { reload: 'bone' });
                }, function() {
                    $state.go('bone');
                });
            }]
        })
        .state('bone.edit', {
            parent: 'bone',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bone/bone-dialog.html',
                    controller: 'BoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Bone', function(Bone) {
                            return Bone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bone', null, { reload: 'bone' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('bone.delete', {
            parent: 'bone',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/bone/bone-delete-dialog.html',
                    controller: 'BoneDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Bone', function(Bone) {
                            return Bone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('bone', null, { reload: 'bone' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

(function() {
    'use strict';

    angular
        .module('labradorApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('loans', {
            parent: 'entity',
            url: '/loans',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'labradorApp.loans.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loans/loans.html',
                    controller: 'LoansController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loans');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('loans-detail', {
            parent: 'entity',
            url: '/loans/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'labradorApp.loans.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loans/loans-detail.html',
                    controller: 'LoansDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loans');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Loans', function($stateParams, Loans) {
                    return Loans.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'loans',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('loans-detail.edit', {
            parent: 'loans-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loans/loans-dialog.html',
                    controller: 'LoansDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Loans', function(Loans) {
                            return Loans.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loans.new', {
            parent: 'loans',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loans/loans-dialog.html',
                    controller: 'LoansDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                loanDate: null,
                                returnDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('loans', null, { reload: 'loans' });
                }, function() {
                    $state.go('loans');
                });
            }]
        })
        .state('loans.edit', {
            parent: 'loans',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loans/loans-dialog.html',
                    controller: 'LoansDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Loans', function(Loans) {
                            return Loans.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loans', null, { reload: 'loans' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loans.delete', {
            parent: 'loans',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loans/loans-delete-dialog.html',
                    controller: 'LoansDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Loans', function(Loans) {
                            return Loans.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loans', null, { reload: 'loans' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

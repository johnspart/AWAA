(function() {
  'use strict';

  angular
    .module('awaa')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      }).state('forgot', {
        url: '/forgot',
        templateUrl: 'app/components/forgot/forgot.html',
        controller: 'ForgotCtrl'
      });

    $urlRouterProvider.otherwise('/');
  }

})();

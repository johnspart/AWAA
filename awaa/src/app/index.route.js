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
      }).state('login', {
        url: '/login',
        templateUrl: 'app/components/login/login.html',
        controller: 'LoginController',
        controllerAs: 'lCtrl'
      }).state('forgot', {
        url: '/forgot',
        templateUrl: 'app/components/forgot/forgot.html',
        controller: 'ForgotController',
        controllerAs: 'frgCtrl'
      });

    $urlRouterProvider.otherwise('/');
  }

})();

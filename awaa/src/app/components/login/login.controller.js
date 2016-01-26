(function() {
  'use strict';

  angular
    .module('awaa')
    .controller('LoginController', LoginController);

  /** @ngInject */
  function LoginController($scope, $http, $cookies, urlSrv, LoginService, toastr) {
    var vm = this;

    vm.credentials = {};

    vm.login = function(form) {
      if (form.$valid)
        LoginService.login(vm.credentials);
      else {
        form.$setDirty(true);
        toastr.info(toastr.simple().content('Revisa tu usuario o contraseña'));
      }
    };

    $http.get(urlSrv + 'token').success(function(token) {
      $http({
        url: 'http://localhost:3000',
        method: 'GET',
        headers: {
          'X-Auth-Token': token.token
        }
      }).success(function(data) {
        vm.greeting = data;
      });
    })

  }
})();

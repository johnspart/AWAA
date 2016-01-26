(function() {
  'use strict';

  angular
    .module('awaa')
    .controller('LoginController', LoginController);

  /** @ngInject */
  function LoginController($scope, $rootScope, $http, $cookies, urlSrv, LoginService, toastr) {
    var vm = this;

    vm.credentials = {};
    vm.error = false;
    vm.login = function(form) {
      if (form.$valid)
        vm.error = !LoginService.login(vm.credentials);
      else {
        form.$setDirty(true);
        toastr.info(toastr.simple().content('Revisa tu usuario o contraseña'));
      }
    };

    

  }
})();

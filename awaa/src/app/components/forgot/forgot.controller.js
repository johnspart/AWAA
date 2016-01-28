(function() {
  'use strict';

  angular
    .module('awaa')
    .controller('ForgotController', ForgotController);

  /** @ngInject */
  function ForgotController($rootScope, ForgotService, toastr) {
    var vm = this;
    vm.user = '';

    vm.send = function(form) {
      if (form.$valid)
        ForgotService.forgot(vm.user);
      else {
        form.$setDirty(true);
        toastr.info($rootScope.labels.cmpUsrReq);
      }
    };
  }
})();

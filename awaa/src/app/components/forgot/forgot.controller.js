(function() {
  'use strict';

  function ForgotCtrl($scope, LoginSrvc) {
    $scope.user = {};

    $scope.send = function() {
      LoginSrvc.forgot($scope.user.name);
    };
  }

  angular
      .module('awaa')
      .controller('ForgotCtrl', ForgotCtrl);
})();

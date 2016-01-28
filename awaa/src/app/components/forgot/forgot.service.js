(function() {
  'use strict';

  angular
    .module('awaa')
    .factory('ForgotService', ForgotService);

  function ForgotService($rootScope, $http, $state, $cookies, $log, $resource, $location, urlSrv, toastr) {

    function forgot(user) {
      $http.post(urlSrv + 'forgot', $.param({
        userName: user
      }), {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).success(function(data) {
        $log.debug(data);
      }).error(function(data) {
        $log.debug(data);
      })
    }

    return {
      forgot: forgot
    }
  }
})();

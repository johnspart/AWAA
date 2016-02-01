(function() {
  'use strict';

  angular
    .module('awaa')
    .factory('ForgotService', ForgotService);

  function ForgotService($http, $log, urlSrv, toastr, labels) {

    function forgot(user) {
      $http.post(urlSrv + 'forgot', $.param({
        userName: user
      }), {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).success(function(data) {
        toastr.info(labels.getLabel(data))
        $log.debug(data);
      }).error(function(data) {
        toastr.error(labels.getLabel(data.label))
        $log.debug(data);
      })
    }

    return {
      forgot: forgot
    }
  }
})();

(function() {
  'use strict';

  angular
    .module('awaa')
    .factory('LoginService', LoginService);

  function LoginService($http, $state, $log, $resource, urlSrv) {
    return $resource(':action', {}, {
      authenticate: {
        method: 'POST',
        url: urlSrv + 'login',
        params: {
          'action': 'authenticate'
        }
      }
    });
  }

})
();

(function() {
  'use strict';

  angular
    .module('awaa')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $rootScope, $http, $cookies, $location, labels, urlSrv) {
    //Run translation if selected language changes
    $rootScope.labels = function() {
      labels.getLabels($rootScope, $rootScope.selectedLanguage);
    };

    //Init
    $rootScope.selectedLanguage = 'es';
    $rootScope.labels();

    $log.debug('runBlock end');

    $rootScope.authenticate = function(callback) {
      $http.get(urlSrv + 'user').success(function(data) {
        if (data.name) {
          $rootScope.authenticated = true;
        } else {
          $rootScope.authenticated = false;
        }
        callback && callback();
      }).error(function() {
        $rootScope.authenticated = false;
        callback && callback();
      });
    };


    $rootScope.authenticate();

    $rootScope.logout = function() {
      $http.post(urlSrv + 'logout', {}).success(function() {
        $rootScope.authenticated = false;
        $location.path("/");
      }).error(function() {
        $rootScope.authenticated = false;
      });
    }

  }

})();

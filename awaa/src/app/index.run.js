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

    var authenticate = function(callback) {
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


    authenticate();
    $rootScope.credentials = {};
    $rootScope.login = function() {
      $rootScope.credentials._csrf = $cookies.get('XSRF-TOKEN');
      $http.post(urlSrv + 'login', $.param($rootScope.credentials), {
        headers: {
          "content-type": "application/x-www-form-urlencoded"
        }
      }).success(function(data) {
        authenticate(function() {
          if ($rootScope.authenticated) {
            $log("Login succeeded");
            $location.path("/");
            $rootScope.error = false;
          } else {
            $log("Login failed")
            $location.path("/login");
            $rootScope.error = true;
          }
        });
      }).error(function(data) {
        $location.path("/login");
        $rootScope.error = true;
        $rootScope.authenticated = false;
      })
    };


    $rootScope.logout = function() {
      $http.post(urlSrv + 'logout', {}).success(function() {
        $rootScope.authenticated = false;
        $location.path("/");
      }).error(function() {
        $rootScope.authenticated = false;
      });
    }



    $http.get(urlSrv + 'token').success(function(token) {
      $http({
        url: 'http://localhost:3000',
        method: 'GET',
        headers: {
          'X-Auth-Token': token.token
        }
      }).success(function(data) {
        $rootScope.greeting = data;
      });
    })
  }

})();

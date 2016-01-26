(function() {
  'use strict';

  angular
    .module('awaa')
    .factory('LoginService', LoginService);

  function LoginService($rootScope, $http, $state, $cookies, $log, $resource, $location, urlSrv, toastr) {

    function login(credentials) {
      credentials._csrf = $cookies.get('XSRF-TOKEN');
      $http.post(urlSrv + 'login', $.param(credentials), {
        headers: {
          "content-type": "application/x-www-form-urlencoded"
        }
      }).success(function(data) {
        $rootScope.authenticate(function() {
          if ($rootScope.authenticated) {
            $log.debug("Login succeeded");
            $location.path("/");
            toastr.info('Fork <a href="https://github.com/Swiip/generator-gulp-angular" target="_blank"><b>generator-gulp-angular</b></a>');
          } else {
            $log.debug("Login failed")
            $location.path("/login");
            toastr.error('Fork <a href="https://github.com/Swiip/generator-gulp-angular" target="_blank"><b>generator-gulp-angular</b></a>');
          }
        });
      }).error(function(data) {
        $location.path("/login");
        toastr.error('Fork <a href="https://github.com/Swiip/generator-gulp-angular" target="_blank"><b>generator-gulp-angular</b></a>');
        $rootScope.authenticated = false;
      })
    };
    return {
      login: login
    }
  }
})();

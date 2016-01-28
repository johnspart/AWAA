(function() {
  'use strict';

  angular
    .module('awaa')
    .config(config);

  /** @ngInject */
  function config($logProvider, toastrConfig, $httpProvider) {
    // Enable log
    $logProvider.debugEnabled(true);

    // Set options third-party lib
    toastrConfig.allowHtml = true;
    toastrConfig.timeOut = 3000;
    toastrConfig.positionClass = 'toast-top-right';
    toastrConfig.preventDuplicates = true;
    toastrConfig.progressBar = true;

    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    //delete $httpProvider.defaults.headers.common["X-Requested-With"];
    //  $httpProvider.defaults.headers.common["Accept"] = "application/json";
    //$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
    $httpProvider.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
    $httpProvider.interceptors.push('XSRFInterceptor');
  }

  angular.module('awaa')
    .factory('XSRFInterceptor', function($cookies, $log) {

      var XSRFInterceptor = {

        request: function(config) {

          var token = $cookies.get('XSRF-TOKEN');

          if (token) {
            config.headers['X-XSRF-TOKEN'] = token;
            config.headers['CSRF'] = token;
            $log.info("X-XSRF-TOKEN: " + token);
          }

          return config;
        }
      };
      return XSRFInterceptor;
    });

})();

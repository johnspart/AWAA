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

    //$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    //delete $httpProvider.defaults.headers.common["X-Requested-With"];
    //  $httpProvider.defaults.headers.common["Accept"] = "application/json";
    //$httpProvider.defaults.headers.common["Content-Type"] = "application/json";
    $httpProvider.defaults.headers.common["Access-Control-Allow-Origin"] = "*";

  }

})();

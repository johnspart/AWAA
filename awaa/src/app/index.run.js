(function() {
  'use strict';

  angular
    .module('awaa')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $rootScope, labels) {
    //Run translation if selected language changes
    $rootScope.labels = function() {
      labels.getLabels($rootScope, $rootScope.selectedLanguage);
    };

    //Init
    $rootScope.selectedLanguage = 'es';
    $rootScope.labels();

    $log.debug('runBlock end');
  }

})();

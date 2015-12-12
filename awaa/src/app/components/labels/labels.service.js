(function() {
  'use strict';

  angular
    .module('awaa')
    .service('labels', labels);

  /** @ngInject */
  function labels($log, $resource) {
    this.getLabels = function($rootScope, language) {
      var languageFilePath = 'fonts/labels_' + language + '.json';
      $log.debug$log, (languageFilePath);
      $resource(languageFilePath).get(function(data) {
        $rootScope.labels = data;
      });
    };
  }
})();

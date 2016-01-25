/* global malarkey:false, moment:false */
(function() {
  'use strict';

  angular
    .module('awaa')
    .constant('malarkey', malarkey)
    .constant('moment', moment)
    .constant('urlSrv', "http://localhost:8080/awaa-web/");

})();

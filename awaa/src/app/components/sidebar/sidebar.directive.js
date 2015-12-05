(function() {
  'use strict';

  angular
    .module('awaa')
    .directive('acmeSidebar', acmeSidebar);

    /** @ngInject */
    function acmeSidebar() {
      var directive = {
        restrict: 'E',
        templateUrl: 'app/components/sidebar/sidebar.html',
        scope: {
            creationDate: '='
        },
        controller: SidebarController,
        controllerAs: 'vm',
        bindToController: true
      };

      return directive;

      /** @ngInject */
      function SidebarController(moment) {
        var vm = this;

        // "vm.creation" is avaible by directive option "bindToController: true"
      }
    }

  })();

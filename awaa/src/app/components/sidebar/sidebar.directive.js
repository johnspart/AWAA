(function() {
  'use strict';

  angular
    .module('awaa')
    .directive('awaaSidebar', awaaSidebar);

  /** @ngInject */
  function awaaSidebar() {
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
    function SidebarController(moment, $rootScope) {
      var vm = this;

      vm.cerrarMenu = function() {
        $rootScope.mostrar = '';
      };

    }
  }

})();

(function() {
  'use strict';

  angular
    .module('awaa')
    .directive('awaaNavbar', awaaNavbar);

  /** @ngInject */
  function awaaNavbar() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/navbar/navbar.html',
      scope: {
        creationDate: '='
      },
      controller: NavbarController,
      controllerAs: 'vm',
      bindToController: true
    };

    return directive;

    /** @ngInject */
    function NavbarController(moment, $rootScope) {
      var vm = this;

      vm.abrirMenu = function() {
        $rootScope.mostrar = 'mostrar';
      };
    }
  }

})();

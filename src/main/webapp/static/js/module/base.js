define(function() {
  'use strict';
  var $login = $('#J_login'),
    $loginOverlay = $('#J_login_overlay'),
    $loginOff = $('#J_login_off');
  $login.click(function() {
    $loginOverlay.show();
  });
  $loginOff.click(function() {
    $loginOverlay.hide();
  });
});

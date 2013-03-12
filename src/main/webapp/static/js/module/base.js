define(function() {
  'use strict';
  var $login = $('#J_login'),
    $loginOverlay = $('#J_login_overlay'),
    $loginOff = $('#J_login_off'),
    $statusBody = $('#J_status_body'),
    $updateStatusBtn = $('#J_update_status');

  $login.click(function() {
    $loginOverlay.show();
  });

  $loginOff.click(function() {
    $loginOverlay.hide();
  });

  $updateStatusBtn.on('hidden', function() {
    var status = $.trim($statusBody.val());
    if (status) {
      $.ajax({
        'url': '/updateStatus',
        'data': {
          'status': status
        },
        'type': 'post',
        'dataType': 'json'
      }).done(function(result) {
          if (result.success) {
            alert('U make it!');
            location.reload();
          } else {
            alert(result.message);
          }
        });
    }
  });
});

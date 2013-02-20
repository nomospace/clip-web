define(function() {
  var $username = $('#J_username'),
    $usernameBtn = $('#J_username_btn');
  $usernameBtn.click(function(e) {
    var username = $.trim($username.val());
    if (username) {
      $.ajax({
        'url': '/updateUsername',
        'data': {
          'username': username
        },
        'type': 'post',
        'dataType': 'json'
      }).done(function(result) {
          if (result.success) {
            alert('U make it!');
          } else {
            alert(result.message);
          }
        });
    }
  });
});

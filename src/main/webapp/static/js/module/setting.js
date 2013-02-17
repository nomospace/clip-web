define(function() {
  var $username = $('#J_username'),
    $usernameBtn = $('#J_username_btn');
  $usernameBtn.click(function(e) {
    var username = $.trim($username.val());
    if (username) {
      $.getJSON('/updateUsername/' + username).done(function(result) {
        if (result.success) {
          alert('设置成功');
        }
      });
    }
  });
});

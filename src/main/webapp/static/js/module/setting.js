define(function() {
  'use strict';

  var $username = $('#J_username'),
    $usernameBtn = $('#J_username_btn'),
    $email = $('#J_email'),
    $emailBtn = $('#J_email_btn'),
    $remind = $('#J_remind'),
    $remindBtn = $('#J_remind_btn');

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

  $emailBtn.click(function(e) {
    var email = $.trim($email.val());
    if (email) {
      $.ajax({
        'url': '/updateEmail',
        'data': {
          'email': email
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

  $remindBtn.click(function(e) {
    var remind = $.trim($remind.find(':checked').val());
    if (remind) {
      $.ajax({
        'url': '/updateRemind',
        'data': {
          'remind': remind
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

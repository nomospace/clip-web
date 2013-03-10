define(function() {
  'use strict';

  var $name = $('#J_name'),
    $nameBtn = $('#J_name_btn'),
    $email = $('#J_email'),
    $emailBtn = $('#J_email_btn'),
    $remind = $('#J_remind'),
    $remindBtn = $('#J_remind_btn');

  $nameBtn.click(function(e) {
    var name = $.trim($name.val());
    if (name) {
      $.ajax({
        'url': '/updateName',
        'data': {
          'name': name
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

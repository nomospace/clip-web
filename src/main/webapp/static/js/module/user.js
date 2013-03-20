define(['masonry'], function () {
  'use strict';
  $('html').removeAttr('contentEditable');
  $('#J_status_list').masonry({
    itemSelector: '.status-body'
//    columnWidth: 240
  });
});

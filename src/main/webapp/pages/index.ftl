<#assign page_name="index"/>
<#include "common/module.ftl"/>
<#escape x as x?html>
  <@html>
    <@body class="${page_name}-page">
    <!-- wrapper -->
    <div class='wrapper'>
      <#if !isLogin??&&fromHome??>
        <div class="alert alert-info">
          <button type="button" class="close" data-dismiss="alert">×</button>
          为了保护用户的隐私，请先登录
        </div>
      </#if>
      <div class="row">
        <div class="span3"></div>
        <div class="span9">
          <#if user??>
            <#include "common/status.ftl">
          </#if>
        </div>
      </div>
      <!-- Main container. -->
      <div role="main" id="main"></div>
      <!-- Application source. -->
    </div>
    </@body>
  </@html>
</#escape>

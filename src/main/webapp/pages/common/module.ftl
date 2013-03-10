<#include "config.ftl">
<#--
   通用页面HTML
   <@HTML>
     <div>content</div>
   </@HTML>
-->
<#macro html target="">
<!DOCTYPE html>
<html>
  <@head title="clip-web"
  keywords="clip-web"
  description="clip-web" target="${target}">
  </@head>
    <#nested>
</html>
</#macro>

<#--
   通用页面HEAD
    <@head title="clip-web首页"
        keywords="clip-web"
        description="clip-web">
    </@head>
-->
<#macro head title="clip-web" keywords="clip-web" description="clip-web" target="">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>${title}</title>
  <meta name="keywords" content="${keywords}"/>
  <meta name="description" content="${description}"/>
  <#if target!="">
    <base target="${target}"/>
  </#if>
  <link rel="icon" type="image/x-icon" href="/static/favicon.ico" />
  <#include "function.ftl"/>
  <#include "css.ftl">
  <!--[if IE]>
  <script src="${cfg_base_js_base}/html5.js"></script>
  <![endif]-->
  <#nested />
</head>
</#macro>

<#--
   通用页面BODY
   <@body>
     <div>body content</div>
   </@body>
-->
<#macro body class="" id="">
<body class="${class}" <#if id!="">id="${id}"</#if>>
  <@navbar></@navbar>
  <#include "header.ftl"/>
  <#include "login.ftl"/>
  <#nested>
  <#include "footer.ftl"/>
  <#include "js.ftl">
</body>
</#macro>

<#--
   特殊无权限控制页面BODY
   <@body>
     <div>body content</div>
   </@body>
-->
<#macro body_noAuth class="" id="">
<body class="${class}" <#if id!="">id="${id}"</#if>>
  <#nested>
<a id="J_page_to_top" class="global-totop" title="回到顶部"></a>
  <#include "js.ftl">
</body>
</#macro>

<#-- 通用页面内容主体 -->
<#macro main_con class="" id="">
<div class="main-con ${class}" <#if id!="">id="${id}"</#if>>
<#-- 子服务内容主体 -->
  <div class="main-wrapper">
    <div class="main-inner">
      <#nested>
    </div>
  </div>
</div>
</#macro>

<#-- 顶部导航面内容主体 -->
<#macro navbar class="" id="">
<div class="navbar navbar-googlebar navbar-inverse">
  <div class="navbar-inner">
    <div class="container">
      <a class="brand" href="/">ClipWeb</a>
      <ul class="nav pull-right">
        <#if user??>
          <li><a href="/${user.username!""}">${user.username!""}</a></li>
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" data-action="popup-users">账户<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li>
                <a href="/setting">设置</a>
              </li>
              <li>
                <a href="/notifications">提醒</a>
              </li>
              <li>
                <a href="/help">帮助</a>
              </li>
              <li>
                <a href="/logout">退出</a>
              </li>
            </ul>
          </li>
        <#else>
          <li><a id="J_login" href="javascript:;">登录</a></li>
        </#if>
      </ul>
    </div>
  </div>
</div>
</#macro>

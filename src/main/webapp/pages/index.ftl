<#assign page_name="index"/>
<#include "common/module.ftl"/>
<#escape x as x?html>
  <@html>
    <@body class="index-page">
    <!-- wrapper -->
    <div class='wrapper'>
      <#if !isLogin??&&fromHome??>
        <div class="alert alert-info">
          <button type="button" class="close" data-dismiss="alert">×</button>
          为了保护用户的隐私，请先登录
        </div>
      </#if>
      <#if user??>
        <div class="alert alert-info">
          您已登录 ${user.username!""}
        </div>
        <#list statuses as status>
          <#if status?has_content&&status.user?has_content&&status.source?has_content>
            <img src="${status.user.profileImageUrl!""}" alt="${status.user.name!""}"><span>${status.user.name!""}</span><span>${status.createdAt?date}</span>
            <p>${status.text!""}</p>
            <#if status.thumbnailPic?has_content>
              <img src="${status.thumbnailPic!""}" alt="">
            </#if>
            <p>来自 <a href="${status.source.url!""}">${status.source.name!""}</a></p>
          </#if>
        </#list>
      </#if>
      <!-- Main container. -->
      <div role="main" id="main"></div>
      <#if !user??>
        <div id="J_login" class="nav-login" style="top: 45px;">
          <div class="login-head"><h3>授权登录</h3></div>
          <div class="login-connent">
            <ul>
              <li class="login qq"><a title="使用腾讯微博授权登录" href="/connect/qq">qq</a></li>
              <li class="login sina"><a title="使用新浪微博授权登录" href="/connect/sina">sina</a></li>
              <li class="login douban"><a title="使用豆瓣授权登录" href="/connect/douban">douban</a></li>
              <li class="login twitter"><a title="使用 Twitter 授权登录" href="/connect/twitter">twitter</a></li>
              <li class="login renren"><a title="使用人人授权登录" href="/connect/renren">renren</a></li>
              <li class="login instagram"><a title="使用 instagram 授权登录" href="/connect/instagram">instagram</a></li>
            </ul>
          </div>
          <div title="关闭" id="J_login_off" class="nav-login-close"></div>
        </div>
      </#if>
      <!-- Application source. -->
    </div>
    <div class='cl cr'></div>
    </div>
    </@body>
  </@html>
</#escape>

<#assign page_name="user"/>
<#include "common/module.ftl"/>
<#escape x as x?html>
  <@html>
    <@body class="${page_name}-page">
    <!-- wrapper -->
    <div class='wrapper'>
      <#if user??>
        <#include "common/status.ftl">
      </#if>
      <!-- Main container. -->
      <div role="main" id="main">

      </div>
      <div id="user_info" class="side-box">
        <div class="info-profile">
          <div class="avatar">
            <a href="/user/${ouser.username}" title="${ouser.username}" class="img x">
              <img width="64px" height="64px" src="${ouser.profileImageUrl!""}"></a>
          </div>
          <div class="entry">
            <div class="name"><a href="/user/${ouser.username}">${ouser.username}</a></div>
            <p>
              <!-- 其他用户展示已绑定平台的图标，点击跳转个平台 -->
              <!-- 个人展示页面未绑定图标灰色：无 "oauth"，点击跳转授权 -->
              <span class="from sina oauth">
                  <a title="访问新浪微博" href="http://weibo.com/1867649773" target="_blank">新浪微博</a>
              </span>
              <span class="from qq oauth">
                <a title="访问腾讯微博" href="http://t.qq.com/qatest2" target="_blank">腾讯微博</a>
              </span>
              <span class="from instagram">
                <a title="添加Instagram授权登录" href="/connect/instagram">instagram</a>
              </span>
              <span class="from renren">
                <a title="添加人人授权登录" href="/connect/renren">renren</a>
              </span>
              <span class="from douban">
                <a title="添加豆瓣授权登录" href="/connect/douban">douban</a>
              </span>
              <span class="from wordpress">
                <a title="绑定wordpress" href="/bind/wordpress">wordpress</a>
              </span>
              <span class="from twitter">
                <a title="添加twitter授权登录" href="/connect/twitter">twitter</a>
              </span>
            </p>
          </div>
        </div>
        <!-- 个人简介 -->
        <div class="info_inner">
          <p>
          ${ouser.desription!""}
          </p>
        </div>
      </div>
      <!-- Application source. -->
    </div>
    </@body>
  </@html>
</#escape>

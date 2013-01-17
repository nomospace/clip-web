<#assign page_name="index"/>
<#include "common/module.ftl"/>
<#escape x as x?html>
  <@html>
    <@body class="index-page">
    <!-- navbar -->
    <div class='navbar'>
      <div class='navbar-inner'>
        <div class='container'>
          <a class='brand' href='/'>ClipWeb</a>
          <ul class='nav pull-right'>
            <li><a href='/'>首页</a></li>
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" data-action="popup-users">账户<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li>
                  <a href="/setup">设置</a>
                </li>
                <li>
                  <a href="/notifications">提醒</a>
                </li>
                <li>
                  <a href="/help">帮助</a>
                </li>
                <li>
                  <a href="/signout">退出</a>
                </li>
              </ul>
            </li>
            <li><a href='/signin'>登录</a></li>
          </ul>
          <a class="fork2github" href="https://github.com/nomospace/clip-web">
            <img src="/static/images/octocat-icon.png" alt="Fork me on GitHub">
          </a>
        </div>
      </div>
    </div>
    <!-- wrapper -->
    <div class='wrapper'>
      <div>
        <!-- Main container. -->
        <div role="main" id="main"></div>
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
        <!-- Application source. -->
      </div>
      <div class='cl cr'></div>
    </div>
    </@body>
  </@html>
</#escape>

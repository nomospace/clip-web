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
    <div id='wrapper'>
      <div>
        <!-- Main container. -->
        <div role="main" id="main"></div>
        <!-- Application source. -->
      </div>
      <div class='cl cr'></div>
    </div>
    </@body>
  </@html>
</#escape>

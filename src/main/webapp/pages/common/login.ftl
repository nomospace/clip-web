<#if !user??>
<section id="J_login_overlay" class="nav-login" style="top: 45px;display: none;">
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
</section>
<#else>
<section class="wrapper">
  <p class="alert alert-info">
    您已登录 ${user.name!""}
  </p>
</section>
</#if>

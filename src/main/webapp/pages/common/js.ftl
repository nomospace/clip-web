<#include "template.ftl">
<script src="${cfg_base_js_base}/require-jquery.js"></script>
<script src="${cfg_base_js_base}/bootstrap.js"></script>
<script src="${cfg_base_js_base}/underscore.js"></script>
<script src="${cfg_base_js_base}/handlebars.js"></script>
<script>
  clip = {
    cfg: {
      version: '${cfg_version}',
      token: {
      <#if token?exists>
        accessToken: '${token!""}'
      <#--expireIn: '${weiboToken.expireIn!""}',-->
      <#--refreshToken: '${weiboToken.refreshToken!""}',-->
      <#--uid: '${weiboToken.uid!""}'-->
      </#if>
      }
    },
    url: {
      common: '${cfg_context_path}/common/'
    },
    fn: {},
    mod: {}
  };
  require.config({
    baseUrl: '${cfg_base_js_module}',
    deps: ['base', '${page_name!"index"}'],
    paths: {
      'css': '${cfg_base_css}'
    },
    shim: {
    },
  <#--urlArgs: 'v=${cfg_version_js}'-->
    urlArgs: 'v=' + (+new Date()) + '${cfg_version_js}'
  });
</script>

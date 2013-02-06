<#include "template.ftl">
<script src="${cfg_base_js_base}/require-jquery.js"></script>
<#--<script src="${cfg_base_js_base}/backbone.marionette.js"></script>-->
<script src="${cfg_base_js_base}/bootstrap.js"></script>
<#--<script src="${cfg_base_js_base}/jquery.json/jquery.json.js"></script>-->
<script src="${cfg_base_js_base}/underscore.js"></script>
<#--<script src="${cfg_base_js_base}/mustache.js"></script>-->
<#-- requirejs 配置  -->
<script>
  clip = {
    cfg: {
      version: '${cfg_version}',
      weiboToken: {
      <#if weiboToken?exists>
        accessToken: '${weiboToken!""}'
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
    paths: {
      'css': '${cfg_base_css}',
      'module': '${cfg_base_js_module}',
      'text': '${cfg_base_js_base}/require-text',
      'order': '${cfg_base_js_base}/require-order',
      'cookie': '${cfg_base_js_widget}/jquery.cookie',
      'store': '${cfg_base_js_widget}/store',
      'handlebars': '${cfg_base_js_widget}/handlebars',
      'zclip': '${cfg_base_js_widget}/jquery.zclip'
    },
  <#--urlArgs: 'v=${cfg_version_js}'-->
    urlArgs: 'v=' + (+new Date()) + '${cfg_version_js}'
  });

  require(['module/index']);
</script>
<#--/ requirejs 配置  -->

<#-- google 统计 -->

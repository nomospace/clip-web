<#include "template.ftl">
<#if cfg_develop>
<script src="${cfg_base_js_base}/require-jquery.js"></script>
<#--<script src="${cfg_base_js_base}/jquery.json/jquery.json.js"></script>-->
<script src="${cfg_base_js_base}/underscore.js"></script>
<#--<script src="${cfg_base_js_base}/mustache.js"></script>-->
<#else>
<script src="${cfg_base_js}/lib/lib-pack.js"></script>
</#if>
<#-- requirejs配置  -->
<script>
  clip = {
    cfg: {
      version: '${cfg_version}'
    },
    url: {
      common: '${cfg_context_path}/common/'
    },
    fn: {},
    mod: {}
  };
  require.config({
    paths: {
      // 外部module
      'css': '${cfg_base_css}',
      'module': '${cfg_base_js_module}',
      'text': '${cfg_base_js_base}/require-text',
      'order': '${cfg_base_js_base}/require-order',
      // 业务module
      // infrastructure
      // widgets & utilities
      'cookie': '${cfg_base_js_widget}/jquery.cookie',
      'store': '${cfg_base_js_widget}/store',
      'handlebars': '${cfg_base_js_widget}/handlebars',
      'zclip': '${cfg_base_js_widget}/jquery.zclip'
    },
    urlArgs: 'v=${cfg_version_js}'
  <#--urlArgs: 'v=' + (+new Date())+ '${cfg_version_js}'-->
  });

  require(['module/index']);
</script>
<#--/ requirejs配置  -->

<#-- google统计 -->

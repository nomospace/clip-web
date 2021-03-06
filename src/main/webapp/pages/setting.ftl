<#assign page_name="setting"/>
<#include "common/module.ftl"/>
<#escape x as x?html>
  <@html>
    <@body class="setting-page">
    <div class="wrapper">
      <div class="row-fluid">
        <div class="form-horizontal span8">
          <h3>账户设置</h3>
          <fieldset>
            <legend>设置个性域名</legend>
            <input type="text" id="J_name" value="${user.name!""}">
            <button type="submit" class="btn btn-primary" id="J_name_btn">设置</button>
          <span class="help-block">
            你的个性域名 xxx, 数字字母和.-_ <br>
            然后就可以 http://localhost:3002/xxx 来访问个人页面了(设置后无法变更)
          </span>
          </fieldset>
          <fieldset>
            <legend>我的信息</legend>
            <div class="control-group">
              <label class="control-label">clip_id：</label>
              <div class="controls">
                <span class="help-inline">${user.id!""}</span>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">注册时间：</label>
              <div class="controls">
                <span class="help-inline">${user.time?number_to_date}</span>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">电子邮件：</label>
              <div class="controls">
                <input type="text" id="J_email" value="${user.email!""}">
              </div>
              <div class="controls">
                <span class="help-block">定期发送更新好的 PDF 版本和历史上的今天提醒<br>(建议把 help@clip.me 添加到你的邮件联系人中)</span>
              </div>
            </div>
            <div class="control-group">
              <div class="controls">
                <button type="submit" class="btn btn-primary" id="J_email_btn">保存设置</button>
              </div>
            </div>
          </fieldset>
          <#--<fieldset>-->
            <#--<legend>邮件提醒</legend>-->
            <#--<span class="help-block">clip 会通过 email 提醒一些与你有关的事情</span>-->
            <#--<div class="control-group">-->
              <#--<label class="control-label">历史上的今天：</label>-->
              <#--<div class="controls" id="J_remind">-->
                <#--<label class="radio">-->
                  <#--<input type="radio" name="today_in_history" value="1" <#if !user.remind??||user.remind==1>checked</#if>>接受</label>-->
                <#--<label class="radio">-->
                  <#--<input type="radio" name="today_in_history" value="0" <#if user.remind??&&user.remind==0>checked</#if>>不接受</label>-->
              <#--</div>-->
            <#--</div>-->
            <#--<div class="control-group">-->
              <#--<div class="controls">-->
                <#--<button type="submit" class="btn btn-primary" id="J_remind_btn">修改提醒设置</button>-->
              <#--</div>-->
            <#--</div>-->
          <#--</fieldset>-->
          <fieldset>
            <legend>隐私设置</legend>
            <label class="checkbox">
              <input type="checkbox" id="inlineCheckbox1" value="option1"> 公开
            </label>
            <label class="checkbox">
              <input type="checkbox" id="inlineCheckbox2" value="option2"> 登录用户可见
            </label>
            <label class="checkbox">
              <input type="checkbox" id="inlineCheckbox3" value="option3"> 仅自己可见
            </label>
            <span class="help-block">clip 的信息流默认所有用户可见，你可以方便的分享给其他用户，作为自己的 about 页面是个很不错的选择</span>
            <div class="control-group">
              <div class="controls">
                <button type="submit" class="btn btn-primary">修改隐私设置</button>
              </div>
            </div>
          </fieldset>
        </div>
      </div>
    </div>
    </@body>
  </@html>
</#escape>

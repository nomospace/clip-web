<#if statuses?has_content>
<ol id="J_status_list" class="row clearfix">
  <#list statuses as status>
    <#if status?has_content&&status.user?has_content&&status.source?has_content>
      <li class="status-body span4">
        <a class="status-avatar" href="/user/${user.name!""}"><img src="${status.user.profileImageUrl!""}" alt="${status.user.name!""}"></a>
        <div class="status-date">
          <p>${status.user.name!""}</p><p>${status.createdAt?date}</p>
        </div>
        <p>${status.text!""}</p>
        <#if status.thumbnailPic?has_content>
          <img src="${status.thumbnailPic!""}" alt="">
        </#if>
        <#if status.retweetedStatus?has_content>
          <#assign _status=status.retweetedStatus/>
          <div class="status-retweet">
            //<a href="/user/${_status.user.name!""}">@${_status.user.name!""}</a>
            <p>${_status.text!""}</p>
            <#if _status.thumbnailPic?has_content>
              <p><img src="${_status.thumbnailPic!""}"></p>
            </#if>
          </div>
        </#if>
        <p>来自 <a href="${status.source.url!""}" target="_blank">${status.source.name!""}</a></p>
      </li>
    </#if>
  </#list>
</ol>
</#if>

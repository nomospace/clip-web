<#if statuses?has_content>
<ol class="row">
  <#list statuses as status>
    <#if status?has_content&&status.user?has_content&&status.source?has_content>
      <li class="status-body span4">
        <a class="status-avatar" href="/user/${user.username!""}"><img src="${status.user.profileImageUrl!""}" alt="${status.user.name!""}"></a>
        <div class="status-date">
          <p>${status.user.name!""}</p><p>${status.createdAt?date}</p>
        </div>
        <p>${status.text!""}</p>
        <#if status.thumbnailPic?has_content>
          <img src="${status.thumbnailPic!""}" alt="">
        </#if>
        <p>来自 <a href="${status.source.url!""}" target="_blank">${status.source.name!""}</a></p>
      </li>
    </#if>
  </#list>
</ol>
</#if>

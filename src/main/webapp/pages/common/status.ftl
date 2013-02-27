<#list statuses as status>
  <#if status?has_content&&status.user?has_content&&status.source?has_content>
  <section>
    <a href="/user/${user.username!""}"><img src="${status.user.profileImageUrl!""}" alt="${status.user.name!""}"></a>
    <span>${status.user.name!""}</span><span>${status.createdAt?date}</span>
    <p>${status.text!""}</p>
    <#if status.thumbnailPic?has_content>
      <img src="${status.thumbnailPic!""}" alt="">
    </#if>
    <p>来自 <a href="${status.source.url!""}" target="_blank">${status.source.name!""}</a></p>
  </section>
  </#if>
</#list>

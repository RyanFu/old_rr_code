${"\r"}
<#compress>
<#assign page = "1"/>
<#assign num = 0/>
<#list count as c>
	<#assign num=num+c?number>
</#list>
<#include "1,0">
<li id="feed${id[0]}">
	<#include "1,4">
	<h3>
		<a href="http://renren.com/profile.do?id=${fID[0]}&ref=${vType}" target="_blank">${fName[0]}</a>
		<#if idef?exists><img lala="http://xnimg.cn/imgpro/icons/follow.gif" src="http://s.xnimg.cn/a.gif" onload="feed_img_delay_load(this);" title="通过人人真实身份验证"/></#if>
		上传了${num}张照片至 <a href="http://page.renren.com/photo/album?id=${aID[0]}&owner=${fID[0]}&ref=${vType}" target="_blank">${aName[0]}</a>
	</h3>
	<div id="moreThumbnails${id[0]}" class="content">
		<#list src as aSrc>
			<#if 3==aSrc_index><#break></#if>
			<a href="http://page.renren.com/photo/photo?id=${pID[aSrc_index]}&owner=${fID[0]}&ref=${vType}" target="_blank"><img width="100" lala="${aSrc}" src="http://s.xnimg.cn/a.gif" onload="feed_img_delay_load(this);" class="photo"/></a>
		</#list>
	</div>
	<div class="details">
		<div class="legend">
			<img alt="相册" src="http://s.xnimg.cn/a.gif"" class="iPhoto"/>
			<span class="duration"><@format>${time[0]}</@format></span>
			<#include "1,5">
		</div>
	</div>
<#include "1,1">
</li>
</#compress>
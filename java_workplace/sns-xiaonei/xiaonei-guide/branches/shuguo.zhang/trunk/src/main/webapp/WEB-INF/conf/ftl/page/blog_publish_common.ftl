${"\r"}
<#compress>
<#assign page = "1"/>
<#include "1,0">
<li id="feed${id[0]}">
	<#include "1,4">
	<h3>
		<a href="http://renren.com/profile.do?id=${fID[0]}&ref=${vType}">${fName[0]}</a> 
		评论了
		<#if uID[0] == fID[0]>
			自己
		<#else>
			<a href="http://renren.com/profile.do?id=${uID[0]}&ref=${vType}">${uName[0]}</a>
		</#if>
		的日志 <a href="${(url[0])?replace('xiaonei.com','renren.com')}&ref=${vType}" target="_blank">${title[0]}</a>
	</h3>
	<div class="content">
		<#if comm?exists && comm[0] != ""><p class="comment"><q>${comm[0]}</q></p></#if>
	</div>
	<div class="details">
		<div class="legend">
			<img src="http://s.xnimg.cn/a.gif"" alt="评论" class="iPost" />
			<span class="duration"><@format>${time[0]}</@format></span>
			<#include "1,5">
		</div>
	</div>
<#include "1,1">
</li>
</#compress>
${"\r"}
<#compress>
<#assign page = "1"/>
<#include "1,0">
<li id="feed${id[0]}">
	<div class="figure">
		<a class="avatar" href="http://renren.com/profile.do?id=${fID[0]}&ref=${vType}" target="_blank"><img needclip="1" width="50" height="50" lala="${(h[0])!"about:blank"}" src="http://s.xnimg.cn/a.gif" onload="feed_img_delay_load(this);" /></a>
	</div>
	<h3>
		<#list fName as n>
			<a target="_blank" href="http://renren.com/profile.do?id=${fID[n_index]}&ref=${vType}">${n}</a>
		</#list> 分享俱乐部 <a target="_blank" href="http://org.renren.com/${uID[0]}?ref=${vType}">${uName[0]}</a>
	</h3>
	<div class="content">
		<div class="figure">
			<a target="_blank" href="http://org.renren.com/${uID[0]}?ref=${vType}"><img lala="${ (src[0])!"about:blank"}" src="http://s.xnimg.cn/a.gif" onload="feed_img_delay_load(this);" alt="${uName[0]}"/></a>
		</div>
		<#if summary?exists && summary[0] != "" ><p><strong>${summary[0]}</strong></p></#if>
		<#if comm?exists && comm[0] != ""><p class="comment"><q>${comm[0]}</q></p></#if>
	</div>
	<div class="details">
		<div class="legend">
			<img src="http://s.xnimg.cn/a.gif"" alt="分享" class="iShare" />
			<span class="duration"><@format>${time[0]}</@format></span>
			<a href="http://org.renren.com/pa/bf?pid=${uID[0]}&ref=${vType}" target="_blank">${bfName[0]}</a>
			<span class="seperator">|</span>
			<a href="http://share.renren.com/share/ShareList.do?id=${fID[0]}&share_id=${sID[0]}#share_${sID[0]}" target="_blank">评论该分享</a>
		</div>
	</div>
<#include "1,1">
</#compress>
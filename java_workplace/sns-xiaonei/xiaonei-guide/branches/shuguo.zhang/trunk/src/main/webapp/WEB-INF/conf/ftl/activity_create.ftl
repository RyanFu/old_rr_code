${"\r"}
<#compress>
<#include "1,0">
<li id="feed${id[0]}">
	<#include "1,4">
	<h3>
		<a href="http://renren.com/profile.do?id=${fID[0]}&ref=${vType}" target="_blank">${fName[0]}</a> 创建活动 <a href="${(url[0])?replace('xiaonei.com','renren.com')}&ref=${vType}" target="_blank">${title[0]}</a>
	</h3>
	<div class="content">
		<a href="${(url[0])?replace('xiaonei.com','renren.com')}&ref=${vType}" target="_blank"><img class="avatar" src="${(src[0])!"about:blank"}"/></a>
		<p>类型: ${cate[0]}</p>
		<p>地点: ${pos[0]}</p>
		<p>描述: ${desc[0]}</p>
	</div>
	<div class="details">
		<div class="legend">
			<img src="http://xnimg.cn/a.gif" alt="活动" class="iActs" />
			<span class="duration"><@format>${time[0]}</@format></span>
		</div>
	</div>
<#include "1,1">
</li>
</#compress>

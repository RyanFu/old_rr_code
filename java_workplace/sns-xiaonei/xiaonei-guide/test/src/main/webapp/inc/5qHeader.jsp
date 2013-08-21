<%@ page pageEncoding="GBK" %>
<div id="header">
	<h1 id="logo"><a href="${applicationScope.urlHome}/Home.do" title="${domainIdentified["siteName"]}"><img src="${applicationScope.urlSmallStatic}/img/logo-mini.gif" alt="${domainIdentified["siteName"]}(Logo)" /></a></h1>
	<div id="navigation">
		<h3>������:</h3>
		<ul>
			<li><a href="${applicationScope.urlHome}/Home.do">��ҳ</a></li>
			<li><a href="${applicationScope.urlBrowse}/SocialNet.do">��㿴��</a></li>
			<li><a href="${applicationScope.urlSchool}/university.do?id=${requestScope.visiter.univ}">ѧУ</a></li>
			<li><a href="${applicationScope.urlBlog}/BlogHome.do">��־</a></li>
			<li><a href="${applicationScope.urlPhoto}/freshalbum.do">���</a></li>
			<li><a href="${applicationScope.urlGroup}/RandomTribe.do">Ⱥ<c:if test="${requestScope.visiter.source == 5}">/Ȧ��</c:if></a></li>
			<li><a href="${applicationScope.urlEvent}/GetEventList.do?univ=${requestScope.visiter.univ}">�</a></li>
			<li><a href="${applicationScope.urlBrowse}/Search.do">����</a></li>
			<li><a href="${applicationScope.urlMain}/Invite.do">����</a></li>
		</ul>
	</div>
	<div id="utility">
		<c:choose>
			<c:when test="${empty requestScope.visiter}">
				<html:form method="post" action="/Login.do" onsubmit="getEl('email').value=Trim(getEl('email').value);" focus="email">
					<input type="hidden" name="origURL" value="${requestScope.origURL}" />
					<label class="label" for="email">�ʺ�email:</label><html:text property="email" styleClass="inputtext" styleId="email" size="12" maxlength="20" />
					<label class="label" for="password">����:</label><input type="password" name="password" id="password" class="inputtext" size="12" maxlength="20" />
					<input type="submit" id="login" name="submit" class="inputbutton" value="��¼" />
					<input type="button" id="register" name="register" class="inputbutton" value="ע��" onclick="document.location='${applicationScope.urlMain}/Register.do'" />
				</html:form>
			</c:when>
			<c:otherwise>
				<%--��ã�--%><a href="${applicationScope.urlMain}/BasicInfo.do">${requestScope.visiter.name}</a><span class="pipe">|</span><a href="${applicationScope.urlMain}/info/Help.do">����</a><span class="pipe">|</span><a href="${applicationScope.urlMain}/Logout.do">�˳�</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>

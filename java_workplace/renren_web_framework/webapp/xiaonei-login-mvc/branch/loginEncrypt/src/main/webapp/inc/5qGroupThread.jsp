<%@ page pageEncoding="GBK" %>
<c:choose>
	<c:when test="${(requestScope.tribe.access == 1) && (requestScope.isMember > 3) && (!requestScope.flag)}">
		<div class="attention"><a href="${applicationScope.urlGroup}/GetTribe.do?id=${requestScope.tribe.id}&amp;curpage=0">${requestScope.tribe.name}</a>��˽��Ⱥ���ǳ�Ա�����Ķ�Ⱥ���⡣<br /><a href="${applicationScope.urlGroup}/RequestTribe.do?id=${requestScope.tribe.id}">�������Ⱥ</a></div>
	</c:when>
	<c:when test="${(requestScope.tribe.access == 1) && (requestScope.isMember == 3) && (!requestScope.flag)}">
		<div class="attention">���Ѿ��������<a href="${applicationScope.urlGroup}/GetTribe.do?id=${requestScope.tribe.id}&amp;curpage=0">${requestScope.tribe.name}</a>����ȴ���׼������Ⱥ����Ա���ã��ڻ����׼֮ǰ���������Ķ�Ⱥ���⡣</div>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${empty requestScope.label}">
				<c:set var="threadCount" value="${requestScope.tribe.threadCount}" />
			</c:when>
			<c:otherwise>
				<c:set var="threadCount" value="${requestScope.label.threadCount}" />
			</c:otherwise>
		</c:choose>
		<c:if test="${requestScope.tribe.labelCount>0}">
			<ul id="tabs">
				<li class="<c:if test='${!empty requestScope.label}'>in</c:if>activetab"><a href="${applicationScope.urlGroup}/GetThreadList.do?id=${requestScope.tribe.id}&amp;label=0">����</a></li>
				<c:forEach var="item" items="${requestScope.labelList}">
					<li class="<c:if test='${item.id != requestScope.label.id}'>in</c:if>activetab"><a href="${applicationScope.urlGroup}/GetThreadList.do?id=${requestScope.tribe.id}&amp;label=${item.id}">${item.name}</a></li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${threadCount > 30}">
			<c:set var="navUrl" value="GetThreadList.do" />
			<c:set var="navUrlEnd" value="&amp;id=${requestScope.tribe.id}&amp;label=${requestScope.label.id}" />
			<c:set var="navCount" value="${threadCount}" />
			<div class="page">
				<c:set var="itemPerPage" value="30" />
				<%@ include file="/inc/pnav_GBK.inc" %>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${threadCount>0}">
				<table class="threadlist">
					<tr>
						<th class="threadTitle">����</th>
						<th class="threadAuthor">����</th>
						<th class="threadStat">�ظ�<!-- / �Ķ�--></th>
						<th class="threadResponder">���ظ�</th>
					</tr>
					<c:set var="row" value="odd" />
					<c:forEach var="item" items="${requestScope.threadList}">
						<tr class="${row}">
							<td class="threadTitle"><c:if test="${item.type==5}"><img src="${applicationScope.urlSmallStatic}/img/headtopic.gif" alt="�ö�" /> </c:if><a href="${applicationScope.urlGroup}/GetThread.do?id=${item.id}&amp;parentpage=${currentPage}&amp;curpage=0&amp;label=${requestScope.label.id}&amp;tribeId=${requestScope.tribe.id}">${item.title}</a></td>
							<td class="threadAuthor"><a href="${applicationScope.urlMain}/profile.do?id=${item.owner}">${item.ownerName}</a></td>
							<td class="threadStat">${item.postCount - 1}<!-- / ${item.viewCount}--></td>
							<td class="threadResponder"><span class="time"><fmt:formatDate value="${item.lastTime}" type="both" pattern="MM-dd HH:mm" /></span> | <a href="${applicationScope.urlMain}/profile.do?id=${item.editor}">${item.editorName}</a></td>
						</tr>
						<c:choose>
							<c:when test="${row == 'odd'}">
								<c:set var="row" value="even" />
							</c:when>
							<c:otherwise>
								<c:set var="row" value="odd" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div class="attention">
						<c:if test="${!empty requestScope.label}">���ְ�</c:if>
						��ʱû������</div>
			</c:otherwise>
		</c:choose>
		<c:if test="${navCount > 30}">
			<div class="page">
				<c:set var="itemPerPage" value="30" />
				<%@ include file="/inc/pnav_GBK.inc" %>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${(requestScope.tribe.access == 2) && (requestScope.isMember > 3)}">
		<div class="attention"><a href="${applicationScope.urlGroup}/GetTribe.do?id=${requestScope.tribe.id}&amp;curpage=0">${requestScope.tribe.name}</a> �ǰ빫��Ⱥ���ǳ�Ա�����Ķ�Ⱥ���⵫���ܷ���ͻظ��������<a href="${applicationScope.urlGroup}/RequestTribe.do?id=${requestScope.tribe.id}">�������Ⱥ</a></div>
	</c:when>
	<c:when test="${(requestScope.tribe.access == 2) && (requestScope.isMember == 3)}">
		<div class="attention">���Ѿ��������<a href="${applicationScope.urlGroup}/GetTribe.do?id=${requestScope.tribe.id}&amp;curpage=0">${requestScope.tribe.name}</a>����ȴ���׼������Ⱥ����Ա���ã��ڻ����׼֮ǰ���������Ķ�Ⱥ���⵫���ܷ���ͻظ���</div>
	</c:when>
	<c:when test="${(requestScope.tribe.access == 3) || (requestScope.isMember <= 1)}">
		<script type="text/javascript">
		var isClicked = false;
		function onReferClick()
		{
			isClicked = true;
			if(isAllowed)
				getEl('textedit').focus();
		}
		function for5qtopic(){
		  subnumstr = new String(getEl("subject").value);
			if (subnumstr.length ==0)
			{
				alert('������ⲻ��Ϊ��');
				getEl("subject").focus();
				return false;
			}
			if(getEl('body')==null||getEl('body')==""){
			    alert("���ݲ���Ϊ��!") ;
			    return false ;
			}
			var numsub = getEl("theFile").value.length;
			isAttEmptyGoCommSubmit(numsub);			
			return true ;
			
		}
		var fileCtrlLen;
		function isAttEmptyGoCommSubmit(fileCtrlLen)
		{
			if(fileCtrlLen == 0) //û�и���
				document.editorForm.action = "${applicationScope.urlGroup}/gbkPost.do";
			else
				document.editorForm.action = "${applicationScope.urlUpload}/gbkPost.do";
				
			document.editorForm.submit;
		}		
		</script>
		<form action="${applicationScope.urlUpload}/gbkPost.do" method=post name="editorForm" enctype="multipart/form-data" onsubmit="return for5qtopic();">
			<p><label for="subject">������:</label><input type="text" class="inputtext" id="subject" name="subject" /></p>
			<c:if test="${requestScope.tribe.labelCount > 0}">
				<p><label for="label">�ְ�:</label>
					<select name="label" id="label" class="select">
						<option value="0">����</option>
						<c:forEach var="item" items="${requestScope.labelList}" >
							<option value="${item.id}" <c:if test='${requestScope.label.id==item.id}'>selected="selected" </c:if>>${item.name}</option>
						</c:forEach>
					</select>
				</p>
			</c:if>
			<%-- div id="actionMenu"><a href="#" onclick="return dlgAction('img','textedit')"><img src="${applicationScope.urlSmallStatic}/img/blog/image.gif" alt="[����ͼƬ]" /></a><a href="#" onclick="return dlgAction('link','textedit')"><img src="${applicationScope.urlSmallStatic}/img/blog/hyperlink.gif" alt="[��������]" /></a><a href="#" onclick="getEl('theFileDiv').style.display='inline';return false;"><img src="${applicationScope.urlSmallStatic}/img/paperclip.gif" alt="[�ϴ�ͼƬ���ļ�]" /></a><span id="theFileDiv"> <html:file property="theFile" styleId="theFile" size="25" /> <input type="button" onclick="getEl('theFileDiv').style.display='none'" value="ȡ��" /></span></div>
			<div class="resizable-textarea">
				<textarea id="textedit" name="body" cols="100%" rows="10" class="resizable" onfocus="if(isClicked){cc(event); isClicked=false}"></textarea>
			</div --%>
			<textarea name="body" id="body" cols="100%" rows="10" style="display:none"></textarea>
			<%@ include file="/inc/editor.inc" %>
			<div id="upFile">
			�ϴ��ļ���ͼƬ��<a href="${applicationScope.urlProfile}/info/Help.do#help1_30" target="_blank">�鿴֧������</a>�� <input type="file" name="theFile" id="theFile" />
			</div>
			<p>
				<input type="hidden" name="tribe" value="${requestScope.tribe.id}" />
				<input type="hidden" name="curpage" value="${currentPage}" />
				<input type="hidden" name="ak" value="${requestScope.ak}" />				
				<input type="submit" id="textEditSubBtn" name="submit" class="subbutton" value="��������" />
			</p>
		</form>
	</c:when>
	<c:when test="${(requestScope.tribe.access == 1) && (requestScope.isMember >= 2)}">
		<div class="attention"><a href="${applicationScope.urlGroup}/GetTribe.do?id=${requestScope.tribe.id}&amp;curpage=0">${requestScope.tribe.name}</a> ��˽��Ⱥ�������<a href="${applicationScope.urlGroup}/RequestTribe.do?id=${requestScope.tribe.id}">�������Ⱥ</a></div>
	</c:when>
</c:choose>

<%@ page contentType="text/html; charset=UTF-8" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><div class="list_item"><div class="container" id="0000000012"><c:if test="${editmenu}"><a onmousedown="new moveable_app(this.parentNode, null);" class="handle" href="#"/></c:if><a class="link_title" onmousedown="new track_moveable(this.parentNode, this);" href="${applicationScope.urlTeam}/wt_myteam.do" style="background-image:url(${applicationScope.urlSmallStatic}/img/newsfeed/team.gif)">Team</a></div></div>
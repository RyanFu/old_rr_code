<%@ page contentType="text/html; charset=UTF-8" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><div class="list_item"><div class="container" id="0000000010"><c:if test="${editmenu}"><a onmousedown="new moveable_app(this.parentNode, null);" class="handle" href="#"/></c:if><a class="link_title" onmousedown="new track_moveable(this.parentNode, this);" href="${applicationScope.urlCourse}/Course.do" style="background-image:url(${applicationScope.urlSmallStatic}/img/newsfeed/course.gif)">课程</a></div></div>
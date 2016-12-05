<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>
</head>
<body>

<h1>This is board!!!</h1>

<div>

</div>

<table>
	<tr>
		<th>번호</th><th>제목</th><th>작성자</th><th>읽은수</th><th>리플수</th>
	</tr>
	<c:forEach items="${list}" var="boardVO">
	<tr>
		<td align="center">${boardVO.getBno()}</td>
		<td align="center">
			<a href="read?bno=${boardVO.getBno()}&page=${cri.getPage()}&articlePerPage=${cri.getArticlePerPage()}">
			${boardVO.getTitle()}</a>
		</td>
		<td align="center">${boardVO.getWriter()}</td>
		<td align="center">${boardVO.getViewcnt()}</td>
		<td align="center">${boardVO.getReplycnt()}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5" align="center">
		<c:forEach var="i" begin="0" end="8">
			<c:choose>
				<c:when test="${cri.getPage() eq i+cri.getPageStart()}">
					${i + cri.getPageStart()}
				</c:when>
				<c:otherwise>
					<c:if test="${i+cri.getPageStart() <= cri.getMaxPage()}">
						<a href="/board/list?page=${i + cri.getPageStart()}&articlePerPage=10">
						${i + cri.getPageStart()}</a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</td>
	</tr>
</table>

</body>
</html>
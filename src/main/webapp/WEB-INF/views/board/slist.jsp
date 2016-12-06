<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>

	$(document).ready(function() {
		$("#searchBtn").on("click", function(event) {
			event.preventDefault();
			self.location = "/board/slist?"
							+ "searchType=" + $("select option:selected").val()
							+ "&keyword=" + $("#keyword").val();
		});
		
		$("#writeBtn").on("click", function(event) {
			event.preventDefault();
			self.location = "/board/write";
		});
	});

</script>

</head>
<body>

<h1>This is board!!!</h1>

<div>
	<select name="searchType">
		<option value="n"
			<c:out value="${cri.searchType == null?'selected':''}"/>>
			---</option>
		<option value="t"
			<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
			Title</option>
		<option value="c"
			<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
			Content</option>
		<option value="w"
			<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
			Writer</option>
		<option value="tc"
			<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
			Title OR Content</option>
		<option value="cw"
			<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
			Content OR Writer</option>
		<option value="tcw"
			<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
			Title OR Content OR Writer</option>
	</select>
	<input id="keyword" type='text' name='keyword' value="${cri.getKeyword()}">
	<input id="searchBtn" type="submit" value="검색">
	<input id="writeBtn" type="submit" value="글쓰기">
</div>

<table>
	<tr>
		<th>번호</th><th>제목</th><th>작성자</th><th>읽은수</th><th>리플수</th>
	</tr>
	<c:forEach items="${list}" var="boardVO">
	<tr>
		<td align="center">${boardVO.getBno()}</td>
		<td align="center">
			<a href="read?bno=${boardVO.getBno()}&page=${cri.getPage()}&articlePerPage=${cri.getArticlePerPage()}&searchType=${cri.getSearchType()}&keyword=${cri.getKeyword()}">
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
						<a href="/board/slist?page=${i + cri.getPageStart()}&articlePerPage=${cri.getArticlePerPage()}&searchType=${cri.getSearchType()}&keyword=${cri.getKeyword()}">
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
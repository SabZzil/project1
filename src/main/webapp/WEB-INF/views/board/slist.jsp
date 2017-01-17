<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<title>List</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script>

	$(document).ready(function() {
	
		var st = $("#stInput").attr("value");
		
		$("#searchBtn").on("click", function(event) {
			event.preventDefault();
			self.location = "/board/slist?"
							+ "searchType=" + $("#stInput").attr("value")
							+ "&keyword=" + $("#keyword").val();
		});
		
		$("#writeBtn").on("click", function(event) {
			event.preventDefault();
			self.location = "/board/write";
		});
		
		$(".searchType").on("click", function(event) {
			event.preventDefault();
			$("#searchDropDown").html($(this).html()
					+"<span class='caret'></span>");
			$("#stInput").attr("value", $(this).attr("href"));
		});
		
		if(st=="") {
			$("#searchDropDown").html("검색"+"<span class='caret'></span>");
		} else if(st=="t") {
			$("#searchDropDown").html("제목"+"<span class='caret'></span>");
		} else if(st=="c") {
			$("#searchDropDown").html("내용"+"<span class='caret'></span>");
		} else if(st=="w") {
			$("#searchDropDown").html("작성자"+"<span class='caret'></span>");
		} else if(st=="tc") {
			$("#searchDropDown").html("제목 or 내용"+"<span class='caret'></span>");
		} else if(st=="cw") {
			$("#searchDropDown").html("내용 or 작성자"+"<span class='caret'></span>");
		} else if(st=="tcw") {
			$("#searchDropDown").html("제목 or 내용 or 작성자"+"<span class='caret'></span>");
		}
	});

</script>

</head>
<body>



<div class="col-md-6">
	<h1>게시판</h1>
<!-- 
	<div>
		<select class="btn btn-default" name="searchType">
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
-->
		
	<div>
		<div class="row">
			<div class="col-md-8">
				<div class="input-group"> 
					<div class="input-group-btn">
						<button id="searchDropDown" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							검색<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a class="searchType" href="t">제목</a></li>
							<li><a class="searchType" href="c">내용</a></li>
							<li><a class="searchType" href="w">작성자</a></li>
							<li><a class="searchType" href="tc">제목 or 내용</a></li>
							<li><a class="searchType" href="cw">내용 or 작성자</a></li>
							<li><a class="searchType" href="tcw">제목 or 내용 or 작성자</a></li>
						</ul>
					</div><!-- /btn-group -->
					<input id="stInput" type="hidden" value="${cri.searchType}">
					<input id="keyword" name="keyword" type="text" class="form-control" placeholder="검색 키워드" value="${cri.getKeyword()}">
					<span class="input-group-btn">
						<button id="searchBtn" class="btn btn-default" type="button">검색</button>
					</span>
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
			
			<div class="col-md-2"></div>
			
			<div class="col-md-2">
				<button id="writeBtn" class="btn btn-default">글쓰기</button>
			</div>
		</div>

<!-- 			
		<input id="keyword" type='text' name='keyword' value="${cri.getKeyword()}">
		<input id="searchBtn" type="submit" value="검색">
		<input id="writeBtn" type="submit" value="글쓰기">
-->		
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-condensed">
				<colgroup>
					<col style="width:10%">
					<col style="width:60%">
					<col style="width:20%">
					<col style="width:10%">
					
				</colgroup>
				<tr>
					<th>번호</th><th>제목</th><th>작성자</th><th>조회수</th>
				</tr>
				<c:forEach items="${list}" var="boardVO">
				<tr>
					<td>${boardVO.getBno()}</td>
					<td>
						<a href="read?bno=${boardVO.getBno()}&page=${cri.getPage()}&articlePerPage=${cri.getArticlePerPage()}&searchType=${cri.getSearchType()}&keyword=${cri.getKeyword()}">
						${boardVO.getTitle()} [${boardVO.getReplycnt()}]</a>
					</td>
					<td>${boardVO.getWriter()}</td>
					<td>${boardVO.getViewcnt()}</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="center">
					<div class="btn-group" role="group">
					<c:forEach var="i" begin="0" end="8">
						<c:choose>
							<c:when test="${cri.getPage() eq i+cri.getPageStart()}">
								<button type="button" class="btn btn-outline-primary">
									${i + cri.getPageStart()}
								</button>
							</c:when>
							<c:otherwise>
								<c:if test="${i+cri.getPageStart() <= cri.getMaxPage()}">
									<a class="btn btn-default" role="button" href="/board/slist?page=${i + cri.getPageStart()}&articlePerPage=${cri.getArticlePerPage()}&searchType=${cri.getSearchType()}&keyword=${cri.getKeyword()}">
										${i + cri.getPageStart()}
									</a>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</div>
					</td>
				</tr>	

			</table>
		</div>
	</div>

</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Read Article</title>

<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>

	$(document).ready(function(){
		var path = "/board/reply/${boardVO.getBno()}/1";
		getReplies(path);
		
		$("#addReply").on("click", function(event) {
			event.preventDefault();
			$(".replyInput").attr("style", "");
		});
		
		$("#addReplySubmit").on("click", function(event) {
			event.preventDefault();
			
			$.ajax({
				type : 'post',
				url : '/board/reply/add',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : $("#bno").val(),
					replyer : $("#replyer").val(),
					replytext : $("#replytext").val()
				}),
				success : function(result) {
					if (result == 'success') {
						alert("등록 되었습니다.");
						getReplies(path);
					}
				}
			});			
		});
		
		$("#reply").on("click", ".delBtn", function(event) {
			event.preventDefault();
			
			var rno = $(this).attr("href");
			
			$.ajax({
				type : 'delete',
				url : '/board/reply/delete/' + rno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : 'text',
				success : function(result) {
					if (result == 'success') {
						alert("삭제 되었습니다.");
						getReplies(path);
					}
				}
			});			

		});
		
		$("#pagenation").on("click", ".pagenum", function(event) {
			event.preventDefault();
			var p = $(this).attr("href");
			var pt = "/board/reply/${boardVO.getBno()}/" + p;
			getReplies(pt);
		});
		
		$.getJSON("/board/getAttach/"+$("#bno").val(),function(list){
			$(list).each(function(){
				
				var fileInfo = getFileInfo(this);
				
				var html = "<li><img src='" + fileInfo.imgsrc + "'/>"
				+ "<a href='" + fileInfo.getLink + "'>" + fileInfo.fileName + "</a>"
			//	+ " " + "<a href='" + fileInfo.fullName + "' class='delBtn'>X</a>" 
				+ "</li>";
				
				 $("#attachList").append(html);
				
			});
		});
		
	});
	
	function getReplies(path) {
		$.getJSON(path, function(data){
			var replies = "<table>";
			data.replyList.forEach(function(replyVO) {
				var dt = new Date(replyVO.regdate);
				var date = dt.getFullYear() + "-" 
						+ ("00" + (dt.getMonth() + 1)).slice(-2) + "-"
						+ ("00" + (dt.getDate())).slice(-2);
				replies += "<tr><td>" + replyVO.replyer + "</td>"
							+ "<td>" + replyVO.replytext +"</td>"
							+ "<td>"+ date + "</td>"
							+ "<td> </td>";
				if("${login.uid}"==replyVO.replyer) {
					replies += "<td><a class='delBtn' href='" + replyVO.rno
							+ "'>삭제</a></td>";
				}
							
							+ "</tr>";
			});
			replies += "</table>";
			$("#reply").html(replies);
			
			var cri = data.cri;
			
			var page = "";
			var start = Math.max(cri.replyPage-4, 1);
			var end = Math.min(cri.replyPage+4, cri.maxReplyPage);
			
			for(var i=start; i<=end; i++) {
				if(i==cri.replyPage) {
					page += i + " ";
				} else {
					page += "<a class='pagenum' href='" + i + "'>" + i + "</a>" + " ";
				}
			}
			$("#pagenation").html(page);
		
		});
	}

</script>


</head>
<body>

<table>
	<tr><td>번호</td><td>${boardVO.getBno()}</td></tr>
	<tr><td>제목</td><td>${boardVO.getTitle()}</td></tr>
	<tr><td>작성자</td><td>${boardVO.getWriter()}</td></tr>
	<tr><td>내용</td><td>${boardVO.getContent()}</td></tr>
</table>

<ul id="attachList"></ul>

<a href="slist?page=${cri.getPage()}&articlePerPage=${cri.getArticlePerPage()}&searchType=${cri.getSearchType()}&keyword=${cri.getKeyword()}">
목록으로
</a>

<form action="modifyA" method="post">
	<input type="hidden" name="bno" value="${boardVO.getBno()}"/>
	<input type="hidden" name="title" value="${boardVO.getTitle()}"/>
	<input type="hidden" name="content" value="${boardVO.getContent()}"/>
	<input type="hidden" name="writer" value="${boardVO.getWriter()}"/>
	<input type="hidden" name="page" value="${cri.getPage()}"/>
	<input type="hidden" name="articlePerPage" value="${cri.getArticlePerPage()}"/>
	<input type="hidden" name="articlePerPage" value="${cri.getArticlePerPage()}"/>
	<input type="hidden" name="searchType" value="${cri.getSearchType()}"/>
	<input type="hidden" name="keyword" value="${cri.getKeyword()}"/>
	
	<c:if test="${login.uid == boardVO.writer}">
		<input type="submit" value="수정하기">
	</c:if>

</form>

<div id="reply">reply</div>

<form id="addReplyForm" action="/board/reply/add" method="post">
	<input id="bno" type="hidden" name="bno" value="${boardVO.getBno()}" readonly/>
	<div class="replyInput" style="display:none">
		<c:if test="${not empty login}">
			<input id="replyer" type="text" name="replyer" value="${login.uid}" readonly/>
			<input id="replytext" type="text" name="replytext" placeholder="replytext"/>
			<input id="addReplySubmit" type="submit" value="등록">
		</c:if>
		<c:if test="${empty login}">
			please login.<br>
			<a href="/user/login">go login</a>
		</c:if>
	
	</div>
</form>

<input id="addReply" type="button" value="댓글추가"/>

<div id="pagenation">
</div>

</body>
</html>
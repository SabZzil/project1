<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Read Article</title>

<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

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
						$("#replyModal").modal("toggle");
						
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
		
		$("#reply").on("click", ".pagenum", function(event) {
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
			var replies = "<table id='replyTable' class='table table-condensed'>"
						+ "<caption>댓글</caption>"
						+ "<colgroup>"
							+ "<col style='width:15%'>"
							+ "<col style='width:60%'>"
							+ "<col style='width:16%'>"
							+ "<col style='width:9%'>"
						+ "</colgroup>";
			data.replyList.forEach(function(replyVO) {
				var dt = new Date(replyVO.regdate);
				var date = dt.getFullYear() + "-" 
						+ ("00" + (dt.getMonth() + 1)).slice(-2) + "-"
						+ ("00" + (dt.getDate())).slice(-2);
				replies += "<tr><td>" + replyVO.replyer + "</td>"
							+ "<td>" + replyVO.replytext +"</td>"
							+ "<td>"+ date + "</td>";
				if("${login.uid}"==replyVO.replyer) {
					replies += "<td><a class='delBtn' href='" + replyVO.rno
							+ "'>삭제</a></td>";
				} else {
					replies += "<td></td>";
				}
							
							+ "</tr>";
			});
			
			
			var cri = data.cri;
			
			var page = "<tr><td colspan='4' align='center'><div class='btn-group'>";
			var start = Math.max(cri.replyPage-4, 1);
			var end = Math.min(cri.replyPage+4, cri.maxReplyPage);
			
			for(var i=start; i<=end; i++) {
				if(i==cri.replyPage) {
					page += "<button class='btn btn-primay btn-xs'>" 
							+ i + "</button>";
				} else {
					page += "<a class='pagenum btn btn-default btn-xs' role='button' href='" + i + "'>" + i + "</a>" + " ";
				}
			}
			page += "</div></td><tr>";
			
			replies += page + "</table>";
			$("#reply").html(replies);
		});
	}

</script>


</head>
<body>

<div class="row">
	<div class="col-md-6">
		<table class="table table-condensed">
			<caption>게시글</caption>
			<colgroup>
				<col style="width:15%">
				<col style="width:85%">
			</colgroup>
			<tr><td>번호</td><td>${boardVO.getBno()}</td></tr>
			<tr><td>제목</td><td>${boardVO.getTitle()}</td></tr>
			<tr><td>작성자</td><td>${boardVO.getWriter()}</td></tr>
			<tr><td colspan="2">${boardVO.getContent()}</td></tr>
		</table>
	</div>
</div>

<div class="row">
	<ul id="attachList"></ul>
</div>



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
		<input class='btn btn-default' type="submit" value="본문수정">
	</c:if>

</form>

<div class="row">
	<div id="reply" class="col-md-6"></div>
</div>


<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">댓글 등록</h4>
      </div>
      <div class="modal-body">

        <form>
          <input id="bno" type="hidden" name="bno" value="${boardVO.getBno()}" readonly/>
		  <c:if test="${not empty login}">
          	<div class="form-group">
            	<label for="writer" class="control-label">replyer</label>
            	<input type="text" class="form-control" id="replyer" value="${login.uid}" readonly>
          	</div>
          	<div class="form-group">
            	<label for="message-text" class="control-label">Message</label>
            	<textarea class="form-control" id="replytext"></textarea>
          	</div>
          </c:if>
        </form>
			<c:if test="${empty login}">
				please login.<br>
				<a href="/user/login">go login</a>
			</c:if>



<!-- 
		<form id="addReplyForm" action="/board/reply/add" method="post">
			<input id="bno" type="hidden" name="bno" value="${boardVO.getBno()}" readonly/>
			<div class="replyInput">
				<c:if test="${not empty login}">
					<input id="replyer" type="hidden" name="replyer" value="${login.uid}" readonly/>
					<input id="replytext" type="text" name="replytext" placeholder="replytext"/>
				</c:if>
				<c:if test="${empty login}">
					please login.<br>
					<a href="/user/login">go login</a>
				</c:if>
			</div>
		</form>
-->
      </div>
      <div class="modal-footer">
        <button id="closeReplyModal" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <c:if test="${not empty login}">
        	<button id="addReplySubmit" type="button" class="btn btn-primary">Submit</button>
        </c:if>
      </div>
    </div>
  </div>
</div>

<!-- 
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
-->


<div class="row">
	<div class="col-md-5">
		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#replyModal">
			댓글작성
		</button>
	</div>

	<div class="col-md-1">
		<a class="btn btn-default" role="button" href="slist?page=${cri.getPage()}&articlePerPage=${cri.getArticlePerPage()}&searchType=${cri.getSearchType()}&keyword=${cri.getKeyword()}">
			목록으로
		</a>
	</div>
</div>



</body>
</html>
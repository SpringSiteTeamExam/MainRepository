<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="/resources/include/css/reply.css" />
<!-- jQuery Framework 참조하기 -->
<script type="text/javascript"
	src="/resources/include/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/include/js/common.js"></script>
<script type="text/javascript">
	var replyNum, message = "작성시 입력한 비밀번호를 입력해 주세요.", pwdConfirm = 0, btnKind = "";

	$(function() {
		/** 기본 덧글 목록 불러오기 */
		var b_num = "<c:out value='${detail.b_num}' />";
		listAll(b_num)

		/** 덧글 내용 저장 이벤트 */
		$("#replyInsert").click(function() {
			// 작성자 이름에 대한 입력여부 검사 
			if (!chkData("#r_name", "이름을"))
				return;
			else if (!chkData("#r_pwd", "비밀번호를"))
				return;
			else if (!chkData("#r_content", "내용을"))
				return;
			else {
				var insertUrl = "/replies/replyInsert.do";
				/** 글 저장을 위한 Post 방식의 Ajax 연동 처리 */
				$.ajax({
					url : insertUrl, //전송 url
					type : "post", // 전송 시 method 방식
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : "text",
					data : JSON.stringify({
						b_num : b_num,
						r_name : $("#r_name").val(),
						r_pwd : $("#r_pwd").val(),
						r_content : $("#r_content").val()
					}),
					error : function() {
						alert('시스템 오류 입니다.  관리자에게 문의 하세요');
					},
					success : function(resultData) {
						if (resultData == "SUCCESS") {
							alert("댓글 등록이 완료되었습니다.");
							dataReset();
							listAll(b_num);
						}
					}
				});
			}
		});
		
		/** 수정버튼 클릭시 수정폼 출력 */
		$(document).on("click", ".update_form", function() {
			$(".reset_btn").click();
			var currLi = $(this).parents("li");
			if(pwdConfirm==0){
				replyNum = currLi.attr("data-num");
				btnKind="upBtn";
				pwdView(currLi);
				}else if(pwdConfirm==1){
					var conText = currLi.children().eq(1).html();
					currLi.find("input[type='button']").hide();
					var conArea = currLi.children().eq(1);        
					conArea.html("");
					var data="<textarea name='content' id='content'>" + conText+"</textarea>";
					data+="<input type='button' class='update_btn'  value='수정완료'>";
					data+="<input type='button' class='reset_btn'  value='수정취소'>";
					conArea.html(data);
					}
			});
	});
</script>
</head>
<body>

</body>
</html>
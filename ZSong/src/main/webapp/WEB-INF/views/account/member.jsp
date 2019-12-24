<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%	
	session.setAttribute("location", "member");
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
	String grade = (String)session.getAttribute("grade");
%>
    


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

	    <script src="https://code.jquery.com/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>


    <style>

/*     	tbody{ */
/*     		background-color: #E9ECEF; */
/*     	}    	 */
    	
    	ul{
    		margin: auto;
    	}
    	
    	td{
    		text-align: center;
    	}

		.custom-select {
			width: 110px;
		}
    </style>

<script type="text/javascript">
	function home(){
		javascript:window.location='account_home';
	}
	function member(){
		javascript:window.location='member?page=1';
	}
	function manager(){
		javascript:window.location='manager?page=1';
	}
	function ban(){
		javascript:window.location='banMember?page=1';
	}
	function withdraw(){
		javascript:window.location='withdraw?page=1';
	}
	
	function ban_regist(id){
		var content = document.getElementById('content').value;

		  $.ajax({
			    type:'post',
			    url:'ban_access',
			    data: ({id:id, content:content}),

			    success:function(data){
				    if(data == 0)
					    alert("내용을 입력하세요.");
				    else
				    {
					    closeContent();
					    document.location.href="member?page=1";
				    }
				    
			    }

			 });
	}


	function withdraw_regist(id){
		  $.ajax({
			    type:'post',
			    url:'withdraw_access',
			    data: ({id:id, option:'1'}),

			    success:function(data){
				    if(data == 1)
				    	document.location.href="member?page=1";
			    }

			 });
	}
</script>


<style>
    .menu a{cursor:pointer;}
    .menu .hide{display:none;}
</style>
  
<script type="text/javascript">
    // html dom 이 다 로딩된 후 실행된다.
    $(document).ready(function(){
        // menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
        $(".menu>a").click(function(){
            var submenu = $(this).next("ul");
 
            // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
            if( submenu.is(":visible") ){
                submenu.slideUp();
            }else{
                submenu.slideDown();
            }
        });
    });
</script>



<!-- lightbox css -->

<style>

.mw_layer {

display: none;
position: fixed;
_position: absolute;
top: 0;
left: 0;
z-index: 10000;
width: 100%;
height: 100%
}

.mw_layer.open {
display: block
}

.mw_layer .bg {
position: absolute;
top: 0;
left: 0;
width: 100%;
height: 100%;
background: #000;
opacity: .5;
filter: alpha(opacity = 50)
}

#layer {
position: absolute;
top: 40%;
left: 40%;
width: 400px;
height: 400px;
margin: -150px 0 0 -194px;
padding: 28px 28px 0 28px;
border: 2px solid #555;
background: #fff;
font-size: 12px;
font-family: Tahoma, Geneva, sans-serif;
color: #767676;
line-height: normal;
white-space: normal
}

</style>



<!-- 라이트박스 -->

<script type="text/javascript">

 function openContent(idx){
  $('.mw_layer').addClass('open');
  $.ajax({
    type:'post',
    url:'stop_content',
    data: ({idx:idx}),

    success:function(data){
  	$('#layer').html(data);
	}

 });
 }

 function closeContent(){$('.mw_layer').removeClass('open');}
 

 jQuery(function($){
  var layerWindow = $('.mw_layer');
 

  // ESC Event

  $(document).keydown(function(event){

   if(event.keyCode != 27) return true;
   if (layerWindow.hasClass('open')) {
    layerWindow.removeClass('open');
   }
   return false;
  });

  // Hide Window

  layerWindow.find('>.bg').mousedown(function(event){
   layerWindow.removeClass('open');
   return false;
  });

  //$("tr:even").addClass("odd");
 });

</script>
 

<style type="text/css">
.fixed-table-body {
  overflow-x: auto;
}
</style>



<style type="text/css">/* Chart.js */
@-webkit-keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}@keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}.chartjs-render-monitor{-webkit-animation:chartjs-render-animation 0.001s;animation:chartjs-render-animation 0.001s;}</style>



<style type="text/css">
.nav-item a{ 
  color: black; 
/*   border: 5px solid currentColor; */
/*   box-shadow: 0 0 5px solid currentColor; */
}

</style>


<script type="text/javascript">

	$(window).scroll(function(event){
	if(jQuery(window).scrollTop() > jQuery("#side").offset().top) {
	jQuery("#chase").css("position", "fixed");
	}
	else if((jQuery(window).scrollTop() < jQuery("#side").offset().top)) {
	jQuery("#chase").css("position", "static");
	}
	});
</script>






</head>
<body>
	<jsp:include page="../header2.jsp" />
      		
      


<div class="container-fluid">
  <div class="row"> 
    <nav class="col-md-2 d-none d-md-block bg-light sidebar" id="side">
      <div class="sidebar-sticky" style="margin-top: 80px;" id="chase">
        <ul class="nav flex-column">
          <li class="nav-item" style="margin-bottom: 15px;">
            <a class="nav-link active" href="#" onclick="home()">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bar-chart-2"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>
              	회원 현황 <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item" style="margin-bottom: 15px;">
            <a class="nav-link" href="#" onclick="member()" style="color: red;">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
              	회원 계정
            </a>
          </li>
          <li class="nav-item" style="margin-bottom: 15px;">
            <a class="nav-link" href="#" onclick="manager()">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
              	관리자 계정
            </a>
          </li>
          <li class="nav-item" style="margin-bottom: 15px;">
            <a class="nav-link" href="#" onclick="ban()">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
              	정지 계정
            </a>
          </li>
          <li class="nav-item" style="margin-bottom: 15px;">
            <a class="nav-link" href="#"  onclick="withdraw()">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-layers"><polygon points="12 2 2 7 12 12 22 7 12 2"></polygon><polyline points="2 17 12 22 22 17"></polyline><polyline points="2 12 12 17 22 12"></polyline></svg>
              	탈퇴 계정
            </a>
          </li>
<!--           <li class="nav-item" style="margin-bottom: 15px;"> -->
<!--             <a class="nav-link" href="#" onclick="state()"> -->
<!--               <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bar-chart-2"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg> -->
<!--               	프로젝트 현황 -->
<!--             </a> -->
<!--           </li> -->
        </ul>

      </div>
    </nav>


    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    
    
    
        <nav class="my-2 my-md-0 mr-md-3" style="text-align: right;">
     		<br>
        	<a class="p-2" href="/account_home" style="color: red; font-weight: bold;">계정관리</a>
 			<a class="p-2" href="/register_challenge?page=1" style="color: #000000; font-weight: bold;">프로젝트 관리</a>
 			<a class="p-2" href="/cash_mileage?page=1" style="color: #000000; font-weight: bold;">결제 관리</a>
<!--  			<a class="p-2" href="/payment_" style="color: #000000; font-weight: bold;">신고 관리</a> -->
 			<a class="p-2" href="/waitQnA?page=1" style="color: #000000; font-weight: bold;">신고 및 Q&A</a>
 			
      </nav>
    
    
    
    
    
    
    <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
	    <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
	    	<div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"> </div>
	    </div>
	    <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
	    	<div style="position:absolute;width:200%;height:200%;left:0; top:0"></div>
	    </div>
    </div>
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom" >
        <h1 class="h2">승인 대기</h1> 
        <div class="btn-toolbar mb-2 mb-md-0">

<!--게시판 테이블 상단 -->

        </div>
      </div>

      <div class="my-4 w-100"  width="1083" height="457" style="display: block; height: 366px; width: 867px;">
      
      
<table class="table" style="text-align: center;">
  <thead class="table" style="background-color: #333333; ">
    <tr style="color: white;">
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;" width="100px;">아이디</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">비밀번호</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">이름</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">닉네임</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">소개</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">프로필사진</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">게시물 공개여부</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">계좌번호</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">가입일</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">최종접속일</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">등급경험치</th>
      <th scope="col" rowspan="2" style="vertical-align:middle; font-size: 10pt;">등급</th>
      <th scope="col" colspan="6">분야별 경험치</th>
    </tr>

    <tr style="color: white; font-size: 9pt;">
    	<th>역량</th>
    	<th>건강</th>
    	<th>관계</th>
    	<th>자산</th>
    	<th>생활</th>
    	<th>취미</th>
    </tr>
  </thead>
  <tbody>
    		<c:forEach items="${member}" var="dto">
			
<%-- 			<c:if test="${dto.member_blacklist==0}"> --%>
			
			<tr>
				<th scope="row" style="vertical-align: middle;"><ul style="list-style: none; padding: 0; vertical-align: middle;">
			        <li class="menu"  style="vertical-align: middle;">
			            <a href="#">${dto.member_id}</a>
			            <ul class="hide">
			                <li>
			                <a href="#layer" onclick="openContent('${dto.member_id}')">정지</a></li>
			                <li><a href="#layer" onclick="withdraw_regist('${dto.member_id}')">탈퇴</a></li>
			            </ul>
			        </li>
			    </ul>
			    
				<td style="vertical-align: middle;">${dto.member_pw}</td>
				<td style="vertical-align: middle;">${dto.member_name}</td>
				<td style="vertical-align: middle;">${dto.member_nickname}</td>
				<td style="vertical-align: middle;">${dto.member_introduction}</td>
<%-- 				<td style="vertical-align: middle;">${dto.member_profile_image}</td> --%>
				<td style="vertical-align: middle;"><img alt="" src="/img/account.png"></td>
				<td style="vertical-align: middle;">${dto.member_public}</td>
				<td style="vertical-align: middle;">${dto.member_account}</td>
				<td style="vertical-align: middle;"><c:set var="joindate" value="${fn:substring(dto.member_joindate,0,10)}"/> ${joindate}</td>
				<td style="vertical-align: middle;">${dto.member_last_access}</td>
				<td style="vertical-align: middle;">${dto.member_exp}</td>
				<td style="vertical-align: middle;">${dto.member_grade}</td>
				<td style="vertical-align: middle;">${dto.member_capability}</td>
				<td style="vertical-align: middle;">${dto.member_health}</td>
				<td style="vertical-align: middle;">${dto.member_relationship}</td>
				<td style="vertical-align: middle;">${dto.member_assets}</td>
				<td style="vertical-align: middle;">${dto.member_life}</td>
				<td style="vertical-align: middle;">${dto.member_hobby}</td>
					
			</tr>
<%-- 			</c:if> --%>
			
			</c:forEach>

			
			<tr>
				
					<td colspan="6">
					</td>
					
				<form action="member?page=${page.curPage}" method="post" name="find">
						<td colspan="1">
						<select class="custom-select" name="findoption" id ="findoption">
							<option selected>검색옵션</option>
							<option value="아이디">아이디</option>
							<option value="이름">이름</option>
						</select>
						</td>
						<td colspan="2">
						<div class="input-group">
							<input type="text" class="form-control" name="findtext" id="findtext">
							<button type="submit">검색</button>
						</div>	
						</td>
				</form>
				<td colspan="9"></td>	
<!-- 				글작성 로그인 상태에서 가능 조건 -->
<!-- 				<td colspan="2" style="text-align: right;"> -->
<!-- 					<button onclick="login_check()">글작성</button> -->
<!-- 				</td> -->
			</tr>
			
			
			
			<tr>
				<td colspan="16" aria-label="Page navigation example" align="center">
				<div class="row align-items-center">
				<ul class="pagination">
				    <li class="page-item">
					   	<c:choose>
						<c:when test="${(page.curPage - 1) < 1}">
							<a class="page-link" href="#">[ &lt;&lt; ]</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="/member?page=1">[ &lt;&lt; ]</a>
						</c:otherwise>
						</c:choose>
				   	</li>
				   	
				   	
				    <li class="page-item">
	    				<c:choose>
						<c:when test="${(page.curPage - 1) < 1}">
							<a class="page-link"href="#">[ &lt; ]</a>
						</c:when>
						<c:otherwise>
							<a class="page-link"href="/member?page=${page.curPage - 1}">[ &lt; ]</a>
						</c:otherwise>
						</c:choose>
				    </li>				    
				    
				 
				    
					    <c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
					    	<li class="page-item" >
							<c:choose>
							<c:when test="${page.curPage == fEach}">
								<a class="page-link"href="#">[ ${fEach} ]</a>
							</c:when>
							<c:otherwise>
								<a class="page-link"href="/member?page=${fEach}">[ ${fEach} ]</a>
							</c:otherwise>
							</c:choose>
							</li>
						</c:forEach>
				    
				   

				    <li class="page-item" style="display: inline; float: left;">
	    				<c:choose>
						<c:when test="${(page.curPage +1) > page.totalPage }">
							<a class="page-link"href="#">[ &gt; ]</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="/member?page=${page.curPage + 1}">[ &gt; ]</a>
						</c:otherwise>
						</c:choose>
				    </li>			



					<li class="page-item">
					   	<c:choose>
						<c:when test="${page.curPage == page.totalPage}">
							<a class="page-link" href="#">[ &gt;&gt; ]</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href="/member?page=${page.totalPage}">[ &gt;&gt; ]</a>
						</c:otherwise>
						</c:choose>
				   	</li>


				  </ul>
				
				</div>
			
				</td>
			</tr>
  </tbody>
</table>

  
      </div>

    </main>
  </div>
</div>
      

 <!-- light box -->

<div class="mw_layer">
<div class="bg"></div>
<div id="layer">
<!-- 	<h4>정지 사유</h4> -->
<!-- 	<textarea rows="20" cols="50"></textarea> -->
<!-- 	<input type="button" value="처리"/> -->
</div>
</div>


<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>



 
<? include("/resources/vse/inc/headerScript.html");?>
<? include("/resources/vse/inc/header.html");?>
<link href="/resources/vse/css/subpage/sub.css" rel="stylesheet" type="text/css"/>
<script src="/resources/vse/js/subpage/sub.js" type="text/javascript"></script>


<!-- #contentWrap -->
<!-- 메인과 서브 모두 같은 id="contentWrap"안에 들어가지만 class의 차이로 메인과 서브를 구별 할 것입니다.-->
<div id="contentWrap" class="">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<h3 class="tit"> 1:1문의 </h3>
	<div class="inner mt60">
		<ul class="tab_type_03 tab_btn x_5">
			<li><a href="javascript:;">전체</a></li>
			<li><a href="javascript:;">제품문의</a></li>
			<li><a href="javascript:;">구매문의</a></li>
			<li><a href="javascript:;">사용법문의</a></li>
			<li><a href="javascript:;">AS문의</a></li>
			<li><a href="javascript:;">골프코스 문의</a></li>
			<li><a href="javascript:;">업무제후</a></li>
			<li><a href="javascript:;">기타</a></li>
			<li class="active"><a href="javascript:;">VSE</a></li>
		</ul>
	</div>
	<div class="bg_gray mt50 pb50 pt50">
		<div class="inner">
			<div class="search_type_01">
				<input type="text" placeholder="궁금하신 내용을 입력해주세요."/>
				<a class="btn_search" href="javascript:;" title="검색하기"><img src="/resources/vse/images/btn/btn_search.png" alt="검색아이콘"></a>
			</div>
		</div>
	</div>
	<div class="inner mt60">
		
		<div class="clear mb20">
			<span class="f_r mt20">총 <em class="color_red">13</em>건 | <em class="color_red">2</em>페이지</span>
		</div>
		<!-- span 답변 클레스 추가 : 답변대기 state__standby 답변완료 state_completion-->
		<div class="table_type_02 text_center">
			<table>
				<colgroup>
					<col style="width:90px;" />
					<col style="width:100px"/>
					<col style="auto"/>
					<col style="width:200px"/>
					<col style="width:90px"/>
					<col style="width:200px"/>
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>분류</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>상태</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>13</td>
						<td>VSE</td>
						<td><a href="customer_inquiry_view.html" class="block text_l">VSE 개인 설치 가능한가요?</a></td>
						<td>abc000</td>
						<td><span class="state__standby">답변대기</span></td>
						<td>2021-08-20</td>
					</tr>
					<tr>
						<td>13</td>
						<td>VSE</td>
						<td><a href="customer_inquiry_view.html" class="block text_l">VSE 개인 설치 가능한가요?</a></td>
						<td>abc000</td>
						<td><span class="state_completion">답변완료</span></td>
						<td>2021-08-20</td>
					</tr>
					<tr>
						<td>13</td>
						<td>VSE</td>
						<td><a href="customer_inquiry_view.html" class="block text_l">VSE 개인 설치 가능한가요?</a></td>
						<td>abc000</td>
						<td><span class="state__standby">답변대기</span></td>
						<td>2021-08-20</td>
					</tr>
				</tbody>
			</table>
		</div>
		<ul class="paging mt40"> 
			<li class="paging_prev"><a href="javascript:;"><span>이전</span></a></li>
			<li class="on"><a href="javascript:;"><span>1</span></a></li>
			<li><a href="javascript:;"><span>2</span></a></li>
			<li class="paging_next"><a href="javascript:;"><span>다음</span></a></li>
		</ul>
		<div class="btn_wrap mt60">
			<a href="customer_inquiry_write.html" class="btn btn_black wd_250">문의등록</a>	
		</div>
    </div>


</div>
<script>
$(function(){
	$('#header .gnb > li').eq(4).addClass('active');
});
</script>

<!-- // #contentWrap -->

<? include("/resources/vse/inc/footer.html");?>			
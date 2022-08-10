<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : inc_footer.jsp
    Description : 페이지 하단을 장식한다.
--%>
<div id="footer" ${pageMenuId eq 'KMA' ? 'class="section" data-anchor="section08"' : ''}>
	<div class="footer_info">
		<div class="inner">
			<ul class="clearfix">
				<li>
					<div class="tit_wrap">
						<p class="tit">고객센터</p>
						<div class="icon"><img src="/resources/user/ui_common/images/f_icon01.png" alt=""></div>
					</div>
					<div class="right_info">
						<p class="phone">1644-0955</p>	
						<p class="time">평일 09:00 ~ 18:00    <br class="tab"><span class="pc"></span>    점심 12:00 ~ 13:00</p>
						<p class="day">휴무 토, 일요일/공휴일</p>
					</div>
				</li> 
				<li>
					<div class="tit_wrap">
						<p class="tit">방문 및 택배 접수</p>
						<div class="icon"><img src="/resources/user/ui_common/images/f_icon02.png" alt=""></div>
					</div>
					<div class="right_info">
						<p>서울특별시 서초구  <br class="tabMo"> 논현로 145 <br class="pc">	수냐빌딩 5층</p>
					</div>
				</li>
				<li>
					<div class="tit_wrap">
						<p class="tit">기업 및 대량구매 문의</p>
						<div class="icon"><img src="/resources/user/ui_common/images/f_icon03.png" alt=""></div>
					</div>
					<div class="right_info">
						<p>seungji27@vcinc.co.kr</p>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="footer">
		<div class="footer_top">
			<div class="inner clearfix">
				<img src="/resources/user/ui_common/images/footer_logo.png" alt="">
				<div class="right_menu">
					<ul class="clearfix">
						<li><a href="/role/service-rules.vc">이용약관</a></li>
						<li><a href="/role/privacy-protection-policy.vc">개인정보처리방침</a></li>
						<li><a href="/role/denial-of-unauthorized-collecting-of-email-address.vc">이메일무단수집거부</a></li>
					</ul>
				</div>	
			</div>
		</div>
		<div class="footer_bottom">
			<div class="inner">
				<p>(주)브이씨</p>
				<p>
					<em class="pc">사업장소재지 : </em>서울특별시 강남구 테헤란로 108길 23<br class="pc"><em class="pc">      I      
					</em>대표자 : 김준오 <br class="pc"><em class="pc">     I      
					</em>대표번호 : 02-538-3100 / FAX : 02-538-3104,5 <br>
					사업자등록번호 : 209-81-39552<em class="pc">      I      
					</em><br class="pc">정보처리담당자 : 이상윤<br class="pc"><em class="pc">      I      
					</em>통신판매업신고 : 제2019-서울강남-03541호
				</p>
			</div>
		</div>
	</div>
</div>

<div id="mask" class="modal_backdrop"  style="z-index:1000"></div>

<script>
function maxLengthCheck(object){ 
	if (object.value.length > object.maxLength){ 
		object.value = object.value.slice(0, object.maxLength); 
	} 
}

$(function () {
    $('input[type="file"]').on('change',function(){
    	if(window.FileReader){  
    		var filename = $(this)[0].files[0].name;
 	    }else{ 
 	    	var filename = $(this).val().split('/').pop().split('\\').pop(); 
 	    }
    	
		$(this).parents('.file').find('input[type="text"]').val(filename);
	});
    
    $('select[name=rowLimit]').on('change', function(event){
		$('form[name=searchForm]').submit();   
	});
	
    $(".print_btn").on("click", function(event){
    	event.preventDefault();  
    	self.print();
    	return false;
    });
    
    //공유 - URL복사
	$('.share_icon').on('click',function(){
		if($(this).hasClass('active')){
			$(this).removeClass('active');
		}else{
			$(this).addClass('active');
		}
	});
    
  	//장바구니
	$('.addcart').on('click',function(){
		if($(this).hasClass('active')){
			$(this).removeClass('active');
		}else{
			$(this).addClass('active');
		}
	});
    
  	//상품/리뷰 좋아요.
    $('.btn_like').click(function(event){
    	event.preventDefault();  
    	
    	if(loginObj.isLoginCheck2()){
	    	var el = $(event.currentTarget);
	    	var elType = event.currentTarget.type;
	    	var scrapCnt = 0;
	    	var scrapCd = $(this).data('cd');
	    	
	    	if(scrapCd == 'REVIEW'){
	    		//scrapCnt = $(this).siblings('span').html();
	    		scrapCnt = $(this).closest('div.review_head').find('span.review-scrap-cnt').html();
	    	}
	
	    	var scrapUrl = '/shop/scrap/add.vc';
	        var bLike = $(this).hasClass('active');
	        var data = {};
	        
	        if(bLike){
	      		scrapUrl = '/shop/scrap/del.vc';
	      		$(this).removeClass('active');
	          	scrapCnt--;
	        } else {
	          	$(this).addClass('active');
	          	scrapCnt++;
	        }
	        
	        if(scrapCd == 'PRODUCT'){
	        	data = { 'scrapCd' : scrapCd, 'prdSn' : $(this).data('sn') };
	        }else{
	        	data = { 'scrapCd' : scrapCd, 'blcSn' : $(this).data('sn') };
	        }
	        
	        $.ajax({
	            url : '${contextPath}' + scrapUrl,
	            data : data,
	            method: 'post',
	            success: function (data, textStatus, jqXHR) {
	            	if(scrapCd == 'REVIEW' && data.result.status){
// 	            		$(el).siblings('span').html(scrapCnt);
	            		$(el).closest('div.review_head').find('span.review-scrap-cnt').html(scrapCnt);
	            	}
	                console.log('스크랩성공 : ' + data);
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                console.log('스크랩실패');
	            }
	        });
    	}
    });
  	
  	/**
  	 * 장바구니 추가
  	 */
    $('.btnAddCart').click(function(event){
    	event.preventDefault();  
    	
    	var el = $(event.currentTarget);
    	var prdCnt = '1';
    	
    	try{
	    	if($(this).closest('form').length > 0){
	    		prdCnt = $(this).closest('form').find('input[name=orderQuantity]').val()
	    	}
    	}catch(e) {}
    	
        $.ajax({
            url : '${contextPath}' + '/shop/cart/add.vc',
            data : {'prdSn': $(this).data('sn'), 'prdCnt': prdCnt },
            method: 'post',
            success: function (data, textStatus, jqXHR) {
           		location.href = '/shop/cart.vc';
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('장바구니 실패');
            }
        });
    });
  	
  	/*
  	 * 구매하기
  	 */
    $('.btnQuikOrder').click(function(event){
    	event.preventDefault();  
    	if(loginObj.isLoginCheck()){
    		location.href = '/shop/order/register.vc?prdSn=' + $(this).data('sn');
    	}else{
    		if(confirm('로그인 후 구매 하시겠습니까?')){
    			location.href = '/member/login.vc';		
    		}else{
    			location.href = '/shop/order/register.vc?prdSn=' + $(this).data('sn');
    		}
    	}
    	
    });
});

$(document).ready(function(){
	
	$('.login_box').hide();
	$('.gnb .right_menu > li.mypg_btn').click(function(){
		$('.login_box').toggle();
	});
	
	<c:if test="${not empty param.isApp}">
		$('a.menu_btn').hide();
	</c:if>

	/* 모바일에서 sub_menu pop*/
	
	$('.sub_menu_top .mypg_btn.m a').on('click', function(){
		console.log('pp')
		$('.sub_menu_top .login_box.m').slideToggle();
		/*if($('#header .sub_menu_top > .login_box').hasClass('show')){
			$('#header .sub_menu_top > .login_box').removeClass('show');
		}else{
		console.log('aa')
			$('#header .sub_menu_top > .login_box').addClass('show');
		console.log('bb')
		}*/
	});
	
	$(window).scroll(function(){ 
		if($(window).scrollTop() > 480){
			$('a.top_btn, a.talk_btn').show();
		}else{	
			$('a.top_btn, a.talk_btn').hide();
		}
	});
	
	$('a.talk_btn').hide();
	$('a.top_btn').hide().on('click',function(){
		fn_moveScrollTop(0);
	});
});
</script>

<!-- Google Tag Manager (noscript) -->
<noscript>
	<iframe src="https://www.googletagmanager.com/ns.html?id=GTM-MVJVT9X" height="0" width="0" style="display:none;visibility:hidden"></iframe>
</noscript>

<!-- 네이버 애널리틱스 -->
<script type="text/javascript" src="//wcs.naver.net/wcslog.js"></script>
<script type="text/javascript">
	if(!wcs_add) var wcs_add = {};
	wcs_add["wa"] = "208eca34615d18";
</script>
<script type="text/javascript"> 
	if(!wcs_add) var wcs_add = {};
	wcs_add["wa"] = "s_20e3846d9d50";
	wcs.inflow("viruscleanlab.com");
</script>
<script type="text/javascript"> 
	if(window.wcs) { wcs_do(); }
</script>

<!-- Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-EVGHP6PPCE"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'G-EVGHP6PPCE');
</script>

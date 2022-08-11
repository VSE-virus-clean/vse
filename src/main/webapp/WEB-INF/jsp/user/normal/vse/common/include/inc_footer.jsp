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
<div id="footer">
	<div class="footer_con top">
		<div class="inner">
			<!-- 
			<div class="footer_sec">
				<h3><a href="/company/intro.vse">회사소개</a></h3>
			</div>
			<div class="footer_sec">
				<h3><a href="/company/conditions.vse">이용약관</a></h3>
			</div>
			<div class="footer_sec">
				<h3><a href="/company/personal.vse">개인정보취급방침</a></h3>
			</div>
			 -->
			<div class="footer_sec footer_sns_wrap">
				<h3>FOLLOW US</h3>
				<ul class="footer_sns">
					<li><a href="https://www.youtube.com/channel/UCLMJLbX6Pur9ukrnJ52LPLQ" target="_blank" class="sns_y" ></a></li>
					<li><a href="https://www.facebook.com/voicecaddie.VSE/" target="_blank" class="sns_f"></a></li>
					<li><a href="https://instagram.com/vse_kor?utm_medium=copy_link" target="_blank" class="sns_i"></a></li>
					<li><a href="https://blog.naver.com/voicecaddiek" target="_blank" class="sns_nb"></a></li>
					<li><a href="https://post.naver.com/voicecaddiek/" target="_blank" class="sns_np"></a></li>
				</ul>
			</div>
		</div>
	</div>
	<!--footer_con-->

	<div class="footer_con bottom">
		<div class="inner">
			<div class="footer_sec">
				<h3>Customer Service</h3>
				<ul>
					<li>Support@voicecaddie.com</li>
					<li>562-926-3978</li>
				</ul>
			</div>
			<!-- 
			<div class="footer_sec">
				<h3>보이스캐디 고객센터</h3>
				<ul>
					<li>a/s문의: 1577-2862 (내선 2번) </li>
					<li>구매문의: 1577-2862 (내선 3번)</li>
					<li>기업특판: 070-8290-5372</li>
					<li>방문 및 택배접수 : 서울시 서초구 논현로 145 수냐빌딩 5층</li>
				</ul>
			</div>
			 -->
			<div class="footer_sec">
				<ul>
					<li><a class="_p_icon" href="https://voicecaddie.com/terms-of-service/" target="_blank">Terms of Service</a></li>
					<li><a class="_p_icon" href="https://voicecaddie.com/privacy-policy/" target="_blank">Privacy Policy</a></li>
					<li><a class="_p_icon" href="https://voicecaddie.com/warranty/" target="_blank">Warranty Policy</a></li>
					<li><a class="_p_icon" href="https://voicecaddie.com/shipping-returns/" target="_blank">Shipping and Returns</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!--footer_con
	<p class="footer_copyright">COPYRIGHT © 2021. voicecaddie. ALL RIGHTS RESERVED.</p>
	-->
</div>
<div id="wp_tg_cts" style="display:none;"></div>

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
});

$(document).ready(function(){
	
});
</script>
<script type="text/javascript">
var wptg_tagscript_vars = wptg_tagscript_vars || [];
wptg_tagscript_vars.push(
(function() {
	return {
		wp_hcuid:"",   /*고객넘버 등 Unique ID (ex. 로그인  ID, 고객넘버 등 )를 암호화하여 대입. */
		ti:"53446",	/*광고주 코드 */
		ty:"Home",	/*트래킹태그 타입 */
		device:"web"	/*디바이스 종류  (web 또는  mobile)*/
	};
}));
</script>
<script type="text/javascript" async src="//cdn-aitg.widerplanet.com/js/wp_astg_4.0.js"></script>
<script async src="https://www.googletagmanager.com/gtag/js?id=G-D360CDGTK5"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'G-D360CDGTK5');
</script>
<script type="text/javascript">
//Enliple Tracker
(function(a,g,e,n,t){a.enp=a.enp||function(){(a.enp.q=a.enp.q||[]).push(arguments)};n=g.createElement(e);n.async=!0;n.defer=!0;n.src="https://cdn.megadata.co.kr/dist/prod/enp_tracker_self_hosted.min.js";t=g.getElementsByTagName(e)[0];t.parentNode.insertBefore(n,t)})(window,document,"script");
enp('create', 'common', 'vs_nas', { device: 'B' }); // W:웹, M: 모바일, B: 반응형
enp('send', 'common', 'vs_nas');
enp('create', 'conversion', 'vs_nas', { device: 'B', btnSelector: '#GNB-STARTUP', convType: 'etc', productName: 'GNB-창업메뉴'});
enp('create', 'conversion', 'vs_nas', { device: 'B', btnSelector: '#MAIN-STARTUP', convType: 'etc', productName: '메인-창업상담'});
</script>


<!-- Smartlog -->
<script type="text/javascript"> 
    var hpt_info={'_account':'UHPT-73111', '_server': 'a73'};
</script>
<script language="javascript" src="//cdn.smlog.co.kr/core/smart.js" charset="utf-8"></script>
<noscript><img src="//a73.smlog.co.kr/smart_bda.php?_account=73111" style="display:none;width:0;height:0;" border="0"/></noscript> 


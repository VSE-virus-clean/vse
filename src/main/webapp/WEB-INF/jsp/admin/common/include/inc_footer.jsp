<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 
    JSP Name : inc_footer.jsp
    Description : 페이지 하단을 장식한다.
--%>

<!-- 로딩중 이미지 -->
<iframe id="ifr" noresize="noresize" allowTransparency="true" scrolling="no" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" title="기능상 필요한 내용 없는 빈 프레임" ></iframe>
<iframe id="ifr2" noresize="noresize" allowTransparency="true" scrolling="no" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" title="기능상 필요한 내용 없는 빈 프레임" ></iframe>
<iframe id="ifr3" noresize="noresize" style="background:transparent;" allowTransparency="true" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" title="기능상 필요한 내용 없는 빈 프레임" onload="eventObj.iframeClickEvent();"><body style="background:transparent;"></body></iframe>
<div id="mask"></div>
<div id="mask2"></div>
<div id="loading-wrap"><img src="/resources/admin/images/common/loading.gif" alt="" /></div>

<script type="text/javascript">
$(function () {
	/* 모달 */
	$('.btn_open').on('click',function(){
		event.preventDefault();
		$('.modal_window').show();
		$('.modal_backdrop').show();
	});
		
	$('.btn_cancel').on('click',function(){
		$('.modal_window').hide();
		$('.modal_backdrop').hide();
	});
	
	$('.close').on('click',function(){
		$(this).parents('.modal_window').hide();
		$('.modal_backdrop').hide();
	});
	
	
	/* Left Menu */
	$('.depth01').on('click',function(){
		if($(this).hasClass('open')){
			$(this).find('.depth02').slideUp();
			$(this).removeClass('open');
		}else{
			$(this).find('.depth02').slideDown();
			$(this).addClass('open')
		}
	});

	/* Radio Box */
	$('.radio_box').on('click',function(){
		$('.radio_box').each(function(){			
			if($(this).find('input').is(':checked')){
				$(this).addClass('check');
			}
			if(!$(this).find('input').is(':checked')){
				$(this).removeClass('check');
			}
		})
	});
	
	 
    $('a.btnRowDelete').on({
        'click' : function(event){
            event.preventDefault();  
            formSubmitObj.submitDel($(this));
        }
    });


	/* 첨부 파일*/
	$('.filebox .upload-hidden').on('change', function(){  
	     if(window.FileReader){  
	    	 var filename = $(this)[0].files[0].name;
	     } 
	     else { 
	    	 var filename = $(this).val().split('/').pop().split('\\').pop(); 
	     }
	     
	     $(this).siblings('.upload-name').val(filename);
	});
	
 	$('select[name=rowLimit]').on('change', function(event){
 		$('form[name=searchForm] input[name=rowLimit]').val($(this).val());
		$('form[name=searchForm]').submit();   
	});
		
	
});

var MENU_ID = '${pageMenuId}';
$(document).ready(function(){
	
	<c:if test="${sessionScope.sessionVO.authLevel eq 'A'}">
		$('[id^="snb_"]').css('display', 'none');
		console.log('${sessionScope.sessionVO.menuCd}');
		<c:forTokens var="menu" items="${sessionScope.sessionVO.menuCd}" delims="|">
			$("#snb_${menu}").css('display', 'block');
		</c:forTokens>
	</c:if>
	
	$('#lefrMenu').ready(function(){
	    if (MENU_ID != undefined && MENU_ID != ''){
	        var depth = MENU_ID.split('');
	        var menuId = '';
	        
	        for(var i = 0; i < depth.length; i++){
	        	menuId += depth[i];
	            $('#snb_'+ menuId).addClass('open');
	        }                
	    }
	    
	    $('.depth01.open').find('.depth02').slideDown();
	});
});
</script>

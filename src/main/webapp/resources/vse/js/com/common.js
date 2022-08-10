//기기 확인 
function isMobile(){
	var UserAgent = navigator.userAgent;

	if (UserAgent.match(/iPhone|iPad|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null)

	{
		return true;
	}else{
		return false;
	}
}
$(function(){
	$(document).ready(function(){
		if (isMobile()) {
				// 모바일이면 실행될 코드 들어가는 곳
				
				$('#wrap').addClass('mobile');
	
				//css 모바일용 
				var $ele1	= document.createElement("link");
				$ele1.href = "/resources/vse/css/com/mobile_common.css";
				$ele1.rel	= "stylesheet";
				$ele1.type	= "text/css";
				document.getElementById("contentAnchor").appendChild($ele1);
				
				var $ele2	= document.createElement("link");
				$ele2.href = "/resources/vse/css/com/mobile_style.css";
				$ele2.rel	= "stylesheet";
				$ele2.type	= "text/css";
				document.getElementById("contentAnchor").appendChild($ele2);
				
				var $ele4	= document.createElement("link");
				$ele4.href = "/resources/vse/css/main/mobile_main.css";
				$ele4.rel	= "stylesheet";
				$ele4.type	= "text/css";
				document.getElementById("contentAnchor").appendChild($ele4);
	
				var $ele3	= document.createElement("link");
				$ele3.href = "/resources/vse/css/subpage/mobile_sub.css";
				$ele3.rel	= "stylesheet";
				$ele3.type	= "text/css";
				document.getElementById("contentAnchor").appendChild($ele3);
	
		
	
				//모바일 GNB
				$('.allmenu').on('click',function(){
					$('.mo_gnb').show();
					$('body,html').addClass('scrollnone');
				});
				$('.allmenu_close').on('click',function(){
					$('.mo_gnb').hide();
					$('body,html').removeClass('scrollnone');
					$('.mo_gnb_con .mo_gnb_1 > li').removeClass('on');
				});
				$('.mo_gnb_con .mo_gnb_1 > li > a').on('click',function(){
					$(this).parent().toggleClass('on');
				});
	
	
		} else {
				// 모바일이 아니면 실행될 코드 들어가는 곳
	
		}
	});
	
});

//header
$(function(){
	
	var	HeaderGnb =$('#header .gnb > li');
	$('#header .gnb > li').on('mouseenter click focusin',function(){
		
		$('#header').addClass('gnb_hover');
		if($(this).hasClass('hover')){
			HeaderGnb.removeClass('hover');
		}else{
			HeaderGnb.removeClass('hover');
			$(this).addClass('hover');
		}

		/*$('#contentWrap').on('click',function(){
			HeaderGnb.removeClass('hover');
		});*/


	});
	$('#header .gnb').on('mouseleave focusout ',function(){
		$('#header').removeClass('gnb_hover');
		HeaderGnb.removeClass('hover');
	});
	


	


	



});


//tab
$(function(){
	$('.tab_btn > li a').on('click',function(){
		var tabBtnCk = $(this).parent().index();
		var tabConList = $(this).parents('.tab_wrap').find('.tab_con >.tab_list');
		$(this).parents('.tab_btn').find('li').removeClass('active');
		$(this).parent().addClass('active');
		tabConList.removeClass('active');
		tabConList.eq(tabBtnCk).addClass('active');
		
		//slide
		$('.slide_type_01 > ul').slick('setPosition');
		$('.slide_type_01 > ul').slick('goTo',0);

	});
});
//select
$(function(){
	$('.select_type_01').selectric();
});


//개인정보 
$(function(){
	//창업상담
	$('#CounselingInfoRCk').attr('disabled', 'true'); //막기
	$("#CounselingprivacyScroll").scroll(function(){
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(this).prop('scrollHeight');
	  
		if (scrollTop + 350 >= scrollHeight) {
			$("#CounselingInfoRCkWarp").addClass('ck');
			$('#CounselingInfoRCk').removeAttr('disabled'); //열기
		}
	});
	//사업설명회
	$('#BusinessInfoRCk').attr('disabled', 'true'); //막기
	$("#BusinessprivacyScroll").scroll(function(){
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(this).prop('scrollHeight');
	  
		if (scrollTop + 350 >= scrollHeight) {
			$("#BusinessInfoRCkWarp").addClass('ck');
			$('#BusinessInfoRCk').removeAttr('disabled'); //열기
		}
	});
	// 레슨프로
	$('#CounselingInfoRCk').attr('disabled', 'true'); //막기
	$("#CounselingprivacyScroll").scroll(function(){
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(this).prop('scrollHeight');
	  
		if (scrollTop + 350 >= scrollHeight) {
			$("#CounselingInfoRCkWarp").addClass('ck');
			$('#CounselingInfoRCk').removeAttr('disabled'); //열기
		}
	});
});
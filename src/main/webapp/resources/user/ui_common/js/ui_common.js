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
$(document).ready(function(){
	if(isMobile()){
		if (window.matchMedia('(orientation: portrait)').matches) {
			// Portrait 모드일 때 실행할 스크립트
			// 폭과 높이가 같으면 Portrait 모드로 인식돼요
			$('#wrap').removeClass('Landscape');
		} else {
			// Landscape 모드일 때 실행할 스크립트
			//console.log('가로');
			$('#wrap').addClass('Landscape');
		}
		$(window).resize(function(){
			console.log('가로');
			
			if (window.matchMedia('(orientation: portrait)').matches) {
				// Portrait 모드일 때 실행할 스크립트
				// 폭과 높이가 같으면 Portrait 모드로 인식돼요
				$('#wrap').removeClass('Landscape');
			} else {
				// Landscape 모드일 때 실행할 스크립트
				//console.log('가로');
				$('#wrap').addClass('Landscape');
			}
			
		});
	} else {
    	// 모바일이 아니면 실행될 코드 들어가는 곳
		$('#wrap').removeClass('Landscape');
    }
 });
$(function(){
	//tab
	$('.tab ul li a').on('click',function(){
		var targer = $(this).closest('li')
		var idx = targer.index();

		targer.addClass('active').siblings().removeClass('active');
		$('.tab_cont > ul> li').eq(idx).addClass('active').siblings().removeClass('active');
	});


	$('.menu_btn').on('click',function(){
		if($('#heaeder').hasClass('show')){
			$('#heaeder').removeClass('show');
			
			if(window.location.pathname != '/index.vc')
			{ 
				//서브
				if(window.innerWidth <= 720){
					$('#heaeder .logo img').attr('src', '/resources/user/ui_common/images/sub_logo.png'); 
					$('#heaeder .right .menu_btn .close').attr('src', '/resources/user/ui_common/images/close_btn_w.png'); 

				}
			}else{
				//메인
				$('#heaeder').removeClass('main_header');
			}
		}else{
			$('#heaeder').addClass('show');
			$('#heaeder .logo img').attr('src', '/resources/user/ui_common/images/top_logo.png'); 
			$('#heaeder .right .menu_btn .close').attr('src', '/resources/user/ui_common/images/close_btn.png'); 
			if(window.location.pathname != '/index.vc')
			{	//서브
				if(window.innerWidth <= 720){
					console.log('mo')
					$('#heaeder .logo img').attr('src', '/resources/user/ui_common/images/top_logo.png'); 
					$('#heaeder .right .menu_btn .close').attr('src', '/resources/user/ui_common/images/close_btn.png'); 
				}else{	
					console.log('pc')
					$('#heaeder .logo img').attr('src', '/resources/user/ui_common/images/sub_logo.png'); 
					$('#heaeder .right .menu_btn .close').attr('src', '/resources/user/ui_common/images/close_btn_w.png'); 
				}
			}else{
				//메인
				$('#heaeder').addClass('main_header');
			}
			
			event.preventDefault();
			event.stopPropagation();
		}
	
	});
	
	
	//마이페이지 팝업 모바일
	$('.modal_window.mypg_modal .ranking_detail_wrap li').on('click', function(){
		
		$(this).children('.r_coup_wrap').toggleClass('active');

	});

	$('.question').on('click',function(){
		console.log('d')
		target = $(this).closest('li');

		if(target.hasClass('active')){
			target.removeClass('active');
			target.find('.answer').slideUp();
		}else{
			target.addClass('active')
			target.find('.answer').slideDown();
		}
	});

	//top_btn
	$('.top_btn').on('click',function(){
		$('body,html').animate({scrollTop: '0'}, 1000);
	});
	
	
	/* fullpage.js */
	/*$('#container').fullpage({
		anchors: ['.section01', '.section02', '.section03', '.section04' ,'.section05' , '.section06' ,'.section07'],
		afterLoad: function(anchorLink, index){
			
			console.log('123123');		
			
			if(index == 4){		
				$(".section04").addClass("in-view");
			};

			if(index == 3 || index == 4){
				console.log('ee');
				$("#heaeder").addClass("on");
			}else{
				$("#heaeder").removeClass("on");
			};

		}	

	});*/
	
	

	/* $('#container').scroll(function(){
        var scrollT = $(this).scrollTop(); //스크롤바의 상단위치
        var scrollH = $(this).height(); //스크롤바를 갖는 div의 높이
        var contentH = $('.section').height(); //문서 전체 내용을 갖는 div의 높이
        if(scrollT + scrollH +1 >= contentH) { // 스크롤바가 아래 쪽에 위치할 때
            $('#divContent').append(imgs);
        }
    });*/

	

	//main - sub menu 
	
	
	
	var winW = $(window).innerWidth();
	
	 
	if(winW => 1024){
		$('#heaeder .sub_memu > .sub_menu_inner > ul > li').on('click', function(){
			console.log('click');
			$(this).children('.depth02').slideToggle(500);
			
		});
	}else{
		$('#heaeder .sub_memu > .sub_menu_inner > ul > li').on('hover', function(){
			console.log('hover');
			$(this).children('.depth02').slideToggle(500);
		});
	}

	
	
});



$(function () {
	var sec01 = $('.section01').offset().top; 
	var sec02 = $('.section02').offset().top; 
	var sec03 = $('.section03').offset().top;
	var sec04 = $('.section04').offset().top;
	var sec05 = $('.section05').offset().top;
	var WindowH	= $(window).innerHeight();// 윈도우 높이
	var WindowH2 = WindowH / 2;
	
	$('.section01').addClass('active');

	//scroll event
	$(document).scroll(function(){
		var scrollPoint = $(window).scrollTop() + WindowH; // 현제 스크롤 위치 // 현제 스크롤 높이 + 전체 컨텐츠
		var scrollT = $(window).scrollTop();		
		var floatingH = $('.floating').innerHeight(); // 플루팅 높이

		if(sec02 - WindowH2 <= scrollT){
			$('.section').removeClass('active');
			$('.section02').addClass('active');
			$('#MainVideo01').get(0).currentTime = 0;
			$('#MainVideo01').get(0).play();
		}if(sec03  - WindowH2 <= scrollT){
			$('.section').removeClass('active');
			$('.section03').addClass('active');
		}if(sec04  - WindowH2 <= scrollT){
			$('.section').removeClass('active');
			$('.section04').addClass('active');
		}if(sec05  - WindowH2 <= scrollT){
			$('.section').removeClass('active');
			$('.section05').addClass('active');
		}
		
		if(sec01 >= scrollT){
			$('.section').removeClass('active');
			$('.section01').addClass('active');
		}
        
	});






});



/*sns*/

/*
	발급된 토큰은 장기 실행 액세스 토큰으로 60일간 사용이 가능합니다.
	https://developers.facebook.com/docs/instagram-basic-display-api/guides/long-lived-access-tokens
	
	발급된 토큰은 만료일(60일)이내에 refresh_access_token혹은, 페이스북 개발자 센터내의 토큰 재발급을 통해 연장을 해주어야합니다.
	https://developers.facebook.com/docs/instagram-basic-display-api/reference/refresh_access_token
*/
function instafeeder() {
    
        var userFeed = new Instafeed({
            get: 'user',
            userId: "1354802608281204", //accessToken 의 앞자리
            fileds: 'media_type',
            template: '<li><a href="{{link}}" title="SNS 게시물 새창 열기" target="_blank"><img style="width:233px; height:233px" src="{{image}}" alt="sns 이미지" /><span class="overbox"><i></i></span></a></li>',
            accessToken: 'IGQVJWUk9FSWJuaENvZA3k0bjJpUmUwN3lOc1lwNHY4cDgwbi1SaTZAFcHRuRVVVN1VNaXhKQVZAsN1dieHZAwOTFlcnkxT0NwS2ZALUEY5aEl2S3RFR0QtT0ttbDBHWU5PUDBBNzRvaUM3YXkzV0RmaV9JNAZDZD',
            target: 'pc',
            filter:
            function(image) {
                return image.type != 'video';
            },
            after: function() {       
                var images = $("#pc").find('li'); // instafeed
                
                if (images.length > 10) { //image만 10개 가져옴
                 $(images.slice(10, images.length)).remove();
                    
                    var Section05_hov=$('.sec05_sl_outer.pc_show li');
                    if ($(window).width() > 1024) { // 1024보다 클 때 동작
                        $(Section05_hov).hover(function(){
                            $(this).toggleClass('bg');
                        });
                    };
                
                    var Section05 =$('.sec05_sl_outer');
                    Section05.slick({
                        slide:'li',
                        slidesToShow: 4,
                        slidesToScroll: 4,
                        autoplay:false,
                        infinite:true,
                        autoplaySpeed:8000,
                        speed:600,
                        pauseOnHover:false,
                        accessibility:true, //접근성
                        prevArrow: $('.section05_slide_arrow.prev.pc_show'), 
                        nextArrow: $('.section05_slide_arrow.next.pc_show'),
                        dots:false, 
                        draggable : true,//드래그 가능 여부 
						responsive: [ // 반응형 웹 구현 옵션
								
								{ 
									breakpoint: 768, //화면 사이즈 768px
									settings: {	
										//위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
										slidesToShow: 1,
				                        slidesToScroll: 1,
									} 
								}
						]

                    });          
                }
            }
        });  
        userFeed.run();
        
};
	




	




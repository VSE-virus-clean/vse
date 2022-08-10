$(function(){

	$('#contentWrap').addClass('active');

	var WindowH	= $(window).innerHeight();// 윈도우 높이
	var WindowH2 = WindowH / 3;
	$(document).scroll(function(){
		var scrollPoint = $(window).scrollTop() + WindowH; // 현제 스크롤 위치 // 현제 스크롤 높이 + 전체 컨텐츠
		var scrollT = $(window).scrollTop();
		
		var floatingH = $('.floating').innerHeight(); // 플루팅 높이
		var constrol = 40;
		
		if(WindowH <= scrollT){
			$('#contentWrap').removeClass('active');
		}else{
			$('#contentWrap').addClass('active');
		
		}		
        
	});
});


//slide
$(function(){

	$(document).ready(function(){
		setTimeout(function() {
				//product_slide_01
				var slickElement = $('.product_slide_01 .slide_type_01');
				slickElement.on('init reInit afterChange', function(event, slick, currentSlide, nextSlide){
					 var i = (currentSlide ? currentSlide : 0);
					 $('.product_info li').removeClass('active');
					 $('.product_info li').eq(i).addClass('active');
				});
				$('.product_info li a').on('click',function(){
					var ProductSNum = $(this).parent().index();
					console.log(ProductSNum);
					$('.product_info li').removeClass('active');
					$(this).parent().addClass('active');
					//slide
					$('.product_slide_01 .slide_type_01 > ul').slick('setPosition');
					$('.product_slide_01 .slide_type_01 > ul').slick('goTo',ProductSNum);
				});

				var Slide01 = $('.slide_type_01 > ul');
				Slide01.slick({
					slide:'li',
					infinite : true, 	//무한 반복 옵션
					initialSlide : 0, //처음 슬라이드가  맨앞으로 오게
					slidesToShow : 1,		// 한 화면에 보여질 컨텐츠 개수
					slidesToScroll : 1,		//스크롤 한번에 움직일 컨텐츠 개수
					arrows : true, 		// 옆으로 이동하는 화살표 표시 여부
					dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
					autoplay : true,			//  자동 재생 사용 여부
					draggable : true, 	//드래그 가능 여부 
					autoplaySpeed : 3000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
					fade: true,
					speed: 300,
					cssEase: 'linear',
					responsive: [ // 반응형 웹 구현 옵션
						{  
							breakpoint: 730, //화면 사이즈 960px
							settings: {
								//위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
								slidesToShow:1 ,
								//variableWidth:true,
							} 
						}
					]

					
				});
				
				
				// slide scrollbar
				var swiper = new Swiper(".slide_type_02 .swiper-container", {
					slidesPerView: "auto",
					spaceBetween : 20, // 슬라이드간 간격
					centeredSlides: true,
					
					scrollbar: {
					  el: ".swiper-scrollbar",
					  hide: true,
					},
					navigation: {
					  nextEl: ".swiper-button-next",
					  prevEl: ".swiper-button-prev",
					}

					
				 });

						
		}, 100);

	});
	//

});


//product_slide_02
$(function(){
	$('.product_slide_02 .tab_type_01 a').on('click',function(){
		//var  Product02Img = $('.product_slide_02 .img img');
		var CkProductSlide02 = $(this).attr('value');
		var CkProductSlide02 = CkProductSlide02 - 1;
		console.log(CkProductSlide02)
		//Product02Img.attr('src',CkProductSlide02);
		$('.product_slide_02 .slide_type_01 > ul').slick('goTo', CkProductSlide02); //슬릭 처음


	});
});
$(function(){
	$('.startups_list .btn').on('click',function(){
		if($(this).parents('li').hasClass('active')){
			$(this).parents('li').removeClass('active');
		}else{
			$(this).parents('li').addClass('active');
		}
		
	});
});
//faq
$(function(){
	$('.faq_wrap .faq_tit').on('click',function(){
		if($(this).parent().hasClass('active')){

			$(this).parent().removeClass('active');
		}else{
			$('.faq_wrap li').removeClass('active');
			$(this).parent().addClass('active');
		}
	});
});

//파일첨부
$(function(){	
	var fileTarget = $('.filebox .upload-hidden'); 
	fileTarget.on('change', function(){ 
		// 값이 변경되면 
		if(window.FileReader){ 
		// modern browser 
		var filename = $(this)[0].files[0].name; 
		} else { 
			// old IE 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
		} 
	// 추출한 파일명 삽입 
	$(this).siblings('.upload-name').val(filename); });
});




function selectAll(selectAll)  {
  const checkboxes 
     = document.querySelectorAll('input[type="checkbox"]');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked
  })
}

// 모바일 일때
$(function(){
	if (isMobile()) {
	
		$(document).ready(function(){
			setTimeout(function() {
				// slide scrollbar
				var swiperStartups = new Swiper(".process_slide.swiper-container", {
					slidesPerView: "auto",
					spaceBetween : 5, // 슬라이드간 간격
					centeredSlides: true,
					scrollbar: {
					  el: ".swiper-scrollbar",
					  hide: true,
					}
				 });
			}, 400);

		});

	} else {
			// 모바일이 아니면 실행될 코드 들어가는 곳

	}
});


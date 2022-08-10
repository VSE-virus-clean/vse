$(function(){
	//tab

	$('.tab ul li a').on('click',function(){
		var targer = $(this).closest('li')
		var idx = targer.index();

		targer.addClass('active').siblings().removeClass('active');
		$('.tab_cont > ul> li').eq(idx).addClass('active').siblings().removeClass('active');

	});

})
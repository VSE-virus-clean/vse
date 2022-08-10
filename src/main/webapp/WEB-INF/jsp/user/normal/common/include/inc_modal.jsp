<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : shopDet.jsp
    Description : Front 레이어 팝업
--%>

<!-- 구매내역 레이어 -->
<div class="modal_window modal_wrap modal-benefit mypg_modal">
	<div class="modal_window_cont ">
		<div class="inner_pop">
			<div class="modal_window_ranking">
				<div class="ranking_tit_wrap">
					<h3 class="p_tit">
						<strong>${sessionScope.sessionVO.name}</strong>
						<span>님의 등급은&nbsp; </span>
						
						<c:choose>
							<c:when test="${sessionScope.sessionVO.grade eq 'NORMAL'}">
								<img src="/resources/user/images/mypage/normal_icon.png" alt=""><strong class="nomal">일반회원 </strong>
							</c:when>
							<c:when test="${sessionScope.sessionVO.grade eq 'BRONZE'}">
								<img src="/resources/user/images/mypage/bronze_img.png" alt=""><strong class="bronze">Bronze </strong>
							</c:when>
							<c:when test="${sessionScope.sessionVO.grade eq 'SILVER'}">
								<img src="/resources/user/images/mypage/silver_icon.png" alt=""><strong class="silver">Silver </strong>
							</c:when>
							<c:when test="${sessionScope.sessionVO.grade eq 'GOLD'}">
								<img src="/resources/user/images/mypage/gold_icon.png" alt=""><strong class="gold">Gold </strong>
							</c:when>
						</c:choose>
						
						<span> &nbsp;입니다.</span>
					</h3>
				</div>
				<div class="ranking_org_wrap">
					<ul class="ranking_detail_wrap">
						<li>
							<div class="rank_det_tit">
								<p>일반회원</p>
							</div>
							<div class="rank_det_cont">
								<img src="/resources/user/images/mypage/normal_icon.png" alt="">
								<p>디바이스를 <br>3개 미만 구매한 사용자</p>
							</div>
						</li>
						<li class="on">
							<div class="rank_list_wrap">
								<div class="rank_det_tit">
									<p>브론즈</p>
								</div>
								<div class="rank_det_cont">
									<img src="/resources/user/images/mypage/bronze_icon.png" alt="">
									<p>디바이스를<br /><strong>3</strong>개 이상 구매</p>
								</div>
							</div>
<!-- 							<div class="r_coup_wrap  hidden"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="left_disc"> -->
<!-- 										<p><em>&middot;</em>1만원 할인<em>&middot;</em></p> -->
<!-- 										<img src="/resources/user/images/mypage/coupon_icon.png" alt="" class="on"> -->
<!-- 									</div> -->
<!-- 									<div class="right_disc"> -->
<!-- 										<img src="/resources/user/images/mypage/download_icon.png" alt=""> -->
<!-- 										<p>쿠폰<br>다운받기</p> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
						</li>
						<li>
							<div class="rank_list_wrap">
								<div class="rank_det_tit">
									<p>실버</p>
								</div>
								<div class="rank_det_cont">
									<img src="/resources/user/images/mypage/silver_icon.png" alt="">
									<p>디바이스를<br /><strong>10</strong>개 이상 구매</p>
								</div>
							</div>
<!-- 							<div class="r_coup_wrap hidden"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="left_disc"> -->
<!-- 										<p><em>&middot;</em>1만원 할인<em>&middot;</em></p> -->
<!-- 										<img src="/resources/user/images/mypage/coup_gray.png" alt="" class="on"> -->
<!-- 									</div> -->
<!-- 									<div class="right_disc"> -->
<!-- 										<img src="/resources/user/images/mypage/download_icon.png" alt=""> -->
<!-- 										<p>쿠폰<br>다운받기</p> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
						</li>
						<li>
							<div class="rank_list_wrap">
								<div class="rank_det_tit">
									<p>골드</p>
								</div>
								<div class="rank_det_cont">
									<img src="/resources/user/images/mypage/gold_icon.png" alt="">
									<p>디바이스를<br /><strong>30</strong>개 이상 구매</p>
								</div>
							</div>
<!-- 							<div class="r_coup_wrap  hidden"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="left_disc"> -->
<!-- 										<p><em>&middot;</em>1만원 할인<em>&middot;</em></p> -->
<!-- 										<img src="/resources/user/images/mypage/coup_gray.png" alt="" class="on"> -->
<!-- 									</div> -->
<!-- 									<div class="right_disc"> -->
<!-- 										<img src="/resources/user/images/mypage/download_icon.png" alt=""> -->
<!-- 										<p>쿠폰<br>다운받기</p> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
						</li>
					</ul>
<!-- 					<ul class="ranking_detail_benefit hidden"> -->
<!-- 						<li> -->
<!-- 							<div class="benefit_arrow"></div> -->
<!-- 							<div class="benefit_cont active"> -->
<!-- 								<p>1만원 할인</p> -->
<!-- 								<div class="benefit_cont_img"></div> -->
<!-- 							</div> -->
<!-- 							<div class="benefit_download active"> -->
<!-- 								<a href="javascript:;"> -->
<!-- 									<span>쿠폰 다운받기</span> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<div class="benefit_arrow"></div> -->
<!-- 							<div class="benefit_cont"> -->
<!-- 								<p>2만원 할인</p> -->
<!-- 								<div class="benefit_cont_img"></div> -->
<!-- 							</div> -->
<!-- 							<div class="benefit_download"> -->
<!-- 								<a href="javascript:;"> -->
<!-- 									<span>쿠폰 다운받기</span> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<div class="benefit_arrow"></div> -->
<!-- 							<div class="benefit_cont"> -->
<!-- 								<p>3만원 할인</p> -->
<!-- 								<div class="benefit_cont_img"></div> -->
<!-- 							</div> -->
<!-- 							<div class="benefit_download"> -->
<!-- 								<a href="javascript:;"> -->
<!-- 									<span>쿠폰 다운받기</span> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 						</li> -->
<!-- 					</ul>	 -->
				</div>
<!-- 				<a href="#" class="more_btn btn btn_line open_cont hidden pcTab"><span>혜택 확인</span></a> -->
			</div>
			<div class="modal_window_history">
				<!-- <div class="history_cart" style="margin-top:0;">
					<h4 class="cont_tit02">구매내역</h4>
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width:10%">
								<col style="width:20%">
								<col style="width:25%">
								<col style="width:45%">
							</colgroup>
							<thead>
								<tr>
									<th>NO</th>
									<th>주문일시</th>
									<th>주문번호</th>
									<th>상품명</th>
								</tr>
							</thead>
							<tbody>
								<tr class="pcTab">
									<td>
										<p>1</p>
									</td>
									<td>
										<p>2021.01.12</p>
									</td>
									<td>
										<p>***************</p>
									</td>
									<td>
										<p>바이러스 클린 랩(레드)</p>
									</td>
								</tr>
								<tr class="mo">
									<td>
										<div class="tbl_in">
											<div class="histr_num">
												<p>NO.<em>1</em></p>
											</div>
											<div class="histr_cont">
												<div class="histr_det">
													<strong>주문일시</strong><span>2021.01.12</span>
												</div>
												<div class="histr_det">
													<strong>주문번호</strong><span>ABC-123456DDD</span>
												</div>
												<div class="histr_det">
													<strong>상품명</strong><span>바이러스클린랩 (레드)</span>
												</div>
											</div>
										</div>
									</td>
								</tr>	
								<tr class="pcTab">
									<td>
										<p>2</p>
									</td>
									<td>
										<p>2021.01.10</p>
									</td>
									<td>
										<p>***************</p>
									</td>
									<td>
										<p>바이러스 클린 랩(블랙)</p>
									</td>
								</tr>
								<tr class="pcTab">
									<td>
										<p>3</p>
									</td>
									<td>
										<p>2021.01.01</p>
									</td>
									<td>
										<p>***************</p>
									</td>
									<td>
										<p>바이러스 클린 랩(그린)</p>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="paging">
						<a href="#" class="prev no">prev</a>	
						<ul class="page">
							<li class="active"><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
						</ul>
						<a href="#" class="next">next</a>	
					</div>
				</div>-->
				<a href="#" class="btn btn_pp modal_cls_btn" style="width: 330px;"><span>확인</span></a>
			</div>
		</div>
	</div>
</div>

<!-- 문의상세보기 -->
<div class="modal_window modal_wrap qna_view_pop modal-qna-view">
	<div class="modal_window_cont ">
		<div class="">
			<div class="pop_top">
				<h4 class="cont_tit02">문의</h4>
				<a href="javascript:;" class="modal_cls_btn"><img src="/resources/user/ui_common/images/close_x_btn.png" alt=""></a>
			</div>
			<div class="popup_cont">
				<div class="qna_view">
					<div class="type"><P id="qna-modal-mgrpCd"></P></div>
					<div class="qna_right">
						<div class="qna_top">
							<p class="qna_tit" id="qna-modal-blcTitl"></p>
							<div class="date">
								<strong>작성일 </strong> <span id="qna-modal-userViewDtm"></span>
							</div>
						</div>
						<div class="qna_cont" id="qna-modal-blcSbc1"></div>
					</div>
				</div>
			</div>	
			
			<!--답변완료시 노출-->
			<div class="anwser">
				<div class="popup_cont">
					<div class="qna_view">
						<div class="type"><P>답변완료</P></div>
						<div class="ans_right">
							<p id="qna-modal-blcSbc2"></p>
						</div>
					</div>						
				</div>
			</div>
			
			<div class="btn_wrap">
				<a href="javascript:;" class="btn btn_pp modal_cls_btn" style="width: 330px;"><span>닫기</span></a>
			</div>
		</div>
	</div>
</div>

<!-- 문의하기 입력 폼-->
<div class="modal_window modal_wrap qna modal-qna-register">
	<div class="modal_window_cont ">
		<div class="inner_pop">
			<div class="pop_top">
				<h4 class="cont_tit02">문의하기</h4>
				<a href="javascript:;" class="modal_cls_btn"><img src="/resources/user/ui_common/images/close_x_btn.png" alt=""></a>
			</div>
			<div class="popup_cont">
				<form name="subQnaRegForm" method="post" action="/member/qna/register.vc" onsubmit="var rtn = formModalSubmitObj.submitQnaReg(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<c:catch var="qnaFormCatchException">
						<input type="hidden" name="prdSn" value="${result.info.prdSn}" />
					</c:catch>
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width:20%">
								<col style="width:80%">
							</colgroup>
							<thead>
							</thead>
							<tbody>
								<tr>
									<th>제목</th>
									<td>
										<input type="text" name="blcTitl" id="blcTitl" style="width:100%;" title="제목" placeholder="제목을 입력해주세요.">
									</td>
								</tr>
								<tr>
									<th>문의<br class="mo">종류</th>
									<td>
										<select name="mgrpCd" title="문의종류">
					                        <option value="">선택해주세요.</option>
					                    </select>
									</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>
										<textarea name="blcSbc1" id="blcSbc1" cols="30" rows="10" class="qna_write_cont" title="내용" placeholder="내용을 입력해주세요." ></textarea>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="btn_wrap">
						<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>문의하기</span></a>
						<a href="javascript:;" class="btn btn_line modal_cls_btn" style="width: 330px;"><span>취소</span></a>
					</div>
				</form>
			</div>				
		</div>
	</div>
</div>

<!-- 문의하기 수정 폼-->
<div class="modal_window modal_wrap qna modal-qna-modify">
	<div class="modal_window_cont ">
		<div class="inner_pop">
			<div class="pop_top">
				<h4 class="cont_tit02">문의하기</h4>
				<a href="javascript:;" class="modal_cls_btn"><img src="/resources/user/ui_common/images/close_x_btn.png" alt=""></a>
			</div>
			<div class="popup_cont">
				<form name="subQnaRegForm" method="post" action="/member/qna/modify.vc" onsubmit="var rtn = formModalSubmitObj.submitQnaReg(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<c:catch var="qnaFormCatchException">
						<input type="hidden" name="prdSn" value="${result.info.prdSn}" />
					</c:catch>
					<input type="hidden" name="blcSn" value="0" />
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width:20%">
								<col style="width:80%">
							</colgroup>
							<thead>
							</thead>
							<tbody>
								<tr>
									<th>제목</th>
									<td>
										<input type="text" name="blcTitl" id="blcTitl" style="width:100%;" title="제목" placeholder="제목을 입력해주세요.">
									</td>
								</tr>
								<tr>
									<th>문의종류</th>
									<td>
										<select name="mgrpCd" title="문의종류">
					                        <option value="">선택해주세요.</option>
					                    </select>
									</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>
										<textarea name="blcSbc1" id="blcSbc1" cols="30" rows="10" class="qna_write_cont" title="내용" placeholder="내용을 입력해주세요." ></textarea>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="btn_wrap">
						<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>수정하기</span></a>
						<a href="javascript:;" class="btn btn_line modal_cls_btn" style="width: 330px;"><span>취소</span></a>
					</div>
				</form>
			</div>				
		</div>
	</div>
</div>


<!-- 후기작성-->
<div class="modal_window modal_wrap review modal-review-register">
	<div class="modal_window_cont ">
		<div class="inner_pop">
			<div class="pop_top">
				<h4 class="cont_tit02">후기 작성하기</h4>
				<a href="javascript:;" class="modal_cls_btn"><img src="/resources/user/ui_common/images/close_x_btn.png" alt=""></a>
			</div>
			<div class="popup_cont">
				<form name="subReviewRegForm" method="post" enctype="multipart/form-data" action="/shop/review/register.vc" onsubmit="var rtn = formModalSubmitObj.submitReviewReg(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<input type="hidden" name="blcSn" value="0" />
					<input type="hidden" name="grade" value="0" />
					<input type="hidden" name="sgrpCd" value="" />
					<c:catch var="reviewFormCatchException">
						<input type="hidden" name="prdSn" value="${result.info.prdSn}" />
					</c:catch>
				
					<div class="star_review">
						<div class="star_inner">
							<p>평점</p>
							<div class="star">
								<label for="star1" class="starcheck">
									<input type="checkbox" id="star1" value="1">
									<img src="/resources/user/images/product/star.png" alt="">
								</label>
								<label for="star2" class="starcheck">
									<input type="checkbox" id="star2" value="2">
									<img src="/resources/user/images/product/star.png" alt="">
								</label>
								<label for="star3" class="starcheck">
									<input type="checkbox" id="star3" value="3">
									<img src="/resources/user/images/product/star.png" alt="">
								</label>
								<label for="star4" class="starcheck">
									<input type="checkbox" id="star4" value="4">
									<img src="/resources/user/images/product/star.png" alt="">
								</label>
								<label for="star5" class="starcheck">
									<input type="checkbox" id="star5" value="5">
									<img src="/resources/user/images/product/star.png" alt="">
								</label>
							</div>
							<p id="review-grade-txt">0</p>
						</div> 
					</div>
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width: 120px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th>제목</th>
									<td>
										<input type="text" name="blcTitl" id="blcTitl" style="width:100%;" title="제목" placeholder="제목을 입력해주세요.">
									</td>
								</tr>
								<tr class="id">
									<th>내용</th>
									<td>
										<textarea name="blcSbc1" id="blcSbc1" cols="30" rows="10" class="qna_write_cont" title="내용" placeholder="상품에 대한 소중한 평가를 남겨주세요." ></textarea>
<!-- 										<p class="">0/100</p> -->
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="add_img">
						<h4 class="cont_tit02"><p>사진첨부 <span>최대 3장까지 가능합니다</span></p></h4>
						<div class="add_file_box">
							${function:printAttachFileRegUser2(1, "THUMB", "사진첨부1")}
							${function:printAttachFileRegUser2(2, "THUMB", "사진첨부2")}
							${function:printAttachFileRegUser2(3, "THUMB", "사진첨부3")}
						</div>
					</div>
					<p class="point_o">※ 개인정보, 저작권침해, 욕설, 비방, 홍보성 글, 제품과 관계 없는 작성글은 관리자에 의해 임의 삭제 될 수 있습니다.</p>
					<div class="btn_wrap">
						<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>등록</span></a>
						<a href="javascript:;" class="btn btn_line modal_cls_btn" style="width: 330px;"><span>취소</span></a>
					</div>
				</form>
			</div>				
		</div>
	</div>
</div>

<!-- 쿠폰할인 적용 -->
<div class="modal_window modal_wrap coupon order modal-coupon-order">
	<div class="modal_window_cont ">
		<div class="inner_pop">
			<div class="pop_top">
				<h4 class="cont_tit02">쿠폰할인</h4>
			</div>
			
			<div class="popup_cont">
				<div class="table-type01">
					<table>
						<colgroup>
							<col style="width:auto">
							<col style="width:37%">
							<col style="width:18%">
							<col style="width:18%">
						</colgroup>
						<tbody>
							<!--  쿠폰 목록 -->
						</tbody>
					</table>
				</div>
				<div class="f_pay coupon">
					<div class="f_pay_inner">
						<ul>
							<li class="discount">
								<p class="info">총 할인금액 <span class="modal-coupon-order-discountPrice">0</span>원</p>
							</li>
							<li class="total">
								<p class="info">최종결제 금액 <span class="modal-coupon-order-totalPrice">0</span>원</p>
							</li>
						</ul>
					</div>
				</div>
				<div class="btn_wrap">
					<a href="#" class="btn btn_pp btnCouponDiscountPriceApply" style="width: 330px;"><span>쿠폰적용</span></a>
					<a href="javascript:;" class="btn btn_line modal_cls_btn" style="width: 330px;"><span>사용안함</span></a>
				</div>
			</div>				
		</div>
	</div>
</div>

<script>
//모달객체
var selectObject = null;

//할인전 가격
var couponOriginPrice = 0;

//할인적용 가격
var couponTotalPrice = 0;

//할인한 가격
var couponDiscountPrice = 0;

$(function(){
	/*
	 * 모달 닫기
	 */
	$(".modal_cls_btn").on("click", function(event){
		event.preventDefault();  
		modalUtil.close();
	});
	
	$('form[name=subReviewRegForm] input[id^=star]').on('change', function(event){
// 		if($(this).is(':checked')){
		var grade = $(this).val();
		$('form[name=subReviewRegForm] input[id^=star]').prop('checked', false);
		
		for(var i = 1; i <= grade; i++ ){
			$('form[name=subReviewRegForm] input[id=star'+ i +']').prop('checked', true);
		}
		
		$('form[name=subReviewRegForm] input[name=grade]').val(grade);
		$('#review-grade-txt').html(grade);
// 		}else{
// 			$('form[name=subReviewRegForm] input[id^=star]').prop('checked', false);
// 		}
	});
	
	/**
	 * 후기작성 이미지첨부파일 미리보기	
	 */
	$('.add_file_box input[type=file]').on('change', function(event){
		var input = this;
		if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
            	$(input).siblings('img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
	});
	
	//쿠폰선택하기 : CALCULATE
	$('input[name=couponDiscount]').live('click', function(event){
		var mgrpcd = $(this).data('mgrpcd');
		var maxprice = $(this).data('maxprice');
		var discount = parseInt($(this).val());
		
		if(mgrpcd =='WON'){
			couponDiscountPrice = discount;
		}else{
			couponDiscountPrice = Math.round(couponOriginPrice * (discount / 100));
			//최대값
			if(couponDiscountPrice > maxprice){
				couponDiscountPrice = maxprice;
			}
		}
		
		if(couponDiscountPrice < 0){
			couponDiscountPrice = couponOriginPrice; 
		}
		
		couponTotalPrice = couponOriginPrice - couponDiscountPrice;
		
		//할인가격
		$('span.modal-coupon-order-discountPrice').html(numUtil.createComma(couponDiscountPrice));
		
		//상품금액 - 할인가격 = 상품결제 가격
		$('span.modal-coupon-order-totalPrice').html(numUtil.createComma(couponTotalPrice));
	});
	
	//쿠폰 할인금액 적용하기
	$('.btnCouponDiscountPriceApply').on('click', function(event){
		event.preventDefault(); 
		
		$(selectObject).closest('tr').find('input[name=prdDiscountPrice]').val(couponDiscountPrice);
		$(selectObject).closest('tr').find('div.coupon_p').html(orderPriceCalculator.getWon(couponDiscountPrice));
		
		//전체 가격에서도 할인을 해야함.
		orderPriceCalculator.setDiscountPrice(couponDiscountPrice);
		
		//최종 prdTotalPrice의 가격 반영은 결제 버튼 눌렀을대 할것.
		modalUtil.close();
	});
	
	<%-- 사용자 리뷰 삭제 --%>
	$('a.btnReviewDel').on({
        'click' : function(event){
            event.preventDefault();  
            formModalSubmitObj.submitReviewDel($(this));
        }
    });
	
	<%-- 사용자 리뷰 수정 --%>
	$('a.btnReviewMod').on({
        'click' : function(event){
            event.preventDefault();  
            modalUtil.open('review-view', $(this));
        }
    });
});


/**
 * 할인 금액 반영 및 구매금액 최종 계산에 사용
 */
var orderPriceCalculator = {	
	deliveryFee : parseInt('${empty resultCode.info.deliveryMin ? 0 : resultCode.info.deliveryMin}'),
	deliveryFree : parseInt('${empty resultCode.info.deliveryFree ? 0 : resultCode.info.deliveryFree}'),
	/*
	 * 결제 상품의 가격 합계 : 변함없음.
	 */
	totalPrice : 0,
	/*
	 * 결제 상품의 할인가격 합계 : 쿠폰적용에 다라서 변함
	 */
	totalDiscountPrice: 0,
	/*
	 * 배송비 : 전체 결제 금액이 7만원 이상이면 0원 아니면 3,000원
	 */
	totalDeliveryFee : 0,
	/*
	 * 실계 결제 금액 : 결제 버튼이 눌러졌을때 계산할것.
	 */
	totalOderPriceOrigin : 0,
	totalOderPriceEdge : 0,
	/*
	 * 초기화
	 */
	initPrice : function(_totalPrice){
		this.totalPrice = _totalPrice;
		this.totalDeliveryFee = _totalPrice >= this.deliveryFree ? 0 : this.deliveryFee;
		
		//처음 세팅과 마지막 결제시만 totalOderPriceOrigin 사용합니다.
		this.totalOderPriceOrigin = this.totalPrice + this.totalDeliveryFee;
		
		//화면은 totalOderPriceEdge 사용합니다.
		this.totalOderPriceEdge = this.totalOderPriceOrigin;
		this.updateDocument();
	},
	/*
	 * 최종 결제전에 호출해서 input에 반영해준다.
	 */
	paymentPrice : function(){
		debugger;
		$('input[name=discountPrice]').val(this.totalDiscountPrice);
		$('input[name=deliveryFee]').val(this.totalDeliveryFee);
		$('input[name=orderPrice]').val(this.totalOderPriceEdge);
	},
	/*
	 * 쿠폰할인가격 적용
	 */
	setDiscountPrice : function(_discountPrice){
		this.totalDiscountPrice = _discountPrice;
		this.totalDeliveryFee = (this.totalPrice - this.totalDiscountPrice) > this.deliveryFree ? 0 : this.deliveryFee;
		this.totalOderPriceEdge = (this.totalPrice + this.totalDeliveryFee) - this.totalDiscountPrice;
		this.updateDocument();
	},
	/*
	 * 가격을 원표시로 변경
	 * 3000 -> 3,000 원
	 */
	getWon : function(_price){
		return numUtil.createComma(_price) + ' 원';
	},
	/*
	 * 화면에 결제 금액 갱신
	 */
	updateDocument : function(){
		$('p.txtTotalPrice').html(this.getWon(this.totalPrice));
		$('p.txtTotalDeliver').html(this.getWon(this.totalDeliveryFee));
		$('p.txtTotalDiscount').html(this.getWon(this.totalDiscountPrice));
		$('p.txtTotalOrderPrice').html(this.getWon(this.totalOderPriceEdge));
		$('p.deliveryPrice').html(this.totalDeliveryFee > 0 ? this.getWon(this.totalDeliveryFee) : '배송무료');
	} 
};

var modalUtil = {
		
	open : function(_id, object){
		selectObject = object;
		$('body,html').addClass('scroll_none');
		debugger;
		if(_id == 'qna-view'){
			formModalSubmitObj.submitQnaView($(object).data('sn'));
		}else if(_id == 'review-view'){
			formModalSubmitObj.submitReviewView($(object).data('sn'));
		}else if(_id == 'coupon-order'){
			formModalSubmitObj.submitCouponList();
		}else{
			$('body').addClass('modal_open');
			modalComUtil.open(_id);
		}
	},    
	close : function(object){
		$('body,html').removeClass('scroll_none');
		try{
			$('form[name^=sub]')[0].reset();
		}catch(e){
			//ignore
		}
		
		$('body').removeClass('modal_open');
		modalComUtil.close();
	}
};

var submitQnaViewUrl = '/member/qna/view.vc';
var formModalSubmitObj = {
	submitQnaView : function(_id){
		$('.modal-qna-view .anwser').hide();
		$('#qna-modal-mgrpCd').html('');
		$('#qna-modal-blcTitl').html('');
		$('#qna-modal-blcSbc1').html('');
		$('#qna-modal-blcSbc2').html('');
		$('#qna-modal-userViewDtm').html('');
		
		ajaxUtil.postDisableAsync(submitQnaViewUrl, { 'blcSn' : _id}, formModalSubmitObj.resultQnaView);
        return false;
	},
	resultQnaView : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined){
			var data = json.result.info;
			
			if(data.asYn == 'Y'){
				$('#qna-modal-mgrpCd').html(data.mgrpCd);
				$('#qna-modal-blcTitl').html(data.blcTitl);
				$('#qna-modal-blcSbc1').html(stringUtil.replaceNewLineBrTag(data.blcSbc1));
				$('#qna-modal-blcSbc2').html(stringUtil.replaceNewLineBrTag(data.blcSbc2));
				$('#qna-modal-userViewDtm').html(data.userViewDtm);
				$('.modal-qna-view .anwser').show();
				$('body').addClass('modal_open');
				modalComUtil.open('qna-view');
			}else{
				debugger;
				$('.modal-qna-modify select[name=mgrpCd] option[value="'+ data.mgrpCd +'"]').prop('selected', true);
				$('.modal-qna-modify input[name=blcSn]').val(data.blcSn);
				$('.modal-qna-modify input[name=blcTitl]').val(data.blcTitl);
				$('.modal-qna-modify textarea[name=blcSbc1]').val(data.blcSbc1);
				$('body').addClass('modal_open');
				modalComUtil.open('qna-modify');
			}
        }else{
            ajaxUtil.error(json);
        }
    },
    submitReviewDel : function(object){
		var msg = '상품리뷰를 삭제 하시겠습니까?'
	    if(confirm(msg)){
	        var data = { blcSn : $(object).data('sn') };
	        ajaxUtil.postDisableAsync('/shop/review/delete.vc', data, formModalSubmitObj.reviewDelResult);    
	    }
		
		return false;
	},
	reviewDelResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
        	debugger
        	$('.li-review-' + json.result.searchInfo.blcSn).remove();
        }else{
            ajaxUtil.error(json);
        }
    },
    submitReviewView : function(_id){
        var data = { blcSn : _id };
        ajaxUtil.postDisableAsync('/shop/review/view.vc', data, formModalSubmitObj.reviewViewResult);    
        return false;
	},
	reviewViewResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
        	debugger
//         	$('.li-review-' + json.result.searchInfo.blcSn).remove();
        }else{
            ajaxUtil.error(json);
        }
    },
	submitQnaReg : function(form){
        if(!submitUtil.isEmpty(form.blcTitl, '문의 제목을 입력해주세요')){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.mgrpCd, '문의 종류를 선택해주세요')){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc1, '문의 내용을 입력해주세요')){
            return false;
        }
        
        if(submitUtil.isNull(form.prdSn)){
        	form.prdSn.value = 0;
        }
           
		ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formModalSubmitObj.resultQnaReg);
        
        return false;
	},
	resultQnaReg : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
        	//location.replace('/member/qna/list.vc');
        	location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    },
    submitReviewReg : function(form){
    	
    	form.sgrpCd.value = '';
    	
    	console.log('form.grade.value :' +form.grade.value);
    	
        if(!submitUtil.isEmpty(form.prdSn, '상품정보가 없습니다.')){
        	return false;
        }

        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc1)){
            return false;
        }
        
        if(!submitUtil.isNull(form.attachFile1)){
        	form.sgrpCd.value = 'PHOTO';
            if(!submitUtil.isAttachFile(form.attachFile1, 'IMG')){
                return false;
            }
        }
        
        if(!submitUtil.isNull(form.attachFile2)){
        	form.sgrpCd.value = 'PHOTO';
            if(!submitUtil.isAttachFile(form.attachFile2, 'IMG')){
                return false;
            }
        }
        
        if(!submitUtil.isNull(form.attachFile3)){
        	form.sgrpCd.value = 'PHOTO';
            if(!submitUtil.isAttachFile(form.attachFile3, 'IMG')){
                return false;
            }
        }
        
        ajaxUtil.formSubmit($(form), formModalSubmitObj.resultReviewReg);
        
        return false;
	},
	resultReviewReg : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
        	location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    },
	submitCouponList : function(){
		ajaxUtil.postDisableAsync('/member/coupon/order/list.vc', {}, formModalSubmitObj.resultCouponList);
        return false;
	},
	resultCouponList : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined){
			var data = json.result.list;
			
			var htmlTxt = '';
			
			if(data.length > 0){
				for(var i = 0; i < data.length; i++)
				{
					if(data[i].cupSbc.length > 10){
						data[i].cupSbc = data[i].cupSbc.substr(0, 10) + '...';
					}
					htmlTxt += ' <tr>'
						    + ' 	<td>'
						    + ' 		<label class="input_radio" for="coupon' + i +'">'
						    + ' 			<input id="coupon' + i +'" name="couponDiscount" type="radio" data-mgrpcd="'+ data[i].mgrpCd +'" data-maxprice="'+ data[i].maxPrice +'" value="' + data[i].price + '">'
						    + ' 			<span>' + numUtil.createComma(data[i].price) + ' ' + (data[i].mgrpCd == "WON" ? '원' : '%') + '</span>'
						    + ' 		</label>'
						    + ' 	</td>'
						    + ' 	<td class="coupon_tit">'
						    + ' 		<p>' + data[i].cupTitl +'</p><img src="/resources/user/images/order/coupon_icon.png" alt="">'
						    + ' 	</td>'
						    + ' 	<td>' + data[i].cupSbc + '</td>'
						    + ' 	<td>' + data[i].expsFnhDtm + '까지</td>'
						    + ' </tr>';
				}
			}else{
				htmlTxt = '<tr><td colspan="4">할인가능 쿠폰이 없습니다.</td></tr>';
			}
			
			$('.modal-coupon-order table tbody').html(htmlTxt);

			couponOriginPrice = $(selectObject).closest('tr').find('input[name=prdTotalPrice]').val();
			couponTotalPrice = couponOriginPrice;
			couponDiscountPrice = 0;
			
			
			//할인가격
			$('span.modal-coupon-order-discountPrice').html(numUtil.createComma(couponDiscountPrice));
			
			//상품금액 - 할인가격 = 상품결제 가격
			$('span.modal-coupon-order-totalPrice').html(numUtil.createComma(couponTotalPrice));
		
			$('body').addClass('modal_open');
			modalComUtil.open('coupon-order');
        }else{
            ajaxUtil.error(json);
        }
    }
	
};
</script>

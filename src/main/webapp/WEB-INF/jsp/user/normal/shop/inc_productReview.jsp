<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 상품리뷰
--%>
<div class="inner">
	<div class="product_info_contents">
		<div class="review_box clearfix">
			<ul class="clearfix">
				<li>
					<p class="box_tit">전체평점</p>
					<div class="box_cont">
						<div class="box_inner clearfix">
							<div class="score">
								<h2><fmt:formatNumber value="${result.info.gradeAvg}" type="number"/></h2>
								<div class="star">
									<c:forEach begin="1" end="${result.info.gradeAvg}" varStatus="i">
										<img src="/resources/user/images/product/star.png" alt="">
									</c:forEach>
								</div>	
							</div>
							
							<ul class="review_info">
								<li>
									<p class="tit">&middot; 일반리뷰</p>
									<p><fmt:formatNumber value="${result.info.reviewCnt}" type="number"/>건</p>
								</li>
								<li>
									<p class="tit">&middot; 포토리뷰</p>	
									<p><fmt:formatNumber value="${result.info.reviewPhotoCnt}" type="number"/>건</p>
								</li>
								<li>
									<p class="tit">&middot; 찜 횟수</p>	
									<p><fmt:formatNumber value="${result.info.scrapCnt}" type="number"/>건</p>
								</li>
							</ul>
						</div>
					</div>
				</li>
				<li class="pcTab">
					<p class="box_tit">세부평점</p>
					<div class="box_cont">
						<div class="box_inner">
							<div class="detail_score">
								<!-- 평점 전체 갯수 -->
								<c:set var="totalGradeCnt" value="${result.info.grade1Cnt + result.info.grade2Cnt + result.info.grade3Cnt + result.info.grade4Cnt + result.info.grade5Cnt}" scope="page" />
								<ul>
									<li>
										<p class="star_num">
											<img src="/resources/user/images/product/star.png" alt=""><span>5</span>	
										</p>
										<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade5Cnt / totalGradeCnt}" type="percent" />;"></em></div>
										<p class="review_score_num"><fmt:formatNumber value="${result.info.grade5Cnt}" type="number"/></p>																	
									</li>
									<li>
										<p class="star_num">
											<img src="/resources/user/images/product/star.png" alt=""><span>4</span>	
										</p>
										<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade4Cnt / totalGradeCnt}" type="percent"/>;"></em></div>
										<p class="review_score_num"><fmt:formatNumber value="${result.info.grade4Cnt}" type="number"/></p>																	
									</li>
									<li>
										<p class="star_num">
											<img src="/resources/user/images/product/star.png" alt=""><span>3</span>	
										</p>
										<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade3Cnt / totalGradeCnt}" type="percent"/>;"></em></div>
										<p class="review_score_num"><fmt:formatNumber value="${result.info.grade3Cnt}" type="number"/></p>																	
									</li>
									<li>
										<p class="star_num">
											<img src="/resources/user/images/product/star.png" alt=""><span>2</span>	
										</p>
										<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade2Cnt / totalGradeCnt}" type="percent"/>;"></em></div>
										<p class="review_score_num"><fmt:formatNumber value="${result.info.grade2Cnt}" type="number"/></p>																	
									</li>
									<li>
										<p class="star_num">
											<img src="/resources/user/images/product/star.png" alt=""><span>1</span>	
										</p>
										<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade1Cnt / totalGradeCnt}" type="percent"/>;"></em></div>
										<p class="review_score_num"><fmt:formatNumber value="${result.info.grade1Cnt}" type="number"/></p>																	
									</li>
								</ul>
							</div>
						</div>
					</div>
				</li>
				<li class="pc">
					<p class="box_tit">포토리뷰 모아보기</p>
					<div class="box_cont">
						<div class="box_inner">
							<ul class="review_img_list clearfix">
								<c:set var="printPhoto" value="0" />
								<c:forEach items="${resultReview.list}" var="data">
									<c:if test="${data.sgrpCd eq 'PHOTO'}">
										<c:set var="printPhoto" value="${printPhoto + 1}" />
										<li><a class="goto-photo-review" href="javascript:;" data-sn="${data.blcSn}">${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}</a></li>
									</c:if>
								</c:forEach>
								<c:if test="${printPhoto < 10}">
									<c:forEach begin="${10 - (10 - printPhoto)}" end="10" step="1">
										<li></li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
					</div>
				</li>
			</ul>
			<div class="mo">
				<p class="box_tit">세부평점</p>
				<div class="box_cont">
					<div class="box_inner">
						<div class="detail_score">
							<!-- 평점 전체 갯수 -->
							<c:set var="totalGradeCnt" value="${result.info.grade1Cnt + result.info.grade2Cnt + result.info.grade3Cnt + result.info.grade4Cnt + result.info.grade5Cnt}" scope="page" />
							<ul>
								<li>
									<p class="star_num">
										<img src="/resources/user/images/product/star.png" alt=""><span>5</span>	
									</p>
									<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade5Cnt / totalGradeCnt}" type="percent" />;"></em></div>
									<p class="review_score_num"><fmt:formatNumber value="${result.info.grade5Cnt}" type="number"/></p>																	
								</li>
								<li>
									<p class="star_num">
										<img src="/resources/user/images/product/star.png" alt=""><span>4</span>	
									</p>
									<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade4Cnt / totalGradeCnt}" type="percent"/>;"></em></div>
									<p class="review_score_num"><fmt:formatNumber value="${result.info.grade4Cnt}" type="number"/></p>																	
								</li>
								<li>
									<p class="star_num">
										<img src="/resources/user/images/product/star.png" alt=""><span>3</span>	
									</p>
									<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade3Cnt / totalGradeCnt}" type="percent"/>;"></em></div>
									<p class="review_score_num"><fmt:formatNumber value="${result.info.grade3Cnt}" type="number"/></p>																	
								</li>
								<li>
									<p class="star_num">
										<img src="/resources/user/images/product/star.png" alt=""><span>2</span>	
									</p>
									<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade2Cnt / totalGradeCnt}" type="percent"/>;"></em></div>
									<p class="review_score_num"><fmt:formatNumber value="${result.info.grade2Cnt}" type="number"/></p>																	
								</li>
								<li>
									<p class="star_num">
										<img src="/resources/user/images/product/star.png" alt=""><span>1</span>	
									</p>
									<div class="score_bar"><em style="width:<fmt:formatNumber value="${result.info.grade1Cnt / totalGradeCnt}" type="percent"/>;"></em></div>
									<p class="review_score_num"><fmt:formatNumber value="${result.info.grade1Cnt}" type="number"/></p>																	
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="tabMobile">
				<p class="box_tit">포토리뷰 모아보기</p>
				<div class="box_cont">
					<div class="box_inner">
						<ul class="review_img_list clearfix">
							<c:set var="printPhoto" value="0" />
							<c:forEach items="${resultReview.list}" var="data">
								<c:if test="${data.sgrpCd eq 'PHOTO'}">
									<c:set var="printPhoto" value="${printPhoto + 1}" />
									<li><a class="goto-photo-review" href="javascript:;" data-sn="${data.blcSn}">${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}</a></li>
								</c:if>
							</c:forEach>
							<c:if test="${printPhoto < 10}">
								<c:forEach begin="${10 - (10 - printPhoto)}" end="10" step="1">
									<li></li>
								</c:forEach>
							</c:if>
						</ul>
						<a href="#" class="more_btn mo"><span>더 보기 &gt;</span></a>
					</div>
				</div>
			</div>
		
		</div>
		<div id="wrap-review" class="detail_review">
			<div class="r_tit">
				<h4>상품리뷰</h4>
				<span><fmt:formatNumber value="${resultReview.searchInfo.totalRow}" type="number"/> 개의 후기가 있어요</span>
			</div>
			<div class="nav_part" style="padding-bottom:10px">
				<div class="r_nav" style="display: flex;">
					<div class="r_nav_wrap">	
						<div class="nav_order">
							<select name="" id="">
								<option value="">별점 높은 순</option>
								<option value="">별점 낮은 순</option>
							</select>
						</div>
						<label class="input_checkbox">
							<input type="checkbox" id="showPhotoReview">
							<span><em>포토리뷰만 보기</em></span>
						</label>
					</div>
					
					<c:if test="${not empty sessionScope.sessionVO}">
						<a href="javascript:;" class="btn btn_pp nav_write pcTab"  onclick="modalUtil.open('review-register');" style="width: 330px;">
							<span>후기 작성하기</span>
						</a>
						<a href="javascript:;" class="btn btn_pp nav_write mo" onclick="modalUtil.open('review-register');" >
							<span>리뷰등록</span>
						</a>
					</c:if>
				</div>
				
<!-- 				<a href="#" class="more_btn btn btn_line"> <span>리뷰 더 보기</span></a> -->
			</div>
			<ul class="review_lists" >
				<c:choose>
                	<c:when test="${empty resultReview.list}">
                		<li class="revie_wrap nodata">등록된 상품리뷰가 없습니다.</li>
                	</c:when>
                	<c:otherwise>
                		<c:forEach items="${resultReview.list}" var="data" varStatus="i">
	                		<li class="li-review-${data.blcSn} revie_wrap ${data.sgrpCd eq 'PHOTO' ? 'photo-review' : ''}">
								<div class="review_head active pcTab" >
									<div class="star">
										<c:forEach begin="1" end="${data.grade}" varStatus="i">
											<img src="/resources/user/images/product/star.png" alt="">
										</c:forEach>
									</div>
									<a href="javascript:;" class="reviews_tit"><span><tag:html value="${data.blcTitl}" attr="NQ" /></span></a>
									<span>${data.mbrNick}</span>
									<em>|</em>
									<span>${data.userViewDtm}</span>
									<a href="#" data-cd="REVIEW" data-sn="${data.blcSn}" class="review_icon btn_like ${data.myScrapCnt > 0 ? 'active' : ''}"><span></span></a>
									<span class="review-scrap-cnt">${data.scrapCnt}</span>
								</div>
								<div class="review_head mo">
									<div class="rev_tit">
										<div class="star">
											<c:forEach begin="1" end="${data.grade}" varStatus="i">
												<img src="/resources/user/images/product/star.png" alt="">
											</c:forEach>
										</div>
										<a href="javascript:;" class="reviews_tit"><span><tag:html value="${data.blcTitl}" attr="NQ" /></span></a>
									</div>
									<div class="rev_id">
										<span>${data.mbrNick}</span>
										<span>${data.userViewDtm}</span>
									</div>
									<div class="review_icon_wrap">
										<a href="#" data-cd="REVIEW" data-sn="${data.blcSn}" class="review_icon btn_like ${data.myScrapCnt > 0 ? 'active' : ''}"><span></span></a>
										<span class="review-scrap-cnt">${data.scrapCnt}</span>
									</div> 
								</div>
								<div class="review_cont" >
									<div class="review_cont_wrap">
										<div>
											${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
											${function:printImageFileByList(data.fileSn2, data.lgrpCd, data.filNm2, data.filNm2)}
											${function:printImageFileByList(data.fileSn3, data.lgrpCd, data.filNm3, data.filNm3)}
										</div>
										<p><tag:html value="${data.blcSbc1}" attr="BR" /></p>
									</div>
									
									<%-- 자신의 리뷰만 관리가 가능하다. --%>
									<c:if test="${not empty sessionScope.sessionVO and sessionScope.sessionVO.sn eq data.mbrSn}">
										<div class="btn_wrap">
<%-- 											<a href="javascript:;" class="btn btn_pp btnReviewMod" data-sn="${data.blcSn}"><span>수정</span></a> --%>
											<a href="javascript:;" class="btn btn_line btnReviewDel" data-sn="${data.blcSn}"><span>삭제</span></a>
										</div>
									</c:if>
								</div>
							</li>
						</c:forEach>
                	</c:otherwise>
                </c:choose>
			</ul>
		</div>							
	</div>
</div>
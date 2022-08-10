<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeDet.jsp
    Description : 서비스 > NOTICE 상세조회
--%>
<link rel="stylesheet" href="/resources/user/css/community.css?v=${cacheParam}">

<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="#">Community</a></li>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="communityview">
		<div class="inner">
			<h3 class="p_tit01">Community</h3>
		</div>	
		<!-- <div class="banner" style="background:url(${function:printImageFileLink('THUMB', result.file.list)}) no-repeat;"></div> -->
		<div class="inner">
			<div class="tab_ui_mini">
				<div class="view_title">
					<p class="tit second"><tag:html value="${result.info.blcTitl}" attr="NQ" /></p>							
				</div>
				<div class="gallery_view">
					<div class="view_cont">
						<div class="review_cont_left">
							<img src="/resources/user/images/community/person.png" alt="">
							<div class="review_left_wrap">
								<p class="tit">
									<c:choose>
										<c:when test="${result.info.notiYn eq 'Y'}"><strong>관리자</strong></c:when>
										<c:otherwise><strong>${result.info.mbrNick}</strong></c:otherwise>
									</c:choose>
								</p>
								<p class="date second">${result.info.userViewDtm}</p>
							</div>
						</div>
						<div class="review_cont_right">
							<c:if test="${not empty result.file.list}">
								<p><strong>첨부파일</strong></p>
								<p class="second">${function:printAttachFileList2("Y", "ATTCH", result.file.list)}</p>
							</c:if>
						</div>						
					</div>
					
					<%-- 본문내용 --%>
					<div class="view_cont_img">
						<c:choose>
							<c:when test="${result.info.notiYn eq 'Y'}"><tag:html value="${result.info.blcSbc1}" attr="NQ" /></c:when>
							<c:otherwise><tag:html value="${result.info.blcSbc1}" attr="BR" /></c:otherwise>
						</c:choose>
									
					</div>	
					
					<%-- 댓글입력 --%>
					<div class="review_cont_wrap">
						<div class="comment">
							<div class="comment_numb">
								<p><strong>댓글</strong><strong class="orange">&nbsp;${result.replyCnt}&nbsp;</strong><span>&gt;</span></p>
							</div>
							
							<!-- 댓글입력 -->
							<c:if test="${not empty sessionScope.sessionVO}">
								<div class="comment_new">
									<form name="commentForm" method="post" action="${contextPath}/${requestUri}/reply/register.vc" onsubmit="var rtn = formReplySubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
										<div class="comment_write">
											<img src="/resources/user/images/community/person.png" alt="">
											<input type="hidden" name="blcSn" value="${result.info.blcSn}">
											<textarea name="blcCmdSbc" placeholder="댓글을 입력해주세요." class="review_textarea"></textarea>
											<div class="btn_wrap">
												<a href="#" class="btn btn_pp submit"><span>등록</span></a>
											</div>
										</div>
									</form>
									<div class="comment_notice">
										<p>#해당 내용과 관련 없는 내용일 경우 관리자에서 답변을 삭제할 수 있습니다.</p>
									</div>
								</div>
							</c:if>
							
							<c:if test="${not empty result.replyList}">
							<!-- 입력된 댓글 내용 -->
								<div class="comment_plus">
									<!-- 댓글 내용 -->
									<c:forEach var="data" items="${result.replyList}" varStatus="i">
										<c:if test="${data.parBlcCmdSn eq 0 }">
											<div class="comment_org comment_wrap">
												<div class="comment_org_main">
													<img src="/resources/user/images/community/person.png" alt="">
													<div class="comment_tit">
														<div class="comment_tit_main">
															<span class="tit"><strong>${data.mbrNick}</strong></span>
															<span class=""><tag:html value="${data.blcCmdSbc}" attr="BR" /></span>
														</div>
													</div>
												</div>
												<div class="comment_tit_det">
													<span class="date">${data.userViewDtm}</span>
													<c:if test="${not empty sessionScope.sessionVO and sessionScope.sessionVO.id eq data.mbrId}">
														<span><em>&middot;</em></span><a href="#" data-sn="${data.blcCmdSn}" class="delete btnReplyDel"><span >삭제</span></a>	
													</c:if>
												</div>
											</div>
										
											<c:if test="${not empty sessionScope.sessionVO}">
												<!-- 대댓글 입력 -->
												<form name="recommentForm${data.blcCmdSn}" method="post" action="${contextPath}/${requestUri}/reply/register.vc" onsubmit="var rtn = formReplySubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
													<div class="comment_write">
														<img src="/resources/user/images/community/person.png" alt="">
														<input type="hidden" name="blcSn" value="${data.blcSn}">
														<input type="hidden" name="parBlcCmdSn" value="${data.blcCmdSn}">
														<textarea name="blcCmdSbc" placeholder="댓글을 입력해주세요." class="review_textarea"></textarea>
														<div class="btn_wrap">
															<a href="#" class="btn btn_pp submit"><span>등록</span></a>
														</div>
													</div>
												</form>
											</c:if>
											
											<!--  대댓글 -->
											<c:forEach var="data2" items="${result.replyList}" varStatus="i">
												<c:if test="${data2.parBlcCmdSn eq data.blcCmdSn }">
													<div class="comment_done comment_wrap">
														<div class="comment_done_main">
															<img src="/resources/user/images/community/person.png" alt="">
															<div class="comment_tit">
																<div class="comment_tit_main">
																	<span class="tit"><strong>${data2.mbrNick}</strong></span>
																	<span class=""><tag:html value="${data2.blcCmdSbc}" attr="BR" /></span>
																</div>
															</div>
														</div>
														<div class="comment_tit_det">
															<span class="date">${data2.userViewDtm} </span>
															<c:if test="${not empty sessionScope.sessionVO and sessionScope.sessionVO.id eq data2.mbrId}">
																<span><em>&middot;</em></span><a href="#" data-sn="${data2.blcCmdSn}" class="delete btnReplyDel"><span >삭제</span></a>	
															</c:if>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>
									
								</div>	
							</c:if>
						</div>
						
						<%-- 다음/이전글 : 공지는 이전 다음글 보여줄수 없음. --%>
						<c:if test="${result.info.notiYn eq 'N'}">
							<div class="view_cont_bottom">
								<ul>
									<li class="none"> 
										<p>다음글 -</p>
										<c:choose>
										<c:when test="${not empty result.preInfo}">
											<a href="${contextPath}/${requestUri}/view.vc?blcSn=${result.preInfo.blcSn}&rowNum=${result.preInfo.rowNum}${function:searchQuery(result.searchInfo)}">${result.preInfo.blcTitl}</a>
											<p class="date">${result.preInfo.userViewDtm}</p>
										</c:when>									
										<c:otherwise><a href="#">다음 글이 없습니다.</a></c:otherwise>
	        							</c:choose>
									</li>
									<li> 
										<p>이전글 -</p>
										<c:choose>
										<c:when test="${not empty result.nextInfo}">
											<a href="${contextPath}/${requestUri}/view.vc?blcSn=${result.nextInfo.blcSn}&rowNum=${result.nextInfo.rowNum}${function:searchQuery(result.searchInfo)}">${result.nextInfo.blcTitl}</a>
											<p class="date">${result.nextInfo.userViewDtm}</p>
										</c:when>									
										<c:otherwise><a href="#">이전 글이 없습니다.</a></c:otherwise>
	        							</c:choose>
									</li>
								</ul>	
							</div>
						</c:if>
					</div>
					
					<div class="btn_wrap">
						<a href="${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}" class="btn btn_pp" style="width: 200px;"><span>목록</span></a>
					</div>
				</div>
			</div>			
		</div>
	</div>
</div>

<script type="text/javascript">
$(function () {
	$('a.btnReplyDel').on('click', function(e){
		e.preventDefault();
		formReplySubmitObj.submitDel($(e.currentTarget));
	});
});

var formReplySubmitObj = {
    submit : function(form){
    	if(!submitUtil.isEmpty(form.blcCmdSbc)){
            return false;
        }
    	
        ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formReplySubmitObj.result);    
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined){
           location.reload(true);
        }else{
           
        }
    },
    submitDel : function(el){
        if (confirm('댓글을 삭제하시겠습니까?')) {
        	$(el).closest('div.comment_wrap').remove();
            ajaxUtil.postDisableAsync('${contextPath}/${requestUri}/reply/delete.vc' , {blcSn : '${result.info.blcSn}', blcCmdSn : $(el).data('sn')}, formReplySubmitObj.resultDel);    
        }
        return false;
    },
    resultDel : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined){
//         	location.reload(true);
        }else{
           
        }
    }
};
</script> 

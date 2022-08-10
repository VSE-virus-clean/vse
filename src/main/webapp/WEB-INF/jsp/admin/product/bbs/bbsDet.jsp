<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeMod.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 수정폼
--%>
<div class="sec_top">
	<h3 class="sec_tit">커뮤니티 상세</h3>
	<ul class="top_tab">
		<li><a href="#">게시판관리</a></li>
		<li><a href="#">커뮤니티관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">커뮤니티</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	    	<input name="blcSn" type="hidden" value="${result.info.blcSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="190px" /><col width="*" /><col width="190px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th>등록자</th>
	                <td colspan="3">아이디 : ${result.info.mbrId}<br/>닉네임 : ${result.info.mbrNick} <br/> 이름 : ${result.info.mbrNm}</td> 
	            </tr>
	            <tr>    
	                <th>등록일</th>
	                <td colspan="3">${result.info.rgstDtm}</td>
	            </tr>
	            <tr>    
	                <th>제목</th>
	                <td colspan="3">
	                	<tag:html value="${result.info.blcTitl}" attr="NQ" />
	                </td> 
	            </tr>
	            <tr>    
	                <th>공개여부</th>
	                <td colspan="3">
	                	${result.info.useYn eq 'Y' ? '공개' : '비공개'}
<!-- 	                    <label class="radio_box" for="useY"><input type="radio" name="useYn" id="useY" value="Y" /><span>공개</span></label>&nbsp;&nbsp; -->
<!-- 	                    <label class="radio_box" for="useN"><input type="radio" name="useYn" id="useN" value="N" /><span>비공개</span></label> -->
	                </td>  
	            </tr>	            
	            <tr>    
	                <th>내용</th>
	                <td colspan="3" class="con">
	                    <tag:html value="${result.info.blcSbc1}" attr="BR" />
	                </td>  
	            </tr>
	            <tr>    
	                <th>이미지</th>
	                <td colspan="3" class="con">
	                	<p>${function:printImageFile("THUMB", result.file.list)}</p>
	                </td>   
	            </tr>
	            <tr>    
	                <th>첨부파일</th>
	                <td colspan="3" class="con">
	                	<p>${function:printAttachFileList2("Y", "ATTCH", result.file.list)}</p>
	                </td>   
	            </tr>
	        </table>
	        
	        <div class="review_cont_wrap">
				<div class="comment">
					<div class="comment_numb">
						<p><strong>댓글</strong><strong class="orange">&nbsp;${result.replyCnt}&nbsp;</strong><span>&gt;</span></p>
					</div>
					
					<c:if test="${not empty result.replyList}">
					<!-- 입력된 댓글 내용 -->
						<div class="comment_plus">
							<!-- 댓글 내용 -->
							<c:forEach var="data" items="${result.replyList}" varStatus="i">
								<c:if test="${data.parBlcCmdSn eq 0 }">
									<div class="comment_org comment_wrap">
										<div class="comment_org_main">
									   <!-- <img src="/resources/user/images/community/person.png" alt=""> -->
											<div class="comment_tit">
												<div class="comment_tit_main">
													<span class="tit"><strong>${data.mbrNick}</strong></span>
													<span class="det"><tag:html value="${data.blcCmdSbc}" attr="BR" /></span>
												</div>
											</div>
										</div>
										<div class="comment_tit_det">
											<span class="date">${data.userViewDtm}</span>
											<span><em>&middot;</em></span><a href="#" data-sn="${data.blcCmdSn}" class="delete btnReplyDel"><span >삭제</span></a>	
										</div>
									</div>
									
									<!--  대댓글 -->
									<c:forEach var="data2" items="${result.replyList}" varStatus="i">
										<c:if test="${data2.parBlcCmdSn eq data.blcCmdSn }">
											<div class="comment_done comment_wrap">
												<div class="comment_done_main">
												<!--<img src="/resources/user/images/community/person.png" alt="">-->
													<div class="comment_tit">
														<div class="comment_tit_main">
															<span class="tit"><strong>${data2.mbrNick}</strong></span>
															<span class="det"><tag:html value="${data2.blcCmdSbc}" attr="BR" /></span>
														</div>
													</div>
												</div>
												<div class="comment_tit_det">
													<span class="date">${data2.userViewDtm} </span>
													<span><em>&middot;</em></span><a href="#" data-sn="${data2.blcCmdSn}" class="delete btnReplyDel"><span >삭제</span></a>	
												</div>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
							
						</div>	
					</c:if>
				</div>
				
			</div>
	        
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
				</div>
			</div>
	    </form>
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
	    submitDel : function(el){
	        if (confirm('댓글을 삭제하시겠습니까?')) {
	        	$(el).closest('div.comment_wrap').remove();
	            ajaxUtil.postDisableAsync('/product/reply/delete.vc' , {blcSn : '${result.info.blcSn}', blcCmdSn : $(el).data('sn')}, formReplySubmitObj.resultDel);    
	        }
	        return false;
	    },
	    resultDel : function(json){
	        try{
	            json = JSON.parse(json);
	        }catch(e){}
	        
	        if(json.bindingStatus == undefined && json.result != undefined){
// 	         	location.reload(true);
	        }else{
	           
	        }
	    }
	};
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeReg.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 입력폼
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
			<h3 class="p_tit">Community</h3>
		</div>	
<!-- 		<div class="banner"></div> -->
		<div class="inner">
			 <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
				<div class="table-type01 write_form">
					<table>
						<colgroup>
							<col style="width: 200px;">
							<col>
						</colgroup>
						<tbody>							
							<tr>
								<th>제목</th>
								<td>
									<input type="text" id="blcTitl" name="blcTitl" maxlength="100" title="제목"  placeholder="제목을 입력해주세요."/>
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<textarea name="blcSbc1" id="blcSbc1" cols="30" rows="12" style="background-color: #efefef;" title="내용" placeholder="내용을 입력해주세요."></textarea>
								</td>
							</tr>
							<tr>
								<th>대표이미지</th>
								<td>
									${function:printAttachFileRegUser(1, "THUMB", "배너이미지")}
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td>
									${function:printAttachFileRegUser(2, "ATTCH", "배너이미지")}
								</td>
							</tr>
						</tbody>
					</table>
				</div>	
				<div class="btn_wrap">
					<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>등록</span></a>
					<a href="${contextPath}/${requestUri}/list.vc" class="btn btn_line" style="width: 330px;"><span>취소</span></a>
				</div>
			</form>
		</div>
	</div>
</div>


<script type="text/javascript">
$(function () {
    $('form[name=mainForm]').ready(function(){
    	
    });
});

var formSubmitObj = {
    submit : function(form){
        
        //제목
        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc1)){
            return false;
        }
        
        if(!submitUtil.isNull(form.attachFile1)){
            if(!submitUtil.isAttachFile(form.attachFile1, 'IMG')){
                return false;
            }
        }
        
        if(!submitUtil.isNull(form.attachFile2)){
            if(!submitUtil.isAttachFile(form.attachFile2, 'ALL')){
                return false;
            }
        }
        
        ajaxUtil.formSubmit($(form), formSubmitObj.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.uploadInfo.status ){
            location.replace('${contextPath}/${requestUri}/list.vc');
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : categoryList.jsp
    Description : 상품 카테고리 관리
--%>
<style>
.btn.sm {padding:0 10px;display: inline-block;line-height: 35px;font-size: 16px;text-align:center;}
.btn.sm.on { display: inline-block; }
.btn.sm.off { display:none; }

.table_type_01 {}
.table_type_01 .tit {float:left;width:50%;display:inline-block;}
.table_type_01 input {height:30px;}
.table_type_01 input.num {width:40px;}
.table_type_01 li {overflow:hidden;line-height:50px;width:100%;box-sizing: border-box; background-color: #FFF;}
.table_type_01 > li > div {background-color:#F9F9F9;padding:10px;overflow:hidden;width:100%;    box-sizing: border-box;}
.table_type_01 > li > ul li{padding:0 20px;border-bottom:1px solid #ccc;}
.table_type_01 .control {float:right;}
.table_type_01 .control span {margin-left:5px;}
.table_type_01 .btn + .btn {margin-left:5px;}

ul.depth1 input, ul.depth2 input{  width:90%; }
ul.depth1 input.readonly{  border:none; background-color: #F9F9F9; }
ul.depth2 input.readonly{  border:none; background-color: #FFF;}
</style>

<div class="sec_top">
	<h3 class="sec_tit">상품 카테고리 관리</h3>
	<ul class="top_tab">
		<li><a href="#">상품관리</a></li>
	</ul>
</div>

<div class="sec_cont">
	<div class="sec_cont_top">
		<h4 class="cont_tit">카테고리 추가</h4>
		<input type="text" name="comCdNm" data-prencomcd="PRODUCT-CATE" style="width:80%;height:35px;margin-right:10px;" title="카테고리명" placeholder="카테고리명을 입력해 주세요."/>
		<button type="button" class="btn btn_gray sm btn-add-1depth">+ 등록</button>
	</div>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">카테고리 목록</h4>
	<ul class="table_type_01 depth1">
		<c:forEach items="${result.list}" var="data" varStatus="i">
			<c:choose>
			<c:when test="${i.first}">
				<li id="delSeq_${data.comCdSn}" data-prencomcd="${data.prenComCd}" >
					<div>
			</c:when>
			<c:when test="${data.depth eq result.list[i.index-1].depth}">
				<c:if test="${data.depth eq 1}">
						</div>
					</li>
					<li id="delSeq_${data.comCdSn}" data-prencomcd="${data.prenComCd}" >
						<div>
				</c:if>
				<c:if test="${data.depth eq 2}">
					</li>
					<li id="delSeq_${data.comCdSn}" data-prencomcd="${data.prenComCd}" >
				</c:if>
			</c:when>
			<c:when test="${data.depth ne result.list[i.index-1].depth}">
				<c:if test="${data.depth eq 1}">
							</li>
						</ul>
					</li>
					<li id="delSeq_${data.comCdSn}" data-prencomcd="${data.prenComCd}" >
						<div>
				</c:if>
				<c:if test="${data.depth eq 2}">
					</div>
					<ul class="depth2">
						<li id="delSeq_${data.comCdSn}" data-prencomcd="${data.prenComCd}" >
				</c:if>
			</c:when>
			</c:choose>
			
			<p class="tit">- <input type="text" name="comCdNm" value="${data.comCdNm}" class="readonly" readonly="readonly" title="카테고리명" placeholder="카테고리명을 입력해 주세요."></p>
			<p class="control">
				<c:if test="${data.depth eq 1}">
					<span><a class="btn btn_red sm btn-add-2depth" data-cd="${data.comCd}" href="#">+ 카테고리등록</a></span>
				</c:if>
				<span>
					<a class="btn btn_red sm levelonehandle" href="javascript:;">Drag</a>
					<a class="btn btn_gray sm btn-edit-code" data-sn="${data.comCdSn}" href="#">수정</a>
					<a class="btn btn_gray sm btn-save-code off" data-sn="${data.comCdSn}" href="#">저장</a>
					<a class="btn btn_gray sm btn-del-code" data-sn="${data.comCdSn}" href="#">삭제</a>
				</span>
			</p>
			
			<c:choose>
			<c:when test="${i.last}">
					</div>
				</li>
				
				<c:if test="${data.depth eq 1}">
						</div>
					</li>
				</c:if>
				<c:if test="${data.depth eq 2}">
							</li>
						</ul>
					</li>
				</c:if>
			</c:when>
			</c:choose>
			
		</c:forEach>
		
		
<!-- 		<li> -->
<!-- 			<div> -->
<!-- 				<p class="tit">- SHOP</p> -->
<!-- 				<p class="control"> -->
<!-- 					<span>순서 <input type="text" class="num"/></span> -->
<!-- 					<span> -->
<!-- 						<a class="btn btn_red sm" href="#">+ 카테고리등록</a> -->
<!-- 						<a class="btn btn_gray sm" href="#">수정</a> -->
<!-- 						<a class="btn btn_gray sm" href="#">삭제</a> -->
<!-- 					</span> -->
<!-- 				</p> -->
<!-- 			</div> -->
<!-- 			<ul> -->
<!-- 				<li> -->
<!-- 					<p class="tit">- 바이러스클린랩</p> -->
<!-- 					<p class="control"> -->
<!-- 						<span>사용 | 순서 <input type="text" class="num"/></span> -->
<!-- 						<span> -->
<!-- 							<a class="btn btn_gray sm" href="#">수정</a> -->
<!-- 							<a class="btn btn_gray sm" href="#">삭제</a> -->
<!-- 						</span> -->
<!-- 					</p> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<p class="tit">- 액세사리</p> -->
<!-- 					<p class="control"> -->
<!-- 						<span>사용 | 순서 <input type="text" class="num"/></span> -->
<!-- 						<span> -->
<!-- 							<a class="btn btn_gray sm" href="#">수정</a> -->
<!-- 							<a class="btn btn_gray sm" href="#">삭제</a> -->
<!-- 						</span> -->
<!-- 					</p> -->
<!-- 				</li> -->
<!-- 			</ul> -->
<!-- 		</li> -->
	</ul>
</div>
               
<!-- <div class="sec_cont"> -->
<!-- 	<h4 class="cont_tit">카테고리</h4> -->
	
<!-- 	<div class="content-body"> -->
<!-- 		<a href="#" onclick="appendMenuRow();">카테고리 추가</a> -->
<!-- 	</div> -->
<!-- 	<div id="categoryList" class="content-body"> -->
<!--  		<ul class="depth1"> -->
<!--  			<li> -->
<!--                 <a href="#" data-comsn="0" class="btn-del-menu-cate"><img src="/ar/images/btn_trash.png" alt=""></a> -->
<!--                 <input type="text" name="comCd" class="input-type1 wd327" placeholder="메뉴명을 입력해 주세요." maxlength="16" value="" /> -->
<!--                 <img class="btnSortable off" src="/ar/images/btn_arrow_tb.png" alt=""> -->
<!--                 <a href="#" class="btn-edit-menu-cate btn-typeC bg-fc off">수정</a> -->
<!--                 <a href="#" class="btn-save-menu-cate btn-typeC bg-fc on">저장</a> -->
<!--             </li> -->
<!--  		</ul> -->
<!--     </div> -->
<!-- </div> -->

<script type="text/javascript">
$(function () {
    $('input.upSellYn').on({
        'change' : function(event){
            var yn = $(this).prop('checked') ? 'N' : 'Y';
            ajaxUtil.postDisableAsync('${contextPath}/${requestUri}/updateSellYn.vc', { 'prdSn' : $(this).data('sn'), 'sellYn' : yn }, function(result){
            	debugger;
            });
        }
    });
});

$(document).ready(function () {
	
	//1depth 등록
	$('.btn-add-1depth').live('click', function(event){
        event.preventDefault();

        var comCdNm = $(this).closest('div').find('input[name=comCdNm]').val().trim();
        var data = { comCdNm : comCdNm, prenComCd : 'PRODUCT-CATE' }
     
        if(comCdNm.length){
            ajaxUtil.postDisableAsync('${contextPath}/product/category/register.vc', data, formSubmitObj.submitResult);
        }else{
        	alert('카테고리명을 입력해 주세요.');
        	$(this).closest('div').find('input[name=comCdNm]').focus();
        }
    });
	
	//2depth 등록
	$('.btn-add-code').live('click', function(event){
        event.preventDefault();

        var comCdNm = $(this).closest('li').find('input[name=comCdNm]').val().trim();
        var data = { comCdNm : comCdNm, prenComCd : $(this).closest('li').find('input[name=comCdNm]').data('prencomcd') }
     
        if(comCdNm.length){
            ajaxUtil.postDisableAsync('${contextPath}/product/category/register.vc', data, formSubmitObj.submitResult);
        }else{
        	alert('카테고리명을 입력해 주세요.');
        	$(this).closest('li').find('input[name=comCdNm]').focus();
        }
    });
	
	$('.btn-add-2depth').live('click', function(event){
        event.preventDefault();
        
        var li = $(this).closest('li');
    	var rowHtml = '<li>'
    			    + '		<p class="tit">- <input type="text" name="comCdNm" data-prencomcd="' + $(this).data('cd') +'" value="" title="카테고리명" placeholder="카테고리명을 입력해 주세요."></p>'
    			    + '		<p class="control">'
    			    + '			<span>'
    			    + '				<a class="btn btn_red sm btn-add-code" data-sn="${data.comCdSn}" href="javascript:;">+ 등록</a>'
    			    + '			</span>'
    			    + '		</p>'
    			    + '</li>';
        
        if(!$(li).find('ul').length > 0 ){
            $(li).append('<ul class="depth2"></ul>');
        }

        var depth2 = $(li).find('ul');

        $(depth2).append(rowHtml);
    });
	
    //삭제
    $('.btn-del-code').live('click', function(event){
        event.preventDefault();
       	var msg = '카테고리를 삭제 하시겠습니까?'
   	    if(confirm(msg)){
   	        var data = { comCdSn : $(this).data('sn') };
   	        ajaxUtil.postDisableAsync('${contextPath}/product/category/delete.vc', data, formSubmitObj.submitResult);    
   	    }
      	
    });

    //수정버튼
    $('.btn-edit-code').live('click', function(event){
        event.preventDefault();
        $(this).removeClass('on').addClass('off');
        $(this).siblings('a.btn-save-code').removeClass('off').addClass('on');
        $(this).closest('li').find('input[name=comCdNm]:eq(0)').removeClass('readonly').prop('readonly', false);
    });
    
    //수정
    $('.btn-save-code').live('click', function(event){
        event.preventDefault();

        var comCdNm = $(this).closest('li').find('input[name=comCdNm]:eq(0)').val().trim();
        var comCdSn = $(this).data('sn');
        var data = { comCdNm : comCdNm, comCdSn : comCdSn }
     
        if(comCdNm.length){
            ajaxUtil.postDisableAsync('${contextPath}/product/category/modify.vc', data, formSubmitObj.submitResult);
        }else{
        	alert('카테고리명을 입력해 주세요.');
        	$(this).closest('li').find('input[name=comCdNm]:eq(0)').focus();
        }
    });

    //순서변경 : depth1
    $(".sec_cont ul.depth1").sortable({
    	handle: ".levelonehandle",
        placeholder: "ui-state-highlight",
        update: function( event, ui ) {
        	console.log($(this).sortable('serialize', { key: 'delSeq' }));
        	ajaxUtil.postDisableAsync('${contextPath}/product/category/sort.vc', $(this).sortable('serialize', { key: 'delSeq' }), formSubmitObj.submitResult);
        }
    });
    $(".sec_cont ul.depth1").disableSelection();

    //순서변경 : depth2
    $(".sec_cont ul.depth1 ul.depth2").sortable({
    	handle: ".levelonehandle",
        placeholder: "ui-state-highlight",
        update: function( event, ui ) {
        	console.log($(this).sortable('serialize', { key: 'delSeq' }));
        	ajaxUtil.postDisableAsync('${contextPath}/product/category/sort.vc', $(this).sortable('serialize', { key: 'delSeq' }), formSubmitObj.submitResult);
        }
    });
    $(".sec_cont ul.depth2").disableSelection();

});

var formSubmitObj = {
    submitResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
            location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>
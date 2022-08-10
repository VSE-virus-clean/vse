<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
    JSP Name : chatList.jsp
    Description : chat 목록
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<div id="container">
	<div class="tab_area">
        <div class="left">
            <a href="/member/myInfo/changeInfo" class="btnbx2 bdefault">会員情報変更</a>
            <a href="/member/qna/list" class="btnbx2 bdefault">お問合せリスト</a>
            <a href="/member/order/list" class="btnbx2 bdefault on">購入リスト</a>
        </div>
    </div>
    
    <div class="board-list">
    	<table cellspacing="0" class="tbl_list">
	        <thead>
	            <tr>
	                <th style="width:130px;">注文日</th>
	                <th>商品名</th>
	                <th style="width:150px;">注文番号</th>
	                <th style="width:60px;">注文数量</th>
	                <th style="width:80px;">決済方法</th>
	                <th style="width:130px;">決済完了日</th>
	                <th style="width:130px;">発送日</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:choose>
	                <c:when test="${empty result.list}">
	                    <tr>
	                        <td colspan="7">検索された注文内容がございません。</td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${result.list}" var="data" varStatus="i">
	                        <tr data-orderid="${data.orderId}" data-name="${data.itemNm}">
	                            <td>${data.rgstDtm}</td>
	                            <td class="left">
	                            	<a href="/shop/item/view?blcSn=${data.cotnSn}">${data.itemNm}</a>
                            	</td>
	                            <td>${data.orderId}</td>
	                            <td>${data.orderQuantity}</td>
	                            <td>${data.payType eq '1' ? '口座振込み' : 'カード決済'}</td>
	                            <td>${not empty data.pgDtm ? data.pgDtm : '-'}</td>
	                            <td>
	                            	<c:choose>
	                            		<c:when test="${data.useYn eq 'N'}">注文キャンセル<br/>${data.mdftDtm}</c:when>
	                            		<c:when test="${data.useYn eq 'Y' and data.deliveryYn eq 'N'}"><a href="/${requestUri}/cancle" class="btnbx bdark btn_cancle">注文キャンセル</a></c:when>
	                            		<c:when test="${data.deliveryYn eq 'Y'}">配送完了<br/>${data.mdftDtm}</c:when>
	                            	</c:choose>
	                            </td>
	                        </tr>
	                    </c:forEach>
	                </c:otherwise>
	            </c:choose>
	        </tbody>
	    </table>
	</div>	
	
    <div class="paginate">
        <tag:paging url="/${requestUri}/list?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
    </div>
</div>


<script type="text/javascript">
$(function () {
	$('.btn_cancle').on({
	    click : function(event){
	        event.preventDefault();
	        formSubmitObj.submitCancle($(this));
	    } 
	});
});

var formSubmitObj = {
	submitCancle : function(object){
        if(confirm($(object).parents('tr').data('name') + 'のご注文をキャンセルしますか？')){
            var data = { 'orderId' :  $(object).parents('tr').data('orderid') };
            ajaxUtil.postDisableAsync($(object).attr('href'), data, formSubmitObj.resultCancle);
        } 
    },
    resultCancle : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
            alert('ご注文をキャンセルしました。');
            location.reload(true);
        }else{
            ajaxUtil.error(json);     
        }
    },
}
</script>
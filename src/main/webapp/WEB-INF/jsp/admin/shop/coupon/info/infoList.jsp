<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeList.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 목록
--%>
<div class="sec_top">
	<h3 class="sec_tit">쿠폰관리</h3>
	<ul class="top_tab">
		<li><a href="#">쿠폰관리</a></li>
		<li><a href="#">쿠폰목록</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">조회조건</h4>
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<table class="r_search_table">
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
					   <th>등록일</th>
					   <td colspan="3">										
							<input type="text" id="searchStartDate" name="searchStartDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off"  value=""> <em> ~ </em>
							<input type="text" id="searchEndDate" name="searchEndDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" value="">
						</td>
					</tr>
					<tr>
						<th>검색어</th>
						<td colspan="3">
					   		<input type="hidden" id="searchType" name="searchType" value="0">
							<input type="text" id="searchKey" name="searchKey" title="상세검색입력" style="width:450px;" maxlength="100" minlength="2"/>
						</td>
					</tr>
					<tr>
						<th>할인타입</th>
						<td>
							<label class="radio_box" for="mgrpCd1">
								<input type="radio" name="searchGubunType2" id="mgrpCd1" value="" checked="checked">
								<span>전체</span>
							</label>
							<label class="radio_box" for="mgrpCd2">
								<input type="radio" name="searchGubunType2" id="mgrpCd2" value="PER">
								<span>할인(%)</span>
							</label>
							<label class="radio_box" for="mgrpCd3">
								<input type="radio" name="searchGubunType2" id="mgrpCd3" value="WON">
								<span>할인금액(원)</span>
							</label>
						</td>
						<th>할인상태</th>
						<td>
							<label class="radio_box" for="lgrpCd1">
								<input type="radio" name="searchGubunType" id="lgrpCd1" value="" checked="checked">
								<span>전체</span>
							</label>
							<label class="radio_box" for="lgrpCd2">
								<input type="radio" name="searchGubunType" id="lgrpCd2" value="지급쿠폰">
								<span>지급</span>
							</label>
							<label class="radio_box" for="lgrpCd3">
								<input type="radio" name="searchGubunType" id="lgrpCd3" value="다운쿠폰">
								<span>다운</span>
							</label>
							<label class="radio_box" for="lgrpCd4">
								<input type="radio" name="searchGubunType" id="lgrpCd4" value="이벤트쿠폰">
								<span>이벤트</span>
							</label>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">조회</button>
				<button type="reset" class="btn btn_gray">초기화</button>
			</div>
		</form>
	</div>
	
	<div class="content-body">
		<div class="table_tit">
			<div class="table_left_title">
				<span>총<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong>건 조회</span>
			</div>
		</div>
    
    	<form name="subForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		    <table class="cont_table" style="table-layout:fixed">
		    	<colgroup>
					<col style="width:100px">
					<col style="">  
					<col style="width:250px">
					<col style="width:100px">
					<col style="width:120px">
					<col style="width:100px">
					<col style="width:90px">
					<col style="width:90px">
					<col style="width:150px">
					<col style="width:180px">
				</colgroup>
		        <thead>
		            <tr>
		                <th><input type="checkbox" class="checkbox-selectAll" id="checkbox-selectAll">번호</th>
		                <th>쿠폰명</th>
						<th>사용기간</th>
						<th>할인타입</th>
						<th>발급타입</th>
						<th>상태</th>                           
						<th>발급수</th>
						<th>사용수</th>
						<th>등록일</th>
		                <th>관리</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="10" class="nodata">등록된 정보가 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>
		                            	<input type="checkbox" id="sn_${data.cupMetaSn}" name="delSeq" class="check2 checkbox-select" value="${data.cupMetaSn}" title="선택"> ${function:rowNumber(result.searchInfo, i.count)}
		                            </td>
		                            <td>${data.cupTitl}</td>
									<td>${data.expsRgstDtm}  ~  ${data.expsFnhDtm}</td>
									<td>
										<c:choose>
											<c:when test="${data.mgrpCd eq 'WON'}">
												<fmt:formatNumber value="${data.price}" type="number"/> 원
											</c:when>
											<c:when test="${data.mgrpCd eq 'PER'}">
												${data.price} %
											</c:when>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${data.lgrpCd eq '지급쿠폰'}">
												<a href="javascript:;" data-sn="${data.cupMetaSn}" class="btn btn_gray" onclick="modalUtil.open('user-list', this);">${data.lgrpCd}</a>
											</c:when>
											<c:otherwise><span>${data.lgrpCd}</span></c:otherwise>
										</c:choose>
									</td>
									<td>${data.useYn eq 'Y' ? '발급중' : '발급중지'}</td>
									<td>
										<a href="${contextPath}/shop/coupon/info/usable/list.vc?cupMetaSn=${data.cupMetaSn}">${data.usable}</a>
									</td>
									<td>${data.unusable}</td>
									<td>${data.rgstDtm}</td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/modify.vc?cupMetaSn=${data.cupMetaSn}${function:searchQuery(result.searchInfo)}">수정</a>
										<a href="${contextPath}/${requestUri}/delete.vc" class="btn btn_gray btnRowDelete"  data-sn="${data.cupMetaSn}">삭제</a>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
    
		    <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray btnInfoCancle" onclick="">선택취소</button>
					<button type="submit" class="btn btn_gray" onclick="">선택삭제</button>
				</div>
				<div class="right">
					<button type="button" onclick="location.href='${contextPath}/${requestUri}/register.vc';" class="btn btn_bk">쿠폰등록</button>
				</div>
			</div>
	    </form>
		    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
	    </div>
    </div>
</div>

<!-- 회원검색 -->
<div class="modal_window modal_wrap modal-user-list">
	<div class="modal_dialog">
		<div class="modal_content cont02">
			<div class="sec_top">
				<h3 class="sec_tit">회원검색</h3>
				<a href="javascript:;" class="close modal_cls_btn"><img src="/resources/admin/images/common/close_btn.png" alt=""></a>
				
				<form name="subSearchForm" method="post" onsubmit="var rtn = formModalSubmitObj.submitSearch(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<input type="hidden" name="page" value="1" />
					<input type="hidden" name="rowLimit" value="5" />
					<div class="check_box" id="text_box_group">
						<select id="searchType" name="searchType">
							<option value="">전체</option>
							<option value="id">아이디</option>
							<option value="name">회원명</option>
							<option value="phone">휴대폰</option>
						</select>
						<input type="text" id="searchKey" name="searchKey" title="검색어입력" style="width:450px;" maxlength="100" minlength="2"/>
						<button type="submit">조회</button>
					</div>
				</form>
				
				<form name="subRegiForm" method="post" action="${contextPath}/shop/coupon/member/register.vc" onsubmit="var rtn = formModalSubmitObj.submitCouponReg(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<input type="hidden" name="cupMetaSn" value="0" />
					<div class="content-body">
						<div class="table_tit">
							<div class="table_left_title">
								<span>총 <strong class="totCnt" style=" font-weight: 700;">0</strong>건 조회</span>
							</div>
						</div>
	
						<table class="cont_table" id="tab" style="TABLE-layout:fixed">
							<colgroup>
								<col style="width:60px">
								<col style="width:22%">  
								<col style="width:22%">
								<col style="width:22%">
								<col style="width:22%">
							<thead>
								<tr>
									<th><input type="checkbox" class="checkbox-selectAll" id="all"></th>
									<th>아이디</th>
									<th>회원명</th>
									<th>휴대폰</th>
									<th>가입일</th>
								</tr>
							</thead>
							<tbody>
								<tr><td colspan="5" class="nodata">검색해 주세요.</td></tr>
	<!-- 							<tr> -->
	<!-- 								<td><input type="checkbox" name="check" class="check2" value="00000000000000001234" title="선택">	</td> -->
	<!-- 								<td>ksm174</td> -->
	<!-- 								<td>홍명진</td> -->
	<!-- 								<td>010-4155-****</td> -->
	<!-- 								<td>2019-05-28</td> -->
	<!-- 							</tr> -->
							</tbody>
						</table>
						
						<div class="paging_wrap modal-paging" style="padding:20px 0; text-align: center;"></div>
						
						<div class="btn_center_gorup clearfix">
							<div class="right">
								<button type="button" class="btn btn_bk btnSelectUser">선택</button>
							</div>
						</div>

						<div class="id_box">
							<ul><!-- 선택한 사용자 목록 --></ul>
						</div>
						<div class="btn_center_gorup">
							<button type="submit" class="btn btn_red">지급</button>
							<button type="button" class="btn btn_gray modal_cls_btn">취소</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="modal_backdrop"></div>

<script type="text/javascript">
$(function () {
	/*
	 * 모달 닫기
	 */
	$('.modal_cls_btn').on('click', function(event){
		event.preventDefault();  
		modalUtil.close();
	});
	
	$('.modal-paging a').live('click', function(event){
		event.preventDefault();  
		$('form[name=subSearchForm] input[name=page]').val($(this).attr('href'));
		$('form[name=subSearchForm]').submit();
	});
	
	$('.btnSelectUser').on('click', function(event){
		event.preventDefault();  
	 	if($('form[name=subRegiForm] input.checkbox-select:checked').length > 0){
        	var htmlTxt = '';
        	var selectList = $('form[name=subRegiForm] input.checkbox-select:checked');
        	for(var i = 0; i < selectList.length; i++){
//	         		selectList[0].dataset.name;
//	         		selectList[0].value;
        		htmlTxt += ' <li>'
        		        +  ' <input type="hidden" name="delSeq" value="'+ selectList[0].value +'">'
    			    	+  ' <span>'+ selectList[i].dataset.name +'</span> <a href="#" class="btnRemoveUser"><img src="/resources/admin/images/common/can.png" alt=""></a>';
        			    +  ' </li>';
        	}
        	
        	$('.id_box > ul').append(htmlTxt);
        	
        	$('form[name=subRegiForm] input.checkbox-select, form[name=subRegiForm] input.checkbox-selectAll').prop('checked', false);
        	
        }else{
            alert('1개이상 선택해 주세요.');
        }
	});
	
	$('.btnRemoveUser').live('click', function(event){
		event.preventDefault();  
		$(this).closest('li').remove();
	});
	
	
    $('form[name=searchForm]').ready(function(){
    	$('form[name=searchForm] input[name=searchGubunType][value="${result.searchInfo.searchGubunType}"]').prop('checked', true).trigger('click'); 
    	$('form[name=searchForm] input[name=searchGubunType2][value="${result.searchInfo.searchGubunType2}"]').prop('checked', true).trigger('click'); 
    	$('form[name=searchForm] input[name=searchStartDate]').val('${result.searchInfo.searchStartDate}');
    	$('form[name=searchForm] input[name=searchEndDate]').val('${result.searchInfo.searchEndDate}');
        $('form[name=searchForm] input[name=searchKey]').val('${result.searchInfo.searchKey}');  
    });
});

//모달객체
var selectObject = null;
var modalUtil = {
	open : function(_id, object){
		selectObject = object;
		
		console.log('선택 쿠폰아이디 : ' + $(object).data('sn'));
		$('form[name=subRegiForm] input[name=cupMetaSn]').val($(object).data('sn'));
		ajaxUtil.postDisableAsync('${contextPath}/shop/coupon/member/list.vc', $('form[name=subSearchForm]').serialize(), formModalSubmitObj.resultSearch);
		$('body').addClass('modal_open');
		modalComUtil.open(_id);
	},    
	close : function(object){
		try{
			$('form[name^=sub]')[0].reset();
			$('.modal-user-list .totCnt').html(0);
			$('.modal-user-list table tbody').html('');
			$('.modal-user-list .modal-paging').html('');
			$('.id_box > ul').html('');
		}catch(e){
			//ignore
		}
		
		$('body').removeClass('modal_open');
		modalComUtil.close();
	}
};

var formSubmitObj = {
    submit : function(form){
        if(!submitUtil.isNull(form.searchKey)){
            if(!submitUtil.isMinLength(form.searchKey)){
                return false;    
            }        
        }  
        
        if(!submitUtil.isNull(form.searchStartDate) && !submitUtil.isNull(form.searchEndDate)){
    		if(!submitUtil.isDateCompare(form.searchStartDate, form.searchEndDate)){
            	return false;
    		}
        }
        
        return true;
    },
    submitDel : function(object){
		var msg = '게시물을 삭제 하시겠습니까?'
	    if(confirm(msg)){
	        var data = { cupMetaSn : $(object).data('sn') };
	        ajaxUtil.postDisableAsync($(object).attr('href'), data, formSubmitObj.delResult);    
	    }
	},
	delSubmit : function(form){
        if($('input.checkbox-select:checked').length > 0){
        	if (confirm('선택한 게시물을 삭제 하시겠습니까?')) {
            	ajaxUtil.postDisableAsync(form.action, $(form).serialize(), formSubmitObj.delResult);
        	}
        }else{
            alert('1개이상 선택해 주세요.');
        }
        
        return false;
    },
    delResult : function(json){
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


var formModalSubmitObj = {
	submitSearch : function(form){
		ajaxUtil.postDisableAsync('${contextPath}/shop/coupon/member/list.vc', $(form).serialize(), formModalSubmitObj.resultSearch);
        return false;
	},
	resultSearch : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined){
			var data = json.result.list;
			var htmlTxt = '';
			
			if(data && data.length > 0){
				for(var i = 0; i < data.length; i++)
				{
					htmlTxt += ' <tr>'
						    + ' 	<td>'
						    + ' 		<input type="checkbox" class="check2 checkbox-select" data-name="' + data[i].mbrNm +'" value="'+ data[i].mbrSn +'" title="선택">'
						    + ' 	</td>'
						    + ' 	<td>' + data[i].mbrId + '</td>'
						    + ' 	<td>' + data[i].mbrNm + '</td>'
						    + ' 	<td>' + data[i].mbrHp + '</td>'
						    + ' 	<td>' + data[i].rgstDtm + '</td>'
						    + ' </tr>';
				}
			}else{
				htmlTxt = '<tr><td colspan="5" class="nodata">검색 데이터가 없습니다.</td></tr>';
			}
			
			
			ajaxPagging.create('', 'modal-paging', json.result.searchInfo.totalRow,  json.result.searchInfo.page, json.result.searchInfo.rowLimit, json.result.searchInfo.pageLimit);
			
			$('.modal-user-list .totCnt').html(json.result.searchInfo.totalRow);
			$('.modal-user-list table tbody').html(htmlTxt);
        }else{
            ajaxUtil.error(json);
        }
    },
    submitCouponReg : function(form){
    	if($('form[name=subRegiForm] input[name=delSeq]').length > 0){
        	if (confirm('쿠폰을 지급 하시겠습니까?')) {
            	ajaxUtil.postDisableAsync(form.action, $(form).serialize(), formModalSubmitObj.resultCouponReg);
        	}
        }else{
            alert('회원을 선택해 주세요.');
        }
    	
    	return false;
    },
    resultCouponReg : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
        	alert('쿠폰지급이 완료 되었습니다.');
            location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : memberDet.jsp
    Description : 회원  상세조회
--%>
<div class="sec_top">
	<h3 class="sec_tit">고객 정보 수정</h3>
	<ul class="top_tab">
		<li><a href="#">고객 관리</a></li>
	</ul>
</div>
	
	
<div class="sec_cont">
	<h4 class="cont_tit">회원 정보</h4>
	<div class="r_search_box">
    
    	<form name="mainForm" method="post" action="${contextPath}/${requestUri}/modify.vc" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
        	<input type="hidden" name="mbrSn" value="${result.info.mbrSn}" />
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="190px" /><col width="*" /><col width="190px" /><col width="*" />
	            </colgroup>
	            <tr>
	            	<th>회원등급</th>
	                <td colspan="3">
	                	<select id="mbrGrade" name="mbrGrade">
			            	<option value="NORMAL">NORMAL</option>
			            	<option value="BRONZE">BRONZE</option>
			            	<option value="SILVER">SILVER</option>
			            	<option value="GOLD">GOLD</option>
			            </select>
	                </td>  
	            </tr>
	            
	            <tr>    
	            	<th>아이디</th>
	                <td colspan="3">${result.info.mbrId} <c:if test="${result.info.stCd eq 'D'}">( 탈퇴 : ${result.info.mdftDtm} )</c:if></td>  
	            </tr>
	            <tr>
	                <th>닉네임</th>
	                <td colspan="3">${result.info.mbrNick}</td>    
	            </tr>
	            <tr>    
	            	<th>가입경로</th>
	                <td colspan="3">${result.info.snsCd}</td>  
	            </tr>
	            <c:if test="${result.info.snsCd eq 'HOME' and result.info.stCd ne 'D'}">
		            <tr>
						<th>비밀번호</th>
						<td colspan="3">
							<a href="${contextPath}/${requestUri}/resetPassword.vc" class="btnstyle blue btn_resetPw">비밀번호 초기화</a>
						</td>
					</tr>
				</c:if>
				
	            <tr>    
	                <th class="necessary">성명</th>
	                <td colspan="3"><input type="text" name="mbrNm" maxlength="100" style="width:150px;" title="성명" value="${result.info.mbrNm}"></td>    
	            </tr>
	            
	            <c:if test="${result.info.stCd ne 'D'}">
		            <tr> 
		            	<th class="necessary">이메일</th>
		                <td colspan="3"><input type="text" name="mbrEml" maxlength="100" style="width:345px;" title="이메일" value="${result.info.mbrEml}"></td>    
		            </tr>
		            <tr> 
		            	<th>생년월일(나이) / 성별</th>
		                <td colspan="3">${result.info.mbrBday} ( ${result.info.mbrAge}세 ) / ${result.info.genderCd}</td>    
		            </tr>
		            <tr>    
		                <th>전화번호</th>
		                <td colspan="3">
		                	<input type="text" class="telNumOnly" name="mbrTn" maxlength="13" style="width:345px;" value="${result.info.mbrTn}" title="전화번호" />
		                </td> 
		            </tr>
		            <tr>    
		                <th class="necessary">휴대폰번호</th>
		                <td colspan="3">
		                	<input type="text" class="telNumOnly" name="mbrHp" maxlength="13" style="width:345px;" value="${result.info.mbrHp}" title="휴대폰번호" />
		                </td> 
		            </tr>
		            <tr> 
		                <th>주소</th>
		                <td colspan="3">
		                	<div id="zipcodeWrap" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
								<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
								<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
							</div>
							<div class="add">
								<div>
									<input type="text" id="zipCd" name="zipCd" maxlength="8" style="width:170px;" title="우편번호" placeholder="우편번호" value="${result.info.zipCd}" readonly="readonly"/>
									<a href="#" id="btnZipCode" class="btn02 btn_pp add_search"><span>우편번호검색</span></a>
								</div>
								<div>
									<input type="text" id="adrSbc1" name="adrSbc1" style="width:80%;" title="주소1" readonly="readonly" value="${result.info.adrSbc1}" />
								</div>
								<div>
									<input type="text" id="adrSbc2" name="adrSbc2" class="gray" maxlength="100" style="width:80%;" title="상세주소" value="${result.info.adrSbc2}" placeholder="상세주소를 입력해주세요"/>
								</div>
							</div>
		                </td>
		            </tr>
		            <tr>    
		                <th>메일 및 SMS 수신동의</th>
		                <td colspan="3">
							<input type="hidden" id="smsYn" name="smsYn" value="${result.info.smsYn}">
							<input type="hidden" id="emlYn" name="emlYn" value="${result.info.emlYn}">
							<input type="checkbox" id="agreed" name="agreed" class="check2 checkbox-select">
		                	<label class="input_checkbox" for="agreed">이메일 및 SMS 알림 수신동의</label>
		                </td>
		            </tr>
		            <tr>    
		                <th>커뮤니티 접근여부</th>
		                <td colspan="3">
		                	<label class="radio_box" for="bbsY"><input type="radio" name="bbsYn" id="bbsY" value="Y" /><span>허용</span></label>&nbsp;&nbsp;
		                    <label class="radio_box" for="bbsN"><input type="radio" name="bbsYn" id="bbsN" value="N" /><span>금지</span></label>
		                </td>
		            </tr>
		            <tr>    
		                <th>구매횟수</th>
		                <td colspan="3">0 회 ( 총 금액 : 0원)</td>
		            </tr>
	            </c:if>
	            <tr>    
	                <th>가입일</th>
	                <td colspan="3">${result.info.rgstDtm}</td>
	            </tr>
	            <tr>    
	                <th>MEMO</th>
	                <td colspan="3">
	                	<textarea name="rmk" id="rmk" style="width:95%; height:200px;" title="메모">${result.info.rmk}</textarea>    
	               	</td> 
	            </tr>
	        </table>
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
				</div>
				<c:if test="${result.info.stCd ne 'D'}">
					<div class="right">
						<button type="submit" class="btn btn_red">수정</button>
					</div>
				</c:if>
			</div>
	    </form>
	</div>
</div>
	
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>	
<script type="text/javascript">
$(function () {
	$('form[name=mainForm]').ready(function(){
    	$('select[name=mbrGrade] option[value="${result.info.mbrGrade}"]').prop('selected', true);
    	$('input[name=agreed]').prop('checked', ${result.info.smsYn eq 'Y' and result.info.emlYn eq 'Y'});
    	$('input[name=bbsYn][value=${result.info.bbsYn}]').prop('checked', true).trigger('click');
    });
	
	/**
	 * 비밀번호 초기화
	 */
	$('.btn_resetPw').on({
	    click : function(event){
	        event.preventDefault();
	        formSubmitObj.submitResetPw($(this));
	    } 
	});
	
	$('a#btnZipCode, input[name=zipCd]').on({
        'click' : function(event){
            event.preventDefault();  
            execDaumPostcode();
        },
        'focus' : function(event){
            event.preventDefault();  
            execDaumPostcode();
        }
    });
});

//우편번호 찾기 찾기 화면을 넣을 element
var zipcodeWrap = document.getElementById('zipcodeWrap');
function closeDaumPostcode() {
    zipcodeWrap.style.display = 'none';
}

function initLayerPosition(){
    var width = 500; //우편번호서비스가 들어갈 element의 width
    var height = 500; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 3; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    zipcodeWrap.style.width = width + 'px';
    zipcodeWrap.style.height = height + 'px';
    zipcodeWrap.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    zipcodeWrap.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    zipcodeWrap.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
}

function execDaumPostcode() {
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                addr += extraAddr;
            }

            document.forms.mainForm.zipCd.value = data.zonecode;
            document.forms.mainForm.adrSbc1.value = addr;
            document.forms.mainForm.adrSbc2.focus();
            
            zipcodeWrap.style.display = 'none';

            document.body.scrollTop = currentScroll;
        },
        onresize : function(size) {
	        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            zipcodeWrap.style.height = '500px';
        },
        width : '100%',
        height : '500px',
        maxSuggestItems : 5
    }).embed(zipcodeWrap);

    // iframe을 넣은 element를 보이게 한다.
    zipcodeWrap.style.display = 'block';
    
 	// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();
}

var formSubmitObj = {
	submitResetPw : function(object){
        if(confirm('사용자의 비밀번호를 초기화 하시겠습니까?')){
            var data = { mbrSn : '${result.info.mbrSn}' };
            ajaxUtil.postDisableAsync($(object).attr('href'), data, formSubmitObj.resultResetPw);
        } 
    },
    resultResetPw : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if( json.result != undefined && json.result.status){
            alert('비밀번호가 초기화 되었습니다.\n사용자에게 메일이 발송 되었습니다.');
        }else{
            ajaxUtil.error(json);     
        }
    },
	submit : function(form){
    	
		if(!submitUtil.isEmpty(form.mbrNm)){
            return false;
        }
		
    	if(!submitUtil.isEmpty(form.mbrHp)){
            return false;
        }
    	
    	if(!submitUtil.isEmail(form.mbrEml, '메일 형식 확인')){
            return false;
        	}
    	
    	if(!submitUtil.isEmpty(form.zipCd)){
            return false;
        }
           
        if(!submitUtil.isEmpty(form.adrSbc1)){
            return false;
        }
        
        if($("input[name=agreed]").is(":checked")){
        	form.smsYn.value = 'Y';
        	form.emlYn.value = 'Y';
        }else{
        	form.smsYn.value = 'N';
        	form.emlYn.value = 'N';
        }
           
    	if(confirm('회원정보를 변경 하시겠습니까?')){
        	ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
    	}
         
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
            alert('회원정보가 변경 되었습니다.');
            location.reload(true);
        }else{
			ajaxUtil.error(json);	
        }
    },
}

</script>

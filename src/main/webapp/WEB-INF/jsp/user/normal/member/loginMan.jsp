<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : loginMan.jsp
    Description : 회원 > 로그인
--%>
<link rel="stylesheet" href="/resources/user/css/order.css?v=${cacheParam}">
<link rel="stylesheet" href="/resources/user/css/menber.css?v=${cacheParam}">

<div id="container">
	<div class="inquiry login_main">
		<h3 class="p_tit p_tit02">로그인</h3>
		
		<div class="tab_ui col_2">
	        <div class="tab">
	           <ul class="clearfix inner_1">
	              <li class="active" ><a href="javascript:;">회원 로그인</a></li>
	              <li class=""><a href="javascript:;">비회원 주문조회</a></li>
	           </ul>
	        </div>
            <div class="tab_cont">
            	<ul>
                     <li class="active">
                        <div class="inner_1 inner_2">	
                        	<form name="mainForm" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
							<input type="hidden" name="mbrPwEnc" value="">							
								<div class="log_form">
									<input type="text" name="mbrId" class="idput engMode noSpace"  title="아이디" placeholder="아이디를 입력해주세요"/>
									<input type="password" name="mbrPw" class="pwdput engMode noSpace submit" title="비밀번호" value="" autocomplete="off" placeholder="패스워드를 입력해주세요"/>
								</div>
								<ul class="log_find clearfix">
									<li><a href="/registration/agree.vc">회원가입</a></li>
									<li><em>&middot;</em></li>	
									<li><a href="/help/idInquiry.vc">아이디찾기</a></li>
									<li><em>&middot;</em></li>
									<li><a href="/help/pwInquiry.vc">비밀번호찾기</a></li>
								</ul>
								<a href="#" class="btn btn_pp log_btn submit"><span>로그인</span></a>
								<ul class="log_option">
									<li><a id="custom-login-btn" href="javascript:loginWithKakao();"><img src="/resources/user/images/order/kakao_log.png?v=${cacheParam}" alt="카카오톡 로그인"><p>카카오톡 로그인</p></a></li>
									<li><a id="naverIdLogin_loginButton"><img src="/resources/user/images/order/naver_log.png?v=${cacheParam}" alt="네이버 로그인"><p>네이버 로그인</p></a></li>
									<li><a id="custom-login-btn-google"><img src="/resources/user/images/order/google_log.png?v=${cacheParam}" alt="구글 로그인"><p>구글 로그인</p></a></li>
									<li><a id="custom-login-btn-apple"><img src="/resources/user/images/order/apple_log.png?v=${cacheParam}" alt="Apple 로그인"><p>Apple 로그인</p></a></li>					
								</ul>
							</form>
						</div>
                     </li>
                     <li class="">
						<div class="inner_1">
							<form name="subForm" method="post" action="/shop/guest/order/inquiry.vc" onsubmit="var rtn = formSubmitObj.submitOrderSearch(this); if(!rtn){ submitUtil.enable(); } return rtn;">
								<div class="log_form">
									<input type="text" name="buyerNm" class="noSpace" placeholder="주문자 이름을 입력해주세요">
									<input type="text" name="orderNo" class="numOnly noSpace" maxlength="30" placeholder="주문번호를 입력해주세요">
								</div>
								<a href="#" class="btn btn_pp log_btn submit"><span>비회원 주문조회</span></a>
							</form>
						</div>
					 </li>
            	</ul>
        	</div>                  
        </div>
            
	</div>		
</div>

<script type="text/javascript" src="https://apis.google.com/js/api:client.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="https://appleid.cdn-apple.com/appleauth/static/jsapi/appleid/1/ko_KR/appleid.auth.js"></script>
<script type="text/javascript">
/*
 * 카카오 로그인
 */
function loginWithKakao() {
  Kakao.Auth.login({
    success: function(authObj) {
      //사용자 정보
      Kakao.API.request({
         url: '/v2/user/me',
         success: function(res) {
        	 callbackSns("KAKAO", res.id, res.kakao_account.email );
         },
         fail: function(error) {
        	 console.log('login success, but failed to request user information: ' + JSON.stringify(error));
         }
       });
    },
    fail: function(err) {
    	console.log(JSON.stringify(err));
    },
  })
}

/*
 * 카카오연동 로그아웃
 */
function logoutWithKakao() {
	Kakao.API.request({
		url: '/v1/user/unlink',
		success: function(response) {
			console.log(response);
		},
		fail: function(error) {
			console.log(error);
		},
	});
}

/*
 * google 로그인 callback
 * 	- googleUser.getBasicProfile().getName();
 */
function loginWithGoogle(element) {
    auth2.attachClickHandler(element, {},
        function(googleUser) {
    		callbackSns("GOOGLE", googleUser.getBasicProfile().getId(), googleUser.getBasicProfile().getEmail());
        }, function(error) {
        	console.log('Google 로그인 Callback Error : ' + JSON.stringify(error, undefined, 2));
    	}
    );
}
 

/*
 * apple 로그인 버튼 이벤트 등록하기
 */
function loginWithApple(element) {
/*	
	{
	   "state": "xxx",
	   "code": "yyy",
	   "id_token": "zzz",
	   "user": {
	        "name": {
	            "firstName":"John",
	            "lastName":"Doe"
	        },
	        "email":"example@privaterelay.appleid.com"
	    }
	}
*/
	element.addEventListener('click', function(){
		const data = AppleID.auth.signIn().then(function(response){
			console.log("response", JSON.stringify(response));
			var payload = parseJwt(response.authorization.id_token);
			callbackSns("APPLE", payload.sub, payload.email ? payload.email : '');
		}, function(err){
			if(err.error != 'popup_closed_by_user'){
				console.log("response", err);
				alert(err.error);
			}
		});
		
    });
}

function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};

function random_bytes(n) {
    var crypto = (self.crypto || self.msCrypto), QUOTA = 65536;
        var a = new Uint8Array(n);
        for (var i = 0; i < n; i += QUOTA) {
            crypto.getRandomValues(a.subarray(i, i + Math.min(n - i, QUOTA)));
        }
        return a.toString();
}

function Hex2Bin(hex){
    var bytes = [];
    var str;

    for(var i=0; i< hex.length-1; i+=2){
        bytes.push(parseInt(hex.substr(i, 2), 16));
    }

    str = String.fromCharCode.apply(String, bytes);

    return(str);
} 

/*
 * SNS 연동 초기화
 * 	- naver : https://developers.naver.com/docs/login/web
 */
function initSNSLogin(){
	//naver
	var naverLogin = new naver.LoginWithNaverId({
			clientId: "<spring:eval expression="@global['sns.naver.clientId']" />",
			callbackUrl: "<spring:eval expression="@global['sns.naver.callbackUrl']" />",
			isPopup: true /* 팝업을 통한 연동처리 여부 */
	});
	naverLogin.init();
	   
	//kakao
	Kakao.init('<spring:eval expression="@global['sns.kakao.appkey.javascript']" />');
	Kakao.isInitialized();
	
	//google
	gapi.load('auth2', function(){
	    auth2 = gapi.auth2.init({
	      client_id: "<spring:eval expression="@global['sns.google.clientId']" />",
	      cookiepolicy: 'single_host_origin',
	      scope: 'email'
	    });
	    loginWithGoogle(document.getElementById('custom-login-btn-google'));
    });
	
	const appleState = Hex2Bin(random_bytes(5));
	AppleID.auth.init({
        clientId : "<spring:eval expression="@global['sns.apple.clientId']" />",
        scope : 'email',
        redirectURI: "<spring:eval expression="@global['sns.apple.callbackUrl']" />",
        state : 'vcl',
        usePopup : true
    });
	loginWithApple(document.getElementById('custom-login-btn-apple'));
	
}

$(document).ready(function(){

	initSNSLogin();

	/*
     * ESC선택시 모달 닫기
     */
//     $(document).keydown(function(event){
//         if(event.keyCode == 27){
//             modalUtil.close();
//         }
//     });
});
	
var formSubmitObj = {
    submit : function(form){
    	if(!submitUtil.isEmpty(form.mbrId)){
	    	return false;
		}
		
       	if(!submitUtil.isEmpty(form.mbrPw)){
            return false;
        }
       	else {
       		form.mbrPwEnc.value = Base64.encode(form.mbrPw.value);
       	}

        ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.code == 'OK'){
        	if(json.result.returnUrl == ''){
                location.replace(json.result.firstMenuUrl);
            }else{
                location.replace(json.result.returnUrl);
            }
        }else{
        	ajaxUtil.error(json);
        	
        	if(json.result.code == 'E_WRONG_LOCK'){
         	   	location.href = "/help/pwInquiry.vc";
            }else if(json.result.code == 'E_WRONG_CERT'){
        	   	location.href = "/certification/agree.vc";
	        }else{
	           	$('form[name=mainForm] input[type=password]').eq(0).val('').focus();
	        }
        }
    },
    resultSns : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.code == 'OK'){
        	if(json.result.returnUrl == ''){
                location.replace(json.result.firstMenuUrl);
            }else{
                location.replace(json.result.returnUrl);
            }
        }else{
       		ajaxUtil.error(json);
       		
           	if(json.result.code == 'E_WRONG_ID'){
        	   	location.href = "/registration/agree.vc";
           	}else if(json.result.code == 'E_WRONG_CERT'){
           	   	location.href = "/certification/agree.vc";
           	}
        }
    },
    submitOrderSearch : function(form){
    	if(!submitUtil.isEmpty(form.buyerNm, '주문자 이름을 입력해주세요')){
	    	return false;
		}
		
       	if(!submitUtil.isEmpty(form.orderNo, '주문번호를 입력해주세요')){
            return false;
        }

        return true;
    },
};

/*
 * SNS Callback 함수
 * @param snsCd : KAKAO, NAVER, APPLE, GOOGLE
 */
function callbackSns(snsCd, mbrId, mbrEml){
	console.log('callbackSns = ' + snsCd + ' : ' + mbrId + ' : ' + mbrEml);
	
	if(mbrEml == undefined){
		mbrEml = '';
	}
	
	var data = {
		 'snsCd' : snsCd
		,'mbrId' : mbrId
		,'mbrEml' : mbrEml
	}
	
	//로그인 페이지로 전달 해서 사용자 정보가 없으면 회원가입페이지로 이동. 기타 정보는 세션에 담을것.
	ajaxUtil.postDisableAsync('/member/login.vc', data, formSubmitObj.resultSns);
}
</script>
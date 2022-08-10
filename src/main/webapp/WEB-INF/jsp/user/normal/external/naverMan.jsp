<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    Description : 네이버 로그인 Callback 페이지
--%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId({
			clientId: "<spring:eval expression="@global['sns.naver.clientId']" />",
			callbackUrl: "<spring:eval expression="@global['sns.naver.callbackUrl']" />",
			isPopup: true,
			callbackHandle: true
	});
		
	naverLogin.init();
	
	window.addEventListener('load', function () {
		naverLogin.getLoginStatus(function (status) {
			if (status) {
				/* 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
				var uniqId = naverLogin.user.getId();
				var name = naverLogin.user.getName();
// 				var nickName = naverLogin.user.getNickName();
				var email = naverLogin.user.getEmail();
				if( email == undefined || email == null) {
					alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
					/* 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
					naverLogin.reprompt();
					return;
				}

			
				var opener = null;
				if (window.opener) {
					opener = window.opener;
				} else {
					opener = window.open('', 'opener');
				}

				/*
				var redirect = "https://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "/oauth/sample/javascript_sample.html";
				if (opener) {
					opener.location.replace(redirect + location.hash);
					opener.location.reload();
					window.close();
				} else {
					window.location.replace(redirect);
				}
*/

				opener.callbackSns("NAVER", uniqId, email);
			    windowUtil.close();
			} else {
				alert('네이버 연동 로그인이 실패했습니다. 다시 시도해 주세요.');
				windowUtil.close()
			}
		});
	});
</script>
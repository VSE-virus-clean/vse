<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : completeMan.jsp
    Description : 회원가입 > 신청완료
--%>
<link rel="stylesheet" href="/resources/user/css/menber.css?v=${cacheParam}">
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li class="active"><a href="#">회원가입</a></li>
		</ul>
		<div class="mo inner">
			<a href=""></a>
		</div>			
	</div>
	<div class="join_ok">
		<div class="inner">
			<h3 class="p_tit">회원가입</h3>
			<div class="step">
				<ul>
					<li><p><span class="num"><em>01</em></span> <span>약관동의</span></p></li>
					<li><p><span class="num"><em>02</em></span> <span>회원정보 입력</span></p></li>
					<li class="active"><p><span class="num"><em>03</em></span> <span>가입완료</span></p></li>
				</ul>
			</div>
			<div class="join">
				<h4 class="cont_tit02"><span>Step 03</span>가입완료</h4>
				<div class="line_box">
					<div class="line_box_inner">
						<img src="/resources/user/images/menber/joinok.png" alt="">
						<h3>회원가입이 <strong>완료</strong> 되었습니다.</h3>
						<p>사이트에 회원가입이 정상적으로 처리되었습니다.</p>
					</div>						
				</div>
				<div class="btn_wrap">
					<a href="/index.vc" class="btn btn_pp" style="width: 330px;"><span>메인으로</span></a>
					<a href="/member/login.vc" class="btn btn_line" style="width: 330px;"><span>로그인</span></a>
				</div>
			</div>
		</div>						
	</div>		
</div>

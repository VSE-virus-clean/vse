<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : communityList.jsp
    Description : 커뮤니티 목록
--%>
<link rel="stylesheet" href="/resources/user/css/mypage.css?v=${cacheParam}">

<div id="container">
	
	<div class="order order_main_page mp_prd_page">
		<div class="inner">
		
			<%@ include file="/WEB-INF/jsp/user/normal/member/inc_mypageTop.jsp" %>
			
			<div class="step">
				<ul>
					<li><p><span class="num"><em>01</em></span> <span>제품 및 구입처 확인</span></p></li>
					<li class="active"><p><span class="num"><em>02</em></span> <span>설문조사</span></p></li>
					<li><p><span class="num"><em>03</em></span> <span>정품등록 완료</span></p></li>
				</ul>
			</div>

			<div class="mp_prd_wrap">
				<div class="mp_prd_wrap03 active">
					<p class="prd03_tit">안녕하세요. 휴대용 듀얼 바이러스 살균기 PSZ의 정품등록 설문조사에 응해주셔서 진심으로 감사드립니다.<br />본 설문에 응답하신 내용은 조사 목적을 위한 통계자료로만 사용될 뿐, 그 외의 목적으로 사용되는 일은 없을 것을 약속 드립니다. <br />본 설문에 솔직한 답변 부탁드립니다. 감사합니다.</p>
					
					<div class="prd03_tbl_tit">
						<strong>Q1</strong>
						<span>여러분에 대해서 알려주세요</span>
					</div>
					<div class="table-type01 prd03_tbl01">
						<table>
							<colgroup>
								<col style="width:20%">
								<col style="width:80%">
							</colgroup>
							<tbody>
								<tr>
									<th>성별</th>
									<td class="pay_radio clearfix">
										<label class="input_radio" for="gender_01">
											<input id="gender_01" checked="" name="gender" type="radio">
											<span>남</span>
										</label>
										<label class="input_radio" for="gender_02">
											<input id="gender_02" name="gender" type="radio">
											<span>여</span>
										</label>
									</td>
								</tr>
								<tr>
									<th>연령대</th>
									<td class="pay_radio clearfix">
										<label class="input_radio" for="age_01">
											<input id="age_01" checked="" name="age" type="radio">
											<span>10대 이하</span>
										</label>
										<label class="input_radio" for="age_02">
											<input id="age_02" name="age" type="radio">
											<span>20대</span>
										</label>
										<label class="input_radio" for="age_03">
											<input id="age_03" name="age" type="radio">
											<span>30대</span>
										</label>
										<label class="input_radio" for="age_04">
											<input id="age_04" name="age" type="radio">
											<span>40대</span>
										</label>
										<label class="input_radio" for="age_05">
											<input id="age_05" name="age" type="radio">
											<span>50대</span>
										</label>
										<label class="input_radio" for="age_06">
											<input id="age_06" name="age" type="radio">
											<span>60대</span>
										</label>
										<label class="input_radio" for="age_07">
											<input id="age_07" name="age" type="radio">
											<span>70대 이상</span>
										</label>
									</td>
								</tr>
								<tr>
									<th>거주지</th>
									<td class="pay_radio clearfix">
										<label class="input_radio" for="house_01">
											<input id="house_01" checked="" name="house" type="radio">
											<span>서울</span>
										</label>
										<label class="input_radio" for="house_02">
											<input id="house_02" name="house" type="radio">
											<span>인천</span>
										</label>
										<label class="input_radio" for="house_03">
											<input id="house_03" name="house" type="radio">
											<span>대구</span>
										</label>
										<label class="input_radio" for="house_04">
											<input id="house_04" name="house" type="radio">
											<span>광주</span>
										</label>
										<label class="input_radio" for="house_05">
											<input id="house_05" name="house" type="radio">
											<span>울산</span>
										</label>
										<label class="input_radio" for="house_06">
											<input id="house_06" checked="" name="house" type="radio">
											<span>부산</span>
										</label>
										<label class="input_radio" for="house_07">
											<input id="house_07" name="house" type="radio">
											<span>세종</span>
										</label>
										<label class="input_radio" for="house_08">
											<input id="house_08" name="house" type="radio">
											<span>강원</span>
										</label>
										<label class="input_radio" for="house_09">
											<input id="house_09" name="house" type="radio">
											<span>충북</span>
										</label>
										<label class="input_radio" for="house_10">
											<input id="house_10" name="house" type="radio">
											<span>충남</span>
										</label>
										<label class="input_radio" for="house_11">
											<input id="house_11" checked="" name="house" type="radio">
											<span>전북</span>
										</label>
										<label class="input_radio" for="house_12">
											<input id="house_12" name="house" type="radio">
											<span>전남</span>
										</label>
										<label class="input_radio" for="house_13">
											<input id="house_13" name="house" type="radio">
											<span>경북</span>
										</label>
										<label class="input_radio" for="house_14">
											<input id="house_14" name="house" type="radio">
											<span>경남</span>
										</label>
										<label class="input_radio" for="house_15">
											<input id="house_15" name="house" type="radio">
											<span>제주</span>
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q2</strong>
						<span>'바이러스살균기' 혹은 '공기살균기'에 대하여 알고 계셨나요?</span>
					</div>
					<div class="table-type01 prd03_tbl02">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix">
										<label class="input_radio" for="recog_01">
											<input id="recog_01" checked="" name="recog" type="radio">
											<span>알고있었다.</span>
										</label>
										<label class="input_radio" for="recog_02">
											<input id="recog_02" name="recog" type="radio">
											<span>몰랐다.</span>
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q3</strong>
						<span>다른 바이러스살균기를 사용해 본 적이 있으십니까?</span>
					</div>
					<div class="table-type01 prd03_tbl03">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio cntr clearfix">
										<label class="input_radio" for="ever_01">
											<input id="ever_01" checked="" name="ever" type="radio">
											<span>있다.</span><span>(4번답변 후 5번으로)</span>
										</label>
										<label class="input_radio" for="ever_02" class="ever_02">
											<input id="ever_02" name="ever" type="radio">
											<span>몰랐다.</span><span>(4번답변 후 5번으로)</span>
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q4</strong>
						<span><b class="txt_orng">(사용해 본 적이 있다면)</b> 알고 계시는 바이러스살균기 브랜드를 기재하여 주십시오.</span>
					</div>
					<div class="table-type01 prd03_tbl04">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix">
										<input type="text" placeholder="브랜드를 기재하여 주십시오." class="gray">
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q5</strong>
						<span>PSZ를 어떻게 알게 되셨나요?</span>
					</div>
					<div class="table-type01 prd03_tbl05">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix txt_left">
										<div class="inner_8">
											<label class="input_radio" for="know_how_01">
												<input id="know_how_01" checked="" name="know_how" type="radio">
												<span>지인</span>
											</label>
											<label class="input_radio" for="know_how_02">
												<input id="know_how_02" name="know_how" type="radio">
												<span>온라인배너광고</span>
											</label>
											<label class="input_radio" for="know_how_03">
												<input id="know_how_03" checked="" name="know_how" type="radio">
												<span>SNS(광고 포함)</span>
											</label>
											<label class="input_radio" for="know_how_04">
												<input id="know_how_04" name="know_how" type="radio">
												<span>포털사이트</span>
											</label>
											<label class="input_radio" for="know_how_05">
												<input id="know_how_05" checked="" name="know_how" type="radio">
												<span>블로그</span>
											</label>
											<label class="input_radio " for="know_how_06 ">
												<input id="know_how_06" name="know_how" type="radio">
												<span>IPTV광고</span>
											</label>
											<label class="input_radio long_label01" for="know_how_07">
												<input id="know_how_07" checked="" name="know_how" type="radio">
												<span>옥외광고(버스, 택시, 버스정류장쉘터 포함)</span>
											</label>
											<label class="input_radio long_label02" for="know_how_08">
												<input id="know_how_08" name="know_how" type="radio">
												<span>기타</span>
												<input type="text" placeholder="" class="gray">
											</label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q6</strong>
						<span>PSZ를 어디서 구매하셨나요?</span>
					</div>
					<div class="table-type01 prd03_tbl06">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio cntr clearfix">
										<label class="input_radio" for="where_buy_01">
											<input id="where_buy_01" checked="" name="where_buy" type="radio">
											<span>공식홈페이지</span>
										</label>
										<label class="input_radio" for="where_buy_02">
											<input id="where_buy_02" name="where_buy" type="radio">
											<span>네이버 스토어팜</span>
										</label>
										<label class="input_radio" for="where_buy_03">
											<input id="where_buy_03" checked="" name="where_buy" type="radio">
											<span>11번가</span>
										</label>
										<label class="input_radio" for="where_buy_04">
											<input id="where_buy_04" name="where_buy" type="radio">
											<span>G마켓</span>
										</label>
										<label class="input_radio" for="where_buy_05">
											<input id="where_buy_05" checked="" name="where_buy" type="radio">
											<span>쿠팡</span>
										</label>
										<label class="input_radio long_label03" for="where_buy_06">
											<input id="where_buy_06" name="where_buy" type="radio">
											<span>기타</span>
											<input type="text" placeholder="" class="gray">
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q7</strong>
						<span>PSZ의 구매 목적이 무엇인가요?</span>
					</div>
					<div class="table-type01 prd03_tbl07">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio cntr clearfix">
										<label class="input_radio" for="why_buy_01">
											<input id="why_buy_01" checked="" name="why_buy" type="radio">
											<span>개인 사용</span>
										</label>
										<label class="input_radio" for="why_buy_02">
											<input id="why_buy_02" name="why_buy" type="radio">
											<span>가족 선물용</span>
										</label>
										<label class="input_radio" for="why_buy_03">
											<input id="why_buy_03" checked="" name="why_buy" type="radio">
											<span>지인 선물용</span>
										</label>
										<label class="input_radio" for="why_buy_04">
											<input id="why_buy_04" name="why_buy" type="radio">
											<span>아기 외출용</span>
										</label>
										<label class="input_radio" for="why_buy_05">
											<input id="why_buy_05" checked="" name="why_buy" type="radio">
											<span>차량용</span>
										</label>
										<label class="input_radio long_label03" for="why_buy_06">
											<input id="why_buy_06" name="why_buy" type="radio">
											<span>기타</span>
											<input type="text" placeholder="" class="gray">
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q8</strong>
						<span>PSZ을 구매하신 가장 큰 이유 <b class="txt_orng">2가지만 선택</b>해주십시오.</span>
					</div>
					<div class="table-type01 prd03_tbl08 half_arrg">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix txt_left">
										<div class="inner_8">
											<label class="input_radio" for="top_why_01">
												<input id="top_why_01" checked="" name="top_why" type="radio">
												<span>바이러스 살균</span>
											</label>
											<label class="input_radio" for="top_why_02">
												<input id="top_why_02" name="top_why" type="radio">
												<span>App 연동</span>
											</label>
											<label class="input_radio" for="top_why_03">
												<input id="top_why_03" checked="" name="top_why" type="radio">
												<span>UVC 표면 살균</span>
											</label>
											<label class="input_radio" for="top_why_04">
												<input id="top_why_04" name="top_why" type="radio">
												<span>합리적인 가격</span>
											</label>
											<label class="input_radio" for="top_why_05">
												<input id="top_why_05" checked="" name="top_why" type="radio">
												<span>휴대용(작은 사이즈)</span>
											</label>
											<label class="input_radio long_label04" for="top_why_06">
												<input id="top_why_06" name="top_why" type="radio">
												<span>기타</span>
												<input type="text" placeholder="" class="gray">
											</label>
											<label class="input_radio" for="top_why_07">
												<input id="top_why_07" checked="" name="top_why" type="radio">
												<span>디자인</span>
											</label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q9</strong>
						<span>PSZ의 '바이러스 살균' 기능은 주로 어디서 사용하시나요 혹은 사용하실 예정이신가요? <b class="txt_orng">2가지만 선택</b>해주십시오.</span>
					</div>
					<div class="table-type01 prd03_tbl09 half_arrg">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix txt_left">
										<div class="inner_8">
											<label class="input_radio" for="where_use_01">
												<input id="where_use_01" checked="" name="where_use" type="radio">
												<span>사무실</span>
											</label>
											<label class="input_radio" for="where_use_02">
												<input id="where_use_02" name="where_use" type="radio">
												<span>자동차 안</span>
											</label>
											<label class="input_radio" for="where_use_03">
												<input id="where_use_03" checked="" name="where_use" type="radio">
												<span>집 안(침실, 거실 등)</span>
											</label>
											<label class="input_radio" for="where_use_04">
												<input id="where_use_04" name="where_use" type="radio">
												<span>외부 활동(휴대용)</span>
											</label>
											<label class="input_radio" for="where_use_05">
												<input id="where_use_05" checked="" name="where_use" type="radio">
												<span>아기 유모차</span>
											</label>
											<label class="input_radio long_label04" for="where_use_06">
												<input id="where_use_06" name="where_use" type="radio">
												<span>기타</span>
												<input type="text" placeholder="" class="gray">
											</label>
											<label class="input_radio" for="where_use_07">
												<input id="where_use_07" checked="" name="where_use" type="radio">
												<span>카페, 식당 등 외부 공간</span>
											</label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q10</strong>
						<span>PSZ의 'UVC 표면살균' 기능으로는 주로 어떻게 사용하시나요?</span>
					</div>
					<div class="table-type01 prd03_tbl10 half_arrg">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix txt_left">
										<div class="inner_8">
											<label class="input_radio" for="top_use_01">
												<input id="top_use_01" checked="" name="top_use" type="radio">
												<span>사무실 사무용품 살균(책상, 키보드, 마우스 등)</span>
											</label>
											<label class="input_radio" for="top_use_02">
												<input id="top_use_02" name="top_use" type="radio">
												<span>개인용품(휴대폰, 텀블러, 마스크, 이어폰)</span>
											</label>
											<label class="input_radio" for="top_use_03">
												<input id="top_use_03" checked="" name="top_use" type="radio">
												<span>집 안 각종 용품 살균(책상, 화장실, 현관 손잡이, 주방 등)</span>
											</label>
											<label class="input_radio" for="top_use_04">
												<input id="top_use_04" name="top_use" type="radio">
												<span>엘리베이터 버튼, 식당, 카페 등 외부 공간 살균</span>
											</label>
											<label class="input_radio" for="top_use_05">
												<input id="top_use_05" checked="" name="top_use" type="radio">
												<span>아기용품 살균(유모차, 우유병, 아기 장난감 등)</span>
											</label>
											<label class="input_radio long_label04" for="top_use_06">
												<input id="top_use_06" name="top_use" type="radio">
												<span>기타</span>
												<input type="text" placeholder="" class="gray">
											</label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q11</strong>
						<span>PSZ에서 가장 만족스러운 기능을 <b class="txt_orng">2가지만</b> 골라주십시오.</span>
					</div>
					<div class="table-type01 prd03_tbl11 half_arrg">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix txt_left">
										<div class="inner_8">
											<label class="input_radio" for="top_which_01">
												<input id="top_which_01" checked="" name="top_which" type="radio">
												<span>바이러스 살균(플라즈마)</span>
											</label>
											<label class="input_radio" for="top_which_02">
												<input id="top_which_02" name="top_which" type="radio">
												<span>디자인</span>
											</label>
											<label class="input_radio" for="top_which_03">
												<input id="top_which_03" checked="" name="top_which" type="radio">
												<span>UVC 표면 살균</span>
											</label>
											<label class="input_radio" for="top_which_04">
												<input id="top_which_04" name="top_which" type="radio">
												<span>App 연동</span>
											</label>
											<label class="input_radio" for="top_which_05">
												<input id="top_which_05" checked="" name="top_which" type="radio">
												<span>휴대용(작은 사이즈)</span>
											</label>
											<label class="input_radio long_label04" for="top_which_06">
												<input id="top_which_06" name="top_which" type="radio">
												<span>기타</span>
												<input type="text" placeholder="" class="gray">
											</label>
											<label class="input_radio" for="top_which_07">
												<input id="top_which_07" checked="" name="top_which" type="radio">
												<span>TVOC 센서(Auto Mode)</span>
											</label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q12</strong>
						<span>PSZ에서 개선이 필요한 점을 자유롭게 적어주십시오.</span>
					</div>
					<div class="table-type01 prd03_tbl12">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="txt_left">
										<div class="necessary">
											<span><em>&middot;&nbsp;</em>기능</span>
											<input type="text" placeholder="" class="gray">
										</div>
										<div class="necessary">
											<span><em>&middot;&nbsp;</em>디자인</span>
											<input type="text" placeholder="" class="">
										</div>
										<div class="necessary">
											<span><em>&middot;&nbsp;</em>휴대성</span>
											<input type="text" placeholder="" class="">
										</div>
										<div class="necessary">
											<span><em>&middot;&nbsp;</em>APP</span>
											<input type="text" placeholder="" class="">
										</div>
										<div class="necessary">
											<span><em>&middot;&nbsp;</em>기타</span>
											<input type="text" placeholder="" class="">
										</div>

									</td>
								</tr>
								
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q13</strong>
						<span>'PSZ'을 주변에 추천하고 싶으신가요?</span>
					</div>
					<div class="table-type01 prd03_tbl13">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio cntr clearfix">
										<label class="input_radio" for="recommend_01">
											<input id="recommend_01" checked="" name="recommend" type="radio">
											<span>적극 추천한다.</span>
										</label>
										<label class="input_radio" for="recommend_02">
											<input id="recommend_02" name="recommend" type="radio">
											<span>추천한다.</span>
										</label>
										<label class="input_radio" for="recommend_03">
											<input id="recommend_03" checked="" name="recommend" type="radio">
											<span>보통</span>
										</label>
										<label class="input_radio" for="recommend_04">
											<input id="recommend_04" checked="" name="recommend" type="radio">
											<span>추천하지 않는다.</span>
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q14</strong>
						<span>추천하지 않는다면 그 이유는 무엇인가요?</span>
					</div>
					<div class="table-type01 prd03_tbl14 half_arrg">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix txt_left">
										<div class="inner_5">
											<label class="input_radio" for="why_recommend_01">
												<input id="why_recommend_01" checked="" name="why_recommend" type="radio">
												<span>성능이 좋지 않아서</span>
											</label>
											<label class="input_radio" for="why_recommend_02">
												<input id="why_recommend_02" name="why_recommend" type="radio">
												<span>사용이 불편해서</span>
											</label>
											<label class="input_radio" for="why_recommend_03">
												<input id="why_recommend_03" checked="" name="why_recommend" type="radio">
												<span>디자인</span>
											</label>
											<label class="input_radio long_label04" for="why_recommend_04">
												<input id="why_recommend_04" name="why_recommend" type="radio">
												<span>기타</span>
												<input type="text" placeholder="" class="gray">
											</label>
											<label class="input_radio" for="why_recommend_05">
												<input id="why_recommend_05" checked="" name="why_recommend" type="radio">
												<span>가격이 비싸서</span>
											</label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="prd03_tbl_tit">
						<strong>Q15</strong>
						<span>PSZ에 대하여 여러분의 자유로운 의견을 들려주세요!</span>
					</div>
					<div class="table-type01 prd03_tbl15 last_tbl">
						<table>
							<colgroup>
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td class="pay_radio clearfix">
										<textarea name="" id="" cols="30" rows="10" class="gray"></textarea>	
									</td>
								</tr>
							</tbody>
						</table>
					</div>


					<h4 class="prd_last_tit">설문에 응해주셔서 감사합니다!</h4>
					
					<div class="btn_wrap prd_btn_wrap">
						<a href="javascript:;" class="btn btn_pp" style="width: 330px;" onclick="openNext03()"><span>다음</span></a>
						<a href="javascript:;" class="btn btn_line" style="width: 330px;" onclick="openPrev02()"><span>취소</span></a>
					</div>
				</div>
			</div>

		</div>						
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script src="/resources/user/js/order.js"></script>
<script>

</script>

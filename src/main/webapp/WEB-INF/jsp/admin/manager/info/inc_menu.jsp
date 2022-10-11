<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : infoReg.jsp
    Description : 사이트 관리자 등록
--%>


<table class="r_search_table">
	<caption></caption>
	<colgroup>
		<col width="200px">
		<col width="">
		<col width="">
		<col width="">
	</colgroup>
	<tbody>								
		<tr>
			<th scope="row" rowspan="4">
				<input type="checkbox" title="배너/팝업관리" id="A" name="menuCds" prenMenuCd="0" menuPathCd="A" value="A|배너/팝업관리" > <label for="A">배너/팝업관리</label>
			</th>
			<td><input  type="checkbox" title="배너관리" id="A1" name="menuCds" prenMenuCd="A" menuPathCd="A-A1" value="A1|배너관리" > <label for="A1">배너관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="WEB팝업관리" id="A2" name="menuCds" prenMenuCd="A" menuPathCd="A-A2" value="A2|WEB팝업관리" > <label for="A2">WEB 팝업관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="APP팝업관리" id="A3" name="menuCds" prenMenuCd="A" menuPathCd="A-A3" value="A3|APP팝업관리" > <label for="A3">APP 팝업관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="APPPUSH관리" id="A4" name="menuCds" prenMenuCd="A" menuPathCd="A-A4" value="A4|APPPUSH관리" > <label for="A4">APP PUSH관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th scope="row" rowspan="4">
				<input type="checkbox" title="상품관리" id="B" name="menuCds" prenMenuCd="0" menuPathCd="B" value="B|상품관리" > <label for="B">상품관리</label>
			</th>
			<td><input  type="checkbox" title="상품목록" id="B1" name="menuCds" prenMenuCd="B" menuPathCd="B-B1" value="B1|상품목록" > <label for="B1">상품목록</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="상품문의" id="B2" name="menuCds" prenMenuCd="B" menuPathCd="B-B2" value="B2|상품문의" > <label for="B2">상품문의</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="상품후기" id="B3" name="menuCds" prenMenuCd="B" menuPathCd="B-B3" value="B3|상품후기" > <label for="B3">상품후기</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="카테고리" id="B4" name="menuCds" prenMenuCd="B" menuPathCd="B-B4" value="B4|카테고리" > <label for="B4">카테고리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th scope="row" rowspan="2">
				<input type="checkbox" title="주문관리" id="C" name="menuCds" prenMenuCd="0" menuPathCd="C" value="C|주문관리" > <label for="C">주문관리</label>
			</th>
			<td><input  type="checkbox" title="주문관리" id="C1" name="menuCds" prenMenuCd="C" menuPathCd="C-C1" value="C1|주문관리" > <label for="C1">주문관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="송장등록" id="C2" name="menuCds" prenMenuCd="C" menuPathCd="C-C2" value="C2|송장등록" > <label for="C2">송장등록</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th scope="row" rowspan="2">
				<input type="checkbox" title="쿠폰관리" id="D" name="menuCds" prenMenuCd="0" menuPathCd="D" value="D|쿠폰관리" > <label for="D">쿠폰관리</label>
			</th>
			<td><input  type="checkbox" title="쿠폰관리" id="D1" name="menuCds" prenMenuCd="D" menuPathCd="D-D1" value="D1|쿠폰관리" > <label for="D1">쿠폰관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="쿠폰내역" id="D2" name="menuCds" prenMenuCd="D" menuPathCd="D-D2" value="D2|쿠폰내역" > <label for="D2">쿠폰내역</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th scope="row">
				<input type="checkbox" title="정품관리" id="E" name="menuCds" prenMenuCd="0" menuPathCd="E" value="E|정품관리" > <label for="E">정품관리</label>
			</th>
			<td><input  type="checkbox" title="정품목록" id="E1" name="menuCds" prenMenuCd="E" menuPathCd="E-E1" value="E1|정품목록" > <label for="E1">정품목록</label></td>
			<td></td>
			<td></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td><input  type="checkbox" title="정품미승인목록" id="E2" name="menuCds" prenMenuCd="E" menuPathCd="E-E2" value="E2|정품미승인목록" > <label for="E2">정품미승인목록</label></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><input  type="checkbox" title="설문조사" id="E3" name="menuCds" prenMenuCd="E" menuPathCd="E-E3" value="E3|설문조사" > <label for="E3">설문조사</label></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 		</tr> -->
		<tr>
			<th scope="row" rowspan="4">
				<input type="checkbox" title="게시판관리" id="F" name="menuCds" prenMenuCd="0" menuPathCd="F" value="F|게시판관리" > <label for="F">게시판관리</label>
			</th>
			<td><input  type="checkbox" title="공지사항" id="F1" name="menuCds" prenMenuCd="F" menuPathCd="F-F1" value="F1|공지사항" > <label for="F1">공지사항</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="이벤트" id="F2" name="menuCds" prenMenuCd="F" menuPathCd="F-F2" value="F2|이벤트" > <label for="F2">이벤트</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="FAQ" id="F3" name="menuCds" prenMenuCd="F" menuPathCd="F-F3" value="F3|FAQ" > <label for="F3">FAQ</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="커뮤니티" id="F4" name="menuCds" prenMenuCd="F" menuPathCd="F-F4" value="F4|커뮤니티" > <label for="F4">커뮤니티</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th scope="row">
				<input type="checkbox" title="고객관리" id="G" name="menuCds" prenMenuCd="0" menuPathCd="G" value="G|고객관리" > <label for="G">고객관리</label>
			</th>
			<td><input  type="checkbox" title="고객목록" id="G1" name="menuCds" prenMenuCd="G" menuPathCd="G-G1" value="G1|고객목록" > <label for="G1">고객목록</label></td>
			<td></td>
			<td></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td><input  type="checkbox" title="SMS히스토리" id="G2" name="menuCds" prenMenuCd="G" menuPathCd="G-G2" value="G2|SMS히스토리" > <label for="G2">SMS히스토리</label></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 		</tr> -->
		<tr>
			<th scope="row" rowspan="3">
				<input type="checkbox" title="통계관리" id="H" name="menuCds" prenMenuCd="0" menuPathCd="H" value="H|통계관리" > <label for="H">통계관리</label>
			</th>
			<td><input  type="checkbox" title="매출통계" id="H1" name="menuCds" prenMenuCd="H" menuPathCd="H-H1" value="H1|매출통계" > <label for="H1">매출 통계</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="정산통계" id="H2" name="menuCds" prenMenuCd="H" menuPathCd="H-H2" value="H2|정산통계" > <label for="H2">정산 통계</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="상품관리통계" id="H3" name="menuCds" prenMenuCd="H" menuPathCd="H-H3" value="H3|상품관리통계" > <label for="H3">상품관리 통계</label></td>
			<td></td>
			<td></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td><input  type="checkbox" title="정품등록 통계" id="H4" name="menuCds" prenMenuCd="H" menuPathCd="H-H4" value="H4|정품등록 통계" > <label for="H4">정품등록 통계</label></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><input  type="checkbox" title="고객분석(앱) 통계" id="H5" name="menuCds" prenMenuCd="H" menuPathCd="H-H5" value="H5|고객분석(앱) 통계" > <label for="H5">고객분석(앱) 통계</label></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 		</tr> -->
		<tr>
			<th scope="row" rowspan="5">
				<input type="checkbox" title="VSE" id="J" name="menuCds" prenMenuCd="0" menuPathCd="J" value="J|VSE" > <label for="J">VSE 관리</label>
			</th>
			<td><input  type="checkbox" title="이벤트-뉴스" id="J1" name="menuCds" prenMenuCd="J" menuPathCd="J-J1" value="J1|이벤트-뉴스" > <label for="J1">이벤트&amp;뉴스관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="FAQ관리" id="J2" name="menuCds" prenMenuCd="J" menuPathCd="J-J2" value="J2|FAQ관리" > <label for="J2">FAQ관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="창업상담관리" id="J3" name="menuCds" prenMenuCd="J" menuPathCd="J-J3" value="J3|창업상담관리" > <label for="J3">창업상담관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="사업설명회관리" id="J4" name="menuCds" prenMenuCd="J" menuPathCd="J-J4" value="J4|사업설명회관리" > <label for="J4">사업설명회관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="프로지원서관리" id="J5" name="menuCds" prenMenuCd="J" menuPathCd="J-J5" value="J5|프로지원서관리" > <label for="J5">프로지원서관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<th scope="row" rowspan="2">
				<input type="checkbox" title="환경설정" id="I" name="menuCds" prenMenuCd="0" menuPathCd="I" value="I|환경설정" > <label for="I">환경설정</label>
			</th>
			<td><input  type="checkbox" title="펌웨어관리" id="I1" name="menuCds" prenMenuCd="I" menuPathCd="I-I1" value="I1|펌웨어관리" > <label for="I1">펌웨어관리</label></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><input  type="checkbox" title="운영정책관리" id="I2" name="menuCds" prenMenuCd="I" menuPathCd="I-I2" value="I2|운영정책관리" > <label for="I2">운영정책관리</label></td>
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>
	        

<script type="text/javascript">
$(function(){
	 /**
      * 메뉴 선택시 상위 하위 메뉴 선택 이벤트
      */
     $('input[name=menuCds]').on({
         change : function(){
             eventChecked($(this));
         }
     });
});

/**
 * 체크박스 제어 
 */
function eventChecked(obj){
   var state = $(obj).prop("checked");
   var fullCodePath = $(obj).attr("menuPathCd").split("-");
   
   if(state){
       for(j=0; j < fullCodePath.length; j++){
           $("input[name=menuCds][id="+fullCodePath[j]+"]").prop('checked', true); 
       }
       $("input[name=menuCds][id^="+ $(obj).attr("id")+"]").prop('checked', true);
   }else{
       $("input[name=menuCds][prenMenuCd^="+$(obj).attr("id")+"]").prop('checked', false);

       for(j=0; j < fullCodePath.length; j++){
           if($("input[name=menuCds][prenMenuCd^="+$(obj).attr("prenMenuCd")+"]:checked").length < 1){
               $("input[name=menuCds][id="+fullCodePath[j]+"]").prop('checked', false); 
           }
       }
   }
}
</script>
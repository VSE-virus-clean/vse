<%
    response.setHeader("Pragma", "no-cache" );
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-store");
    response.setHeader("Cache-Control", "no-cache" );
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.*" %>
<%@ page import = "java.util.regex.*" %>
<%-- 
    JSP Name : kmcisInit.jsp
    Description : 본인확인 초기화
--%>
<%!
   // 파라미터 유효성 검증 --------------------------------------------
	boolean b = true;
	String regex = "";
	String regex1 = "";

	public Boolean paramChk(String patn, String param){
		Pattern pattern = Pattern.compile(patn);
		Matcher matcher = pattern.matcher(param);
		b = matcher.matches();
		return b;
	}
%>
<%
    //날짜 생성
    Calendar today = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String day = sdf.format(today.getTime());

    Random ran = new Random();
    int numLength = 6;
    String randomStr = "";
    for (int i = 0; i < numLength; i++) {
        randomStr += ran.nextInt(10);
    }

    //tr_cert 데이터 변수 선언 ---------------------------------------------------------------
	String tr_cert       = "";
    String cpId          = "VCIM1001";        // 회원사ID
    String urlCode       = "010002";     // URL코드
    String certNum       = day + randomStr;     // 요청번호 ( 본인인증 요청시 중복되지 않게 생성해야함. (예-시퀀스번호) )
    String date          = day;        // 요청일시
    String certMet       = "M";     // 본인인증방법
    String name          = "";        // 성명
    String phoneNo	     = "";	    // 휴대폰번호
    String phoneCorp     = "";   // 이동통신사
	String birthDay	     = "";	// 생년월일
	String gender	     = "";		// 성별
    String nation        = "";      // 내외국인 구분
	String plusInfo      = "";	// 추가DATA정보
	String extendVar     = "0000000000000000";                  // 확장변수
    //End-tr_cert 데이터 변수 선언 ---------------------------------------------------------------

	String tr_url     = (request.isSecure() ? "https://" : "http://") + request.getHeader("host") + "/api/kmcis/result.vc";         // 본인인증서비스 결과수신 POPUP URL
	String tr_add     = "N";         // IFrame사용여부
	
    //01. 한국모바일인증(주) 암호화 모듈 선언
    com.icert.comm.secu.IcertSecuManager seed  = new com.icert.comm.secu.IcertSecuManager();

	//02. 1차 암호화 (tr_cert 데이터변수 조합 후 암호화)
	String enc_tr_cert = "";
	tr_cert = cpId +"/"+ urlCode +"/"+ certNum +"/"+ date +"/"+ certMet +"/"+ birthDay +"/"+ gender +"/"+ name +"/"+ phoneNo +"/"+ phoneCorp +"/"+ nation +"/"+ plusInfo +"/"+ extendVar;
	enc_tr_cert = seed.getEnc(tr_cert, "");

	//03. 1차 암호화 데이터에 대한 위변조 검증값 생성 (HMAC)
	String hmacMsg = "";
	hmacMsg = seed.getMsg(enc_tr_cert);

	//04. 2차 암호화 (1차 암호화 데이터, HMAC 데이터, extendVar 조합 후 암호화)
	tr_cert  = seed.getEnc(enc_tr_cert + "/" + hmacMsg + "/" + extendVar, "");
%>
<!-- <table> -->
<!--     <tr> -->
<!--         <td align=center>회원사ID</td> -->
<%--         <td align=left><%=cpId%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>URL코드</td> -->
<%--         <td align=left><%=urlCode%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>요청번호</td> -->
<%--         <td align=left><%=certNum%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>요청일시</td> -->
<%--         <td align=left><%=date%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>본인인증방법</td> -->
<%--         <td align=left><%=certMet%></td> --%>
<!--         </td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>이용자성명</td> -->
<%--         <td align=left><%=name%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>휴대폰번호</td> -->
<%--         <td align=left><%=phoneNo%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>이동통신사</td> -->
<%--         <td align=left><%=phoneCorp%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>생년월일</td> -->
<%-- 		<td align=left><%=birthDay%></td> --%>
<!--     </tr> -->
<!-- 	<tr> -->
<!--         <td align=center>이용자성별</td> -->
<%--         <td align=left><%=gender%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>내외국인</td> -->
<%--         <td align=left><%=nation%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>추가DATA정보</td> -->
<%--         <td align=left><%=plusInfo%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>요청정보(암호화)</td> -->
<!--         <td align=left> -->
<%--             <%=tr_cert.substring(0,50)%>... --%>
<!--         </td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>결과수신URL</td> -->
<%--         <td align=left><%=tr_url%></td> --%>
<!--     </tr> -->
<!--     <tr> -->
<!--         <td align=center>IFrame사용여부</td> -->
<%--         <td align=left><%=tr_add%></td> --%>
<!--     </tr>	 -->
<!-- </table> -->

<form name="reqKMCISForm" method="post" action="https://www.kmcert.com/kmcis/web/kmcisReq.jsp">
    <input type="hidden" name="tr_cert"     value = "<%=tr_cert%>">
    <input type="hidden" name="tr_url"      value = "<%=tr_url%>">
    <input type="hidden" name="tr_add"      value = "<%=tr_add%>">	
</form>

<script>
	document.reqKMCISForm.submit();
</script>

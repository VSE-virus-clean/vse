<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>사진 첨부하기</title>
</head>
<body>
<script type="text/javascript">
	try { document.domain = "*.viruscleanlab.com"; } catch(e) {}
	
    // execute callback script
    var sUrl = document.location.search.substr(1);
    
	if (sUrl != "blank") {
        var oParameter = {}; // query array

        sUrl.replace(/([^=]+)=([^&]*)(&|$)/g, function(){
            oParameter[arguments[1]] = arguments[2];
            return "";
        });
        
        if ((oParameter.errstr || '').length) {
            (parent.jindo.FileUploader._oCallback[oParameter.callback_func+'_error'])(oParameter);
        } else {
	        (parent.jindo.FileUploader._oCallback[oParameter.callback_func+'_success'])(oParameter);
	   }
	}
</script>
</body>
</html>
/**
 * Supported Browser : MSIE, Chrome, FireFox, Safari
 * 
 * Object       : common-util.js
 * Description  : 공통 Util용 스크립트 
 * Author       : LaheeDad
 * Since        : 2013.9.23.
 * Version      : 1.0
 * 
 * Modification Information
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013.9.23.     LaheeDad         최초 생성
 */

var $$MENU_ID;

/**
 * 앞뒤 공백 제거
 * @returns 공백이 제거된 문자열
 */
String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/gi, '');
};

/**
 * Java StartWith 구현
 * @param   시작문자(문자열)
 * @returns Boolean : 시작문자유무(true:시작문자)
 */
if ( typeof String.prototype.startsWith != 'function' ) {
    String.prototype.startsWith = function( str ) {
        return str.length > 0 && this.substring(0, str.length) === str;
    };
};

/**
 * Java EndWith 구현
 * @param   마지막문자(문자열)
 * @returns Boolean : 마지막문자유무(true:마지막문자)
 */
if ( typeof String.prototype.endsWith != 'function' ) {
    String.prototype.endsWith = function( str ) {
        return str.length > 0 && this.substring(this.length - str.length, this.length) === str;
    };
};


/**
 * 페이지 로딩시 bind 되어야 할 이벤트 등록 
 */
$(function() {  
    
   // $('img').error( function(){$(this).attr('src', '/resources/admin/images/error_img.jpg'); });
    
    $(window).resize(function(){
        //modalComUtil.resize();
    });
    
    $('#header ul.menu').ready(function(){
        if ($$MENU_ID != undefined && $$MENU_ID != ''){
            var depth = $$MENU_ID.split('|');    
            $('li.'+depth[0]).addClass('on');        
        }
    });

    $('#left').ready(function(){
        if ($$MENU_ID != undefined && $$MENU_ID != ''){
            var depth = $$MENU_ID.split('|');        
            
            for(var i = 1; i < depth.length; i++){
                $('.'+depth[i]).addClass('on');
            }                
        }
    });
    
    /**
     * A태그중 .submit은 Form객체 전송시 사용
     */
    $('a.submit').on({
        click : function(event){    
            event.preventDefault();   
            if(!$(this).prop('disabled')){
                submitUtil.disabled();
                $(this).parents('form').eq(0).submit();   
            }
        }
    });
    
    /**
     * Submit버튼 클릭시 disabled 시키기
     */
    $('button[type=submit]').on({
        click : function(event){    
        	event.preventDefault();   
            if(!$(this).prop('disabled')){
                submitUtil.disabled();
                $(this).parents('form').eq(0).submit();   
            }
        }
    });
    
    /**
     * A태그중 .reset은 Form객체 초기화
     * - hidden은 초기화 되지 않음.
     */
    $("a.reset").on({
        click : function(event){ 
            event.preventDefault();  
            if(!$(this).prop('disabled')){
                if('새로 입력한 모든 내용을 초기화 하시겠습니까?\n저장하지 않는 정보는 삭제 됩니다.'){
                    $('form[name=mainForm]')[0].reset();
                    $('input[type=text]').eq(0).focus();
                }
            }
        }
    });
    
    /**
     * A태그중 .searchSubmit .reportSubmit은 엑셀다운로드 Form객체 전송시 사용
     */
    $('a.searchSubmit, a.reportSubmit').on({
        click : function(event){    
            event.preventDefault();  
            
            if(!$(this).prop('disabled')){
                submitUtil.disabled();
                
                var form = $(this).parents('form').eq(0);
                $(form).attr('action', $(this).attr('href'));
                $(form).submit(); 
            }
        }
    });

    /**
     * input태그중 .submit은 Form객체 전송시 사용
     */
    $('input.submit').on({
        keypress :function(event){
            if(!$(this).prop('disabled')){
                if(event.which == '13'){
                    submitUtil.disabled();
                    $(this).parents('form').eq(0).submit();
                }
            }
        }
    });
    
    /**
     * 입력글자 대문자로 변환
     * enter submit금지
     * space 금지
     *
     * 1 .enter key / space key 무시
     * 2. 영문 소문자는 대문자로 치환 (a = 65, z = 90)
     *    - enter = 13
     *    - space = 32    
     */
    $('.txtUpperNoSubmit').on({
        keypress :function(event){
            if(event.which == '13' || event.which == '32'){
                event.preventDefault();
            }
        },
        keyup : function(event){
            if(event.which >= 65 && event.which <= 90){
                $(this).val($(this).val().toUpperCase());
            }
        }
    });
    
    /**
     * space 금지
     */     
    $(".noSpace").on({
        keypress : function(event){
            if(event.which == '32'){
                event.preventDefault();
            }
        }
    });    
    
    /**
     *  숫자만 입력
     *  0~9 : 48~57  
     *  BAKC SPACE : 8
     *  ENTER : 13
     *  SPACE : 32    
     */
    $('.numOnly').css('imeMode', 'disabled').live({
        keypress : function(event){   
            if(event.which && !(event.which  > 47 && event.which  < 58 || event.which == 8)) {
//            	alert('숫자만 입력해 주세요.');
                event.preventDefault();
            }
        },
        keyup : function(event){
            var str = $(this).val();
            var len = str.length;
            var replaceStr = new String;

            for(var i=0; i < len; i++){
                if(str.charCodeAt(i) > 47 && str.charCodeAt(i) < 58){
                    replaceStr += str.charAt(i);
                }
            }

            if($(this).attr('numType') !== undefined){
                if($(this).attr('numType') === 'money'){
                    $(this).val( numUtil.createComma(replaceStr));
                }
            }else{
                $(this).val(replaceStr);
            }
        }
    });
    
    /**
     *  숫자, 소숫점만 입력
     *  0~9 : 48~57  
     *  BAKC SPACE : 8
     *  ENTER : 13
     *  SPACE : 32  
     *  소숫점 : 46
     */
    $('.numOnly2').css('imeMode', 'disabled').live({
        keypress : function(event){   
            
            if(event.which && !(event.which  > 47 && event.which  < 58 || event.which == 8 || event.which == '13' || event.which == '46' )) {
            	alert('숫자만 입력해 주세요.');
                event.preventDefault();
            }
        },
        keyup : function(event){
            var str = $(this).val();
            var len = str.length;
            var replaceStr = new String;

            for(var i=0; i < len; i++){
                if(str.charCodeAt(i) > 47 && str.charCodeAt(i) < 58 || str.charCodeAt(i) == 46){
                    replaceStr += str.charAt(i);
                }
            }

            $(this).val(replaceStr);
        }
    });
    
    /**
     *  숫자, 콤마만 입력
     *  0~9 : 48~57  
     *  BAKC SPACE : 8
     *  ENTER : 13
     *  SPACE : 32  
     *  콤마 : 44
     */
    $('.numOnly3').css('imeMode', 'disabled').live({
        keypress : function(event){   
            
            if(event.which && !(event.which  > 47 && event.which  < 58 || event.which == 8 || event.which == '13' || event.which == '44' )) {
            	alert('숫자만 입력해 주세요.');
                event.preventDefault();
            }
        },
        keyup : function(event){
            var str = $(this).val();
            var len = str.length;
            var replaceStr = new String;

            for(var i=0; i < len; i++){
                if(str.charCodeAt(i) > 47 && str.charCodeAt(i) < 58 || str.charCodeAt(i) == 44){
                    replaceStr += str.charAt(i);
                }
            }

            $(this).val(replaceStr);
        }
    });
    
    /**
     *  숫자, 소숫점만 입력 1자리, 3자리
     *  0~9 : 48~57  
     *  BAKC SPACE : 8
     *  ENTER : 13
     *  SPACE : 32  
     *  소숫점 : 46
     */
    $('.numOnly21').css('imeMode', 'disabled').live({
        keypress : function(event){   
            
            if(event.which && !(event.which  > 47 && event.which  < 58 || event.which == 8 || event.which == '13' || event.which == '46' )) {
            	alert('숫자만 입력해 주세요.');
                event.preventDefault();
            }
            
            returnStr = $(this).val();
        },
        keyup : function(event){
            var strArr = new Array();
            var str = $(this).val();
            var count = 0;
            
            strArr = str.split('.');
                    
            //소수점 갯수 체크
            if(strArr.length > 2){
                alert('소수점 1자리만 입력해 주세요.');
                $(this).val(returnStr);
            }
            
            //소수점 체크
            if (strArr.length > 1) {
                if (strArr[0].length > 3) {
                    alert('3자리 숫자만 입력해 주세요.');
                    $(this).val(returnStr);
                }
                if (strArr[1].length > 1) {
                    alert('소수점 한자리만 입력해 주세요.');
                    $(this).val(returnStr);
                }
            } else {
                if (str.length > 3) {
                    alert('3자리 숫자만 입력해 주세요.');
                    $(this).val(returnStr);
                }
            }
            
            var str = $(this).val();
            var len = str.length;
            var replaceStr = new String;

            for(var i=0; i < len; i++){
                if(str.charCodeAt(i) > 47 && str.charCodeAt(i) < 58 || str.charCodeAt(i) == 46){
                    replaceStr += str.charAt(i);
                }
            }

            $(this).val(replaceStr);
        }
    });
    
    
    /**
     *  전화번호 : 숫자와 -만 입력
     *  0~9 : 48~57  
     *  BAKC SPACE : 8
     *  - : 45 
     */
    $('.telNumOnly').css('imeMode', 'disabled').on({
        keypress : function(event){   
            if(event.which && !(event.which  > 47 && event.which  < 58 || event.which == 8 || event.which == '13' || event.which == '45' )) {
                event.preventDefault();
            }
        },
        keyup : function(event){
            var str = $(this).val();
            var len = str.length;
            var replaceStr = new String;

            for(var i=0; i < len; i++){
                if(str.charCodeAt(i) > 47 && str.charCodeAt(i) < 58 || str.charCodeAt(i) == 45){
                    replaceStr += str.charAt(i);
                }
            }

            $(this).val(replaceStr);
        }
    });

    /**
     * 기본으로 영문을 입력받아햐 할때 사용(IE만 가능)
     */
    $('.engMode').css('imeMode', 'disabled');
    
    /**
     *  대소문자만 입력
     *  0~9 : 48~57  
     *  대문자문자 : 65~90
     *  소문자: 97~122
     */
    $('.engOnly').css('imeMode', 'disabled').on({
        keypress : function(event){   
            if (!(event.which && (event.which  > 64 && event.which  < 91 || event.which == 8) || (event.which  > 96 && event.which  < 123 || event.which == 8))) {
                alert('대소문자만 입력해 주세요.');
                event.preventDefault();
            }
        },
        keyup : function(event){
            var str = $(this).val();
            var len = str.length;
            var replaceStr = new String;

            for(var i=0; i < len; i++){
                if((str.charCodeAt(i) > 64 && str.charCodeAt(i) < 91) || (str.charCodeAt(i) > 96 && str.charCodeAt(i) < 123)){
                    replaceStr += str.charAt(i);
                }
            }

            $(this).val(replaceStr);
        }
    });
    
    
    /**
     *  아이디에 영문/숫자만 가능
     *  0~9 : 48~57  
     *  대문자문자 : 65~90
     *  소문자: 97~122
     */
    $('.idPattern').css('imeMode', 'disabled').on({
        keypress : function(event){   
            if (!(event.which && (event.which > 47 && event.which  < 58 || event.which == 8) || (event.which  > 64 && event.which  < 91 || event.which == 8) 
                    || (event.which  > 96 && event.which  < 123 || event.which == 8) )) {
                event.preventDefault();
            }
        },
        keyup : function(event){
            var str = $(this).val();
            var len = str.length;
            var replaceStr = new String;

            for(var i=0; i < len; i++){
                if( (str.charCodeAt(i) > 47  && str.charCodeAt(i) < 58) || (str.charCodeAt(i) > 64 && str.charCodeAt(i) < 91) 
                        || (str.charCodeAt(i) > 96 && str.charCodeAt(i) < 123)){
                    replaceStr += str.charAt(i);
                }
            }

            $(this).val(replaceStr);
        }
    });
    
    /**
     * 아이디에 특문입력금지
    **/
    /*$(".idPattern").on({
        keypress : function(event){
            if (!((event.which && (event.which  > 64 && event.which  < 91 || event.which == 8) || 
                    (event.which  > 96 && event.which  < 123 || event.which == 8) || 
                    (event.which  > 47 && event.which  < 58 || event.which == 8 || event.which == '13' || event.which == '32') || 
                    (event.which == '45' || event.which == '95' || event.which == '46')))) {
                event.preventDefault();
            }
        }
    });  */
    
    /**
     * 모달 화면 열기
     */
    $('.btn_modal_open').on({
        click : function(event){
            event.preventDefault();
            modalUtil.open($(this));
        } 
    });
    
    /**
     * 모달 화면 닫기
     */
    $('.modal_wrap a.btn_pop_close, .modal_wrap a.btn_cancle').on({
        click : function(event){
            event.preventDefault();
            modalUtil.close($(this));
        } 
    });   
    
    var today =  new Date();
    /**
     * 달력
     */
    try{
        $('.datepicker').datepicker({
        	 dateFormat: "yy-mm-dd"
            //,showOn: "both" //"button"
            //,buttonImage: "/resources/admin/images/ico_calender.gif"
            ,buttonImageOnly: false
            ,showMonthAfterYear: true
            ,changeMonth : true
            ,changeYear : true
            ,yearRange : 'c-30:c+60'
            ,beforeShow: function() {
                $('#ifr3').css({'width':$(window).width(),'height':$(document).height()});
                $('#ifr3').show();  
                $('#ui-datepicker-div, .ui-datepicker').css({'z-index' : 999999}); //ui.js에서 수정해줘야함.
            }
            ,onClose : function(dateText, inst){
                $('#ifr3').hide();
            }        
        }).live({
            keypress : function(event){   
                if(event.which && !(event.which  > 47 && event.which  < 58 || event.which == 8 || event.which == '13' || event.which == '32')) {
                    alert('숫자만 입력해 주세요.');
                    event.preventDefault();
                }
            },
            keyup : function(event){
                var obj = $(this);
                var strDate = $(this).val().replace(/[\.]/gi,"");
                
                if( isNaN(strDate) || strDate==null ){
                    return;
                }
    
                var maxSize = 8;
                var size = strDate.length;
    
                if(size == maxSize || size > maxSize){
                    if(!dateUtil.isDateFormat(strDate)){
                        $(this).select();
                        alert("날짜가 잘못 입력되었습니다.\n\n 다시 입력해주세요..");
                        return;
                    }
                }
    
                if(size > 6 ){
                    $(this).val(strDate.substring(0,4) + dateUtil.delimiter + strDate.substring(4,6) + dateUtil.delimiter + strDate.substring(6));
                }else if(size >= 4 ){
                    $(this).val(strDate.substring(0,4) + dateUtil.delimiter + strDate.substring(4));
                }
            }
        });
    }catch(e){ 
        //alert('[ERROR DATEPICKER] message : ' + e.message); 
    }
    
    /* 텍스트 박스 글자수 체크 */
    $('.textLengthCheck').on({
        keyup : function (event) {
            var obj = $(this);
            
            var maxLength = parseInt($(this).attr("maxlength"));
            if ($(this).val().length > maxLength) {
                alert('글자수가 ' + ($(this).val().length-1) + '자 이내로 제한됩니다');
                $(this).val($(this).val().substring(0, maxLength));
            }
        }
    });

    
    //파일 다운로드
    fileUtil.downloadEvent();
    
    //파일 삭제
    fileUtil.deleteEvent();
    
    eventObj.disabledEvent();
    
    //목록에서 전체 ROW선택
    eventObj.checkboxSelectAll();
    
    //게시물 로그 보기
    //eventObj.popupLogViewer();
});


var eventObj = {
    iframeClickEvent : function(){
        document.getElementById('ifr3').contentWindow.document.body.onclick = function(){
            $('.datepicker').datepicker('hide');
            $('#ifr3').hide();        
        };
    },
    /**
     * 파일첨부를 변경시 해당 파일정보는 input.text에 보여준다.
     * 
     * eventObj.inputFileChangeEvent();
     */    
    inputFileChangeEvent : function(){        
        $('input[type=file]').live({            
            change : function(){
                var txtName = $(this).attr('name');
                
                try{
                    $('#txt_'+ txtName).val($(this).val());
                    $('.desc_'+ txtName).prop('disabled', false);
                    
                    if(loginObj.isDevice == 'N'){
                        $('.desc_'+ txtName).css({'background-color' : '#FFF'});
                    }
                }catch(e){
                    //ignore
                }
            }
        });   
    },
    /**
     * .disabled 일때 배경색 설정하기
     */
    disabledEvent : function(){
        $('.disabled').prop('disabled', true); //.css({'background-color' : '#F5F5F5'});
    },
    /**
     * 주소 찾기
     * - 사용하는 페이지에서 이벤트 적용해 줄것.
     * @use eventObj.searchAddress();
     
    searchAddress : function(){
        $('.btn_find_address').on({
            click : function(event){
                event.preventDefault();  
                commonUtil.searchAddress();
            }
        });
    },*/
    /**
     * 목록에서 전체 ROW선택을 위한 이벤트
     */
    checkboxSelectAll : function(){
        $('input.checkbox-selectAll').live({
            change : function(event){
                $(this).parents('table').find('input.checkbox-select').prop('checked', $(this).is(':checked'));
            }
        });
        
        $('.btnInfoCancle').live({
            click : function(event){
                $('input.checkbox-select, input.checkbox-selectAll').prop('checked', false);
            }
        });
    },
    /**
     * 주소 찾기
     * - 사용하는 페이지에서 이벤트 적용해 줄것.111
     * @use eventObj.searchAddress();
     */
    searchAddress : function(){
//        $(".find-address").click(function(){
//            window.open("/common/pop/addressPList.vc", 'popAddress', 'width=740,height=724,scrollbars=yes,resizable=no');
//        }); 
    },
    /**
     * 게시물 로그 보기
     */
    popupLogViewer : function(){
        $('.popup-logViewer').click(function(event){
            event.preventDefault();
            windowUtil.open($(this).attr('href'),  "popLogViewer", 600, 400, 0) ;
        });
    }
};



/**
 * 공통 코드 조회
 */
var comCodeUtil = {        
    /**
     * 출력할 영역의 elementID(#id)
     */
    docuemntElementID : '',
    
    /**
     *  출력한 select element의 name값
     */
    selectElementName : '',
    
    searchUrl : loginObj.contextPath + '/cm/caList.vc',
    searchHUrl : loginObj.contextPath + '/cm/caHList.vc',
    
    /**
     * 공통 코드 조회
     * ref : comCodeUtil.getCode('검색코드', '출력 Element Name(ID아님)', 'code,code');
     * @param code 조회 코드
     * @param callBack 리턴되는 Call Back 함수명, TEXT로 넘기면 안됨.
     */
    getCode : function(code, callBack){
        var data = { comCd : code };
        ajaxUtil.postDisableAsync(loginObj.contextPath + '/cm/caDet.vc', data, callBack);
    },
    /**
     * 공통 코드 조회후 화면 출력 :: 커스텀용으로 추가
     * ref : comCodeUtil.getCodeNPrint('Url', '검색코드', '출력 Element Name(ID아님)', 'select||checkbox||radio');
     * @param code 조회 부모 코드
     * @param selectElementName 출력한 select element의 name값
     * @param type 화면 표시 방법 (select / checkbox / radio)
     */
    getCodeNPrintByCustom : function(url, code, selectElementName, type, notSelectCode){
        comCodeUtil.selectElementName = selectElementName;
        
        var data = { searchKey : code };
       
        if(type == undefined || type === 'select'){
            ajaxUtil.postDisableAsync(url, data, comCodeUtil.getCodeNSelectPrintResult);
        }else if(type === 'checkbox'){
            ajaxUtil.postDisableAsync(url, data, comCodeUtil.getCodeNCheckPrintResult);
        }else if(type === 'radio'){
            ajaxUtil.postDisableAsync(url, data, comCodeUtil.getCodeNRadioPrintResult);
        }
    },
    /**
     * 공통 코드 조회후 화면 출력
     * ref : comCodeUtil.getCodeNPrint('검색코드', '출력 Element Name(ID아님)', 'select||checkbox||radio', 'code,code');
     * @param code 조회 부모 코드
     * @param selectElementName 출력한 select element의 name값
     * @param type 화면 표시 방법 (select / checkbox / radio)
     * @param langCode 표시언어 (KR-default / EN / ZH)
     * @param selectCode 검색 예외/포함 자식코드, 구분자는 "," 
     *          ref : 'aa,ab'            
     * @pram type A(포함), B(제외)  selectCode를 조회 조건에서 제외할것인지 포함 할것인지 확인
     */
    getCodeNPrint : function(code, selectElementName, type, langCode, selectCode, searchType){
        comCodeUtil.selectElementName = selectElementName;
        
        if(langCode == undefined){
            langCode = "KR";
        }
        
        if(searchType == undefined){
            searchType = "B";
        }
        
        var data = { prenComCd : code, siteCd : langCode, selectCd : selectCode, searchType : searchType };
        
        if(type == undefined || type === 'select'){
            ajaxUtil.postDisableAsync(comCodeUtil.searchUrl, data, comCodeUtil.getCodeNSelectPrintResult);
        }else if(type === 'checkbox'){
            ajaxUtil.postDisableAsync(comCodeUtil.searchUrl, data, comCodeUtil.getCodeNCheckPrintResult);
        }else if(type === 'radio'){
            ajaxUtil.postDisableAsync(comCodeUtil.searchUrl, data, comCodeUtil.getCodeNRadioPrintResult);
        }
    },
    /**
     * 공통 코드 CTE 조회후 화면 출력
     * ref : comCodeUtil.getHCodeNPrint('검색코드', '출력 Element Name(ID아님)', 'select||checkbox||radio', 'code,code');
     * @param code 조회 부모 코드
     * @param selectElementName 출력한 select element의 name값
     * @param type 화면 표시 방법 (select / checkbox / radio)
     * @param langCode 표시언어 (KR-default / EN / ZH)
     * @param selectCode 검색 예외/포함 자식코드, 구분자는 "," 
     *          ref : 'aa,ab'            
     * @pram type A(포함), B(제외)  selectCode를 조회 조건에서 제외할것인지 포함 할것인지 확인
     */
    getHCodeNPrint : function(code, selectElementName, type, langCode, selectCode, searchType){
        comCodeUtil.selectElementName = selectElementName;
        
        if(langCode == undefined){
            langCode = "KR";
        }
        
        if(searchType == undefined){
            searchType = "B";
        }
        
        var data = { prenComCd : code, siteCd : langCode, selectCd : selectCode, searchType : searchType };
        
        if(type == undefined || type === 'select'){
            ajaxUtil.postDisableAsync(comCodeUtil.searchHUrl, data, comCodeUtil.getCodeNSelectPrintResult);
        }else if(type === 'checkbox'){
            ajaxUtil.postDisableAsync(comCodeUtil.searchHUrl, data, comCodeUtil.getCodeNCheckPrintResult);
        }else if(type === 'radio'){
            ajaxUtil.postDisableAsync(comCodeUtil.searchHUrl, data, comCodeUtil.getCodeNRadioPrintResult);
        }
    },
    /**
     * 공통 코드 조회후 화면 출력
     * ref : comCodeUtil.getCodeNPrint('검색코드', '출력 Element Name(ID아님)', 'select||checkbox||radio', 'code,code');
     * @param code 조회 부모 코드
     * @param selectElementName 출력한 select element의 name값
     * @param selectCode 검색 예외/포함 자식코드, 구분자는 "," 
     *          ref : 'aa,ab'            
     * @pram type A(포함), B(제외)  selectCode를 조회 조건에서 제외할것인지 포함 할것인지 확인
     */
    getCodeNPrint2 : function(code, selectElementName, selectCode, searchType){
        comCodeUtil.selectElementName = selectElementName;
        
        var preVal = '0';
        var tr = $('select[name='+comCodeUtil.selectElementName+']').parents('tr');
        
        $('.btn-edit').hide();
        $('form[name=subForm1]').hide();
        
        if(code != ''){
            if(searchType == undefined){
                searchType = "B";
            }
            
            $('form[name=subForm1]').find('input[name=prenComCd]').val(code);
            
            var data = { prenComCd : code, selectCd : selectCode, searchType : searchType };
            ajaxUtil.postDisableAsync(comCodeUtil.searchUrl, data, comCodeUtil.getCodeNSelectPrintResult2);
        }else{
            $(tr).hide().parents('tbody').find('tr').eq($(tr).index()-1).find('.btn-edit').show();
            
            
            if($(tr).index() - 2 >= 0){
                preVal = $(tr).hide().parents('tbody').find('tr').eq($(tr).index()-2).find('select').val();
            }
            
            $('form[name=subForm1]').find('input[name=prenComCd]').val(preVal);
        }
        
    },
    /**
     * 공통 코드 SELECTE로 화면 출력
     * - 기존 Default는 유지하고 option을 추가 하도록 수정
     * @param json json
     */
    getCodeNSelectPrintResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        //alert(JSON.stringify(json));
        if(json.bindingStatus == undefined && json.result.status){
            var codeList = json.result.list;
            var codeSize = codeList.length;
            var oSelect = $('select[name='+comCodeUtil.selectElementName+']');
            
            oSelect.next('select').children().not(':eq(0)').remove();
            oSelect.next('select').prop('disabled', true);
            
//            if(json.result.searchInfo.prenComCd.startsWith('Z')){
//                //코드값을 사용하지 않는 코드
//                for(var i = 0; i < codeSize ; i++){
//                    $(oSelect).append('<option value="'+codeList[i].comCdNm+'">'+codeList[i].comCdNm+'</option>');
//                }
//            }else if(json.result.searchInfo.prenComCd.startsWith('C')){
//                //제품 코드
//                for(var i = 0; i < codeSize ; i++){
//                    $(oSelect).append('<option value="'+codeList[i].comCd+'">'+codeList[i].comCdNm+' ['+ codeList[i].comCd +']</option>');
//                }
//            }else{
            for(var i = 0; i < codeSize ; i++){
                $(oSelect).append('<option value="'+codeList[i].comCdNm+'">'+codeList[i].comCdNm+'</option>');
            }
            
            oSelect.show().prop('disabled', false);
        }else{
            //ajaxUtil.error(json);
        }
    },
    /**
     * 공통 코드 SELECTE로 화면 출력
     * - 기존 Default는 유지하고 option을 추가 하도록 수정
     * @param json json
     */
    getCodeNSelectPrintResult2 : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result.status){
            
            var oSelect = $('select[name='+comCodeUtil.selectElementName+']');
            
            if(json.result.list != undefined){
                var codeList = json.result.list;
                var codeSize = codeList.length;
                
                if(codeSize > 0){
                    oSelect.children().not(':eq(0)').remove();
                    
                    for(var i = 0; i < codeSize ; i++){
                        $(oSelect).append('<option value="'+codeList[i].comCd+'">'+codeList[i].comCdNm+'</option>');
                    }
                }
            }else{
                oSelect.children().not(':eq(0)').remove();
                $(oSelect).append('<option value="">입력된 코드가 없습니다.</option>');
            }
            
            oSelect.parents('tr').show().find('.btn-edit').show();
            
        }else{
            ajaxUtil.error(json);
        }
    },
    /**
     * 공통 코드 CHECK로 화면 출력
     * @param json json
     */
    getCodeNCheckPrintResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result.status){
            var codeList = json.result.list;
            var codeSize = codeList.length;
            var text = '';
            
            for(var i = 0; i < codeSize ; i++){
                text += '<input type="checkbox" name="'+comCodeUtil.selectElementName+'" id="'+codeList[i].comCd+'" value="'+codeList[i].comCd+'" /><label for="'+codeList[i].comCd+'">'+codeList[i].comCdNm+'</label>';
            }
            
            $('#'+comCodeUtil.selectElementName).html(text);
            
        }else{
            ajaxUtil.error(json);
        }
    },
    /**
     * 공통 코드 RADIO로 화면 출력
     * - 기존 Default는 유지하고 option을 추가 하도록 수정
     * @param json json
     */
    getCodeNRadioPrintResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        //alert(JSON.stringify(json));
        if(json.bindingStatus == undefined && json.result.status){
            var codeList = json.result.list;
            var codeSize = codeList.length;
            var text = '';
            
            for(var i = 0; i < codeSize ; i++){
                text += '<label class="input_radio" for="'+codeList[i].comCd+'">' + '<input type="radio" name="'+comCodeUtil.selectElementName+'" id="'+codeList[i].comCd+'" value="'+codeList[i].comCd+'" /><span>'+codeList[i].comCdNm+'</span></label>';
            }
            
            $('#'+comCodeUtil.selectElementName).html(text);
            
        }else{
            ajaxUtil.error(json);
        }
    }
};

/**
 * AJAX 페이징 처리
 */
var ajaxPagging = {  
    /**
     * 현재 페이지 번호
     */
    currentPageNo : 1,
    /**
     * AJAX 페이징 처리
     * 
     * @use ajaxPagging.create(pUrl, pElementClass, pTotalRow, pPage, pRowLimit, pPageLimit)
     * @param pUrl              페이지 url
     * @param pElementClass     Dom ID
     * @param pTotalRow         전체 갯수
     * @param pPage             현재 페이지
     * @param pRowLimit         페이지 노출 Row갯수
     * @param pPageLimit        한번에 보여주는 페이지수
     */
    create : function(pUrl, pElementClass, pTotalRow, pPage, pRowLimit, pPageLimit){
        var btFirst = '';           //첫페이지 번호
        var btPrev = '';            //이전 페이지 번호
        var btPage = '';            //현재 페이지 번호
        var btNext = '';            //다음 페이지 번호
        var btLast = '';            //마지막 페이지 번호
        
        if(pTotalRow > 0){
            currentPageNo = pPage > 0 ? pPage : 1;
            
            var totalPage = parseInt(pTotalRow / pRowLimit + (pTotalRow % pRowLimit == 0 ? 0 : 1));       //전체 페이지 수
            var startPage =  parseInt( parseInt((currentPageNo-1)/pPageLimit) * pPageLimit  + 1);           //시작페이지 번호
            
            //첫페이지로
            if(currentPageNo > 1){
                btFirst = '<a href="'+ pUrl + 1 +'" class="first" title="첫 페이지로 이동">&lt;&lt;</a>&nbsp;';
            }
            //이전 페이지
            if(currentPageNo > pPageLimit){
                btPrev = '<a href="' + pUrl + (startPage-1) + '" class="prev" title="이전 페이지로 이동">&lt;</a>&nbsp;';
            }
            //다음 페이지
            if((startPage + pPageLimit) <= totalPage){
                btNext = '<a href="' + pUrl + (startPage+pPageLimit) + '" class="next" title="다음 페이지로 이동">&gt;</a>&nbsp;';
            }  
            //마지막 페이지로
            if(currentPageNo < totalPage){
                btLast = '<a href="' + pUrl + totalPage + '" class="end" title="마지막 페이지로 이동">&gt;&gt;</a>';
            }
            
            for(var i=0; i < pPageLimit; i++){
                var pageNo = startPage + i;       
                if(pageNo > totalPage){  break;  }        
                if(pageNo == currentPageNo){
                    btPage += '<a href="#" class="on" onclick="return false;">'+ pageNo +'</a>&nbsp;';
                }else{
                    btPage += '<a href="'+ pUrl + pageNo +'">'+ pageNo +'</a>&nbsp;';
                }
            }
        }
          
        $('.'+pElementClass).html(btFirst + btPrev + btPage + btNext + btLast);
    }
};

/**
 * 윈도우 팝업 처리
 */
var windowUtil = {
    /**
     * 팝업 열기
     * 
     * - 기본 팝업 열기 : windowUtil.open(uri,  "poptitle", 430, 190, 0) ;
     * - 팝업사이즈 변경 필요할 경우 : windowUtil.open(uri,  "poptitle1", 430, 190, 0,1);
     *  
     * @param url           경로
     * @param windowName    팝업명
     * @param width         가로크기
     * @param height        세로크기
     * @param strScroll     스크롤 여부
     * @param strResize     크기변경 여부
     */
    open: function(url,  windowName, width, height, strScroll, strResize) {
        var popupWindow = "" ;
        
        if(!(width == '' && height == '')){
            windowX = Math.ceil( (window.screen.width  - width) / 2 );
            windowY = Math.ceil( (window.screen.height - height) / 2 );
        }
        
        if(strResize == undefined || strResize == '') {
            strResize = 0 ;
        }
        
        if(!(width == '' && height == '')){
            popupWindow = window.open(url, windowName, "width=" + width + ", height=" + height + ", top="+ windowY +", left="+ windowX +", scrollbars="+ strScroll+", resizable="+ strResize);
        }else{
            popupWindow = window.open(url, windowName, '', '');
        }

        if(!popupWindow){
            // @TODO  팝업 해제 방법  메뉴얼 필요.
//            alert("팝업을 해제해 주세요.");
            alert("※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는\n화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다.\n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
        }else{
            try {popupWindow.focus(); } catch(e){}
        }
        
        return popupWindow;
    },
    /**
     * 팝업창을 닫는다. 
     * - 팝업창에서 사용.  
     * 
     * @use windowUtil.close();
     * @returns {Boolean}
     */
    close : function() {
        window.open('', '_self', '');
        window.close();
        return false;
    }        
};

/**
 * 문자열 관련
 */
var stringUtil = {
    /**
     * NULL인지 체크
     * @param str
     * @returns {Boolean} true : null
     */
    isNull : function(str)    {
        var bRtn = false;
        if (str == undefined || str == null || str == 'null' || str.toString().replace(/ /g,"") == ""){
            bRtn =  true;
        }
        return bRtn;
    },
    /**
     * MessageResource문자열에서 개행 문자 제거 
     * @param str   문자열
     * @returns
     */
    replaceNewLine : function(str){
        return str.replace(/\\n/g,'\n');
    },
    replaceNewLineBrTag : function(str){
        return str.replace(/\n/g,'<br/>');
    },
    /**
     * 
     * 날자 형식으로 리턴
     * ref) var param = stringUtil.makeDateFormat(20140101);  // 2014.01.01
     * @param YYYYMMDD
     * @returns YYYY.MM.DD
     */
    makeDateFormat : function(strDate){
        
        var delimiter = "-";    //날짜 형식 구분자
        
        if( isNaN(strDate) || strDate == null ){
            strDate = "";
        }
        
        var size = strDate.length;
        if(size >= 6 ){
            strDate = strDate.substring(0,4) + delimiter + strDate.substring(4,6) + delimiter + strDate.substring(6);
        }else if(size >= 4 ){
            strDate = strDate.substring(0,4) + delimiter + strDate.substring(4);
        }
        
        return strDate;
    }
};


/**
 * 숫자 관련 처리 
 */
var numUtil = {
    /**
     * 콤마 제거
     * 
     * @use numUtil.removeComma(num);
     * @param num
     * @returns
     */
    removeComma : function(num) {
        num = new String(num);
        return num.replace(/,/gi,"");
    },
    /**
     * 숫자에 자릿수 표시
     * 
     * @use numUtil.createComma(num);
     * @param num
     * @returns
     */
    createComma : function(num) {
        num = numUtil.removeComma(num);

        if (isNaN(num) || num == 0){
            return 0;
        }
    
        var sign = "";
    
        if (num < 0) {
            num = num * (-1);
            sign = "-";
        } else {
            num = num * 1;
        }
    
        var reg = /(^[+-]?\d+)(\d{3})/;
        num += '';
    
        while (reg.test(num))
            num = num.replace(reg, '$1' + ',' + '$2');
    
        return sign + num ;
    }        
};


var dateUtil = {
    /**
     * 날짜 형식 구분자
     */
    delimiter : "-",
    /**
     * 요청에 따른 날짜리턴
     * 아무값이 없으면 오늘 날짜
     * @param sdate 특정 날자를 넘길 경우.
     * @param dateFormat 요청 데이터 형식
     * @param split
     * @returns
     */
    getDate : function( sdate, dateFormat) {
        var date;
        
        if( sdate == undefined || sdate == "" || sdate == null){
            date = new Date();
        }else{
            date = new Date(sdate);
        }
        
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();

        if (("" + month).length == 1) { month = "0" + month; }
        if (("" + day).length  == 1) { day  = "0" + day;  }

        if(dateFormat=="yyyy"){
            return year;
        }else if(dateFormat=="mm"){
            return month ;
        }else if(dateFormat=="dd"){
            return day;
        }else if(dateFormat=="yyyymm"){
            return year + this.delimiter + month;
        }else if(dateFormat=="mmdd"){
            return month + this.delimiter + day;
        }else{
            return  year + this.delimiter + month + this.delimiter + day;
        }
    },
    /**
     * 월의 마지막 날짜 구하기
     * @param year 년도
     * @param month 월
     * @returns
     */
    getMonthLastDay : function(year, month) {
        // 월별 마지막 날짜
        var arrLastDay = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

		debugger;
        // 윤년인 경우 2월의 마지막은 29일
        if((0 == year%4 && 0 != year%100) || 0 == year%400) {
            arrLastDay[1]=29;
        }
        
        return arrLastDay[month-1];
    },
    /**
     * 년월일(YYYYMMDD)의 유효성을 체크    
     * ref : dateUtil.isDateFormat(yyyymmdd)
     * @param ymd
     * @returns {Boolean} ture : OK
     */
    isDateFormat : function(ymd) {
        ymd = ymd.replace(/[\-]/gi,"");
        
        if( isNaN(ymd) || ymd==null ){
            return false;
        }else{
            var year = ymd.substring(0, 4);
            var month = parseInt(ymd.substring(4, 6), 10) -1;
            var day = parseInt(ymd.substring(6), 10);
            var endDay = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

            if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
                endDay[1] = 29;
            }
            
            return (day >= 1 && day <= endDay[month]);
        }
    },
    /**
     * 입력시간의 유효성을 체크
     * ref : dateUtil.isTimeFormat('HHMM')
     * @param hhmm  시간
     * @returns {Boolean}
     */
    isTimeFormat : function(hhmm){
        var bRtn = true;
       
        hhmm = hhmm.replace(/[-:]/gi,"");
        
        if( isNaN(hhmm) || hhmm == null ||  hhmm.length != 4){
            bRtn = false;
        }else{
            if(parseInt(hhmm.substr(0, 2)) > 23){
                bRtn = false;
            }
            
            if(parseInt(hhmm.substr(2, 2)) > 59){
                bRtn = false;
            }            
        }
        
        if(!bRtn){
            alert('시간범위가 아닙니다. 최대 23시 59분까지 입니다.');
        }
        
        return bRtn;
    },
   /**
    * 입력시간의 유효성을 체크
    * - 시작시간과 종료시간의 범위검사 추가
    * ref : dateUtil.isTimeFormat('HHMM', 'HHMM')
    * @param hhmm   시작시간
    * @param hhmm2  종료시간
    * @returns {Boolean}
    */
    isTimeFormat2 : function(hhmm, hhmm2){
        var bRtn = true;
        
        hhmm = hhmm.replace(/[-:]/gi,"");
        hhmm2 = hhmm2.replace(/[-:]/gi,"");
        
        if(dateUtil.isTimeFormat(hhmm) && dateUtil.isTimeFormat(hhmm2)){
            if(parseInt(hhmm) > parseInt(hhmm2)){
                bRtn = false;
                alert('시작시간이 종료시간보다 늦습니다. 다시 확인해 주세요.');
            }
        }else{
            bRtn = false;
        }
        
        return bRtn;
    },
    /**
     * 날짜 차이를 일로계산 한다    
     * ref : dateUtil.diffDate('2002/02/01','2003/03/01')
     * @param fromDate
     * @param toDate
     * @returns
     */
    diffDate : function( fromDate, toDate) {
        // 값이 없는 경우 0일을 리턴한다.(조회 최소기간:하루)
        if(stringUtil.isNull(fromDate) || stringUtil.isNull(toDate)){
            return "0";
        }

        var MinMilli = 1000 * 60;
        var HrMilli = MinMilli * 60;
        var DyMilli = HrMilli * 24;

        //var d1 = new Date(stringUtil.replaceAll(fromDate, ".", "/"));
        //var d2 = new Date(stringUtil.replaceAll(toDate, ".", "/"));
        // 날자 표현형식 통일 : yyyy/mm/dd
        var d1 = new Date(fromDate);
        var d2 = new Date(toDate);

        var d3 = d2-d1;
        var str = d3 /DyMilli ;

        return str;
    },
    /**
     * 특정 날짜에 대해 지정한 값만큼 가감(+-)한 날짜를 반환
     * ref : dateUtil.addDate(aType, aDay, aDate, split)
     *       20130304 로부터 2달뒤 ==> dateUtil.addDate("m", 2, "20130304", "/");
     * @param aType 가감타입 : y(연도), m(월),  d(일), md(월가감 날짜까지 표현)
     * @param aDay 가감일
     * @param aDate 가감기준일
     * @returns {String}
     */
    addDate : function (aType, aDay, aDate){
        var yyyy;
        var mm;
        var dd;
        var cDate;
        var cYear, cMonth, cDay;
        
        if(aDate == undefined){
            aDate = dateUtil.getDate();
        }

        aDate = aDate.replace(/[\-]/gi,"");

        yyyy = aDate.substr(0, 4);
        mm  = aDate.substr(4, 2);
        dd  = aDate.substr(6, 2);

        if (aType == "y") {
            yyyy = (yyyy * 1) + (aDay * 1);
        } else if (aType == "m" || aType == "md") {
            mm  = (mm * 1) + (aDay * 1);
        } else if (aType == "d") {
            dd  = (dd * 1) + (aDay * 1);
            if (aDay < 0) dd++;
            else  dd--;
        }

        cDate = new Date(yyyy, mm - 1, dd); // 12월, 31일을 초과하는 입력값에 대해 자동으로 계산된 날짜가 만들어짐.
        cYear = cDate.getFullYear();
        cMonth = cDate.getMonth() + 1;
        cDay = cDate.getDate();

        cMonth = cMonth < 10 ? "0" + cMonth : cMonth;
        cDay = cDay < 10 ? "0" + cDay : cDay;


        if (aType == "m"){
            return cYear + this.delimiter + cMonth;
        }else{
            return cYear + this.delimiter + cMonth + this.delimiter + cDay;
        }
    }    
};

/**
 * 모달 팝업 처리
 * 
 * 공통으로 모달을 열고 닫을때 사용한다.
 * 모달의 상세한 설정은 각 페이지 에 modalUtil을 생성해서 사용한다.
 * ref.) /glovis-support-system-user/src/main/webapp/WEB-INF/jsp/sq/qsg/qsgaMan.jsp
 *
 * 
 * ***주의사항
 * 팝업과 모달의 코딩을 같이 하고 있기 때문에 Modal사용시 Class에 'modal_wrap'을 추가 표기해줄것.
 * 하나의 페이지에서 여러개의 모달을 싸용해야 한다면 class명을 modal_wrap과 modal-{식별아이이}을 선언해줄것.
 * ref.) 퍼블리싱 : <div id="pop_wrap" class="pop_wrap">
 *       모달적용 : <div class="modal_wrap modal-{식별아이이}">
 * 
 * //이벤트를 등록해준다.
 * //.btn_modal_open 공통으로 적용을 해두었음.
 * //기본적으로 하나의 모달을 사용한다면 모달 오픈 버튼 이름을 아래로 변경해 줄것.
 * //여러개의 모달을 사용한다면 각 모달 버튼에 대한 이벤트를 별도로 주어서 처리해야한다.(공통모듈 참고할것.)
 * $('.btn_modal_open').on({
 *     click : function(event){
 *         event.preventDefault();
 *         modalUtil.open($(this));
 *     } 
 * });
 * 
 * //모달 열고 닫기위한 페이지별 함수를 아래와 같이 공통으로 생성한다.
 * //모달 열고 닫기 전에 처리해야하는 로직을 아래에 추가한다.
 * var modalUtil = {
 *     open : function(object){
 *          //모달 오픈전 처리해야할 로직 추가
 *          
 *          //모달 열기         
 *          모달이 하나일때 : modalComUtil.open();  *          
 *          모달리 여러개일때 : modalComUtil.open('cont'); //modal-cont로 선언시
 *     },    
 *     close : function(object){
 *          //모달 닫기전 처리해야할 로직 추가
 *          
 *          //모달 닫기
 *          modalComUtil.close();
 *     }
 * };
 */
var modalComUtil = {
    /**
     * 모달 팝업 열기
     * 
     * @use modalComUtil.open();
     *      -> modal_box의 컨텐츠 영역이 하나뿐일때 사용 
     *      modalComUtil.open('new_cont');
     *      -> modal_box의 컨텐츠 영역이 여러개 일때 보여줄 영역의 Class명을 입력한다. 
     * @param 모달 팝업 열기
     */
    open : function(pContentClassName){
        
        if(pContentClassName == undefined){
            pContentClassName = '_wrap';
        }else{
            pContentClassName = '-' + pContentClassName;
        }
        
        $('.modal_wrap').hide();
        $('#mask').show();  
        
        //글 갯수에 따른 높이값 계산 후 화면 중앙 위치
        //this.resize();

        $('#ifr').show();
        $('#mask').fadeTo("slow", 0.5);    
        $('.modal'+pContentClassName).show();
    },    
    /**
     * 모달 팝업 닫기 
     * @use modalUtil.close();
     */
    close : function(){
        $('#ifr, #mask, .modal_wrap').hide();
    },
    /**
     * 윈도우 크기변환시 실행
     */
    resize : function(){
        var left = ( $(window).scrollLeft() + ($(window).width() - $('.modal_wrap').width()) / 2 );
        var top =  ((document.documentElement.clientHeight / 2) - ( $('.modal_wrap').height() / 2)) - 20;
        $('.modal_wrap').css({'top': top+'px', 'left':left + 'px', 'position':'fixed'});
        $('#ifr, #mask').css({'width':$(window).width(),'height':$(document).height()}); 
    }
};


/**
 * 공통 함수 :: 이벤트 아님
 */
var comUtil = {
    
};

/**
 * 쿠키관련 함수
 */
var cookieUtil = {
    /**
     * 쿠키 확인 
     * @param name  cookie name
     * @returns
     */    
    getCookie : function(name){
        var nameOfCookie = name+"=";
        var x = 0;
        while ( x <= document.cookie.length )
        {
                var y = (x+nameOfCookie.length);
                if ( document.cookie.substring( x, y ) == nameOfCookie ) {
                        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
                                endOfCookie = document.cookie.length;
                        return unescape( document.cookie.substring( y, endOfCookie ) );
                }
                x = document.cookie.indexOf( " ", x ) + 1;
                if ( x == 0 )
                        break;
        }
        return null;
    },
    /**
     * 쿠키 설정
     * - day가 0이거나 undefined면 해당 쿠키가 삭제된다.
     * @param name cookie name
     * @param value cookie value
     * @param day   expires day
     */
    setCookie : function(name, value, day){
        if(typeof day == 'undefined'){
            day = 0;
        }        
        var expire = new Date();
        expire.setDate(expire.getDate() + day);
        document.cookie = name + '=' + escape(value) + '; path=/ ' + ';expires=' + expire.toGMTString() + ';';
    }
};

/**
*
*  Base64 encode / decode
*  http://www.webtoolkit.info/
*
**/
var Base64 = {
 
    // private property
    _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
 
    // public method for encoding
    encode : function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
 
        input = Base64._utf8_encode(input);
 
        while (i < input.length) {
 
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
 
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
 
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
 
            output = output +
            this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
            this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
 
        }
 
        return output;
    },
 
    // public method for decoding
    decode : function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
 
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
 
        while (i < input.length) {
 
            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));
 
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
 
            output = output + String.fromCharCode(chr1);
 
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
 
        }
 
        output = Base64._utf8_decode(output);
 
        return output;
 
    },
 
    // private method for UTF-8 encoding
    _utf8_encode : function (string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";
 
        for (var n = 0; n < string.length; n++) {
 
            var c = string.charCodeAt(n);
 
            if (c < 128) {
                utftext += String.fromCharCode(c);
            }
            else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            }
            else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }
 
        }
 
        return utftext;
    },
 
    // private method for UTF-8 decoding
    _utf8_decode : function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
 
        while ( i < utftext.length ) {
 
            c = utftext.charCodeAt(i);
 
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            }
            else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }
            else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
 
        }
 
        return string;
    }
};

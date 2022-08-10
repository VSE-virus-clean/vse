/**
 * FORM 객체 유효성및 이벤트처리 관련 스크립트
 * 
 * Object       : jquery-submit.js
 * Description  : FORM 객체 유효성및 이벤트처리 관련 스크립트 
 * Author       : LaheeDad
 * Since        : 2013.9.10.
 * Version      : 1.0
 * 
 * Modification Information
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013.9.10.     LaheeDad         최초 생성
 */


/**
 * 문자열의 왼쪽에서 count 만큼의 문자열을 반환
 * @param   count
 * @returns string
 */
String.prototype.left = function(count){
    return this.substr(0, count);
};

/**
 * 문자열의 오른쪽에서 count 만큼의 문자열을 반환
 * @param   count
 * @returns string
 */
String.prototype.right = function(count){
    return this.substr(this.length-count, count);
};


/**
 * From 유효성 확인을 위한 객체
 */
var submitUtil = {
    /**
     * SUBMIT 비활성화
     * @use submitUtil.disabled();
     */
    disabled : function() {
        $('input[type=submit], a.submit, button[type=submit]').prop('disabled', true);
    },
    /**
     * SUBMIT 활성화
     * @use submitUtil.enable();
     */
    enable : function() {     
        $('input[type=submit], a.submit, button[type=submit]').prop('disabled', false);
    },
    /**
     * 앞뒤 공백을 제거한후에 다시 Obejct에 담아준다.
     * @use submitUtil.trim(Object)
     * @param object
     */
    trim : function(object){        
        $(object).val($.trim($(object).val()));   
    },
    /**
     * 입력값 입력유무 확인 
     * - minLength속성이 있으면 최소 문자수도 확인.
     * - text/textarea/file/select 만 가능
     * - select는 ''로 구분함.
     * @use submitUtil.isEmpty(Object, String)
     * @param object
     * @param formName 폼명(Alert Message 표시에 필요함.)     
     * @returns {Boolean} true : 입력값이 있음. / false : 입력값 없음.
     */
    isEmpty : function(object, formName) {
        var str = '';
        try{     
            if(typeof formName == 'undefined'){
                formName = $(object).attr('title') + '을(를) 입력해 주세요.';
            }
            
            switch(object.type){
                case 'text' :
                case 'number' :
                case 'password' :   
                case 'hidden' : 
                case 'textarea' :   
                    this.trim(object);  
                    str = $(object).val();
                    break;
                case 'file' :
                    str = $(object).val();
                    break;
                case 'select-one' :                    
                    str = $(object).val(); //$('select[name=' + $(object).attr('name') + '] option:selected').val();                    
                    break;
                default : 
                    return this.alertNfocus(object, formName + ' : text / textarea / select / file 만 가능합니다.' + object.type);  
            }
            
            if("" == str || null == str){
                return this.alertNfocus(object, formName);                
            }else{
            	
            	if(this.isMaxLength(object, $(object).attr('title'))){
                	return this.isMinLength(object, $(object).attr('title'));
                }else{
                	return false;
                }
            }
            
        }catch (e) {
            return this.alertNfocus(object, '[isEmpty]Script Error Message :: ' + e);
        }
    },   
    /**
     * 최소문자수 확인
     * @use  submitUtil.isMinLength(Object, String);
     * @param object
     * @param formName 폼명(Alert Message 표시에 필요함.)  
     * @returns
     */
    isMinLength : function(object, formName) {
        try{
            if(typeof formName == 'undefined'){
                formName = $(object).attr('title');
            }
            
            if($(object).attr('minlength') != null){
                if(!($(object).val().length >= $(object).attr('minlength'))){
                	return this.alertNfocus(object, formName + '을(를) '+ $(object).attr('minlength') + '글자 이상 입력해주세요.');   
                } 
            }                
            
            return true;
        }catch (e) {
            return this.alertNfocus(object, '[isEmpty]Script Error Message :: ' + e);
        }
    },
    /**
     * 최대문자수 확인
     * @use  submitUtil.isMaxLength(Object);
     */
    isMaxLength : function(object, formName) {
        try{
            if($(object).attr('maxlength') != null){
                if($(object).val().length > $(object).attr('maxlength')){
                	return this.alertNfocus(object, formName + '을(를) '+ $(object).attr('maxlength') + '글자 이하로 입력해주세요.');   
                } 
            }   
            
            return true;
        }catch (e) {
            return this.alertNfocus(object, '[isEmpty]Script Error Message :: ' + e);
        }
    },
    /**
     * 입력값이 NULL인지 체크
     * @use  submitUtil.isNull(Object); 
     * @param object
     * @returns true : null
     */
    isNull : function(object) {
        try{     
            var str = $(object).val();
            if("0" == str || "" == str || null == str || 'null' === str || str.toString().replace(/ /g,"") == ""){
                return true;
            }else{
                this.trim(object);
            }
            
            return false;
        }catch (e) {
            //return this.alertNfocus(object, '[isNull]Script Error Message :: ' + e);
            return false;
        }
    },
    /**
     * 입력값에 공백이 있는지 체크
     * @use  submitUtil.isBlank(Object); 
     * @param object
     * @returns {Boolean}
     */
    isBlank : function(object) {
    	var pattern =  /\s/g;
    	var isPattern = true;
    	
    	try{
    		isPattern = pattern.test($(object).val());
    	}catch (e) {
    		isPattern = true;
    	}
    	return isPattern;
    },
    /**
     * ID 확인
     * @use  submitUtil.isID(Object); 
     * @param object
     * @returns {Boolean}
     */
    isID : function(object, message) {
    	//var pattern = /^.*(?=.{3,10})(?=.*[a-zA-Z])(?=.*[0-9]).*$/gi;
        //var pattern = /^.*(?=.{3,10})(?=.*[a-zA-Z0-9]).*$/gi;
        var pattern = /^[a-zA-Z0-9]{4,20}$/;
        try{
            this.trim(object);
            
            if(typeof message == 'undefined'){
            	message = "숫자, 영어를 포함한 4 자 이상 20 자 이하로 입력 해 세오.";
            }
            
            if (!pattern.test($(object).val()) || this.isBlank(object)) {
                return this.alertNfocus(object, message); 
            }else {
                return true;
            }
        }catch (e) {
            return this.alertNfocus(object, message); 
        }
    },
    /**
     * 비밀번호 체크 
     * 영문 대/소문자, 특수문자, 숫자를 조합하여 최소 8자리
     * @use  submitUtil.isPassword(Object); 
     * @param object
     * @returns {Boolean}
     */
    isPassword : function(object, message) {
        var pattern = /^.*(?=.{12,30})(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*\(\)\-_+=]).*$/gi;
        var pattern2 = /((?=.*[a-zA-Z]{3,})|(?=.*[0-9]{3,})|(?=.*[!@#$%^&*\(\)\-_+=]{3,}))/;
        var pattern3 = /^.*(?=.{8,20})(?=.*[a-zA-Z])(?=.*[0-9]).*$/gi;
        var pattern4 =  /^[a-zA-Z0-9!@#$%^&*\(\)\-_+=]{6,10}$/;
        try{
            this.trim(object);

            var str = $(object).val();
            
            /*
            if(typeof formName == 'undefined'){
                formName = $(object).attr('title');
            }
            
            if(str == ''){
                return this.alertNfocus(object, formName+'를 입력해 주십시오.'); 
            }*/
            
//            if(typeof message == 'undefined'){
                message =  "문자, 숫자를 조합하여 6자 이상 비밀번호를 등록해주세요.";
//            }
            
            var patternYn = pattern4.test(str);
            
            if(patternYn && !this.isBlank(object)){
                return true;
            }else{
                return this.alertNfocus(object, message);
            }
        }catch (e) {
            return this.alertNfocus(object, '[isPassword]Script Error Message :: ' + e);
        }
    },
    /**
     *  E-Mail 주소 확인
     * @use  submitUtil.isEmail(Object);
     * @param object
     * @returns {Boolean}
     */
    isEmail : function(object, message) {
        var pattern1 = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)/;
        var pattern2 = /^[a-zA-Z0-9\-\.\_]+\@[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,4})$/;
        
        try{
        	if(typeof message == 'undefined'){
                message = "이메일을 확인해 주세요.";
            }
        	
            this.trim(object);
            if(!pattern1.test($(object).val()) && !pattern2.test($(object).val())){
                return this.alertNfocus(object, message); 
            }else{
                return true;
            }            
        }catch (e) {
            return this.alertNfocus(object, '[isEmail]Script Error Message :: ' + e);
        }
    },
    /**
     * checkbox,radio checked 확인
     *
     * @use  submitUtil.isChecked(object);
     * @param object : 시작일 form.input객체
     * @param formName 폼명(Alert Message 표시에 필요함.)
     * @return true / false
     */
    isChecked : function(object, formName){
        var bRtn = true;

        try{
            if(!$('input[name="'+$(object).attr("name")+'"]').is(':checked')){
            	
                if(typeof formName == 'undefined'){
                    formName = $(object).attr('title') + '를 선택해 주세요.';
                }
                
                return this.alertNfocus(object, formName);          
            }
        }catch (e) {
            return this.alertNfocus(object, '[isChecked]Script Error Message :: ' + e);
        }

        return bRtn;
    },
    /**
     * 날자 형식여부 체크 
     * ref.)  submitUtil.isDateFormat(formId)
     * @param f_Obj
     * @param f_title
     * @returns
     */
    isDateFormat: function(object) {
        if(!dateUtil.isDateFormat(object.value)){
            return this.alertNfocus(object, $(object).attr('title') + '가 잘못 입력되었습니다.\n다시 입력해주세요.');
        }
        
        return true;
    },
    /**
     * 입력 날짜 유효성 체크
     * - 입력 날짜가 유효한 날짜인지를 확인 한다.
     * @use  submitUtil.isDate(Object);
     * @param object : form.input객체
     * @return true / false
     */
    isDate : function(object){
        alert(' submitUtil.isDateFormat(formId) 사용하세요.');
        return false;
    },
    /**
     * 입력 날짜 유효성 체크
     * - 시작일과 만료일을 확인
     * - 시작일이 만료일보다 크면 flase
     *
     * @use  submitUtil.isDateCompare(Object, Object);
     * @param oStart : 시작일 form.input객체
     * @param oEnd : 만료일 form.input객체
     * @return true / false
     */
    isDateCompare : function(oStart, oEnd){
        var bRtn = true;

        try{
            this.trim(oStart);
            this.trim(oEnd);
            
            var valStart = $.trim($(oStart).val()).replace(/[-]/g,'');
            var valEnd = $.trim($(oEnd).val()).replace(/[-]/g,'');

           if(!(valStart <= valEnd)){
               bRtn = this.alertNfocus(oStart, '선택한 기간이 올바르지 않습니다.\n다시 선택해 주십시오.'); 
           }

        }catch (e) {
            return this.alertNfocus(oEnd, '[isDateCompare]Script Error Message :: ' + e);
        }

        return bRtn;
    },
    /**
     * 입력 날짜 / 시간 유효성 체크
     * - 시작일과 만료일을 확인
     * - 시작일이 만료일보다 크면 flase
     * - 시작일과 종료일이 같다면 하루일정일수도 있음. 시간이 입력되었는지 확인 할것.
     * - 시작일과 종료일이 같다면 시작시간과 종료시간이 같을수는 없음.
     *
     * @use  submitUtil.isDateCompare(Object, Object);
     * @param oStart : 시작일 form.input객체
     * @param oEnd : 만료일 form.input객체
     * @return true / false
     */
    isDateTimeCompare : function(oStart, oEnd){
        var bRtn = true;

        try{
            this.trim(oStart);
            this.trim(oEnd);
            
            var arrayStart = $.trim($(oStart).val()).replace(/[-:]/g,'').split(' ');
            var arrayEnd = $.trim($(oEnd).val()).replace(/[-:]/g,'').split(' ');
            
            if(!(arrayStart[0] <= arrayEnd[0])){
                bRtn = this.alertNfocus(oEnd, '종료일이 시작일보다 빠를 수  없습니다.\n다시 선택해 주십시오.'); 
            }else if(arrayStart[0] === arrayEnd[0] && arrayStart.length > 1){
                //날짜가 같다면 
                if(arrayStart[1] === arrayEnd[1]){
                    bRtn = this.alertNfocus(oEnd, '시작시간과 종료시간이 같을 수  없습니다.\n다시 선택해 주십시오.');
                }else if(!(arrayStart[1] < arrayEnd[1])){
                    bRtn = this.alertNfocus(oEnd, '종료시간이 시작시간보다 빠를 수  없습니다.\n다시 선택해 주십시오.');
                }
            }
        }catch (e) {
            return this.alertNfocus(oEnd, '[isDateTimeCompare]Script Error Message :: ' + e);
        }

        return bRtn;
    },
    /**
     * 이미지 첨부파일 확인
     * 
     * @use  submitUtil.isAttachFile(Object, {IMG,THUMB,DOC,ALL});
     * @param object
     * @param fileType (IMG:이미지, THUMB:썸네일용이미지, PDF:PDF파일, DOC:일반첨부파일, ALL:전체)
     * @returns {Boolean}
     */
    isAttachFile : function(object, fileType){
        var bRtn = true;        //확인결과
        
        //영문/숫자/-/_만 가능하다.
        //var pattern = /^[a-zA-Z0-9][a-zA-Z0-9\-\_]+\.([a-zA-Z0-9]{3})$/;
        
        //영문대소문자한글/_ /-/. 만 허용한다. 
        var pattern = /^[a-zA-Z0-9가-힣\(\)\[\]][a-zA-Z0-9가-힣\s\(\)\[\]]+\.([a-zA-Z0-9]{3,4})$/;
        
        var fileMaxSize = 524288000;

        try{
            //파일크기
            var fileSize = object.files[0].size;
            
            //파일경로
            var attachFilePath = object.value.toLowerCase();
            
            //파일명
            var attachFileName = attachFilePath.substring(attachFilePath.lastIndexOf("\\")+1); 
            
            //파일 확장자   
            var attachFileType = attachFileName.substring(attachFileName.lastIndexOf(".")+1);
            
            /*
             * 파일크기 확인 
             */
            if(fileMaxSize < fileSize){
                return this.alertNfocus(object, '파일첨부 최대 용량은 200MB입니다.'); 
            }
            
            /*
             * 파일규칙 확인 
             */            
            if (!pattern.test(attachFileName)) {
                return this.alertNfocus(object, $(object).attr('title') + '은(는) 영문 , 숫자, 한글로 된 파일명으로 업로드 해 주십시오.'); 
            }

            /*
             * 확장자 확인
             */
            switch(fileType){
	            case 'CSV' :  if(!attachFileType == 'csv'){
					            bRtn = this.alertNfocus(object,$(object).attr('title') + '은(는) CSV 파일만 업로드 가능합니다.');
					        }
					        break;
                case 'IMG' :  if(!(attachFileType == 'jpg' || attachFileType == 'gif' || attachFileType == 'png')){
                                bRtn = this.alertNfocus(object,$(object).attr('title') + '은(는) 이미지 파일만 업로드 가능합니다.(JPG, GIF, PNG)');    
                            }                
                            break;
				case 'PRO' :  if(!(attachFileType == 'pdf' || attachFileType == 'jpg' || attachFileType == 'gif' || attachFileType == 'png')){
                                bRtn = this.alertNfocus(object,$(object).attr('title') + '은(는) PDF, 이미지 파일만 업로드 가능합니다.(JPG, GIF, PNG)');    
                            }                
                            break;
                case 'PDF' :  if(!(attachFileType == 'pdf')){
			                    bRtn = this.alertNfocus(object,$(object).attr('title') + '은(는) PDF 파일만 업로드 가능합니다. ');    
			                }                
			                break;
                case 'MP4' :  if(!(attachFileType == 'mp4')){
                                bRtn = this.alertNfocus(object,$(object).attr('title') + '은(는) MP4 파일만 업로드 가능합니다.');
                            }
                            break;
                case 'XLS' :  if(!(attachFileType == 'xls' || attachFileType == 'xlsx')){
                                bRtn = this.alertNfocus(object,$(object).attr('title') + '은 XLS, XLSX 파일만 등록 가능합니다.');
                            }
                            break;
                case 'DOC' :  if(!(attachFileType == 'pdf' || attachFileType == 'xls' || attachFileType == 'ppt'
                                    || attachFileType == 'pptx' || attachFileType == 'doc' || attachFileType == 'docx'
                                    || attachFileType == 'hwp' || attachFileType == 'xlsx' || attachFileType == 'zip')){
                                bRtn = this.alertNfocus(object,$(object).attr('title') + '은(는) PDF, PPT, PPTX, DOC, DOCX, HWP, XLS, XLSX, ZIP 파일만 업로드 가능합니다.');
                            }
                            break;
                case 'ALL' :  if(!(attachFileType == 'jpg' || attachFileType == 'gif' || attachFileType == 'png'
                                    || attachFileType == 'pdf' || attachFileType == 'xls' || attachFileType == 'ppt'
                                    || attachFileType == 'pptx' || attachFileType == 'doc'|| attachFileType == 'docx'
                                    || attachFileType == 'hwp' || attachFileType == 'xlsx' || attachFileType == 'zip'
                                    || attachFileType == 'igs' || attachFileType == 'iges' || attachFileType == 'stp' || attachFileType == 'step' )){
                                bRtn = this.alertNfocus(object,$(object).attr('title') + '은(는) JPG, GIF, PNG, PDF, PPT, PPTX, DOC, DOCX, HWP, XLS, XLSX, ZIP, IGS, IGES, STP, STEP 파일만 업로드 가능합니다.');
                            }
                            break;    
            }        
        }catch (e) {
            return this.alertNfocus(object, '[isAttachFile]Script Error Message :: ' + e);
        }
        
        return bRtn;
    },    
    /**
     * 경고창 및 포커싱처리
     * @use submitUtil.alertNfocus(Object, String);
     * @param object
     * @param alertMsg  AlertMessage
     * @returns {Boolean}
     */
    alertNfocus : function(object, alertMsg) {        
        try{
            if(alertMsg != ""){
                alert(alertMsg);   
            }

			$(object).prop("readonly",true);
            $(object).click().focus();

			setTimeout(function(){ 
				$(object).prop("readonly",false).click().focus().select();
			}, 40);
        }catch(e){
            //ignore
        }
        return false;
    },
    /**
     * 문자열 바이트 체크
     * @use submitUtil.getByte(Object)
     * @param object
     * @returns {Number}
     */
    getByte : function(object) {    
        var str = $.trim($(object).val());
        var strByte = 0;
        
        for (var i = 0; i < str.length; i++) {
            var ch = str.charAt(i);
            if (escape(ch).length > 4) {
                strByte += 2;
            } else if (ch != '\r') {
                strByte++;
            }
        }

        return strByte;
    }
};


/**
 * 첨부 파일 관리 
 */
var fileUtil = {
    /**
     * 파일 다운로드
     */
    downloadEvent : function(){
        $('.btn_file_download').live({
            click : function(event){
                event.preventDefault();
                if($(this).attr('fileSn') != undefined){
                    location.href = loginObj.contextPath + "/cm/fileDownMan.vc?attcFilSn=" + $(this).attr('fileSn');
                }
            }
        });  
        
        $('.btn_file_download_pdf').live({
            click : function(event){
                event.preventDefault();
                if($(this).attr('fileSn') != undefined){
                    windowUtil.open(loginObj.contextPath + "/cm/fileDownMan.vc?attcFilSn=" + $(this).attr('fileSn'),  "popPdfViewer", "", "") ;
                }
            }
        });  
    },
    /**
     * 첨부선택한 파일 삭제
     */
    deleteEvent : function(){
        $('.btn_delete_file').live({
            click : function(event){
                event.preventDefault();  

                var $$TD = $(this).parents('td');
                $$TD.find('.f').hide();
                $$TD.find('input[type=hidden][name^="attachFileUseYn"]').val('N');
				$$TD.find('img').remove();
                
//                if($(this).attr('fileSn') != undefined){
//                    if(confirm('삭제하시겠습니까?\n삭제하실 경우 게시물 수정과 상관없이 첨부 이미지(파일)가 삭제됩니다.')){
//                        fileUtil.submitDeleteFileInfo($(this));
//                    }
//                }else{
//                    alert('삭제할 파일 정보가 없습니다.');
//                }
            }        
        });
    },
    /**
     * 첨부파일 삭제 :: 단건
     * @use fileUtil.submitDeleteFileInfo($(Object))
     * @param object
     */
    submitDeleteFileInfo : function(object){
        var data = { attcFilSn : $(object).attr('fileSn'),
                     menuCd : $(object).attr('menuCd'),
                     cotnSn : $(object).attr('cotnSn') };
        
        ajaxUtil.postDisableAsync(loginObj.contextPath + '/cm/fileDelMan.vc', data, fileUtil.resultDeleteFileInfo);
    },
    /**
     * 첨부파일 삭제 결과
     * @use fileUtil.resultDeleteAttachFileInfo({JSON})
     * @param json
     */
    resultDeleteFileInfo : function(json){
        if(json.bindingStatus == undefined && json.result.status){
            var object = $('input[type=hidden][name*="attach"][value='+json.result.seq+']');
            var parent = $(object).parents('td');
            
            //파일정보 영역 삭제
            $(object).val('0');
            $(parent).find('.input-file input[type=file]').prop('disabled', false);
            $(parent).find('.input-file').show();
            $(parent).find('.img-file').hide();
            
            try{
                //대체텍스트 삭제
                $('input[id=desc_'+ $(object).attr('id').split('_')[1] +']').val(''); //.css({'background-color' : '#F5F5F5'});
            }catch(e){}
        }else{
            ajaxUtil.error(json);   
        }
    }
};

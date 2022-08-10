package jksoft.com.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jksoft.com.web.vo.AttachFileVO;
import jksoft.com.web.vo.PageVO;
import jksoft.com.web.vo.SearchVO;

/**
 * <pre>
 * 문자열 데이터 처리 관련 유틸리티
 * 
 * EgovStringUtil를 수정함.
 * </pre>
 * 
 * @ClassName : StringUtil.java
 * @Description : 문자열 데이터 처리 관련 유틸리티
 */
@Component("stringUtil")
public class StringUtil {

    private static Logger log = LoggerFactory.getLogger(StringUtil.class);
    

    /**
     * 빈 문자열 <code>""</code>.
     */
    public static final String EMPTY = "";

    /**
     * 문자열이 NULL이면 빈공백으로
     * 
     * @param str
     * @return ""
     */
    public static String replaceNull(String str) {
        return replaceNull(str, "");
    }

    /**
     * 문자열이 NULL이면 정해진 문자로 변환
     * 
     * @param str
     * @param replace
     * @return replace
     */
    public static String replaceNull(String str, String replace) {
        String strRtn;

        if (str == null || "".equals(str)) {
            strRtn = replace;
        } else {
            strRtn = str;
        }

        return strRtn;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * 
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param length 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int length) {
        String returnVal = null;
        
        if (source != null) {
            if (source.length() > length) {
                returnVal = source.substring(0, length) + output;
            } else
                returnVal = source;
        }
        
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * 
     * @param source
     *            원본 문자열 배열
     * @param slength
     *            지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else
                result = source;
        }
        return result;
    }

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     * 
     * <pre>
     *  StringUtil.isEmpty(null)      = true
     *  StringUtil.isEmpty("")        = true
     *  StringUtil.isEmpty(" ")       = false
     *  StringUtil.isEmpty("bob")     = false
     *  StringUtil.isEmpty("  bob  ") = false
     * </pre>
     * 
     * @param str
     *            - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * <p>
     * 기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     * 
     * @param str
     *            입력받는 기준 문자열
     * @param remove
     *            입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    /**
     * <p>
     * 문자열 내부의 콤마 character(,)를 모두 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     * 
     * @param str
     *            입력받는 기준 문자열
     * @return " , "가 제거된 입력문자열
     *         입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }

    /**
     * <p>
     * 문자열 내부의 마이너스 character(-)를 모두 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     * 
     * @param str
     *            입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *         입력문자열이 null인 경우 출력문자열은 null
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }

    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * 
     * @param source
     *            원본 문자열
     * @param subject
     *            원본 문자열에 포함된 특정 문자열
     * @param object
     *            변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr = source;

        while (srcStr.indexOf(subject) >= 0) {
            preStr = srcStr.substring(0, srcStr.indexOf(subject));
            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }

    /**
     * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
     * 
     * @param source
     *            원본 문자열
     * @param subject
     *            원본 문자열에 포함된 특정 문자열
     * @param object
     *            변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(preStr).append(object).append(nextStr);
            return rtnStr.toString();
        } else {
            return source;
        }
    }

    /**
     * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
     * 
     * @param source
     *            원본 문자열
     * @param subject
     *            원본 문자열에 포함된 특정 문자열
     * @param object
     *            변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceChar(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr = source;

        char chA;

        for (int i = 0; i < subject.length(); i++) {
            chA = subject.charAt(i);

            if (srcStr.indexOf(chA) >= 0) {
                preStr = srcStr.substring(0, srcStr.indexOf(chA));
                nextStr = srcStr.substring(srcStr.indexOf(chA) + 1, srcStr.length());
                srcStr = rtnStr.append(preStr).append(object).append(nextStr).toString();
            }
        }

        return srcStr;
    }

    /**
     * <p>
     * <code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.
     * </p>
     * 
     * <p>
     * 입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.
     * </p>
     * 
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     * 
     * @param str
     *            검색 문자열
     * @param searchStr
     *            검색 대상문자열
     * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이 없거나 null인 경우 -1
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }

    /**
     * <p>
     * 오라클의 decode 함수와 동일한 기능을 가진 메서드이다. <code>sourStr</code>과
     * <code>compareStr</code>의 값이 같으면 <code>returStr</code>을 반환하며, 다르면
     * <code>defaultStr</code>을 반환한다.
     * </p>
     * 
     * <pre>
     * StringUtil.decode(null, null, "foo", "bar")= "foo"
     * StringUtil.decode("", null, "foo", "bar") = "bar"
     * StringUtil.decode(null, "", "foo", "bar") = "bar"
     * StringUtil.decode("하이", "하이", null, "bar") = null
     * StringUtil.decode("하이", "하이  ", "foo", null) = null
     * StringUtil.decode("하이", "하이", "foo", "bar") = "foo"
     * StringUtil.decode("하이", "하이  ", "foo", "bar") = "bar"
     * </pre>
     * 
     * @param sourceStr
     *            비교할 문자열
     * @param compareStr
     *            비교 대상 문자열
     * @param returnStr
     *            sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @param defaultStr
     *            sourceStr와 compareStr의 값이 다를 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며, <br/>
     *         다르면 defaultStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr) {
        if (sourceStr == null && compareStr == null) {
            return returnStr;
        }

        if (sourceStr == null && compareStr != null) {
            return defaultStr;
        }

        if (sourceStr.trim().equals(compareStr)) {
            return returnStr;
        }

        return defaultStr;
    }

    /**
     * <p>
     * 오라클의 decode 함수와 동일한 기능을 가진 메서드이다. <code>sourStr</code>과
     * <code>compareStr</code>의 값이 같으면 <code>returStr</code>을 반환하며, 다르면
     * <code>sourceStr</code>을 반환한다.
     * </p>
     * 
     * <pre>
     * StringUtil.decode(null, null, "foo") = "foo"
     * StringUtil.decode("", null, "foo") = ""
     * StringUtil.decode(null, "", "foo") = null
     * StringUtil.decode("하이", "하이", "foo") = "foo"
     * StringUtil.decode("하이", "하이 ", "foo") = "하이"
     * StringUtil.decode("하이", "바이", "foo") = "하이"
     * </pre>
     * 
     * @param sourceStr
     *            비교할 문자열
     * @param compareStr
     *            비교 대상 문자열
     * @param returnStr
     *            sourceStr와 compareStr의 값이 같을 때 반환할 문자열
     * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며, <br/>
     *         다르면 sourceStr을 반환한다.
     */
    public static String decode(String sourceStr, String compareStr, String returnStr) {
        return decode(sourceStr, compareStr, returnStr, sourceStr);
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * 
     * @param object
     *            원본 객체
     * @return resultVal 문자열
     */
    public static String isNullToString(Object object) {
        String string = "";

        if (object != null) {
            string = object.toString().trim();
        }

        return string;
    }

    /**
     * <pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     * </pre>
     */
    public static String nullConvert(Object src) {
        // if (src != null &&
        // src.getClass().getName().equals("java.math.BigDecimal")) {
        if (src != null && src instanceof java.math.BigDecimal) {
            return ((BigDecimal) src).toString();
        }

        if (src == null || src.equals("null")) {
            return "";
        } else {
            return ((String) src).trim();
        }
    }

    /**
     * <pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     * </pre>
     */
    public static String nullConvert(String src) {

        if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
            return "";
        } else {
            return src.trim();
        }
    }

    /**
     * <pre>
     * 인자로 받은 String이 null일 경우 &quot;0&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;0&quot;로 바꾼 String 값.
     * </pre>
     */
    public static int zeroConvert(Object src) {

        if (src == null || src.equals("null")) {
            return 0;
        } else {
            return Integer.parseInt(((String) src).trim());
        }
    }

    /**
     * <pre>
     * 인자로 받은 String이 null일 경우 &quot;&quot;로 리턴한다.
     * &#064;param src null값일 가능성이 있는 String 값.
     * &#064;return 만약 String이 null 값일 경우 &quot;&quot;로 바꾼 String 값.
     * </pre>
     */
    public static int zeroConvert(String src) {

        if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
            return 0;
        } else {
            return Integer.parseInt(src.trim());
        }
    }

    /**
     * <p>
     * 문자열에서 {@link Character#isWhitespace(char)}에 정의된 모든 공백문자를 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.removeWhitespace(null)         = null
     * StringUtil.removeWhitespace("")           = ""
     * StringUtil.removeWhitespace("abc")        = "abc"
     * StringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     * 
     * @param str
     *            공백문자가 제거도어야 할 문자열
     * @return the 공백문자가 제거된 문자열, null이 입력되면 <code>null</code>이 리턴
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }

        return new String(chs, 0, count);
    }

    /**
     * Html 코드가 들어간 문서를 표시할때 태그에 손상없이 보이기 위한 메서드
     * 
     * @param strString
     * @return HTML 태그를 치환한 문자열
     */
    public static String checkHtmlView(String strString) {
        String strNew = "";

        try {
            StringBuffer strTxt = new StringBuffer("");

            char chrBuff;
            int len = strString.length();

            for (int i = 0; i < len; i++) {
                chrBuff = (char) strString.charAt(i);

                switch (chrBuff) {
                    case '<':
                        strTxt.append("&lt;");
                        break;
                    case '>':
                        strTxt.append("&gt;");
                        break;
                    case '"':
                        strTxt.append("&quot;");
                        break;
                    case 10:
                        strTxt.append("<br>");
                        break;
                    case ' ':
                        strTxt.append("&nbsp;");
                        break;
                    // case '&' :
                    // strTxt.append("&amp;");
                    // break;
                    default:
                        strTxt.append(chrBuff);
                }
            }

            strNew = strTxt.toString();

        } catch (Exception ex) {
            return null;
        }

        return strNew;
    }

    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * 
     * @param source
     *            원본 문자열
     * @param separator
     *            분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);

        return returnVal;
    }

    /**
     * <p>
     * {@link String#toLowerCase()}를 이용하여 소문자로 변환한다.
     * </p>
     * 
     * <pre>
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     * 
     * @param str
     *            소문자로 변환되어야 할 문자열
     * @return 소문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }

    /**
     * <p>
     * {@link String#toUpperCase()}를 이용하여 대문자로 변환한다.
     * </p>
     * 
     * <pre>
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     * 
     * @param str
     *            대문자로 변환되어야 할 문자열
     * @return 대문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toUpperCase();
    }

    /**
     * <p>
     * 입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.stripStart(null, *)          = null
     * StringUtil.stripStart("", *)            = ""
     * StringUtil.stripStart("abc", "")        = "abc"
     * StringUtil.stripStart("abc", null)      = "abc"
     * StringUtil.stripStart("  abc", null)    = "abc"
     * StringUtil.stripStart("abc  ", null)    = "abc  "
     * StringUtil.stripStart(" abc ", null)    = "abc "
     * StringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     * 
     * @param str
     *            지정된 문자가 제거되어야 할 문자열
     * @param stripChars
     *            제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }

        return str.substring(start);
    }

    /**
     * <p>
     * 입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.stripEnd(null, *)          = null
     * StringUtil.stripEnd("", *)            = ""
     * StringUtil.stripEnd("abc", "")        = "abc"
     * StringUtil.stripEnd("abc", null)      = "abc"
     * StringUtil.stripEnd("  abc", null)    = "  abc"
     * StringUtil.stripEnd("abc  ", null)    = "abc"
     * StringUtil.stripEnd(" abc ", null)    = " abc"
     * StringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     * 
     * @param str
     *            지정된 문자가 제거되어야 할 문자열
     * @param stripChars
     *            제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }

        return str.substring(0, end);
    }

    /**
     * <p>
     * 입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.
     * </p>
     * 
     * <pre>
     * StringUtil.strip(null, *)          = null
     * StringUtil.strip("", *)            = ""
     * StringUtil.strip("abc", null)      = "abc"
     * StringUtil.strip("  abc", null)    = "abc"
     * StringUtil.strip("abc  ", null)    = "abc"
     * StringUtil.strip(" abc ", null)    = "abc"
     * StringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     * 
     * @param str
     *            지정된 문자가 제거되어야 할 문자열
     * @param stripChars
     *            제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String strip(String str, String stripChars) {
        if (isEmpty(str)) {
            return str;
        }

        String srcStr = str;
        srcStr = stripStart(srcStr, stripChars);

        return stripEnd(srcStr, stripChars);
    }

    /**
     * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
     * 
     * @param source
     *            원본 문자열
     * @param separator
     *            분리자
     * @param arraylength
     *            배열 길이
     * @return 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
        String[] returnVal = new String[arraylength];
        int cnt = 0;
        int index0 = 0;
        int index = source.indexOf(separator);
        while (index >= 0 && cnt < (arraylength - 1)) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        if (cnt < (arraylength - 1)) {
            for (int i = cnt + 1; i < arraylength; i++) {
                returnVal[i] = "";
            }
        }

        return returnVal;
    }

    /**
     * 문자열을 다양한 문자셋(EUC-KR[KSC5601],UTF-8..)을 사용하여 인코딩하는 기능 역으로 디코딩하여 원래의 문자열을
     * 복원하는 기능을 제공함 String temp = new String(문자열.getBytes("바꾸기전 인코딩"),"바꿀 인코딩");
     * String temp = new String(문자열.getBytes("8859_1"),"KSC5601"); => UTF-8 에서
     * EUC-KR
     * 
     * @param srcString
     *            - 문자열
     * @param srcCharsetNm
     *            - 원래 CharsetNm
     * @param charsetNm
     *            - CharsetNm
     * @return 인(디)코딩 문자열
     * @exception MyException
     * @see
     */
    public static String getEncdDcd(String srcString, String srcCharsetNm, String cnvrCharsetNm) {

        String rtnStr = null;

        if (srcString == null) {
            return null;
        }

        try {
            rtnStr = new String(srcString.getBytes(srcCharsetNm), cnvrCharsetNm);
        } catch (UnsupportedEncodingException e) {
            rtnStr = null;
        }

        return rtnStr;
    }

    /**
     * <p>
     * 날짜 형식의 문자열 내부에 마이너스 character(-)를 추가한다.
     * </p>
     * 
     * <pre>
     * StringUtil.addMinusChar(&quot;20100901&quot;) = &quot;2010-09-01&quot;
     * </pre>
     * 
     * @param date
     *            입력받는 문자열
     * @return " - "가 추가된 입력문자열
     */
    public static String addMinusChar(String date) {
        if (date.length() == 8)
            return date.substring(0, 4).concat("-").concat(date.substring(4, 6)).concat("-")
                    .concat(date.substring(6, 8));
        else
            return "";
    }
    
    /**
     * 핸드폰 번호 - 추가하기
     * 
     * Ref) StringUtil.addMinusCharHp(010 1234 5678)
     * @param date
     * @return
     */
    public static String addMinusCharHp(String hp) {
    	
    	String rtnStr = "";
    			
        if(hp.length() == 11){
        	rtnStr = hp.substring(0, 3).concat("-").concat(hp.substring(3, 7)).concat("-").concat(hp.substring(7, 11));
        }else if(hp.length() == 10){
        	rtnStr =  hp.substring(0, 3).concat("-").concat(hp.substring(3, 6)).concat("-").concat(hp.substring(6, 10));
        }else {
        	rtnStr = hp;
        }
        
        return rtnStr;
    }

    /**
     * 임시비밀번호 생성
     * - 영문 대/소문자 + 숫자 +특수문자 조합 10자리
     * 조합 패턴 체크후 재생성
     */
    public static String randomString() {

        String regex = "^.*(?=.{8,10})(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@$%^*\\-_+]).*$";
        // String regex =
        // "^.*(?=.{8,10})(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!,@,#,$,%,^,*,_,+,-,=,~])";
        String stringRandom = stringRandom();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringRandom);
        if (!matcher.find()) {
            randomString();
        }
        return stringRandom;
    }
    
    /**
     * 임시비밀번호 생성
     * - 영문 대/소문자 + 숫자 +특수문자 조합 10자리
     * - Fortify 수정사항
     * Math.random() 대신 Random() 사용할것.
     * 
     * @return 임시비밀번호
     */
    public static String stringRandom() {
        boolean bStatus = true;

        StringBuffer stringBuffer = new StringBuffer();

        /*
         * 해당 특수문자 제외 생성
         * -> " ' , . / : ; < > ? ` ( ) &
         */
        int[] noChar = { 34, 39, 44, 46, 47, 58, 59, 60, 62, 63, 96, 40, 41, 70 };

        // 랜덤 숫자 생성
        // Random randomGen = new Random((new Date()).getTime());
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            bStatus = true;

            int in = (int) (random.nextFloat() * (126 - 33 + 1)) + 33;

            // 특수 문자 확인
            for (int j = 0; j < noChar.length; j++) {
                if (in == noChar[j]) {
                    bStatus = false;
                    break;
                }
            }
            if (bStatus) {
                stringBuffer.append((char) in);
            } else {
                i--;
            }
        }

        return stringBuffer.toString();
    }
    
    /**
     * 임시비밀번호 생성
     * - 영문 대/소문자 + 숫자 +특수문자 조합 10자리
     * 조합 패턴 체크후 재생성
     */
    public static String randomString2() {

        String regex = "^.*(?=.{8,10})(?=.*[a-zA-Z])(?=.*[0-9]).*$";
        String stringRandom = stringRandom2();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringRandom);
        if (!matcher.find()) {
            randomString();
        }
        return stringRandom;
    }

    /**
     * 임시비밀번호 생성
     * - 영문 대/소문자 + 숫자 +특수문자 조합 10자리
     * - Fortify 수정사항
     * Math.random() 대신 Random() 사용할것.
     * 
     * @return 임시비밀번호
     */
    public static String stringRandom2() {
        boolean bStatus = true;

        StringBuffer stringBuffer = new StringBuffer();

        /*
         * 해당 특수문자 제외 생성
         * -> " ' , . / : ; < > ? ` ( ) &
         * !@$%^*\\-_+
         */
        int[] noChar = { 33, 34, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 92, 93, 94, 95, 96 };

        // 랜덤 숫자 생성
        // Random randomGen = new Random((new Date()).getTime());
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            bStatus = true;

            int in = (int) (random.nextFloat() * (126 - 33 + 1)) + 33;

            // 특수 문자 확인
            for (int j = 0; j < noChar.length; j++) {
                if (in == noChar[j]) {
                    bStatus = false;
                    break;
                }
            }
            if (bStatus) {
                stringBuffer.append((char) in);
            } else {
                i--;
            }
        }

        return stringBuffer.toString();
    }
    
    
    /**
     * 램덤 숫자
     * - 중복허용
     * @param iCount	자릿수
     * @return
     */
    public static String randomInt(int iCount) {

        StringBuffer randomString = new StringBuffer();

        Random random = new Random();

        for (int i = 0; i < iCount; i++) {
        	randomString.append(Integer.toString(random.nextInt(10)));
        	
        }

        return randomString.toString();
    }


    public static String requestReplace(String param, String gubun) {
        String result = "";
        String paramValue = param;
        if (paramValue != null) {

            paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

            paramValue = paramValue.replaceAll("\\*", "");
            paramValue = paramValue.replaceAll("\\?", "");
            paramValue = paramValue.replaceAll("\\[", "");
            paramValue = paramValue.replaceAll("\\{", "");
            paramValue = paramValue.replaceAll("\\(", "");
            paramValue = paramValue.replaceAll("\\)", "");
            paramValue = paramValue.replaceAll("\\^", "");
            paramValue = paramValue.replaceAll("\\$", "");
            paramValue = paramValue.replaceAll("'", "");
            paramValue = paramValue.replaceAll("@", "");
            paramValue = paramValue.replaceAll("%", "");
            paramValue = paramValue.replaceAll(";", "");
            paramValue = paramValue.replaceAll(":", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll("#", "");
            paramValue = paramValue.replaceAll("--", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll(",", "");

            if (gubun != "encodeData") {
                paramValue = paramValue.replaceAll("\\+", "");
                paramValue = paramValue.replaceAll("/", "");
                paramValue = paramValue.replaceAll("=", "");
            }

            result = paramValue;

        }
        return result;
    }

    public static String replaceFilter(String parameter) {
        String paramValue = parameter;

        paramValue = paramValue.replaceAll("&lt;", "<");
        paramValue = paramValue.replaceAll("&gt;", ">");
        paramValue = paramValue.replaceAll("&amp;", "&");
        paramValue = paramValue.replaceAll("&quot;", "\"");
        paramValue = paramValue.replaceAll("&#39;", "\\");

        return paramValue;
    }

    /**
     * RPAD구현
     * 
     * @param str 변경문자열
     * @param length 변경길이
     * @param fillChar 채워질문자
     * @return
     */
    public static String rpad(String str, int length, char fillChar) {
        String strRtn = "";

        if (str.length() > length) {
            strRtn = str;
        } else {
            char[] chars = new char[length];
            Arrays.fill(chars, fillChar);
            System.arraycopy(str.toCharArray(), 0, chars, 0, str.length());
            strRtn = new String(chars);
        }

        return strRtn;
    }

    /**
     * LPAD구현
     * 
     * @param str 변경문자열
     * @param length 변경길이
     * @param fillChar 채워질문자
     * @return
     */
    public static String lpad(String str, int length, char fillChar) {
        String strRtn = "";

        if (str.length() > length) {
            strRtn = str;
        } else {
            char[] chars = new char[length];
            Arrays.fill(chars, fillChar);
            System.arraycopy(str.toCharArray(), 0, chars, length - str.length(), str.length());
            strRtn = new String(chars);
        }

        return strRtn;
    }

    /**
     * Html 검색 Query String 생성
     * 
     * @param object VO객체
     * @return
     */
    public static String createSearchQueryString(Object object) {
        return createSearchQueryString(object, false, false);
    }

    /**
     * Html 검색 Query String 생성 : paging
     * 
     * @param object VO객체
     * @return
     */
    public static String createSearchQueryStringByPaging(Object object) {
        return createSearchQueryString(object, true, false);
    }
    
    /**
     * Html 검색 Query String 생성 : paging
     * 
     * @param object VO객체
     * @return
     */
    public static String createSearchQueryStringByPaging1(Object object) {
        return createSearchQueryString(object, false, true);
    }

    /**
     * Html 검색 Query String 생성
     * 
     * @param object VO객체
     * @param bPaging 페이징 여부
     * @return
     */
    public static String createSearchQueryString(Object object, Boolean bPaging, Boolean bPaging1) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            SearchVO searchVO = (SearchVO) object;
            
            // 검색타입
            if (!(searchVO.getSearchType() == null || searchVO.getSearchType() == "")) {
                stringBuilder.append("&searchType=").append(URLEncoder.encode(searchVO.getSearchType(), "UTF-8"));
            }

            // 검색타입
            if (!(searchVO.getSearchSubType() == null || searchVO.getSearchSubType() == "")) {
                stringBuilder.append("&searchSubType=").append(
                        URLEncoder.encode(searchVO.getSearchSubType(), "UTF-8"));
            }

            // 검색어
            if (!(searchVO.getSearchKey() == null || searchVO.getSearchKey() == "")) {
                stringBuilder.append("&searchKey=").append(URLEncoder.encode(searchVO.getSearchKey(), "UTF-8"));
            }
            
            // 구분 검색타입
            if (!(searchVO.getSearchGubunType() == null || searchVO.getSearchGubunType() == "")) {
                stringBuilder.append("&searchGubunType=").append(
                        URLEncoder.encode(searchVO.getSearchGubunType(), "UTF-8"));
            }
            
            // 구분 검색타입
            if (!(searchVO.getSearchGubunType2() == null || searchVO.getSearchGubunType2() == "")) {
                stringBuilder.append("&searchGubunType2=").append(
                        URLEncoder.encode(searchVO.getSearchGubunType2(), "UTF-8"));
            }
            
            // 구분 검색타입
            if (!(searchVO.getSearchGubunType3() == null || searchVO.getSearchGubunType3() == "")) {
                stringBuilder.append("&searchGubunType3=").append(
                        URLEncoder.encode(searchVO.getSearchGubunType3(), "UTF-8"));
            }
            
            // 구분 검색타입
            if (!(searchVO.getSearchGubunType4() == null || searchVO.getSearchGubunType4() == "")) {
                stringBuilder.append("&searchGubunType4=").append(
                        URLEncoder.encode(searchVO.getSearchGubunType4(), "UTF-8"));
            }

            // 검색 시작일
            if (!(searchVO.getSearchStartDate() == null || searchVO.getSearchStartDate() == "")) {
                stringBuilder.append("&searchStartDate=").append(
                        URLEncoder.encode(searchVO.getSearchStartDate(), "UTF-8"));
            }

            // 검색 만료일
            if (!(searchVO.getSearchEndDate() == null || searchVO.getSearchEndDate() == "")) {
                stringBuilder.append("&searchEndDate=").append(URLEncoder.encode(searchVO.getSearchEndDate(), "UTF-8"));
            }
            
            // row 갯수
            if (!(searchVO.getRowLimit() == 10)) {
                stringBuilder.append("&rowLimit=").append(searchVO.getRowLimit());
            }
            
            
//            // 선택 게시물의 ROWNUM
//            if ((searchVO.getRowNum() > 0)) {
//                stringBuilder.append("&rowNum=").append(searchVO.getRowNum());
//            }

            //페이지 번호
            stringBuilder.append("&page=").append(bPaging ? "" : searchVO.getPage());
            
        } catch (NullPointerException nullPointerException) {
            if(log.isDebugEnabled()){
                //log.debug("[StringUtil.createSearchQueryString] Html 검색 Query String 생성실패 ", nullPointerException);
            }
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            if(log.isDebugEnabled()){
                //log.debug("[StringUtil.createSearchQueryString] Html 검색 Query String 생성실패 ", unsupportedEncodingException);
            }
        }
        
        return stringBuilder.toString();
    }

    /**
     * 목록에서 각 ROW의 ROW번호를 계산해서 출력한다.
     * 
     * @param object VO객체
     * @param iRow Row CountNo
     * @return
     */
    public static Integer createTableNumber(Object object, Integer iRow) {
        
        PageVO pageVO = (PageVO) object;

        return pageVO.getTotalRow() - ((pageVO.getPage() - 1) * (pageVO.getRowLimit() - pageVO.getTotalNotiRow())) - iRow + 1;
    }

    /**
     * 개행문자 변환하기
     * 
     * @param str
     * @return
     */
    public static String replaceNewLineCharacter(String str) {

        String str2 = str;

        str2 = str2.replaceAll("&amp;amp;", "&amp;");
        str2 = str2.replaceAll("\r\n", "<br/>");
        str2 = str2.replaceAll("\n", "<br/>");

        return str2;
    }
    
    /**
     * 에디터로 생성된 html코드 변환하기
     * 
     * @param str
     * @return
     */
    public static String replaceEditerCharacter(String str) {

        String str2 = str;

        str2 = str2.replaceAll("&quot;", "\"");
        str2 = str2.replaceAll("&#39;", "'");
        str2 = str2.replaceAll("&lt;", "<");
        str2 = str2.replaceAll("&gt;", ">");
        str2 = str2.replaceAll("\r\n", "<br/>");
        str2 = str2.replaceAll("\n", "<br/>");
        str2 = str2.replaceAll("&amp;amp;", "&amp;");
        str2 = str2.replaceAll("&amp;nbsp;", " ");
        str2 = str2.replaceAll("&amp;lt;", "&lt;");
        str2 = str2.replaceAll("&amp;gt;", "&gt;");

        return str2;
    }
    
    /**
     * 입력받은 달과 날의 숫자를 MM/DD 형식으로 변경한다. 
     *
     * @param number
     * @return MM/DD
     */
    public static String getDateFormat(int date){
        return date < 10 ? ("0" + date) : ("" + date);
    }
    
    /**
     * 숫자의 3자리마다 콤마(,)를 표시해서 출력한다.
     *
     * @param value 변환할 숫자
     * @return
     */
    public static String commaNumber(int value){
        DecimalFormat df = new DecimalFormat("#,##0");
        
        return df.format(value);
    }
    
    
    
    /**
     * DextUpload Html코드 생성
     * - 사용자
     *
     * @param id    object id
     * @param scnCd     등록 파일 구분코드
     * @param menuId    메뉴아이디
     * @return
     */
    public static String createUserDextUploadCode(String id, String scnCd, String menuId, String contnSn){
        return createDextUploadCode(true, id, scnCd, menuId, contnSn, "10", "all", "N");
    }
    
    /**
     * DextUpload Html코드 생성
     * - 관리자
     *
     * @param id    object id
     * @param scnCd     등록 파일 구분코드
     * @param menuId    메뉴아이디
     * @return
     */
    public static String createAdminDextUploadCode(String id, String scnCd, String menuId, String contnSn){
        return createDextUploadCode(false, id, scnCd, menuId, contnSn, "10", "all", "N");
    }
    
    /**
     * DextUpload Html코드 생성
     * - 첨부파일 갯수 및 확장자처리
     * - 사용자
     *
     * @param id    object id
     * @param scnCd     등록 파일 구분코드
     * @param menuId    메뉴아이디
     * @param maxCount  최대첨부파일갯수
     * @param fileType  첨부파일 타입 (all, doc, image)
     * @return
     */
    public static String createUserDextUploadCode2(String id, String scnCd, String menuId, String contnSn, String maxCount, String fileType){
        return createDextUploadCode(true, id, scnCd, menuId, contnSn, maxCount, fileType.toLowerCase(), "N");
    }
    
    /**
     * DextUpload Html코드 생성
     *  - 첨부파일 갯수 및 확장자처리
     * - 관리자
     *
     * @param id    object id
     * @param scnCd     등록 파일 구분코드
     * @param menuId    메뉴아이디
     * @param maxCount  최대첨부파일갯수
     * @param fileType  첨부파일 타입 (all, doc, image)
     * @return
     */
    public static String createAdminDextUploadCode2(String id, String scnCd, String menuId, String contnSn, String maxCount, String fileType){
        return createDextUploadCode(false, id, scnCd, menuId, contnSn, maxCount, fileType.toLowerCase(), "N");
    }
    
    /**
     * DextUpload Html코드 생성
     * - 게시물 복사기능
     * - 사용자
     *
     * @param id    object id
     * @param scnCd     등록 파일 구분코드
     * @param menuId    메뉴아이디
     * @param maxCount  최대첨부파일갯수
     * @param fileType  첨부파일 타입 (all, doc, image)
     * @param copyYn    게시물복사여부
     * @return
     */
    public static String createUserDextUploadCode3(String id, String scnCd, String menuId, String contnSn, String maxCount, String fileType, String copyYn){
        return createDextUploadCode(true, id, scnCd, menuId, contnSn, maxCount, fileType.toLowerCase(), copyYn);
    }
    
    /**
     * DextUpload Html코드 생성
     * - 게시물 복사기능
     * - 관리자
     *
     * @param id    object id
     * @param scnCd     등록 파일 구분코드
     * @param menuId    메뉴아이디
     * @param maxCount  최대첨부파일갯수
     * @param fileType  첨부파일 타입 (all, doc, image)
     * @param copyYn    게시물복사여부
     * @return
     */
    public static String createAdminDextUploadCode3(String id, String scnCd, String menuId, String contnSn, String maxCount, String fileType, String copyYn){
        return createDextUploadCode(false, id, scnCd, menuId, contnSn, maxCount, fileType.toLowerCase(), copyYn);
    }
    
    /**
     * DextUpload Html코드 생성
     *
     * @param bUser     사용자 사이트 여부
     * @param id        object id
     * @param scnCd     등록 파일 구분코드
     * @param menuId    메뉴아이디
     * @param contnSn   컨텐츠일렬번호(신규는 0)
     * @param maxCount  최대첨부파일갯수(기본은 10개)
     * @param fileType  첨부파일 타입 (all, doc, image, media, xls, xlsx)
     * @return
     */
    public static String createDextUploadCode(boolean bUser, String id, String scnCd, String menuId, String cotnSn, String maxCount, String fileType, String copyYn){
        
        Map<String, String> mFileType = new HashMap<String, String>();
        mFileType.put("all", "*.jpg;*.jpeg;*.gif;*.bmp;*.png;*.pdf;*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx;*.hwp;*.zip;");
        mFileType.put("doc", "*.pdf;*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx;*.hwp;*.zip;");
        mFileType.put("image", "*.jpg;*.jpeg;*.gif;*.bmp;*.png;");
        mFileType.put("media", "*.mp4;*.mp3;*.wmv;*.avi;*.mpg;");
        mFileType.put("xls", "*.xls;");
        mFileType.put("xlsx", "*.xlsx;");
        mFileType.put("pdf", "*.pdf;");
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div class=\"upload\">")
                     .append("    <div class=\"upload_area\">")
                     .append("    <object id=\"").append(id).append("\" class=\"fileUploadManager\" filScnCd=\"").append(scnCd).append("\" menuCd=\"").append(menuId).append("\" cotnSn=\"").append(cotnSn).append("\" copyYn=\"").append(copyYn).append("\"")
                     .append("            height=\"95\" width=\"100%\" codeBase=\"").append(bUser ? "/common" : "/hdsadmin").append("/js/DEXTUploadX.cab#version=3,2,10,0\" classid=\"CLSID:DF75BAFF-7DD5-4B83-AF5E-692067C90316\" viewastext>")
                     .append("        <param name=\"MaxCount\" value=\"").append(maxCount).append("\" />");

        /*
        * 동영상은 최대 500메가 변경시 global.config수정 필요함.
        */
        if("media".equals(fileType)){
            stringBuilder.append("        <param name=\"MaxFileSize\" value=\"524288000\" />")
                         .append("        <param name=\"MaxTotalSize\" value=\"524288000\" />");
        }else{
            stringBuilder.append("        <param name=\"MaxFileSize\" value=\"209715200\" />")
                         .append("        <param name=\"MaxTotalSize\" value=\"524288000\" />");
        }
                
        stringBuilder.append("        <param name=\"Filter\" value=\"").append(mFileType.get(fileType)).append("\" />")
                     .append("    </object>")
                     .append("    </div>")
                     .append("    <div class=\"upload_info clfix\">")
                     .append("        <div class=\"float_l\">전체 <span class=\"fileManagerCount\"></span>개 (<span class=\"fileManagerSize\"></span>)</div>")
                     .append("        <div class=\"float_r\">")
                     .append("            <a href=\"javascript:;\" class=\"btn-dext-addFile\"><img src=\"").append(bUser ? "/common" : "/hdsadmin").append("/image/btn/btn_addFile_tbl.gif\" alt=\"파일추가\"></a>")
                     .append("            <a href=\"javascript:;\" class=\"btn-dext-delFile\"><img src=\"").append(bUser ? "/common" : "/hdsadmin").append("/image/btn/btn_delFile_tbl.gif\" alt=\"파일삭제\"></a>")
                     .append("        </div>")
                     .append("    </div>")
                     .append("</div>");
        
//        stringBuilder.append("<ul class=\"warn\">")
//                     .append("<li>! 최적화 사이즈 : <strong>530px*320px</strong></li>")
//                     .append("<li>! 파일 업로드 : 영문 및 숫자로 된 파일명 / bmp, jpg, gif, png / 최대 10MB")
//                     .append("<li>! 규정된 사이즈를 벗어날 경우 임의 조정됩니다.</li>")
//                     .append("<li>! 등록한 이미지는 사용자 목록의 썸네일 이미지로도 출력됩니다.</li>")
//                     .append("<li class=\"c_red\">! 파일명에 _ 를 제외한 특수문자를 사용하시면 보안규정상 파일이 등록 되지 않습니다.</li>")
//                     .append("<li class=\"c_red mb10\">! 파일은 암호를 해제하시고 올려주세요.</li>")        
//                     .append("</ul>");
        
        return stringBuilder.toString();
    }
    
    
    /**
     * 첨부파일 목록 
     *
     * @param bDownLoad 다운로드 링크여부
     * @param lAttachFileVO   첨부파일 목록
     * @return
     */
    public static String printAttachFileList1(String bDownLoad, Object object) throws Exception {
        return printAttachFileListManage("DET", bDownLoad, 0, "ALL_FILE", "", object, "N");
    }
    
    /**
     * 첨부파일 목록 
     *
     * @param bDownLoad 다운로드 링크여부
     * @param filScnCd  파일 구분코드
     * @param lAttachFileVO   첨부파일 목록
     * @return
     */
    public static String printAttachFileList2(String bDownLoad, String filScnCd, Object object) throws Exception {
        return printAttachFileListManage("DET", bDownLoad, 0, filScnCd, "", object, "N");
    }
    
    /**
     * 첨부파일 목록 
     *
     * @param bDownLoad 다운로드 링크여부
     * @param filScnCd  파일 구분코드
     * @param lAttachFileVO   첨부파일 목록
     * @return
     */
    public static String printAttachFileList3(String bDownLoad, String filScnCd, Object object) throws Exception {
        return printAttachFileListManage("VSE-DET", bDownLoad, 0, filScnCd, "", object, "N");
    }
    
    /**
     * 첨부파일  수정
     *
     * @param bDownLoad 다운로드 링크여부
     * @param filScnCd  파일 구분코드
     * @param inputTitle    Input태그 타이틀
     * @param lAttachFileVO  첨부파일 목록
     * @return
     */
    public static String printAttachFileModList1(String bDownLoad, String inputTitle, Object object) throws Exception {
        return printAttachFileListManage("MOD", bDownLoad, 1, "ALL_FILE", inputTitle, object, "Y");
    }
    
    /**
     * 첨부파일  수정
     *
     * @param bDownLoad 다운로드 링크여부
     * @param filScnCd  파일 구분코드
     * @param inputTitle    Input태그 타이틀
     * @param lAttachFileVO  첨부파일 목록
     * @return
     */
    public static String printAttachFileModList2(String bDownLoad, Integer inputNo, String filScnCd, String inputTitle, Object object, String requiredYN) throws Exception {
        return printAttachFileListManage("MOD", bDownLoad, inputNo, filScnCd, inputTitle, object, requiredYN);
    }

    /**
     * 첨부파일 목록 관리
     *
     * @param viewType      화면아이디(DET:상세, MOD:수정)
     * @param downloadYN    다운로드 링크여부
     * @param filScnCd      파일 구분코드
     * @param inputTitle    Input태그 타이틀
     * @param object        첨부파일 목록
     * @param requiredYN    필수요소 여부
     * @return
     * @throws 1Exception
     */
    @SuppressWarnings({ "unchecked" })
    public static String printAttachFileListManage(String viewType, String downloadYN, Integer iNo, String filScnCd, String inputTitle, Object object, String requiredYN) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        List<AttachFileVO> lAttachFileVO = new ArrayList<AttachFileVO>();
        
        try{
            if(object != null && object.getClass() == ArrayList.class){
                lAttachFileVO = (List<AttachFileVO>)object;

                if("DET".equals(viewType)){
                    for(AttachFileVO vo : lAttachFileVO){
                        if("ALL_FILE".equals(filScnCd) || filScnCd.equals(vo.getFilScnCd())){
                            
                            if("Y".equals(downloadYN.toUpperCase())){
                                if(vo.getFilNm().matches(".*(.pdf)")){
                                    stringBuilder.append("<a href=\"#\" class=\"btn_file_download_pdf down\" fileSn=\"").append(vo.getAttcFilSn()).append("\">");
                                }else{
                                    stringBuilder.append("<a href=\"#\" class=\"btn_file_download down\" fileSn=\"").append(vo.getAttcFilSn()).append("\">");
                                }
                            }
                            
                            stringBuilder.append(vo.getOgcFilNm());
//                            stringBuilder.append("DOWN");
                            
                            if("Y".equals(downloadYN.toUpperCase())){
                                stringBuilder.append("</a>");
                            }
                            
                        }
                    }
                }else if("VSE-DET".equals(viewType)){
                    for(AttachFileVO vo : lAttachFileVO){
                        if("ALL_FILE".equals(filScnCd) || filScnCd.equals(vo.getFilScnCd())){
                            
                            if("Y".equals(downloadYN.toUpperCase())){
                                if(vo.getFilNm().matches(".*(.pdf)")){
                                    stringBuilder.append("<a href=\"#\" class=\"mr10 btn_file_download_pdf down\" fileSn=\"").append(vo.getAttcFilSn()).append("\">");
                                }else{
                                    stringBuilder.append("<a href=\"#\" class=\"mr10 btn_file_download down\" fileSn=\"").append(vo.getAttcFilSn()).append("\">");
                                }
                            }
                            
                            stringBuilder.append("<em class=\"icon_file ml0\"></em><b>");
                            stringBuilder.append(vo.getOgcFilNm());
                            stringBuilder.append("</b>");
                            
                            if("Y".equals(downloadYN.toUpperCase())){
                                stringBuilder.append("</a>");
                            }
                            
                        }
                    }
                }else{
                    int checkFileCount = 0;
                    for(AttachFileVO vo : lAttachFileVO){
                        if("ALL_FILE".equals(filScnCd) || filScnCd.equals(vo.getFilScnCd())){
                            
                            stringBuilder.append(printAttachFileManage(iNo + checkFileCount, filScnCd, inputTitle, vo.getAttcFilSn(), vo.getUseYn()));
                            
                            stringBuilder.append("<div class=\"f\">");
                        
                            if("Y".equals(downloadYN.toUpperCase())){
                                if(vo.getFilNm().matches(".*(.pdf|.jpg|.png|.gif)")){
                                    stringBuilder.append("<a href=\"#\" class=\"btn_file_download_pdf down\" target=\"_blank\" fileSn=\"").append(vo.getAttcFilSn()).append("\">");
                                }else{
                                    stringBuilder.append("<a href=\"#\" class=\"btn_file_download down\" fileSn=\"").append(vo.getAttcFilSn()).append("\">");
                                }
                            }
                            
                            if("PLM".equals(vo.getMenuCd()) || "PART".equals(vo.getMenuCd())){
                                stringBuilder.append(vo.getFilNm());
                            }else{
                                stringBuilder.append(vo.getOgcFilNm());
                            }
                            
                            if("Y".equals(downloadYN.toUpperCase())){
                                stringBuilder.append("</a>");
                            }
                            
                            if("Y".equals(requiredYN.toUpperCase())){
                                stringBuilder.append("<a href=\"#\" class=\"btn_delete_file fdel\" fileSn=\"").append(vo.getAttcFilSn()).append("\" menuCd=\"").append(vo.getMenuCd()).append("\" cotnSn=\"").append(vo.getCotnSn()).append("\">")
                                             .append("x")
                                             .append("</a>");
                            }
                            
                            stringBuilder.append("</div>");
                            checkFileCount++;
                        }
                    }
                    
                    if(checkFileCount == 0){
                        throw new IllegalArgumentException();
                    }
                }
            }else{
                throw new IllegalArgumentException();
            }
        }catch(Exception exception){
            
            if("DET".equals(viewType)){
                //ignore
            }else{
                stringBuilder.append(printAttachFileManage(iNo, filScnCd, inputTitle, 0, "Y"));
            }
            
            log.error("파일 목록이 처리 에러");
        }
        
        return stringBuilder.toString();
        
    }
    
    /**
     * 첨부파일 : 등록
     *
     * @param iNo       첨부파일 일렬번호
     * @param filScnCd  첨부파일 구분코드
     * @param inputTitle    Input태그 타이블
     * @return
     * @throws Exception
     */
    public static String printAttachFileReg(Integer iNo, String filScnCd, String inputTitle) throws Exception {
        return printAttachFileManage(iNo, filScnCd, inputTitle, 0, "Y");
    }
    
    /**
     * 첨부파일 : 등록
     *
     * @param iNo       첨부파일 일렬번호
     * @param filScnCd  첨부파일 구분코드
     * @param inputTitle    Input태그 타이블
     * @return
     * @throws Exception
     */
    public static String printAttachFileManage(Integer iNo, String filScnCd, String inputTitle, Integer iFileSn, String userYn) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("<input type=\"hidden\" name=\"attachFileSn").append(iNo).append("\" id=\"attachFileSn").append(iNo).append("\" value=\"").append(iFileSn).append("\" />")
			         .append("<input type=\"hidden\" name=\"attachFileCd").append(iNo).append("\" id=\"attachFileCd").append(iNo).append("\" value=\"").append(filScnCd.toUpperCase()).append("\" />")
			         .append("<input type=\"hidden\" name=\"attachFileUseYn").append(iNo).append("\" id=\"attachFileUseYn").append(iNo).append("\" value=\"").append(userYn).append("\" />")
			         .append("<input type=\"text\" class=\"upload-name bg_g\" value=\"\" disabled=\"disabled\" title=\"첨부파일\" />")
			         .append("<label class=\"file\" for=\"attachFile").append(iNo).append("\">찾아보기</label>");
        
        if(filScnCd.startsWith("THUMB") || filScnCd.startsWith("IMG") ){
        	stringBuilder.append("<input type=\"file\" id=\"attachFile").append(iNo).append("\" name=\"attachFile").append(iNo).append("\" class=\"upload-hidden\" accept=\"image/*\" title=\"").append(inputTitle).append("\"/>");
        }else{
        	stringBuilder.append("<input type=\"file\" id=\"attachFile").append(iNo).append("\" name=\"attachFile").append(iNo).append("\" class=\"upload-hidden\" title=\"").append(inputTitle).append("\"/>");
        }
		
		return stringBuilder.toString();
    }    
    
    
    /**
     * 첨부파일 : 등록
     *
     * @param iNo       첨부파일 일렬번호
     * @param filScnCd  첨부파일 구분코드
     * @param inputTitle    Input태그 타이블
     * @return
     * @throws Exception
     */
    public static String printAttachFileRegUser(Integer iNo, String filScnCd, String inputTitle) throws Exception {
        return printAttachFileUserManage(iNo, filScnCd, inputTitle, 0, "Y");
    }
    
    /**
     * 첨부파일 : 등록
     *
     * @param iNo       첨부파일 일렬번호
     * @param filScnCd  첨부파일 구분코드
     * @param inputTitle    Input태그 타이블
     * @return
     * @throws Exception
     */
    public static String printAttachFileUserManage(Integer iNo, String filScnCd, String inputTitle, Integer iFileSn, String userYn) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("<label class=\"file\" for=\"attachFile").append(iNo).append("\" >")
        			 .append("<input type=\"hidden\" name=\"attachFileSn").append(iNo).append("\" id=\"attachFileSn").append(iNo).append("\" value=\"").append(iFileSn).append("\" />")
			         .append("<input type=\"hidden\" name=\"attachFileCd").append(iNo).append("\" id=\"attachFileCd").append(iNo).append("\" value=\"").append(filScnCd.toUpperCase()).append("\" />")
			         .append("<input type=\"hidden\" name=\"attachFileUseYn").append(iNo).append("\" id=\"attachFileUseYn").append(iNo).append("\" value=\"").append(userYn).append("\" />");
        
	    if(filScnCd.startsWith("THUMB") || filScnCd.startsWith("IMG") ){
	    	stringBuilder.append("<input type=\"file\" id=\"attachFile").append(iNo).append("\" name=\"attachFile").append(iNo).append("\" class=\"upload-hidden\" accept=\"image/*\" title=\"").append(inputTitle).append("\"/>");
	    }else{
	    	stringBuilder.append("<input type=\"file\" id=\"attachFile").append(iNo).append("\" name=\"attachFile").append(iNo).append("\" class=\"upload-hidden\" title=\"").append(inputTitle).append("\"/>");
	    }
        
        stringBuilder.append("<span><input type=\"text\" value=\"\" disabled=\"disabled\" title=\"첨부파일\" /><a class=\"btn02 btn_pp add_search\"><span>찾아보기</span></a></span>");
        
        
        stringBuilder.append("</label>");
		
		return stringBuilder.toString();
    }    
    
    /**
     * 첨부파일 : 등록
     *
     * @param iNo       첨부파일 일렬번호
     * @param filScnCd  첨부파일 구분코드
     * @param inputTitle    Input태그 타이블
     * @return
     * @throws Exception
     */
    public static String printAttachFileRegUser2(Integer iNo, String filScnCd, String inputTitle) throws Exception {
        return printAttachFileUserManage2(iNo, filScnCd, inputTitle, 0, "Y");
    }
    
    /**
     * 첨부파일 : 등록
     *
     * @param iNo       첨부파일 일렬번호
     * @param filScnCd  첨부파일 구분코드
     * @param inputTitle    Input태그 타이블
     * @return
     * @throws Exception
     */
    public static String printAttachFileUserManage2(Integer iNo, String filScnCd, String inputTitle, Integer iFileSn, String userYn) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("<label class=\"file\" for=\"attachFile").append(iNo).append("\" >")
        			 .append("<img /> ")
        			 .append("<input type=\"hidden\" name=\"attachFileSn").append(iNo).append("\" id=\"attachFileSn").append(iNo).append("\" value=\"").append(iFileSn).append("\" />")
			         .append("<input type=\"hidden\" name=\"attachFileCd").append(iNo).append("\" id=\"attachFileCd").append(iNo).append("\" value=\"").append(filScnCd.toUpperCase()).append("\" />")
			         .append("<input type=\"hidden\" name=\"attachFileUseYn").append(iNo).append("\" id=\"attachFileUseYn").append(iNo).append("\" value=\"").append(userYn).append("\" />")
        			 .append("<input type=\"file\" id=\"attachFile").append(iNo).append("\" name=\"attachFile").append(iNo).append("\" accept=\"image/*\" title=\"").append(inputTitle).append("\"/>")
        			 .append("<span>사진첨부</span>")
        			 .append("</label>");
		
		return stringBuilder.toString();
    }    
    
    
    /**
     * 이미지 파일 출력
     *
     * @param filScnCd
     * @param object
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String printImageFile(String filScnCd, Object object) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        try{
            List<AttachFileVO> lAttachFileVO = new ArrayList<AttachFileVO>();
            
            if(object != null && object.getClass() == ArrayList.class){
                lAttachFileVO = (List<AttachFileVO>)object;
                
                for(AttachFileVO vo : lAttachFileVO){
                    if(filScnCd.equals(vo.getFilScnCd())){
                    	if(vo.getFilNm().endsWith("mp4")){
                    		stringBuilder.append("<video width=\"700\" controls playsinline><source src=\"/resources/upload/").append(vo.getMenuCd().toUpperCase()).append("/").append(vo.getFilNm()).append("\" type=\"video/mp4\"></video>");
                    	}else{
                    		stringBuilder.append("<img src=\"/resources/upload/").append(vo.getMenuCd().toUpperCase()).append("/").append(vo.getFilNm()).append("\" alt=\"").append(vo.getFilExpl()).append("\" />");
                    	}
                    }
                }
            }else{
                throw new IllegalArgumentException();
            }
        }catch(IllegalArgumentException illegalArgumentException){
            log.error("Failed printImageFile : Message => {} ", illegalArgumentException.getMessage());
        }

        return stringBuilder.toString();
        
    }
    
    
    /**
     * 이미지 파일 출력 링크만
     *
     * @param filScnCd
     * @param object
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String printImageFileLink(String filScnCd, Object object) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        try{
            List<AttachFileVO> lAttachFileVO = new ArrayList<AttachFileVO>();
            
            if(object != null && object.getClass() == ArrayList.class){
                lAttachFileVO = (List<AttachFileVO>)object;
                
                for(AttachFileVO vo : lAttachFileVO){
                    if(filScnCd.equals(vo.getFilScnCd())){
                		stringBuilder.append("/resources/upload/").append(vo.getMenuCd().toUpperCase()).append("/").append(vo.getFilNm());
                    }
                }
            }else{
                throw new IllegalArgumentException();
            }
        }catch(IllegalArgumentException illegalArgumentException){
            log.error("Failed printImageFile : Message => {} ", illegalArgumentException.getMessage());
        }

        return stringBuilder.toString();
        
    }
    
    
    /**
     * 이미지 파일 출력
     * - 목록
     *
     * @param filScnCd
     * @param object
     * @return
     * @throws Exception
     */
    public static String printImageFileByList(Integer filSn, String menuCd, String filNm, String filExpl) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        if(!(filSn == 0 || filNm.isEmpty())){
            stringBuilder.append("<img src=\"/resources/upload/").append(menuCd.toUpperCase()).append("/").append(filNm).append("\" alt=\"").append(filExpl).append("\" />");
        }

        return stringBuilder.toString();
        
    }
    
    /**
     * 이미지 대체텍스트 출력
     *
     * @param filScnCd
     * @param object
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String printImageDesc(String filScnCd, Object object) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder("");
        
        List<AttachFileVO> lAttachFileVO = new ArrayList<AttachFileVO>();
        
        if(object != null && object.getClass() == ArrayList.class){
            lAttachFileVO = (List<AttachFileVO>)object;
        }
        
        for(AttachFileVO vo : lAttachFileVO){
            if(filScnCd.equals(vo.getFilScnCd())){
                stringBuilder.append(vo.getFilExpl());
            }
        }

        return stringBuilder.toString();
        
    }
    
    /**
     * PLM 등록 첨부파일 화면 출력 : 파일 존재여부 확인
     *
     * @param partNumber
     * @param filScnCd
     * @param filNm
     * @return
     * @throws Exception
     */
    public static String printPlmFile(String partNumber, String filScnCd, String filNm) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        MultiUtil multiUtil = new MultiUtil();
        
        String inputName = "";
        
        //파일 종류별 INPUT NAME
        if("IMG".equals(filScnCd)){
            inputName = "spImgFile";
        }else if("2D".equals(filScnCd)){
            inputName = "sp2DpdfFile";
        }else if("3D".equals(filScnCd)){
            inputName = "sp3DpdfFile";
        }else if("STEP".equals(filScnCd)){
            inputName = "spStepFile";
        }else if("IGS".equals(filScnCd)){
            inputName = "spIgsFile";
        }else if("INST".equals(filScnCd)){
            inputName = "spInstructionsFile";
        }
        
        //파일 존재 여부 확인
        String fileSystemPath = multiUtil.getGlobalConfig("file.system.path.part.plm") + File.separator + partNumber + File.separator + filNm;
        String fileWebPath = multiUtil.getGlobalConfig("file.web.path.part.plm") + "/" + partNumber + "/" + filNm;
        
        //파일
        File plmFile = new File(fileSystemPath);
        
        //화면 출력
        stringBuilder.append("<div class=\"f\">");
        
        if(plmFile.exists()){
            stringBuilder.append("<input type=\"hidden\" name=\"").append(inputName).append("\" value=\"").append(filNm).append("\" />");
            stringBuilder.append("<a href=\"").append(fileWebPath).append("\" class=\"down\" target=\"_blank\" >").append(filNm).append("</a>");
            stringBuilder.append("<a href=\"#\" class=\"btn_delete_origin_file fdel\" data=\"").append(inputName).append("\">x</a>");
        }else{
            stringBuilder.append(filNm).append(" 파일이 존재하지 않습니다.");
        }
        
        stringBuilder.append("</div>");
        
        return stringBuilder.toString();
    }


    /**
     * PLM 첨부파일 상세화면 출력 : 파일 존재여부 확인
     *
     * @param partNumber
     * @param filScnCd
     * @param filNm
     * @param object
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String printPlmFileDetail(String partNumber, String filScnCd, String filNm, Object object) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        MultiUtil multiUtil = new MultiUtil();
        
        List<AttachFileVO> lAttachFileVO = new ArrayList<AttachFileVO>();
        
        String inputName = "";
        
        try{
            if("2D".equals(filScnCd)){
                inputName = "2D PDF";
            }else if("3D".equals(filScnCd)){
                inputName = "3D PDF";
            }else if("STEP".equals(filScnCd)){
                inputName = "3D STEP";
            }else if("IGS".equals(filScnCd)){
                inputName = "3D IGS";
            }else if("INST".equals(filScnCd)){
                inputName = "제품사양서";
            }
            
            
            if(object != null && object.getClass() == ArrayList.class){
                lAttachFileVO = (List<AttachFileVO>)object;
            }else{
                log.debug("StringUtil.printAttachFileList() object.getClass() :: " + object.getClass());
                
                throw new IllegalArgumentException();
            }
            
            for(AttachFileVO vo : lAttachFileVO){
                if(filScnCd.equals(vo.getFilScnCd())){
                    filNm = vo.getFilNm();
                    break;
                }
            }
        }catch(Exception exception){
            log.error("printPlmFileDetail : 파일 목록이 처리 에러");
        }
            
        if(!filNm.isEmpty()){
            //파일 존재 여부 확인
            String fileSystemPath = multiUtil.getGlobalConfig("file.system.path.part.plm") + File.separator + partNumber + File.separator + filNm;
            String fileWebPath = multiUtil.getGlobalConfig("file.web.path.part.plm") + "/" + partNumber + "/" + filNm;
            
            
            log.error("fileSystemPath : " + fileSystemPath);
            
            //파일
            File plmFile = new File(fileSystemPath);
            
            if(plmFile.exists()){
                stringBuilder.append("<dd><a href=\"").append(fileWebPath).append("\" class=\"down\" target=\"_blank\" >").append(inputName).append("</a></dd>");
            }
        }
        
        return stringBuilder.toString();
    }
    
    /**
     * 이미지 파일 출력
     * - 목록
     *
     * @param filScnCd
     * @param object
     * @return
     * @throws Exception
     */
    public static String printBoardListFileLink(Integer filSn, String filNm) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        if(!(filSn == 0 || filNm.isEmpty())){
            if(filNm.matches(".*(.pdf|.jpg|.png|.gif)")){
                stringBuilder.append("<a href=\"#\" class=\"btn_file_download_pdf down\" target=\"_blank\" fileSn=\"").append(filSn).append("\">").append("<img src=\"/resources/user/images/community/clip.png\" alt=\"첨부파일\" />").append("</a>");
            }else{
                stringBuilder.append("<a href=\"#\" class=\"btn_file_download down\" fileSn=\"").append(filSn).append("\">").append("<img src=\"/resources/user/images/community/clip.png\" alt=\"첨부파일\" />").append("</a>");
            }
        }

        return stringBuilder.toString();
    }
    
    
    /**
     * 이미지 파일 출력
     *
     * @param partNumber
     * @param filNm
     * @param object
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static String printPlmImageFile(String partNumber, String filNm, Object object) throws Exception {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        MultiUtil multiUtil = new MultiUtil();
        
        boolean imageStatus = false;
        
        try{
            if(object != null){
                List<AttachFileVO> lAttachFileVO = new ArrayList<AttachFileVO>();
                
                if(object != null && object.getClass() == ArrayList.class){
                    lAttachFileVO = (List<AttachFileVO>)object;
                    
                    for(AttachFileVO vo : lAttachFileVO){
                        if("IMG".equals(vo.getFilScnCd())){
                            imageStatus = true;
                            stringBuilder.append("<img src=\"").append(multiUtil.getGlobalConfig("file.web.path.part.plm")).append("/").append(partNumber).append("/").append(vo.getFilNm()).append("\" alt=\"").append(partNumber).append("\" />");
                        }
                    }
                }
            }
            
            if(!imageStatus){
                //파일 존재 여부 확인
                String fileSystemPath = multiUtil.getGlobalConfig("file.system.path.part.plm") + File.separator + partNumber + File.separator + filNm;
                String fileWebPath = multiUtil.getGlobalConfig("file.web.path.part.plm") + "/" + partNumber + "/" + filNm;
                
                //파일
                File plmFile = new File(fileSystemPath);
                
                if(plmFile.exists()){
                    stringBuilder.append("<img src=\"").append(fileWebPath).append("\" alt=\"").append(partNumber).append("\" />");
                }else{
                    stringBuilder.append("<img src=\"/resources/images/sub/schnone.jpg\" />");
                }
            }
        }catch(Exception exception){
            log.debug("Failed printPlmImageFile : Message => {} ", exception.getMessage());
        }

        return stringBuilder.toString();
    }
    
    public static String printDownloadLink(Integer filSn) throws Exception {
    	return "/cm/fileDownMan.vc?attcFilSn=" + filSn;
    }
    
    
    /**
	 * 이메일 정보 마스킹
	 * - Ref) hah****@naver.com
	 * @param string
	 * @return
	 */
	public static String maskedEmail(String string) {
		
		int iNonMask = 3;
		
		String[] string2 = string.split("@");
		
		if(string2[0].length() <= iNonMask) {
			iNonMask = 1;
		}
		
		return string2[0].substring(0, iNonMask) + (string2[0].substring(iNonMask).replaceAll("\\w", "*")) + "@" + string2[1];
	}
    
}

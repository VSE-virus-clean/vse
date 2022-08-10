package jksoft.com.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * <pre>
 * EDITOR 사용시 생성되는 태그를 화면에 출력할수 있도록 변경한다.
 * </pre>
 *
 * @ClassName   : HtmlFormatTag.java
 * @Description : HTML 태그를 화면에 출력할수 있도록 변경.
 * @author Jeong.hyoungjea
 * @since 2014.01.28.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014.01.28.    Jeong.hyoungjea     최초 생성
 * </pre>
 */

public class HtmlFormatTag extends SimpleTagSupport 
{
    //문자열
    private String value = null;    
    
    //ALL : 태그 전체변환, BR : <br>태그변환
    //SH : #변환 (%23)
    //BR : <br>태그변환
    //NQ : ", <br>태그만 제외
    //DD : <dd>태그로 감싼다.
    //LI : <li>태그로 감싼다.
    //SPAN : <span>태그로 감싼다.
    private String attr = "ALL";    

    
    public void setValue( String value ){
        this.value = value;
    }
    
    public void setAttr( String attr ){
        this.attr = attr;
    }
    
    public void doTag() throws JspException, IOException{
        JspWriter out = getJspContext().getOut();               
        
        if("BR".equals(attr)){
            //TextArea에 입력한 Text의 변환
            if(value != null && !"".equals(value)){   
                value = value.replaceAll("&amp;amp;", "&amp;");
                value = value.replaceAll("\r\n", "<br/>");
                value = value.replaceAll("\n", "<br/>");
            }
        }else if("SH".equals(attr)){
            //주소등에 #이 들어간 문자열의 인코딩 변환
            if(value != null && !"".equals(value)){   
                value = value.replaceAll("%23", "#");
            }
        }else if("DD".equals(attr) || "LI".equals(attr) || "SPAN".equals(attr)){
            //TextArea에서 개행에 따라서 html태그 생성이 필요할 경우 사용
            if(value != null && !"".equals(value)){   
                value = value.replaceAll("&amp;amp;", "&amp;");
                value = value.replaceAll("\r\n", "|");
                value = value.replaceAll("\n", "|");
                
                String[] splitValue = value.split("\\|");
                String tempValue = "";
                
                for(String str : splitValue){
                    tempValue += "<"+ attr.toLowerCase()+">" + str + "</"+ attr.toLowerCase()+">";
                }
                
                value = tempValue;
            }
        }else if("NQ".equals(attr)){
            //개행을 제외한 특수문자 변환
            if(value != null && !"".equals(value)){   
                value = value.replaceAll("&quot;", "\"");
                value = value.replaceAll("&#39;", "'");
                value = value.replaceAll("&lt;", "<");
                value = value.replaceAll("&gt;", ">");
                value = value.replaceAll("&amp;amp;", "&amp;");
                value = value.replaceAll("&amp;nbsp;", " ");
                value = value.replaceAll("&amp;lt;", "&lt;");
                value = value.replaceAll("&amp;gt;", "&gt;");
                value = value.replaceAll("&amp;quot;", "'");
                value = value.replaceAll("&amp;ldquo;", "\"");
                value = value.replaceAll("&amp;rdquo;", "\"");
                value = value.replaceAll("&amp;rsquo;", "\'");
                value = value.replaceAll("&amp;lsquo;", "\'");
                value = value.replaceAll("&ldquo;", "\"");
                value = value.replaceAll("&rdquo;", "\"");
                value = value.replaceAll("&rsquo;", "\'");
                value = value.replaceAll("&lsquo;", "\'");
                value = value.replaceAll("&amp;middot;", "·");
                value = value.replaceAll("&middot;", "·");
                value = value.replaceAll("&amp;hellip;", "…");
                value = value.replaceAll("&hellip;", "…");
                value = value.replaceAll("&amp;prime;", "\'");
                value = value.replaceAll("&prime;", "\'");
            }
        }else{
            //특수 문자 변환
            if(value != null && !"".equals(value)){   
                value = value.replaceAll("&quot;", "\"");
                value = value.replaceAll("&#39;", "'");
                value = value.replaceAll("&lt;", "<");
                value = value.replaceAll("&gt;", ">");
                value = value.replaceAll("\r\n", "<br/>");
                value = value.replaceAll("\n", "<br/>");
                value = value.replaceAll("&amp;amp;", "&amp;");
                value = value.replaceAll("&amp;nbsp;", " ");
                value = value.replaceAll("&amp;lt;", "&lt;");
                value = value.replaceAll("&amp;gt;", "&gt;");
                value = value.replaceAll("&amp;quot;", "'");
                value = value.replaceAll("&amp;ldquo;", "\"");
                value = value.replaceAll("&amp;rdquo;", "\"");
                value = value.replaceAll("&amp;rsquo;", "\'");
                value = value.replaceAll("&amp;lsquo;", "\'");
                value = value.replaceAll("&ldquo;", "\"");
                value = value.replaceAll("&rdquo;", "\"");
                value = value.replaceAll("&rsquo;", "\'");
                value = value.replaceAll("&lsquo;", "\'");
            }
        }
        
        out.println(value);
    }
}

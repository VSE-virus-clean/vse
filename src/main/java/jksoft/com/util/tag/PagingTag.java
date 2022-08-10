package jksoft.com.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * <pre>
 * HTML 페이징 코드 생성
 * </pre>
 *
 * @ClassName   : PagingTag.java
 * @Description : HTML 페이징 코드 생성
 */

public class PagingTag extends SimpleTagSupport
{
    /**
     * 페이지 연결 URL
     */
    private String url = null;
    
    /**
     * 전체 Row수
     */
    private int totalRow = 0;
    
    /**
     * 페이지당 보여줄 Row수
     * optional
     */
    private int rowLimit = 10;
    
    /**
     * 현재 Page 번호
     * optional
     */
    private int page = 1;
    
    /**
     * 한번에 보여줄 페이지 갯수
     * optional
     */
    private int pageLimit = 10;
    
    
    public void setTotalRow( int totalRow ){
        this.totalRow = totalRow;
    }
    
    public void setRowLimit( int rowLimit ){
        this.rowLimit = rowLimit;
    }
    
    public void setPage( int page ){
        this.page = (page < 1) ? 1 : page;
    }
    
    public void setPageLimit( int pageLimit ){
        this.pageLimit = pageLimit;
    }
    
    public void setUrl( String url ){
        this.url = url;
    }   
    
    /**
     * 페이징 Tag구현
     */
    public void doTag() throws JspException, IOException
    {
        JspWriter out = getJspContext().getOut();
        
        String btFirst = "";
        String btPrev = "";
        String btNext = "";
        String btLast = "";
        StringBuilder btPage = new StringBuilder();
        
        if(totalRow > 0){
        
            int totalPage = totalRow == 0 ? 1 : (totalRow / rowLimit + (totalRow % rowLimit == 0 ? 0 : 1));
            int startPage = ((page - 1) / pageLimit) * pageLimit + 1;
                
            //첫페이지로
            if(page > 1){
                btFirst = "<li class=\"pag_first active\"><a href=\"" + url + 1 + "\">&lt;&lt;</a></li>";
            }
            
            //이전 페이지
            if(page > pageLimit){
                btPrev = "<li class=\"pag_first active\"><a href=\"" + url + (startPage - 1) + "\">&lt;</a></li>";
            }
           
            //다음 페이지
            if((startPage + pageLimit) <= totalPage){
                btNext = "<li class=\"pag_last active\"><a href=\"" + url + (startPage + pageLimit) + "\">&gt;</a></li>";
            }  
            
            //마지막 페이지로
            if(page < totalPage){
                btLast = "<li class=\"pag_last active\"><a href=\"" + url + totalPage + "\">&gt;&gt;</a></li>";
            }
            
            //페이지 목록
            for (int i = 0; i < pageLimit; i++)
            {
                int pageNo = startPage + i;
                
                if(pageNo > totalPage) { 
                    break; 
                }else{
                    if(pageNo == page){ 
                        btPage.append("<li class=\"active\"><a href=\"#\" onclick=\"return false;\">").append(pageNo).append("</a></li>");
                    }else{ 
                        btPage.append("<li><a href=\"").append(url + (pageNo)).append("\">").append(pageNo).append("</a></li>");
                    }       
                }
            }
        }
        
        //출력
        out.println("<ul class=\"paging\">" + btFirst + btPrev + btPage + btNext + btLast + "</ul>");    
    }
}

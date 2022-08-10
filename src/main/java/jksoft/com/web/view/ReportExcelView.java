package jksoft.com.web.view;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import jksoft.com.filter.FilterUtil;
import jksoft.com.util.DateUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.XMap;

/**
 * <pre>
 * 엑셀파일 다운로드 VIEW
 * </pre>
 *
 * @ClassName   : ReportExcelView.java
 * @Description : 엑셀파일 다운로드 VIEW
 * @author LaheeDad
 * @since 2013. 9. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 9. 23.     LaheeDad     최초 생성
 * </pre>
 */

@SuppressWarnings("deprecation")
public class ReportExcelView extends AbstractExcelView
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * Excel 생성
     * 
     * Controller에서 model.addAttribute로 넘겨준값을 Excel파일로 변환한다.
     * Controller에서 BeanNameViewResolver로 이동한다.
     * 
     * ref : model.addAttribute("columTitle", strColumTitle); 
     *       return "reportExcelView";
     * 
     * - list : Data, XMap형으로 넘겨줄것.
     * - documentTitle : Document 제목
     * - sheetTitle : Sheet 제목
     * - columTitle : Array형으로 넘겨줄것. Null일 경우 XMap.key값으로 생성된다.
     */
    @SuppressWarnings("unchecked")
	@Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        //DATA LIST
        List<XMap> lmReportList = (List<XMap>)model.get("list");

        int iRowNum = 0;
        
        //DOCUMENT TITLE
        String strDocumentTitle = null;

        //SHEET TITLE
        String strSheeTitle = (String)model.get("sheetTitle");

        //COLUM TITLE
        String[] arrColumTitle = null;

        //COLUM VALUE
        String[] arrColumValue = null;
        
        
        if(model.containsKey("documentTitle")){
            iRowNum = 3;
            strDocumentTitle = (String)model.get("documentTitle");
        }else{
            iRowNum = 1;
        }
        
        if(model.containsKey("columTitle")){
            arrColumTitle = (String[])model.get("columTitle");
        }

        if(model.containsKey("columValue")){
            arrColumValue = (String[])model.get("columValue");
        }

        //SHEET 생성
        HSSFSheet hssfSheet = this.createSheet(workbook, strSheeTitle);
        hssfSheet.setDefaultColumnWidth(20);    //defaultColumnWidth = 20

        //기본 셀 스타일 설정
        //HSSFCellStyle hssfCellStyle = this.createStyle(workbook);

        //HEADER 생성
        //columTitle가 없으면 DB조회 컬럼 Alias명으로 출력된다.
        if(arrColumTitle != null){
            hssfSheet = this.addReportHeaderRow(workbook, hssfSheet, lmReportList, arrColumTitle, strDocumentTitle);
        }else{
            hssfSheet = this.addReportHeaderRow(workbook, hssfSheet, lmReportList, strDocumentTitle);
        }

        hssfSheet.setDisplayRowColHeadings(true);


        if(lmReportList != null){
            //본문 생성
        	HSSFCellStyle hssfCellStyle = this.createStyle(workbook);
            for(Map<String, Object> mList : lmReportList){
                iRowNum = this.addReportRow(workbook, hssfSheet, mList, iRowNum, arrColumValue, hssfCellStyle);
            }
        }

        this.setHeaderForExcel(response, (String)model.get("fileName"));
    }

    /**
     * 셀 기본 스타일 생성
     * @param _hssfWorkBook
     * @return
     */
    private HSSFCellStyle createStyle(HSSFWorkbook _hssfWorkBook)
    {
        HSSFCellStyle hssfCellStyle = _hssfWorkBook.createCellStyle();

        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //2
        hssfCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //1
        hssfCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //5
        hssfCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        hssfCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        hssfCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

        return hssfCellStyle;
    }

    /**
     * 왼쪽 정렬
     * @param _hssfWorkBook
     * @return
     */
    private HSSFCellStyle createLeftStyle(HSSFWorkbook _hssfWorkBook)
    {
        HSSFCellStyle hssfCellStyle = _hssfWorkBook.createCellStyle();

        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); //2
        hssfCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //1
        hssfCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //5
        hssfCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        hssfCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        hssfCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

        return hssfCellStyle;
    }

    /**
     * 제목 스타일
     * @param _hssfWorkBook
     * @return
     */
    private HSSFCellStyle createDocumentTitleStyle(HSSFWorkbook _hssfWorkBook)
    {
        HSSFFont hssfFont = _hssfWorkBook.createFont();
        hssfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle hssfCellStyle = _hssfWorkBook.createCellStyle();

        //Font
        hssfCellStyle.setFont(hssfFont);

        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        return hssfCellStyle;
    }


    /**
     * 컬럼 제목 스타일
     * @param _hssfWorkBook
     * @return
     */
    private HSSFCellStyle createColumTitleStyle(HSSFWorkbook _hssfWorkBook)
    {
        HSSFFont hssfFont = _hssfWorkBook.createFont();
        hssfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle hssfCellStyle = _hssfWorkBook.createCellStyle();
        hssfCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //5
        hssfCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        hssfCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        hssfCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

        //Font
        hssfCellStyle.setFont(hssfFont);

        //정렬
        hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //색 지정 후 꼭 "FillPattern"을 설정해줘야 함.
        hssfCellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        hssfCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        return hssfCellStyle;
    }

    /**
     * 문서의 시트를 생성
     *
     * @param _hssfWorkBook
     * @param _strSheeTitle 시트 이름
     * @return
     */
    private HSSFSheet createSheet(HSSFWorkbook _hssfWorkBook, String _strSheeTitle)
    {
        HSSFSheet sheet = _hssfWorkBook.createSheet(_strSheeTitle);

        return sheet;
    }

    /**
     * 문서 제목과 데이터 각 컬럼제목을 출력
     *
     * @param _hssfWorkBook
     * @param _hssfSheet
     * @param _mList 출력 데이터
     * @param _arrColumTitle 컬럼 제목
     * @param _strDocumentTitle 문서 타이틀
     * @return
     */
    private HSSFSheet addReportHeaderRow(HSSFWorkbook _hssfWorkBook,
                                            HSSFSheet _hssfSheet,                        
                                            List<XMap> _mList,
                                            String[] _arrColumTitle,
                                            String _strDocumentTitle)
    {
        //문서 제목
        HSSFCellStyle hssfDocumentHeaderCellStyle = this.createDocumentTitleStyle(_hssfWorkBook);

        //컬럼 스타일
        HSSFCellStyle hssfColumnHeaderCellStyle = this.createColumTitleStyle(_hssfWorkBook);

        //REPORT TITLE
        HSSFRow hssfHeader = _hssfSheet.createRow(0);
        
        //Title가 없으면 문서제목을 출력하지 않는다.
        if(_strDocumentTitle != null){
            hssfHeader.createCell(0).setCellValue(_strDocumentTitle);
            hssfHeader.getCell(0).setCellStyle(hssfDocumentHeaderCellStyle);
    
            //TH - 두줄 여백처리
            hssfHeader = _hssfSheet.createRow(2);
        }

        //내용이 있으면 컬럼제목 출력 없으면 없다고 출력
        if(_mList != null){
            if(_mList.size() != 0){
                //DATA HEADER 출력
                int iCellNum = 0;
                for(String colum : _arrColumTitle){
                    hssfHeader.createCell(iCellNum).setCellValue(colum);
                    hssfHeader.getCell(iCellNum).setCellStyle(hssfColumnHeaderCellStyle);

                    iCellNum++;
                }
            }else{
                hssfHeader.createCell(0).setCellValue("데이터가 없습니다.");
            }
        }else{
            hssfHeader.createCell(0).setCellValue("권한이 없거나 데이터가 이상합니다.");
            if(log.isErrorEnabled()){
                log.debug("데이터가 없습니다. 권한이 없어서 데이터가 없을수도 있습니다.");                
            }
        }

        return _hssfSheet;
    }


    /**
     * 문서 제목과 데이터 각 컬럼제목을 출력
     *
     * @param _hssfWorkBook
     * @param _hssfSheet
     * @param _hssfCellStyle
     * @param _mList 출력 데이터(컬럼 제목만 사용)
     * @param _strDocumentTitle 문서 타이틀
     * @return
     */
    private HSSFSheet addReportHeaderRow(HSSFWorkbook _hssfWorkBook, 
                                         HSSFSheet _hssfSheet, 
                                         List<XMap> _mList, 
                                         String _strDocumentTitle)
    {
        //문서 제목
        HSSFCellStyle hssfDocumentHeaderCellStyle = this.createDocumentTitleStyle(_hssfWorkBook);

        //컬럼 스타일
        HSSFCellStyle hssfColumnHeaderCellStyle = this.createColumTitleStyle(_hssfWorkBook);

        //REPORT TITLE
        HSSFRow hssfHeader = _hssfSheet.createRow(0);
       
        //Title가 없으면 문서제목을 출력하지 않는다.
        if(_strDocumentTitle != null){
            hssfHeader.createCell(0).setCellValue(_strDocumentTitle);
            hssfHeader.getCell(0).setCellStyle(hssfDocumentHeaderCellStyle);
            
            //TH - 두줄 여백처리
            hssfHeader = _hssfSheet.createRow(2);
        }


        if(_mList != null){
            
            //내용이 있으면 컬럼제목 출력 없으면 없다고 출력
            if(_mList.size() != 0){
                @SuppressWarnings("unchecked")
                Set<String> setList = _mList.get(0).keySet();
    
                //DATA HEADER 출력
                int iCellNum = 0;
                for(Iterator<String> iter = setList.iterator(); iter.hasNext(); iCellNum++ ){
                    hssfHeader.createCell(iCellNum).setCellValue(iter.next());
                    hssfHeader.getCell(iCellNum).setCellStyle(hssfColumnHeaderCellStyle);
                }
            }else{
                hssfHeader.createCell(0).setCellValue("데이터가 없습니다.");
            }
            
        }else{
            hssfHeader.createCell(0).setCellValue("권한이 없거나 데이터가 이상합니다.");
            if(log.isErrorEnabled()){
                log.debug("데이터가 없습니다. 권한이 없어서 데이터가 없을수도 있습니다.");                
            }
        }
        return _hssfSheet;
    }


    /**
     * 문서에 행을 삽입
     *
     * @param _hssfSheet 시트객체
     * @param _hssfCellStyle 셀 스타일
     * @param _mList 출력 데이터(내용만 사용)
     * @param _iRowNum 시작 줄 번호
     * @param _arrColumValue 사용할 컬럼(사용자정의)
     * @return
     */
    private int addReportRow(HSSFWorkbook _hssfWorkBook, HSSFSheet _hssfSheet, Map<String, Object> _mList, int _iRowNum, String[] _arrColumValue, HSSFCellStyle hssfCellStyle)
    {
        int _iRowNum2 = _iRowNum;
        
        //TD
        HSSFRow row = _hssfSheet.createRow(_iRowNum2++);
        HSSFCell cell = null;

        //기본 셀 스타일 설정
//        HSSFCellStyle hssfCellStyle = this.createStyle(_hssfWorkBook);
        //왼쪽 정렬
//        HSSFCellStyle hssfLeftCellStyle = this.createLeftStyle(_hssfWorkBook);

        // 사용자 정의 컬럼 key값 배열이 존재 할 경우
        boolean isUserCell = false;
        if(_arrColumValue != null && _arrColumValue.length > 0){
            isUserCell = true;
        }

        int iCellNum = 0;
        for(String key : _mList.keySet())
        {
            cell = row.createCell(iCellNum);

            // 사용자 정의 컬럼 key값 배열이 존재 할 경우
            if(isUserCell){
                boolean isCellValue = false;
                for(String column : _arrColumValue){
                    if(key.equals(column)){
                        isCellValue = true;
                        break;
                    }
                }
                if(isCellValue){
                    if(key.toLowerCase().indexOf("name") > 0)
                    {
                        cell.setCellValue(FilterUtil.decodeHTML(StringUtil.replaceNull((String)_mList.get(key))));
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellStyle(hssfCellStyle);
                    }
                    else
                    {
                        try{
                            cell.setCellValue(FilterUtil.decodeHTML(StringUtil.replaceNull((String)_mList.get(key))));
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(hssfCellStyle);
                        }catch (ClassCastException nsEx){
                            try{
                                cell.setCellValue(new BigDecimal(_mList.get(key).toString()).doubleValue());
                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                cell.setCellStyle(hssfCellStyle);
                            }catch (Exception ex){
                                cell.setCellValue(FilterUtil.decodeHTML(_mList.get(key).toString()));
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellStyle(hssfCellStyle);
                            }
                        }

                    }
                    iCellNum++;
                }

            }else{ // 모든 컬럼을 엑셀 다운로드시

                if(key.toLowerCase().indexOf("name") > 0)
                {
                    cell.setCellValue(FilterUtil.decodeHTML(StringUtil.replaceNull((String)_mList.get(key))));
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellStyle(hssfCellStyle);
                }
                else
                {
                    try{
                        cell.setCellValue(FilterUtil.decodeHTML(StringUtil.replaceNull((String)_mList.get(key))));
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellStyle(hssfCellStyle);
                    }catch (ClassCastException nsEx){
                        try{
                            cell.setCellValue(new BigDecimal(_mList.get(key).toString()).doubleValue());
                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellStyle(hssfCellStyle);
                        }catch (Exception ex){
                            cell.setCellValue(FilterUtil.decodeHTML(_mList.get(key).toString()));
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellStyle(hssfCellStyle);
                        }
                    }

                }

                iCellNum++;

            }
        }

        return _iRowNum2;
    }


    /**
     * EXCEL 파일 다운로드용 RESPONSE HEADER설정
     *
     * @param _response HttpServletResponse
     * @param _strFileName 파일명
     * @return HttpServletResponse
     */
    protected void setHeaderForExcel(HttpServletResponse _response, String _strFileName) throws Exception
    {
        _response.setContentType("application/xls; charset=UTF-8");
        //_response.setHeader("Content-Disposition", "attachment;filename="+ DateUtil.formatDate("yyMMdd")+ "_" + new String(_strFileName.getBytes("KSC5601"), "8859_1") +".xls");
        _response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(_strFileName, "UTF-8") + "_"+ DateUtil.formatDate("yyMMdd") + ".xls");
    }
}
package net.utility;

public class Paging {  
  /**
   * 숫자 형태의 페이징, 1 페이지부터 시작
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]
   * 
   * @param totalRecord   전체 레코드수
   * @param nowPage       현재 페이지
   * @param recordPerPage 페이지당 레코드 수 
   * @return
   */
  public String paging1(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // 블럭당 페이지 수
    int totalPage    = (int)(Math.ceil((double)totalRecord/recordPerPage)); // 전체 페이지 
    int totalGrp     = (int)(Math.ceil((double)totalPage/pagePerBlock));// 전체 그룹
    int nowGrp       = (int)(Math.ceil((double)nowPage/pagePerBlock));    // 현재 그룹
    int startPage    = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작 
    int endPage      = (nowGrp * pagePerBlock);             // 특정 그룹의 페이지 목록 종료  
    
    StringBuffer str = new StringBuffer();
    
    str.append("<style>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:underline; background-color: #ffffff; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("현재 페이지: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    // 이전 10개 페이지로 이동
    int _nowPage = (nowGrp-1) * pagePerBlock; 
    if (nowGrp >= 2){
      str.append("[<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>이전</A>]");
    }

    //페이지 번호 출력( i : 페이지번호 )
    for(int i=startPage; i<=endPage; i++){
    	if (i > totalPage){
    		break;
    	}//if end
     	if (nowPage == i){ // 현재 페이지이면 강조 효과
    		str.append("<span style='font-size: 1.2em; font-weight: bold;'>"+i+"</span>&nbsp;");  
    	}else{
    		str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A>&nbsp;");
    	}//if end
    }//for end
    
    // 다음 10개 페이지로 이동
    _nowPage = (nowGrp * pagePerBlock)+1; 
    if (nowGrp < totalGrp){
      str.append("[<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>다음</A>]");
    }
    str.append("</DIV>");
    
    return str.toString();
  }//paging() end
  
  /**
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]
   * 
   * @param totalRecord   전체 레코드수
   * @param nowPage       현재 페이지
   * @param recordPerPage 페이지당 레코드 수 
   * @return
   */
  public String paging2(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // 블럭당 페이지 수
    int totalPage    = (int)(Math.ceil((double)totalRecord/recordPerPage)); // 전체 페이지   ceil() : 올림
    int totalGrp     = (int)(Math.ceil((double)totalPage/pagePerBlock));// 전체 그룹
    int nowGrp       = (int)(Math.ceil((double)nowPage/pagePerBlock));    // 현재 그룹
    int startPage    = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작 
    int endPage      = (nowGrp * pagePerBlock);             // 특정 그룹의 페이지 목록 종료  
    
    StringBuffer str = new StringBuffer();
    
    str.append("<style>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #CCCCCC; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    background-color: #CCCCCC;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("현재 페이지: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    //이전 10개 페이지로 이동
    int _nowPage = (nowGrp-1) * pagePerBlock; 
    if (nowGrp >= 2){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>이전</span></A>&nbsp;");
    }

    //페이지 번호 출력
    for(int i=startPage; i<=endPage; i++){
    	if (i > totalPage){
    		break;
    	}//if end 
    	if (nowPage == i){
    		str.append("<span class='span_box_2'>&nbsp;"+i+"&nbsp;</span>&nbsp;");
    	}else{
    		str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+i+"'><span class='span_box_1'>&nbsp;"+i+"&nbsp;</span></A>&nbsp;");  
    	}//if end
	}//for end
    
    //다음 10개 페이지로 이동
    _nowPage = (nowGrp * pagePerBlock)+1; 
    if (nowGrp < totalGrp){
    	str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>다음</span></A>&nbsp;");
    }//if end

    str.append("</DIV>");
    
    return str.toString();
  }//paging2() end
  
  /**
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]
   * 
   * @param totalRecord 전체 레코드수
   * @param nowPage     현재 페이지
   * @param recordPerPage 페이지당 레코드 수 
   * @return
   */
  public String paging3(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // 블럭당 페이지 수
    int totalPage    = (int)(Math.ceil((double)totalRecord/recordPerPage)); // 전체 페이지 
    int totalGrp     = (int)(Math.ceil((double)totalPage/pagePerBlock));// 전체 그룹
    int nowGrp       = (int)(Math.ceil((double)nowPage/pagePerBlock));    // 현재 그룹
    int startPage    = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작 
    int endPage      = (nowGrp * pagePerBlock);             // 특정 그룹의 페이지 목록 종료  
    
    StringBuffer str = new StringBuffer();
    
    str.append("<style>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #CCCCCC; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    background-color: #668db4;");
    str.append("    color: #FFFFFF;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("현재 페이지: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    //이전 10개 페이지로 이동
    int _nowPage = (nowGrp-1) * pagePerBlock;
    if (nowGrp >= 2){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>이전</span></A>&nbsp;");
    }

    //페이지 번호 출력
    for(int i=startPage; i<=endPage; i++){
    	if (i > totalPage){
    		break;
    	}//if end
 
    	if (nowPage == i){
    		str.append("<span class='span_box_2'>&nbsp;"+i+"&nbsp;</span>&nbsp;");
    	}else{
    		str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+i+"'><span class='span_box_1'>&nbsp;"+i+"&nbsp;</span></A>&nbsp;");  
    	}//if end
    }//for end
    
    //다음 10개 페이지로 이동
    _nowPage = (nowGrp * pagePerBlock)+1; 
    if (nowGrp < totalGrp){
    	str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>다음</span></A>&nbsp;");
    }
    str.append("</DIV>");
    
    return str.toString();
  }//paging3() end
  
  public String paging2(int totalRecord, int nowPage, int recordPerPage, String filenm){
	    int pagePerBlock = 10; // 블럭당 페이지 수
	    int totalPage    = (int)(Math.ceil((double)totalRecord/recordPerPage)); // 전체 페이지   ceil() : 올림
	    int totalGrp     = (int)(Math.ceil((double)totalPage/pagePerBlock));// 전체 그룹
	    int nowGrp       = (int)(Math.ceil((double)nowPage/pagePerBlock));    // 현재 그룹
	    int startPage    = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작 
	    int endPage      = (nowGrp * pagePerBlock);             // 특정 그룹의 페이지 목록 종료  
	    
	    StringBuffer str = new StringBuffer();
	    
	    str.append("<style>");
	    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
	    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
	    str.append("  #paging A:hover{text-decoration:none; background-color: #CCCCCC; color:black; font-size: 1em;}");
	    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
	    str.append("  .span_box_1{");
	    str.append("    font-size: 1em;");
	    str.append("    border: 1px;");
	    str.append("    border-style: solid;");
	    str.append("    border-color: #cccccc;");
	    str.append("    padding:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
	    str.append("    margin:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
	    str.append("  }");
	    str.append("  .span_box_2{");
	    str.append("    background-color: #CCCCCC;");
	    str.append("    font-size: 1em;");
	    str.append("    border: 1px;");
	    str.append("    border-style: solid;");
	    str.append("    border-color: #cccccc;");
	    str.append("    padding:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
	    str.append("    margin:0px 0px 0px 0px; /*위, 오른쪽, 아래, 왼쪽*/");
	    str.append("  }");
	    str.append("</style>");
	    str.append("<DIV id='paging'>");
	    //str.append("현재 페이지: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

	    //이전 10개 페이지로 이동
	    int _nowPage = (nowGrp-1) * pagePerBlock; 
	    if (nowGrp >= 2){
	      str.append("<A href='./"+filenm+"?nowPage="+_nowPage+"'><span class='span_box_1'>이전</span></A>&nbsp;");
	    }

	    //페이지 번호 출력
	    for(int i=startPage; i<=endPage; i++){
	    	if (i > totalPage){
	    		break;
	    	}//if end 
	    	if (nowPage == i){
	    		str.append("<span class='span_box_2'>&nbsp;"+i+"&nbsp;</span>&nbsp;");
	    	}else{
	    		str.append("<A href='./"+filenm+"?nowPage="+i+"'><span class='span_box_1'>&nbsp;"+i+"&nbsp;</span></A>&nbsp;");  
	    	}//if end
		}//for end
	    
	    //다음 10개 페이지로 이동
	    _nowPage = (nowGrp * pagePerBlock)+1; 
	    if (nowGrp < totalGrp){
	    	str.append("<A href='./"+filenm+"nowPage="+_nowPage+"'><span class='span_box_1'>다음</span></A>&nbsp;");
	    }//if end

	    str.append("</DIV>");
	    
	    return str.toString();
	  }//paging2() end
  
  public String paging4(int totalRecord, int nowPage, int recordPerPage, String filenm){
	    int pagePerBlock = 1; // 블럭당 페이지 수
	    int totalPage    = (int)(Math.ceil((double)totalRecord/recordPerPage)); // 전체 페이지   ceil() : 올림
	    int totalGrp     = (int)(Math.ceil((double)totalPage/pagePerBlock));// 전체 그룹
	    int nowGrp       = (int)(Math.ceil((double)nowPage/pagePerBlock));    // 현재 그룹
	    int startPage    = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작 
	    int endPage      = (nowGrp * pagePerBlock);             // 특정 그룹의 페이지 목록 종료  
	    
	    StringBuffer str = new StringBuffer();
	    //str.append("현재 페이지: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");


	    //페이지 번호 출력
	    for(int i=startPage; i<=endPage; i++){
	    	if (i > totalPage){
	    		break;
	    	}//if end 
	    	if (nowPage == i){
	    		str.append("<span class='span_box_2'>&nbsp;"+i+"&nbsp;</span>&nbsp;");
	    	}else{
	    		str.append("<A href='./"+filenm+"?nowPage="+i+"'><span class='span_box_1'>&nbsp;"+i+"&nbsp;</span></A>&nbsp;");  
	    	}//if end
		}//for end
	    
	    return str.toString();
	  }//paging3() end
  
}//class end 

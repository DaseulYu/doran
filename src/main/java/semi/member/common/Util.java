package semi.member.common;

public class Util {
	
	// 개행 문자 -> <br> 변경 메서드
	public static String newLineHandling(String content) {
		return content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	}
	
	// XSS
	public static String XSSHandling(String content) {
		
		if(content != null) {
			
			content = content.replaceAll("&", "&amp;"); 
			content = content.replaceAll("<", "&lt;"); 
			content = content.replaceAll(">", "&gt;"); 
			
			content = content.replaceAll("\"", "&quot;");
		}
		return content;
	}
}

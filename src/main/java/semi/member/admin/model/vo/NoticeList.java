package semi.member.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeList {
	
	private int noticeNo;
	private String noticeName;
	private String noticeTitle;
	private String memberNickname;
	private String createDate;
	private char noticeState;
	private int readCount;

}

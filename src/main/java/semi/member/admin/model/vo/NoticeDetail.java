package semi.member.admin.model.vo;

import java.util.List;

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
public class NoticeDetail {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String createDate;
	private int readCount;
	private String memberNickname;
	private String profileImage;
	private int memberNo;
	private String noticeName;
	private List<NoticeImage> imageList;
	

}
